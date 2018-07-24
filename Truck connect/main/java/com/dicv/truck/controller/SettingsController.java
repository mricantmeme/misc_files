package com.dicv.truck.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
import com.dicv.truck.service.SettingService;

@RestController
@RequestMapping("/onlinefleetmngmt/dicv")
public class SettingsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SettingsController.class);

	@Autowired
	private SettingService settingservice;

	@PostMapping("/alert/configuration")
	public @ResponseBody StatusMessageDto configureAlert(@RequestBody AlertSettingsDto alertSettingsDto) {
		return settingservice.configureAlert(alertSettingsDto);

	}

	@GetMapping("/alert/configuration")
	public @ResponseBody AlertSettingsDto getAlertConfiguration(@RequestParam Integer userId) {
		return settingservice.getAlertConfiguration(userId);
	}

	@GetMapping("/getUserAlertPreference")
	public @ResponseBody AlertPreferenceDto getUserAlertPreference(@RequestParam Integer userId) {
		return settingservice.getUserAlertPreference(userId);
	}

	@PostMapping("/setUserAlertPreference")
	public @ResponseBody StatusMessageDto setUserAlertPreference(@RequestBody AlertPreferenceDto alertPreferenceDto) {
		LOGGER.info("Update Alert Preference :: " + alertPreferenceDto);
		return settingservice.setUserAlertPreference(alertPreferenceDto);

	}

	@GetMapping("/kpi/scalingFactor")
	public @ResponseBody KPIScalingFactorDto getKPIScalingFactor(@RequestParam Integer userId) {
		return settingservice.getKPIScalingFactor(userId);
	}

	@PostMapping("/kpi/scalingFactor")
	public @ResponseBody StatusMessageDto updateKPIScalingFactor(
			@RequestBody() KPIScalingFactorDto kPIScalingFactorDto) {
		return settingservice.updateKPIScalingFactor(kPIScalingFactorDto);

	}

	@PostMapping("/manageDicvCategory")
	public @ResponseBody StatusMessageDto manageDicvCategory(@RequestBody CategoryDtlsDto categoryDto) {
		return settingservice.manageDicvCategory(categoryDto);

	}

	@GetMapping("/getProfitability")
	public @ResponseBody ProfitabilityChartDto manageDicvCategory(@RequestParam Integer userId) {
		return settingservice.getProfitability(ifReadOnlyUserChangeToRootAdmin(userId));

	}

	@PostMapping("/manageDicvType")
	public @ResponseBody StatusMessageDto manageDicvType(@RequestBody TypeDto typeDto) {
		return settingservice.manageDicvType(typeDto);

	}

	@PostMapping("/manageDicvRegions")
	public @ResponseBody StatusMessageDto manageDicvRegions(@RequestBody RegionDto regionDto) {
		return settingservice.manageDicvRegions(regionDto);

	}

	@PostMapping("/manageDicvGroups")
	public @ResponseBody StatusMessageDto manageDicvGroups(@RequestBody GroupDto groupDto) {
		return settingservice.manageDicvGroups(groupDto);

	}

	@PostMapping("/manageProfitability")
	public @ResponseBody StatusMessageDto manageProfitability(@RequestBody ProfitabilityChartDto profitability) {
		return settingservice.manageProfitability(profitability);

	}

	@PostMapping("/createLoadCategory")
	public @ResponseBody StatusMessageDto createLoadCategory(@RequestBody LoadCategoryDtlsDto loadCategoryDto) {
		return settingservice.createLoadCategory(loadCategoryDto);

	}

	@PutMapping("/updateLoadCategory")
	public @ResponseBody StatusMessageDto updateLoadCategory(@RequestBody LoadCategoryDtlsDto loadCategory) {
		return settingservice.updateLoadCategory(loadCategory);

	}

	@GetMapping("/cloneAdminSettings")
	public @ResponseBody StatusMessageDto cloneAdminSettings(@RequestParam Integer rootAdminId,
			@RequestParam Integer customerId) {

		return settingservice.cloneAdminSettings(rootAdminId, customerId);

	}

	@PostMapping("/manageCompany")
	public @ResponseBody StatusMessageDto manageCompany(@RequestBody CompanyDto companyDto) {
		return settingservice.manageCompany(companyDto);

	}

	private Integer ifReadOnlyUserChangeToRootAdmin(Integer userId) {
		return (userId != null && userId.intValue() == 3) ? 2 : userId;
	}

}
