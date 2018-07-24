package com.dicv.truck.dto;

public class VehicleCurrentStatus {
	private Integer vehicleId;
	private String registrationId;
	private String status;
	private String description;
	private Long gpsImei;
	private Double currentLat;
	private Double currentLong;
	private String regionName;
	private String vehicleType;

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getCurrentLat() {
		return currentLat;
	}

	public void setCurrentLat(Double currentLat) {
		this.currentLat = currentLat;
	}

	public Double getCurrentLong() {
		return currentLong;
	}

	public void setCurrentLong(Double currentLong) {
		this.currentLong = currentLong;
	}

	public Long getGpsImei() {
		return gpsImei;
	}

	public void setGpsImei(Long gpsImei) {
		this.gpsImei = gpsImei;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public String toString() {
		return "VehicleCurrentStatus [vehicleId=" + vehicleId
				+ ", registrationId=" + registrationId + ", status=" + status
				+ ", description=" + description + ", gpsImei=" + gpsImei
				+ ", currentLat=" + currentLat + ", currentLong=" + currentLong
				+ ", regionName=" + regionName + ", vehicleType=" + vehicleType
				+ "]";
	}

}
