package com.dicv.truck.dto;

public class SpeedReportDto {

	private Integer vehicleId;

	private Double speedDistance;

	private Long speedDuration;

	private Double totalDistance;

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public SpeedReportDto(Integer vehicleId,Long speedDuration, Double speedDistance,
			Double totalDistance) {
		super();
		this.vehicleId = vehicleId;
		this.speedDistance = speedDistance;
		this.speedDuration = speedDuration;
		this.totalDistance = totalDistance;
	}



	public Double getSpeedDistance() {
		return speedDistance;
	}

	public void setSpeedDistance(Double speedDistance) {
		this.speedDistance = speedDistance;
	}

	public Long getSpeedDuration() {
		return speedDuration;
	}

	public void setSpeedDuration(Long speedDuration) {
		this.speedDuration = speedDuration;
	}

	public Double getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(Double totalDistance) {
		this.totalDistance = totalDistance;
	}

}
