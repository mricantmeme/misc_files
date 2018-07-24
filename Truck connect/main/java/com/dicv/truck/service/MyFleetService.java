package com.dicv.truck.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicv.truck.dto.GPSOwnerDetailDto;
import com.dicv.truck.dto.GPSVehicleDetailDto;
import com.dicv.truck.dto.VehicleCanParamDto;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.Vehicle;
import com.dicv.truck.repo.TripDao;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.EnumUserType;

@Service
public class MyFleetService {

	@Autowired
	private UserService userService;

	@Autowired
	private VehicleServices vehicleServices;

	@Autowired
	private TripDao tripRepo;

	public GPSOwnerDetailDto getGpsMyFleet(Integer userId) {
		GPSOwnerDetailDto ownerdetail = new GPSOwnerDetailDto();
		DicvUser user = userService.getUser(userId, "getGpsMyFleet");
		if (user != null) {
			String role = user.getUserType().getUserType();

			List<Vehicle> vehicles = vehicleServices.getVehicleListByUser(userId, role);

			if (vehicles != null && vehicles.size() > 0) {

				List<GPSVehicleDetailDto> vehicleList = new ArrayList<GPSVehicleDetailDto>();

				List<Integer> runningVehicleIds = tripRepo.getScheduledTripList();
				SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy hh:mm a");
				setVehicleDetailsForMyFleet(role, vehicles, vehicleList, runningVehicleIds, sdf);

				ownerdetail.setVehicles(vehicleList);
				if (vehicleList != null && !vehicleList.isEmpty()) {
					ownerdetail.setUserId(userId);
					ownerdetail.setMessage(DicvConstants.DATA_FOUND_MSG);
					ownerdetail.setStatus(HttpServletResponse.SC_OK);
					return ownerdetail;
				}
			}
		}
		ownerdetail.setMessage("Vehicle Not Available For this User");
		ownerdetail.setStatus(HttpServletResponse.SC_NO_CONTENT);
		return ownerdetail;

	}

	private void setVehicleDetailsForMyFleet(String role, List<Vehicle> vehicles, List<GPSVehicleDetailDto> vehicleList,
			List<Integer> runningVehicleIds, SimpleDateFormat sdf) {
		GPSVehicleDetailDto vehicleDetail;
		for (Vehicle vehicle : vehicles) {
			vehicleDetail = new GPSVehicleDetailDto();
			String currentStatus = null;
			currentStatus = runningVehicleIds.contains(vehicle.getVehicleId()) ? DicvConstants.IN_TRIP
					: DicvConstants.NOT_IN_TRIP;
			vehicleDetail = getGPSVehicleDetailDto(vehicle, currentStatus, role);
			VehicleCanParamDto vehicleCanParamDto = new VehicleCanParamDto();
			if (vehicle.getVehicleCanParam() != null) {
				if (vehicle.getRunningStatus().equals(DicvConstants.VEH_STATUS_RUNNING)
						|| vehicle.getRunningStatus().equals(DicvConstants.VEH_STATUS_IDLE)) {
					if (vehicle.getVehicleCanParam().getVehicleEngineRpm() != null) {
						vehicleDetail.setShowParam(true);
						vehicleCanParamDto
								.setCanEngineSpeed(vehicle.getVehicleCanParam().getVehicleEngineRpm().intValue());
					}
					if (vehicle.getVehicleCanParam().getVehicleEngineCoolantTemp() != null) {
						vehicleDetail.setShowParam(true);
						vehicleCanParamDto.setCanCoolantTemp(
								vehicle.getVehicleCanParam().getVehicleEngineCoolantTemp().longValue());
					}
				}
				if (vehicle.getVehicleCanParam().getFuelTankLevel() != null) {
					vehicleDetail.setShowParam(true);
					vehicleCanParamDto.setFuelTankLevel(vehicle.getVehicleCanParam().getFuelTankLevel());
				}
				if (vehicle.getVehicleCanParam().getAdblueLevel() != null) {
					vehicleCanParamDto.setAdblueLevel(vehicle.getVehicleCanParam().getAdblueLevel());
				}
				if (vehicle.getVehicleCanParam().getBatteryHealth() != null
						&& role.equals(EnumUserType.ROOTADMIN.getUserType())) {
					vehicleDetail.setShowParam(true);
					vehicleCanParamDto.setBatteryHealth(vehicle.getVehicleCanParam().getBatteryHealth());
				}
				vehicleDetail.setVehicleCanParam(vehicleCanParamDto);
				if (vehicle.getVehicleCanParam().getVehicleLastUpdateON() != null) {
					Date resultdate = new Date(vehicle.getVehicleCanParam().getVehicleLastUpdateON().getTime());
					vehicleDetail.setVehicleLastUpdateTime((sdf.format(resultdate)).toString());
				}
			}
			vehicleList.add(vehicleDetail);
		}
	}

