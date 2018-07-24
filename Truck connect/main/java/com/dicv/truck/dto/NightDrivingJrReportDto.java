/**
 * 
 */
package com.dicv.truck.dto;

import java.util.Date;
import java.util.List;

/**
 * This is the DTO for generating the night driving report.
 * 
 * @author ant9kor
 * 
 */
public class NightDrivingJrReportDto {

	private Integer userId;
	private List<Integer> vehicleIds;
	private Date fromDate;
	private Date toDate;
	private Integer startTime;
	private Integer endTime;

	public NightDrivingJrReportDto() {

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

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public List<Integer> getVehicleIds() {
		return vehicleIds;
	}

	public void setVehicleIds(List<Integer> vehicleIds) {
		this.vehicleIds = vehicleIds;
	}

}
