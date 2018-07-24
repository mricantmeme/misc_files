/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import com.tcl.site.dto.SiteCreateRequestDto;
import com.tcl.site.dto.SiteCreateResponseDto;
import com.tcl.site.dto.SiteDeleteRequestDto;
import com.tcl.site.dto.SiteResponseDto;
import com.tcl.site.dto.SiteStatusReqDto;
import com.tcl.site.dto.SiteUpdateRequestDto;
import com.tcl.site.dto.UserRegistrationMailDto;
import com.tcl.site.exceptionhandler.NoDataFoundException;
import com.tcl.site.exceptionhandler.UserAlreadyExistException;
import com.tcl.site.exceptionhandler.UserNotFoundException;
import com.tcl.site.model.Site;
import com.tcl.site.model.User;
import com.tcl.site.model.Utility;
import com.tcl.site.repo.SiteRepo;
import com.tcl.site.repo.UserRepo;
import com.tcl.site.repo.UtilityRepo;
import com.tcl.site.response.ListResponse;
import com.tcl.site.response.Response;
import com.tcl.site.util.EmailTemplateType;
import com.tcl.site.util.Helper;
import com.tcl.site.util.PasswordDecode;
import com.tcl.site.util.SiteConstants;

@Service
public class SiteService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SiteRepo siteRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UtilityRepo utilityRepo;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private MongoOperations mongoOperation;

	public Response<SiteCreateResponseDto> createSite(SiteCreateRequestDto requestDto) {

		Response<SiteCreateResponseDto> responseDto = new Response<SiteCreateResponseDto>();
		Optional<User> user = userRepo.findByUserId(requestDto.getUserId());
		if (!user.isPresent())
			throw new UserNotFoundException(SiteConstants.USER_NOT_FOUND);

		Optional<User> userEmail = userRepo.findByEmailId(requestDto.getEmailId());
		if (userEmail.isPresent())
			throw new UserAlreadyExistException(SiteConstants.USER_EMAIL_ID_AlREADY_EXISTS);

		User userUserName = userRepo.findByUserNameContainsIgnoreCase(requestDto.getSiteUserName());
		if (userUserName != null)
			throw new UserAlreadyExistException(SiteConstants.USER_NAME_AlREADY_EXISTS);

		if (!requestDto.getPassword().equals(requestDto.getConfirmPassword())) {
			throw new NoDataFoundException(SiteConstants.PASSWORD_CONFIRMATION);

		}

		Site addSite = addSiteDetails(requestDto);
		User newUser = addNewUser(requestDto, addSite);
		SiteCreateResponseDto response = new SiteCreateResponseDto();
		response.setSiteId(addSite.getSiteId());
		response.setUserId(newUser.getUserId());
		responseDto.setData(response);
		responseDto.setMessage(SiteConstants.SITE_CREATED_SUCCESSFULLY);
		responseDto.setStatus(HttpServletResponse.SC_OK);
		responseDto.setError(false);
		sendSiteRegistrationEmailNotification(requestDto);
		return responseDto;
	}

	private void sendSiteRegistrationEmailNotification(SiteCreateRequestDto requestDto) {
		UserRegistrationMailDto mailDto = new UserRegistrationMailDto();
		mailDto.setFirstName(requestDto.getSiteName());
		mailDto.setToAddress(requestDto.getEmailId());
		mailDto.setUserName(requestDto.getSiteUserName());
		mailDto.setPassword(requestDto.getConfirmPassword());
		mailService.sendEmail(mailDto, requestDto.getEmailId(), mailDto.getCcAddress(),
				EmailTemplateType.SITE_REGISTRATION_EMAIL_TEMPLATE);

	}

	private User addNewUser(SiteCreateRequestDto requestDto, Site addSite) {

		String userId = null;
		Optional<User> getUser = userRepo.findFirstByOrderByCreatedDateDesc();
		if (getUser.isPresent()) {
			if (getUser.get().getUserId().charAt(0) == SiteConstants.SITE_USER_STARTING)
				userId = Helper.generateNextUserId(getUser.get().getUserId());
			else
				userId = Helper.generateUniqueUserId(SiteConstants.SITE_USER_STARTING);

		} else {
			userId = Helper.generateUniqueUserId(SiteConstants.SITE_USER_STARTING);

		}
		requestDto.setRoleId(SiteConstants.SITE_ROLE);

		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		User addUser = modelMapper.map(requestDto, User.class);
		String hashOfPassword = PasswordDecode.getHash(requestDto.getSiteUserName().toLowerCase(), "MD5");
		String passwordHash = PasswordDecode.getHash(requestDto.getConfirmPassword(), hashOfPassword, "MD5");
		addUser.setUserId(userId);
		addUser.setUserName(requestDto.getSiteUserName());
		addUser.setUserStatus(requestDto.getSiteStatus());
		addUser.setFirstName(requestDto.getSiteName());
		addUser.setMiddleName(requestDto.getSiteName());
		addUser.setLastName(requestDto.getSiteName());
		addUser.setEmailId(requestDto.getEmailId());
		addUser.setPassword(passwordHash);
		addUser.setPasswordHash(hashOfPassword);
		addUser.setSite(addSite);
		addUser.setCustomer(addSite.getCustomer());
		addUser.setIsActive(true);
		addUser.setCreatedBy(requestDto.getUserId());
		addUser.setCreatedDate(new Date());
		addUser.setModifiedBy(requestDto.getUserId());
		addUser.setModifiedDate(new Date());
		return userRepo.save(addUser);
	}

	private Site addSiteDetails(SiteCreateRequestDto requestDto) {
		String siteId = null;

		Optional<Utility> details = utilityRepo.findByUtilityId(requestDto.getSiteUtilityId());
		if (!details.isPresent())
			throw new NoDataFoundException(SiteConstants.UTILITY_NOT_FOUND);

		Optional<Site> getSite = siteRepo.findFirstByOrderByCreatedDateDesc();
		if (getSite.isPresent()) {
			if (getSite.get().getSiteId().charAt(0) == SiteConstants.SITE_STARTING)
				siteId = Helper.generateNextSiteId(getSite.get().getSiteId(), details.get().getUtilityName());
			else
				siteId = Helper.generateUniqueSiteId(details.get().getUtilityName());

		} else
			siteId = Helper.generateUniqueSiteId(details.get().getUtilityName());

		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		Site addSite = modelMapper.map(requestDto, Site.class);
		addSite.setSiteId(siteId);
		addSite.setIsActive(true);
		addSite.setCreatedBy(requestDto.getUserId());
		addSite.setCreatedDate(new Date());
		addSite.setModifiedBy(requestDto.getUserId());
		addSite.setModifiedDate(new Date());
		return siteRepo.save(addSite);
	}

	public Response<String> updateSite(SiteUpdateRequestDto requestDto) {
		Response<String> responseDto = new Response<>();
		Optional<Site> site = siteRepo.findBySiteId(requestDto.getSiteId());
		if (site.isPresent()) {
			Site siteDetail = site.get();
			siteDetail.setAddress(requestDto.getAddress());
			siteDetail.getCustomer().setCustomerId(requestDto.getCustomerId());
			siteDetail.setEmailId(requestDto.getEmailId());
			siteDetail.setMobileNumber(requestDto.getSiteMobileNumber());
			siteDetail.setPhoneNumber(requestDto.getPhoneNumber());
			siteDetail.setSiteDescription(requestDto.getSiteDescription());
			siteDetail.setSiteLogo(requestDto.getSiteLogo());
			siteDetail.setSiteName(requestDto.getSiteName());
			siteDetail.setSiteStatus(requestDto.getSiteStatus());
			siteDetail.setSiteUtilityId(requestDto.getSiteUtilityId());
			siteDetail.setZoneName(requestDto.getZoneName());
			siteDetail.setModifiedBy(requestDto.getSiteId());
			siteDetail.setModifiedDate(new Date());
			siteRepo.save(siteDetail);

			Optional<User> user = userRepo.findBySite(site.get());
			if (!user.isPresent())
				throw new NoDataFoundException(SiteConstants.SITE_NOT_FOUND);
			User userStatus = user.get();
			userStatus.setUserStatus(requestDto.getSiteStatus());
			userStatus.setModifiedBy(requestDto.getSiteId());
			userStatus.setModifiedDate(new Date());
			userRepo.save(userStatus);

			responseDto.setMessage(SiteConstants.SITE_UPDATED_SUCCESSFULLY);
			responseDto.setStatus(HttpServletResponse.SC_OK);
			responseDto.setError(false);

		} else {
			responseDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			responseDto.setError(true);
			responseDto.setMessage(SiteConstants.SITE_NOT_FOUND);
		}
		return responseDto;

	}

	public Response<Boolean> deleteSite(SiteDeleteRequestDto requestDto) {
		Response<Boolean> responseDto = new Response<Boolean>();

		List<Site> site = siteRepo.findBySiteIdIn(requestDto.getSiteId());
		if (!site.isEmpty()) {
			for (Site siteDetail : site) {
				siteDetail.setIsActive(false);
				siteDetail.setModifiedDate(new Date());
			}
			siteRepo.save(site);

			List<User> user = userRepo.findBySiteIn(site);
			if (!user.isEmpty()) {
				for (User userDetail : user) {
					userDetail.setIsActive(false);
					userDetail.setModifiedDate(new Date());
				}
				userRepo.save(user);
			}
			responseDto.setMessage(SiteConstants.SITE_DELETED_SUCCESSFULLY);
			responseDto.setStatus(HttpServletResponse.SC_OK);
			responseDto.setError(false);

		} else {
			responseDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			responseDto.setError(true);
			responseDto.setMessage(SiteConstants.SITE_NOT_FOUND);
		}
		return responseDto;
	}

	public ListResponse<List<SiteResponseDto>> listSites(String userId, String search, int page, int size) {
		ListResponse<List<SiteResponseDto>> responseDto = new ListResponse<List<SiteResponseDto>>();

		List<Site> siteDetails = null;
		if (search == null || search.isEmpty())
			siteDetails = siteRepo.findByCreatedByAndIsActive(userId, true, createPageRequest(page, size));

		else
			siteDetails = siteRepo
					.findBySiteNameContainsIgnoreCaseOrSiteDescriptionContainsIgnoreCaseOrEmailIdContainsIgnoreCaseOrMobileNumberContainsIgnoreCaseAndIsActiveAndCreatedBy(
							search, search, search, search, true, userId, createPageRequest(page, size));

		if (!siteDetails.isEmpty()) {
			List<SiteResponseDto> lstEntity = new ArrayList<>();
			siteDetails.forEach(u -> lstEntity.add(convertToSiteDetailsDto(u)));
			responseDto.setData(lstEntity);
			responseDto.setMessage(SiteConstants.SITE_List);
			responseDto.setStatus(HttpServletResponse.SC_OK);
			responseDto.setError(false);
			responseDto.setCount(siteDetails.size());
			responseDto.setTotalRecords(getTotalRecords(userId, search));

		} else {
			responseDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			responseDto.setError(true);
			responseDto.setMessage(SiteConstants.SITE_NOT_FOUND);
		}

		return responseDto;
	}

	private int getTotalRecords(String userId, String search) {
		if (search.isEmpty() || search == null)
			return siteRepo.findByCreatedByAndIsActive(userId, true).size();

		else
			return siteRepo
					.findBySiteNameContainsIgnoreCaseOrSiteDescriptionContainsIgnoreCaseOrEmailIdContainsIgnoreCaseOrMobileNumberContainsIgnoreCaseAndIsActiveAndCreatedBy(
							search, search, search, search, search, true, userId)
					.size();

	}

	private SiteResponseDto convertToSiteDetailsDto(Site site) {
		SiteResponseDto responseDto = modelMapper.map(site, SiteResponseDto.class);
		return responseDto;
	}

	private Pageable createPageRequest(int page, int size) {
		return new PageRequest(page, size, new Sort(Sort.Direction.DESC, "_id"));
	}

	public Response<SiteResponseDto> siteDetails(String siteId) {
		Response<SiteResponseDto> responseDto = new Response<SiteResponseDto>();
		Optional<Site> siteDetails = siteRepo.findBySiteId(siteId);
		if (!siteDetails.isPresent())
			throw new NoDataFoundException(SiteConstants.SITE_NOT_FOUND);
		SiteResponseDto response = modelMapper.map(siteDetails.get(), SiteResponseDto.class);
		responseDto.setData(response);
		responseDto.setError(false);
		responseDto.setMessage(SiteConstants.SITE_DETAILS);
		responseDto.setStatus(HttpServletResponse.SC_OK);
		return responseDto;

	}

	public Response<Boolean> updateSiteStatus(SiteStatusReqDto requestDto) {
		Response<Boolean> responseDto = new Response<Boolean>();
		Optional<Site> siteDetails = siteRepo.findBySiteId(requestDto.getSiteId());
		if (!siteDetails.isPresent())
			throw new NoDataFoundException(SiteConstants.SITE_NOT_FOUND);
		Site updateSite = siteDetails.get();
		updateSite.setSiteStatus(requestDto.getSiteStatus());
		updateSite.setModifiedBy(requestDto.getUserId());
		updateSite.setModifiedDate(new Date());
		siteRepo.save(updateSite);
		Optional<User> user = userRepo.findBySite(updateSite);
		if (!user.isPresent())
			throw new NoDataFoundException(SiteConstants.SITE_NOT_FOUND);
		User userStatus = user.get();
		userStatus.setUserStatus(requestDto.getSiteStatus());
		userStatus.setModifiedBy(requestDto.getUserId());
		userStatus.setModifiedDate(new Date());
		userRepo.save(userStatus);

		responseDto.setError(false);
		responseDto.setMessage(SiteConstants.SITE_STATUS);
		responseDto.setStatus(HttpServletResponse.SC_OK);
		return responseDto;
	}

	public ListResponse<List<SiteResponseDto>> filterSiteList(List<String> filters, String sortByFieldName,
			String sortType, Integer page, Integer size, String userId) {
		ListResponse<List<SiteResponseDto>> responseDto=new ListResponse<>();
		List<SiteResponseDto> lstEntity=new ArrayList<>();

		String siteSearchFields="";
		List<Site> totalSiteDetails=null;
		List<SiteResponseDto> siteDetails=new ArrayList<>();

		BasicQuery executeQuery=null;
		String filter=null;
		String soryBy=null;
		String id=null;

		if(filters.size() >0 && sortByFieldName != "" && sortType != "") {
			filter=filters.stream().map(Object::toString).collect(Collectors.joining("},{","{","}"));
			id="{"+"'created_by'"+":"+"'"+userId+"'"+"},";
			filter=id.concat(filter);
			siteSearchFields=SiteConstants.SITE_DEFAULT_QUERY;
			soryBy="{"+sortByFieldName+":"+sortType+"}";
			siteSearchFields=String.format(siteSearchFields, filter,soryBy);

		}else if (filters.size() >0 && sortByFieldName == "") {
			filter=filters.stream().map(Object::toString).collect(Collectors.joining("},{","{","}"));
			id="{"+"'created_by'"+":"+"'"+userId+"'"+"},";
			filter=id.concat(filter);
			siteSearchFields=SiteConstants.SITE_DEFAULT_FILTER_QUERY;
			siteSearchFields=String.format(siteSearchFields, filter);


		}else if (sortByFieldName != "" && sortType != "") {
			id="{"+"'created_by'"+":"+"'"+userId+"'"+"},";
			soryBy="{"+sortByFieldName+":"+sortType+"}";
			siteSearchFields=SiteConstants.SITE_DEFAULT_SORT_QUERY;
			siteSearchFields=String.format(siteSearchFields,id, soryBy);
		}else {
			id="{"+"'created_by'"+":"+"'"+userId+"'"+"},";
			siteSearchFields=SiteConstants.SITE_DEFAULT_ACTIVE_QUERY;
			siteSearchFields=String.format(siteSearchFields, id);}

		executeQuery = new BasicQuery(siteSearchFields);
		totalSiteDetails = mongoOperation.find(executeQuery, Site.class);


		if(totalSiteDetails.isEmpty()) {
			responseDto.setData(lstEntity);
			responseDto.setCount(0);
			responseDto.setError(false);
			responseDto.setMessage(SiteConstants.SITE_NOT_FOUND);
			responseDto.setStatus(HttpServletResponse.SC_OK);
			responseDto.setTotalRecords(0);
			return 	responseDto;
		}

		totalSiteDetails.forEach(u -> lstEntity.add(convertToSiteDetailsDto(u)));

		// generate pageNation
		if(page==0)
			page=1;
		else
			page++;
		Long startNumber = (long) (((size * page) - size)+1);
		Long endNumber = (long) (size * page);
		if(lstEntity.size() >= 0)
		{

			int fromIndex = (startNumber.intValue() - 1) ;
			int toIndex = endNumber.intValue() ;

			if(lstEntity.size() <=  endNumber.intValue())
			{
				toIndex = lstEntity.size();
			}

			siteDetails = lstEntity.subList(fromIndex , toIndex);
		}
		siteDetails = siteDetails.stream().collect(Collectors.toList());
		responseDto.setData(siteDetails);
		responseDto.setCount(siteDetails.size());
		responseDto.setTotalRecords(lstEntity.size());
		responseDto.setError(false);
		responseDto.setMessage(SiteConstants.SITE_List);

		return responseDto;
	}

}
