package com.dicv.truck.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dicv.truck.dto.UserDto;
import com.dicv.truck.dto.VehicleDto;
import com.dicv.truck.model.DicvCompany;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.Vehicle;
import com.dicv.truck.repo.GpsImeiRepo;
import com.dicv.truck.repo.TripRepo;
import com.dicv.truck.repo.UserRepo;
import com.dicv.truck.repo.VehicleRepo;
import com.dicv.truck.repo.VehicleToGeoFenceRepo;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.EnumUserType;
import com.dicv.truck.utility.TripStatus;
import com.dicv.truck.utility.UserStatus;

@Component
public class ValidationService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private DicvServices dicvService;

	@Autowired
	private TripRepo tripRepo;

	@Autowired
	private VehicleRepo vehicleRepo;

	@Autowired
	private GpsImeiRepo gpsImeiRepo;

	@Autowired
	private VehicleServices vehicleServices;

	@Autowired
	private VehicleToGeoFenceRepo vehicleToGeoFenceRepo;

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationService.class);

	public String validationForUserModify(UserDto userDto) {
		LOGGER.info("User Dto " + userDto);
		if (null == userDto) {
			return "Please provide valid user detail !!!";
		}
		if ((null != userDto.getRecordStatus()
				&& DicvConstants.DELETE_RECORD.equalsIgnoreCase(userDto.getRecordStatus()))
				&& (userDto.getUserId() == null || userDto.getUserId() <= 0)
				&& (userDto.getCreatedOrUpdatedByUserId() == null || userDto.getCreatedOrUpdatedByUserId() <= 0)) {
			return "Please provide valid user detail !!!";
		}

		if (null == userDto.getRecordStatus()
				|| !DicvConstants.DELETE_RECORD.equalsIgnoreCase(userDto.getRecordStatus())) {
			if (!DicvUtil.isValidAttribute(userDto.getUserName()))
				return "Please provide User Name !!!";
		}
		DicvUser createByUser = userService.getUser(userDto.getCreatedOrUpdatedByUserId(), "Create User Validation");
		if (null == createByUser) {
			return " User does not have the privilege";
		}

		if (userDto.getUserId() != null && userDto.getUserId() > 0) {
			DicvUser user = userService.getUser(userDto.getUserId(), "Update User Validation");
			if (user == null) {
				return "User Not Available";
			}
			if (EnumUserType.CUSTOMERADMIN.getUserType().equals(createByUser.getUserType().getUserType())) {
				if (!user.getManagerId().equals(createByUser.getUserId())) {
					LOGGER.info("User Not Having Previlage :: Manager Id " + user.getManagerId() + " Logged In User "
							+ createByUser.getUserId());
					return " User does not have the privilege";
				}
			}
			if (DicvUtil.isValidAttribute(userDto.getRecordStatus())
					&& userDto.getRecordStatus().equalsIgnoreCase(DicvConstants.DELETE_RECORD)) {
				if (!isUserPrivelagedToDelete(createByUser, user)) {
					return "The user does not have the privilege to delete";
				} else {
					String activeUser = isActiveUser(user);
					if (!activeUser.equals(DicvConstants.SUCCESS)) {
						return activeUser;
					}
					return DicvConstants.SUCCESS;
				}
			}
		}
		Long userNameExist = userRepo.checkUserNameExist(userDto.getUserName(), userDto.getUserId(),
				UserStatus.DELETED.getRecordStatusCode());
		if (userNameExist != null && userNameExist > 0)
			return "Username  already exist";
		if (userDto.getDrivingLicenseNo() != null) {
			Long licenseExist = userRepo.checkLicenseAvailable(userDto.getDrivingLicenseNo(), userDto.getUserId(),
					UserStatus.DELETED.getRecordStatusCode());
			if (licenseExist > 0)
				return "The driver license number is already associated to another user.";
		}

		return DicvConstants.SUCCESS;

	}

	public boolean isUserPrivelagedToDelete(DicvUser createdByUser, DicvUser dicvUser) {
		boolean isValid = true;
		if (createdByUser.getUserType().getUserType().toString()
				.equalsIgnoreCase(EnumUserType.ROOTADMIN.getUserType())) {
			isValid = true;
		} else if (dicvUser.getUserId() == createdByUser.getUserId()) {
			isValid = false;
		} else if (dicvUser.getManagerId() != null && !dicvUser.getManagerId().equals(createdByUser.getUserId())) {
			/**
			 * Other than his owner.
			 */
			isValid = false;
		}
		return isValid;
	}

	/**
	 * check if the driver is occupied in a trip.<br>
	 * Check if the driver is default driver
	 */
	private String isActiveUser(DicvUser dicvUser) {
		LOGGER.info("Inside Active User Validation ");
		if (dicvUser.getUserType().getUserType().equalsIgnoreCase(EnumUserType.DRIVER.getUserType())) {
			List<String> tripStatus = getRunningTripStatus();
			Long tripCount = tripRepo.getActiveTripCount(dicvUser.getUserId(), tripStatus);
			if (null != tripCount && tripCount > 0) {
				return "The user cannot be deleted as the user is associated with running trip.";
			}
			Long defaultDriverId = vehicleRepo.getDefaultDriver(dicvUser.getUserId());
			if (defaultDriverId > 0) {
				return "The user cannot be deleted as the user is marked as default driver.";
			}
		}
		if (dicvUser.getUserType().getUserType().equalsIgnoreCase(EnumUserType.CUSTOMERADMIN.getUserType())) {
			Long vehicleCount = vehicleRepo.vehicleCount(dicvUser.getUserId());
			if (vehicleCount > 0) {
				return "The user cannot be deleted as the user is mapped with vehicle.";
			}
		}
		return DicvConstants.SUCCESS;
	}

	private List<String> getRunningTripStatus() {
		List<String> tripStatus = new ArrayList<String>();
		tripStatus.add(TripStatus.ASSIGNED.getStatusCode());
		tripStatus.add(TripStatus.PLANNED.getStatusCode());
		tripStatus.add(TripStatus.RUNNING.getStatusCode());
		return tripStatus;
	}

	public String validationForVehicleModify(VehicleDto vehicleDto) {
		if (null == vehicleDto) {
			return "Kindly provide valid input";
		}
		if ((((null == vehicleDto.getVehicleStatus()) || (null != vehicleDto.getVehicleStatus()
				&& !vehicleDto.getVehicleStatus().equalsIgnoreCase(DicvConstants.DELETE_RECORD)))
				&& (!DicvUtil.isValidAttribute(vehicleDto.getRegistrationId())
						|| !DicvUtil.isAlphaNumeric(vehicleDto.getRegistrationId()) || null == vehicleDto.getUserId()
						|| vehicleDto.getUserId() < 0))) {
			return "Kindly provide valid input, please check if vehicle registration, description, vin, country or the user is valid.";
		}
		DicvUser createByUser = userService.getUser(vehicleDto.getUserId(), "Vehicle Add");
		if (null == createByUser) {
			return "User not Valid";
		}
		if ((null == vehicleDto.getVehicleStatus())
				|| (!vehicleDto.getVehicleStatus().equalsIgnoreCase(DicvConstants.DELETE_RECORD))) {
			if (vehicleDto.getGpsImei() != null && vehicleDto.getGpsImei().toString().length() != 15) {
				return "The IMEI number should have length of 15";
			}
			if (vehicleDto.getVin() != null && vehicleDto.getVin().length() != 17) {
				return "The VIN should have length of 17";
			}
			if ((vehicleDto.getMaxPayLoadCapacity() != null && vehicleDto.getMaxPayLoadCapacity().intValue() < 0)
					|| (vehicleDto.getMaxVehicleSpeed() != null && vehicleDto.getMaxVehicleSpeed().intValue() < 0)) {
				return "Payload capacity or speed limit should not be less than zero";
			}
			Long existingVehicle = vehicleRepo.findByRegId(vehicleDto.getRegistrationId(), vehicleDto.getVehicleId());
			if (existingVehicle > 0) {
				return "The Registration id  is already associated to different vehicle.";
			}
			if (vehicleDto.getVin() != null) {
				existingVehicle = vehicleRepo.findByVin(vehicleDto.getVin(), vehicleDto.getVehicleId());
				if (existingVehicle > 0) {
					return "VIN is already associated to different vehicle.";
				}
			}
			if (vehicleDto.getVehicleId() <= 0) {
				Long imeiNumber = gpsImeiRepo.findByGpsImeiNumber(vehicleDto.getGpsImei());
				if (imeiNumber > 0) {
					return "The GPS IMEI Number is already associated with vehicle, kindly provide different number.";
				}
			}
		}
		if (null != vehicleDto.getDefaultDriverId() && vehicleDto.getDefaultDriverId() > 0) {
			DicvUser defaultDriverUser = userService.getUser(vehicleDto.getDefaultDriverId(), "Default Driver");
			if (null == defaultDriverUser) {
				return "Please specify valid default driver";
			} else if (!defaultDriverUser.getUserType().getUserType()
					.equalsIgnoreCase(EnumUserType.DRIVER.getUserType())) {
				return "To assign a user as default driver the role should be an driver role";
			}
		}
		if (vehicleDto.getCompanyId() != null) {
			DicvCompany dicvCompany = dicvService.getDicvCompany(vehicleDto.getCompanyId());
			if (dicvCompany == null) {
				return "Selected Company Not Available";
			}
		}
		if (vehicleDto.getCompanyId() != null && vehicleDto.getCustomerAdminId() != null) {
			DicvUser customerAdminUSer = userService.getUser(vehicleDto.getCustomerAdminId(), "Get Customer Admin");
			if (customerAdminUSer == null) {
				return "Selected Customer Admin Not Availble for this Company";
			}
		}
		if (vehicleDto.getDefaultDriverId() != null && vehicleDto.getDefaultDriverId() > 0) {
			DicvUser driverUser = userService.getUser(vehicleDto.getDefaultDriverId(), "Deafult Driver ");
			if (driverUser == null) {
				return "Driver Not Availble";
			}
		}
		if (vehicleDto.getVehicleId() > 0) {

			Vehicle vehicle = vehicleServices.getVehicleDetails(vehicleDto.getVehicleId());
			if (DicvUtil.isValidAttribute(vehicleDto.getVehicleStatus())
					&& (vehicleDto.getVehicleStatus().equalsIgnoreCase(DicvConstants.DELETE_RECORD)
							|| (vehicleDto.getVehicleStatus().equalsIgnoreCase("inactive")))) {
				if (null != vehicle.getDicvUser()
						&& (!createByUser.getUserType().getUserType()
								.equalsIgnoreCase(EnumUserType.ROOTADMIN.getUserType()))
						&& !vehicle.getDicvUser().getUserId().equals(createByUser.getUserId())) {
					return "The vehicle cannot be deleted as user does not have privilege to delete.";
				}
				List<String> tripStatus = getRunningTripStatus();
				Long statusForVehicle = tripRepo.getActiveTripByVehicle(vehicle.getVehicleId(), tripStatus);
				if (null != statusForVehicle && statusForVehicle > 0) {
					return "The vehicle cannot be deleted as it is associated in a running or planned trip.";
				}
				Long vehicleToGeoFences = vehicleToGeoFenceRepo.getGeoFenceListCountByVehicle(vehicle.getVehicleId());
				if (null != vehicleToGeoFences && vehicleToGeoFences > 0) {
					return "The vehicle cannot be deleted as it is associated with geo fence.";
				}
				return DicvConstants.SUCCESS;
			}

		}
		return DicvConstants.SUCCESS;

	}

	public boolean isUserPrivelagedToDelete(Vehicle vehicle, DicvUser dicvUser) {
		boolean isValid = false;
		if (dicvUser.getUserType().getUserType().toString().equalsIgnoreCase(EnumUserType.ROOTADMIN.getUserType())) {
			isValid = true;
		} else if (vehicle.getDicvUser() != null && vehicle.getDicvUser().getUserId() == dicvUser.getUserId()) {
			isValid = true;
		}
		return isValid;
	}

	public boolean validateDate(Date startDate, Date endDate) {
		boolean isValidDate = true;
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar currentDate = Calendar.getInstance();

		fromDate.setTime(startDate);
		toDate.setTime(endDate);
		currentDate.setTimeInMillis(System.currentTimeMillis());

		if (fromDate.after(toDate)) {
			isValidDate = false;
		} else if (toDate.after(currentDate) || toDate.equals(currentDate)) {
			isValidDate = false;
		}

		return isValidDate;

	}
}
