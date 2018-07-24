package com.dicv.truck.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicv.truck.dto.LoginResponseDto;
import com.dicv.truck.exception.ServerException;
import com.dicv.truck.exception.UnAuthorizedException;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.repo.LoginRepo;
import com.dicv.truck.token.PasswordEncoder;
import com.dicv.truck.token.TokenInterceptor;
import com.dicv.truck.utility.UserStatus;

@Service
public class LoginService {

	@Autowired
	private LoginRepo loginRepo;

	@Autowired
	private TokenInterceptor tokenInterceptor;

	private static final Logger LOGGER = Logger.getLogger(LoginService.class);

	public LoginResponseDto login(String userName, String password) throws ServerException, UnAuthorizedException {
		try {
			List<DicvUser> userList = loginRepo.getUserDetailsByUserName(userName,
					UserStatus.DELETED.getRecordStatusCode());
			if (userList != null && !userList.isEmpty()) {
				DicvUser user = userList.get(0);
				if (PasswordEncoder.encryptPassword(password).equals(user.getUserPassword())) {
					List<String> roles = new ArrayList<String>();
					roles.add(user.getUserType().getUserType());
					String token = tokenInterceptor.generateToken(user.getUserId());
					LoginResponseDto response = new LoginResponseDto();
					response.setChangePassword(user.getChangePassword());
					response.setId(user.getUserId());
					response.setRoles(roles);
					response.setStatus(HttpServletResponse.SC_OK);
					response.setToken(token);
					response.setUserName(userName);
					response.setUserStatus("ACTIVE");
					if (user.getUserStatus() != null && user.getUserStatus() == "NEW")
						response.setUserStatus(user.getUserStatus());

					LOGGER.info("User Logged in Successfullly :: " + response);
					return response;
				} else {
					LOGGER.info("Password Validation Failed  :: " + user.getUserId() + "   ::  "
							+ user.getUserPassword() + " :: " + PasswordEncoder.encryptPassword(password));
					throw new UnAuthorizedException("Enter Valid Credentials ");
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Login Exception ", ex);
			throw new UnAuthorizedException("Enter Valid Credentials");
		}
		throw new UnAuthorizedException("Enter Valid Credentials ");
	}

}
