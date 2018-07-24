package com.dicv.truck.dto;

public class NightDrivingReportDto {

	private Integer vehicleId;

	private String registrationId;

	private Double totalDistance;

	private Long totalDuration;

	private String duration;

	public NightDrivingReportDto(Integer vehicleId, Long totalDuration, Double totalDistance) {
		super();
		this.vehicleId = vehicleId;
		this.totalDistance = totalDistance;
		this.totalDuration = totalDuration;
	}

	public NightDrivingReportDto() {
		super();
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public Double getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(Double totalDistance) {
		this.totalDistance = totalDistance;
	}

	public Long getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(Long totalDuration) {
		this.totalDuration = totalDuration;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}
