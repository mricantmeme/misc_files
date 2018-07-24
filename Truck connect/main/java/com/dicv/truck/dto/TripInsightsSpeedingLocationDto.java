package com.dicv.truck.dto;

import java.sql.Timestamp;

/**
 * This class is responsible to get insights and speeding location for a trip.
 * 
 * @author aut7kor
 * 
 */
public class TripInsightsSpeedingLocationDto {

	private Integer maximumSpeed;
	private Double maxSpeedLat;
	private Double maxSpeedLong;
	// private Integer greenBandDistance;
	private Double economyBandDistance;
	private Timestamp maxSpeedTime;
	private String speedBand0To20;
	private String speedBand21To40;
	private String speedBand41To60;
	private String speedBand61To80;
	private String speedBandOver80;

	public Integer getMaximumSpeed() {
		return maximumSpeed;
	}

	public void setMaximumSpeed(Integer maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	public Double getMaxSpeedLat() {
		return maxSpeedLat;
	}

	public void setMaxSpeedLat(Double maxSpeedLat) {
		this.maxSpeedLat = maxSpeedLat;
	}

	public Double getMaxSpeedLong() {
		return maxSpeedLong;
	}

	public void setMaxSpeedLong(Double maxSpeedLong) {
		this.maxSpeedLong = maxSpeedLong;
	}

	public Double getEconomyBandDistance() {
		return economyBandDistance;
	}

	public void setEconomyBandDistance(Double economyBandDistance) {
		this.economyBandDistance = economyBandDistance;
	}

	// public Integer getGreenBandDistance() {
	// return greenBandDistance;
	// }
	//
	// public void setGreenBandDistance(Integer greenBandDistance) {
	// this.greenBandDistance = greenBandDistance;
	// }

	/**
	 * @return the maxSpeedTime
	 */
	public Timestamp getMaxSpeedTime() {
		return maxSpeedTime;
	}

	/**
	 * @param maxSpeedTime
	 *            the maxSpeedTime to set
	 */
	public void setMaxSpeedTime(Timestamp maxSpeedTime) {
		this.maxSpeedTime = maxSpeedTime;
	}

	/**
	 * @return the speedBand0To20
	 */
	public String getSpeedBand0To20() {
		return speedBand0To20;
	}

	/**
	 * @param speedBand0To20
	 *            the speedBand0To20 to set
	 */
	public void setSpeedBand0To20(String speedBand0To20) {
		this.speedBand0To20 = speedBand0To20;
	}

	/**
	 * @return the speedBand21To40
	 */
	public String getSpeedBand21To40() {
		return speedBand21To40;
	}

	/**
	 * @param speedBand21To40
	 *            the speedBand21To40 to set
	 */
	public void setSpeedBand21To40(String speedBand21To40) {
		this.speedBand21To40 = speedBand21To40;
	}

	/**
	 * @return the speedBand41To60
	 */
	public String getSpeedBand41To60() {
		return speedBand41To60;
	}

	/**
	 * @param speedBand41To60
	 *            the speedBand41To60 to set
	 */
	public void setSpeedBand41To60(String speedBand41To60) {
		this.speedBand41To60 = speedBand41To60;
	}

	/**
	 * @return the speedBand61To80
	 */
	public String getSpeedBand61To80() {
		return speedBand61To80;
	}

	/**
	 * @param speedBand61To80
	 *            the speedBand61To80 to set
	 */
	public void setSpeedBand61To80(String speedBand61To80) {
		this.speedBand61To80 = speedBand61To80;
	}

	/**
	 * @return the speedBandOver80
	 */
	public String getSpeedBandOver80() {
		return speedBandOver80;
	}

	/**
	 * @param speedBandOver80
	 *            the speedBandOver80 to set
	 */
	public void setSpeedBandOver80(String speedBandOver80) {
		this.speedBandOver80 = speedBandOver80;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TripInsightsSpeedingLocationDto [maximumSpeed=" + maximumSpeed
				+ ", maxSpeedLat=" + maxSpeedLat + ", maxSpeedLong="
				+ maxSpeedLong + ", economyBandDistance=" + economyBandDistance
				+ ", maxSpeedTime=" + maxSpeedTime + ", speedBand0To20="
				+ speedBand0To20 + ", speedBand21To40=" + speedBand21To40
				+ ", speedBand41To60=" + speedBand41To60 + ", speedBand61To80="
				+ speedBand61To80 + ", speedBandOver80=" + speedBandOver80
				+ "]";
	}

}
