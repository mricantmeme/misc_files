package com.dicv.truck.dto;

import java.util.Date;
import java.util.List;

public class VehicleSummaryDto {

	private Integer userId;
	private List<Integer> vehicleIds;
	private Date fromDate;
	private Date toDate;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<Integer> getVehicleIds() {
		return vehicleIds;
	}
	public void setVehicleIds(List<Integer> vehicleIds) {
		this.vehicleIds = vehicleIds;
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
