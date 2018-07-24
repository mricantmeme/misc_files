package com.dicv.truck.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.exception.ServerException;
import com.dicv.truck.exception.UnAuthorizedException;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.service.UserService;
import com.dicv.truck.token.TokenInterceptor;
import com.dicv.truck.utility.DicvUtil;

@RestController
@RequestMapping("/onlinefleetmngmt")
public class NoAuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenInterceptor tokenInterceptor;

	private static final Logger LOGGER = Logger.getLogger(NoAuthController.class);

	@GetMapping("/tokenValidate")
	public @ResponseBody StatusMessageDto tokenValidate(HttpServletRequest request)
			throws UnAuthorizedException, ServerException {
		StatusMessageDto statusMessage = new StatusMessageDto();
		if (!tokenInterceptor.isValidateToken(request.getHeader("authorization"))) {
			statusMessage.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			statusMessage.setMessage("Token Invalid");
		} else {
			statusMessage.setStatus(HttpServletResponse.SC_OK);
			statusMessage.setMessage("Token is valid");

		}
		return statusMessage;

	}

	@PostMapping("/forgotPassword")
	public @ResponseBody StatusMessageDto forgotPassword(@RequestParam("userName") String userName,
			@RequestParam("emailId") String emailId) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		if (!DicvUtil.isValidAttribute(userName) && !DicvUtil.isValidAttribute(emailId)) {
			statusMessageDto.setMessage("Please provide valid User Name !!!");
			statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return statusMessageDto;
		}
		DicvUser user = userService.getUserByUserName(userName);
		if (user == null) {
			statusMessageDto.setMessage("User Name Not Available");
			statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return statusMessageDto;

		}
		if (!user.getEmail().equalsIgnoreCase(emailId)) {
			statusMessageDto.setMessage("Email Id Not Match");
			statusMessageDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return statusMessageDto;

		}
		userService.forgotPassword(user);
		statusMessageDto.setStatus(HttpServletResponse.SC_OK);
		statusMessageDto.setMessage("The password has been reset and the mail has been sent to user");
		LOGGER.log(Level.INFO, "The password has been reset and the mail has been sent to user" + userName);
		return statusMessageDto;
	}

}
