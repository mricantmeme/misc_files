package com.dicv.truck.utility;

public enum DriverStatus {

	AVAILABLE("AVAILABLE"), ASSIGNED("ASSIGNED"), BUSY("BUSY"), NOTAVAILABLE("NOTAVAILABLE");

	private String statusCode;

	DriverStatus(String statusCode) {
		this.statusCode = statusCode;

	}

	public String getStatusCode() {
		return statusCode;
	}

}
