package com.dicv.truck.exception;

public class DataAlreadyExistsException extends ServerException {
	/**
	 * This user defined exception class is responsible to throw exception if
	 * data is exist.
	 * 
	 * @author aut7kor
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public DataAlreadyExistsException(String message)

	{

		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
