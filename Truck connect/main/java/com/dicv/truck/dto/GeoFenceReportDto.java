package com.dicv.truck.dto;

import java.util.Date;

public class GeoFenceReportDto {

	public GeoFenceReportDto() {
		super();
	}

	private String ownerName;
	private Date fromDate;
	private Date toDate;
	private Integer vehicleId;
	private String geoFenceName;
	private Integer geoFenceId;
	private Date geoFenceEntryTime;
	private Date geoFenceExitTime;
	private Long timeSpent;
	private Integer userId;
	private String registrationId;
	private String entryTime;
	private String exitTime;
	private String timeInhrs;

	public GeoFenceReportDto(String geoFenceName, String registrationId, Integer userId, Date geoFenceEntryTime,
			Date geoFenceExitTime, Long timeSpent) {

		this.geoFenceName = geoFenceName;
		this.userId = userId;
		this.geoFenceEntryTime = geoFenceEntryTime;
		this.geoFenceExitTime = geoFenceExitTime;
		this.timeSpent = timeSpent;
		this.registrationId = registrationId;
	}
	
	public GeoFenceReportDto(String geoFenceName, String registrationId, Integer userId, String entryTime,
			String exitTime, Long timeSpent) {

		this.geoFenceName = geoFenceName;
		this.userId = userId;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
		this.timeSpent = timeSpent;
		this.registrationId = registrationId;
	}
	
	public GeoFenceReportDto(String geoFenceName, String registrationId, Integer userId, String entryTime,
			String exitTime, String timeInhrs) {

		this.geoFenceName = geoFenceName;
		this.userId = userId;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
		this.timeInhrs = timeInhrs;
		this.registrationId = registrationId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getGeoFenceName() {
		return geoFenceName;
	}

	public void setGeoFenceName(String geoFenceName) {
		this.geoFenceName = geoFenceName;
	}

	public Integer getGeoFenceId() {
		return geoFenceId;
	}

	public void setGeoFenceId(Integer geoFenceId) {
		this.geoFenceId = geoFenceId;
	}


	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getGeoFenceEntryTime() {
		return geoFenceEntryTime;
	}

	public void setGeoFenceEntryTime(Date geoFenceEntryTime) {
		this.geoFenceEntryTime = geoFenceEntryTime;
	}

	public Date getGeoFenceExitTime() {
		return geoFenceExitTime;
	}

	public void setGeoFenceExitTime(Date geoFenceExitTime) {
		this.geoFenceExitTime = geoFenceExitTime;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getExitTime() {
		return exitTime;
	}

	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}

	public Long getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(Long timeSpent) {
		this.timeSpent = timeSpent;
	}

	public String getTimeInhrs() {
		return timeInhrs;
	}

	public void setTimeInhrs(String timeInhrs) {
		this.timeInhrs = timeInhrs;
	}

}
