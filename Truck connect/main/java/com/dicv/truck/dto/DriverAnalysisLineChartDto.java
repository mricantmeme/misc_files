/**
 * 
 */
package com.dicv.truck.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author IMT5KOR
 * 
 */
public class DriverAnalysisLineChartDto {

	private Integer driverId;
	private String driverImage;
	private BigDecimal distanceTravelled;
	private BigDecimal speedAdherence;
	private Double economyDriving;
	private BigDecimal economyBand;
	private BigDecimal engineNonIdleTimePercent;
	private Integer harshDriving;
	private Integer fuelEfficiency;
	private BigDecimal maxSpeed;
	private Date maxSpeedTime;
	private Date date;
	private Double latitude;
	private Double longitude;
	private Long totalDriveTime;	
	private Double distanceInEconomyBand;
	private BigDecimal engineIdleTimePercent;

	public DriverAnalysisLineChartDto() {

	}

	public DriverAnalysisLineChartDto(Integer driverId,
			BigDecimal distanceTravelled, BigDecimal speedAdherence,
			Double economyDriving, BigDecimal engineNonIdleTimePercent,
			Integer harshDriving, Integer fuelEfficiency) {

		this.driverId = driverId;
		this.distanceTravelled = distanceTravelled;
		this.speedAdherence = speedAdherence;
		this.engineNonIdleTimePercent = engineNonIdleTimePercent;
		this.harshDriving = harshDriving;
		this.fuelEfficiency = fuelEfficiency;
		this.economyDriving = economyDriving;
	}

	public DriverAnalysisLineChartDto(Integer driverId,
			BigDecimal distanceTravelled, BigDecimal speedAdherence,
			Double economyDriving, BigDecimal engineNonIdleTimePercent,
			Integer harshDriving, Integer fuelEfficiency, BigDecimal maxSpeed,
			Date maxSpeedTime, Date date, Double latitude, Double longitude,
			Long totalDriveTime, Double distanceInEconomyBand) {

		this.driverId = driverId;
		this.distanceTravelled = distanceTravelled;
		this.speedAdherence = speedAdherence;
		this.economyDriving = economyDriving;
		this.engineNonIdleTimePercent = engineNonIdleTimePercent;
		this.harshDriving = harshDriving;
		this.fuelEfficiency = fuelEfficiency;
		this.maxSpeed = maxSpeed;
		this.maxSpeedTime = maxSpeedTime;
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
		this.totalDriveTime = totalDriveTime;
		this.distanceInEconomyBand = distanceInEconomyBand;

	}
	
	public DriverAnalysisLineChartDto(Integer driverId,
			BigDecimal distanceTravelled, BigDecimal speedAdherence,
			Double economyDriving, BigDecimal engineNonIdleTimePercent,BigDecimal engineIdleTimePercent,
			Integer harshDriving, Integer fuelEfficiency, BigDecimal maxSpeed,
			Date maxSpeedTime, Date date, Double latitude, Double longitude,
			Long totalDriveTime, Double distanceInEconomyBand) {

		this.driverId = driverId;
		this.distanceTravelled = distanceTravelled;
		this.speedAdherence = speedAdherence;
		this.economyDriving = economyDriving;
		this.engineNonIdleTimePercent = engineNonIdleTimePercent;
		this.engineIdleTimePercent = engineIdleTimePercent;
		this.harshDriving = harshDriving;
		this.fuelEfficiency = fuelEfficiency;
		this.maxSpeed = maxSpeed;
		this.maxSpeedTime = maxSpeedTime;
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
		this.totalDriveTime = totalDriveTime;
		this.distanceInEconomyBand = distanceInEconomyBand;

	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getDriverImage() {
		return driverImage;
	}

	public void setDriverImage(String driverImage) {
		this.driverImage = driverImage;
	}

	public BigDecimal getDistanceTravelled() {
		return distanceTravelled;
	}

	public void setDistanceTravelled(BigDecimal distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
	}

	public BigDecimal getSpeedAdherence() {
		return speedAdherence;
	}

	public void setSpeedAdherence(BigDecimal speedAdherence) {
		this.speedAdherence = speedAdherence;
	}

	public Double getEconomyDriving() {
		return economyDriving;
	}

	public void setEconomyDriving(Double economyDriving) {
		this.economyDriving = economyDriving;
	}

	public BigDecimal getEconomyBand() {
		return economyBand;
	}

	public void setEconomyBand(BigDecimal economyBand) {
		this.economyBand = economyBand;
	}

	public BigDecimal getEngineNonIdleTimePercent() {
		return engineNonIdleTimePercent;
	}

	public void setEngineNonIdleTimePercent(BigDecimal engineNonIdleTimePercent) {
		this.engineNonIdleTimePercent = engineNonIdleTimePercent;
	}

	public Integer getHarshDriving() {
		return harshDriving;
	}

	public void setHarshDriving(Integer harshDriving) {
		this.harshDriving = harshDriving;
	}

	public Integer getFuelEfficiency() {
		return fuelEfficiency;
	}

	public void setFuelEfficiency(Integer fuelEfficiency) {
		this.fuelEfficiency = fuelEfficiency;
	}

	public BigDecimal getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(BigDecimal maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Date getMaxSpeedTime() {
		return maxSpeedTime;
	}

	public void setMaxSpeedTime(Date maxSpeedTime) {
		this.maxSpeedTime = maxSpeedTime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long getTotalDriveTime() {
		return totalDriveTime;
	}

	public void setTotalDriveTime(Long totalDriveTime) {
		this.totalDriveTime = totalDriveTime;
	}

	public Double getDistanceInEconomyBand() {
		return distanceInEconomyBand;
	}

	public void setDistanceInEconomyBand(Double distanceInEconomyBand) {
		this.distanceInEconomyBand = distanceInEconomyBand;
	}

	public BigDecimal getEngineIdleTimePercent() {
		return engineIdleTimePercent;
	}

	public void setEngineIdleTimePercent(BigDecimal engineIdleTimePercent) {
		this.engineIdleTimePercent = engineIdleTimePercent;
	}



}
