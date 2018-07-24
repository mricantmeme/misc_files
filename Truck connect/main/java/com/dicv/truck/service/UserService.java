package com.dicv.truck.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.dicv.truck.dto.DriverDtlsListDto;
import com.dicv.truck.dto.DriverInfoDto;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.dto.UserDetailsDto;
import com.dicv.truck.dto.UserDto;
import com.dicv.truck.dto.UserListDto;
import com.dicv.truck.dto.UserSelectedDto;
import com.dicv.truck.dto.UsersDto;
import com.dicv.truck.dto.UsersSelectedDto;
import com.dicv.truck.exception.DataNotFoundException;
import com.dicv.truck.exception.ServerException;
import com.dicv.truck.model.DicvCountry;
import com.dicv.truck.model.DicvGroup;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.Photo;
import com.dicv.truck.model.UserLog;
import com.dicv.truck.model.UserType;
import com.dicv.truck.model.Vehicle;
import com.dicv.truck.repo.PhotoRepo;
import com.dicv.truck.repo.UserLogRepo;
import com.dicv.truck.repo.UserRepo;
import com.dicv.truck.repo.UserTypeRepo;
import com.dicv.truck.token.PasswordEncoder;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.EnumUserType;
import com.dicv.truck.utility.UserStatus;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserTypeRepo userTypeRepo;

	@Autowired
	private SendMailService sendMailService;

	@Autowired
	private UserLogRepo userLogRepo;

	@Autowired
	private DicvServices dicvService;

	@Autowired
	PhotoRepo photoRepo;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Transactional
	public StatusMessageDto saveUser(UserDto userDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			DicvUser dicvUser = new DicvUser();
			String userpwd = null;
			DicvUser createByUser = getUser(userDto.getCreatedOrUpdatedByUserId(), "Create User");
			if (null == userDto.getUserId() || userDto.getUserId() <= 0) {
				UserType userType = userTypeRepo.findOne(userDto.getRoleId());
				if (userType == null) {
					statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					statusMessageDto.setMessage("Please Add User Role Details");
					return statusMessageDto;
				}
				if (userType.getUserType().equals(EnumUserType.DRIVER.getUserType())) {
					dicvUser.setRecordStatus(UserStatus.OPEN.getRecordStatusCode());
				} else {
					dicvUser.setRecordStatus(UserStatus.NEW.getRecordStatusCode());
				}
				dicvUser.setUserStatus("NEW");
				dicvUser.setVersionNo(1l);
				dicvUser.setChangePassword(1);
				dicvUser.setCreatedBy(createByUser.getUserId());
				dicvUser.setUserName(userDto.getUserName());
				dicvUser.setUserType(userType);
				userpwd = DicvUtil.generatePassword();
				dicvUser.setUserPassword(PasswordEncoder.encryptPassword(userpwd));
			} else {
				dicvUser = getUser(userDto.getUserId(), "Update User");
				if (DicvUtil.isValidAttribute(userDto.getRecordStatus())
						&& userDto.getRecordStatus().equalsIgnoreCase(DicvConstants.DELETE_RECORD)) {
					dicvUser.setRecordStatus(UserStatus.DELETED.getRecordStatusCode());
					dicvUser.setModifiedDate(DicvUtil.getTimestamp());
					dicvUser.setUpdatedBy(createByUser.getUserId());
					userRepo.save(dicvUser);
					saveUserLog(createByUser.getUserId(), dicvUser.getUserId(), DicvConstants.USER,
							DicvConstants.DELETE);
					statusMessageDto.setStatus(HttpServletResponse.SC_OK);
					statusMessageDto.setMessage(DicvConstants.SUCCESS_DELETED);
					return statusMessageDto;
				}
			}
			if (EnumUserType.ROOTADMIN.getUserType().equals(createByUser.getUserType().getUserType())) {
				if (userDto.getCustomerAdminId() != null && userDto.getCustomerAdminId() > 0)
					dicvUser.setManagerId(userDto.getCustomerAdminId());
				else
					dicvUser.setManagerId(createByUser.getUserId());
			}
			dicvUser.setUpdatedBy(createByUser.getUserId());
			dicvUser.setModifiedDate(DicvUtil.getTimestamp());
			Integer userId = setUserData(userDto, dicvUser, createByUser);
			if (userId == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
				return statusMessageDto;
			}
			if (userDto.getUserId() == null || userDto.getUserId() <= 0) {
				statusMessageDto.setMessage(DicvConstants.SUCCESS_CREATED);
				sendMail(userDto, dicvUser, userpwd);
				saveUserLog(createByUser.getUserId(), dicvUser.getUserId(), DicvConstants.USER, DicvConstants.CREATE);
			} else {
				statusMessageDto.setMessage(DicvConstants.SUCCESS_UPDATED);
				saveUserLog(createByUser.getUserId(), dicvUser.getUserId(), DicvConstants.USER, DicvConstants.UPDATE);
			}
			statusMessageDto.setIdentifier(userId);
			statusMessageDto.setStatus(HttpServletResponse.SC_OK);

		} catch (Exception ex) {
			LOGGER.error("Exception Creating User :: ", ex);
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		}
		return statusMessageDto;

	}

	@Async
	private void sendMail(UserDto userDto, DicvUser dicvUser, String userpwd) {
		sendMail(userDto.getEmailId(), dicvUser.getUserName(), userpwd);
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
		case DicvConstants.IMEI:
			userLog.setLogImeiId(loggedId);
			break;
		}
		userLog.setCreatedTime(DicvUtil.getTimestamp());
		userLog.setUserId(userId);
		userLog.setLogType(logType);
		userLogRepo.save(userLog);
	}

	private boolean sendMail(String emailId, String generatedUserName, String password) {
		String message;
		String subject;
		// reset password msg.
		if (null == generatedUserName) {
			message = "Dear User, The password hase been reset to - " + password + " Thank you.";
			subject = "Reset Password";
		} else {
			message = "Dear User, The application user name is - " + generatedUserName + " and password is - "
					+ password + " kindly change password on logging in for the first time. Thank you.";
			subject = " New Password";
		}
		return sendMailService.sendMail("paras.dicv@gmail.com", emailId, subject, message);
	}

	@Transactional
	private Integer setUserData(UserDto userDto, DicvUser dicvUser, DicvUser createByUser) {
		try {
			Integer userId;
			LOGGER.info("Setting User Data :: " + userDto);
			dicvUser.setModifiedDate(DicvUtil.getTimestamp());
			dicvUser.setFirstName(userDto.getFirstName());
			dicvUser.setLastName(userDto.getLastName());
			dicvUser.setAddressLine1(userDto.getAddressLine1());
			dicvUser.setCity(userDto.getCityName());
			dicvUser.setState(userDto.getStateName());
			dicvUser.setZipCode(userDto.getZipCode());
			dicvUser.setEmergencyContactno1(userDto.getEmergencyContact1());
			dicvUser.setEmergencyContactno2(userDto.getEmergencyContact2());
			dicvUser.setLandlineno(userDto.getLandlineno());
			dicvUser.setDicvCompany(null);
			if (EnumUserType.ROOTADMIN.getUserType().equals(createByUser.getUserType().getUserType())) {
				setUserDataForRootAdmin(userDto, dicvUser, createByUser);
			}
			DicvCountry country = null;
			if (null != userDto.getCountryId() && userDto.getCountryId() > 0) {
				country = dicvService.getCountryDetails(userDto.getCountryId());
				if (null != country) {
					dicvUser.setCountryCode(country.getCountryCode());
				}
			}
			dicvUser.setDicvCountry(country);
			dicvUser.setEmail(userDto.getEmailId());
			dicvUser.setMobile(userDto.getMobileNumber());
			dicvUser.setDrivingLicenseNo(userDto.getDrivingLicenseNo());
			if (DicvUtil.isValidAttribute(userDto.getLicenseExpireDate())) {
				Date stringToDate = DicvUtil.stringToDate(userDto.getLicenseExpireDate());
				dicvUser.setLicenseExpireDate(new java.sql.Timestamp(stringToDate.getTime()));
			} else {
				dicvUser.setLicenseExpireDate(null);
			}
			DicvGroup dicvGroup = null;
			if (null != userDto.getGroupId()) {
				dicvGroup = dicvService.getDicvGroup(userDto.getGroupId());
			}
			if (userDto.getImageSource() != null && !userDto.getImageSource().isEmpty()) {
				Photo photo = setPhoto(userDto.getImageSource(), createByUser, DicvUtil.getTimestamp());
				photo = photoRepo.save(photo);
				dicvUser.setPhoto(photo);
			}
			dicvUser.setRecordStatus(UserStatus.OPEN.getRecordStatusCode());
			dicvUser.setDicvGroup(dicvGroup);
			DicvUser updateUser = userRepo.save(dicvUser);
			userId = updateUser.getUserId();
			return userId;
		} catch (Exception ex) {
			LOGGER.error("Exception in Add/Update User ", ex);
			return null;
		}
	}

	private void setUserDataForRootAdmin(UserDto userDto, DicvUser dicvUser, DicvUser createByUser) {
		if (userDto.getCompanyId() != null) {
			dicvUser.setDicvCompany(dicvService.getDicvCompany(userDto.getCompanyId()));
		} else {
			dicvUser.setDicvCompany(null);
		}
		if (userDto.getCustomerAdminId() != null) {
			dicvUser.setManagerId(userDto.getCustomerAdminId());
		} else {
			dicvUser.setManagerId(createByUser.getUserId());
		}
	}

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
			return photo;
		} catch (IOException e) {
			return null;
		}
	}

	public DicvUser getUserByUserName(String userName) {
		try {
			DicvUser dicvUser = new DicvUser();
			List<DicvUser> userList = null;
			if (userName != null) {
				userList = userRepo.getUserDetails(userName, UserStatus.DELETED.getRecordStatusCode());
				if (userList != null && !userList.isEmpty()) {
					dicvUser = userList.get(0);
					return dicvUser;
				}
			}
		} catch (Exception ex) {
			LOGGER.error("User Not Found :: " + userName);
			return null;
		}
		return null;
	}

	public DicvUser getUser(Integer userId, String method) {
		DicvUser dicvUser = new DicvUser();
		if (userId == null)
			return null;
		List<DicvUser> userList = null;
		userList = userRepo.getUserDetails(userId, UserStatus.DELETED.getRecordStatusCode());
		if (userList != null && !userList.isEmpty()) {
			dicvUser = userList.get(0);
			return dicvUser;
		}
		LOGGER.info("User Not Availble " + userId + "  Name ::  " + method);
		return null;
	}

	public UserListDto getRolesBasedUserList(Integer userId, String userType, Integer rowPerPage, Integer page,
			String keyword) {
		UserListDto userList = new UserListDto();
		try {
			DicvUser dicvUser = getUser(userId, "getRolesBasedUserList");

			String loggedInUserRole = dicvUser.getUserType().getUserType();
			Long countOfUsers = 0l;

			if (loggedInUserRole.equals(EnumUserType.ROOTADMIN.getUserType())) {

				if (keyword != null && keyword.length() > 0) {
					countOfUsers = userRepo.countUserByRoleAndKeyword(userType, keyword,
							UserStatus.DELETED.getRecordStatusCode());
				} else {
					countOfUsers = userRepo.countOfUser(userType, UserStatus.DELETED.getRecordStatusCode());
				}
			} else if (loggedInUserRole.equals(EnumUserType.CUSTOMERADMIN.getUserType())) {
				if (keyword != null && keyword.length() > 0) {
					countOfUsers = userRepo.countUserByRoleAndKeyword(userId, userType, keyword,
							UserStatus.DELETED.getRecordStatusCode());
				} else {
					countOfUsers = userRepo.countOfUser(userId, userType, UserStatus.DELETED.getRecordStatusCode());
				}
			}

			if (countOfUsers > 0) {
				userList.setTotalCount(countOfUsers);
				List<UserDetailsDto> userDetailsList = getRolesBasedUserListWithPagination(dicvUser, loggedInUserRole,
						userType, rowPerPage, page, keyword);
				if (userDetailsList != null && !userDetailsList.isEmpty()) {
					userList.setUserList(userDetailsList);
					userList.setStatus(HttpServletResponse.SC_OK);
					userList.setMessage(DicvConstants.DATA_FOUND_MSG);
					return userList;
				}
			}
			userList.setStatus(HttpServletResponse.SC_OK);
			userList.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
		} catch (Exception ex) {
			LOGGER.error("Error in User List By Role ", ex);
			userList.setStatus(HttpServletResponse.SC_OK);
			userList.setMessage(DicvConstants.DATA_FOUND_MSG);
			return userList;

		}
		return userList;
	}

	private List<UserDetailsDto> getRolesBasedUserListWithPagination(DicvUser dicvUser, String loggedInUserRole,
			String userType, Integer rowPerPage, Integer page, String keyword) {
		UserDetailsDto userDetails = null;
		List<DicvUser> userResultList = null;
		List<UserDetailsDto> userDetailsList = null;
		PageRequest pageable = DicvUtil.getPageable(page, rowPerPage);

		if (EnumUserType.ROOTADMIN.getUserType().equals(dicvUser.getUserType().getUserType())) {
			if (keyword != null && keyword.length() > 0) {
				userResultList = userRepo.getUserByRoleAndKeyword(userType, keyword,
						UserStatus.DELETED.getRecordStatusCode(), pageable);
			} else {
				userResultList = userRepo.getUserByRole(userType, UserStatus.DELETED.getRecordStatusCode(), pageable);
			}
		} else if (EnumUserType.CUSTOMERADMIN.getUserType().equals(dicvUser.getUserType().getUserType())) {
			if (keyword != null && keyword.length() > 0) {
				userResultList = userRepo.getUserByRoleAndKeyword(dicvUser.getUserId(), userType, keyword,
						UserStatus.DELETED.getRecordStatusCode(), pageable);
			} else {
				userResultList = userRepo.getUserByRole(dicvUser.getUserId(), userType,
						UserStatus.DELETED.getRecordStatusCode(), pageable);
			}
		}
		if (userResultList != null && !userResultList.isEmpty()) {
			userDetailsList = new ArrayList<UserDetailsDto>();
			for (DicvUser user : userResultList) {
				userDetails = new UserDetailsDto();
				setUserDetails(userDetails, user);
				userDetailsList.add(userDetails);
			}
		}
		return userDetailsList;
	}

	private void setUserDetails(UserDetailsDto userDetails, DicvUser user) {
		userDetails.setUserId(user.getUserId());
		userDetails.setUserName(user.getUserName());
		userDetails.setFirstName(user.getFirstName());
		userDetails.setLastName(user.getLastName());
		userDetails.setAddressLine1(user.getAddressLine1());
		userDetails.setEmailId(user.getEmail());
		userDetails.setMobileNumber(user.getMobile());
		userDetails.setEmergencyContact1(user.getEmergencyContactno1());
		userDetails.setEmergencyContact2(user.getEmergencyContactno2());
		userDetails.setCityName(user.getCity());
		userDetails.setZipCode(user.getZipCode());
		userDetails.setLandlineno(user.getLandlineno());
		userDetails.setDrivingLicenseNo(user.getDrivingLicenseNo());
		if (null != user.getLicenseExpireDate()) {
			userDetails.setLicenseExpireDate(user.getLicenseExpireDate().toString());
		}
		if (null != user.getDicvCountry()) {
			userDetails.setCountryId(user.getDicvCountry().getCountryId());
			userDetails.setCountryCode(user.getDicvCountry().getCountryCode());
			userDetails.setPhoneCode(user.getDicvCountry().getPhoneCode());
		}
		if (null != user.getDicvCompany()) {
			userDetails.setCompanyId(user.getDicvCompany().getCompanyId());
			userDetails.setCompanyName(user.getDicvCompany().getCompanyName());
			userDetails.setCompanyAddress(user.getDicvCompany().getCompanyAddress());
		}
		userDetails.setStateName(user.getState());
	}

	public UserDto getUserDetail(Integer userId) throws DataNotFoundException, ServerException {
		DicvUser dicvUser = new DicvUser();
		List<DicvUser> userList = null;
		userList = userRepo.getUserDetails(userId, UserStatus.DELETED.getRecordStatusCode());
		if (userList != null && !userList.isEmpty()) {
			dicvUser = userList.get(0);
			UserDto userDto = new UserDto();
			setUser(dicvUser, userDto, true);
			return userDto;
		}
		throw new DataNotFoundException(DicvConstants.DATA_NOT_FOUND_MSG);
	}

	private void setUser(DicvUser dicvUser, UserDto userDto, boolean isPhotoRequired) {
		try {
			userDto.setFirstName(dicvUser.getFirstName());
			userDto.setLastName(dicvUser.getLastName());
			userDto.setUserId(dicvUser.getUserId());
			userDto.setUserName(dicvUser.getUserName());
			userDto.setCustomerAdminId(dicvUser.getManagerId());
			userDto.setRole(dicvUser.getUserType().getUserType());
			userDto.setRoleId(dicvUser.getUserType().getUserTypeId());
			userDto.setCityName(dicvUser.getCity());
			userDto.setStateName(dicvUser.getState());
			userDto.setAddressLine1(dicvUser.getAddressLine1());
			userDto.setZipCode(dicvUser.getZipCode());

			if (null != dicvUser.getDicvCountry()) {
				userDto.setCountryId(dicvUser.getDicvCountry().getCountryId());
				userDto.setPhoneCode(dicvUser.getDicvCountry().getPhoneCode());
				userDto.setCountryCode(dicvUser.getDicvCountry().getCountryCode());
			}

			if (null != dicvUser.getDicvCompany()) {
				userDto.setCompanyId(dicvUser.getDicvCompany().getCompanyId());
				userDto.setCompanyAddress(dicvUser.getDicvCompany().getCompanyAddress());
				userDto.setCompanyName(dicvUser.getDicvCompany().getCompanyName());
			}
			userDto.setDrivingLicenseNo(dicvUser.getDrivingLicenseNo());
			userDto.setEmailId(dicvUser.getEmail());
			userDto.setMobileNumber(dicvUser.getMobile());
			userDto.setEmergencyContact1(dicvUser.getEmergencyContactno1());
			userDto.setEmergencyContact2(dicvUser.getEmergencyContactno2());
			userDto.setLandlineno(dicvUser.getLandlineno());
			userDto.setCreatedOrUpdatedByUserId(dicvUser.getCreatedBy());
			if (null != dicvUser.getDicvGroup()) {
				userDto.setGroupId(dicvUser.getDicvGroup().getGroupId());
				userDto.setGroupName(dicvUser.getDicvGroup().getGroupName());
			}
			if (null != dicvUser.getLicenseExpireDate()) {
				userDto.setLicenseExpireDate(dicvUser.getLicenseExpireDate().toString());
			}
			if (DicvUtil.isValidAttribute(dicvUser.getAddressLine1())) {
				userDto.setCompanyAddress(dicvUser.getAddressLine1());
			}
			if (isPhotoRequired && null != dicvUser.getPhoto() && null != dicvUser.getPhoto().getImage()) {
				userDto.setImageSource(new String(Base64.encodeBase64(dicvUser.getPhoto().getImage())));
			}
		} catch (Exception e) {
			LOGGER.info("Error IN Setting USer Details ", e);
			throw new ServerException();
		}

	}

	public UsersSelectedDto getSelectedUsers(String userType, Integer managerId, Integer userId) {
		UsersSelectedDto usersSelected = new UsersSelectedDto();
		List<UserSelectedDto> users = null;
		if (!userType.equals(EnumUserType.DRIVER.getUserType())) {
			if (managerId == null) {
				users = userRepo.getSelectedUsers(userType, UserStatus.DELETED.getRecordStatusCode());
			} else {
				users = userRepo.getSelectedUsers(managerId, userType, UserStatus.DELETED.getRecordStatusCode());
			}
		} else {
			if (managerId == null) {
				users = userRepo.getDriverSelectedUsers(userType, UserStatus.DELETED.getRecordStatusCode());
			} else {
				users = userRepo.getDriverSelectedUsers(managerId, userType, UserStatus.DELETED.getRecordStatusCode());
			}
		}

		if (null == users || users.isEmpty()) {
			usersSelected.setStatus(HttpServletResponse.SC_NO_CONTENT);
			usersSelected.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			return usersSelected;
		}
		usersSelected.setStatus(HttpServletResponse.SC_OK);
		usersSelected.setMessage(DicvConstants.DATA_FOUND_MSG);
		usersSelected.setUsers(users);
		return usersSelected;
	}

	public DriverDtlsListDto getActiveDrivers(Integer userId) {

		DriverDtlsListDto driverDtlsListDto = new DriverDtlsListDto();
		DicvUser dicvUser = getUser(userId, "getActiveDrivers");
		List<DicvUser> driverUser = getUserByRole(EnumUserType.DRIVER.getUserType(), userId,
				dicvUser.getUserType().getUserType());

		if (driverUser != null && driverUser.size() > 0) {
			List<DriverInfoDto> driverInfoDto = new ArrayList<DriverInfoDto>();
			DriverInfoDto driver = new DriverInfoDto();
			for (DicvUser user : driverUser) {
				driver = new DriverInfoDto();
				driver.setDriverId(user.getUserId());
				if (user.getDicvGroup() != null)
					driver.setGroupName(user.getDicvGroup().getGroupName());
				driver.setMobileNumber(user.getMobile());
				driver.setName(user.getUserName());
				driverInfoDto.add(driver);
			}
			driverDtlsListDto.setDrivers(driverInfoDto);
			driverDtlsListDto.setStatus(HttpServletResponse.SC_OK);
			driverDtlsListDto.setMessage(DicvConstants.DATA_FOUND_MSG);
		} else {
			LOGGER.info("User Details :: " + dicvUser.getUserId() + " " + dicvUser.getUserType().getUserType());
			driverDtlsListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			driverDtlsListDto.setMessage("No Driver Found For User");
		}
		return driverDtlsListDto;
	}

	public List<DicvUser> getUserByRole(String role, Integer userId, String loggedInUserRole) {
		List<DicvUser> userList = null;
		if (EnumUserType.ROOTADMIN.getUserType().equals(loggedInUserRole)) {
			userList = userRepo.getUserByRole(role, UserStatus.DELETED.getRecordStatusCode());
		} else if (EnumUserType.CUSTOMERADMIN.getUserType().equals(loggedInUserRole)) {
			userList = userRepo.getUserByRole(userId, role, UserStatus.DELETED.getRecordStatusCode());
		}
		return userList;
	}

	public UsersDto getUserDtoList(String role, Integer userId, String loggedInUserRole) {
		UsersDto usersDto = new UsersDto();
		List<DicvUser> userList = null;
		if (EnumUserType.ROOTADMIN.getUserType().equals(loggedInUserRole)) {
			userList = userRepo.getUserByRole(role, UserStatus.DELETED.getRecordStatusCode());
		} else if (EnumUserType.CUSTOMERADMIN.getUserType().equals(loggedInUserRole)) {
			userList = userRepo.getUserByRole(userId, role, UserStatus.DELETED.getRecordStatusCode());
		}
		if (userList == null || userList.isEmpty())
			return null;
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		UserDto userDto = null;
		if (role.equals(EnumUserType.DRIVER.getUserType())) {
			for (DicvUser user : userList) {
				userDto = new UserDto();
				userDto.setUserName(user.getUserName());
				userDto.setDrivingLicenseNo(user.getDrivingLicenseNo());
				if (user.getLicenseExpireDate() != null)
					userDto.setLicenseExpireDate(user.getLicenseExpireDate().toString());
				userDto.setMobileNumber(user.getMobile());
				userDto.setEmailId(user.getEmail());
				if (user.getDicvGroup() != null)
					userDto.setGroupName(user.getDicvGroup().getGroupName());
				userDto.setCityName(user.getCity());
				userDto.setStateName(user.getState());
				userDtoList.add(userDto);
			}
		} else if (role.equals(EnumUserType.CUSTOMERADMIN.getUserType())) {
			for (DicvUser user : userList) {
				userDto = new UserDto();
				userDto.setFirstName(user.getFirstName());
				userDto.setLastName(user.getLastName());
				if (user.getDicvCompany() != null) {
					userDto.setCompanyId(user.getDicvCompany().getCompanyId());
					userDto.setCompanyName(user.getDicvCompany().getCompanyName());
					userDto.setCompanyAddress(user.getDicvCompany().getCompanyAddress());
				}
				userDto.setMobileNumber(user.getMobile());
				userDto.setEmailId(user.getEmail());
				userDto.setCityName(user.getCity());
				userDto.setStateName(user.getState());
				userDtoList.add(userDto);
			}

		}
		usersDto.setUsers(userDtoList);
		return usersDto;
	}

	@Transactional
	public void forgotPassword(DicvUser dicvUser) {
		String userpwd = DicvUtil.generatePassword();
		dicvUser.setUserPassword(PasswordEncoder.encryptPassword(userpwd));
		dicvUser.setModifiedDate(DicvUtil.getTimestamp());
		Long versionNo = dicvUser.getVersionNo();
		dicvUser.setChangePassword(1);
		dicvUser.setVersionNo(versionNo + 1);
		userRepo.save(dicvUser);
		boolean isMailSent = sendMail(dicvUser.getEmail(), null, userpwd);
		if (!isMailSent) {
			LOGGER.info("Mail Not Sent " + dicvUser.getUserId());
		}
	}

	@Transactional
	public void resetPassword(Integer userId, String oldPassword, String newPassword) {
		DicvUser user = getUser(userId, "resetPassword");
		if (user != null) {
			user.setUserPassword(PasswordEncoder.encryptPassword(newPassword));
			user.setModifiedDate(DicvUtil.getTimestamp());
			Long versionNo = user.getVersionNo();
			user.setVersionNo(versionNo + 1);
			user.setChangePassword(0);
			userRepo.save(user);
		}

	}

	public UsersDto getUsers(Integer userId, Integer driverId, Integer startRow, Integer endRow) {
		UsersDto usersDto = new UsersDto();
		try {
			DicvUser user = getUser(userId, "Get User UsersDto");
			List<DicvUser> users = new ArrayList<DicvUser>();
			if (driverId == null) {

			} else {
				DicvUser driver = getUser(driverId, "Driver User UsersDto");
				if (user.getUserType().getUserType().equals(EnumUserType.CUSTOMERADMIN.getUserType())) {

					if (user.getUserId() == driver.getManagerId()) {

					}

				} else if (user.getUserType().getUserType().equals(EnumUserType.ROOTADMIN.getUserType())) {

				} else {

				}

			}

			for (DicvUser dicvUser : users) {
				UserDto target = new UserDto();
				BeanUtils.copyProperties(dicvUser, target);
				setUser(dicvUser, target, false);
			}
		} catch (Exception ex) {
		}
		return usersDto;
	}

	public Long userCountBycompanyId(Integer companyId) {

		return userRepo.userCountBycompanyId(companyId);

	}

	public UsersSelectedDto getCustomerByCompany(Integer userId, Integer companyId) {
		UsersSelectedDto usersDto = new UsersSelectedDto();
		try {
			DicvUser user = getUser(userId, "User CustomerByCompany");
			if (user.getUserType().getUserType().equals(EnumUserType.ROOTADMIN.getUserType())) {

				List<UserSelectedDto> users = userRepo.getCustomerByCompany(companyId,
						EnumUserType.CUSTOMERADMIN.getUserType());
				if (users != null) {
					usersDto.setUsers(users);
					usersDto.setMessage(DicvConstants.DATA_FOUND_MSG);
				} else {
					usersDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				}
				usersDto.setStatus(HttpServletResponse.SC_OK);
			} else {
				usersDto.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		} catch (Exception ex) {
			LOGGER.info("CustomerByCompany" + userId);
			usersDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			usersDto.setMessage("No Customer Admin Found For Company");
			return usersDto;
		}
		return usersDto;
	}

	@Transactional
	public DicvUser createDriverForVehicle(Vehicle vehicleEntity) {
		DicvUser user = new DicvUser();
		user.setUserName(vehicleEntity.getRegistrationId());
		user.setCreatedBy(vehicleEntity.getCreatedByUser().getUserId());
		user.setUserType(userTypeRepo.getUserType(EnumUserType.DRIVER.getUserType()));
		user.setFirstName(vehicleEntity.getRegistrationId());
		user.setLastName("");
		if (vehicleEntity.getDicvCompany() != null) {
			user.setDicvCompany(vehicleEntity.getDicvCompany());
		}
		if (vehicleEntity.getDicvGroup() != null) {
			user.setDicvGroup(vehicleEntity.getDicvGroup());
		}
		user.setManagerId(vehicleEntity.getDicvUser().getUserId());
		user.setModifiedDate(DicvUtil.getTimestamp());
		user.setRecordStatus(UserStatus.OPEN.getRecordStatusCode());
		user.setUserStatus("NEW");
		user.setVersionNo(1l);
		user.setChangePassword(1);
		String userpwd = DicvUtil.generatePassword();
		user.setUserPassword(PasswordEncoder.encryptPassword(userpwd));
		userRepo.save(user);
		LOGGER.info("Adding User from  vehicle ");
		return user;
	}

	@Transactional
	public void updateDriverForVehicle(Vehicle vehicleEntity) {
		DicvUser user = getUser(vehicleEntity.getDefaultDriver().getUserId(), "");
		user.setUserName(vehicleEntity.getRegistrationId());
		user.setFirstName(vehicleEntity.getRegistrationId());
		if (vehicleEntity.getDicvCompany() != null) {
			user.setDicvCompany(vehicleEntity.getDicvCompany());
		}
		user.setManagerId(vehicleEntity.getDicvUser().getUserId());
		if (vehicleEntity.getDicvGroup() != null) {
			user.setDicvGroup(vehicleEntity.getDicvGroup());
		}
		user.setModifiedDate(DicvUtil.getTimestamp());
		LOGGER.info("Updating  User from  vehicle ");
		userRepo.save(user);
	}

	public Long userCountByGroupId(Integer groupId) {
		return userRepo.userCountByGroupId(groupId);
	}
}
