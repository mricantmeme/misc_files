package com.dicv.truck.dto;

import java.sql.Timestamp;

public class AlertSettingsDto extends StatusMessageDto {

	private Integer userId;

	private Integer alertId;

	private Integer fuelDrop;

	private Integer batteryHealth;
	
	private Long vehicleIdleTime;

	private Integer updatedBy;

	private Timestamp updatedTime;

	public Integer getUserId() {
		return userId;
	}

	public Integer getAlertId() {
		return alertId;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setAlertId(Integer alertId) {
		this.alertId = alertId;
	}

	public Integer getFuelDrop() {
		return fuelDrop;
	}

	public void setFuelDrop(Integer fuelDrop) {
		this.fuelDrop = fuelDrop;
	}

	public Integer getBatteryHealth() {
		return batteryHealth;
	}

	public void setBatteryHealth(Integer batteryHealth) {
		this.batteryHealth = batteryHealth;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Long getVehicleIdleTime() {
		return vehicleIdleTime;
	}

	public void setVehicleIdleTime(Long vehicleIdleTime) {
		this.vehicleIdleTime = vehicleIdleTime;
	}

}
