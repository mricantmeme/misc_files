package com.dicv.truck.dto;

import java.sql.Timestamp;

public class KPIScalingFactorDto extends StatusMessageDto {

	private Integer userId;

	private Integer speeding;

	private Integer economicDriving;

	private Integer idling;

	private Integer economicBand;

	private Timestamp lastUpdatedTime;

	private String lastModifiedUserName;
	
	private Integer harshBraking;

	private Integer harshAcceleration;

	private Integer harshCornering;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSpeeding() {
		return speeding;
	}

	public void setSpeeding(Integer speeding) {
		this.speeding = speeding;
	}

	public Integer getEconomicDriving() {
		return economicDriving;
	}

	public void setEconomicDriving(Integer economicDriving) {
		this.economicDriving = economicDriving;
	}

	public Integer getIdling() {
		return idling;
	}

	public void setIdling(Integer idling) {
		this.idling = idling;
	}

	public Integer getEconomicBand() {
		return economicBand;
	}

	public void setEconomicBand(Integer economicBand) {
		this.economicBand = economicBand;
	}

	public Timestamp getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getLastModifiedUserName() {
		return lastModifiedUserName;
	}

	public void setLastModifiedUserName(String lastModifiedUserName) {
		this.lastModifiedUserName = lastModifiedUserName;
	}

	@Override
	public String toString() {
		return "KPIScalingFactorDto [userId=" + userId + ", speeding=" + speeding + ", economicDriving="
				+ economicDriving + ", idling=" + idling + ", economicBand=" + economicBand + ", lastUpdatedTime="
				+ lastUpdatedTime + ", lastModifiedUserName=" + lastModifiedUserName + "]";
	}

	public Integer getHarshBraking() {
		return harshBraking;
	}

	public void setHarshBraking(Integer harshBraking) {
		this.harshBraking = harshBraking;
	}

	public Integer getHarshAcceleration() {
		return harshAcceleration;
	}

	public void setHarshAcceleration(Integer harshAcceleration) {
		this.harshAcceleration = harshAcceleration;
	}

	public Integer getHarshCornering() {
		return harshCornering;
	}

	public void setHarshCornering(Integer harshCornering) {
		this.harshCornering = harshCornering;
	}

}
