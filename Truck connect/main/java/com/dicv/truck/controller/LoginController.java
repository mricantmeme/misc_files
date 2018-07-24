package com.dicv.truck.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dicv.truck.dto.LoginDetails;
import com.dicv.truck.dto.LoginResponseDto;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.exception.ServerException;
import com.dicv.truck.exception.UnAuthorizedException;
import com.dicv.truck.service.LoginService;


@RestController
@RequestMapping("/")
public class LoginController {

	private static final Logger LOGGER = Logger.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@PostMapping("login")
	public @ResponseBody LoginResponseDto login(@RequestBody() LoginDetails login)
			throws ServerException, UnAuthorizedException {
		LOGGER.info("User Login API " + login.getUsername());
		return loginService.login(login.getUsername(), login.getPassword());
	}

	@PostMapping("logout")
	public @ResponseBody StatusMessageDto logout() throws ServerException, UnAuthorizedException {
		StatusMessageDto statusMessage = new StatusMessageDto();
		statusMessage.setStatus(HttpServletResponse.SC_OK);
		statusMessage.setMessage("You have been logged out successfully from DICVAPP");
		return statusMessage;
	}

}
