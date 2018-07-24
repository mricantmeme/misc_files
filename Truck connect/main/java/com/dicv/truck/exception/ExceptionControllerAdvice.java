/**
 * 
 */
package com.dicv.truck.exception;

import java.sql.SQLException;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.utility.DicvConstants;

/**
 * @author IMT5KOR
 * 
 */

@ControllerAdvice
public class ExceptionControllerAdvice {

	/**
	 * This method handles DataNotFoundException thrown in any controller
	 * 
	 */

	@ExceptionHandler(DataNotFoundException.class)
	public @ResponseBody StatusMessageDto dataNotFoundException(DataNotFoundException e) {

		StatusMessageDto response = new StatusMessageDto();

		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		response.setMessage(e.getMessage());

		return response;
	}

	/**
	 * This method handles PersistenceException thrown in any controller
	 * 
	 */
	@ExceptionHandler(PersistenceException.class)
	public @ResponseBody StatusMessageDto persistenceException(PersistenceException e) {

		StatusMessageDto response = new StatusMessageDto();

		response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
		response.setMessage(DicvConstants.PERSISTENCE_EXCEPTION_MSG);
		response.setDescription(e.getMessage());

		return response;
	}

	/**
	 * This method handles InvalidValueException thrown in any controller
	 * 
	 */
	@ExceptionHandler(InvalidValueException.class)
	public @ResponseBody StatusMessageDto invalidValueException(InvalidValueException e) {

		StatusMessageDto response = new StatusMessageDto();

		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.setMessage(e.getMessage());

		return response;
	}

	/**
	 * This method handles InvalidValueException thrown in any controller
	 * 
	 */
	@ExceptionHandler(DataAlreadyExistsException.class)
	public @ResponseBody StatusMessageDto dataAlreadyExistsException(DataAlreadyExistsException e) {

		StatusMessageDto response = new StatusMessageDto();

		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.setMessage(e.getMessage());

		return response;
	}

	/**
	 * This method handles HibernateExceptions thrown in any controller
	 * 
	 */
	@ExceptionHandler(HibernateException.class)
	public @ResponseBody StatusMessageDto hibernateException(HibernateException e) {

		StatusMessageDto response = new StatusMessageDto();

		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.setMessage(DicvConstants.HIBERNATE_EXCEPTION_MSG);
		response.setDescription(e.getMessage());

		return response;
	}

	/**
	 * This method handles all exceptions other than the above mentioned exceptions
	 * thrown in any controller
	 * 
	 */
	@ExceptionHandler(Exception.class)
	public @ResponseBody StatusMessageDto exception(Exception e) {

		StatusMessageDto response = new StatusMessageDto();

		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		response.setDescription(e.getMessage());

		return response;
	}

	/**
	 * This method handles all Throwable Exception/Errors other than the above
	 * mentioned exceptions thrown in any controller
	 * 
	 */
	@ExceptionHandler(Throwable.class)
	public @ResponseBody StatusMessageDto throwable(Throwable e) {

		StatusMessageDto response = new StatusMessageDto();

		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.setMessage(e.getMessage());

		return response;
	}

	/**
	 * This method handles SQLException thrown in any controller
	 * 
	 */
	@ExceptionHandler(SQLException.class)
	public @ResponseBody StatusMessageDto sqlException(SQLException e) {

		StatusMessageDto response = new StatusMessageDto();

		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		response.setDescription(e.getMessage());

		return response;
	}

	/**
	 * This method handles all Server exceptions thrown in any controller
	 * 
	 */
	@ExceptionHandler(ServerException.class)
	public @ResponseBody StatusMessageDto serverException(ServerException e) {

		StatusMessageDto response = new StatusMessageDto();

		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
		response.setDescription(e.getMessage());

		return response;
	}

	/**
	 * This method handles all UnAuthorizedExceptions thrown in any controller
	 * 
	 */
	@ExceptionHandler(UnAuthorizedException.class)
	public @ResponseBody StatusMessageDto unAuthorizedException(UnAuthorizedException e) {

		StatusMessageDto response = new StatusMessageDto();

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setMessage(e.getMessage());

		return response;
	}

}
