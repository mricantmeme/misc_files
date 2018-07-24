package com.dicv.truck.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicv.truck.dto.CategoryDto;
import com.dicv.truck.dto.CategoryListDto;
import com.dicv.truck.dto.CitiesDto;
import com.dicv.truck.dto.CityDto;
import com.dicv.truck.dto.CompanyDto;
import com.dicv.truck.dto.CompanyDtoList;
import com.dicv.truck.dto.CountryDto;
import com.dicv.truck.dto.CountrysDto;
import com.dicv.truck.dto.GroupDto;
import com.dicv.truck.dto.GroupsDto;
import com.dicv.truck.dto.LoadCategoryDtlsDto;
import com.dicv.truck.dto.LoadCategoryListDto;
import com.dicv.truck.dto.RegionDto;
import com.dicv.truck.dto.RegionListDto;
import com.dicv.truck.dto.TypeDto;
import com.dicv.truck.dto.TypeListDto;
import com.dicv.truck.model.DicvCategory;
import com.dicv.truck.model.DicvCity;
import com.dicv.truck.model.DicvCompany;
import com.dicv.truck.model.DicvCountry;
import com.dicv.truck.model.DicvGroup;
import com.dicv.truck.model.DicvRegion;
import com.dicv.truck.model.DicvType;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.LoadCategory;
import com.dicv.truck.repo.CityRepo;
import com.dicv.truck.repo.CountryRepo;
import com.dicv.truck.repo.DicvCategoryRepo;
import com.dicv.truck.repo.DicvCompanyRepo;
import com.dicv.truck.repo.DicvRegionRepo;
import com.dicv.truck.repo.DicvTypeRepo;
import com.dicv.truck.repo.GroupRepo;
import com.dicv.truck.repo.LoadCategoryRepo;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.EnumUserType;

@Service
public class DicvServices {

	@Autowired
	private UserService userServices;

	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private GroupRepo groupRepo;

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private DicvTypeRepo dicvTypeRepo;

	@Autowired
	private DicvCategoryRepo dicvCategoryRepo;

	@Autowired
	private DicvRegionRepo dicvRegionRepo;

	@Autowired
	private DicvCompanyRepo companyRepo;

	@Autowired
	private LoadCategoryRepo loadCategoryRepo;

	private static final Logger LOGGER = Logger.getLogger(DicvServices.class);

	public GroupsDto getGroup(Integer userId, Integer subGroupId) {
		GroupsDto groupsDto = new GroupsDto();
		try {

			DicvUser user = userServices.getUser(userId, "getALLGroup");
			if (user.getUserType().getUserType().equals(EnumUserType.DEALER.getUserType())) {
				userId = getRootAdminUserId();
			}
			List<DicvGroup> groups = groupRepo.getDicvGroup(userId);

			if (groups != null && !groups.isEmpty()) {
				List<GroupDto> groupDtoList = new ArrayList<GroupDto>();
				groups.forEach(g -> groupDtoList.add(new GroupDto(g.getGroupId(), g.getGroupName(), g.getSubGroupId(),
						null, g.getUpdatedByUser().getUserId())));
				groupsDto.setGroups(groupDtoList);
				groupsDto.setStatus(HttpServletResponse.SC_OK);
				groupsDto.setMessage(DicvConstants.DATA_FOUND_MSG);

			} else {
				groupsDto.setStatus(HttpServletResponse.SC_OK);
				groupsDto.setMessage("Group Not Available");
			}
		} catch (Exception ex) {
			LOGGER.error("Error in Get Groups ", ex);
			groupsDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			groupsDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			return groupsDto;
		}
		return groupsDto;
	}

	public CountrysDto getAllCountry() {
		CountrysDto countrysDto = new CountrysDto();
		try {
			List<DicvCountry> countries = countryRepo.getAllCountry();
			if (countries != null && !countries.isEmpty()) {
				List<CountryDto> countryDtoList = new ArrayList<CountryDto>();
				countries.forEach(d -> countryDtoList.add(
						new CountryDto(d.getCountryId(), d.getCountryName(), d.getCountryCode(), d.getPhoneCode())));
				countrysDto.setCountries(countryDtoList);
				countrysDto.setStatus(HttpServletResponse.SC_OK);
				countrysDto.setMessage(DicvConstants.DATA_FOUND_MSG);

			} else {
				countrysDto.setStatus(HttpServletResponse.SC_OK);
				countrysDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			}
		} catch (Exception ex) {
			LOGGER.error("Error in Get Country List ", ex);
			countrysDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			countrysDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			return countrysDto;
		}
		return countrysDto;
	}

