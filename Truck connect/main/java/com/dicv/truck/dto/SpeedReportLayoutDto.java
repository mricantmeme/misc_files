package com.dicv.truck.dto;

public class SpeedReportLayoutDto {

	private String vehicleName;

	private String speedLimit;

	private String totalDistance;

	private String speedDistance;

	private String speedDuration;

	private String maxSpeed;

	private String mapUrl;
	
	 private String latLong;;

	public SpeedReportLayoutDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SpeedReportLayoutDto(String vehicleName,String speedLimit, String totalDistance, String speedDistance, String speedDuration,
			String maxSpeed, String mapUrl) {
		super();
		this.vehicleName = vehicleName;
		this.speedLimit = speedLimit;
		this.totalDistance = totalDistance;
		this.speedDistance = speedDistance;
		this.speedDuration = speedDuration;
		this.maxSpeed = maxSpeed;
		this.mapUrl = mapUrl;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(String totalDistance) {
		this.totalDistance = totalDistance;
	}

	public String getSpeedDistance() {
		return speedDistance;
	}

	public void setSpeedDistance(String speedDistance) {
		this.speedDistance = speedDistance;
	}

	public String getSpeedDuration() {
		return speedDuration;
	}

	public void setSpeedDuration(String speedDuration) {
		this.speedDuration = speedDuration;
	}

	public String getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(String maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getMapUrl() {
		return mapUrl;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}

	public String getSpeedLimit() {
		return speedLimit;
	}

	public void setSpeedLimit(String speedLimit) {
		this.speedLimit = speedLimit;
	}

	public String getLatLong() {
		return latLong;
	}

	public void setLatLong(String latLong) {
		this.latLong = latLong;
	}

}
