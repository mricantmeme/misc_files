package com.dicv.truck.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dicv.truck.model.AlertSettings;
import com.dicv.truck.model.BatteryDisconnect;
import com.dicv.truck.model.BatteryHealth;
import com.dicv.truck.model.FuelDropAlert;
import com.dicv.truck.model.Notification;
import com.dicv.truck.repo.AlertSettingsRepo;
import com.dicv.truck.repo.BatteryDisconnectRepo;
import com.dicv.truck.repo.BatteryHealthRepo;
import com.dicv.truck.repo.FuelDropRepo;
import com.dicv.truck.repo.NotificationRepo;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.EnumALertType;

@Service
public class DicvSchedular {

	@Value("${alert_mail}")
	private String alertMail;

	@Autowired
	private NotificationRepo notiRepo;

	@Autowired
	private SendMailService sendMailService;

	@Autowired
	private FuelDropRepo fuelDropRepo;

	@Autowired
	private AlertSettingsRepo alertSettingsRepo;

	@Autowired
	private BatteryHealthRepo batteryHealthRepo;

	@Autowired
	private BatteryDisconnectRepo batteryDisconnectRepo;

	private static final Logger LOGGER = LoggerFactory.getLogger(DicvSchedular.class);

	@Scheduled(fixedDelay = 60000)
	private void sendMailNotification() {
		if (alertMail != null && alertMail.equals("Yes")) {
			AlertSettings alert = alertSettingsRepo.getAlertSettings(DicvUtil.getRootAdmin());
			startSendingNotification();
			startFuelDropAlerts(alert);
			startBHAlerts(alert);
			startBatteryDisconnectAlerts(alert);
		}
	}

