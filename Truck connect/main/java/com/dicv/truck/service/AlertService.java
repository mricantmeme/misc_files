package com.dicv.truck.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicv.truck.dto.AlertTypeDto;
import com.dicv.truck.dto.AlertTypeListDto;
import com.dicv.truck.dto.GeoNotificationDtlsDto;
import com.dicv.truck.dto.GeoNotificationDto;
import com.dicv.truck.exception.DataNotFoundException;
import com.dicv.truck.model.AlertSettings;
import com.dicv.truck.model.AlertType;
import com.dicv.truck.model.BatteryDisconnect;
import com.dicv.truck.model.BatteryHealth;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.FuelDropAlert;
import com.dicv.truck.model.Notification;
import com.dicv.truck.model.VehicleIdleAlert;
import com.dicv.truck.repo.AlertSettingsRepo;
import com.dicv.truck.repo.AlertTypeRepo;
import com.dicv.truck.repo.BatteryDisconnectRepo;
import com.dicv.truck.repo.BatteryHealthRepo;
import com.dicv.truck.repo.FuelDropRepo;
import com.dicv.truck.repo.NotificationRepo;
import com.dicv.truck.repo.VehicleIdleRepo;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.EnumALertType;
import com.dicv.truck.utility.EnumUserType;

@Service
public class AlertService {

	@Autowired
	private UserService userServices;

	@Autowired
	private NotificationRepo notificationRepo;

	@Autowired
	private AlertTypeRepo alertTypeRepo;

	@Autowired
	private VehicleIdleRepo idleRepo;

	@Autowired
	private FuelDropRepo fuelDropRepo;

	@Autowired
	private AlertSettingsRepo alertSettingsRepo;

	@Autowired
	private BatteryHealthRepo batteryHealthRepo;

	@Autowired
	private BatteryDisconnectRepo batteryDisconnectRepo;

	@Autowired
	private SettingService settingService;

	private static final Logger LOGGER = Logger.getLogger(AlertService.class);

