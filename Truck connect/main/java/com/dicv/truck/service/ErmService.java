package com.dicv.truck.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicv.truck.dto.ErmVehicleDto;
import com.dicv.truck.dto.ErmVehicleListDto;
import com.dicv.truck.dto.ErmVehicleSubmit;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.ErmVehicle;
import com.dicv.truck.model.GpsImei;
import com.dicv.truck.model.UserLog;
import com.dicv.truck.model.Vehicle;
import com.dicv.truck.repo.ErmVehicleRepo;
import com.dicv.truck.repo.GpsImeiRepo;
import com.dicv.truck.repo.UserLogRepo;
import com.dicv.truck.repo.VehicleRepo;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.EnumUserType;
import com.dicv.truck.utility.RecordStatus;

@Service
public class ErmService {

	@Autowired
	private UserService userService;

	@Autowired
	private GpsImeiRepo gpsImeiRepo;

	@Autowired
	private VehicleRepo vehicleRepo;

	@Autowired
	private ErmVehicleRepo ermVehicleRepo;

	@Autowired
	private UserLogRepo userLogRepo;

	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServices.class);

	@Transactional
	public StatusMessageDto save(ErmVehicleDto ermVehicleDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			if (ermVehicleDto.getErmVehicleId() == null)
				ermVehicleDto.setErmVehicleId(0);

			if (ermVehicleDto.getUserId() != null && ermVehicleDto.getGpsImei() != null
					&& ermVehicleDto.getGpsSimNumber() != null) {

				String validation = validateVehicle(ermVehicleDto);

				if (!validation.equals(DicvConstants.SUCCESS)) {
					statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					statusMessageDto.setMessage(validation);
					return statusMessageDto;
				}

				DicvUser user = userService.getUser(ermVehicleDto.getUserId(), "ERM Vehicle Post");
				ErmVehicle ermVehicle = new ErmVehicle();
				GpsImei gpsImei = new GpsImei();
				if (ermVehicleDto.getErmVehicleId() <= 0) {
					ermVehicle.setCreatedTime(DicvUtil.getTimestamp());
					ermVehicle.setDicvUser(user);
					gpsImei.setIsDeleted(0);
					gpsImei.setCreatedDate(DicvUtil.getTimestamp());
					gpsImei.setDicvUserCreatedBy(user);

				} else {
					ermVehicle = ermVehicleRepo.findOne(ermVehicleDto.getErmVehicleId());
					if (ermVehicle == null || ermVehicle.getDicvUser().getUserId() != user.getUserId()) {
						statusMessageDto.setStatus(HttpServletResponse.SC_OK);
						statusMessageDto.setMessage("Vehicle Not Found");
						return statusMessageDto;
					}
					if (ermVehicle.getStatus() == 1) {
						statusMessageDto.setStatus(HttpServletResponse.SC_OK);
						statusMessageDto.setMessage("Vehicle Profile Already Submitted");
						return statusMessageDto;
					}
					ermVehicle.setUpdatedTime(DicvUtil.getTimestamp());
					gpsImei = ermVehicle.getGpsImei();
					if (gpsImei == null) {
						gpsImei = new GpsImei();
						gpsImei.setIsDeleted(0);
						gpsImei.setCreatedDate(DicvUtil.getTimestamp());
						gpsImei.setDicvUserCreatedBy(user);
					}
				}
				gpsImei.setGpsImei(ermVehicleDto.getGpsImei());
				gpsImei.setGpsSimNumber(ermVehicleDto.getGpsSimNumber());
				gpsImei.setUpdatedDate(DicvUtil.getTimestamp());
				gpsImei.setDicvUserUpdatedBy(user);
				gpsImei.setErmVehicle(ermVehicle);
				ermVehicle.setGpsImei(gpsImei);
				ermVehicle.setStatus(ermVehicleDto.getStatus());
				LOGGER.info("Saving Erm Vehicle :: " + ermVehicle);
				ermVehicleRepo.save(ermVehicle);
				if (ermVehicleDto.getStatus() == 1) {
					if (ermVehicle.getGpsImei().getGpsTransmittedTime() == null) {
						statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						statusMessageDto.setMessage("Please Check Vehicle GpsTransmission");
						return statusMessageDto;
					} else {
						createVehicle(ermVehicle);
						saveUserLog(user.getUserId(), ermVehicle.getErmVehicleId(), DicvConstants.ERM, "SUBMIT");
					}
				} else {
					if (ermVehicleDto.getErmVehicleId() != null)
						saveUserLog(user.getUserId(), ermVehicle.getErmVehicleId(), DicvConstants.ERM, "UPDATE");
					else
						saveUserLog(user.getUserId(), ermVehicle.getErmVehicleId(), DicvConstants.ERM, "ADD");
				}
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setIdentifier(ermVehicle.getErmVehicleId());
				if (ermVehicle.getStatus() != 1) {
					statusMessageDto.setMessage(ermVehicleDto.getErmVehicleId() > 0 ? DicvConstants.SUCCESS_UPDATED
							: DicvConstants.SUCCESS_CREATED);
				} else {
					statusMessageDto.setMessage("Vehicle Profile Submitted Successfully");
				}
				return statusMessageDto;

			} else {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage("Please fill all Mandatory Details");
				return statusMessageDto;
			}

		} catch (Exception ex) {
			LOGGER.info("Exception in ERM Vehicle  " + ex.getMessage());
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return statusMessageDto;
		}
	}

	@Transactional
	private void createVehicle(ErmVehicle ermVehicle) {
		Vehicle vehicle = new Vehicle();
		vehicle.setEnabled(RecordStatus.IS_NOT_ENABLE.getStatus());
		vehicle.setIsDeleted(RecordStatus.IS_NOT_DELETED.getStatus());
		vehicle.setVehicleStatus("AVAILABLE");
		vehicle.setDicvUser(userService.getUser(2, "Root Admin"));
		vehicle.setRunningStatus("NEW");
		vehicle.setModifiedDateTime(DicvUtil.getTimestamp());
		vehicle.setDicvType(null);
		vehicle.setDicvCategory(null);
		vehicle.setVehicleMaxSpeed(80);
		vehicle.setErmVehicle(ermVehicle);
		vehicle = vehicleRepo.save(vehicle);
		GpsImei gpsImei = ermVehicle.getGpsImei();
		gpsImei.setDicvUserUpdatedBy(ermVehicle.getDicvUser());
		gpsImei.setUpdatedDate(DicvUtil.getTimestamp());
		gpsImei.setVehicle(vehicle);
		gpsImeiRepo.save(gpsImei);
	}

	@Transactional
	public void saveUserLog(Integer userId, Integer loggedId, String module, String logType) {
		UserLog userLog = new UserLog();
		switch (module) {
		case DicvConstants.USER:
			userLog.setLogUserId(loggedId);
			break;
		case DicvConstants.VEHICLE:
			userLog.setLogVehicleId(loggedId);
			break;
		case DicvConstants.ERM:
			userLog.setErmVehicleId(loggedId);
			break;
		}
		userLog.setCreatedTime(DicvUtil.getTimestamp());
		userLog.setUserId(userId);
		userLog.setLogType(logType);
		userLogRepo.save(userLog);
	}

	private String validateVehicle(ErmVehicleDto ermVehicleDto) {
		if (ermVehicleDto.getGpsImei() != null && ermVehicleDto.getGpsImei().toString().length() != 15) {
			return "The IMEI number should have length of 15";
		}
		if (ermVehicleDto.getErmVehicleId() <= 0) {
			Long imeiNumber = gpsImeiRepo.findByERMGpsImeiNumber(ermVehicleDto.getGpsImei());
			if (imeiNumber > 0) {
				return "The gps_imei number is already associated with vehicle, kindly provide different number";
			}
		}
		return DicvConstants.SUCCESS;
	}

	public ErmVehicleListDto getVehicleList(Integer userId) {

		ErmVehicleListDto ermVehicleListDto = new ErmVehicleListDto();
		try {
			DicvUser user = userService.getUser(userId, "Dealer - VehicleList");
			if (user == null || !user.getUserType().getUserType().equals(EnumUserType.ERM.getUserType())) {
				ermVehicleListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				ermVehicleListDto.setMessage("Logged in User is Not a ERM");
				return ermVehicleListDto;
			}
			List<ErmVehicle> vehicles = ermVehicleRepo.getErmVehicleList(userId);
			if (vehicles == null || vehicles.isEmpty()) {
				ermVehicleListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				ermVehicleListDto.setMessage("No Vehicle Found");
				return ermVehicleListDto;
			}
			List<ErmVehicleDto> ermVehicleDtoList = new ArrayList<ErmVehicleDto>();
			ErmVehicleDto ermVehicleDto = new ErmVehicleDto();
			for (ErmVehicle ermVehicle : vehicles) {
				ermVehicleDto = new ErmVehicleDto();
				ermVehicleDto.setGpsImei(ermVehicle.getGpsImei().getGpsImei());
				ermVehicleDto.setGpsSimNumber(ermVehicle.getGpsImei().getGpsSimNumber());
				ermVehicleDto.setUserId(userId);
				if (ermVehicle.getGpsImei() != null && ermVehicle.getLastReceivedTime() != null) {
					ermVehicleDto.setGpsReceivedTime(DicvUtil.getStringForTimestamp(ermVehicle.getLastReceivedTime()));
					ermVehicleDto.setGpsTranmission(true);
				} else {
					ermVehicleDto.setGpsReceivedTime("");
				}
				ermVehicleDto.setStatus(ermVehicle.getStatus());
				ermVehicleDto.setErmVehicleId(ermVehicle.getErmVehicleId());
				ermVehicleDtoList.add(ermVehicleDto);
			}
			ermVehicleListDto.setVehicleList(ermVehicleDtoList);
			ermVehicleListDto.setStatus(HttpServletResponse.SC_OK);
			ermVehicleListDto.setMessage(DicvConstants.DATA_FOUND_MSG);

		} catch (Exception e) {
			LOGGER.error("Exception in Getting ERM Vehicle List " + e.getMessage());
			ermVehicleListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			ermVehicleListDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}
		return ermVehicleListDto;
	}

	public ErmVehicleListDto getVehicleDetail(Integer userId, Integer ermVehicleId) {
		if (ermVehicleId != null) {
			return getVehicleDetails(userId, ermVehicleId);
		} else {
			return getVehicleList(userId);
		}
	}

	private ErmVehicleListDto getVehicleDetails(Integer userId, Integer ermVehicleId) {
		ErmVehicleListDto ermVehicleListDto = new ErmVehicleListDto();
		try {
			ErmVehicle ermVehicle = ermVehicleRepo.findOne(ermVehicleId);
			Date prevoiusDate = DicvUtil.getPreviousDayTime(new Date());
			if (ermVehicle != null && ermVehicle.getDicvUser().getUserId() != userId) {
				ErmVehicleDto ermVehicleDto = new ErmVehicleDto();
				List<ErmVehicleDto> list = new ArrayList<ErmVehicleDto>();
				ermVehicleDto.setGpsImei(ermVehicle.getGpsImei().getGpsImei());
				ermVehicleDto.setGpsSimNumber(ermVehicle.getGpsImei().getGpsSimNumber());
				ermVehicleDto.setErmVehicleId(ermVehicleId);
				if (ermVehicle.getGpsImei() != null && ermVehicle.getGpsImei().getGpsTransmittedTime() != null) {
					ermVehicleDto.setGpsReceivedTime(
							DicvUtil.getStringForTimestamp(ermVehicle.getGpsImei().getGpsTransmittedTime()));
				} else {
					ermVehicleDto.setGpsReceivedTime("");
				}
				if (ermVehicle.getLastReceivedTime() != null && ermVehicle.getLastReceivedTime().after(prevoiusDate)) {
					ermVehicleDto.setGpsTranmission(true);
				} else {
					ermVehicleDto.setGpsTranmission(false);
				}
				ermVehicleDto.setStatus(ermVehicle.getStatus());
				ermVehicleDto.setUserId(userId);
				list.add(ermVehicleDto);
				ermVehicleListDto.setVehicleList(list);
				ermVehicleListDto.setStatus(ermVehicle.getStatus());
			} else {
				ermVehicleListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				ermVehicleListDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			}
		} catch (Exception ex) {
			LOGGER.info("Exception in Getting ERM Vehicle  " + ex);
			ermVehicleListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			ermVehicleListDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}
		return ermVehicleListDto;
	}

	@Transactional
	public StatusMessageDto submit(ErmVehicleSubmit ermVehicleSubmit) {
		ErmVehicleListDto ermVehicleListDto = new ErmVehicleListDto();
		try {
			DicvUser user = userService.getUser(ermVehicleSubmit.getUserId(), "Dealer - VehicleList");
			if (user == null || !user.getUserType().getUserType().equals(EnumUserType.ERM.getUserType())) {
				ermVehicleListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				ermVehicleListDto.setMessage("Logged in User is Not a ERM");
				return ermVehicleListDto;
			}
			if (ermVehicleSubmit.getErmVehicleIds() == null || ermVehicleSubmit.getErmVehicleIds().isEmpty()) {
				ermVehicleListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				ermVehicleListDto.setMessage("Vehicle List is Empty");
				return ermVehicleListDto;
			}
			Boolean submit = true;
			List<ErmVehicle> ermList = new ArrayList<ErmVehicle>();
			for (Integer ermId : ermVehicleSubmit.getErmVehicleIds()) {
				ErmVehicle ermVehicle = ermVehicleRepo.findOne(ermId);
				ermVehicle.setUpdatedTime(DicvUtil.getTimestamp());
				GpsImei gpsImei = ermVehicle.getGpsImei();
				ermVehicleListDto.setMessage("Vehicle Profile Submitted Successfully");
				if (gpsImei == null) {
					gpsImei = new GpsImei();
					gpsImei.setIsDeleted(0);
					gpsImei.setCreatedDate(DicvUtil.getTimestamp());
					gpsImei.setDicvUserCreatedBy(user);
					gpsImei.setErmVehicle(ermVehicle);
					gpsImei.setUpdatedDate(DicvUtil.getTimestamp());
				}
				if (ermVehicle.getLastReceivedTime() != null) {
					ermVehicle.setStatus(1);
					ermList.add(ermVehicle);
				} else {
					submit = false;
					break;
				}
			}
			if (!submit) {
				ermVehicleListDto.setMessage("Please Check Gps Transmission");
			} else {
				ermVehicleListDto.setMessage("Vehicles Submitted Successfully");

				for (ErmVehicle erm : ermList) {
					ermVehicleRepo.save(erm);
					createVehicle(erm);
					saveUserLog(user.getUserId(), erm.getErmVehicleId(), DicvConstants.ERM, "SUBMIT");
				}

			}
			ermVehicleListDto.setStatus(HttpServletResponse.SC_OK);

		} catch (Exception ex) {
			LOGGER.info("Exception in Getting ERM Vehicle  " + ex);
			ermVehicleListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			ermVehicleListDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}
		return ermVehicleListDto;
	}

}
