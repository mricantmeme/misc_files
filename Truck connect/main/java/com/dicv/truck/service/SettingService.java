package com.dicv.truck.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicv.truck.dto.AlertPreferenceDto;
import com.dicv.truck.dto.AlertSettingsDto;
import com.dicv.truck.dto.CategoryDtlsDto;
import com.dicv.truck.dto.CompanyDto;
import com.dicv.truck.dto.GroupDto;
import com.dicv.truck.dto.KPIScalingFactorDto;
import com.dicv.truck.dto.LoadCategoryDtlsDto;
import com.dicv.truck.dto.ProfitabilityChartDto;
import com.dicv.truck.dto.RegionDto;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.dto.TypeDto;
import com.dicv.truck.model.AlertPreference;
import com.dicv.truck.model.AlertSettings;
import com.dicv.truck.model.DicvCategory;
import com.dicv.truck.model.DicvCompany;
import com.dicv.truck.model.DicvGeoFenceType;
import com.dicv.truck.model.DicvGroup;
import com.dicv.truck.model.DicvRegion;
import com.dicv.truck.model.DicvType;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.KPIScalingFactor;
import com.dicv.truck.model.LoadCategory;
import com.dicv.truck.model.ProfitabilityChart;
import com.dicv.truck.repo.AlertPreferenceRepo;
import com.dicv.truck.repo.AlertSettingsRepo;
import com.dicv.truck.repo.DicvCategoryRepo;
import com.dicv.truck.repo.DicvCompanyRepo;
import com.dicv.truck.repo.DicvGeoFenceTypeRepo;
import com.dicv.truck.repo.DicvRegionRepo;
import com.dicv.truck.repo.DicvTypeRepo;
import com.dicv.truck.repo.GroupRepo;
import com.dicv.truck.repo.KPIScalingFactorRepo;
import com.dicv.truck.repo.LoadCategoryRepo;
import com.dicv.truck.repo.ProfitablityChartRepo;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.EnumUserType;

@Service
public class SettingService {

	@Autowired
	private UserService userService;

	@Autowired
	private VehicleServices vehicleService;

	@Autowired
	private AlertSettingsRepo alertSettingsRepo;

	@Autowired
	private AlertPreferenceRepo alertPreferenceRepo;

	@Autowired
	private KPIScalingFactorRepo kpiScalingRepo;

	@Autowired
	private DicvCategoryRepo dicvCategoryRepo;

	@Autowired
	private LoadCategoryRepo loadCategoryRepo;

	@Autowired
	private DicvServices dicvService;

	@Autowired
	private DicvTypeRepo dicvTypeRepo;

	@Autowired
	private ProfitablityChartRepo profitablityChartRepo;

	@Autowired
	private DicvRegionRepo dicvRegionRepo;

	@Autowired
	private GroupRepo groupRepo;

	@Autowired
	private DicvCompanyRepo dicvCompanyRepo;

	@Autowired
	private DicvGeoFenceTypeRepo dicvGeoFenceTypeRepo;

	private static final Logger LOGGER = LoggerFactory.getLogger(SettingService.class);

