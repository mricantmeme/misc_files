package com.dicv.truck.exception;

public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Exception Message
	 */
	private String exceptionMessage;

	/**
	 * Overloaded Constructor
	 * 
	 * @param exceptionMessage
	 */
	public ValidationException(String exceptionMessage) {
		super(exceptionMessage);
		this.exceptionMessage = exceptionMessage;
	}

}
