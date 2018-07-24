/**
 * 
 */
package com.dicv.truck.dto;

import java.util.Date;
import java.util.List;

/**
 * @author IMT5KOR
 * 
 */
public class VehicleUtilizationInputDto {

	private Integer userId;
	private Date fromDate;
	private Date toDate;
	private List<Integer> vehicleIds;
	private Integer startRow;
	private Integer endRow;

	public VehicleUtilizationInputDto() {

	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getEndRow() {
		return endRow;
	}

	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	public List<Integer> getVehicleIds() {
		return vehicleIds;
	}

	public void setVehicleIds(List<Integer> vehicleIds) {
		this.vehicleIds = vehicleIds;
	}

	@Override
	public String toString() {
		return "VehicleUtilizationInputDto [userId=" + userId + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", vehicleIds=" + vehicleIds + ", startRow=" + startRow + ", endRow=" + endRow + "]";
	}



}