	private void startBHAlerts(AlertSettings alert) {
		try {
			List<BatteryHealth> results = batteryHealthRepo.getBatteryHealthAlert(alert.getBatteryHealth(),
					new PageRequest(0, 100));

			if (results != null && !results.isEmpty()) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:ss a");
				for (BatteryHealth bh : results) {
					sendBHDropMail(alert, dateFormat, bh);
				}

			}
		} catch (Exception e) {
			LOGGER.error("Error In Notification Scheduler " + e);

		}
	}

	private void startBatteryDisconnectAlerts(AlertSettings alert) {
		try {
			List<BatteryDisconnect> results = batteryDisconnectRepo.getBatteryDisconnectAlert(new PageRequest(0, 100));

			if (results != null && !results.isEmpty()) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:ss a");
				for (BatteryDisconnect bd : results) {
					sendBatteryDisconnectEmail(alert, dateFormat, bd);
				}

			}
		} catch (Exception e) {
			LOGGER.error("Error In Notification Scheduler " + e);

		}
	}

	private void startFuelDropAlerts(AlertSettings alert) {
		try {
			List<FuelDropAlert> results = fuelDropRepo.getFuelDropMailAlert(alert.getFuelDrop(),
					new PageRequest(0, 100));

			if (results != null && !results.isEmpty()) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:ss a");
				for (FuelDropAlert fd : results) {
					sendFuelDropMail(alert, dateFormat, fd);
				}

			}
		} catch (Exception e) {
			LOGGER.error("Error In Notification Scheduler " + e);

		}
	}

	@Transactional
	private void sendBHDropMail(AlertSettings alert, DateFormat dateFormat, BatteryHealth bh) {
		try {

			String subject = "Low Battery Health detected in vehicle " + bh.getVehicle().getRegistrationId();

			String time = (dateFormat.format(bh.getGpsTime().getTime()));

			String msg = "<p>Dear Customer,</p> <p>We have detected low battery health in vehicle "
					+ bh.getVehicle().getRegistrationId() + " . <p>We recommend you to get in touch our "
					+ "service team as early as possible.</p> <p>Time : " + time + " <br>Battery health detected: "
					+ bh.getBatteryLevel() + " % </p> <p>Thank you, <br>BharatBenz " + "connectivity support team </p>";
			String[] supportMail = new String[] { alert.getSuppportMail() };
			boolean isMailSent = sendMailService.sendMail("paras.dicv@gmail.com", supportMail, subject, msg, null);
			if (isMailSent) {
				bh.setEmailSent(1);
			} else {
				bh.setEmailSent(2);
			}
		} catch (Exception e) {
			LOGGER.error("Error In Send Mail  " + e);
			bh.setEmailSent(2);
			batteryHealthRepo.save(bh);
			return;
		}
		batteryHealthRepo.save(bh);
	}

	@Transactional
	private void sendFuelDropMail(AlertSettings alert, DateFormat dateFormat, FuelDropAlert fd) {
		try {
			String subject = "Fuel Drop detected in vehicle " + fd.getVehicle().getRegistrationId();

			String time = (dateFormat.format(fd.getFuelDropTime().getTime()));

			String map = DicvConstants.GOOGLE_MAP_URL;

			String msg = "<p>Dear Customer,</p> <p>We have detected a fuel drop in vehicle "
					+ fd.getVehicle().getRegistrationId() + " . <p>Time : " + time + " <br>Drop detected: "
					+ (fd.getFromLevel() - fd.getToLevel()) + "% from " + fd.getFromLevel() + " % to " + fd.getToLevel()
					+ " % </p> ";
			if (fd.getLatitude() != null && fd.getLongitude() != null) {
				map = map + fd.getLatitude() + "," + fd.getLongitude();
				msg = msg + "<p> Location : <a href='" + map
						+ "'><img src='https://connect.bharatbenz.com/map_icon.png' title='' alt='' width='45px' height='29px' style='vertical-align:text-bottom' /></a></p> ";
			}
			msg = msg + "  <p>Thank you, <br>BharatBenz " + "connectivity support team </p>";

			String[] supportMail = new String[] { alert.getSuppportMail() };
			boolean isMailSent = sendMailService.sendMail("paras.dicv@gmail.com", supportMail, subject, msg, null);
			if (isMailSent) {
				fd.setEmailSent(1);
			} else {
				fd.setEmailSent(2);
			}
		} catch (Exception e) {
			LOGGER.error("Error In Send Mail  " + e);
			fd.setEmailSent(2);
			fuelDropRepo.save(fd);
			return;
		}
		fuelDropRepo.save(fd);
	}

	private Integer sendOverSpeedMail(DateFormat dateFormat, Notification notification) {
		try {
			String subject = "Over Speed detected in vehicle " + notification.getVehicle().getRegistrationId();

			String time = (dateFormat.format(notification.getEventGpsTime().getTime()));

			String map = DicvConstants.GOOGLE_MAP_URL;

			String msg = "<p>Dear Customer,</p> <p>We have detected a over speed in vehicle "
					+ notification.getVehicle().getRegistrationId() + " .</p> <p> Speed : "
					+ notification.getReceivedValue() + " KM </p> " + " <p>Time : " + time + "</p> ";
			if (notification.getGeoLatitude() != null && notification.getGeoLongitute() != null) {
				map = map + notification.getGeoLatitude() + "," + notification.getGeoLongitute();
				msg = msg + "<p> Location : <a href='" + map
						+ "'><img src='https://connect.bharatbenz.com/map_icon.png' title='' alt='' width='45px' height='29px' style='vertical-align:text-bottom' /></a></p> ";
			}
			msg = msg + "  <p>Thank you, <br>BharatBenz " + "connectivity support team </p>";
			String[] supportMail = new String[] { notification.getVehicle().getDicvUser().getEmail() };
			boolean isMailSent = sendMailService.sendMail("paras.dicv@gmail.com", supportMail, subject, msg, null);
			if (isMailSent) {
				return 2;
			} else {
				return 3;
			}
		} catch (Exception e) {
			LOGGER.error("Error In Send Mail  " + e);
			return 3;
		}
	}

	@Transactional
	private void startSendingNotification() {
		try {
			List<Notification> results = notiRepo.getUnSentNotification(new ArrayList<Integer>(Arrays.asList(1, -1)),
					new PageRequest(0, 100));

			if (results != null && !results.isEmpty()) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:ss a");
				for (Notification notification : results) {
					try {
						if (EnumALertType.OVER_SPEED.getAlertType()
								.equals(notification.getAlertType().getAlertType())) {
							notification.setEmailAlert(sendOverSpeedMail(dateFormat, notification));
						} else {
							String email = notification.getVehicle().getDicvUser().getEmail();
							String message = notification.getMessage();
							boolean isMailSent = false;
							if (email != null) {
								isMailSent = sendMailService.sendMail("paras.dicv@gmail.com", email,
										"Vehicle Notification", message);
							}
							if (isMailSent) {
								notification.setEmailAlert(2);
							} else {
								notification.setEmailAlert(3);
							}
						}
					} catch (Exception e) {
						LOGGER.error("Error In Send Mail  " + notification.getNotificationId() + " " + e.getMessage());
						notification.setEmailAlert(3);
						notiRepo.save(notification);
						continue;
					}
					notiRepo.save(notification);
				}

			}
		} catch (Exception e) {
			LOGGER.error("Error In Notification Scheduler " + e);

		}
	}

	@Transactional
	private void sendBatteryDisconnectEmail(AlertSettings alert, DateFormat dateFormat, BatteryDisconnect bd) {
		try {
			String subject = "Main Battery Disconnect detected in vehicle " + bd.getVehicle().getRegistrationId();

			String time = (dateFormat.format(bd.getGpsTime().getTime()));

			String map = DicvConstants.GOOGLE_MAP_URL;

			subject = "Main Battery Disconnect detected in vehicle " + bd.getVehicle().getRegistrationId();

			String msg = "<p>Dear Customer,</p> <p>We have detected battery disconnect issue in vehicle "
					+ bd.getVehicle().getRegistrationId() + " . <p>We recommend you to get in touch our "
					+ "service team as early as possible.</p> <p>Time : " + time
					+ "</p><br/> <p>Thank you, <br>BharatBenz " + "connectivity support team </p>";
			if (bd.getLatitude() != null && bd.getLongitude() != null) {
				map = map + bd.getLatitude() + "," + bd.getLongitude();
				msg = msg + "<p> Location : <a href='" + map
						+ "'><img src='https://connect.bharatbenz.com/map_icon.png' title='' alt='' width='45px' height='29px' style='vertical-align:text-bottom' /></a></p> ";
			}
			msg = msg + "  <p>Thank you, <br>BharatBenz " + "connectivity support team </p>";

			String[] supportMail = new String[] { alert.getSuppportMail() };
			boolean isMailSent = sendMailService.sendMail("paras.dicv@gmail.com", supportMail, subject, msg, null);
			if (isMailSent) {
				bd.setEmailSent(1);
			} else {
				bd.setEmailSent(2);
			}
		} catch (Exception e) {
			LOGGER.error("Error In Send Mail  " + e);
			bd.setEmailSent(2);
			batteryDisconnectRepo.save(bd);
			return;
		}
		batteryDisconnectRepo.save(bd);
	}

}
