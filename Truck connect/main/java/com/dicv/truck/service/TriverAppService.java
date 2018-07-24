package com.dicv.truck.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicv.truck.dto.AppRegistrationDto;
import com.dicv.truck.dto.GpsImeiDtlsDto;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.dto.TripImageUpload;
import com.dicv.truck.dto.TripStartDto;
import com.dicv.truck.dto.TripStopDto;
import com.dicv.truck.model.AlertPreference;
import com.dicv.truck.model.AlertType;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.Dispatch;
import com.dicv.truck.model.Gcm;
import com.dicv.truck.model.GpsImei;
import com.dicv.truck.model.InvoicePhoto;
import com.dicv.truck.model.Notification;
import com.dicv.truck.model.ScheduledTrip;
import com.dicv.truck.model.Trip;
import com.dicv.truck.model.Vehicle;
import com.dicv.truck.repo.AlertPreferenceRepo;
import com.dicv.truck.repo.AlertTypeRepo;
import com.dicv.truck.repo.DispatchRepo;
import com.dicv.truck.repo.GcmRepo;
import com.dicv.truck.repo.GpsImeiRepo;
import com.dicv.truck.repo.InvoicePhotoRepo;
import com.dicv.truck.repo.NotificationRepo;
import com.dicv.truck.repo.TripRepo;
import com.dicv.truck.repo.UserRepo;
import com.dicv.truck.repo.VehicleRepo;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.DriverStatus;
import com.dicv.truck.utility.RecordStatus;
import com.dicv.truck.utility.TripStatus;
import com.dicv.truck.utility.UserStatus;
import com.dicv.truck.utility.VehicleStatus;

@Service
public class TriverAppService {

	@Autowired
	private GpsImeiRepo gpsImeiRepo;

	@Autowired
	private GcmRepo gcmRepo;

	@Autowired
	private VehicleServices vehicleService;

	@Autowired
	private VehicleRepo vehicleRepo;

	@Autowired
	private TripRepo tripRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AlertTypeRepo alertTypeRepo;

	@Autowired
	private AlertPreferenceRepo alertPreferenceRepo;

	@Autowired
	private NotificationRepo notificationRepo;

	@Autowired
	private DispatchRepo dispacthRepo;

	@Autowired
	private InvoicePhotoRepo invoicePhotoRepo;

	private static final Logger LOGGER = LoggerFactory.getLogger(TriverAppService.class);

	public GpsImeiDtlsDto getVehicleImei(Long tabletImei) {
		GpsImeiDtlsDto gpsImeiDtlsDto = new GpsImeiDtlsDto();
		try {

			List<GpsImei> gpsImeiList = gpsImeiRepo.getImeiByTabletImei(tabletImei);
			if (gpsImeiList == null || gpsImeiList.isEmpty()) {
				gpsImeiDtlsDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				gpsImeiDtlsDto.setMessage("No vehicle associated to this gps imei ");
			}

			for (GpsImei gpsImei : gpsImeiList) {
				gpsImeiDtlsDto = new GpsImeiDtlsDto();
				gpsImeiDtlsDto.setGpsImei(gpsImei.getGpsImei());
				if (null != gpsImei.getVehicle()
						&& gpsImei.getVehicle().getIsDeleted() != RecordStatus.DELETED.getStatus()) {
					gpsImeiDtlsDto.setVehicleId(gpsImei.getVehicle().getVehicleId());
					break;
				} else {
					gpsImeiDtlsDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
					gpsImeiDtlsDto.setMessage("No vehicle associated to this gps imei ");
					return gpsImeiDtlsDto;
				}
			}
			gpsImeiDtlsDto.setStatus(HttpServletResponse.SC_OK);
			gpsImeiDtlsDto.setMessage(DicvConstants.DATA_FOUND_MSG);

		} catch (Exception ex) {
			LOGGER.error("Exception in Getting Vehicle Imei ", ex);
			gpsImeiDtlsDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			gpsImeiDtlsDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}

		return gpsImeiDtlsDto;
	}

	@Transactional
	public StatusMessageDto registerForPushNotification(AppRegistrationDto appRegistration) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		Timestamp timestamp = new Timestamp(new Date().getTime());
		Gcm gcm = null;
		Vehicle vehicle = vehicleService.getVehicleDetails(appRegistration.getVehicleId());
		try {
			if (vehicle != null) {
				gcm = new Gcm();
				if (null != vehicle.getGcm() && vehicle.getGcm().getIsDeleted() != RecordStatus.DELETED.getStatus()) {
					gcm = vehicle.getGcm();
				}
				gcm.setRegistrationId(appRegistration.getRegId());
				gcm.setModifiedDateTime(timestamp);
				gcm.setVehicle(vehicle);
				vehicle.setGcm(gcm);
				gcmRepo.save(gcm);
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage("Vehicle does  exist ");
			} else {
				LOGGER.info("Vehicle does not exist ");
				statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				statusMessageDto.setMessage("Vehicle does not exist ");
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in registerForPushNotification ", ex.getMessage());
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}
		return statusMessageDto;

	}

