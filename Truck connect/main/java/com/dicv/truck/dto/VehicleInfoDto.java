package com.dicv.truck.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class VehicleInfoDto {
	public VehicleInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Integer vehicleId;
	private String registrationId;
	private String description;
	private Integer groupId;

	public VehicleInfoDto(Integer vehicleId, String registrationId, String description) {
		super();
		this.vehicleId = vehicleId;
		this.registrationId = registrationId;
		this.description = description;
	}
	
	public VehicleInfoDto(Integer vehicleId, String registrationId, Integer groupId) {
		super();
		this.vehicleId = vehicleId;
		this.registrationId = registrationId;
		this.groupId = groupId;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "VehicleInfoDto [vehicleId=" + vehicleId + ", registrationId=" + registrationId + ", description="
				+ description + "]";
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}
