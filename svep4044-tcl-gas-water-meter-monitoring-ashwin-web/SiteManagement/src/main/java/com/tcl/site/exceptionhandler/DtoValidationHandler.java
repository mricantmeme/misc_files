/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.exceptionhandler;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tcl.site.response.Response;

@ControllerAdvice
public class DtoValidationHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Response validationHandler(MethodArgumentNotValidException exception) {
		Response response = new Response();
		response.setError(true);
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.setMessage(exception.getBindingResult().getFieldError().getDefaultMessage());
		return response;
	}
}