	@Transactional
	public TripStartDto startTrip(TripStartDto tripStartDto) {
		try {
			Vehicle vehicle = null;
			DicvUser dicvUser = null;
			GpsImei gpsImei = null;
			Trip trip = tripRepo.findOne(tripStartDto.getScheduledTripId());

			if (trip == null || trip.getScheduledTrip() == null) {
				tripStartDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				tripStartDto.setMessage("No scheduled trip leg associated to this scheduled trip ");
				return tripStartDto;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
			Date startDateTime = sdf.parse(tripStartDto.getStartDateTime());
			Timestamp startTime = new Timestamp(startDateTime.getTime());
			if (trip.getVehicle() != null && trip.getVehicle().getIsDeleted() != RecordStatus.DELETED.getStatus()) {
				vehicle = trip.getVehicle();
			} else {
				tripStartDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				tripStartDto.setMessage("No vehicle associated to this scheduled trip");
				return tripStartDto;
			}
			gpsImei = vehicle.getGpsImei();
			if (gpsImei == null || gpsImei.getIsDeleted() == RecordStatus.DELETED.getStatus()) {
				tripStartDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				tripStartDto.setMessage("No gps imei associated to this scheduled trip ");
				return tripStartDto;
			}
			if (!(tripStartDto.getGpsImei().equals(gpsImei.getGpsImei()))) {
				tripStartDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				tripStartDto.setMessage(" Invalid GPS IMEI ");
				return tripStartDto;
			}
			trip.setStartLocationLat(vehicle.getCurrentLat());
			trip.setStartLocationLong(vehicle.getCurrentLong());
			trip.setElapsedTime(0);
			trip.setLastUpdateRecvdTime(DicvUtil.getTimestamp());
			trip.setTripStatus(DicvConstants.TRIPSTATUS_RUNNING);
			trip.setTripDistance(0.0);
			trip.setTripStartTime(startTime);
			trip.setCreatedDate(DicvUtil.getTimestamp());
			trip.setModifiedDate(DicvUtil.getTimestamp());
			if (null != trip.getTripDriverUser()
					&& UserStatus.OPEN.getRecordStatusCode().equals(trip.getTripDriverUser().getRecordStatus())) {
				dicvUser = trip.getTripDriverUser();
			} else {
				tripStartDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				tripStartDto.setMessage("No driver associated to this scheduled trip");
				return tripStartDto;
			}
			Dispatch dispatch = saveDispatch(trip);
			trip.setDispatches(dispatch);
			trip.setProcessStatus(1);
			vehicle.setVehicleStatus(VehicleStatus.NOTAVAILABLE.getStatusCode());
			vehicle.setModifiedDateTime(DicvUtil.getTimestamp());
			dicvUser.setUserStatus(DriverStatus.NOTAVAILABLE.getStatusCode());
			dicvUser.setModifiedDate(DicvUtil.getTimestamp());
			// Close All UnScheduled Trips
			Trip newTrip = tripRepo.save(trip);
			vehicleRepo.save(vehicle);
			userRepo.save(dicvUser);
			tripStartDto.setTripId(newTrip.getTripId());
			List<AlertType> typeList = alertTypeRepo.getAlertTypeByType("TRIP_START");
			if (typeList != null && typeList.size() > 0) {
				sdf = new SimpleDateFormat("dd-MMM-yy hh:mm a");
				String msg = sdf.format(DicvUtil.getTimestamp()) + " " + vehicle.getRegistrationId()
						+ " Trip Start Alert ";
				sendNotification(vehicle, vehicle.getDicvUser(), DicvUtil.getTimestamp(), typeList, msg,
						vehicle.getCurrentLat(), vehicle.getCurrentLong());
			}
			tripStartDto.setStatus(HttpServletResponse.SC_OK);
			tripStartDto.setMessage("Trip Started ");
		} catch (Exception e) {
			LOGGER.error("Exception in Trip Start ", e.getMessage());
			tripStartDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			tripStartDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}
		return tripStartDto;
	}

	@Transactional
	private Dispatch saveDispatch(Trip trip) {
		List<Dispatch> dispatchList = dispacthRepo.getDispatch(trip.getTripId());
		Dispatch dispatch = dispatchList.get(0);
		dispatch.setDispatchStatus(DicvConstants.TRIPSTATUS_RUNNING);
		dispatch.setTrip(trip);
		dispatch.setModifiedDateTime(DicvUtil.getTimestamp());
		dispacthRepo.save(dispatch);
		return dispatch;
	}

	@Transactional
	private void sendNotification(Vehicle vehicle, DicvUser user, Date gpsTime, List<AlertType> typeList, String msg,
			Double gpsLatitude, Double gpsLongitude) {
		List<AlertPreference> alertPerf = alertPreferenceRepo.getAlertPreferenceList(user.getUserId());
		Notification not = new Notification();
		not.setAlertType(typeList.get(0));
		not.setEmailAlert(alertPerf.get(0).getPickupEmailAlert());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Timestamp(gpsTime.getTime()));
		not.setEventGpsTime(cal);
		not.setGeoLatitude(gpsLatitude);
		not.setGeoLongitute(gpsLongitude);
		not.setMessage(msg);
		not.setNotificationType("Info");
		not.setReceivedDateTime(Calendar.getInstance());
		not.setSmsAlert(alertPerf.get(0).getPickupSmsAlert());
		not.setStatus("NEW");
		not.setDicvUser(user);
		not.setVehicle(vehicle);
		not.setWebAlert(alertPerf.get(0).getPickupWebAlert());
		notificationRepo.save(not);
	}

