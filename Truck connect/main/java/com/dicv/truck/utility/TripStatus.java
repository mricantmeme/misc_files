package com.dicv.truck.utility;

public enum TripStatus {

	PLANNED("PLANNED"), ASSIGNED("ASSIGNED"), RUNNING("RUNNING"), COMPLETED("COMPLETED"), CANCELED("CANCELED"), PAUSED(
			"PAUSED"), RESTARTED("RESTARTED");

	private String statusCode;

	TripStatus(String statusCode) {
		this.statusCode = statusCode;

	}

	public String getStatusCode() {
		return statusCode;
	}

}
