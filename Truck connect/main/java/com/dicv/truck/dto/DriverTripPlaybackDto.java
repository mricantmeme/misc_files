package com.dicv.truck.dto;

import java.util.Date;

/**
 * @author aut7kor
 * 
 */
public class DriverTripPlaybackDto {

	public DriverTripPlaybackDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DriverTripPlaybackDto(Integer gpsSpkm, Date gpsTime, Double gpsLatitude, Double gpsLongitude) {
		super();
		this.gpsLatitude = gpsLatitude;
		this.gpsLongitude = gpsLongitude;
		this.gpsSpkm = gpsSpkm;
		this.gpsTime = gpsTime;
	}

	private Double gpsLatitude;
	private Double gpsLongitude;
	private Integer gpsSpkm;
	private String stopDuration;
	private Date gpsTime;

	public Double getGpsLatitude() {
		return gpsLatitude;
	}

	public void setGpsLatitude(Double gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	public Double getGpsLongitude() {
		return gpsLongitude;
	}

	public void setGpsLongitude(Double gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	public Integer getGpsSpkm() {
		return gpsSpkm;
	}

	public void setGpsSpkm(Integer gpsSpkm) {
		this.gpsSpkm = gpsSpkm;
	}

	public String getStopDuration() {
		return stopDuration;
	}

	public void setStopDuration(String stopDuration) {
		this.stopDuration = stopDuration;
	}

	public Date getGpsTime() {
		return gpsTime;
	}

	public void setGpsTime(Date gpsTime) {
		this.gpsTime = gpsTime;
	}

}
