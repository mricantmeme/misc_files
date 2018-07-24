package com.dicv.truck.exception;

public class UnAuthorizedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8735124592684898358L;

	public UnAuthorizedException() {

	}

	public UnAuthorizedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public UnAuthorizedException(String message, Throwable cause) {
		super(message, cause);

	}

	public UnAuthorizedException(String message) {
		super(message);

	}

	public UnAuthorizedException(Throwable cause) {
		super(cause);

	}

}