	public GeoNotificationDtlsDto getNotification(Integer userId) {
		GeoNotificationDtlsDto geoNotificationDtls = new GeoNotificationDtlsDto();
		List<GeoNotificationDto> geoFencingMsgList = new ArrayList<GeoNotificationDto>();
		try {
			DicvUser user = userServices.getUser(userId, "getNotification");
			if (user == null) {
				geoNotificationDtls.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				geoNotificationDtls.setMessage(DicvConstants.USER_DOESNOT_EXIST);
				return geoNotificationDtls;
			}
			AlertSettings alert = alertSettingsRepo.getAlertSettings(userId);
			if (alert == null) {
				settingService.saveAlertConfiguration(user);
				alert = alertSettingsRepo.getAlertSettings(userId);
			}
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.HOUR, -12);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy hh:mm a");
			getNotificationList(userId, geoFencingMsgList, calendar);
			getVehicleIdleAlert(userId, geoFencingMsgList, calendar, alert, sdf);
			if (user.getUserType().getUserType().equals(EnumUserType.ROOTADMIN.getUserType())) {
				getFuelDropAlert(userId, geoFencingMsgList, calendar, alert, sdf);
				getBatteryHealth(userId, geoFencingMsgList, calendar, alert, sdf);
			}
			getBatteryDisconnect(userId, geoFencingMsgList, calendar, sdf);
			if (geoFencingMsgList != null && geoFencingMsgList.size() > 0) {
				Collections.sort(geoFencingMsgList,
						(p1, p2) -> p1.getReceivedDateTime().compareTo(p2.getReceivedDateTime()));
				geoNotificationDtls.setGeoFencingMsgList(geoFencingMsgList);
				geoNotificationDtls.setStatus(HttpServletResponse.SC_OK);
				geoNotificationDtls.setMessage(DicvConstants.DATA_FOUND_MSG);
			} else {
				geoNotificationDtls.setStatus(HttpServletResponse.SC_NO_CONTENT);
				geoNotificationDtls.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			}
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, "Exception in Server to Get Notification " + e.getMessage());
		}
		return geoNotificationDtls;
	}

	private void getNotificationList(Integer userId, List<GeoNotificationDto> geoFencingMsgList, Calendar calendar) {
		GeoNotificationDto geoNotification;
		List<Notification> notificationList = notificationRepo.getNotification(userId, calendar);
		if (notificationList != null && !notificationList.isEmpty()) {
			for (Notification notification : notificationList) {
				geoNotification = new GeoNotificationDto();
				geoNotification.setVehicleId(notification.getVehicle().getVehicleId());
				geoNotification.setMessage(notification.getMessage());
				geoNotification.setStatus(notification.getStatus());
				if (notification.getAlertType() != null)
					geoNotification.setType(notification.getAlertType().getAlertType());
				geoNotification.setReceivedDateTime(notification.getEventGpsTime());
				geoNotification.setReadDateTime(notification.getReadDateTime());
				geoFencingMsgList.add(geoNotification);
			}
		}
	}

	private void getVehicleIdleAlert(Integer userId, List<GeoNotificationDto> geoFencingMsgList, Calendar calendar,
			AlertSettings alert, SimpleDateFormat sdf) {
		GeoNotificationDto geoNotification;
		List<VehicleIdleAlert> idleList = idleRepo.getVehicleIdle(userId, calendar, alert.getVehicleIdleTime());
		if (idleList != null && !idleList.isEmpty()) {
			for (VehicleIdleAlert idle : idleList) {
				geoNotification = new GeoNotificationDto();
				Date resultdate = new Date(idle.getIdleStartTime().getTimeInMillis());
				geoNotification.setVehicleId(idle.getVehicle().getVehicleId());
				geoNotification.setType(EnumALertType.VEHICLE_IDLE_ALERT.getAlertType());
				geoNotification.setReceivedDateTime(idle.getIdleStartTime());
				geoNotification
						.setMessage((sdf.format(resultdate)).toString() + "  " + idle.getVehicle().getRegistrationId()
								+ " Vehicle Idle For  " + (idle.getTimeSpent() / 60000) + " mins");
				geoFencingMsgList.add(geoNotification);
			}
		}
	}

	private void getFuelDropAlert(Integer userId, List<GeoNotificationDto> geoFencingMsgList, Calendar calendar,
			AlertSettings alert, SimpleDateFormat sdf) {
		GeoNotificationDto geoNotification;
		List<FuelDropAlert> fuelDropList = fuelDropRepo.getFuelDropAlert(userId, calendar, alert.getFuelDrop());
		if (fuelDropList != null && !fuelDropList.isEmpty()) {

			for (FuelDropAlert fuelDrop : fuelDropList) {
				geoNotification = new GeoNotificationDto();
				Date resultdate = new Date(fuelDrop.getFuelDropTime().getTimeInMillis());
				geoNotification.setType(EnumALertType.FUEL_DROP_ALERT.getAlertType());
				geoNotification.setReceivedDateTime(fuelDrop.getFuelDropTime());
				geoNotification.setMessage((sdf.format(resultdate)).toString() + "  "
						+ fuelDrop.getVehicle().getRegistrationId() + " Fuel Drop Detected ");
				geoFencingMsgList.add(geoNotification);
			}
		}
	}

	private void getBatteryHealth(Integer userId, List<GeoNotificationDto> geoFencingMsgList, Calendar calendar,
			AlertSettings alert, SimpleDateFormat sdf) {
		GeoNotificationDto geoNotification;
		List<BatteryHealth> batteryHealthList = batteryHealthRepo.getBatteryHealthAlert(userId, calendar,
				alert.getBatteryHealth());
		if (batteryHealthList != null && !batteryHealthList.isEmpty()) {
			for (BatteryHealth bh : batteryHealthList) {
				geoNotification = new GeoNotificationDto();
				Date resultdate = new Date(bh.getGpsTime().getTimeInMillis());
				geoNotification.setType(EnumALertType.LOW_BATTERY.getAlertType());
				geoNotification.setReceivedDateTime(bh.getGpsTime());
				geoNotification
						.setMessage((sdf.format(resultdate)).toString() + "  " + bh.getVehicle().getRegistrationId()
								+ " Low Battery Health  - " + bh.getBatteryLevel() + " % ");
				geoFencingMsgList.add(geoNotification);
			}
		}
	}

	private void getBatteryDisconnect(Integer userId, List<GeoNotificationDto> geoFencingMsgList, Calendar calendar,
			SimpleDateFormat sdf) {
		GeoNotificationDto geoNotification;
		List<BatteryDisconnect> batteryDisconnectList = batteryDisconnectRepo.getBatteryDisconnectAlert(userId,
				calendar);
		if (batteryDisconnectList != null && !batteryDisconnectList.isEmpty()) {
			for (BatteryDisconnect bh : batteryDisconnectList) {
				geoNotification = new GeoNotificationDto();
				Date resultdate = new Date(bh.getGpsTime().getTimeInMillis());
				geoNotification.setType(EnumALertType.BATTERY_DISCONNECT.getAlertType());
				geoNotification.setReceivedDateTime(bh.getGpsTime());
				geoNotification.setMessage((sdf.format(resultdate)).toString() + "  "
						+ bh.getVehicle().getRegistrationId() + " Main Battery Disconnect ");
				geoFencingMsgList.add(geoNotification);
			}
		}
	}

	public AlertTypeListDto getAlertType() {
		List<AlertType> allAlertType = alertTypeRepo.getAlertTypeList();
		if (null == allAlertType) {
			throw new DataNotFoundException("No alert type Found");
		}
		AlertTypeListDto alertTypeListDto = new AlertTypeListDto();
		List<AlertTypeDto> alertList = new ArrayList<AlertTypeDto>();
		AlertTypeDto alertTypeDto = new AlertTypeDto();
		for (AlertType alertType : allAlertType) {
			alertTypeDto = new AlertTypeDto();
			alertTypeDto.setAlertType(alertType.getAlertType());
			alertTypeDto.setAlertTypeId(alertType.getAlertTypeId());
			alertTypeDto.setDescription(alertType.getDescription());
			alertList.add(alertTypeDto);
		}
		alertTypeListDto.setStatus(HttpServletResponse.SC_OK);
		alertTypeListDto.setMessage(DicvConstants.DATA_FOUND_MSG);
		alertTypeListDto.setAlertTypes(alertList);
		return alertTypeListDto;
	}

}
