/**
 * 
 */
package com.dicv.truck.dto;

import java.util.Date;

/**
 * DTO for generating the geo fence report.
 * 
 * 
 */
public class GeoFenceJrReportDto {

	private Integer userId;
	private String vehicleId;
	private String geoFenceId;
	private Date fromDate;
	private Date toDate;

	public GeoFenceJrReportDto() {

	}

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

	public String getGeoFenceId() {
		return geoFenceId;
	}

	public void setGeoFenceId(String geoFenceId) {
		this.geoFenceId = geoFenceId;
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

}
