package com.dicv.truck.dto;

import java.util.Date;

public class VehicleRequestDto {

	private String VehicleID;

	private Long DeviceID;

	private Integer CustomerID;

	private String Provider;

	private String DateTime;

	private GPSData GPSData;

	public String getVehicleID() {
		return VehicleID;
	}

	public void setVehicleID(String vehicleID) {
		VehicleID = vehicleID;
	}

	public Long getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(Long deviceID) {
		DeviceID = deviceID;
	}

	public Integer getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(Integer customerID) {
		CustomerID = customerID;
	}

	public String getProvider() {
		return Provider;
	}

	public void setProvider(String provider) {
		Provider = provider;
	}

	public String getDateTime() {
		return DateTime;
	}

	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}

	public GPSData getGPSData() {
		return GPSData;
	}

	public void setGPSData(GPSData gPSData) {
		GPSData = gPSData;
	}
}
