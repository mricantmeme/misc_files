package com.dicv.truck.dto;

import java.math.BigDecimal;
import java.util.Date;

public class VehicleSummaryReportDto {

	private String ownerName;
	private Date fromDate;
	private Date toDate;
	private Integer vehicleId;
	private String vehicleName;
	private BigDecimal totalDistance;
	private String totalDrivingTime;
	private BigDecimal maximumSpeed;
	private BigDecimal averageSpeed;
	private Integer numberOfStops;
	private String maxIdleTime;
	private Double maxIdleLatitude;
	private Double maxIdleLongitude;
	private String totalIdleTime;
	private BigDecimal totalNightDistance;
	private String totalNightDrivingTime;
	private String registrationId;

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
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

	public BigDecimal getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(BigDecimal totalDistance) {
		this.totalDistance = totalDistance;
	}

	public BigDecimal getMaximumSpeed() {
		return maximumSpeed;
	}

	public void setMaximumSpeed(BigDecimal maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	public BigDecimal getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(BigDecimal averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public Integer getNumberOfStops() {
		return numberOfStops;
	}

	public void setNumberOfStops(Integer numberOfStops) {
		this.numberOfStops = numberOfStops;
	}

	public Double getMaxIdleLatitude() {
		return maxIdleLatitude;
	}

	public void setMaxIdleLatitude(Double maxIdleLatitude) {
		this.maxIdleLatitude = maxIdleLatitude;
	}

	public Double getMaxIdleLongitude() {
		return maxIdleLongitude;
	}

	public void setMaxIdleLongitude(Double maxIdleLongitude) {
		this.maxIdleLongitude = maxIdleLongitude;
	}

	public String getTotalDrivingTime() {
		return totalDrivingTime;
	}

	public void setTotalDrivingTime(String totalDrivingTime) {
		this.totalDrivingTime = totalDrivingTime;
	}

	public String getMaxIdleTime() {
		return maxIdleTime;
	}

	public void setMaxIdleTime(String maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}

	public String getTotalIdleTime() {
		return totalIdleTime;
	}

	public void setTotalIdleTime(String totalIdleTime) {
		this.totalIdleTime = totalIdleTime;
	}

	public BigDecimal getTotalNightDistance() {
		return totalNightDistance;
	}

	public void setTotalNightDistance(BigDecimal totalNightDistance) {
		this.totalNightDistance = totalNightDistance;
	}

	public String getTotalNightDrivingTime() {
		return totalNightDrivingTime;
	}

	public void setTotalNightDrivingTime(String totalNightDrivingTime) {
		this.totalNightDrivingTime = totalNightDrivingTime;
	}

	@Override
	public String toString() {
		return "VehicleSummaryReportDto [ownerName=" + ownerName
				+ ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", vehicleId=" + vehicleId + ",vehicleName=" + vehicleName
				+ ", totalDistance=" + totalDistance + ", totalDrivingTime="
				+ totalDrivingTime + ", maximumSpeed=" + maximumSpeed
				+ ", averageSpeed=" + averageSpeed + ", numberOfStops="
				+ numberOfStops + ", maxIdleTime=" + maxIdleTime
				+ ", maxIdleLatitude=" + maxIdleLatitude
				+ ", maxIdleLongitude=" + maxIdleLongitude + ", totalIdleTime="
				+ totalIdleTime + ", totalNightDistance=" + totalNightDistance
				+ ", totalNightDrivingTime=" + totalNightDrivingTime + "]";
	}

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

}
