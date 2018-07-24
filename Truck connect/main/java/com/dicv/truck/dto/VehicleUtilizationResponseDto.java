/**
 * 
 */
package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IMT5KOR
 * 
 */
public class VehicleUtilizationResponseDto {

	public VehicleUtilizationResponseDto(Integer vehicleId, Double vehUtilization, Long totalDrivingTime,
			Double maxSpeed, Double totalDistance, Long upTime) {
		super();
		this.vehicleId = vehicleId;
		this.vehUtilization = vehUtilization;
		this.totalDrivingTime = totalDrivingTime;
		this.maxSpeed = maxSpeed;
		this.totalDistance = totalDistance;
		this.upTime = upTime;
	}

	private String userName;

	private Integer vehicleId;

	// private String vehicleName;

	private String vehicleType;

	private String vehicleDescription;

	private String registrationId;

	private String vehicleImage;

	private String countryCode;

	private String countryName;

	private String date;

	/* vehUtilization in --in percent */
	private Double vehUtilization;

	List<VehicleStatusGraphReportDto> vehicleStatusGraphList = new ArrayList<VehicleStatusGraphReportDto>();

	/* totalDrivingTime in Seconds */
	private Long totalDrivingTime;

	/* maxSpeed in --in kmph */
	private Double maxSpeed;

	/* averageSpeed in --in kmph */
	private Double averageSpeed;

	/* totalDistance in Km */
	private Double totalDistance;

	/* upTime in Seconds */
	private Long upTime;

	public VehicleUtilizationResponseDto() {

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

	public List<VehicleStatusGraphReportDto> getVehicleStatusGraphList() {
		return vehicleStatusGraphList;
	}

	public void setVehicleStatusGraphList(List<VehicleStatusGraphReportDto> vehicleStatusGraphList) {
		this.vehicleStatusGraphList = vehicleStatusGraphList;
	}

	/*
	 * public String getVehicleName() { return vehicleName; }
	 * 
	 * public void setVehicleName(String vehicleName) { this.vehicleName =
	 * vehicleName; }
	 */

	public String getRegistrationId() {
		return registrationId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getVehicleImage() {
		return vehicleImage;
	}

	public void setVehicleImage(String vehicleImage) {
		this.vehicleImage = vehicleImage;
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

	public String getVehicleDescription() {
		return vehicleDescription;
	}

	public void setVehicleDescription(String vehicleDescription) {
		this.vehicleDescription = vehicleDescription;
	}

	public Double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Double getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(Double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public Double getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(Double totalDistance) {
		this.totalDistance = totalDistance;
	}

	public Long getUpTime() {
		return upTime;
	}

	public void setUpTime(Long upTime) {
		this.upTime = upTime;
	}

	public Long getTotalDrivingTime() {
		return totalDrivingTime;
	}

	public void setTotalDrivingTime(Long totalDrivingTime) {
		this.totalDrivingTime = totalDrivingTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
