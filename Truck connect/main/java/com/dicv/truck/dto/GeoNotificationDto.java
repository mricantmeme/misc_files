package com.dicv.truck.dto;

import java.util.Calendar;

public class GeoNotificationDto {

	private Integer userId;

	private Integer vehicleId;

	private String message;

	private String type;

	private String status;

	private Calendar receivedDateTime;

	private Calendar readDateTime;

	// private Integer geoFenceId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/*
	 * public Integer getGeoFenceId() { return geoFenceId; }
	 * 
	 * public void setGeoFenceId(Integer geoFenceId) { this.geoFenceId =
	 * geoFenceId; }
	 */

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getReceivedDateTime() {
		return receivedDateTime;
	}

	public void setReceivedDateTime(Calendar receivedDateTime) {
		this.receivedDateTime = receivedDateTime;
	}

	public Calendar getReadDateTime() {
		return readDateTime;
	}

	public void setReadDateTime(Calendar readDateTime) {
		this.readDateTime = readDateTime;
	}

	@Override
	public String toString() {
		return "GeoNotification [userId=" + userId + ", vehicleId=" + vehicleId
				+ ", message=" + message + ", type=" + type + ", status="
				+ status + ", receivedDateTime=" + receivedDateTime
				+ ", readDateTime=" + readDateTime + "]";
	}

}
