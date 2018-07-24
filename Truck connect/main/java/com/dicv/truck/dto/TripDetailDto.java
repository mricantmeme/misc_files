package com.dicv.truck.dto;

import java.sql.Timestamp;

/**
 * This class is responsible to keep getTripDetails() method information based
 * on user request parameter like scheduledTripId and status[status should be
 * either PLANNED/RUNNING/COMPLETED].
 * 
 * @author aut7kor
 * 
 */
public class TripDetailDto {

	private Long tripId;
	private String driverName;
	private String vehicleRegNum;
	private String vehicleDescription;
	private String vehicleIdleTime;
	private String stopTime;
	private Double averageVehicleSpeed;
	private Integer distanceTravelled;
	private String timeElapsed;
	private Integer nosOfStop;
	private Integer vehicleId;
	private Integer driverId;
	private String engineRunHours;
	private String fromDateStr;
	private String toDateStr;
	private Long scheduledTripId;
	private String fromAddress;
	private String toAddress;
	private String fromCity;
	private String toCity;
	private String isSms;
	private String isEmail;
	private Double goodsWeight;
	private int scheduledTripFlag;
	private Double stopLocationLat;
	private Double stopLocationLong;
	private Double startLocationLat;
	private Double startLocationLong;
	private Timestamp actualTripStartDate;
	private Timestamp actualTripEndDate;
	private String drivingTime;
	private TripPickupDispatchDtlsDto tripPickupDispatchDtlsDto; // Adding trip
																	// pickup
																	// and
																	// dispatch
																	// details.
	private TripDriverAnalysisDto tripDriverAnalysis; // Adding trip driver
														// analysis details.
	private TripInsightsSpeedingLocationDto tripInsightsSpeedingLocation; // Adding
																			// trip
																			// insights
																			// and
																			// speeding
																			// location
																			// details
	private Integer loadCategoryId;
	private String loadCategoryName;
	private Integer volume;
	private Integer loadWeight;
	private Integer revenue;
	private String customerName;
	private Double driverCost;
	private Double fuelCost;
	private Double operationalCost;
	private Integer distance;
	private Integer duration;

	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getVehicleRegNum() {
		return vehicleRegNum;
	}

	public void setVehicleRegNum(String vehicleRegNum) {
		this.vehicleRegNum = vehicleRegNum;
	}

	public String getVehicleDescription() {
		return vehicleDescription;
	}

	public void setVehicleDescription(String vehicleDescription) {
		this.vehicleDescription = vehicleDescription;
	}

	public String getVehicleIdleTime() {
		return vehicleIdleTime;
	}

	public void setVehicleIdleTime(String vehicleIdleTime) {
		this.vehicleIdleTime = vehicleIdleTime;
	}

	public Double getAverageVehicleSpeed() {
		return averageVehicleSpeed;
	}

	public void setAverageVehicleSpeed(Double averageVehicleSpeed) {
		this.averageVehicleSpeed = averageVehicleSpeed;
	}

