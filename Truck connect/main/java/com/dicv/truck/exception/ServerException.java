package com.dicv.truck.exception;

public class ServerException extends RuntimeException {

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
	public ServerException(String exceptionMessage) {
		super(exceptionMessage);
		this.exceptionMessage = exceptionMessage;
	}

	public ServerException() {
		super();
	}

}
