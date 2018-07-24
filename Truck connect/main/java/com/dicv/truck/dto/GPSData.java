package com.dicv.truck.dto;

import java.sql.Timestamp;

public class GPSData {
	private String Location;

	private Double Latitude;

	private Double Longitude;

	private Integer Speed;

	private Long Altitude;

	private Character GPSStatus;

	private Long Satellites;

	private String NetworkInfo;

	private String GPSDateTime;

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public Double getLatitude() {
		return Latitude;
	}

	public void setLatitude(Double latitude) {
		Latitude = latitude;
	}

	public Double getLongitude() {
		return Longitude;
	}

	public void setLongitude(Double longitude) {
		Longitude = longitude;
	}

	public Integer getSpeed() {
		return Speed;
	}

	public void setSpeed(Integer speed) {
		Speed = speed;
	}

	public Long getAltitude() {
		return Altitude;
	}

	public void setAltitude(Long altitude) {
		Altitude = altitude;
	}

	public Character getGPSStatus() {
		return GPSStatus;
	}

	public void setGPSStatus(Character gPSStatus) {
		GPSStatus = gPSStatus;
	}

	public Long getSatellites() {
		return Satellites;
	}

	public void setSatellites(Long satellites) {
		Satellites = satellites;
	}

	public String getNetworkInfo() {
		return NetworkInfo;
	}

	public void setNetworkInfo(String networkInfo) {
		NetworkInfo = networkInfo;
	}

	public String getGPSDateTime() {
		return GPSDateTime;
	}

	public void setGPSDateTime(String gPSDateTime) {
		GPSDateTime = gPSDateTime;
	}

}