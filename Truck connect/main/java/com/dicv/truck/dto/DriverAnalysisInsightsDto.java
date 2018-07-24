/**
 * 
 */
package com.dicv.truck.dto;

import java.math.BigDecimal;

/**
 * @author Ramakrishna
 * 
 */
public class DriverAnalysisInsightsDto extends StatusMessageDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3836738397378759589L;

	private BigDecimal maxSpeed;
	private String maxSpeedTime;
	private double latitude;
	private double longitude;
	private String date;	
	private double totaleconomyBandDistance;
	private String driveTimePerDay;

	public DriverAnalysisInsightsDto() {

	}

	public BigDecimal getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(BigDecimal maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getMaxSpeedTime() {
		return maxSpeedTime;
	}

	public void setMaxSpeedTime(String maxSpeedTime) {
		this.maxSpeedTime = maxSpeedTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDriveTimePerDay() {
		return driveTimePerDay;
	}

	public void setDriveTimePerDay(String driveTimePerDay) {
		this.driveTimePerDay = driveTimePerDay;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getTotaleconomyBandDistance() {
		return totaleconomyBandDistance;
	}

	public void setTotaleconomyBandDistance(double totaleconomyBandDistance) {
		this.totaleconomyBandDistance = totaleconomyBandDistance;
	}

}
