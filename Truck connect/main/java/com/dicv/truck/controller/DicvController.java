package com.dicv.truck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dicv.truck.dto.CategoryListDto;
import com.dicv.truck.dto.CitiesDto;
import com.dicv.truck.dto.CompanyDtoList;
import com.dicv.truck.dto.CountrysDto;
import com.dicv.truck.dto.GroupsDto;
import com.dicv.truck.dto.LoadCategoryListDto;
import com.dicv.truck.dto.RegionListDto;
import com.dicv.truck.dto.TypeListDto;
import com.dicv.truck.exception.ServerException;
import com.dicv.truck.service.DicvServices;

@RestController
@RequestMapping("/onlinefleetmngmt/dicv")
public class DicvController {

	@Autowired
	private DicvServices dicvServices;

	@GetMapping("/getGroups")
	public @ResponseBody GroupsDto getGroup(@RequestParam("subGroupId") Integer subGroupId,
			@RequestParam("userId") Integer userId) {
		return dicvServices.getGroup(ifReadOnlyUserChangeToRootAdmin(userId), subGroupId);
	}

	@GetMapping("/getAllCountry")
	public @ResponseBody CountrysDto getAllCountry() throws ServerException {
		return dicvServices.getAllCountry();
	}

	@GetMapping("/getCities")
	public @ResponseBody CitiesDto getCities(@RequestParam("countryId") Integer countryId) {
		return dicvServices.getCities(countryId);
	}

	@GetMapping("/getAllRegions")
	public @ResponseBody RegionListDto getAllRegions(@RequestParam("userId") Integer userId) {
		return dicvServices.getAllRegions(ifReadOnlyUserChangeToRootAdmin(userId));
	}

	@GetMapping("/getType")
	public TypeListDto getType(@RequestParam("subTypeId") Integer subTypeId, @RequestParam("userId") Integer userId) {
		return dicvServices.getType(subTypeId, ifReadOnlyUserChangeToRootAdmin(userId));
	}

	@GetMapping("/getCategory")
	public CategoryListDto getCategory(@RequestParam("userId") Integer userId) {
		return dicvServices.getCategory(ifReadOnlyUserChangeToRootAdmin(userId));
	}

	@GetMapping("/getLoadCategory")
	public LoadCategoryListDto getLoadCategory(@RequestParam("userId") Integer userId) {
		return dicvServices.getLoadCategory(ifReadOnlyUserChangeToRootAdmin(userId));
	}

	@GetMapping("/getCompanies")
	public CompanyDtoList getCompanies(@RequestParam("userId") Integer userId) {
		return dicvServices.getCompanies(userId);
	}

	private Integer ifReadOnlyUserChangeToRootAdmin(Integer userId) {
		return (userId != null && userId.intValue() == 3) ? 2 : userId;
	}

}
