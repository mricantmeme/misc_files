package com.dicv.truck.dto;

/**
 * Hold the data required to display in alert report.
 * 
 * @author seg3kor
 * 
 */
public class AlertsReportJrDto {

	private String alertType;

	private String dateTime;

	private String mapUrl;

	private String registrationId;

	private String location;

	public AlertsReportJrDto(String registrationId, String alertType, String mapUrl, String dateTime) {
		this.alertType = alertType;
		this.dateTime = dateTime;
		this.mapUrl = mapUrl;
		this.registrationId = registrationId;
	}
	
	public AlertsReportJrDto(String registrationId, String alertType, String mapUrl, String dateTime,String location) {
		this.alertType = alertType;
		this.dateTime = dateTime;
		this.mapUrl = mapUrl;
		this.registrationId = registrationId;
		this.location = location;
	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getMapUrl() {
		return mapUrl;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
