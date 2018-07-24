package com.dicv.truck.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dicv.truck.dto.Address;
import com.dicv.truck.dto.DriverCurrentStatus;
import com.dicv.truck.dto.DriversStatus;
import com.dicv.truck.dto.FavoriteLocationDtlsDto;
import com.dicv.truck.dto.FavoriteLocationDto;
import com.dicv.truck.dto.FavoriteLocationListDto;
import com.dicv.truck.dto.RoutePlanDtlsDto;
import com.dicv.truck.dto.RoutePlanDto;
import com.dicv.truck.dto.ScheduledTripEditDto;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.dto.VehicleCurrentStatus;
import com.dicv.truck.dto.VehiclesStatus;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.Dispatch;
import com.dicv.truck.model.FavoriteLocation;
import com.dicv.truck.model.Gcm;
import com.dicv.truck.model.GpsImei;
import com.dicv.truck.model.LoadCategory;
import com.dicv.truck.model.ScheduledTrip;
import com.dicv.truck.model.Trip;
import com.dicv.truck.model.Vehicle;
import com.dicv.truck.repo.DispatchRepo;
import com.dicv.truck.repo.FavouriteLocationRepo;
import com.dicv.truck.repo.GcmRepo;
import com.dicv.truck.repo.LoadCategoryRepo;
import com.dicv.truck.repo.ScheduledTripRepo;
import com.dicv.truck.repo.TripRepo;
import com.dicv.truck.repo.VehicleRepo;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.EnumUserType;
import com.dicv.truck.utility.TripStatus;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

