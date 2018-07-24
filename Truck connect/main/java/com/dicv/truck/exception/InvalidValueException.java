package com.dicv.truck.exception;

public class InvalidValueException extends ServerException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Exception Message
	 */
	private String message;

	public InvalidValueException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
