package com.dicv.truck.dto;

public class MyFleetVehicleReport {

	private String registrationId;

	private Integer vehicleId;

	private String runningStatus;

	private String currentLocation;

	private String landMark;

	private String latLong;

	private Integer gpsSpkm;

	private String defaultDriver;

	private String gpsTime;
	
	private String mapUrl;

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getRunningStatus() {
		return runningStatus;
	}

	public void setRunningStatus(String runningStatus) {
		this.runningStatus = runningStatus;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getDefaultDriver() {
		return defaultDriver;
	}

	public void setDefaultDriver(String defaultDriver) {
		this.defaultDriver = defaultDriver;
	}

	public String getLatLong() {
		return latLong;
	}

	public void setLatLong(String latLong) {
		this.latLong = latLong;
	}

	public Integer getGpsSpkm() {
		return gpsSpkm;
	}

	public void setGpsSpkm(Integer gpsSpkm) {
		this.gpsSpkm = gpsSpkm;
	}

	public String getGpsTime() {
		return gpsTime;
	}

	public void setGpsTime(String gpsTime) {
		this.gpsTime = gpsTime;
	}

}