	private GPSVehicleDetailDto getGPSVehicleDetailDto(Vehicle vehicle, String currentStatus, String role) {
		GPSVehicleDetailDto vehicleDetail = new GPSVehicleDetailDto();
		vehicleDetail.setRegistrationId(vehicle.getRegistrationId());
		vehicleDetail.setDescription(vehicle.getDescription());
		vehicleDetail.setVehicleVariant(vehicle.getVariant());
		vehicleDetail.setVin(vehicle.getVin());
		vehicleDetail.setGpsHdop(vehicle.getGpsHdop());
		vehicleDetail.setCurrentLong(vehicle.getCurrentLong());
		vehicleDetail.setCurrentEngineSpeed(vehicle.getCurrentEngineSpeed());
		vehicleDetail.setCurrentLat(vehicle.getCurrentLat());
		vehicleDetail.setVehicleStatus(vehicle.getRunningStatus());
		vehicleDetail.setVehicleTripStatus(currentStatus);
		vehicleDetail.setVehicleId(vehicle.getVehicleId());

		if (vehicle.getGpsCog() != null)
			vehicleDetail.setVehicleDirection(getVehicleDirectionForCOG(vehicle.getGpsCog()));

		if (null != vehicle.getDicvRegion() && null != vehicle.getDicvRegion().getRegionId())
			vehicleDetail.setRegionId(vehicle.getDicvRegion().getRegionId());
		vehicleDetail.setCurrentVehicleSpeed(0);
		if (DicvConstants.VEH_STATUS_RUNNING.equals(vehicleDetail.getVehicleStatus()))
			vehicleDetail.setCurrentVehicleSpeed(vehicle.getCurrentVehicleSpeed());

		if (role.equalsIgnoreCase(EnumUserType.ROOTADMIN.getUserType())) {
			if (null != vehicle.getRootAdminGroup())
				vehicleDetail.setGroupId(vehicle.getRootAdminGroup().getGroupId());

		} else if (null != vehicle.getDicvGroup() && null != vehicle.getDicvGroup().getGroupId()) {
			vehicleDetail.setGroupId(vehicle.getDicvGroup().getGroupId());
		}

		if (vehicle.getDicvUser() != null && vehicle.getDicvUser().getDicvCompany() != null)
			vehicleDetail.setCompanyId(vehicle.getDicvUser().getDicvCompany().getCompanyId());

		if (null != vehicle.getGpsImei())
			vehicleDetail.setImeiId(vehicle.getGpsImei().getGpsImei());

		if (null != vehicle.getDicvType())
			vehicleDetail.setVehicleVariant(vehicle.getDicvType().getTypeName());

		if (null != vehicle.getVehicleUpdateTime()) {
			vehicleDetail.setVehicleUpdateTime(vehicle.getVehicleUpdateTime());
			vehicleDetail.setVehicleUpdateTimeStr(dateToStringWithTime(vehicle.getVehicleUpdateTime()));
		}

		return vehicleDetail;

	}

	private String getVehicleDirectionForCOG(Float gpsCog) {
		String direction = "None";
		int cogIntVal = gpsCog.intValue();

		if ((cogIntVal >= 0 && cogIntVal <= 20) || (cogIntVal > 340 && cogIntVal <= 360)) {
			direction = "N";
		} else if ((cogIntVal > 20 && cogIntVal <= 70)) {
			direction = "NE";
		} else if ((cogIntVal > 70 && cogIntVal <= 110)) {
			direction = "E";
		} else if ((cogIntVal > 110 && cogIntVal <= 160)) {
			direction = "SE";
		} else if ((cogIntVal > 160 && cogIntVal <= 200)) {
			direction = "S";
		} else if ((cogIntVal > 200 && cogIntVal <= 250)) {
			direction = "SW";
		} else if ((cogIntVal > 250 && cogIntVal <= 290)) {
			direction = "W";
		} else if ((cogIntVal > 290 && cogIntVal <= 340)) {
			direction = "NW";
		}
		return direction;
	}

	public String dateToStringWithTime(Date date) {
		DateFormat sdf = new SimpleDateFormat("yyy/MM/dd HH:mm");
		return (sdf.format(date));
	}

}
