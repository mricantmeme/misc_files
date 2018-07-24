package com.dicv.truck.dto;

import java.io.Serializable;

public class DeviceHealthReportDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6484006975955280693L;

	/**
	 * 
	 */
	
	private Long gpsImei;
	
	private String registrationId;
	
	private String groupName;
	
	private String vinNumber;
	
	private String gpsTime;
	
	private String vehicleLastUpdateON;
	
	private Integer signalStrength;
	
	private Double deviceBattery;
	
	private Double gpsVolt;

	public Long getGpsImei() {
		return gpsImei;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getVinNumber() {
		return vinNumber;
	}

	public Integer getSignalStrength() {
		return signalStrength;
	}

	public void setGpsImei(Long gpsImei) {
		this.gpsImei = gpsImei;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setVinNumber(String vinNumber) {
		this.vinNumber = vinNumber;
	}

	public void setSignalStrength(Integer signalStrength) {
		this.signalStrength = signalStrength;
	}

	public Double getDeviceBattery() {
		return deviceBattery;
	}

	public void setDeviceBattery(Double deviceBattery) {
		this.deviceBattery = deviceBattery;
	}

	public String getGpsTime() {
		return gpsTime;
	}

	public void setGpsTime(String gpsTime) {
		this.gpsTime = gpsTime;
	}

	public String getVehicleLastUpdateON() {
		return vehicleLastUpdateON;
	}

	public void setVehicleLastUpdateON(String vehicleLastUpdateON) {
		this.vehicleLastUpdateON = vehicleLastUpdateON;
	}

	public Double getGpsVolt() {
		return gpsVolt;
	}

	public void setGpsVolt(Double gpsVolt) {
		this.gpsVolt = gpsVolt;
	}


	
	
	
	
	
	

}
