package com.dicv.truck.utility;

/**
 * The class is responsible for
 * 
 * @author AUT7KOR
 * @version 1.0 12/11/2015
 * 
 */
public enum VehicleStatus {

	AVAILABLE("AVAILABLE"), RUNNINGONLINE("RUNNINGONLINE"), RUNNINGOFFLINE(
			"RUNNINGOFFLINE"), NOTAVAILABLE("NOTAVAILABLE"), RUNNING("RUNNING"), IDLE(
			"IDLE"), OFFLINE("OFFLINE"), NEW("NEW");

	private String statusCode;

	VehicleStatus(String statusCode) {
		this.statusCode = statusCode;

	}

	public String getStatusCode() {
		return statusCode;
	}

}