	public CitiesDto getCities(Integer countryId) {
		CitiesDto citiesDto = new CitiesDto();
		try {
			List<DicvCity> cityList = cityRepo.getCityByState(countryId);
			if (cityList != null && !cityList.isEmpty()) {
				List<CityDto> cities = new ArrayList<CityDto>();
				cityList.forEach(c -> cities.add(new CityDto(c.getCityId(), c.getCityCode(), c.getCityName())));
				citiesDto.setCities(cities);
				citiesDto.setStatus(HttpServletResponse.SC_OK);
				citiesDto.setMessage(DicvConstants.DATA_FOUND_MSG);
			} else {
				citiesDto.setStatus(HttpServletResponse.SC_OK);
				citiesDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			}

		} catch (Exception ex) {
			LOGGER.error("Error in Get City List ", ex);
			citiesDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			citiesDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			return citiesDto;
		}
		return citiesDto;
	}

	public DicvCountry getCountryDetails(Integer countryId) {

		if (countryId == null) {
			return null;
		}
		List<DicvCountry> list = countryRepo.getCountryDetails(countryId);
		DicvCountry country = null;
		if (list != null && !list.isEmpty()) {
			country = list.get(0);
			if (country.getIsDeleted() != 1)
				return country;
			else
				return null;
		}
		return country;
	}

	public DicvType getTypeById(Integer typeId) {
		try {
			if (typeId != null) {
				DicvType dicvType = dicvTypeRepo.findOne(typeId);
				return dicvType;
			}
			return null;
		} catch (Exception ex) {
			LOGGER.error("Error in Get DicvType ", ex);
			return null;
		}
	}

	public DicvCategory getDicvCategoryId(Integer categoryId) {
		try {
			if (categoryId != null) {
				DicvCategory dicvCategory = dicvCategoryRepo.findOne(categoryId);
				return dicvCategory;
			}
			return null;
		} catch (Exception ex) {
			LOGGER.error("Error in Get DicvCategory ", ex);
			return null;
		}
	}

	public DicvCompany getDicvCompany(Integer companyId) {
		try {
			if (companyId != null) {
				DicvCompany company = companyRepo.findOne(companyId);
				return company;
			}
			return null;
		} catch (Exception ex) {
			LOGGER.error("Error in Get Company ", ex);
			return null;
		}
	}

	public DicvCountry getDicvCountry(Integer countryId) {
		try {
			if (countryId != null) {
				DicvCountry country = countryRepo.findOne(countryId);
				return country;
			}
			return null;
		} catch (Exception ex) {
			LOGGER.error("Error in Get Country ", ex);
			return null;
		}
	}

	public DicvGroup getDicvGroup(Integer groupId) {
		try {
			if (groupId != null) {
				DicvGroup groups = groupRepo.findOne(groupId);
				if (groups != null && groups.getIsDeleted() == 0)
					return groups;
			}
		} catch (Exception ex) {
			LOGGER.error("Error in Get DicvGroup ", ex);
			return null;
		}
		return null;
	}

	public DicvRegion getDicvRegion(Integer regionId) {
		try {
			if (regionId != null) {
				DicvRegion dicvRegion = dicvRegionRepo.findOne(regionId);
				return dicvRegion;
			}
		} catch (Exception ex) {
			LOGGER.error("Error in Get DicvRegion ", ex);
			return null;
		}
		return null;
	}

	public RegionListDto getAllRegions(Integer userId) {
		RegionListDto regionList = new RegionListDto();
		try {
			LOGGER.info("Getting Region Data For User :: " + userId);
			DicvUser user = userServices.getUser(userId, "getAllRegions");
			if (user == null) {
				regionList.setMessage(DicvConstants.UNAUTHENTICATED_USER);
				regionList.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return regionList;
			}
			List<DicvRegion> resultList = dicvRegionRepo.findAllRegions(userId);
			if (null != resultList && resultList.size() > 0) {
				List<RegionDto> regions = new ArrayList<RegionDto>();
				for (DicvRegion region : resultList) {
					RegionDto regionSo = new RegionDto();
					regionSo.setRegionId(region.getRegionId());
					regionSo.setRegionName(region.getRegionName());
					if (region.getUpdatedByUser() != null)
						regionSo.setUserId(region.getUpdatedByUser());
					if (null != region.getSubRegionId() && region.getSubRegionId() > 0)
						regionSo.setSubregionId(region.getSubRegionId());
					regions.add(regionSo);
				}
				regionList.setStatus(HttpServletResponse.SC_OK);
				regionList.setMessage(DicvConstants.DATA_FOUND_MSG);
				regionList.setRegions(regions);
				return regionList;
			}
			LOGGER.info("Region Not Found");
			regionList.setMessage("Region Not Available");
			regionList.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			LOGGER.error("Exception in Region List" + e.getMessage());
			regionList.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			regionList.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return regionList;
		}
		return regionList;
	}

