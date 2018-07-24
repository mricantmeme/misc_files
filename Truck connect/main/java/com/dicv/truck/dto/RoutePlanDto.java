package com.dicv.truck.dto;

/*
 * The class is responsible for capture route plan data while scheduling new trip.
 * 
 * @author AUT7KOR
 *  
 */

public class RoutePlanDto {

	private Integer vehicleId;
	private Integer driverId;
	private Double startPontLat;
	private Double startPontLong;
	private Double endPontLat;
	private Double endPontLong;
	private String fromAddress;
	private String toAddress;
	private String toDate;
	private String fromDate;
	private String dispatchType;
	private Double latitude;
	private Double longitude;
	private Integer userId;
	private String isSms;
	private String isEmail;
	private Integer loadCategoryId;
	private Integer volume;
	private Integer loadWeight;
	private Integer revenue;
	private String customerName;
	private Double driverCost;
	private Double fuelCost;
	private Double operationalCost;
	private Double distance;
	private Integer duration;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Double getStartPontLat() {
		return startPontLat;
	}

	public void setStartPontLat(Double startPontLat) {
		this.startPontLat = startPontLat;
	}

	public Double getStartPontLong() {
		return startPontLong;
	}

	public void setStartPontLong(Double startPontLong) {
		this.startPontLong = startPontLong;
	}

	public Double getEndPontLat() {
		return endPontLat;
	}

	public void setEndPontLat(Double endPontLat) {
		this.endPontLat = endPontLat;
	}

	public Double getEndPontLong() {
		return endPontLong;
	}

	public void setEndPontLong(Double endPontLong) {
		this.endPontLong = endPontLong;
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

	public String getDispatchType() {
		return dispatchType;
	}

	public void setDispatchType(String dispatchType) {
		this.dispatchType = dispatchType;
	}

	public double getLatitude() {
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

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
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
	 * @return the distance
	 */
	public Double getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(Double distance) {
		this.distance = distance;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoutePlanDto [vehicleId=" + vehicleId + ", driverId="
				+ driverId + ", startPontLat=" + startPontLat
				+ ", startPontLong=" + startPontLong + ", endPontLat="
				+ endPontLat + ", endPontLong=" + endPontLong
				+ ", fromAddress=" + fromAddress + ", toAddress=" + toAddress
				+ ", toDate=" + toDate + ", fromDate=" + fromDate
				+ ", dispatchType=" + dispatchType + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", userId=" + userId
				+ ", isSms=" + isSms + ", isEmail=" + isEmail
				+ ", loadCategoryId=" + loadCategoryId + ", volume=" + volume
				+ ", loadWeight=" + loadWeight + ", revenue=" + revenue
				+ ", customerName=" + customerName + ", driverCost="
				+ driverCost + ", fuelCost=" + fuelCost + ", operationalCost="
				+ operationalCost + ", distance=" + distance + ", duration="
				+ duration + "]";
	}

}
