/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.devices.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tcl.devices.response.Response;


/*
 * User defined exception to throw an error.
 */
@ControllerAdvice
public class NoDataFoundHandler {

	/*
	 * This method throws an error whenever it gets called the error message gets
	 * passes to it in the method call the message gets returned to it. Thus
	 * creating a new user defined exception.
	 */
	@ExceptionHandler(NoDataFoundException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody

	public Response noDataFound(NoDataFoundException exception) {
		Response response = new Response();
		response.setError(true);
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.setMessage(exception.getMessage());
		return response;
	}
}
