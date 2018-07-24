package com.dicv.truck.service;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicv.truck.dto.DealerVehicleDto;
import com.dicv.truck.dto.DealerVehicleDtoList;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.dto.VehicleFileUploadDto;
import com.dicv.truck.dto.VehicleFileUploadListDto;
import com.dicv.truck.dto.VehicleUploadFileList;
import com.dicv.truck.dto.VeicleUploadFileDto;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.GpsImei;
import com.dicv.truck.model.UserLog;
import com.dicv.truck.model.Vehicle;
import com.dicv.truck.model.VehicleFileUpload;
import com.dicv.truck.repo.GpsImeiRepo;
import com.dicv.truck.repo.UserLogRepo;
import com.dicv.truck.repo.VehicleFileUploadRepo;
import com.dicv.truck.repo.VehicleRepo;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.EnumUserType;
import com.dicv.truck.utility.RecordStatus;

@Service
public class DealerService {

	@Autowired
	private UserService userService;

	@Autowired
	private VehicleRepo vehicleRepo;

	@Autowired
	private VehicleServices vehicleServices;

	@Autowired
	private DicvServices dicvService;

	@Autowired
	private GpsImeiRepo gpsImeiRepo;

	@Autowired
	private UserLogRepo userLogRepo;

	@Autowired
	private VehicleFileUploadRepo vehicleFileUploadRepo;

	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServices.class);

	@Transactional
	public StatusMessageDto save(DealerVehicleDto vehicleDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			if (vehicleDto.getVehicleId() == null)
				vehicleDto.setVehicleId(0);

			if (vehicleDto != null && vehicleDto.getUserId() != null && vehicleDto.getGpsImei() != null
					&& vehicleDto.getGpsSimNumber() != null && vehicleDto.getRegistrationId() != null
					&& vehicleDto.getVin() != null) {

				String validation = validateVehicle(vehicleDto);

				if (!validation.equals(DicvConstants.SUCCESS)) {
					statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					statusMessageDto.setMessage(validation);
					return statusMessageDto;
				}

				DicvUser user = userService.getUser(vehicleDto.getUserId(), "Dealer User ");

				Vehicle vehicle = new Vehicle();
				GpsImei gpsImei = new GpsImei();
				if (vehicleDto.getVehicleId() > 0) {
					LOGGER.info("Update Vehicle Details " + vehicleDto);
					vehicle = vehicleServices.getVehicleDetails(vehicleDto.getVehicleId());
					if (vehicle == null) {
						statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						statusMessageDto.setMessage("Vehicle Not Available");
						return statusMessageDto;
					}
					gpsImei = vehicle.getGpsImei();
				} else {
					vehicle = new Vehicle();
					vehicle.setEnabled(RecordStatus.IS_NOT_ENABLE.getStatus());
					vehicle.setIsDeleted(RecordStatus.IS_NOT_DELETED.getStatus());
					vehicle.setVehicleStatus("AVAILABLE");
					vehicle.setRunningStatus("NEW");
					gpsImei.setVehicle(vehicle);
					gpsImei.setCreatedDate(DicvUtil.getTimestamp());
					gpsImei.setDicvUserCreatedBy(user);
					vehicle.setCreatedByUser(user);
					gpsImei.setGpsImei(vehicleDto.getGpsImei());
				}
				gpsImei.setDicvUserUpdatedBy(user);
				gpsImei.setUpdatedDate(DicvUtil.getTimestamp());
				vehicle.setModifiedDateTime(DicvUtil.getTimestamp());
				vehicle.setDicvType(null);
				if (vehicleDto.getVehicleTypeId() != null) {
					vehicle.setDicvType(dicvService.getTypeById(vehicleDto.getVehicleTypeId()));
				}
				vehicle.setDicvCategory(null);
				if (vehicleDto.getVehicleCategory() != null) {
					vehicle.setDicvCategory(dicvService.getDicvCategoryId(vehicleDto.getVehicleCategory()));
				}
				gpsImei.setGpsSimNumber(vehicleDto.getGpsSimNumber());
				vehicle.setGpsImei(gpsImei);
				vehicle.setRegistrationId(vehicleDto.getRegistrationId());
				vehicle.setVin(vehicleDto.getVin());
				vehicle.setDescription(vehicleDto.getDescription());
				vehicle.setDicvCompany(null);
				if (vehicleDto.getCompanyId() != null) {
					vehicle.setDicvCompany(dicvService.getDicvCompany(vehicleDto.getCompanyId()));
				}
				if (vehicleDto.getPurchaseDate() != null) {
					vehicle.setPurchaseDate(
							new Timestamp(DicvUtil.stringToDate(vehicleDto.getPurchaseDate()).getTime()));
				}
				vehicle.setDealerUser(user);
				vehicle.setDicvUser(userService.getUser(2, "Root Admin - Dealer"));
				vehicleRepo.save(vehicle);
				if (vehicleDto.getVehicleId() != null)
					saveUserLog(user.getUserId(), vehicle.getVehicleId(), DicvConstants.VEHICLE, "UPDATE");
				else
					saveUserLog(user.getUserId(), vehicle.getVehicleId(), DicvConstants.VEHICLE, "ADD");
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setIdentifier(vehicle.getVehicleId());
				statusMessageDto.setMessage(vehicleDto.getVehicleId() != null && vehicleDto.getVehicleId() > 0
						? DicvConstants.SUCCESS_UPDATED
						: DicvConstants.SUCCESS_CREATED);
				return statusMessageDto;

			} else {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage("Please fill all Mandatory Details");
			}

		} catch (IllegalArgumentException ex) {
			LOGGER.info("Vehicle Not Found " + vehicleDto.getVehicleId());
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage("Vehicle Not Found");
			return statusMessageDto;
		} catch (Exception ex) {
			LOGGER.info("Exception in Dealer Vehicle  " + ex);
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return statusMessageDto;
		}
		return statusMessageDto;
	}

	private String validateVehicle(DealerVehicleDto vehicleDto) {
		if (vehicleDto.getGpsImei() != null && vehicleDto.getGpsImei().toString().length() != 15) {
			return "The IMEI number should have length of 15";
		}
		if (vehicleDto.getVin().length() != 17) {
			return "The VIN should have length of 17";
		}
		Long existingVehicle = vehicleRepo.findByRegId(vehicleDto.getRegistrationId(), vehicleDto.getVehicleId());
		if (existingVehicle > 0) {
			return "The Registration id  is already associated to different vehicle.";
		}
		existingVehicle = vehicleRepo.findByVin(vehicleDto.getVin(), vehicleDto.getVehicleId());
		if (existingVehicle > 0) {
			return "VIN is already associated to different vehicle.";
		}
		if (vehicleDto.getVehicleId() <= 0) {
			Long imeiNumber = gpsImeiRepo.findByGpsImeiNumber(vehicleDto.getGpsImei());
			if (imeiNumber > 0) {
				return "The gps_imei number is already associated with vehicle, kindly provide different number.";
			}
		}
		return DicvConstants.SUCCESS;
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

	private DealerVehicleDtoList getVehicleList(Integer userId) {

		DealerVehicleDtoList dealerVehicleDtoList = new DealerVehicleDtoList();
		try {
			DicvUser user = userService.getUser(userId, "Dealer - VehicleList");
			String userType = user.getUserType().getUserType();
			if (!userType.equals(EnumUserType.DEALER.getUserType())) {
				dealerVehicleDtoList.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				dealerVehicleDtoList.setMessage("Logged in User is Not a dealer");
				return dealerVehicleDtoList;
			}
			List<Vehicle> vehicles = vehicleRepo.getVehicleList();
			if (vehicles == null || vehicles.isEmpty()) {
				dealerVehicleDtoList.setStatus(HttpServletResponse.SC_NO_CONTENT);
				dealerVehicleDtoList.setMessage("No Vehicle Found");
				return dealerVehicleDtoList;
			}
			List<DealerVehicleDto> vehicleDtoList = new ArrayList<DealerVehicleDto>();
			DealerVehicleDto dealerVehicleDto = new DealerVehicleDto();
			Date prevoiusDate = DicvUtil.getPreviousDayTime(new Date());
			for (Vehicle veh : vehicles) {
				dealerVehicleDto = new DealerVehicleDto();
				dealerVehicleDto.setRegistrationId(veh.getRegistrationId());
				dealerVehicleDto.setGpsTranmission(false);
				if (veh.getGpsImei() != null) {
					dealerVehicleDto.setGpsImei(veh.getGpsImei().getGpsImei());
					dealerVehicleDto.setGpsSimNumber(veh.getGpsImei().getGpsSimNumber());
					if (veh.getVehicleUpdateTime() != null && veh.getVehicleUpdateTime().after(prevoiusDate)) {
						dealerVehicleDto.setGpsTranmission(true);
					} else {
						dealerVehicleDto.setGpsTranmission(false);
					}
				}
				if (veh.getErmVehicle() != null && veh.getErmVehicle().getDicvUser() != null)
					dealerVehicleDto.setErmUser(veh.getErmVehicle().getDicvUser().getUserName());
				dealerVehicleDto.setVehicleId(veh.getVehicleId());
				dealerVehicleDto.setVin(veh.getVin());
				if (veh.getDicvCompany() != null) {
					dealerVehicleDto.setCompanyId(veh.getDicvCompany().getCompanyId());
					dealerVehicleDto.setCompanyName(veh.getDicvCompany().getCompanyName());
				}
				dealerVehicleDto.setDescription(veh.getDescription());
				dealerVehicleDto.setMaxPayLoadCapacity(veh.getMaxPayloadCapacity());
				dealerVehicleDto.setMaxVehicleSpeed(veh.getVehicleMaxSpeed());
				if (veh.getDicvCategory() != null) {
					dealerVehicleDto.setVehicleCategory(veh.getDicvCategory().getCategoryId());
					dealerVehicleDto.setVehicleCategoryName(veh.getDicvCategory().getCategoryName());
				}
				if (veh.getDicvType() != null) {
					dealerVehicleDto.setVehicleTypeId(veh.getDicvType().getTypeId());
					dealerVehicleDto.setVehicleTypeName(veh.getDicvType().getTypeName());
				}
				dealerVehicleDto.setVehicleCanParam(
						veh.getVehicleCanParam() != null && veh.getVehicleCanParam().getVehicleCanParamId() != null
								? true
								: false);
				vehicleDtoList.add(dealerVehicleDto);
			}
			dealerVehicleDtoList.setVehicle(vehicleDtoList);
			dealerVehicleDtoList.setStatus(HttpServletResponse.SC_OK);
			dealerVehicleDtoList.setMessage(DicvConstants.DATA_FOUND_MSG);

		} catch (Exception e) {
			LOGGER.error("Exception in Getting Vehicle List " + e.getMessage());
			dealerVehicleDtoList.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			dealerVehicleDtoList.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}
		return dealerVehicleDtoList;
	}

	public DealerVehicleDtoList getVehicleDetail(Integer userId, Integer vehicleId) {
		if (vehicleId != null) {
			return getVehicleDetails(userId, vehicleId);
		} else {
			return getVehicleList(userId);
		}
	}

	private DealerVehicleDtoList getVehicleDetails(Integer userId, Integer vehicleId) {
		DealerVehicleDtoList dealerDtolist = new DealerVehicleDtoList();
		DealerVehicleDto dealerDto = new DealerVehicleDto();
		try {
			DicvUser user = userService.getUser(userId, "Dealer - VehicleList");
			String userType = user.getUserType().getUserType();
			if (!userType.equals(EnumUserType.DEALER.getUserType())) {
				dealerDtolist.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				dealerDtolist.setMessage("Logged in User is Not a dealer");
				return dealerDtolist;
			}
			List<DealerVehicleDto> vehicle = new ArrayList<DealerVehicleDto>();
			Vehicle veh = vehicleServices.getVehicleDetails(vehicleId);
			if (veh != null && veh.getDealerUser().getUserId().equals(userId) && veh.getIsDeleted() == 0) {
				dealerDto.setRegistrationId(veh.getRegistrationId());
				dealerDto.setGpsTranmission(false);
				if (veh.getGpsImei() != null) {
					dealerDto.setGpsImei(veh.getGpsImei().getGpsImei());
					dealerDto.setGpsSimNumber(veh.getGpsImei().getGpsSimNumber());
					if (veh.getVehicleUpdateTime() != null
							&& veh.getVehicleUpdateTime().after(DicvUtil.getPreviousDayTime(new Date()))) {
						dealerDto.setGpsTranmission(true);
					} else {
						dealerDto.setGpsTranmission(false);
					}
				}
				dealerDto.setRegistrationId(veh.getRegistrationId());
				dealerDto.setGpsTranmission(false);
				dealerDto.setVehicleId(veh.getVehicleId());
				dealerDto.setVin(veh.getVin());
				dealerDto.setDescription(veh.getDescription());
				dealerDto.setMaxPayLoadCapacity(veh.getMaxPayloadCapacity());
				dealerDto.setMaxVehicleSpeed(veh.getVehicleMaxSpeed());
				if (veh.getDicvCategory() != null) {
					dealerDto.setVehicleCategory(veh.getDicvCategory().getCategoryId());
					dealerDto.setVehicleCategoryName(veh.getDicvCategory().getCategoryName());
				}
				if (veh.getDicvCompany() != null) {
					dealerDto.setCompanyId(veh.getDicvCompany().getCompanyId());
					dealerDto.setCompanyName(veh.getDicvCompany().getCompanyName());
				}
				if (veh.getDicvType() != null) {
					dealerDto.setVehicleTypeId(veh.getDicvType().getTypeId());
					dealerDto.setVehicleTypeName(veh.getDicvType().getTypeName());
				}
				dealerDto.setVehicleCanParam(
						veh.getVehicleCanParam() != null && veh.getVehicleCanParam().getVehicleEngineRpm() != null
								? true
								: false);
				vehicle.add(dealerDto);
				dealerDtolist.setVehicle(vehicle);
				dealerDtolist.setStatus(HttpServletResponse.SC_OK);
				dealerDtolist.setMessage(DicvConstants.DATA_FOUND_MSG);

			} else {
				dealerDtolist.setStatus(HttpServletResponse.SC_NO_CONTENT);
				dealerDtolist.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			}
		} catch (Exception ex) {
			LOGGER.info("Exception in Getting Vehicle  " + ex);
			dealerDtolist.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			dealerDtolist.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return dealerDtolist;
		}
		return dealerDtolist;
	}

	public StatusMessageDto upload(VehicleUploadFileList vehicleFileUploadListDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {

			DicvUser user = userService.getUser(vehicleFileUploadListDto.getUserId(), "Dealer - Vehicle File Upload");
			if (user == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage("User Not Available");
				return statusMessageDto;
			}
			String userType = user.getUserType().getUserType();
			if (!userType.equals(EnumUserType.DEALER.getUserType())) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage("Logged in User is Not a dealer");
				return statusMessageDto;
			}
			Vehicle vehicle = vehicleServices.getVehicleDetails(vehicleFileUploadListDto.getVehicleId());
			if (vehicle == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage("Vehicle Not Available");
				return statusMessageDto;
			}

			for (VeicleUploadFileDto vehicleFileDto : vehicleFileUploadListDto.getFileList()) {

				VehicleFileUpload upload = new VehicleFileUpload();
				if (vehicleFileDto.getFileId() != null) {
					upload = vehicleFileUploadRepo.findOne(vehicleFileDto.getFileId());
				}
				if (upload == null || upload.getFileId() == null) {
					upload.setCreatedTime(DicvUtil.getTimestamp());

				}
				upload.setContent(vehicleFileDto.getContent().getBytes(StandardCharsets.UTF_8));
				upload.setDescription(vehicleFileDto.getDescription());
				upload.setFileName(vehicleFileDto.getFileName());
				upload.setFileSize(vehicleFileDto.getFileSize());
				upload.setIsDeleted(0);
				upload.setFileType(vehicleFileDto.getFileType());
				upload.setModifiedDateTime(DicvUtil.getTimestamp());
				upload.setVehicle(vehicle);
				upload.setDicvUser(user);
				upload.setVehicle(vehicle);
				vehicleFileUploadRepo.save(upload);
			}
			statusMessageDto.setStatus(HttpServletResponse.SC_OK);
			statusMessageDto.setIdentifier(vehicleFileUploadListDto.getVehicleId());
			statusMessageDto.setMessage("File Uploaded Successfully");
			return statusMessageDto;

		} catch (IllegalArgumentException ex) {
			LOGGER.info("File Not Found " + vehicleFileUploadListDto.getVehicleId());
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage("Vehicle File Not Found ");
			return statusMessageDto;
		} catch (Exception ex) {
			LOGGER.info("Exception in Dealer File Uplaod For Vehicle  " + ex);
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return statusMessageDto;
		}
	}

	public VehicleFileUploadListDto viewfiles(Integer userId, Integer vehicleId) {

		VehicleFileUploadListDto vehicleFileUploadListDto = new VehicleFileUploadListDto();
		try {
			DicvUser user = userService.getUser(userId, "Dealer - VehicleList");
			String userType = user.getUserType().getUserType();
			if (!userType.equals(EnumUserType.DEALER.getUserType())) {
				vehicleFileUploadListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				vehicleFileUploadListDto.setMessage("Logged in User is Not a dealer");
				return vehicleFileUploadListDto;
			}
			Vehicle vehicles = vehicleServices.getVehicleDetails(vehicleId);
			if (vehicles == null) {
				vehicleFileUploadListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				vehicleFileUploadListDto.setMessage("No Vehicle Found");
				return vehicleFileUploadListDto;
			}
			List<VeicleUploadFileDto> fileList = vehicleFileUploadRepo.getFiles(vehicleId);
			if (fileList != null && fileList.size() > 0) {
				vehicleFileUploadListDto.setFileList(fileList);
				vehicleFileUploadListDto.setStatus(HttpServletResponse.SC_OK);
				vehicleFileUploadListDto.setMessage(DicvConstants.DATA_FOUND_MSG);
			} else {
				vehicleFileUploadListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				vehicleFileUploadListDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			}
			return vehicleFileUploadListDto;
		} catch (Exception e) {
			LOGGER.error("Exception in Getting Vehicle List " + e.getMessage());
			vehicleFileUploadListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			vehicleFileUploadListDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return vehicleFileUploadListDto;
		}

	}

	public VehicleFileUploadDto downloadFile(Integer userId, Integer fileId) {

		VehicleFileUploadDto vehicleFileUploadListDto = new VehicleFileUploadDto();
		try {
			DicvUser user = userService.getUser(userId, "Dealer - DownloadFile");
			String userType = user.getUserType().getUserType();
			if (!userType.equals(EnumUserType.DEALER.getUserType())) {
				vehicleFileUploadListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				vehicleFileUploadListDto.setMessage("Logged in User is Not a dealer");
				return vehicleFileUploadListDto;
			}
			VehicleFileUpload vehicleFileUpload = vehicleFileUploadRepo.findOne(fileId);
			if (vehicleFileUpload.getIsDeleted() != 0) {
				vehicleFileUploadListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				vehicleFileUploadListDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);

			} else {
				vehicleFileUploadListDto.setContent(new String(vehicleFileUpload.getContent(), StandardCharsets.UTF_8));
				vehicleFileUploadListDto.setDescription(vehicleFileUpload.getDescription());
				vehicleFileUploadListDto.setFileId(vehicleFileUpload.getFileId());
				vehicleFileUploadListDto.setFileName(vehicleFileUpload.getFileName());
				vehicleFileUploadListDto.setFileSize(vehicleFileUpload.getFileSize());
				vehicleFileUpload.setCreatedTime(DicvUtil.getTimestamp());
				vehicleFileUploadListDto.setFileType(vehicleFileUpload.getFileType());
				vehicleFileUploadListDto.setStatus(HttpServletResponse.SC_OK);
				vehicleFileUploadListDto.setMessage(DicvConstants.DATA_FOUND_MSG);
			}
			return vehicleFileUploadListDto;
		} catch (IllegalArgumentException e) {
			vehicleFileUploadListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			vehicleFileUploadListDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			return vehicleFileUploadListDto;
		} catch (Exception e) {
			LOGGER.error("Exception in Getting Vehicle List " + e.getMessage());
			vehicleFileUploadListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			vehicleFileUploadListDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return vehicleFileUploadListDto;
		}

	}

	public StatusMessageDto deleteFile(Integer userId, Integer fileId) {

		VehicleFileUploadDto vehicleFileUploadListDto = new VehicleFileUploadDto();
		try {
			DicvUser user = userService.getUser(userId, "Dealer - deleteFile");
			String userType = user.getUserType().getUserType();
			if (!userType.equals(EnumUserType.DEALER.getUserType())) {
				vehicleFileUploadListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				vehicleFileUploadListDto.setMessage("Logged in User is Not a dealer");
				return vehicleFileUploadListDto;
			}
			VehicleFileUpload vehicleFileUpload = vehicleFileUploadRepo.findOne(fileId);
			vehicleFileUpload.setIsDeleted(1);
			vehicleFileUpload.setModifiedDateTime(DicvUtil.getTimestamp());
			vehicleFileUploadRepo.save(vehicleFileUpload);
			vehicleFileUploadListDto.setStatus(HttpServletResponse.SC_OK);
			vehicleFileUploadListDto.setMessage("File Deleted Successfully");
			return vehicleFileUploadListDto;
		} catch (IllegalArgumentException e) {
			vehicleFileUploadListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			vehicleFileUploadListDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			return vehicleFileUploadListDto;
		} catch (Exception e) {
			LOGGER.error("Exception in Getting Vehicle List " + e.getMessage());
			vehicleFileUploadListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			vehicleFileUploadListDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return vehicleFileUploadListDto;
		}

	}

}
