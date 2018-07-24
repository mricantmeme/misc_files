package com.dicv.truck.dto;

import java.io.Serializable;

public class VehicleDashboardResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;

	private Integer vehicleId;

	private String vehicleName;

	private String vin;
	
	private String group;

	private String customerName;

	private String registrationId;

	private String countryCode;

	private String countryName;

	/* vehUtilization in --in percent */
	private Double vehUtilization;

	public VehicleDashboardResponseDto() {

	}

	public VehicleDashboardResponseDto(String userName, Integer vehicleId, String vehicleName, String registrationId,
			String countryName, String countryCode, Double vehUtilization, String vin, String customerName) {

		this.userName = userName;
		this.vehicleId = vehicleId;
		this.vehicleName = vehicleName;
		this.registrationId = registrationId;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.vehUtilization = vehUtilization;
		this.vin = vin;
		this.customerName = customerName;
	}

	public VehicleDashboardResponseDto(Integer vehicleId, Double vehUtilization) {
		this.vehicleId = vehicleId;
		this.vehUtilization = vehUtilization;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Double getVehUtilization() {
		return vehUtilization;
	}

	public void setVehUtilization(Double vehUtilization) {
		this.vehUtilization = vehUtilization;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