	public TypeListDto getType(Integer subTypeId, Integer userId) {

		TypeListDto typeLists = new TypeListDto();
		try {
			DicvUser user = userServices.getUser(userId, "getType");
			if (user.getUserType().getUserType().equals(EnumUserType.DEALER.getUserType())) {
				userId = getRootAdminUserId();
			}
			List<TypeDto> typeList = dicvTypeRepo.getSubType(userId, subTypeId);
			if (null == typeList || typeList.size() == 0) {
				typeLists.setStatus(HttpServletResponse.SC_OK);
				typeLists.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			} else {
				typeLists.setMessage(DicvConstants.DATA_FOUND_MSG);
				typeLists.setTypeList(typeList);
				typeLists.setStatus(HttpServletResponse.SC_OK);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Getting Type ", e);
			typeLists.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			typeLists.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return typeLists;
		}
		return typeLists;
	}

	private Integer getRootAdminUserId() {
		return 2;
	}

	public CategoryListDto getCategory(Integer userId) {
		CategoryListDto categoryListDto = new CategoryListDto();
		try {
			DicvUser user = userServices.getUser(userId, "getCategory");
			if (null == user) {
				categoryListDto.setMessage("User Not Have Access");
				categoryListDto.setStatus(HttpServletResponse.SC_OK);
				return categoryListDto;
			}
			if (user.getUserType().getUserType().equals(EnumUserType.DEALER.getUserType())) {
				userId = getRootAdminUserId();
			}
			List<CategoryDto> resultList = dicvCategoryRepo.getCategory(userId);
			if (null == resultList || resultList.isEmpty()) {
				categoryListDto.setStatus(HttpServletResponse.SC_OK);
				categoryListDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			} else {
				categoryListDto.setCategoryList(resultList);
				categoryListDto.setMessage(DicvConstants.DATA_FOUND_MSG);
				categoryListDto.setStatus(HttpServletResponse.SC_OK);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Getting CategoryDto ", e);
			categoryListDto.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			categoryListDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}
		return categoryListDto;
	}

	public CompanyDtoList getCompanies(Integer userId) {
		CompanyDtoList companyDtoList = new CompanyDtoList();
		try {
			DicvUser user = userServices.getUser(userId, "getCompanies");
			if (user.getUserType().getUserType().equals(EnumUserType.ROOTADMIN.getUserType())
					|| user.getUserType().getUserType().equals(EnumUserType.DEALER.getUserType())) {
				List<DicvCompany> companyList = companyRepo.getCompanyList();
				if (companyList != null && !companyList.isEmpty()) {
					CompanyDto companyDto = new CompanyDto();
					List<CompanyDto> list = new ArrayList<CompanyDto>();
					for (DicvCompany company : companyList) {
						companyDto = new CompanyDto();
						companyDto.setCompanyAddress(company.getCompanyAddress());
						companyDto.setCompanyCode(company.getCompanyCode());
						companyDto.setCompanyId(company.getCompanyId());
						companyDto.setCompanyName(company.getCompanyName());
						list.add(companyDto);
					}
					companyDtoList.setCompanyList(list);
					companyDtoList.setMessage(DicvConstants.DATA_FOUND_MSG);
					companyDtoList.setStatus(HttpServletResponse.SC_OK);

				} else {
					companyDtoList.setMessage("Companies Not Available ");
					companyDtoList.setStatus(HttpServletResponse.SC_NO_CONTENT);
				}
			} else {
				companyDtoList.setMessage("User Not Have Access");
				companyDtoList.setStatus(HttpServletResponse.SC_OK);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Getting Companies ", e);
			companyDtoList.setStatus(HttpServletResponse.SC_OK);
			companyDtoList.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
		}

		return companyDtoList;
	}

	public LoadCategoryListDto getLoadCategory(Integer userId) {
		LoadCategoryListDto loadCategoryListDto = new LoadCategoryListDto();
		List<LoadCategoryDtlsDto> loadCategoryList = new ArrayList<LoadCategoryDtlsDto>();
		try {
			List<LoadCategory> lcList = loadCategoryRepo.getLoadCategoryList(userId);
			if (lcList != null && lcList.size() > 0) {
				LoadCategoryDtlsDto loadCategoryDto = new LoadCategoryDtlsDto();
				for (LoadCategory loadCategory : lcList) {
					loadCategoryDto = new LoadCategoryDtlsDto();
					loadCategoryDto.setUserId(loadCategory.getDicvUserCreatedBy().getUserId());
					loadCategoryDto.setLoadCategoryName(loadCategory.getLoadCategoryName());
					loadCategoryDto.setLoadCategoryId(loadCategory.getLoadCategoryId());
					loadCategoryList.add(loadCategoryDto);
				}
				loadCategoryListDto.setMessage(DicvConstants.DATA_FOUND_MSG);
				loadCategoryListDto.setStatus(HttpServletResponse.SC_OK);
				loadCategoryListDto.setLoadCategoryList(loadCategoryList);
				return loadCategoryListDto;
			} else {
				loadCategoryListDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				loadCategoryListDto.setStatus(HttpServletResponse.SC_OK);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Getting Load Categories ", e);
			loadCategoryListDto.setStatus(HttpServletResponse.SC_OK);
			loadCategoryListDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
		}
		return loadCategoryListDto;
	}

}
