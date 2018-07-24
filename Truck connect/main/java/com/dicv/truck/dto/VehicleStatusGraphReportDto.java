/**
 * 
 */
package com.dicv.truck.dto;

import java.util.Date;

/**
 * @author IMT5KOR
 * 
 */
public class VehicleStatusGraphReportDto {

	private Integer vehicleId;

	private Date gps_time;

	private Integer gps_spkm;

	private Date gps_date;

	public VehicleStatusGraphReportDto() {

	}

	/*
	 * public VehicleStatusGraphReportDto(Integer vehicleId, Date gps_time,
	 * Double gps_spkm, Date gps_date) {
	 * 
	 * this.vehicleId = vehicleId; this.gps_time = gps_time; this.gps_spkm =
	 * gps_spkm; this.gps_date = gps_date; }
	 */

	public VehicleStatusGraphReportDto(Integer vehicleId, Date gps_time, Integer gps_spkm, Date gps_date) {
		this.vehicleId = vehicleId;
		this.gps_time = gps_time;
		this.gps_spkm = gps_spkm;
		this.gps_date = gps_date;
	}

	public VehicleStatusGraphReportDto(Date gps_time, Integer gps_spkm, Date gps_date) {

		this.gps_time = gps_time;
		this.gps_spkm = gps_spkm;
		this.gps_date = gps_date;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getGps_date() {
		return gps_date;
	}

	public void setGps_date(Date gps_date) {
		this.gps_date = gps_date;
	}

	public Date getGps_time() {
		return gps_time;
	}

	public void setGps_time(Date gps_time) {
		this.gps_time = gps_time;
	}

	public Integer getGps_spkm() {
		return gps_spkm;
	}

	public void setGps_spkm(Integer gps_spkm) {
		this.gps_spkm = gps_spkm;
	}

	@Override
	public String toString() {
		return "VehicleStatusGraphReportDto [vehicleId=" + vehicleId + ", gps_time=" + gps_time + ", gps_spkm="
				+ gps_spkm + ", gps_date=" + gps_date + "]";
	}

}
