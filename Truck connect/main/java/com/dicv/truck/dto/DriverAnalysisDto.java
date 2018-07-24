package com.dicv.truck.dto;


/**
 * This is a so class, to retrieve the analysed trip information.
 * 
 */
public class DriverAnalysisDto extends StatusMessageDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Name of the driver associated to a trip.
	 */
	private String driverName;

	/**
	 * Holds driver id.
	 */
	private Integer driverId;

	/**
	 * Holds driver photo.
	 */
	private String driverImage;

	/**
	 * DICV Group name
	 */
	private String groupName;

	private Double driverScore;
	private Double starCount;

	private Double averageSpeed;

	private Double distanceTravelled;
	private Double economyBandDistance;
	private String speedAdherencePercent;
	private String economyBandPercent;
	private String engineIdleTimePercent;

	private String engineNonIdleTimePercent;
	private String harshDrivingPercent;
	private String fuelEfficiencyPercent;
	private String drivingDuration;
	private String speedBand0To20;
	private String speedBand21To40;
	private String speedBand41To60;
	private String speedBand61To80;
	private String speedBandOver80;
	private String economyDriving;

	/**
	 * @return the driverName
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * @param driverName
	 *            the driverName to set
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Double getDriverScore() {
		return driverScore;
	}

	public void setDriverScore(Double driverScore) {
		this.driverScore = driverScore;
	}

	public Double getStarCount() {
		return starCount;
	}

	public void setStarCount(Double starCount) {
		this.starCount = starCount;
	}

	public Double getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(Double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public Double getDistanceTravelled() {
		return distanceTravelled;
	}

	public void setDistanceTravelled(Double distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
	}

	public Double getEconomyBandDistance() {
		return economyBandDistance;
	}

	public void setEconomyBandDistance(Double economyBandDistance) {
		this.economyBandDistance = economyBandDistance;
	}

	public String getSpeedAdherencePercent() {
		return speedAdherencePercent;
	}

	public void setSpeedAdherencePercent(String speedAdherencePercent) {
		this.speedAdherencePercent = speedAdherencePercent;
	}

	public String getEconomyBandPercent() {
		return economyBandPercent;
	}

	public void setEconomyBandPercent(String economyBandPercent) {
		this.economyBandPercent = economyBandPercent;
	}

	public String getEngineIdleTimePercent() {
		return engineIdleTimePercent;
	}

	public void setEngineIdleTimePercent(String engineIdleTimePercent) {
		this.engineIdleTimePercent = engineIdleTimePercent;
	}

	public String getEngineNonIdleTimePercent() {
		return engineNonIdleTimePercent;
	}

	public void setEngineNonIdleTimePercent(String engineNonIdleTimePercent) {
		this.engineNonIdleTimePercent = engineNonIdleTimePercent;
	}

	public String getHarshDrivingPercent() {
		return harshDrivingPercent;
	}

	public void setHarshDrivingPercent(String harshDrivingPercent) {
		this.harshDrivingPercent = harshDrivingPercent;
	}

	public String getFuelEfficiencyPercent() {
		return fuelEfficiencyPercent;
	}

	public void setFuelEfficiencyPercent(String fuelEfficiencyPercent) {
		this.fuelEfficiencyPercent = fuelEfficiencyPercent;
	}

	public String getDrivingDuration() {
		return drivingDuration;
	}

	public void setDrivingDuration(String drivingDuration) {
		this.drivingDuration = drivingDuration;
	}

	public String getSpeedBand0To20() {
		return speedBand0To20;
	}

	public void setSpeedBand0To20(String speedBand0To20) {
		this.speedBand0To20 = speedBand0To20;
	}

	public String getSpeedBand21To40() {
		return speedBand21To40;
	}

	public void setSpeedBand21To40(String speedBand21To40) {
		this.speedBand21To40 = speedBand21To40;
	}

	public String getSpeedBand41To60() {
		return speedBand41To60;
	}

	public void setSpeedBand41To60(String speedBand41To60) {
		this.speedBand41To60 = speedBand41To60;
	}

	public String getSpeedBand61To80() {
		return speedBand61To80;
	}

	public void setSpeedBand61To80(String speedBand61To80) {
		this.speedBand61To80 = speedBand61To80;
	}

	public String getSpeedBandOver80() {
		return speedBandOver80;
	}

	public void setSpeedBandOver80(String speedBandOver80) {
		this.speedBandOver80 = speedBandOver80;
	}

	public String getEconomyDriving() {
		return economyDriving;
	}

	public void setEconomyDriving(String economyDriving) {
		this.economyDriving = economyDriving;
	}
}
