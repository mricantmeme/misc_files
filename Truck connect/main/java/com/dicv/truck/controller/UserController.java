package com.dicv.truck.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dicv.truck.dto.DriverDtlsListDto;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.dto.UserDto;
import com.dicv.truck.dto.UserListDto;
import com.dicv.truck.dto.UsersDto;
import com.dicv.truck.dto.UsersSelectedDto;
import com.dicv.truck.exception.InvalidValueException;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.service.UserService;
import com.dicv.truck.service.ValidationService;
import com.dicv.truck.token.PasswordEncoder;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.DicvUtil;

@RestController
@RequestMapping("/onlinefleetmngmt/dicv")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private ValidationService validationService;

	@PostMapping("/createUser")
	public @ResponseBody StatusMessageDto createUser(@RequestBody UserDto userDto) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		String response = validationService.validationForUserModify(userDto);
		if (response.equals(DicvConstants.SUCCESS)) {
			return userService.saveUser(userDto);
		} else {
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage(response);
		}
		return statusMessageDto;

	}

	@PostMapping("/changePassword")
	public @ResponseBody StatusMessageDto changePassword(@RequestParam Integer userId, @RequestParam String oldPassword,
			@RequestParam String newPassword) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		if (null == userId || userId <= 0) {
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage("Please provide valid userId !!!");
			return statusMessageDto;
		}
		if (!DicvUtil.isValidAttribute(oldPassword) && !DicvUtil.isValidAttribute(newPassword)) {
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage("Please provide valid password !!!");
			return statusMessageDto;
		}
		if (!DicvUtil.isValidPassword(newPassword)) {
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage("Please follow password guidelines," + " must contain a digit,"
					+ " must contain a lower case letter," + " must contain an upper case letter,"
					+ " must contains one special symbols in the list [ @#$% ],"
					+ " length at least 8 characters and maximum of 20");
			LOGGER.info("Voliates password rule !!!");
			return statusMessageDto;
		}
		DicvUser user = userService.getUser(userId, "changePassword");
		if (user == null) {
			statusMessageDto.setMessage("User Not Available");
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return statusMessageDto;

		}
		if (!user.getUserPassword().equalsIgnoreCase(PasswordEncoder.encryptPassword(oldPassword))) {
			statusMessageDto.setMessage("Please Enter Valid Current Password");
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return statusMessageDto;
		}
		userService.resetPassword(userId, oldPassword, newPassword);
		statusMessageDto.setStatus(HttpServletResponse.SC_OK);
		statusMessageDto.setMessage("Password Updated Successfully");
		return statusMessageDto;

	}

	@GetMapping("/getRolesBasedUserList")
	public @ResponseBody UserListDto getRolesBasedUserList(@RequestParam Integer userId, @RequestParam String role,
			@RequestParam Integer rowPerPage, @RequestParam Integer page,
			@RequestParam(required = false) String keyword) {

		if (null == rowPerPage || null == page || page < 1) {
			UserListDto userListDto = new UserListDto();
			userListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			userListDto.setMessage("Kindly enter valid pagination values.");
			return userListDto;

		}
		return userService.getRolesBasedUserList(userId, role, rowPerPage, page, keyword);

	}

	@GetMapping("/getUserDetail")
	public UserDto getUserDetail(@RequestParam("userId") Integer userId) {
		if (null == userId || userId <= 0)
			throw new InvalidValueException(DicvConstants.INVALIDINPUT_EXCEPTION_MSG);
		return userService.getUserDetail(userId);
	}

	@GetMapping("/getSelectedUsers")
	public UsersSelectedDto getSelectedUsers(String role, Integer managerId, Integer userId) {
		return userService.getSelectedUsers(role, managerId, userId);
	}

	@GetMapping("/getUsers")
	public UsersDto getUsers(@RequestParam Integer userId, @RequestParam(required = false) Integer driverId,
			@RequestParam(required = false) Integer startRow, @RequestParam(required = false) Integer endRow) {
		return userService.getUsers(userId, driverId, startRow, endRow);
	}

	@GetMapping("/getCustomerByCompany")
	public UsersSelectedDto getCustomerByCompany(@RequestParam Integer userId, @RequestParam Integer companyId) {
		return userService.getCustomerByCompany(userId, companyId);
	}

	@GetMapping("/getActiveDrivers")
	public DriverDtlsListDto getActiveDrivers(@RequestParam("userId") Integer userId) {
		if (null == userId || userId <= 0)
			throw new InvalidValueException(DicvConstants.INVALIDINPUT_EXCEPTION_MSG);
		return userService.getActiveDrivers(userId);
	}

}
