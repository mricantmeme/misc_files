package com.dicv.truck.utility;

public enum UserStatus {

	OPEN("o"), CLOSED("c"), DELETED("d"), NEW("n");

	private String statusCode;

	UserStatus(String statusCode) {
		this.statusCode = statusCode;

	}

	public String getRecordStatusCode() {
		return statusCode;
	}

}
