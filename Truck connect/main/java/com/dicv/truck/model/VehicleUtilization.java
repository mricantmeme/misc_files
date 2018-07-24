package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the VEHICLE_UTILIZATION database table.
 * 
 */
@Entity
@Table(name = "VEHICLE_UTILIZATION")
public class VehicleUtilization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "VEHICLE_UTILIZATION_VEHICLEUTILIZATIONID_GENERATOR", sequenceName = "VEHICLE_UTILIZATION_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEHICLE_UTILIZATION_VEHICLEUTILIZATIONID_GENERATOR")
	@Column(name = "VEHICLE_UTILIZATION_ID")
	private Long vehicleUtilizationId;

	@Column(name = "AVERAGE_SPEED")
	private Double averageSpeed;

	@Column(name = "CURRENT_LAT")
	private Double currentLat;

	@Column(name = "CURRENT_LONG")
	private Double currentLong;

	@Column(name = "MAXIMUM_SPEED")
	private Double maximumSpeed;

	@Column(name = "RECORD_TIMESTAMP")
	private Timestamp recordTimestamp;

	@Temporal(TemporalType.DATE)
	@Column(name = "REPORT_DATE")
	private Date reportDate;

	@Column(name = "TOTAL_DISTANCE")
	private Double totalDistance;

	@Column(name = "TOTAL_DRIVING_TIME")
	private Long totalDrivingTime;

	@Column(name = "TOTAL_UP_TIME")
	private Long totalUpTime;

	@Column(name = "UTILIZATION")
	private Double utilization;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private DicvUser dicvUserVehicleUtilization;

	@ManyToOne
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	@Column(name = "MAX_IDLE_LATITUDE")
	private Double maxIdleLatitude;

	@Column(name = "MAX_IDLE_LONGITUDE")
	private Double maxIdleLongtitude;

	@Column(name = "MAX_IDLE_TIME")
	private Long maxIdleTime;

	@Column(name = "TOTAL_IDLE_TIME")
	private Long totalIdleTime;

	@Column(name = "NO_OF_STOPS")
	private Integer noOfStops;
	
	@Column(name = "IDLE_LOCATION")
	private String idleLocation;

	@Column(name = "SPEED_LOCATION")
	private String speedLocation;

	public VehicleUtilization() {
	}

	public Double getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(Double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public Double getCurrentLat() {
		return currentLat;
	}

	public void setCurrentLat(Double currentLat) {
		this.currentLat = currentLat;
	}

	public Double getCurrentLong() {
		return currentLong;
	}

	public void setCurrentLong(Double currentLong) {
		this.currentLong = currentLong;
	}

	public Double getMaximumSpeed() {
		return maximumSpeed;
	}

	public void setMaximumSpeed(Double maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	public Timestamp getRecordTimestamp() {
		return recordTimestamp;
	}

	public void setRecordTimestamp(Timestamp recordTimestamp) {
		this.recordTimestamp = recordTimestamp;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Double getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(Double totalDistance) {
		this.totalDistance = totalDistance;
	}

	public Long getTotalDrivingTime() {
		return totalDrivingTime;
	}

	public void setTotalDrivingTime(Long totalDrivingTime) {
		this.totalDrivingTime = totalDrivingTime;
	}

	public Long getTotalUpTime() {
		return totalUpTime;
	}

	public void setTotalUpTime(Long totalUpTime) {
		this.totalUpTime = totalUpTime;
	}

	public Double getUtilization() {
		return utilization;
	}

	public void setUtilization(Double utilization) {
		this.utilization = utilization;
	}

	/*
	 * public DicvCountry getCountryVehicleUtil() { return countryVehicleUtil; }
	 * 
	 * public void setCountryVehicleUtil(DicvCountry countryVehicleUtil) {
	 * this.countryVehicleUtil = countryVehicleUtil; }
	 */

	public DicvUser getDicvUserVehicleUtilization() {
		return dicvUserVehicleUtilization;
	}

	public void setDicvUserVehicleUtilization(DicvUser dicvUserVehicleUtilization) {
		this.dicvUserVehicleUtilization = dicvUserVehicleUtilization;
	}

	public Double getMaxIdleLatitude() {
		return maxIdleLatitude;
	}

	public void setMaxIdleLatitude(Double maxIdleLatitude) {
		this.maxIdleLatitude = maxIdleLatitude;
	}

	public Double getMaxIdleLongtitude() {
		return maxIdleLongtitude;
	}

	public void setMaxIdleLongtitude(Double maxIdleLongtitude) {
		this.maxIdleLongtitude = maxIdleLongtitude;
	}

	public Long getMaxIdleTime() {
		return maxIdleTime;
	}

	public void setMaxIdleTime(Long maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}

	public Long getTotalIdleTime() {
		return totalIdleTime;
	}

	public void setTotalIdleTime(Long totalIdleTime) {
		this.totalIdleTime = totalIdleTime;
	}

	public Integer getNoOfStops() {
		return noOfStops;
	}

	public void setNoOfStops(Integer noOfStops) {
		this.noOfStops = noOfStops;
	}

	public Long getVehicleUtilizationId() {
		return vehicleUtilizationId;
	}

	public void setVehicleUtilizationId(Long vehicleUtilizationId) {
		this.vehicleUtilizationId = vehicleUtilizationId;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getIdleLocation() {
		return idleLocation;
	}

	public void setIdleLocation(String idleLocation) {
		this.idleLocation = idleLocation;
	}

	public String getSpeedLocation() {
		return speedLocation;
	}

	public void setSpeedLocation(String speedLocation) {
		this.speedLocation = speedLocation;
	}



}