	@Transactional
	public StatusMessageDto configureAlert(AlertSettingsDto alertSettingsDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			DicvUser authUser = userService.getUser(alertSettingsDto.getUserId(), "ConfigureAlert");
			if (authUser != null) {
				AlertSettings alertSettings = alertSettingsRepo.getAlertSettings(alertSettingsDto.getUserId());
				if (alertSettings == null) {
					alertSettings = saveAlertConfiguration(authUser);
				}
				if (alertSettings != null) {
					alertSettings.setBatteryHealth(alertSettingsDto.getBatteryHealth());
					alertSettings.setFuelDrop(alertSettingsDto.getFuelDrop());
					if (alertSettingsDto.getVehicleIdleTime() != null) {
						alertSettings.setVehicleIdleTime(alertSettingsDto.getVehicleIdleTime() * 60000);
					} else {
						alertSettings.setVehicleIdleTime(180000l);
					}
					alertSettings.setUpdatedTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
					alertSettings.setUpdatedBy(alertSettingsDto.getUserId());
					alertSettingsRepo.save(alertSettings);
					statusMessageDto.setStatus(HttpServletResponse.SC_OK);
					statusMessageDto.setMessage(DicvConstants.SUCCESS_UPDATED);
				} else {
					statusMessageDto.setStatus(HttpServletResponse.SC_OK);
					statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in Updating ALERT SETTINGS :: " + ex);
			return returnInternalError(statusMessageDto);
		}
		return statusMessageDto;
	}

	public AlertSettingsDto getAlertConfiguration(Integer userId) {
		AlertSettingsDto alertSettingsDto = new AlertSettingsDto();

		try {
			DicvUser authUser = userService.getUser(userId, "getAlertConfiguration");
			if (authUser != null) {
				AlertSettings alertSettings = alertSettingsRepo.getAlertSettings(userId);
				if (alertSettings == null) {
					alertSettings = saveAlertConfiguration(authUser);
				}
				if (alertSettings != null) {
					alertSettingsDto.setBatteryHealth(alertSettings.getBatteryHealth());
					alertSettingsDto.setFuelDrop(alertSettings.getFuelDrop());
					alertSettingsDto.setVehicleIdleTime(alertSettings.getVehicleIdleTime() / 60000);
					alertSettingsDto.setStatus(HttpServletResponse.SC_OK);
					alertSettingsDto.setMessage(DicvConstants.DATA_FOUND_MSG);
				}
			} else {
				alertSettingsDto.setStatus(HttpServletResponse.SC_OK);
				alertSettingsDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in Getting ALERT SETTINGS :: " + ex);
			alertSettingsDto.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			alertSettingsDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return alertSettingsDto;
		}

		return alertSettingsDto;
	}

	public AlertSettings saveAlertConfiguration(DicvUser authUser) {
		AlertSettings rootAdminAlertSettings = alertSettingsRepo.getAlertSettings(DicvUtil.getRootAdmin());
		AlertSettings alertSettings = new AlertSettings();
		alertSettings.setDicvUser(authUser);
		alertSettings.setFuelDrop(rootAdminAlertSettings.getFuelDrop());
		alertSettings.setBatteryHealth(rootAdminAlertSettings.getBatteryHealth());
		alertSettings.setVehicleIdleTime(rootAdminAlertSettings.getVehicleIdleTime());
		alertSettings.setUpdatedTime(DicvUtil.getTimestamp());
		alertSettingsRepo.save(alertSettings);
		alertSettings = alertSettingsRepo.getAlertSettings(authUser.getUserId());
		return alertSettings;
	}

	public AlertPreferenceDto getUserAlertPreference(Integer userId) {
		AlertPreferenceDto alertPreferenceDto = new AlertPreferenceDto();
		try {
			DicvUser dicvUser = userService.getUser(userId, "getUserAlertPreference");
			if (dicvUser == null) {
				alertPreferenceDto.setStatus(HttpServletResponse.SC_OK);
				alertPreferenceDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				return alertPreferenceDto;
			}
			List<AlertPreference> alertPreferenceForUser = alertPreferenceRepo.getAlertPreferenceList(userId);
			if (alertPreferenceForUser == null || alertPreferenceForUser.size() == 0) {
				alertPreferenceDto.setStatus(HttpServletResponse.SC_OK);
				alertPreferenceDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			}
			AlertPreference alertPreference = alertPreferenceRepo.getOne(alertPreferenceForUser.get(0).getAlertId());
			alertPreferenceDto.setOverSpeedEmailAlert(alertPreference.getOverSpeedEmailAlert());
			alertPreferenceDto.setGeofenceOutEmailAlert(alertPreference.getGeofenceOutEmailAlert());
			alertPreferenceDto.setGeofenceInEmailAlert(alertPreference.getGeofenceInEmailAlert());
			alertPreferenceDto.setGeofenceOutEmailAlert(alertPreference.getGeofenceOutEmailAlert());
			alertPreferenceDto.setStatus(HttpServletResponse.SC_OK);
			alertPreferenceDto.setMessage(DicvConstants.DATA_FOUND_MSG);

		} catch (Exception e) {
			LOGGER.error("Exception in Getting ALERT Preference :: " + e);
			alertPreferenceDto.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			alertPreferenceDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return alertPreferenceDto;
		}
		return alertPreferenceDto;

	}

	@Transactional
	public StatusMessageDto setUserAlertPreference(AlertPreferenceDto alertPreferenceDto) {
		AlertPreference alertPreference = null;
		List<AlertPreference> alertPreferenceByUser = new ArrayList<AlertPreference>();
		try {
			DicvUser dicvUser = userService.getUser(alertPreferenceDto.getUserId(), "setUserAlertPreference");
			if (dicvUser == null) {
				alertPreferenceDto.setStatus(HttpServletResponse.SC_OK);
				alertPreferenceDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				return alertPreferenceDto;
			}
			alertPreferenceByUser = alertPreferenceRepo.getAlertPreferenceList(alertPreferenceDto.getUserId());
			if (alertPreferenceByUser != null && alertPreferenceByUser.size() > 0) {
				alertPreference = alertPreferenceByUser.get(0);
			} else {
				alertPreference = new AlertPreference();
				alertPreference.setCreatedDate(DicvUtil.getTimestamp());
				alertPreference.setDicvUser(dicvUser);
				alertPreference.setCreatedBy(dicvUser.getUserId());
			}
			alertPreference.setTripStopWebAlert(alertPreferenceDto.getTripStopWebAlert());
			alertPreference.setTripStopSmsAlert(alertPreferenceDto.getTripStopSmsAlert());
			alertPreference.setTripStopEmailAlert(alertPreferenceDto.getTripStopEmailAlert());
			alertPreference.setTripStartWebAlert(alertPreferenceDto.getTripStartWebAlert());
			alertPreference.setTripStartSmsAlert(alertPreferenceDto.getTripStartSmsAlert());
			alertPreference.setTripStartEmailAlert(alertPreferenceDto.getTripStartEmailAlert());
			alertPreference.setPickupWebAlert(alertPreferenceDto.getPickupWebAlert());
			alertPreference.setPickupSmsAlert(alertPreferenceDto.getPickupSmsAlert());
			alertPreference.setPickupEmailAlert(alertPreferenceDto.getPickupEmailAlert());
			alertPreference.setOverSpeedWebAlert(alertPreferenceDto.getOverSpeedWebAlert());
			alertPreference.setOverSpeedSmsAlert(alertPreferenceDto.getOverSpeedSmsAlert());
			alertPreference.setOverSpeedEmailAlert(alertPreferenceDto.getOverSpeedEmailAlert());
			alertPreference.setLowBatteryWebAlert(alertPreferenceDto.getLowBatteryWebAlert());
			alertPreference.setLowBatterySmsAlert(alertPreferenceDto.getLowBatterySmsAlert());
			alertPreference.setLowBatteryEmailAlert(alertPreferenceDto.getLowBatteryEmailAlert());
			alertPreference.setGeofenceOutWebAlert(alertPreferenceDto.getGeofenceOutWebAlert());
			alertPreference.setGeofenceOutSmsAlert(alertPreferenceDto.getGeofenceOutSmsAlert());
			alertPreference.setGeofenceOutEmailAlert(alertPreferenceDto.getGeofenceOutEmailAlert());
			alertPreference.setDeliveryEmailAlert(alertPreferenceDto.getDeliveryEmailAlert());
			alertPreference.setDeliverySmsAlert(alertPreferenceDto.getDeliverySmsAlert());
			alertPreference.setDeliveryWebAlert(alertPreferenceDto.getDeliveryWebAlert());
			alertPreference.setGeofenceInEmailAlert(alertPreferenceDto.getGeofenceInEmailAlert());
			alertPreference.setGeofenceInSmsAlert(alertPreferenceDto.getGeofenceInSmsAlert());
			alertPreference.setGeofenceInWebAlert(alertPreferenceDto.getGeofenceInWebAlert());
			alertPreference.setGeofenceOutEmailAlert(alertPreferenceDto.getGeofenceOutEmailAlert());
			alertPreference.setGeofenceOutSmsAlert(alertPreferenceDto.getGeofenceOutSmsAlert());
			alertPreference.setGeofenceOutWebAlert(alertPreferenceDto.getGeofenceOutWebAlert());
			alertPreference.setUpdatedBy(dicvUser.getUserId());
			alertPreference.setIsDeleted(0);
			alertPreference.setModifiedDate(DicvUtil.getTimestamp());
			alertPreferenceDto.setStatus(HttpServletResponse.SC_OK);
			alertPreferenceDto.setMessage(DicvConstants.SUCCESS_UPDATED);
			alertPreferenceDto.setIdentifier(alertPreference.getAlertId());
			alertPreference = alertPreferenceRepo.save(alertPreference);
		} catch (Exception e) {
			LOGGER.error("Exception in Update ALERT Preference :: " + e);
			alertPreferenceDto.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			alertPreferenceDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return alertPreferenceDto;
		}
		return alertPreferenceDto;

	}

	@Transactional
	public KPIScalingFactorDto getKPIScalingFactor(Integer userId) {
		KPIScalingFactorDto kPIScalingFactorDto = new KPIScalingFactorDto();
		try {
			DicvUser dicvUser = userService.getUser(userId, "Get KPI");
			if (dicvUser == null) {
				kPIScalingFactorDto.setStatus(HttpServletResponse.SC_OK);
				kPIScalingFactorDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				return kPIScalingFactorDto;
			}
			if (dicvUser.getUserType().getUserType().equals(EnumUserType.ROOTADMIN.getUserType())) {
				List<KPIScalingFactor> kpiScalingFactorList = kpiScalingRepo.getKPIScalingFactor();
				KPIScalingFactor kpiScalingFactor = kpiScalingFactorList.get(0);
				kPIScalingFactorDto.setSpeeding(kpiScalingFactor.getSpeeding());
				kPIScalingFactorDto.setIdling(kpiScalingFactor.getIdling());
				kPIScalingFactorDto.setLastUpdatedTime(kpiScalingFactor.getLastUpdatedTime());
				kPIScalingFactorDto.setHarshAcceleration(kpiScalingFactor.getHarshAcceleration());
				kPIScalingFactorDto.setHarshBraking(kpiScalingFactor.getHarshBraking());
				kPIScalingFactorDto.setHarshCornering(kpiScalingFactor.getHarshCornering());
				kPIScalingFactorDto.setStatus(HttpServletResponse.SC_OK);
				kPIScalingFactorDto.setMessage(DicvConstants.DATA_FOUND_MSG);
			} else {
				kPIScalingFactorDto.setStatus(HttpServletResponse.SC_OK);
				kPIScalingFactorDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in Getting Scaling Factor :: " + ex);
			kPIScalingFactorDto.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			kPIScalingFactorDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return kPIScalingFactorDto;
		}
		return kPIScalingFactorDto;
	}

	@Transactional
	public StatusMessageDto updateKPIScalingFactor(KPIScalingFactorDto kPIScalingFactorDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			DicvUser dicvUser = userService.getUser(kPIScalingFactorDto.getUserId(), "Update KPI");
			if (dicvUser == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				return kPIScalingFactorDto;
			}
			if (dicvUser.getUserType().getUserType().equals(EnumUserType.ROOTADMIN.getUserType())) {
				List<KPIScalingFactor> kpiScalingFactorList = kpiScalingRepo.getKPIScalingFactor();
				KPIScalingFactor kpiScalingFactor = kpiScalingFactorList.get(0);
				if (kpiScalingFactor != null) {
					kpiScalingFactor.setSpeeding(kPIScalingFactorDto.getSpeeding());
					kpiScalingFactor.setIdling(kPIScalingFactorDto.getIdling());
					kpiScalingFactor.setLastUpdatedTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
					kpiScalingFactor.setHarshAcceleration(kPIScalingFactorDto.getHarshAcceleration());
					kpiScalingFactor.setHarshBraking(kPIScalingFactorDto.getHarshBraking());
					kpiScalingFactor.setHarshCornering(kPIScalingFactorDto.getHarshCornering());
					kpiScalingFactor.setUpdatedBy(kPIScalingFactorDto.getUserId());
					kpiScalingRepo.save(kpiScalingFactor);
					LOGGER.info("KPI Updated Successfully :: " + kpiScalingFactor);
					statusMessageDto.setStatus(HttpServletResponse.SC_OK);
					statusMessageDto.setMessage(DicvConstants.SUCCESS_UPDATED);
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in Update Scaling Factor :: " + ex);
			return returnInternalError(statusMessageDto);
		}
		return statusMessageDto;
	}

	@Transactional
	public StatusMessageDto manageDicvCategory(CategoryDtlsDto categoryDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			DicvUser dicvUser = userService.getUser(categoryDto.getUserId(), "Manage Category");
			if (dicvUser == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				return statusMessageDto;
			}
			DicvCategory dicvCategory = new DicvCategory();
			if (categoryDto.getCategoryId() == null) {
				dicvCategory = new DicvCategory();
				dicvCategory.setCreatedOn(DicvUtil.getTimestamp());
				dicvCategory.setCreatedByUser(dicvUser);
				dicvCategory.setIsDeleted(0);
				statusMessageDto.setMessage(DicvConstants.SUCCESS_CREATED);
			} else {
				dicvCategory = dicvService.getDicvCategoryId(categoryDto.getCategoryId());
				if (dicvCategory == null) {
					statusMessageDto.setStatus(HttpServletResponse.SC_OK);
					statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
					return statusMessageDto;
				}
			}
			if (categoryDto.getIsDelete() != 1) {
				if (categoryDto.getCategoryName() != null) {
					dicvCategory.setCategoryName(categoryDto.getCategoryName());
				}
				dicvCategory.setSubCategoryId(categoryDto.getSubCategoryId());
				dicvCategory.setUpdatedOn(DicvUtil.getTimestamp());
				dicvCategory.setUpdatedByUser(dicvUser.getUserId());
				statusMessageDto.setMessage(DicvConstants.SUCCESS_UPDATED);
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
			} else {
				if (vehicleService.getVehicleCountByCategory(categoryDto.getCategoryId()) > 0) {
					statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					statusMessageDto.setMessage("Category Cannot be deleted as Vehicle exist");
					return statusMessageDto;
				}
				dicvCategory.setIsDeleted(1);
				dicvCategory.setUpdatedOn(DicvUtil.getTimestamp());
				dicvCategory.setUpdatedByUser(dicvUser.getUserId());
				statusMessageDto.setMessage(DicvConstants.SUCCESS_DELETED);
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
			}
			dicvCategoryRepo.save(dicvCategory);
		} catch (Exception e) {
			LOGGER.error("Exception in Add/Update Category :: " + e);
			return returnInternalError(statusMessageDto);
		}
		return statusMessageDto;

	}

	public ProfitabilityChartDto getProfitability(Integer userId) {
		ProfitabilityChart profitabilityChart = null;
		ProfitabilityChartDto profitabilityChartDto = new ProfitabilityChartDto();
		try {
			List<ProfitabilityChart> list = profitablityChartRepo.getProfitabilityChart(userId);
			if (list == null || list.isEmpty()) {
				profitabilityChartDto.setStatus(HttpServletResponse.SC_OK);
				profitabilityChartDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				return profitabilityChartDto;
			}
			profitabilityChart = list.get(0);
			profitabilityChartDto = new ProfitabilityChartDto();
			profitabilityChartDto.setProfitabilityChartId(profitabilityChart.getProfitabilityChartId());
			profitabilityChartDto.setUserId(profitabilityChart.getDicvUserCreatedBy().getUserId());
			if (null != profitabilityChart.getDriverCost()) {
				profitabilityChartDto.setDriverCost(profitabilityChart.getDriverCost());
			}
			if (null != profitabilityChart.getFuelCost()) {
				profitabilityChartDto.setFuelCost(profitabilityChart.getFuelCost());
			}
			if (null != profitabilityChart.getOperationalCost()) {
				profitabilityChartDto.setOperationalCost(profitabilityChart.getOperationalCost());
			}
			profitabilityChartDto.setStatus(HttpServletResponse.SC_OK);
			profitabilityChartDto.setMessage(DicvConstants.DATA_FOUND_MSG);
			return profitabilityChartDto;
		} catch (Exception e) {
			LOGGER.error("Exception in Getting Profitability :: " + e);
			profitabilityChartDto.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			profitabilityChartDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return profitabilityChartDto;
		}
	}

	@Transactional
	public StatusMessageDto manageDicvType(TypeDto typeDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			DicvUser dicvUser = userService.getUser(typeDto.getUserId(), "Manage Type");
			if (dicvUser == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				return statusMessageDto;
			}
			if (typeDto.getTypeId() == null) {
				return addDicvType(typeDto, statusMessageDto, dicvUser);
			} else {
				if (typeDto.getIsDelete() == 1) {
					return deleteDicvType(typeDto, statusMessageDto);
				} else {
					return updateDicvType(typeDto, statusMessageDto, dicvUser);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Add/Update Type :: " + e);
			return returnInternalError(statusMessageDto);
		}
	}

	private StatusMessageDto addDicvType(TypeDto typeDto, StatusMessageDto statusMessageDto, DicvUser dicvUser) {
		DicvType dicvType = new DicvType();
		dicvType.setCreatedOn(DicvUtil.getTimestamp());
		dicvType.setCreatedByUser(dicvUser);
		dicvType.setIsDeleted(0);
		statusMessageDto.setMessage(DicvConstants.SUCCESS_CREATED);
		dicvType.setTypeName(typeDto.getTypeName());
		dicvType.setSubTypeId(typeDto.getSubTypeId());
		dicvType.setUpdatedByUser(typeDto.getUserId());
		dicvType.setUpdatedOn(DicvUtil.getTimestamp());
		statusMessageDto.setStatus(HttpServletResponse.SC_OK);
		dicvTypeRepo.save(dicvType);
		return statusMessageDto;
	}

	private StatusMessageDto updateDicvType(TypeDto typeDto, StatusMessageDto statusMessageDto, DicvUser dicvUser) {
		DicvType dicvType = dicvService.getTypeById(typeDto.getTypeId());
		if (dicvType == null) {
			statusMessageDto.setStatus(HttpServletResponse.SC_OK);
			statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			return statusMessageDto;
		}
		statusMessageDto.setMessage(DicvConstants.SUCCESS_UPDATED);
		dicvType.setTypeName(typeDto.getTypeName());
		dicvType.setSubTypeId(typeDto.getSubTypeId());
		dicvType.setUpdatedByUser(typeDto.getUserId());
		dicvType.setUpdatedOn(DicvUtil.getTimestamp());
		statusMessageDto.setStatus(HttpServletResponse.SC_OK);
		dicvTypeRepo.save(dicvType);
		return statusMessageDto;
	}

	private StatusMessageDto deleteDicvType(TypeDto typeDto, StatusMessageDto statusMessageDto) {
		DicvType dicvType = dicvService.getTypeById(typeDto.getTypeId());
		if (dicvType == null) {
			statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			statusMessageDto.setMessage("Type Not Found");
			return statusMessageDto;
		}
		if (vehicleService.getVehicleCountByDicvType(dicvType.getTypeId()) > 0) {
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage("Type Cannot be deleted as Vehicle exist");
			return statusMessageDto;
		}
		dicvType.setIsDeleted(1);
		dicvType.setUpdatedOn(DicvUtil.getTimestamp());
		dicvTypeRepo.save(dicvType);
		statusMessageDto.setStatus(HttpServletResponse.SC_OK);
		statusMessageDto.setMessage(DicvConstants.SUCCESS_DELETED);
		return statusMessageDto;
	}

	@Transactional
	public StatusMessageDto manageDicvRegions(RegionDto regionDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			if (regionDto.getIsDelete()) {
				DicvRegion dicvRegion = dicvService.getDicvRegion(regionDto.getRegionId());
				if (dicvRegion == null) {
					statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
					statusMessageDto.setMessage("Region Not Found");
					return statusMessageDto;
				}
				dicvRegion.setIsDeleted(1);
				dicvRegion.setUpdatedOn(DicvUtil.getTimestamp());
				dicvRegionRepo.save(dicvRegion);
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.SUCCESS_DELETED);
				return statusMessageDto;
			}
			DicvUser dicvUser = userService.getUser(regionDto.getUserId(), "Manage Region");
			if (dicvUser == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				return statusMessageDto;
			}
			if (null == regionDto.getRegionId()) {
				DicvRegion dicvRegion = new DicvRegion();
				if (regionDto.getRegionName() != null && regionDto.getRegionName().trim() != "") {
					dicvRegion.setRegionName(regionDto.getRegionName());
				} else {
					statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
					statusMessageDto.setMessage("Please Enter Proper Region Name");
					return statusMessageDto;
				}
				dicvRegion.setSubRegionId(regionDto.getSubregionId());
				dicvRegion.setCreatedByUser(dicvUser.getUserId());
				dicvRegion.setUpdatedByUser(dicvUser.getUserId());
				dicvRegion.setIsDeleted(0);
				dicvRegion.setCreatedOn(DicvUtil.getTimestamp());
				dicvRegion.setUpdatedOn(DicvUtil.getTimestamp());
				dicvRegionRepo.save(dicvRegion);
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setIdentifier(dicvRegion.getRegionId());
				statusMessageDto.setMessage(DicvConstants.SUCCESS_CREATED);
				return statusMessageDto;
			} else {
				DicvRegion dicvRegion = dicvService.getDicvRegion(regionDto.getRegionId());
				if (dicvRegion == null) {
					statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
					statusMessageDto.setMessage("Region Not Found");
					return statusMessageDto;
				}
				if (regionDto.getRegionName() != null && regionDto.getRegionName().trim() != "") {
					dicvRegion.setRegionName(regionDto.getRegionName());
				} else {
					statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
					statusMessageDto.setMessage("Please Enter Proper Region Name");
					return statusMessageDto;
				}
				dicvRegion.setSubRegionId(regionDto.getSubregionId());
				dicvRegion.setUpdatedByUser(dicvUser.getUserId());
				dicvRegion.setUpdatedOn(DicvUtil.getTimestamp());
				dicvRegionRepo.save(dicvRegion);
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.SUCCESS_UPDATED);
				return statusMessageDto;
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Add/Update Region :: " + e);
			return returnInternalError(statusMessageDto);
		}
	}

	public StatusMessageDto manageDicvGroups(GroupDto groupDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			DicvUser dicvUser = userService.getUser(groupDto.getUserId(), "Manager Group");
			if (dicvUser == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				return statusMessageDto;
			}
			if (null == groupDto.getGroupId()) {
				return addNewGroup(groupDto, statusMessageDto, dicvUser);
			} else if (groupDto.getIsDelete()) {
				return deleteGroup(groupDto, statusMessageDto, dicvUser);
			} else {
				return updateGroup(groupDto, statusMessageDto, dicvUser);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Add/Update Group :: " + e);
			return returnInternalError(statusMessageDto);
		}
	}

	private StatusMessageDto returnInternalError(StatusMessageDto statusMessageDto) {
		statusMessageDto.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		statusMessageDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		return statusMessageDto;
	}

	private StatusMessageDto updateGroup(GroupDto groupDto, StatusMessageDto statusMessageDto, DicvUser dicvUser) {
		DicvGroup dicvGroup = dicvService.getDicvGroup(groupDto.getGroupId());
		if (dicvGroup == null) {
			statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			statusMessageDto.setMessage("Group Not Found");
			return statusMessageDto;
		}

		if (groupDto.getGroupName() != null && groupDto.getGroupName().trim() != "") {
			dicvGroup.setGroupName(groupDto.getGroupName());
		} else {
			statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			statusMessageDto.setMessage("Please Enter Proper Group Name");
			return statusMessageDto;
		}
		dicvGroup.setSubGroupId(groupDto.getSubGroupId());
		dicvGroup.setUpdatedByUser(dicvUser);
		dicvGroup.setUpdatedOn(DicvUtil.getTimestamp());
		groupRepo.save(dicvGroup);
		statusMessageDto.setStatus(HttpServletResponse.SC_OK);
		statusMessageDto.setMessage(DicvConstants.SUCCESS_UPDATED);
		return statusMessageDto;
	}

	private StatusMessageDto deleteGroup(GroupDto groupDto, StatusMessageDto statusMessageDto, DicvUser dicvUser) {
		DicvGroup dicvGroup = dicvService.getDicvGroup(groupDto.getGroupId());
		if (dicvGroup == null) {
			statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			statusMessageDto.setMessage("Group Not Found");
			return statusMessageDto;
		}
		if (!dicvUser.getUserType().getUserType().equals(EnumUserType.ROOTADMIN.getUserType())) {
			if (vehicleService.vehicleCountByGroupId(groupDto.getGroupId()) > 0) {
				statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				statusMessageDto.setMessage("Group Cannot be deleted as Vehicle exist");
				return statusMessageDto;
			}
		} else {
			if (vehicleService.getVehicleCountByRooTAdminGroupId(groupDto.getGroupId()) > 0) {
				statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				statusMessageDto.setMessage("Group Cannot be deleted as Vehicle exist");
				return statusMessageDto;
			}
		}
		if (userService.userCountByGroupId(groupDto.getGroupId()) > 0) {
			statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			statusMessageDto.setMessage("Group Cannot be deleted as User exist");
			return statusMessageDto;
		}
		dicvGroup.setIsDeleted(1);
		dicvGroup.setUpdatedByUser(dicvUser);
		dicvGroup.setUpdatedOn(DicvUtil.getTimestamp());
		groupRepo.save(dicvGroup);
		statusMessageDto.setStatus(HttpServletResponse.SC_OK);
		statusMessageDto.setMessage(DicvConstants.SUCCESS_DELETED);
		return statusMessageDto;
	}

	private StatusMessageDto addNewGroup(GroupDto groupDto, StatusMessageDto statusMessageDto, DicvUser dicvUser) {
		DicvGroup dicvGroup = new DicvGroup();
		if (groupDto.getGroupName() != null && groupDto.getGroupName().trim() != "") {
			dicvGroup.setGroupName(groupDto.getGroupName());
		} else {
			statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			statusMessageDto.setMessage("Please Enter Proper Group Name");
			return statusMessageDto;
		}
		dicvGroup.setSubGroupId(groupDto.getSubGroupId());
		dicvGroup.setCreatedByUser(dicvUser);
		dicvGroup.setUpdatedByUser(dicvUser);
		dicvGroup.setIsDeleted(0);
		dicvGroup.setCreatedOn(DicvUtil.getTimestamp());
		dicvGroup.setUpdatedOn(DicvUtil.getTimestamp());
		groupRepo.save(dicvGroup);
		statusMessageDto.setStatus(HttpServletResponse.SC_OK);
		statusMessageDto.setIdentifier(dicvGroup.getGroupId());
		statusMessageDto.setMessage(DicvConstants.SUCCESS_CREATED);
		return statusMessageDto;
	}

	public StatusMessageDto manageProfitability(ProfitabilityChartDto profitabilityDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			DicvUser dicvUser = userService.getUser(profitabilityDto.getUserId(), "Manage Profitablity");
			if (dicvUser == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				return statusMessageDto;
			}
			List<ProfitabilityChart> list = profitablityChartRepo.getProfitabilityChart(profitabilityDto.getUserId());
			if (list != null && !list.isEmpty()) {
				ProfitabilityChart profitability = list.get(0);
				if (null != profitability.getDriverCost()) {
					profitability.setDriverCost(profitabilityDto.getDriverCost());
				}
				if (null != profitability.getFuelCost()) {
					profitability.setFuelCost(profitabilityDto.getFuelCost());
				}
				if (null != profitability.getOperationalCost()) {
					profitability.setOperationalCost(profitabilityDto.getOperationalCost());
				}
				profitability.setUpdatedDate(DicvUtil.getTimestamp());
				profitability.setDicvUserUpdatedBy(dicvUser.getUserId());
				profitablityChartRepo.save(profitability);
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.SUCCESS_UPDATED);
				return statusMessageDto;
			} else {
				ProfitabilityChart profitability = new ProfitabilityChart();
				if (null != profitability.getDriverCost()) {
					profitability.setDriverCost(profitabilityDto.getDriverCost());
				}
				if (null != profitability.getFuelCost()) {
					profitability.setFuelCost(profitabilityDto.getFuelCost());
				}
				if (null != profitability.getOperationalCost()) {
					profitability.setOperationalCost(profitabilityDto.getOperationalCost());
				}
				profitability.setCreatedDate(DicvUtil.getTimestamp());
				profitability.setDicvUserCreatedBy(dicvUser);
				profitability.setUpdatedDate(DicvUtil.getTimestamp());
				profitability.setDicvUserUpdatedBy(dicvUser.getUserId());
				profitablityChartRepo.save(profitability);
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.SUCCESS_CREATED);
				return statusMessageDto;
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Add/Update Profitablity :: " + e);
			return returnInternalError(statusMessageDto);
		}
	}