	@Transactional
	public StatusMessageDto stopTrip(TripStopDto tripStopDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			LOGGER.info("Inside Trip Stop " + tripStopDto.getTripId());
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			Trip trip = tripRepo.getOne(tripStopDto.getTripId());

			if (trip == null) {
				LOGGER.info("Trip Not Found");
				statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				statusMessageDto.setMessage("Trip Not Found");
				return statusMessageDto;
			}

			if (TripStatus.COMPLETED.getStatusCode().equals(trip.getTripStatus())) {

				statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				statusMessageDto.setMessage("Trip already stopped !!");
				return statusMessageDto;
			}

			if (trip.getVehicle() == null || trip.getVehicle().getVehicleId() == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				statusMessageDto.setMessage("No vehicle associated to this scheduled trip");
				return statusMessageDto;
			}
			Vehicle vehicle = trip.getVehicle();
			if (vehicle.getGpsImei() == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				statusMessageDto.setMessage("No gps imei associated to this scheduled trip ");
				return statusMessageDto;
			}
			GpsImei gpsImei = vehicle.getGpsImei();
			if (gpsImei.getGpsImei() == null || !(tripStopDto.getGpsImei().equals(gpsImei.getGpsImei()))) {

				statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				statusMessageDto.setMessage("GPS imei number does not exist ");
				return statusMessageDto;
			}
			if (trip.getScheduledTrip() == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				statusMessageDto.setMessage("No scheduled trip leg associated to this scheduled trip ");
				return statusMessageDto;
			}
			trip.setTripStatus(TripStatus.COMPLETED.getStatusCode());
			trip.setTripEndTime(DicvUtil.getTimestamp());
			trip.setProcessStatus(2);
			trip.setModifiedDate(DicvUtil.getTimestamp());
			if (trip.getTripDriverUser() == null
					|| UserStatus.DELETED.getRecordStatusCode().equals(trip.getTripDriverUser().getRecordStatus())) {
				statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				statusMessageDto.setMessage("No driver associated to this trip ");
				return statusMessageDto;

			}
			List<Dispatch> dispatchList = dispacthRepo.getDispatch(trip.getTripId());
			if (dispatchList == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				statusMessageDto.setMessage("No dispatch associated to this trip ");
				return statusMessageDto;

			}
			DicvUser dicvUser = trip.getTripDriverUser();
			dicvUser.setUserStatus(DriverStatus.AVAILABLE.getStatusCode());
			dicvUser.setModifiedDate(DicvUtil.getTimestamp());

			userRepo.save(dicvUser);
			vehicle.setVehicleStatus(VehicleStatus.AVAILABLE.getStatusCode());
			vehicle.setModifiedDateTime(DicvUtil.getTimestamp());
			vehicleRepo.save(vehicle);
			trip.getScheduledTrip().setTripStatus(TripStatus.COMPLETED.getStatusCode());
			trip.getScheduledTrip().setModifiedDateTime(DicvUtil.getTimestamp());
			trip.getScheduledTrip().setTrip(trip);
			trip.setStopLocationLat(vehicle.getCurrentLat());
			trip.setStopLocationLong(vehicle.getCurrentLong());
			tripRepo.save(trip);
			List<AlertType> typeList = alertTypeRepo.getAlertTypeByType("TRIP_START");
			if (typeList != null && typeList.size() > 0) {
				sdf = new SimpleDateFormat("dd-MMM-yy hh:mm a");
				String msg = sdf.format(DicvUtil.getTimestamp()) + " " + vehicle.getRegistrationId()
						+ " Trip Start Alert ";
				sendNotification(vehicle, vehicle.getDicvUser(), DicvUtil.getTimestamp(), typeList, msg,
						trip.getStartLocationLat(), trip.getStopLocationLong());

			}
			statusMessageDto.setStatus(HttpServletResponse.SC_OK);
			statusMessageDto.setMessage("Trip Stopped ");
		} catch (Exception e) {
			LOGGER.error("Exception in Trip Stop ", e);
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}

