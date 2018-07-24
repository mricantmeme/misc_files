package com.dicv.truck.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dicv.truck.dto.DashBoardDto;
import com.dicv.truck.dto.DashBoardListDto;
import com.dicv.truck.dto.OwnerDtlsDto;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.dto.VehicleDashboardListDto;
import com.dicv.truck.dto.VehicleDashboardResponseDto;
import com.dicv.truck.dto.VehicleDtlsDto;
import com.dicv.truck.dto.VehicleDtlsDtoHome;
import com.dicv.truck.dto.VehicleDto;
import com.dicv.truck.dto.VehicleInfoDto;
import com.dicv.truck.dto.VehicleInfoListDto;
import com.dicv.truck.dto.VehicleSpeedListDto;
import com.dicv.truck.dto.VehicleStatusGraphReportDto;
import com.dicv.truck.dto.VehicleUtilizationInputDto;
import com.dicv.truck.dto.VehicleUtilizationListDto;
import com.dicv.truck.dto.VehicleUtilizationResponseDto;
import com.dicv.truck.exception.DataNotFoundException;
import com.dicv.truck.exception.InvalidValueException;
import com.dicv.truck.exception.ServerException;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.GpsImei;
import com.dicv.truck.model.Photo;
import com.dicv.truck.model.Vehicle;
import com.dicv.truck.repo.GpsRepo;
import com.dicv.truck.repo.PhotoRepo;
import com.dicv.truck.repo.UserRepo;
import com.dicv.truck.repo.VehicleRepo;
import com.dicv.truck.repo.VehicleUtilizationRepo;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.EnumUserType;
import com.dicv.truck.utility.RecordStatus;
import com.dicv.truck.utility.VehicleStatus;

@Service
public class VehicleServices {

	@Autowired
	private PhotoRepo photoRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private DicvServices dicvServices;

	@Autowired
	private VehicleRepo vehicleRepo;