	@Transactional
	public StatusMessageDto updateLoadCategory(LoadCategoryDtlsDto loadCategoryDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			DicvUser dicvUser = userService.getUser(loadCategoryDto.getUserId(), "Upload Load Category");
			if (dicvUser == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				return statusMessageDto;
			}
			LoadCategory loadCat = null;
			loadCat = loadCategoryRepo.findOne(loadCategoryDto.getLoadCategoryId());
			if (loadCat == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				statusMessageDto.setMessage("Requested Load Category Not Available");
				return statusMessageDto;

			} else {
				statusMessageDto.setMessage(DicvConstants.SUCCESS_UPDATED);
			}
			loadCat.setUpdatedDate(DicvUtil.getTimestamp());
			loadCat.setLoadCategoryName(loadCategoryDto.getLoadCategoryName());
			loadCat.setDicvUserUpdatedBy(dicvUser);
			loadCategoryRepo.save(loadCat);
			statusMessageDto.setStatus(HttpServletResponse.SC_OK);
			return statusMessageDto;
		} catch (Exception e) {
			LOGGER.error("Exception in Add/Update Profitablity :: " + e);
			return returnInternalError(statusMessageDto);
		}
	}

	public StatusMessageDto createLoadCategory(LoadCategoryDtlsDto loadCategoryDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			DicvUser dicvUser = userService.getUser(loadCategoryDto.getUserId(), "Create Load Category");
			if (dicvUser == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				return statusMessageDto;
			}
			LoadCategory loadCat = new LoadCategory();
			loadCat.setCreatedDate(DicvUtil.getTimestamp());
			loadCat.setDicvUserCreatedBy(dicvUser);
			loadCat = loadCategoryRepo.save(loadCat);
			loadCat.setUpdatedDate(DicvUtil.getTimestamp());
			loadCat.setLoadCategoryName(loadCategoryDto.getLoadCategoryName());
			loadCat.setDicvUserUpdatedBy(dicvUser);
			loadCategoryRepo.save(loadCat);
			statusMessageDto.setStatus(HttpServletResponse.SC_OK);
			statusMessageDto.setMessage(DicvConstants.SUCCESS_CREATED);
			return statusMessageDto;
		} catch (Exception e) {
			LOGGER.error("Exception in Add/Update Profitablity :: " + e);
			return returnInternalError(statusMessageDto);
		}
	}

	@Transactional
	public StatusMessageDto cloneAdminSettings(Integer rootAdminId, Integer customerId) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {

			DicvUser rootUser = userService.getUser(rootAdminId, "clone service");
			DicvUser customerAdmin = userService.getUser(customerId, "clone service");
			cloneDicvType(rootUser, customerAdmin);
			cloneDicvCategory(rootUser, customerAdmin);
			cloneDicvRegion(rootUser.getUserId(), customerAdmin.getUserId());
			cloneDicvGroup(rootUser, customerAdmin);
			cloneLoadCategory(rootUser, customerAdmin);
			cloneGeoFenceType(rootUser, customerAdmin);

		} catch (Exception ex) {

		}
		return statusMessageDto;
	}

	private void cloneGeoFenceType(DicvUser rootUser, DicvUser customerAdmin) {

		List<DicvGeoFenceType> loadList = dicvGeoFenceTypeRepo.getGeoFenceTypeList(rootUser.getUserId());
		if (loadList != null && !loadList.isEmpty()) {
			DicvGeoFenceType clone = new DicvGeoFenceType();
			for (DicvGeoFenceType type : loadList) {
				clone = new DicvGeoFenceType();
				clone.setIsDeleted(0);
				clone.setCreatedByUser(customerAdmin);
				clone.setCreatedOn(DicvUtil.getTimestamp());
				clone.setIsDeleted(0);
				clone.setTypeName(type.getTypeName());
				clone.setUpdatedByUser(customerAdmin.getUserId());
				clone.setUpdatedOn(DicvUtil.getTimestamp());
				dicvGeoFenceTypeRepo.save(clone);
			}
		}
	}

	private void cloneLoadCategory(DicvUser rootUser, DicvUser customerAdmin) {

		List<LoadCategory> loadList = loadCategoryRepo.getLoadCategoryList(rootUser.getUserId());
		if (loadList != null && !loadList.isEmpty()) {
			LoadCategory clone = new LoadCategory();
			for (LoadCategory type : loadList) {
				clone = new LoadCategory();
				clone.setCreatedDate(DicvUtil.getTimestamp());
				clone.setDicvUserCreatedBy(customerAdmin);
				clone.setDicvUserUpdatedBy(customerAdmin);
				clone.setIsDeleted(0);
				clone.setLoadCategoryName(type.getLoadCategoryName());
				clone.setUpdatedDate(DicvUtil.getTimestamp());
				loadCategoryRepo.save(clone);
			}
		}
	}

	private void cloneDicvType(DicvUser rootUser, DicvUser customerAdmin) {

		List<DicvType> typeList = dicvTypeRepo.getTypeList(rootUser.getUserId());
		if (typeList != null && !typeList.isEmpty()) {
			DicvType clone = new DicvType();
			for (DicvType type : typeList) {
				clone = new DicvType();
				clone.setTypeName(type.getTypeName());
				clone.setCreatedOn(DicvUtil.getTimestamp());
				clone.setSubTypeId(type.getSubTypeId());
				clone.setIsDeleted(type.getIsDeleted());
				clone.setUpdatedByUser(customerAdmin.getUserId());
				clone.setUpdatedOn(DicvUtil.getTimestamp());
				clone.setCreatedByUser(customerAdmin);
				dicvTypeRepo.save(clone);

			}

		}

	}

	private void cloneDicvCategory(DicvUser rootUser, DicvUser customerAdmin) {

		List<DicvCategory> catList = dicvCategoryRepo.getCategoryList(rootUser.getUserId());
		if (catList != null && !catList.isEmpty()) {
			DicvCategory clone = new DicvCategory();
			for (DicvCategory dicvCategory : catList) {
				clone = new DicvCategory();
				clone.setCategoryName(dicvCategory.getCategoryName());
				clone.setSubCategoryId(dicvCategory.getSubCategoryId());
				clone.setCreatedOn(DicvUtil.getTimestamp());
				clone.setIsDeleted(dicvCategory.getIsDeleted());
				clone.setUpdatedByUser(customerAdmin.getUserId());
				clone.setUpdatedOn(DicvUtil.getTimestamp());
				clone.setCreatedByUser(customerAdmin);
				dicvCategoryRepo.save(clone);

			}

		}

	}

	private void cloneDicvRegion(Integer rootUser, Integer customerAdmin) {

		List<DicvRegion> dicvRegionList = dicvRegionRepo.findAllRegions(rootUser);
		if (dicvRegionList != null && !dicvRegionList.isEmpty()) {
			DicvRegion clone = new DicvRegion();
			for (DicvRegion dicvRegion : dicvRegionList) {
				clone = new DicvRegion();
				clone.setRegionName(dicvRegion.getRegionName());
				clone.setSubRegionId(dicvRegion.getSubRegionId());
				clone.setCreatedOn(DicvUtil.getTimestamp());
				clone.setIsDeleted(dicvRegion.getIsDeleted());
				clone.setUpdatedByUser(customerAdmin);
				clone.setUpdatedOn(DicvUtil.getTimestamp());
				clone.setCreatedByUser(customerAdmin);
				dicvRegionRepo.save(clone);

			}

		}

	}

	private void cloneDicvGroup(DicvUser rootUser, DicvUser customerAdmin) {

		List<DicvGroup> dicvGroupList = groupRepo.getDicvGroup(rootUser.getUserId());
		if (dicvGroupList != null && !dicvGroupList.isEmpty()) {
			DicvGroup clone = new DicvGroup();
			for (DicvGroup dicvGroup : dicvGroupList) {
				clone = new DicvGroup();
				clone.setGroupName(dicvGroup.getGroupName());
				clone.setSubGroupId(dicvGroup.getSubGroupId());
				clone.setCreatedOn(DicvUtil.getTimestamp());
				clone.setIsDeleted(dicvGroup.getIsDeleted());
				clone.setUpdatedByUser(customerAdmin);
				clone.setUpdatedOn(DicvUtil.getTimestamp());
				clone.setCreatedByUser(customerAdmin);
				groupRepo.save(clone);

			}

		}

	}

	public StatusMessageDto manageCompany(CompanyDto companyDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			DicvUser dicvUser = userService.getUser(companyDto.getUserId(), "Company ");
			if (dicvUser == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				return statusMessageDto;
			}
			if (null == companyDto.getCompanyId()) {
				DicvCompany company = new DicvCompany();
				if (companyDto.getCompanyName() != null && companyDto.getCompanyName().trim() != "") {
					company.setCompanyName(companyDto.getCompanyName());
				} else {
					statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
					statusMessageDto.setMessage("Please Enter Proper Company Name");
					return statusMessageDto;
				}
				company.setCompanyName(companyDto.getCompanyName());
				company.setCompanyAddress(companyDto.getCompanyAddress());
				company.setCompanyCode(companyDto.getCompanyCode());
				company.setIsDeleted(0);
				dicvCompanyRepo.save(company);
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setIdentifier(company.getCompanyId());
				statusMessageDto.setMessage(DicvConstants.SUCCESS_CREATED);
				return statusMessageDto;
			} else if (companyDto.getIsDelete()) {
				DicvCompany company = dicvCompanyRepo.findOne(companyDto.getCompanyId());
				if (company == null) {
					statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
					statusMessageDto.setMessage("Company Not Found");
					return statusMessageDto;
				}
				// Check User/Vehicle Available for this Company
				if (userService.userCountBycompanyId(companyDto.getCompanyId()) > 0) {
					statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
					statusMessageDto.setMessage("Company Cannot be deleted as User exist");
					return statusMessageDto;
				}
				if (vehicleService.vehicleCountBycompanyId(companyDto.getCompanyId()) > 0) {
					statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
					statusMessageDto.setMessage("Company Cannot be deleted as Vehicle exist");
					return statusMessageDto;
				}
				company.setIsDeleted(1);
				dicvCompanyRepo.save(company);
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.SUCCESS_DELETED);
				return statusMessageDto;
			} else {
				DicvCompany company = dicvCompanyRepo.findOne(companyDto.getCompanyId());
				if (company == null) {
					statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
					statusMessageDto.setMessage("Company Not Found");
					return statusMessageDto;
				}
				company.setCompanyName(companyDto.getCompanyName());
				company.setCompanyAddress(companyDto.getCompanyAddress());
				company.setCompanyCode(companyDto.getCompanyCode());
				dicvCompanyRepo.save(company);
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.SUCCESS_UPDATED);
				return statusMessageDto;
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Add/Update Company :: " + e);
			return returnInternalError(statusMessageDto);
		}

	}
}
