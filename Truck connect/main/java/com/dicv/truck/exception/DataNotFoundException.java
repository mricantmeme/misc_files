package com.dicv.truck.exception;

/**
 * @author IMT5KOR
 * 
 *         Custom Exception which handles Data not found exceptions thrown by
 *         controller
 * 
 */
public class DataNotFoundException extends ServerException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public DataNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