	@Autowired
	private GpsRepo gpsRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private VehicleUtilizationRepo vehicleUtilRepo;

	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServices.class);

	@Transactional
	public StatusMessageDto createVehicle(VehicleDto vehicleDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			DicvUser user = userService.getUser(vehicleDto.getUserId(), "Create Vehicle");
			Vehicle vehicleEntity = new Vehicle();

			if (vehicleDto.getVehicleId() <= 0) {
				vehicleEntity = new Vehicle();
				vehicleEntity.setCreatedByUser(user);
				setDefaultVehicleValues(vehicleEntity, vehicleDto, user);
				statusMessageDto.setMessage(DicvConstants.SUCCESS_CREATED);
			} else if (vehicleDto.getVehicleId() > 0) {
				vehicleEntity = getVehicleDetails(vehicleDto.getVehicleId());
				if (null == vehicleEntity) {
					statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					statusMessageDto.setMessage(
							"The vehicle does not exists or does not belong to the user, please provide valid vehicle");
					return statusMessageDto;
				}
				if ((!user.getUserType().getUserType().equals(EnumUserType.ROOTADMIN.getUserType()))
						&& null == vehicleEntity.getDicvUser()) {
					statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					statusMessageDto.setMessage(
							"The vehicle does not does not belong to any user, please provide valid vehicle");
					return statusMessageDto;
				}
				if (DicvUtil.isValidAttribute(vehicleDto.getVehicleStatus())
						&& (DicvConstants.DELETE_RECORD.equalsIgnoreCase(vehicleDto.getVehicleStatus()))) {
					return deleteVehicle(vehicleDto, statusMessageDto, user, vehicleEntity);
				}
				statusMessageDto.setMessage(DicvConstants.SUCCESS_UPDATED);
			}
			GpsImei gpsimei = new GpsImei();
			if (vehicleEntity.getGpsImei() != null) {
				gpsimei = vehicleEntity.getGpsImei();
			}
			updateGpsImei(vehicleDto, vehicleEntity, gpsimei, user);
			setVehicleDetails(vehicleDto, user, vehicleEntity);
			statusMessageDto.setIdentifier(vehicleEntity.getVehicleId());
			statusMessageDto.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			LOGGER.error("Exception in Vehicle crud ", e);
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}
		return statusMessageDto;
	}

	private StatusMessageDto deleteVehicle(VehicleDto vehicleDto, StatusMessageDto statusMessageDto, DicvUser user,
			Vehicle vehicleEntity) {
		markVehicleAsDeleted(user, vehicleEntity, vehicleDto.getVehicleStatus());
		vehicleRepo.save(vehicleEntity);
		userService.saveUserLog(user.getUserId(), vehicleEntity.getVehicleId(), DicvConstants.VEHICLE,
				DicvConstants.DELETE);
		statusMessageDto.setIdentifier(vehicleDto.getVehicleId());
		statusMessageDto.setStatus(HttpServletResponse.SC_OK);
		statusMessageDto.setMessage(DicvConstants.SUCCESS_DELETED);
		return statusMessageDto;
	}

	public Vehicle getVehicleDetails(Integer vehicleId) {
		try {
			Vehicle vehicle = new Vehicle();
			List<Vehicle> vehicleList = null;
			vehicleList = vehicleRepo.getVehicleDetails(vehicleId);
			if (vehicleList != null && !vehicleList.isEmpty()) {
				vehicle = vehicleList.get(0);
				return vehicle;
			}
			return null;
		} catch (Exception ex) {
			LOGGER.error("Vehicle Not Found :: " + vehicleId);
			return null;
		}
	}

	private void setDefaultVehicleValues(Vehicle vehicleEntity, VehicleDto vehicleDto, DicvUser user) {
		vehicleEntity.setEnabled(RecordStatus.IS_NOT_ENABLE.getStatus());
		vehicleEntity.setVehicleStatus(VehicleStatus.AVAILABLE.getStatusCode());
		vehicleEntity.setRunningStatus(VehicleStatus.NEW.getStatusCode());
		vehicleEntity.setIsDeleted(RecordStatus.IS_NOT_DELETED.getStatus());
		vehicleEntity.setCurrentEngineSpeed(0);
		vehicleEntity.setCurrentLat(0.0);
		vehicleEntity.setCurrentLong(0.0);
		vehicleEntity.setCurrentVehicleSpeed(0);
	}

	private void updateGpsImei(VehicleDto vehicleDto, Vehicle vehicle, GpsImei gpsImei, DicvUser user) {
		if (gpsImei.getGpsImeiId() == null) {
			gpsImei.setIsDeleted(0);
			gpsImei.setDicvUserCreatedBy(user);
			gpsImei.setCreatedDate(DicvUtil.getTimestamp());
			gpsImei.setGpsImei(vehicleDto.getGpsImei());
		}
		gpsImei.setDicvUserUpdatedBy(user);
		gpsImei.setUpdatedDate(DicvUtil.getTimestamp());
		gpsImei.setDescription(vehicleDto.getGpsImeiDescription());
		gpsImei.setGpsSimNumber(vehicleDto.getGpsSimNumber());
		gpsImei.setGpsProvider(vehicleDto.getGpsProvider());
		gpsImei.setTabletSimNumber(vehicleDto.getTabletSimNumber());
		gpsImei.setTabletImei(vehicleDto.getTabletIMEINumber());
		gpsImei.setTabletProvider(vehicleDto.getTabletProvider());
		gpsImei.setDicvCountry1(null);
		gpsImei.setDicvCountry2(null);
		if (null != vehicleDto.getGpsCountryId()) {
			gpsImei.setDicvCountry1(dicvServices.getCountryDetails(vehicleDto.getGpsCountryId()));
		}
		if (null != vehicleDto.getTabletCountryId()) {
			gpsImei.setDicvCountry2(dicvServices.getCountryDetails(vehicleDto.getTabletCountryId()));
		}
		gpsImei.setVehicle(vehicle);
		vehicle.setGpsImei(gpsImei);
	}

	@Transactional
	private void markVehicleAsDeleted(DicvUser user, Vehicle vehicleEntity, String vehicleStatus) {
		if (null != vehicleEntity.getGpsImei()) {
			vehicleEntity.getGpsImei().setIsDeleted(RecordStatus.DELETED.getStatus());
		}
		if (null != vehicleEntity.getPhoto()) {
			vehicleEntity.getPhoto().setIsDeleted(RecordStatus.DELETED.getStatus());
		}
		if (vehicleStatus.equalsIgnoreCase(DicvConstants.DELETE_RECORD)) {
			vehicleEntity.setEnabled(RecordStatus.IS_NOT_ENABLE.getStatus());
			vehicleEntity.setIsDeleted(RecordStatus.DELETED.getStatus());
		} else {
			vehicleEntity.setEnabled(RecordStatus.IS_NOT_ENABLE.getStatus());
		}
		vehicleEntity.setVehicleStatus(VehicleStatus.NOTAVAILABLE.getStatusCode());
		vehicleEntity.setDicvUser(user);
		vehicleEntity.setModifiedDateTime(DicvUtil.getTimestamp());
	}

	@Transactional
	private void setVehicleDetails(VehicleDto vehicleDto, DicvUser user, Vehicle vehicleEntity) throws ServerException {
		vehicleEntity.setDicvType(dicvServices.getTypeById(vehicleDto.getVehicleTypeId()));
		vehicleEntity.setDicvCategory(dicvServices.getDicvCategoryId(vehicleDto.getVehicleCategory()));
		vehicleEntity.setDescription(vehicleDto.getDescription());
		vehicleEntity.setMaxPayloadCapacity(vehicleDto.getMaxPayLoadCapacity());
		if (vehicleDto.getImageString() != null && !vehicleDto.getImageString().isEmpty()) {
			Photo photo = setPhoto(vehicleDto.getImageString(), user, DicvUtil.getTimestamp());
			photo = photoRepo.save(photo);
			vehicleEntity.setPhoto(photo);
		} else {
			vehicleEntity.setPhoto(null);
		}
		vehicleEntity.setVehicleMaxSpeed(vehicleDto.getMaxVehicleSpeed());
		if (vehicleDto.getMaxVehicleSpeed() == null)
			vehicleEntity.setVehicleMaxSpeed(80);

		vehicleEntity.setDicvCountry(dicvServices.getCountryDetails(vehicleDto.getCountryId()));
		vehicleEntity.setRegistrationId(vehicleDto.getRegistrationId().toUpperCase());
		if (vehicleDto.getVin() != null)
			vehicleEntity.setVin(vehicleDto.getVin().toUpperCase());
		vehicleEntity.setPurchaseDate(DicvUtil.getStringToDate(vehicleDto.getPurchaseDate()));
		vehicleEntity.setDicvGroup(dicvServices.getDicvGroup(vehicleDto.getGroupId()));
		vehicleEntity.setDicvUser(user);
		if (EnumUserType.ROOTADMIN.getUserType().equals(user.getUserType().getUserType())) {
			if (vehicleDto.getRootAdminGroupId() != null)
				vehicleEntity.setRootAdminGroup(dicvServices.getDicvGroup(vehicleDto.getRootAdminGroupId()));
			if (vehicleDto.getCompanyId() != null) {
				vehicleEntity.setDicvCompany(dicvServices.getDicvCompany(vehicleDto.getCompanyId()));
			} else {
				vehicleEntity.setDicvCompany(null);
			}
			if (vehicleDto.getCustomerAdminId() != null) {
				vehicleEntity.setDicvUser(userService.getUser(vehicleDto.getCustomerAdminId(), "Get Customer Admin"));
			} else {
				vehicleEntity.setDicvUser(user);
			}
		} else {
			if (user.getDicvCompany() != null) {
				vehicleEntity.setDicvCompany(user.getDicvCompany());
			}
			if (user.getManagerId() != null) {
				vehicleEntity.setDicvUser(user);
			}
		}
		vehicleEntity.setDicvRegion(dicvServices.getDicvRegion(vehicleDto.getRegionId()));
		vehicleEntity.setModel(vehicleDto.getVehicleModel());
		vehicleEntity.setMake(vehicleDto.getVehicleMake());
		vehicleEntity.setVariant(vehicleDto.getVariant());
		vehicleEntity.setRouteNumber(vehicleDto.getRouteNumber());
		vehicleEntity.setModifiedDateTime(DicvUtil.getTimestamp());
		vehicleEntity.setDefaultDriver(null);
		vehicleEntity = vehicleRepo.save(vehicleEntity);
		if (vehicleDto.getDefaultDriverId() != null && vehicleDto.getDefaultDriverId() > 0) {
			DicvUser driverUser = userService.getUser(vehicleDto.getDefaultDriverId(), "Default Driver ");
			vehicleEntity.setDefaultDriver(driverUser);
			assignDriverToCompanyAndCustomerAdmin(driverUser, vehicleEntity);
		} else {
			DicvUser dicvUser = userService.createDriverForVehicle(vehicleEntity);
			vehicleEntity.setDefaultDriver(dicvUser);
			vehicleEntity = vehicleRepo.save(vehicleEntity);
			userService.saveUserLog(user.getUserId(), vehicleEntity.getVehicleId(), DicvConstants.VEHICLE,
					DicvConstants.CREATE);
		}
		if (vehicleDto.getVehicleId() > 0) {
			userService.saveUserLog(user.getUserId(), vehicleEntity.getVehicleId(), DicvConstants.VEHICLE,
					DicvConstants.UPDATE);
			if (vehicleEntity.getDefaultDriver() != null) {
				userService.updateDriverForVehicle(vehicleEntity);
			}
		}
	}

	private void assignDriverToCompanyAndCustomerAdmin(DicvUser driverUser, Vehicle vehicleEntity) {
		if (vehicleEntity.getDicvCompany() != null) {
			if (driverUser.getDicvCompany() == null
					|| (driverUser.getDicvCompany().getCompanyId() != vehicleEntity.getDicvCompany().getCompanyId())) {
				driverUser.setDicvCompany(vehicleEntity.getDicvCompany());
				if (driverUser.getManagerId() == null
						|| (driverUser.getManagerId() != vehicleEntity.getDicvUser().getUserId())) {
					driverUser.setManagerId(vehicleEntity.getDicvUser().getUserId());
				}
				userRepo.save(driverUser);
			}
		}
	}

	@Transactional
	private Photo setPhoto(String imageSource, DicvUser createBy, Timestamp date) {

		try {
			byte[] base64ToBytes = DicvUtil.Base64ToBytes(imageSource);
			Photo photo = new Photo();
			if (null != createBy) {
				photo.setCreatedBy(createBy.getUserId());
				photo.setUpdatedBy(createBy.getUserId());
			}
			photo.setModifiedDate(date);
			photo.setIsDeleted(0);
			photo.setImage(base64ToBytes);
			photo = photoRepo.save(photo);
			return photo;
		} catch (IOException e) {
			return null;
		}
	}

	public OwnerDtlsDto getVehicleList(Integer userId, String page, Integer startRow, Integer endRow) {

		OwnerDtlsDto ownerDto = new OwnerDtlsDto();
		try {
			DicvUser authUser = userService.getUser(userId, "getVehicleList");
			String userType = authUser.getUserType().getUserType();
			List<Vehicle> vehicles = getVehicleListByUser(userId, userType);
			if (DicvConstants.VEHICLELIST_HOME.equals(page)) {
				return getHomePageVehicleList(ownerDto, vehicles);
			} else {
				List<VehicleDtlsDto> vehicleList = new ArrayList<VehicleDtlsDto>();
				for (Vehicle vehicle : vehicles) {
					if (null != page && DicvConstants.VEHICLELIST_PROFILES.equalsIgnoreCase(page)) {
						vehicleList.add(setVehicleDetail(vehicle, false, userType));
					} else if (!(VehicleStatus.NEW.getStatusCode().equals(vehicle.getRunningStatus()))) {
						vehicleList.add(setVehicleDetail(vehicle, true, userType));
					}
				}
				ownerDto.setVehicles(vehicleList);
				ownerDto.setOwnerId(userId);
				return ownerDto;
			}
		} catch (DataNotFoundException e) {
			throw new DataNotFoundException(DicvConstants.DATA_NOT_FOUND_MSG);
		} catch (Exception e) {
			LOGGER.error("Exception in Getting Vehicle List " + e.getMessage());
			throw new ServerException(e.getMessage());
		}
	}

	public List<Vehicle> getVehicleListByUser(Integer userId, String role) {
		List<Vehicle> vehicles = null;
		if (role.equals(EnumUserType.ROOTADMIN.getUserType())) {
			vehicles = vehicleRepo.getVehicleList();

		} else if (role.equals(EnumUserType.CUSTOMERADMIN.getUserType())) {
			vehicles = vehicleRepo.getVehicleList(userId);
		}
		return vehicles;
	}

	private OwnerDtlsDto getHomePageVehicleList(OwnerDtlsDto ownerDto, List<Vehicle> vehicles) {
		VehicleDtlsDtoHome vehicleDtl;
		List<VehicleDtlsDtoHome> vehicleDtlsDtoHomeList = new ArrayList<VehicleDtlsDtoHome>();
		for (Vehicle vehicle : vehicles) {
			vehicleDtl = new VehicleDtlsDtoHome();
			if (null != vehicle.getDicvCountry()) {
				vehicleDtl.setCountryCode(vehicle.getDicvCountry().getCountryCode());
				vehicleDtl.setCountryName(vehicle.getDicvCountry().getCountryName());
			}
			if (null != vehicle.getVehicleId()) {
				vehicleDtl.setVehicleId(vehicle.getVehicleId());
			}
			if (null != vehicle.getRunningStatus()) {
				vehicleDtl.setVehicleRunningStatus(vehicle.getRunningStatus());
			}
			vehicleDtlsDtoHomeList.add(vehicleDtl);
		}
		ownerDto.setVehicleDtlsDtoHomeList(vehicleDtlsDtoHomeList);
		return ownerDto;
	}

	private VehicleDtlsDto setVehicleDetail(Vehicle vehicle, boolean isVehicleImageRequired, String role) {
		VehicleDtlsDto vehicleDtls = new VehicleDtlsDto();
		if (null != vehicle.getVehicleId()) {
			vehicleDtls.setVehicleId(vehicle.getVehicleId());
		}

		vehicleDtls.setRegistrationId(vehicle.getRegistrationId());

		vehicleDtls.setVehicleStatus(vehicle.getVehicleStatus());

		vehicleDtls.setDescription(vehicle.getDescription());

		vehicleDtls.setVehicleRunningStatus(vehicle.getRunningStatus());

		vehicleDtls.setRouteNumber(vehicle.getRouteNumber());

		vehicleDtls.setVin(vehicle.getVin());
		if (vehicle.getDealerUser() != null)
			vehicleDtls.setDealerName(vehicle.getDealerUser().getFirstName() + vehicle.getDealerUser().getLastName());

		if (null != vehicle.getDicvCountry())

		{
			vehicleDtls.setCountryCode(vehicle.getDicvCountry().getCountryCode());
			vehicleDtls.setCountryName(vehicle.getDicvCountry().getCountryName());
		}

		vehicleDtls.setCurrentLat(vehicle.getCurrentLat());
		vehicleDtls.setCurrentLong(vehicle.getCurrentLong());

		if (null != vehicle.getDicvType()) {
			vehicleDtls.setVehicleTypeId(vehicle.getDicvType().getTypeId());
			vehicleDtls.setVehicleType(vehicle.getDicvType().getTypeName());
		}
		if (vehicle.getDicvUser() != null) {
			vehicleDtls.setCustomerAdmin(vehicle.getDicvUser().getFirstName() + vehicle.getDicvUser().getLastName());
			vehicleDtls.setCustomerAdminId(vehicle.getDicvUser().getUserId());
			vehicleDtls.setOwnerId(vehicle.getDicvUser().getUserId());
		}

		if (null != vehicle.getDicvCompany()) {
			vehicleDtls.setCompanyId(vehicle.getDicvCompany().getCompanyId());
			vehicleDtls.setCompanyName(vehicle.getDicvCompany().getCompanyName());

		}

		if (null != vehicle.getPurchaseDate()) {
			vehicleDtls.setPurchaseDate(vehicle.getPurchaseDate().toString());
		}

		if (null != vehicle.getDicvGroup()) {

			vehicleDtls.setGroupId(vehicle.getDicvGroup().getGroupId());
			vehicleDtls.setGroupName(vehicle.getDicvGroup().getGroupName());
		}

		if (role.equalsIgnoreCase(EnumUserType.ROOTADMIN.getUserType()) && null != vehicle.getRootAdminGroup()) {
			vehicleDtls.setRootAdminGroupId(vehicle.getRootAdminGroup().getGroupId());
			vehicleDtls.setRootAdminGroupName(vehicle.getRootAdminGroup().getGroupName());
		}

		if (null != vehicle.getDicvRegion()) {
			vehicleDtls.setRegionId(vehicle.getDicvRegion().getRegionId());
			vehicleDtls.setRegionName(vehicle.getDicvRegion().getRegionName());
		}

		if (null != vehicle.getGpsImei()) {
			if (null != vehicle.getGpsImei().getGpsImei())
				vehicleDtls.setGpsImei(vehicle.getGpsImei().getGpsImei().toString());
			if (null != vehicle.getGpsImei().getGpsSimNumber())
				vehicleDtls.setGpsSimNumber(vehicle.getGpsImei().getGpsSimNumber().toString());
			vehicleDtls.setGpsImeiDescription(vehicle.getGpsImei().getDescription());
			if (null != vehicle.getGpsImei().getTabletImei())
				vehicleDtls.setTabletIMEINumber(vehicle.getGpsImei().getTabletImei().toString());
			if (null != vehicle.getGpsImei().getTabletSimNumber())
				vehicleDtls.setTabletSimNumber(vehicle.getGpsImei().getTabletSimNumber().toString());

			vehicleDtls.setTabletProvider(vehicle.getGpsImei().getTabletProvider());
		}

		if (isVehicleImageRequired && null != vehicle.getPhoto() && null != vehicle.getPhoto().getImage()) {
			vehicleDtls.setImageString(new String(Base64.encodeBase64(vehicle.getPhoto().getImage())));
		}

		if (null != vehicle.getDicvCategory()) {
			vehicleDtls.setVehicleCategory(vehicle.getDicvCategory().getCategoryId());
			vehicleDtls.setVehicleCategoryDesc(vehicle.getDicvCategory().getCategoryName());

		}

		vehicleDtls.setMaxPayLoadCapacity(vehicle.getMaxPayloadCapacity());
		vehicleDtls.setMaxVehicleSpeed(vehicle.getVehicleMaxSpeed());

		if (null != vehicle.getEnabled()) {
			vehicleDtls.setRecordStatus(vehicle.getEnabled().toString());
		}
		if (null != vehicle.getDefaultDriver()) {
			vehicleDtls.setDefaultDriverId(vehicle.getDefaultDriver().getUserId());
			vehicleDtls.setDefaultDriver(vehicle.getDefaultDriver().getUserName());
		}
		return vehicleDtls;
	}

	public DashBoardListDto getDashBoardInfo(Integer userId) {
		DashBoardListDto dashBoardListDto = new DashBoardListDto();
		try {
			DicvUser user = userService.getUser(userId, "getDashBoardInfo");
			String role = user.getUserType().getUserType();
			Long totalCount = 0l;
			List<Object[]> list = null;
			if (role.equalsIgnoreCase(EnumUserType.ROOTADMIN.getUserType())) {
				totalCount = vehicleRepo.vehicleCount();
				list = vehicleRepo.getVehicleCountryAndStatus();
			} else if (role.equalsIgnoreCase(EnumUserType.CUSTOMERADMIN.getUserType())) {
				totalCount = vehicleRepo.vehicleCount(userId);
				list = vehicleRepo.getVehicleCountryAndStatus(userId);
			}
			dashBoardListDto.setRegisteredVehicles(totalCount.intValue());
			if (list != null) {
				Map<String, DashBoardDto> map = new HashMap<String, DashBoardDto>();
				for (Object[] obj : list) {
					String countryCode = (String) obj[0];
					String countryName = (String) obj[1];
					Long count = (Long) obj[2];
					String runningStatus = (String) obj[3];
					if (map.get(countryCode) != null) {
						DashBoardDto list1 = map.get(countryCode);
						if (runningStatus.equals("IDLE") || runningStatus.equals("RUNNING"))
							list1.setActiveVehicles(list1.getActiveVehicles() + count);
						list1.setTotalVehicles(list1.getTotalVehicles() + count);
						map.put(countryCode, list1);
					} else {
						DashBoardDto list1 = new DashBoardDto();
						if (runningStatus.equals("IDLE") || runningStatus.equals("RUNNING"))
							list1.setActiveVehicles(count);
						list1.setTotalVehicles(count);
						list1.setCountryCode(countryCode);
						list1.setCountryName(countryName);
						map.put(countryCode, list1);
					}
				}
				List<DashBoardDto> dashBoardDto = new ArrayList<DashBoardDto>();
				for (String dto : map.keySet()) {
					dashBoardDto.add(map.get(dto));
				}
				if (dashBoardDto != null && dashBoardDto.size() > 0) {
					dashBoardListDto.setDashBoard(dashBoardDto);
					dashBoardListDto.setStatus(HttpServletResponse.SC_OK);
					dashBoardListDto.setMessage(DicvConstants.DATA_FOUND_MSG);
				} else {
					dashBoardListDto.setStatus(HttpServletResponse.SC_OK);
					dashBoardListDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				}
			} else {
				dashBoardListDto.setStatus(HttpServletResponse.SC_OK);
				dashBoardListDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			}

		} catch (Exception ex) {
			LOGGER.error("Error in Getting Dashboard ", ex);
			dashBoardListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			dashBoardListDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);

		}
		return dashBoardListDto;

	}

	public VehicleDashboardListDto vehicleDashboardReport(VehicleUtilizationInputDto vehicleUtilizationInputDto)
			throws DataNotFoundException, ServerException {
		VehicleDashboardListDto vehicleDashboardList = new VehicleDashboardListDto();
		try {
			DicvUser user = userService.getUser(vehicleUtilizationInputDto.getUserId(), "vehicleDashboardReport");
			String role = user.getUserType().getUserType();

			List<Integer> listOfVehicles = getVehicleIdsByUserRoleAndUserId(vehicleUtilizationInputDto.getUserId(),
					role);

			List<VehicleDashboardResponseDto> vehicleUtilizationResponse = vehicleUtilRepo.getAvgUtilization(
					listOfVehicles, vehicleUtilizationInputDto.getFromDate(), vehicleUtilizationInputDto.getToDate(),
					new PageRequest(0, 10));

			if (vehicleUtilizationResponse == null || vehicleUtilizationResponse.isEmpty()) {
				vehicleDashboardList.setStatus(HttpServletResponse.SC_NO_CONTENT);
				vehicleDashboardList.setMessage("Vehicle Highest Utilization Data Not Availble");
				return vehicleDashboardList;
			}

			List<VehicleDashboardResponseDto> vehicleDashBoardResponseDto = new ArrayList<>();

			listOfVehicles = new ArrayList<Integer>();
			for (VehicleDashboardResponseDto vehId : vehicleUtilizationResponse) {
				listOfVehicles.add(vehId.getVehicleId());
			}

			List<Vehicle> vehList = getVehicleByVehicleIds(listOfVehicles);

			getVehicleAvgUtilization(vehicleUtilizationInputDto, role, vehicleUtilizationResponse,
					vehicleDashBoardResponseDto, vehList);

			if (vehicleDashBoardResponseDto != null && vehicleDashBoardResponseDto.size() > 0) {
				vehicleDashboardList.setVehicleDashboardReport(vehicleDashBoardResponseDto);
				vehicleDashboardList.setStatus(HttpServletResponse.SC_OK);
				vehicleDashboardList.setMessage(DicvConstants.DATA_FOUND_MSG);
				return vehicleDashboardList;
			} else {
				vehicleDashboardList.setStatus(HttpServletResponse.SC_NO_CONTENT);
				vehicleDashboardList.setMessage("Vehicle Highest Utilization Data Not Availble");
				return vehicleDashboardList;
			}

		} catch (Exception e) {
			LOGGER.error("Error in Getting vehicleDashboardReport ", e);
			vehicleDashboardList.setStatus(HttpServletResponse.SC_NO_CONTENT);
			vehicleDashboardList.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return vehicleDashboardList;
		}
	}

	public List<Vehicle> getVehicleByVehicleIds(List<Integer> listOfVehicles) {
		List<Vehicle> vehList = vehicleRepo.getByVehicleIds(listOfVehicles);
		return vehList;
	}

	private List<Integer> getVehicleIdsByUserRoleAndUserId(Integer userId, String role) {
		List<Integer> listOfVehicles = null;
		if (role.equalsIgnoreCase(EnumUserType.ROOTADMIN.getUserType())) {
			listOfVehicles = vehicleRepo.getVehicleIds();
		} else if (role.equalsIgnoreCase(EnumUserType.CUSTOMERADMIN.getUserType())) {
			listOfVehicles = vehicleRepo.getVehicleIds(userId);
		}
		return listOfVehicles;
	}

	private void getVehicleAvgUtilization(VehicleUtilizationInputDto vehicleUtilizationInputDto, String role,
			List<VehicleDashboardResponseDto> vehicleUtilizationResponse,
			List<VehicleDashboardResponseDto> vehicleDashBoardResponseDto, List<Vehicle> vehList) {
		VehicleDashboardResponseDto vehicleDashBoardDto;
		Vehicle vehicle;
		Map<Integer, Vehicle> map = getMapByVehicleList(vehList);

		for (VehicleDashboardResponseDto vehUtil : vehicleUtilizationResponse) {
			vehicleDashBoardDto = new VehicleDashboardResponseDto();
			vehicleDashBoardDto.setVehicleId(vehUtil.getVehicleId());
			vehicle = map.get(vehUtil.getVehicleId());
			if (vehicle == null)
				continue;
			if (vehicle.getDicvType() != null)
				vehicleDashBoardDto.setVehicleName(vehicle.getDicvType().getTypeName());
			if (vehicle != null)
				vehicleDashBoardDto.setRegistrationId(vehicle.getRegistrationId());
			if (vehicle.getDicvCountry() != null) {
				vehicleDashBoardDto.setCountryName(vehicle.getDicvCountry().getCountryName());
				vehicleDashBoardDto.setCountryCode(vehicle.getDicvCountry().getCountryCode());
			}

			if (role.equals(EnumUserType.ROOTADMIN.getUserType())) {

				if (vehicle.getRootAdminGroup() != null)
					vehicleDashBoardDto.setGroup(vehicle.getRootAdminGroup().getGroupName());
				if (vehicle.getDicvUser() != null
						&& vehicle.getDicvUser().getUserId() != vehicleUtilizationInputDto.getUserId())
					vehicleDashBoardDto.setCustomerName(vehicle.getDicvUser().getUserName());

			} else {
				if (vehicle.getDicvGroup() != null)
					vehicleDashBoardDto.setGroup(vehicle.getDicvGroup().getGroupName());
			}
			if (vehUtil.getVehUtilization() != null) {
				vehicleDashBoardDto.setVehUtilization(Math.round(vehUtil.getVehUtilization() * 100d) / 100d);
			}
			vehicleDashBoardResponseDto.add(vehicleDashBoardDto);
		}
	}

	private Map<Integer, Vehicle> getMapByVehicleList(List<Vehicle> vehList) {
		Map<Integer, Vehicle> map = vehList.stream()
				.collect(Collectors.toMap(Vehicle::getVehicleId, Function.identity()));
		return map;
	}

	public OwnerDtlsDto getAllVehiclesList(Integer userId, String keyword, Integer page, Integer rowPerPage) {

		OwnerDtlsDto ownerDto = new OwnerDtlsDto();

		try {
			Long count = 0l;
			PageRequest pageable = DicvUtil.getPageable(page, rowPerPage);
			DicvUser dicvUser = userService.getUser(userId, "getAllVehiclesList");

			String loggedInUserRole = dicvUser.getUserType().getUserType();

			if (loggedInUserRole.equals(EnumUserType.ROOTADMIN.getUserType())) {

				if (keyword != null && keyword.length() > 0) {
					count = vehicleRepo.vehicleCount(keyword);
				} else {
					count = vehicleRepo.vehicleCount();
				}
			} else if (loggedInUserRole.equals(EnumUserType.CUSTOMERADMIN.getUserType())) {

				if (keyword != null && keyword.length() > 0) {
					count = vehicleRepo.vehicleCount(userId, keyword);
				} else {
					count = vehicleRepo.vehicleCount(userId);
				}
			}

			if (count > 0) {
				if ((count > ((page - 1) * rowPerPage))) {
					ownerDto.setHasNextPage(true);
					ownerDto.setTotalCount(count);
				}
			} else {
				ownerDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				ownerDto.setMessage("No vehilcles registered under user");
				return ownerDto;
			}
			List<VehicleDtlsDto> dtlsDtos = new ArrayList<VehicleDtlsDto>();
			List<Vehicle> vehicles = new ArrayList<Vehicle>();
			if (loggedInUserRole.equals(EnumUserType.ROOTADMIN.getUserType())) {

				if (keyword != null && keyword.length() > 0) {
					vehicles = vehicleRepo.getVehicleList(keyword, pageable);
				} else {
					vehicles = vehicleRepo.getVehicleList(pageable);
				}

			} else if (loggedInUserRole.equals(EnumUserType.CUSTOMERADMIN.getUserType())) {

				if (keyword != null && keyword.length() > 0) {
					vehicles = vehicleRepo.getVehicleList(userId, keyword, pageable);
				} else {
					vehicles = vehicleRepo.getVehicleList(userId, pageable);
				}

			}
			if (vehicles == null) {
				ownerDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				ownerDto.setMessage("No vehilcles registered under user");
				return ownerDto;
			}
			ownerDto.setAddress(dicvUser.getAddressLine1());
			ownerDto.setEmail(dicvUser.getEmail());
			ownerDto.setMobileNum(dicvUser.getMobile());
			ownerDto.setName(dicvUser.getFirstName());
			ownerDto.setOwnerId(dicvUser.getCreatedBy());
			ownerDto.setUserId(dicvUser.getUserId());
			VehicleDtlsDto vehicleDtlsDto = new VehicleDtlsDto();
			for (Vehicle veh : vehicles) {
				vehicleDtlsDto = setVehicleDetail(veh, true, loggedInUserRole);
				dtlsDtos.add(vehicleDtlsDto);
			}
			ownerDto.setVehicles(dtlsDtos);
			ownerDto.setStatus(HttpServletResponse.SC_OK);
			ownerDto.setMessage(DicvConstants.DATA_FOUND_MSG);

		} catch (Exception e) {
			LOGGER.error("Error in Getting Vehicle List ", e);
			ownerDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			ownerDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}
		return ownerDto;
	}

	public VehicleDtlsDto getVehicleDetail(Integer userId, Integer vehicleId) {
		try {
			Vehicle vehicleEntity = vehicleRepo.findOne(vehicleId);
			if (null == vehicleEntity || vehicleEntity.getIsDeleted() != 0) {
				throw new DataNotFoundException(
						"The vehicle record does not exists, kindly provide valid vehicle number.");
			}
			DicvUser user = userService.getUser(userId, "getVehicleDetail");

			if (!user.getUserType().getUserType().equals(EnumUserType.ROOTADMIN.getUserType())) {

				if (user.getUserId() != vehicleEntity.getDicvUser().getUserId())
					throw new InvalidValueException("The vehicle does not belong to the user.");
			}
			VehicleDtlsDto vehicleDtls = setVehicleDetail(vehicleEntity, true, user.getUserType().getUserType());

			return vehicleDtls;
		} catch (Exception e) {
			LOGGER.error("Error in Getting Vehicle Details ", e);
			throw new ServerException(DicvConstants.SERVER_EXCEPTION_MSG);
		}

	}

	public VehicleInfoListDto getActiveVehicles(Integer userId) {
		VehicleInfoListDto vehicleInfoListDto = new VehicleInfoListDto();
		DicvUser user = userService.getUser(userId, "getActiveVehicles");
		List<VehicleInfoDto> infoList = null;
		if (user.getUserType().getUserType().equals(EnumUserType.ROOTADMIN.getUserType())) {
			infoList = vehicleRepo.getActiveVehicles();
		} else if (user.getUserType().getUserType().equals(EnumUserType.CUSTOMERADMIN.getUserType())) {
			infoList = vehicleRepo.getActiveVehicles(userId);
		}
		if (infoList != null && !infoList.isEmpty()) {
			vehicleInfoListDto.setVehicles(infoList);
			vehicleInfoListDto.setStatus(HttpServletResponse.SC_OK);
			vehicleInfoListDto.setMessage(DicvConstants.DATA_FOUND_MSG);
			return vehicleInfoListDto;
		} else {
			vehicleInfoListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			vehicleInfoListDto.setMessage("Vehicle Not Available");
		}
		return vehicleInfoListDto;
	}

	public VehicleUtilizationListDto vehicleUtilizationReport(VehicleUtilizationInputDto vehicleUtilizationInputDto) {
		VehicleUtilizationListDto vehicleUtilizationList = new VehicleUtilizationListDto();
		try {
			List<Integer> listOfIntVehicles = new ArrayList<Integer>();

			listOfIntVehicles = new ArrayList<Integer>(vehicleUtilizationInputDto.getVehicleIds());

			PageRequest pageable = new PageRequest(vehicleUtilizationInputDto.getStartRow(),
					(vehicleUtilizationInputDto.getEndRow() - vehicleUtilizationInputDto.getStartRow()) + 1);

			List<VehicleUtilizationResponseDto> vehicleUtilResp = vehicleUtilRepo.getCalucaltedVehicleUtilization(
					listOfIntVehicles, vehicleUtilizationInputDto.getFromDate(), vehicleUtilizationInputDto.getToDate(),
					pageable);

			if (vehicleUtilResp == null || vehicleUtilResp.isEmpty()) {
				vehicleUtilizationList.setStatus(HttpServletResponse.SC_NO_CONTENT);
				vehicleUtilizationList.setMessage("Truck Utilization Not Available for Selected Date");
				return vehicleUtilizationList;
			}
			listOfIntVehicles = new ArrayList<Integer>();
			for (VehicleUtilizationResponseDto vehUtil : vehicleUtilResp) {
				listOfIntVehicles.add(vehUtil.getVehicleId());
			}
			List<VehicleUtilizationResponseDto> vehicleUtilizationResponse = new ArrayList<>();
			List<Vehicle> vehList = vehicleRepo.getByVehicleIds(listOfIntVehicles);
			Map<Integer, Vehicle> map = getMapByVehicleList(vehList);
			calcualteUtilization(vehicleUtilizationInputDto, vehicleUtilResp, vehicleUtilizationResponse, map);

			if (vehicleUtilizationResponse != null && !vehicleUtilizationResponse.isEmpty()) {
				vehicleUtilizationList.setVehicleUtilizationReport(vehicleUtilizationResponse);
				vehicleUtilizationList.setStatus(HttpServletResponse.SC_OK);
				vehicleUtilizationList.setMessage(DicvConstants.DATA_FOUND_MSG);
			} else {
				vehicleUtilizationList.setStatus(HttpServletResponse.SC_NO_CONTENT);
				vehicleUtilizationList.setMessage("Truck Utilization Not Available for Selected Date");
			}
		} catch (Exception e) {
			LOGGER.error("Error in Vehicle Utilization ", e);
			vehicleUtilizationList.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			vehicleUtilizationList.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}
		return vehicleUtilizationList;

	}

	private void calcualteUtilization(VehicleUtilizationInputDto vehicleUtilizationInputDto,
			List<VehicleUtilizationResponseDto> vehicleUtilResp,
			List<VehicleUtilizationResponseDto> vehicleUtilizationResponse, Map<Integer, Vehicle> map) {
		VehicleUtilizationResponseDto vehicleUtilDto;
		Vehicle vehicle;
		Double averageSpeed;
		for (VehicleUtilizationResponseDto vehUtil : vehicleUtilResp) {
			vehicleUtilDto = new VehicleUtilizationResponseDto();
			vehicleUtilDto.setVehicleId(vehUtil.getVehicleId());
			vehicle = map.get(vehUtil.getVehicleId());
			averageSpeed = 0d;
			if (vehicle == null)
				continue;
			if (vehicle.getDicvType() != null)
				vehicleUtilDto.setVehicleType(vehicle.getDicvType().getTypeName());
			if (vehicle.getPhoto() != null && vehicle.getPhoto().getImage() != null)
				vehicleUtilDto.setVehicleImage(new String(Base64.encodeBase64(vehicle.getPhoto().getImage())));
			if (vehicle != null)
				vehicleUtilDto.setRegistrationId(vehicle.getRegistrationId());
			vehicleUtilDto.setVehicleDescription(vehicle.getDescription());
			if (vehicle.getDicvCountry() != null) {
				vehicleUtilDto.setCountryName(vehicle.getDicvCountry().getCountryName());
				vehicleUtilDto.setCountryCode(vehicle.getDicvCountry().getCountryCode());
			}
			if (vehicle.getDicvUser() != null)
				vehicleUtilDto.setUserName(vehicle.getDicvUser().getUserName());

			vehicleUtilDto.setMaxSpeed(vehUtil.getMaxSpeed());
			vehicleUtilDto.setTotalDistance(vehUtil.getTotalDistance());
			vehicleUtilDto.setTotalDrivingTime(vehUtil.getTotalDrivingTime());
			vehicleUtilDto.setUpTime(vehUtil.getUpTime());
			vehicleUtilDto
					.setDate((new SimpleDateFormat("yyyy-MM-dd").format(vehicleUtilizationInputDto.getFromDate())));
			vehicleUtilDto.setAverageSpeed(0d);
			Double util = 0d;
			if (vehUtil.getVehUtilization() != null && vehUtil.getVehUtilization() > 0)
				util = Math.round(vehUtil.getVehUtilization() * 100D) / 100D;
			vehicleUtilDto.setVehUtilization(util);
			if (vehUtil.getTotalDrivingTime() != null && vehUtil.getTotalDistance() != null
					&& vehUtil.getTotalDrivingTime() > 0 && vehUtil.getTotalDistance() > 0) {
				averageSpeed = (vehUtil.getTotalDistance() / (vehUtil.getTotalDrivingTime().doubleValue() / 3600d));
				averageSpeed = Math.round(averageSpeed * 100D) / 100D;
				vehicleUtilDto.setAverageSpeed(averageSpeed);
			}
			vehicleUtilizationResponse.add(vehicleUtilDto);
		}
	}

	public VehicleSpeedListDto vehicleUtilizationGraphReport(VehicleUtilizationInputDto vehicleUtilizationInputDto) {
		VehicleSpeedListDto vehicleSpeedListDto = new VehicleSpeedListDto();
		try {

			List<VehicleStatusGraphReportDto> graphReport = null;
			Vehicle veh = null;
			if (vehicleUtilizationInputDto.getVehicleIds() != null
					&& vehicleUtilizationInputDto.getVehicleIds().get(0) != null)
				veh = vehicleRepo.findOne(vehicleUtilizationInputDto.getVehicleIds().get(0));

			if (veh.getIsDeleted() == 0 && veh.getGpsImei() != null && veh.getGpsImei().getGpsImei() != null
					&& vehicleUtilizationInputDto.getFromDate().equals(vehicleUtilizationInputDto.getToDate())) {

				graphReport = gpsRepo.getVehicleSpeedList(veh.getGpsImei().getGpsImei(),
						vehicleUtilizationInputDto.getFromDate(), vehicleUtilizationInputDto.getToDate());
				if (graphReport != null && !graphReport.isEmpty()) {
					vehicleSpeedListDto.setGraphReport(graphReport);
					vehicleSpeedListDto.setStatus(HttpServletResponse.SC_OK);
					vehicleSpeedListDto.setMessage(DicvConstants.DATA_FOUND_MSG);
					return vehicleSpeedListDto;
				}
			}
			vehicleSpeedListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			vehicleSpeedListDto.setMessage("Truck Speed Report Not Availble for Selected Date");
			return vehicleSpeedListDto;
		} catch (Exception e) {
			LOGGER.error("Error in Vehicle Speed Report" + e.getMessage());
			vehicleSpeedListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			vehicleSpeedListDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return vehicleSpeedListDto;
		}

	}

	public Long vehicleCountBycompanyId(Integer companyId) {

		return vehicleRepo.vehicleCountBycompanyId(companyId);

	}

	public VehicleInfoListDto getVehicleWithGroupInfo(Integer userId) {
		VehicleInfoListDto vehicleGroupDto = new VehicleInfoListDto();
		DicvUser user = userService.getUser(userId, "getVehicleWithGroupInfo");
		List<Vehicle> vehicleList = null;
		List<VehicleInfoDto> vehicles = new ArrayList<VehicleInfoDto>();
		VehicleInfoDto vehicleInfoDto = null;
		if (user.getUserType().getUserType().equals(EnumUserType.ROOTADMIN.getUserType())) {
			vehicleList = vehicleRepo.getAllVehicleList();
			if (vehicleList != null && !vehicleList.isEmpty()) {
				for (Vehicle veh : vehicleList) {
					vehicleInfoDto = new VehicleInfoDto();
					if (veh.getRootAdminGroup() != null)
						vehicleInfoDto.setGroupId(veh.getRootAdminGroup().getGroupId());
					vehicleInfoDto.setRegistrationId(veh.getRegistrationId());
					vehicleInfoDto.setVehicleId(veh.getVehicleId());
					vehicles.add(vehicleInfoDto);
				}
			}

		} else if (user.getUserType().getUserType().equals(EnumUserType.CUSTOMERADMIN.getUserType())) {
			vehicleList = vehicleRepo.getAllVehicleList(userId);
			if (vehicleList != null && !vehicleList.isEmpty()) {
				for (Vehicle veh : vehicleList) {
					vehicleInfoDto = new VehicleInfoDto();
					if (veh.getDicvGroup() != null)
						vehicleInfoDto.setGroupId(veh.getDicvGroup().getGroupId());
					vehicleInfoDto.setRegistrationId(veh.getRegistrationId());
					vehicleInfoDto.setVehicleId(veh.getVehicleId());
					vehicles.add(vehicleInfoDto);
				}
			}
		}
		if (vehicles != null && !vehicles.isEmpty()) {
			vehicleGroupDto.setVehicles(vehicles);
			vehicleGroupDto.setStatus(HttpServletResponse.SC_OK);
			vehicleGroupDto.setMessage(DicvConstants.DATA_FOUND_MSG);
			return vehicleGroupDto;
		} else {
			vehicleGroupDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			vehicleGroupDto.setMessage("Vehicle Not Available");
		}
		return vehicleGroupDto;
	}

	public Long vehicleCountByGroupId(Integer groupId) {
		return vehicleRepo.getVehicleCountByGroupId(groupId);
	}

	public Long getVehicleCountByRooTAdminGroupId(Integer groupId) {
		return vehicleRepo.getVehicleCountByRooTAdminGroupId(groupId);
	}

	public Long getVehicleCountByCategory(Integer category) {
		return vehicleRepo.getVehicleCountByCategory(category);
	}

	public Long getVehicleCountByDicvType(Integer typeId) {
		return vehicleRepo.getVehicleCountByDicvType(typeId);
	}

}
