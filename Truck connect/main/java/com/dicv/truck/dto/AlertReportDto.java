package com.dicv.truck.dto;

import java.util.Date;

/**
 * Holds data related to alert report.
 * 
 * @author seg3kor
 * 
 */
public class AlertReportDto {

	private Integer userId;
	private String vehicleId;
	private String alertReportType;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getAlertReportType() {
		return alertReportType;
	}

	public void setAlertReportType(String alertReportType) {
		this.alertReportType = alertReportType;
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

	private Date fromDate;
	private Date toDate;

}