@Service
public class ScheduleTripService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTripService.class);

	@Autowired
	private UserService userService;

	@Autowired
	private VehicleServices vehicleService;

	@Autowired
	private VehicleRepo vehicleRepo;

	@Autowired
	private ScheduledTripRepo scheduledTripRepo;

	@Autowired
	private TripRepo tripRepo;

	@Autowired
	private DispatchRepo dispacthRepo;

	@Autowired
	private FavouriteLocationRepo favRepo;

	@Autowired
	private LoadCategoryRepo loadCategoryRepo;

	@Autowired
	private GoogleAPIService googleAPIService;

	@Value("${google_server_key}")
	private String googleServerKey;

	@Autowired
	private GcmRepo gcmRepo;

	public DriversStatus getAllDriverStatus(Integer userId, Integer scheduledTripId, Date fromDate, Date toDate) {
		DriversStatus driversStatus = new DriversStatus();
		DicvUser user = userService.getUser(userId, "Get Driver Status");
		try {
			if (user != null) {
				List<DicvUser> userList = userService.getUserByRole(EnumUserType.DRIVER.getUserType(), userId,
						user.getUserType().getUserType());
				if (userList == null || userList.isEmpty()) {
					driversStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
					driversStatus.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				}
				List<Trip> schList = tripRepo.getActiveTripList(userId, fromDate, toDate);

				List<DriverCurrentStatus> driverMap = new ArrayList<DriverCurrentStatus>();
				List<Integer> notAvailble = new ArrayList<Integer>();
				if (!CollectionUtils.isEmpty(schList)) {
					for (Trip sl : schList) {
						if (sl.getTripDriverUser() != null)
							notAvailble.add(sl.getTripDriverUser().getUserId());
					}
				}

				for (DicvUser driver : userList) {
					DriverCurrentStatus dcs = new DriverCurrentStatus();
					dcs.setDriverId(driver.getUserId());
					dcs.setName(driver.getUserName());
					dcs.setMobileNumber(driver.getMobile());
					if (driver.getDicvGroup() != null)
						dcs.setGroupName(driver.getDicvGroup().getGroupName());
					dcs.setStatus("AVAILABLE");
					if (notAvailble.contains(driver.getUserId()))
						dcs.setStatus("NOTAVAILABLE");
					driverMap.add(dcs);
				}
				driversStatus.setDriverList(driverMap);
				driversStatus.setStatus(HttpServletResponse.SC_OK);
				driversStatus.setMessage(DicvConstants.DATA_FOUND_MSG);
				return driversStatus;
			} else {
				driversStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
				driversStatus.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Driverv Status ", e);
			driversStatus.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			driversStatus.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}
		return driversStatus;
	}

	public VehiclesStatus getAllVehicleStatus(Integer userId, Integer scheduledTripId, Date fromDate, Date toDate) {
		VehiclesStatus vehiclesStatus = new VehiclesStatus();
		DicvUser user = userService.getUser(userId, "Get Vehicle Status");
		try {
			if (user != null) {
				List<Vehicle> vehList = null;
				if (user.getUserType().getUserType().equals(EnumUserType.ROOTADMIN.getUserType()))
					vehList = vehicleRepo.getVehicleList();
				else
					vehList = vehicleRepo.getVehicleList(userId);
				if (vehList == null || vehList.isEmpty()) {
					vehiclesStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
					vehiclesStatus.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				}
				List<Trip> schList = tripRepo.getActiveTripList(userId, fromDate, toDate);

				List<VehicleCurrentStatus> vcsList = new ArrayList<VehicleCurrentStatus>();
				List<Integer> notAvailble = new ArrayList<Integer>();
				if (!CollectionUtils.isEmpty(schList)) {
					for (Trip sl : schList) {
						notAvailble.add(sl.getVehicle().getVehicleId());
					}
				}

				for (Vehicle veh : vehList) {
					VehicleCurrentStatus vehicleCurrentStatus = new VehicleCurrentStatus();
					vehicleCurrentStatus.setVehicleId(veh.getVehicleId());
					vehicleCurrentStatus.setCurrentLat(veh.getCurrentLat());
					vehicleCurrentStatus.setCurrentLong(veh.getCurrentLong());
					vehicleCurrentStatus.setDescription(veh.getDescription());
					if (veh.getGpsImei() != null)
						vehicleCurrentStatus.setGpsImei(veh.getGpsImei().getGpsImei());
					if (veh.getDicvRegion() != null)
						vehicleCurrentStatus.setRegionName(veh.getDicvRegion().getRegionName());
					vehicleCurrentStatus.setRegistrationId(veh.getRegistrationId());
					if (veh.getDicvType() != null)
						vehicleCurrentStatus.setVehicleType(veh.getDicvType().getTypeName());
					vehicleCurrentStatus.setStatus("AVAILABLE");
					if (notAvailble.contains(veh.getVehicleId()))
						vehicleCurrentStatus.setStatus("NOTAVAILABLE");
					vcsList.add(vehicleCurrentStatus);
				}
				vehiclesStatus.setVcsList(vcsList);
				vehiclesStatus.setStatus(HttpServletResponse.SC_OK);
				vehiclesStatus.setMessage(DicvConstants.DATA_FOUND_MSG);
				return vehiclesStatus;
			} else {
				vehiclesStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
				vehiclesStatus.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Vehicle Status ", e);
			vehiclesStatus.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			vehiclesStatus.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}
		return vehiclesStatus;
	}

	public RoutePlanDtlsDto getRoutePlan(Integer scheduledTripId, Date currentDate) {
		RoutePlanDtlsDto routePlanDtlsDto = new RoutePlanDtlsDto();
		Date fromDate = DicvUtil.addDays(currentDate, -2);
		Date toDate = DicvUtil.addDays(currentDate, 2);
		try {
			Trip trip = tripRepo.getRoutePlan(scheduledTripId.longValue(), fromDate, toDate);

			if (trip != null) {
				routePlanDtlsDto = new RoutePlanDtlsDto();
				routePlanDtlsDto.setScheduledTripId(trip.getScheduledTrip().getScheduledTripId());
				routePlanDtlsDto.setStartPointLat(trip.getStartLocationLat());
				routePlanDtlsDto.setStartPointLong(trip.getStartLocationLong());
				routePlanDtlsDto.setEndPointLat(trip.getStopLocationLat());
				routePlanDtlsDto.setEndPointLong(trip.getStopLocationLong());
				routePlanDtlsDto.setFromAddress(trip.getFromAddress());
				routePlanDtlsDto.setToAddress(trip.getToAddress());
				routePlanDtlsDto.setIsSms(trip.getScheduledTrip().getIssms());
				routePlanDtlsDto.setIsEmail(trip.getScheduledTrip().getIsemail());
				routePlanDtlsDto.setStartDate(DicvUtil.dateToStringFormat(trip.getScheduledTrip().getFromDate()));
				routePlanDtlsDto.setEndDate(DicvUtil.dateToStringFormat(trip.getScheduledTrip().getToDate()));
				routePlanDtlsDto.setScheduledTripFlag(trip.getScheduledTrip().getScheduledTripFlag());
				routePlanDtlsDto.setStatus(HttpServletResponse.SC_OK);
				routePlanDtlsDto.setMessage(DicvConstants.DATA_FOUND_MSG);
			} else {
				LOGGER.error(" No dispatch associated to this scheduled trip ");
				routePlanDtlsDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				routePlanDtlsDto.setMessage(" No dispatch associated to this scheduled trip ");
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Vehicle Status ", e);
			routePlanDtlsDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			routePlanDtlsDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}
		return routePlanDtlsDto;
	}

	public StatusMessageDto setRoutePlan(RoutePlanDto routePlan) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HHmmss");
			Date toDate = DicvUtil.stringToDate(routePlan.getToDate());
			Date fromDate = DicvUtil.stringToDate(routePlan.getFromDate());
			long diffDaysBetFromAndToDate = DicvUtil.getDifferenceDays(fromDate, toDate);
			LOGGER.info("Difference between fromDate and toDate in days : " + diffDaysBetFromAndToDate);
			if (diffDaysBetFromAndToDate > 90) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto
						.setMessage("Difference between fromDate and toDate should not be greater than ninety days");
				return statusMessageDto;
			}
			if (diffDaysBetFromAndToDate < 0) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage("From Date should not be less than To Date");
				return statusMessageDto;
			}
			Timestamp timestamp = new Timestamp(new Date().getTime());
			String currDateStr = dateFormat.format(timestamp.getTime());
			Date currentDate = DicvUtil.stringToDate(currDateStr);
			long diffDaysBetCurrAndFromDate = DicvUtil.getDifferenceDays(currentDate, fromDate);
			LOGGER.info("Difference between currentDate and fromDate in days : " + diffDaysBetCurrAndFromDate);
			if (diffDaysBetCurrAndFromDate > 90) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage(
						"Difference between currentDate and fromDate should not be greater than ninety days");
				return statusMessageDto;
			}

			if ((diffDaysBetCurrAndFromDate < 0)) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage("From Date should not be less than Current Date");
				return statusMessageDto;
			}
			DicvUser dicvUserOwner = userService.getUser(routePlan.getUserId(), "Schedule Trip Owner");
			Vehicle vehicle = vehicleService.getVehicleDetails(routePlan.getVehicleId());
			DicvUser dicvUserDriver = userService.getUser(routePlan.getDriverId(), "Schedule Trip Driver");
			if (vehicle == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage("Vehicle does not exist");
				return statusMessageDto;
			}
			GpsImei gpsImei = vehicle.getGpsImei();
			if (null == gpsImei || gpsImei.getGpsImei() == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage("GPS IMEI doesn't exist for the given vehicle ");
				return statusMessageDto;

			}
			Trip trip = saveScheduledTripDetails(routePlan, toDate, fromDate, timestamp, dicvUserOwner, vehicle,
					dicvUserDriver);
			String regId = getGcmRegistrationByVehicle(routePlan.getVehicleId());
			String message = "New scheduledTripId : " + trip.getTripId();
			if (regId != null)
				sendGcmNotification(message, regId);
			LOGGER.info("The Vehicle route plan has been successfully uploaded !! " + trip.getTripId());
			statusMessageDto.setStatus(HttpServletResponse.SC_OK);
			statusMessageDto.setMessage("Trip Scheduled Successfully");
		} catch (Exception ex) {
			LOGGER.error("Exception in Add/Update Route ", ex);
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return statusMessageDto;
		}
		return statusMessageDto;
	}

	private Trip saveScheduledTripDetails(RoutePlanDto routePlan, Date toDate, Date fromDate, Timestamp timestamp,
			DicvUser dicvUserOwner, Vehicle vehicle, DicvUser dicvUserDriver) {
		ScheduledTrip scheduledTrip = new ScheduledTrip();
		if (null != routePlan.getLoadCategoryId()) {
			LoadCategory loadCategory = loadCategoryRepo.findOne(routePlan.getLoadCategoryId());
			scheduledTrip.setLoadCategory(loadCategory);
		}
		scheduledTrip.setDicvUser(dicvUserOwner);
		scheduledTrip.setFromDate(fromDate);
		scheduledTrip.setToDate(toDate);
		scheduledTrip.setTripStatus(TripStatus.PLANNED.getStatusCode());
		scheduledTrip.setTripDateTime(timestamp);
		scheduledTrip.setModifiedDateTime(timestamp);
		scheduledTrip.setIssms(routePlan.getIsSms());
		scheduledTrip.setIsemail(routePlan.getIsEmail());
		scheduledTrip.setScheduledTripFlag(1);
		scheduledTrip.setCreatedDate(timestamp);
		scheduledTrip.setStartLocationLat(routePlan.getStartPontLat());
		scheduledTrip.setStartLocationLong(routePlan.getStartPontLong());
		scheduledTrip.setStopLocationLat(routePlan.getEndPontLat());
		scheduledTrip.setStopLocationLong(routePlan.getEndPontLong());
		Address fromAddress = googleAPIService.getAddress(routePlan.getStartPontLat(), routePlan.getStartPontLong());
		Address toAddress = googleAPIService.getAddress(routePlan.getEndPontLat(), routePlan.getEndPontLong());
		if (fromAddress.getResponse())
			scheduledTrip.setFromAddress(fromAddress.getAddress());
		if (toAddress.getResponse())
			scheduledTrip.setToAddress(toAddress.getAddress());
		scheduledTrip.setModifiedDateTime(DicvUtil.getTimestamp());
		scheduledTrip.setStopLocationLong(routePlan.getEndPontLong());
		scheduledTrip.setDicvUserUpdatedBy(dicvUserOwner.getUserId());
		scheduledTrip.setVolume(routePlan.getVolume());
		scheduledTrip.setLoadWeight(routePlan.getLoadWeight());
		scheduledTrip.setRevenue(routePlan.getRevenue());
		scheduledTrip.setCustomerName(routePlan.getCustomerName());
		scheduledTrip.setDriverCost(routePlan.getDriverCost());
		scheduledTrip.setFuelCost(routePlan.getFuelCost());
		scheduledTrip.setOperationalCost(routePlan.getOperationalCost());
		scheduledTrip.setDistance(routePlan.getDistance());
		scheduledTrip.setDuration(routePlan.getDuration());
		scheduledTripRepo.save(scheduledTrip);
		Trip trip = saveTripDetails(routePlan, toDate, fromDate, timestamp, dicvUserOwner, vehicle, dicvUserDriver,
				scheduledTrip);
		return trip;
	}

	private Trip saveScheduledTripDetails(ScheduledTripEditDto routePlan, Date toDate, Date fromDate,
			Timestamp timestamp, DicvUser dicvUserOwner, Vehicle vehicle, DicvUser dicvUserDriver) {
		ScheduledTrip scheduledTrip = new ScheduledTrip();
		if (null != routePlan.getLoadCategoryId()) {
			LoadCategory loadCategory = loadCategoryRepo.findOne(routePlan.getLoadCategoryId());
			scheduledTrip.setLoadCategory(loadCategory);
		}
		scheduledTrip.setDicvUser(dicvUserOwner);
		scheduledTrip.setFromDate(fromDate);
		scheduledTrip.setToDate(toDate);
		scheduledTrip.setTripStatus(TripStatus.PLANNED.getStatusCode());
		scheduledTrip.setTripDateTime(timestamp);
		scheduledTrip.setModifiedDateTime(timestamp);
		scheduledTrip.setIssms(routePlan.getIsSms());
		scheduledTrip.setIsemail(routePlan.getIsEmail());
		scheduledTrip.setScheduledTripFlag(1);
		scheduledTrip.setCreatedDate(timestamp);
		scheduledTrip.setStartLocationLat(routePlan.getStartPontLat());
		scheduledTrip.setStartLocationLong(routePlan.getStartPontLong());
		scheduledTrip.setStopLocationLat(routePlan.getEndPontLat());
		scheduledTrip.setStopLocationLong(routePlan.getEndPontLong());
		Address fromAddress = googleAPIService.getAddress(routePlan.getStartPontLat(), routePlan.getStartPontLong());
		Address toAddress = googleAPIService.getAddress(routePlan.getEndPontLat(), routePlan.getEndPontLong());
		if (fromAddress.getResponse())
			scheduledTrip.setFromAddress(fromAddress.getAddress());
		if (toAddress.getResponse())
			scheduledTrip.setToAddress(toAddress.getAddress());
		scheduledTrip.setModifiedDateTime(DicvUtil.getTimestamp());
		scheduledTrip.setStopLocationLong(routePlan.getEndPontLong());
		scheduledTrip.setDicvUserUpdatedBy(dicvUserOwner.getUserId());
		scheduledTrip.setVolume(routePlan.getVolume());
		scheduledTrip.setLoadWeight(routePlan.getLoadWeight());
		scheduledTrip.setRevenue(routePlan.getRevenue());
		scheduledTrip.setCustomerName(routePlan.getCustomerName());
		scheduledTrip.setDriverCost(routePlan.getDriverCost());
		scheduledTrip.setFuelCost(routePlan.getFuelCost());
		scheduledTrip.setOperationalCost(routePlan.getOperationalCost());
		scheduledTrip.setDistance(routePlan.getDistance());
		scheduledTrip.setDuration(routePlan.getDuration());
		scheduledTripRepo.save(scheduledTrip);
		Trip trip = saveTripDetails(routePlan, toDate, fromDate, timestamp, dicvUserOwner, vehicle, dicvUserDriver,
				scheduledTrip);
		return trip;
	}

	private Trip saveTripDetails(ScheduledTripEditDto routePlan, Date toDate, Date fromDate, Timestamp timestamp,
			DicvUser dicvUserOwner, Vehicle vehicle, DicvUser dicvUserDriver, ScheduledTrip scheduledTrip) {
		Trip trip = new Trip();
		trip.setCreatedDate(DicvUtil.getTimestamp());
		trip.setStartLocationLat(routePlan.getStartPontLat());
		trip.setVehicle(vehicle);
		trip.setStartLocationLong(routePlan.getStartPontLong());
		trip.setTripStatus(DicvConstants.TRIPSTATUS_PLANNED);
		trip.setIsDeleted(0);
		trip.setTripDriverUser(dicvUserDriver);
		trip.setScheduledTrip(scheduledTrip);
		trip.setStartLocationLat(routePlan.getStartPontLat());
		trip.setStartLocationLong(routePlan.getStartPontLong());
		trip.setTripStartTime(new Timestamp(fromDate.getTime()));
		trip.setTripEndTime(new Timestamp(toDate.getTime()));
		trip.setStopLocationLat(routePlan.getEndPontLat());
		trip.setFromAddress(scheduledTrip.getFromAddress());
		trip.setToAddress(scheduledTrip.getToAddress());
		trip.setModifiedDate(DicvUtil.getTimestamp());
		trip.setStopLocationLong(routePlan.getEndPontLong());
		trip.setProcessStatus(1);
		trip.setStopLocationLat(routePlan.getEndPontLat());
		trip.setStopLocationLong(routePlan.getEndPontLong());
		Dispatch dispatch = new Dispatch();
		dispatch.setLatitude(routePlan.getLatitude());
		dispatch.setLongitude(routePlan.getLongitude());
		dispatch.setDispatchType(routePlan.getDispatchType());
		dispatch.setDateTime(timestamp);
		dispatch.setFromAddress(routePlan.getFromAddress());
		dispatch.setToAddress(routePlan.getToAddress());
		dispatch.setDispatchStatus(TripStatus.ASSIGNED.getStatusCode());
		dispatch.setDicvUserCreatedBy(dicvUserOwner);
		dispatch.setDicvUserUpdatedBy(dicvUserOwner.getUserId());
		dispatch.setModifiedDateTime(timestamp);
		dispatch.setTrip(trip);
		trip.setDispatches(dispatch);
		trip.setScheduledTrip(scheduledTrip);
		trip.setScheduledTrip(scheduledTrip);
		tripRepo.save(trip);
		return trip;
	}

	private Trip saveTripDetails(RoutePlanDto routePlan, Date toDate, Date fromDate, Timestamp timestamp,
			DicvUser dicvUserOwner, Vehicle vehicle, DicvUser dicvUserDriver, ScheduledTrip scheduledTrip) {
		Trip trip = new Trip();
		trip.setCreatedDate(DicvUtil.getTimestamp());
		trip.setStartLocationLat(routePlan.getStartPontLat());
		trip.setVehicle(vehicle);
		trip.setStartLocationLong(routePlan.getStartPontLong());
		trip.setTripStatus(DicvConstants.TRIPSTATUS_PLANNED);
		trip.setIsDeleted(0);
		trip.setTripDriverUser(dicvUserDriver);
		trip.setScheduledTrip(scheduledTrip);
		trip.setStartLocationLat(routePlan.getStartPontLat());
		trip.setStartLocationLong(routePlan.getStartPontLong());
		trip.setTripStartTime(new Timestamp(fromDate.getTime()));
		trip.setTripEndTime(new Timestamp(toDate.getTime()));
		trip.setStopLocationLat(routePlan.getEndPontLat());
		trip.setFromAddress(scheduledTrip.getFromAddress());
		trip.setToAddress(scheduledTrip.getToAddress());
		trip.setModifiedDate(DicvUtil.getTimestamp());
		trip.setStopLocationLong(routePlan.getEndPontLong());
		trip.setProcessStatus(1);
		trip.setStopLocationLat(routePlan.getEndPontLat());
		trip.setStopLocationLong(routePlan.getEndPontLong());
		trip.setScheduledTrip(scheduledTrip);
		trip.setScheduledTrip(scheduledTrip);
		Trip newtrip = tripRepo.save(trip);
		Dispatch dispatch = new Dispatch();
		dispatch.setLatitude(routePlan.getLatitude());
		dispatch.setLongitude(routePlan.getLongitude());
		dispatch.setDispatchType(routePlan.getDispatchType());
		dispatch.setDateTime(timestamp);
		dispatch.setFromAddress(routePlan.getFromAddress());
		dispatch.setToAddress(routePlan.getToAddress());
		dispatch.setDispatchStatus(TripStatus.ASSIGNED.getStatusCode());
		dispatch.setDicvUserCreatedBy(dicvUserOwner);
		dispatch.setDicvUserUpdatedBy(dicvUserOwner.getUserId());
		dispatch.setModifiedDateTime(timestamp);
		dispatch.setTrip(newtrip);
		dispacthRepo.save(dispatch);
		return trip;
	}

	private String getGcmRegistrationByVehicle(Integer vehicleId) {
		try {
			List<Gcm> vehicleList = gcmRepo.getGcmDetails(vehicleId);
			if (vehicleList != null && vehicleList.size() > 0) {
				return vehicleList.get(0).getRegistrationId();
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in Add/Update Route ", ex);
			return null;
		}
		return null;

	}

	// Google Cloud Messaging
	public Result sendGcmNotification(String msg, String regId) {
		Result gcmresult = null;
		LOGGER.info("sendGcmNotification-> input : msg - " + msg + " ,regId - " + regId);
		try {
			String userMessage = msg;
			Sender sender = new Sender(googleServerKey);
			Message message = new Message.Builder().delayWhileIdle(false).addData("message", userMessage).build();
			gcmresult = sender.send(message, regId, 1);
			LOGGER.info("GCM Notification Message Details :" + gcmresult.toString() + " message :" + message
					+ " messageId :" + gcmresult.getMessageId() + " canonical_ids :"
					+ gcmresult.getCanonicalRegistrationId() + " regId :" + regId);
		} catch (IOException ioe) {
			LOGGER.error("IO Exception in Sending Gcm Notification ", ioe.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception in Sending Gcm Notification ", e.getMessage());
		}

		return gcmresult;
	}

	public StatusMessageDto editRoutePlan(ScheduledTripEditDto scheduledTripEditDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HHmmss");
			Date toDate = DicvUtil.stringToDate(scheduledTripEditDto.getToDate());
			Date fromDate = DicvUtil.stringToDate(scheduledTripEditDto.getFromDate());
			long diffDaysBetFromAndToDate = DicvUtil.getDifferenceDays(fromDate, toDate);
			LOGGER.info("Difference between fromDate and toDate in days : " + diffDaysBetFromAndToDate);
			if (diffDaysBetFromAndToDate > 90) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto
						.setMessage("Difference between fromDate and toDate should not be greater than ninety days");
				return statusMessageDto;
			}
			if (diffDaysBetFromAndToDate < 0) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage("From Date should not be less than To Date");
				return statusMessageDto;
			}
			Timestamp timestamp = new Timestamp(new Date().getTime());
			String currDateStr = dateFormat.format(timestamp.getTime());
			Date currentDate = DicvUtil.stringToDate(currDateStr);
			long diffDaysBetCurrAndFromDate = DicvUtil.getDifferenceDays(currentDate, fromDate);
			LOGGER.info("Difference between currentDate and fromDate in days : " + diffDaysBetCurrAndFromDate);
			if (diffDaysBetCurrAndFromDate > 90) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage(
						"Difference between currentDate and fromDate should not be greater than ninety days");
				return statusMessageDto;
			}

			if ((diffDaysBetCurrAndFromDate < 0)) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage("From Date should not be less than Current Date");
				return statusMessageDto;
			}
			DicvUser dicvUserOwner = userService.getUser(scheduledTripEditDto.getUserId(), "Schedule Trip Owner");
			Vehicle vehicle = vehicleService.getVehicleDetails(scheduledTripEditDto.getVehicleId());
			DicvUser dicvUserDriver = userService.getUser(scheduledTripEditDto.getDriverId(), "Schedule Trip Driver");
			if (vehicle == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage("Vehicle does not exist");
				return statusMessageDto;
			}
			GpsImei gpsImei = vehicle.getGpsImei();
			if (null == gpsImei || gpsImei.getGpsImei() == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage("GPS IMEI doesn't exist for the given vehicle ");
				return statusMessageDto;

			}
			Trip trip = saveScheduledTripDetails(scheduledTripEditDto, toDate, fromDate, timestamp, dicvUserOwner,
					vehicle, dicvUserDriver);
			String regId = getGcmRegistrationByVehicle(scheduledTripEditDto.getVehicleId());
			String message = "Updated scheduledTripId : " + trip.getTripId();
			if (regId != null)
				sendGcmNotification(message, regId);
			LOGGER.info("The Vehicle route plan has been successfully uploaded !! " + trip.getTripId());
			statusMessageDto.setStatus(HttpServletResponse.SC_OK);
			statusMessageDto.setMessage("Trip Updated Successfully");
		} catch (Exception ex) {
			LOGGER.error("Exception in Add/Update Route ", ex);
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return statusMessageDto;
		}
		return statusMessageDto;
	}

	public FavoriteLocationListDto getFavoriteLocation(Integer userId) {
		FavoriteLocationListDto favoriteLocationList = new FavoriteLocationListDto();
		FavoriteLocationListDto favoriteLocationListDto = new FavoriteLocationListDto();
		FavoriteLocationDto favoriteLocn = null;
		List<FavoriteLocationDto> favoriteLocnList = new ArrayList<FavoriteLocationDto>();
		try {
			List<FavoriteLocation> flList = favRepo.getFavoriteLocationList(userId);
			if (flList != null && flList.size() > 0) {
				for (FavoriteLocation favoriteLocation : flList) {
					favoriteLocn = new FavoriteLocationDto();
					favoriteLocn.setFavoriteTagName(favoriteLocation.getFavoriteTagName());
					favoriteLocn.setAddress(favoriteLocation.getAddress());
					favoriteLocn.setFavoriteId(favoriteLocation.getFavoriteId());
					favoriteLocnList.add(favoriteLocn);
				}
				favoriteLocationList.setFavoriteLocationList(favoriteLocnList);
				favoriteLocationList.setStatus(HttpServletResponse.SC_OK);
				favoriteLocationList.setMessage(DicvConstants.DATA_FOUND_MSG);
			} else {
				favoriteLocationListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				favoriteLocationListDto.setMessage("From Date should not be less than Current Date");
			}
			return favoriteLocationListDto;
		} catch (Exception ex) {
			LOGGER.error("Exception in FavoriteLocation ", ex);
			favoriteLocationListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			favoriteLocationListDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return favoriteLocationListDto;
		}
	}

	public StatusMessageDto saveFavoriteLocation(FavoriteLocationDtlsDto favoriteLocationDtls) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			DicvUser dicvUser = userService.getUser(favoriteLocationDtls.getUserId(), "save Favorite Location");
			if (null != dicvUser) {
				Timestamp currentDateTime = new Timestamp(new Date().getTime());
				FavoriteLocation favoriteLocation = new FavoriteLocation();
				favoriteLocation.setFavoriteTagName(favoriteLocationDtls.getFavoriteTagName());
				favoriteLocation.setAddress(favoriteLocationDtls.getAddress());
				favoriteLocation.setCreatedOn(currentDateTime);
				favoriteLocation.setModifiedDateTime(currentDateTime);
				favoriteLocation.setDicvUser(dicvUser);
				favoriteLocation.setDicvUserUpdatedBy(dicvUser.getUserId());
				favRepo.save(favoriteLocation);
				statusMessageDto.setMessage(DicvConstants.DATA_FOUND_MSG);
			} else {
				statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			}
			statusMessageDto.setStatus(HttpServletResponse.SC_OK);
			return statusMessageDto;
		} catch (Exception e) {
			LOGGER.error("Exception in Set FavoriteLocation ", e);
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return statusMessageDto;
		}
	}

}