		return statusMessageDto;

	}

	@Transactional
	public StatusMessageDto imageUpload(TripImageUpload tripImageUpload) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		ScheduledTrip scheduledTrip = null;
		Trip trip = tripRepo.findOne(tripImageUpload.getTripId());
		List<Dispatch> dispacthList = dispacthRepo.getDispatch(tripImageUpload.getTripId());
		try {
			if (dispacthList == null || dispacthList.isEmpty()) {
				statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				statusMessageDto.setMessage("Dispacth Not Available");
				return statusMessageDto;
			}

			if (null == trip.getTripDriverUser()) {
				LOGGER.info(" No driver associated to this trip ");
				statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				statusMessageDto.setMessage("No driver associated to this trip ");
				return statusMessageDto;
			}
			for (Dispatch dispatch : dispacthList) {
				InvoicePhoto invoicePhoto = new InvoicePhoto();
				dispatch.setGoodsWeight(tripImageUpload.getGoodsWeight());
				byte[] bytes = tripImageUpload.getFile().getBytes();
				invoicePhoto.setInvoicePhoto(bytes);
				invoicePhoto.setDispatch(dispatch);
				invoicePhoto.setInvoiceType(tripImageUpload.getType());
				invoicePhoto.setUpdateDateTime(DicvUtil.getTimestamp());
				invoicePhoto.setCreatedDate(DicvUtil.getTimestamp());
				invoicePhoto.setDicvUserCreatedBy(trip.getTripDriverUser().getUserId());
				invoicePhoto.setDicvUserUpdatedBy(trip.getTripDriverUser().getUserId());
				invoicePhoto = invoicePhotoRepo.save(invoicePhoto);

				if (null != trip.getScheduledTrip()
						&& trip.getScheduledTrip().getIsDeleted() != RecordStatus.DELETED.getStatus()) {
					scheduledTrip = trip.getScheduledTrip();
					if (null != scheduledTrip.getDicvUser() && UserStatus.OPEN.getRecordStatusCode()
							.equals(scheduledTrip.getDicvUser().getRecordStatus())) {
						if (null != trip.getVehicle()
								&& trip.getVehicle().getIsDeleted() != RecordStatus.DELETED.getStatus()) {
							if (DicvConstants.PICKUP_MSG.equalsIgnoreCase(tripImageUpload.getType())) {
								List<AlertType> typeList = alertTypeRepo.getAlertTypeByType("PICKUP");
								if (typeList != null && typeList.size() > 0) {
									SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy hh:mm a");
									String msg = sdf.format(DicvUtil.getTimestamp()) + " "
											+ trip.getVehicle().getRegistrationId() + " Pickup Alert ";
									sendNotification(trip.getVehicle(), trip.getVehicle().getDicvUser(),
											DicvUtil.getTimestamp(), typeList, msg, trip.getStartLocationLat(),
											trip.getStopLocationLong());

								}
							} else if (DicvConstants.DISPATCH_MSG.equalsIgnoreCase(tripImageUpload.getType())) {
								List<AlertType> typeList = alertTypeRepo.getAlertTypeByType("DELIVERY");
								if (typeList != null && typeList.size() > 0) {
									SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy hh:mm a");
									String msg = sdf.format(DicvUtil.getTimestamp()) + " "
											+ trip.getVehicle().getRegistrationId() + " Delivery Alert ";
									sendNotification(trip.getVehicle(), trip.getVehicle().getDicvUser(),
											DicvUtil.getTimestamp(), typeList, msg, trip.getStartLocationLat(),
											trip.getStopLocationLong());

								}
							}
						} else {
							statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
							statusMessageDto.setMessage(" No vehicle associated to this trip ");
							return statusMessageDto;
						}
					} else {
						statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
						statusMessageDto.setMessage("No owner associated to this trip ");
						return statusMessageDto;
					}
				}
				if (invoicePhoto.getInvoicePhotoId() != null) {
					statusMessageDto.setStatus(HttpServletResponse.SC_OK);
					statusMessageDto.setMessage("InvoicePhoto have been updated successfully.");
				} else {
					statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
					statusMessageDto.setMessage("InvoicePhoto Not uploaded");
				}

			}

		} catch (Exception e) {
			LOGGER.error("Exception in Image Upload  ", e);
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return statusMessageDto;
		}
		return statusMessageDto;
	}

}
