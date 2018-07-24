package com.dicv.truck.dto;

public class OverSpeedReportDto {

	public OverSpeedReportDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Integer vehicleId;

	private Double maxSpeed;

	private Double latitude;

	private Double longitude;
	
	private String location;

	public OverSpeedReportDto(Integer vehicleId, Double maxSpeed, Double latitude, Double longitude) {
		super();
		this.vehicleId = vehicleId;
		this.maxSpeed = maxSpeed;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public OverSpeedReportDto(Integer vehicleId, Double maxSpeed, Double latitude, Double longitude,String location) {
		super();
		this.vehicleId = vehicleId;
		this.maxSpeed = maxSpeed;
		this.latitude = latitude;
		this.longitude = longitude;
		this.location = location;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
