/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.exceptionhandler;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tcl.site.response.Response;

@ControllerAdvice
public class InvalidCredentialsHandler {

	@ExceptionHandler(InvalidCredentialsException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Response invalidCredentials(InvalidCredentialsException exception) {
		Response response = new Response();
		response.setError(true);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setMessage(exception.getMessage());
		return response;
	}
}
