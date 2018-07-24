package com.dicv.truck.dto;

public class VehicleSummaryReportVO {

	private String registrationId;
	private String totalDistance;
	private String totalDrivingTime;
	private String maximumSpeed;
	private String averageSpeed;
	private String numberOfStops;
	private String maxIdleTime;
	private String maxIdleLocation;
	private String totalIdleTime;
	private String latLong;

	public String getNumberOfStops() {
		return numberOfStops;
	}

	public void setNumberOfStops(String numberOfStops) {
		this.numberOfStops = numberOfStops;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(String totalDistance) {
		this.totalDistance = totalDistance;
	}

	public String getTotalDrivingTime() {
		return totalDrivingTime;
	}

	public void setTotalDrivingTime(String totalDrivingTime) {
		this.totalDrivingTime = totalDrivingTime;
	}

	public String getMaximumSpeed() {
		return maximumSpeed;
	}

	public void setMaximumSpeed(String maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	public String getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(String averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public String getMaxIdleTime() {
		return maxIdleTime;
	}

	public void setMaxIdleTime(String maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}

	public String getMaxIdleLocation() {
		return maxIdleLocation;
	}

	public void setMaxIdleLocation(String maxIdleLocation) {
		this.maxIdleLocation = maxIdleLocation;
	}

	public String getTotalIdleTime() {
		return totalIdleTime;
	}

	public void setTotalIdleTime(String totalIdleTime) {
		this.totalIdleTime = totalIdleTime;
	}

	public String getLatLong() {
		return latLong;
	}

	public void setLatLong(String latLong) {
		this.latLong = latLong;
	}

}
