package com.dicv.truck.exception;

/**
 * The class is responsible for
 * 
 * @author AUT7KOR
 * @version 1.0 30/10/2015
 * 
 */

public class InvalidIdException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public InvalidIdException(String message) {
		super(message);
		this.message = message;

	}

}