	public String getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(String timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public Integer getNosOfStop() {
		return nosOfStop;
	}

	public void setNosOfStop(Integer nosOfStop) {
		this.nosOfStop = nosOfStop;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getEngineRunHours() {
		return engineRunHours;
	}

	public void setEngineRunHours(String engineRunHours) {
		this.engineRunHours = engineRunHours;
	}

	public String getFromDateStr() {
		return fromDateStr;
	}

	public void setFromDateStr(String fromDateStr) {
		this.fromDateStr = fromDateStr;
	}

	public String getToDateStr() {
		return toDateStr;
	}

	public void setToDateStr(String toDateStr) {
		this.toDateStr = toDateStr;
	}

	public Long getScheduledTripId() {
		return scheduledTripId;
	}

	public void setScheduledTripId(Long scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getIsSms() {
		return isSms;
	}

	public void setIsSms(String isSms) {
		this.isSms = isSms;
	}

	public String getIsEmail() {
		return isEmail;
	}

	public void setIsEmail(String isEmail) {
		this.isEmail = isEmail;
	}

	public Double getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(Double goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public int getScheduledTripFlag() {
		return scheduledTripFlag;
	}

	public void setScheduledTripFlag(int scheduledTripFlag) {
		this.scheduledTripFlag = scheduledTripFlag;
	}

	public Timestamp getActualTripStartDate() {
		return actualTripStartDate;
	}

	public void setActualTripStartDate(Timestamp actualTripStartDate) {
		this.actualTripStartDate = actualTripStartDate;
	}

	public Timestamp getActualTripEndDate() {
		return actualTripEndDate;
	}

	public void setActualTripEndDate(Timestamp actualTripEndDate) {
		this.actualTripEndDate = actualTripEndDate;
	}

	public TripPickupDispatchDtlsDto getTripPickupDispatchDtlsDto() {
		return tripPickupDispatchDtlsDto;
	}

	public void setTripPickupDispatchDtlsDto(TripPickupDispatchDtlsDto tripPickupDispatchDtlsDto) {
		this.tripPickupDispatchDtlsDto = tripPickupDispatchDtlsDto;
	}

	public TripDriverAnalysisDto getTripDriverAnalysis() {
		return tripDriverAnalysis;
	}

	public void setTripDriverAnalysis(TripDriverAnalysisDto tripDriverAnalysis) {
		this.tripDriverAnalysis = tripDriverAnalysis;
	}

	public TripInsightsSpeedingLocationDto getTripInsightsSpeedingLocation() {
		return tripInsightsSpeedingLocation;
	}

	public void setTripInsightsSpeedingLocation(TripInsightsSpeedingLocationDto tripInsightsSpeedingLocation) {
		this.tripInsightsSpeedingLocation = tripInsightsSpeedingLocation;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	/**
	 * @return the drivingTime
	 */
	public String getDrivingTime() {
		return drivingTime;
	}

	/**
	 * @param drivingTime
	 *            the drivingTime to set
	 */
	public void setDrivingTime(String drivingTime) {
		this.drivingTime = drivingTime;
	}

	/**
	 * @return the loadCategoryId
	 */
	public Integer getLoadCategoryId() {
		return loadCategoryId;
	}

	/**
	 * @param loadCategoryId
	 *            the loadCategoryId to set
	 */
	public void setLoadCategoryId(Integer loadCategoryId) {
		this.loadCategoryId = loadCategoryId;
	}

	/**
	 * @return the loadCategoryName
	 */
	public String getLoadCategoryName() {
		return loadCategoryName;
	}

	/**
	 * @param loadCategoryName
	 *            the loadCategoryName to set
	 */
	public void setLoadCategoryName(String loadCategoryName) {
		this.loadCategoryName = loadCategoryName;
	}

	/**
	 * @return the volume
	 */
	public Integer getVolume() {
		return volume;
	}

	/**
	 * @param volume
	 *            the volume to set
	 */
	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	/**
	 * @return the loadWeight
	 */
	public Integer getLoadWeight() {
		return loadWeight;
	}

	/**
	 * @param loadWeight
	 *            the loadWeight to set
	 */
	public void setLoadWeight(Integer loadWeight) {
		this.loadWeight = loadWeight;
	}

	/**
	 * @return the revenue
	 */
	public Integer getRevenue() {
		return revenue;
	}

	/**
	 * @param revenue
	 *            the revenue to set
	 */
	public void setRevenue(Integer revenue) {
		this.revenue = revenue;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TripDetailDto [tripId=" + tripId + ", driverName=" + driverName + ", vehicleRegNum=" + vehicleRegNum
				+ ", vehicleDescription=" + vehicleDescription + ", vehicleIdleTime=" + vehicleIdleTime + ", stopTime="
				+ stopTime + ", averageVehicleSpeed=" + averageVehicleSpeed + ", distanceTravelled=" + distanceTravelled
				+ ", timeElapsed=" + timeElapsed + ", nosOfStop=" + nosOfStop + ", vehicleId=" + vehicleId
				+ ", driverId=" + driverId + ", engineRunHours=" + engineRunHours + ", fromDateStr=" + fromDateStr
				+ ", toDateStr=" + toDateStr + ", scheduledTripId=" + scheduledTripId + ", fromAddress=" + fromAddress
				+ ", toAddress=" + toAddress + ", isSms=" + isSms + ", isEmail=" + isEmail + ", goodsWeight="
				+ goodsWeight + ", scheduledTripFlag=" + scheduledTripFlag + ", actualTripStartDate="
				+ actualTripStartDate + ", actualTripEndDate=" + actualTripEndDate + ", drivingTime=" + drivingTime
				+ ", tripPickupDispatchDtlsDto=" + tripPickupDispatchDtlsDto + ", tripDriverAnalysis="
				+ tripDriverAnalysis + ", tripInsightsSpeedingLocation=" + tripInsightsSpeedingLocation
				+ ", loadCategoryId=" + loadCategoryId + ", loadCategoryName=" + loadCategoryName + ", volume=" + volume
				+ ", loadWeight=" + loadWeight + ", revenue=" + revenue + ", customerName=" + customerName + "]";
	}

	/**
	 * @return the driverCost
	 */
	public Double getDriverCost() {
		return driverCost;
	}

	/**
	 * @param driverCost
	 *            the driverCost to set
	 */
	public void setDriverCost(Double driverCost) {
		this.driverCost = driverCost;
	}

	/**
	 * @return the fuelCost
	 */
	public Double getFuelCost() {
		return fuelCost;
	}

	/**
	 * @param fuelCost
	 *            the fuelCost to set
	 */
	public void setFuelCost(Double fuelCost) {
		this.fuelCost = fuelCost;
	}

	/**
	 * @return the operationalCost
	 */
	public Double getOperationalCost() {
		return operationalCost;
	}

	/**
	 * @param operationalCost
	 *            the operationalCost to set
	 */
	public void setOperationalCost(Double operationalCost) {
		this.operationalCost = operationalCost;
	}

	/**
	 * @return the duration
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getDistanceTravelled() {
		return distanceTravelled;
	}

	public void setDistanceTravelled(Integer distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Double getStopLocationLat() {
		return stopLocationLat;
	}

	public void setStopLocationLat(Double stopLocationLat) {
		this.stopLocationLat = stopLocationLat;
	}

	public Double getStopLocationLong() {
		return stopLocationLong;
	}

	public void setStopLocationLong(Double stopLocationLong) {
		this.stopLocationLong = stopLocationLong;
	}

	public Double getStartLocationLat() {
		return startLocationLat;
	}

	public void setStartLocationLat(Double startLocationLat) {
		this.startLocationLat = startLocationLat;
	}

	public Double getStartLocationLong() {
		return startLocationLong;
	}

	public void setStartLocationLong(Double startLocationLong) {
		this.startLocationLong = startLocationLong;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

}
