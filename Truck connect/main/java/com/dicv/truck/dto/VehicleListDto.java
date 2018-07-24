package com.dicv.truck.dto;

import java.io.Serializable;

public class VehicleListDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2078075196304496312L;

	private Integer vehicleId;

	private Long gpsImei;

	private String registrationId;

	private String email;

	private String groupName;
	

	public VehicleListDto(Integer vehicleId, String registrationId, Long gpsImei, String groupName) {
		super();
		this.vehicleId = vehicleId;
		this.gpsImei = gpsImei;
		this.registrationId = registrationId;
		this.groupName = groupName;
	}
	
	
	public VehicleListDto(Integer vehicleId, String registrationId, Long gpsImei) {
		super();
		this.vehicleId = vehicleId;
		this.gpsImei = gpsImei;
		this.registrationId = registrationId;
	}

	public VehicleListDto(Integer vehicleId, String registrationId) {
		super();
		this.vehicleId = vehicleId;
		this.registrationId = registrationId;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public VehicleListDto(Integer vehicleId, Long gpsImei, String registrationId, String email) {
		super();
		this.vehicleId = vehicleId;
		this.gpsImei = gpsImei;
		this.registrationId = registrationId;
		this.email = email;
	}

	public Long getGpsImei() {
		return gpsImei;
	}

	public void setGpsImei(Long gpsImei) {
		this.gpsImei = gpsImei;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
