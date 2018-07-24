package com.dicv.truck.dto;

/**
 * This class is responsible to calculate
 * speedAdherencePercent,economyBandPercent and non_idleTimePercent for specific
 * trip.
 * 
 * @author aut7kor
 * 
 */
public class TripDriverAnalysisDto {

	private String speedAdherencePercent;
	private String economyBandPercent;
	private String engineNonIdleTimePercent;
	private String economicDriving;


	public String getEconomyBandPercent() {
		return economyBandPercent;
	}

	public void setEconomyBandPercent(String economyBandPercent) {
		this.economyBandPercent = economyBandPercent;
	}

	public String getEngineNonIdleTimePercent() {
		return engineNonIdleTimePercent;
	}

	public void setEngineNonIdleTimePercent(String engineNonIdleTimePercent) {
		this.engineNonIdleTimePercent = engineNonIdleTimePercent;
	}

	@Override
	public String toString() {
		return "TripDriverAnalysisDto [speedAdherencePercent=" + speedAdherencePercent + ", economyBandPercent="
				+ economyBandPercent + ", engineNonIdleTimePercent=" + engineNonIdleTimePercent + "]";
	}

	public String getEconomicDriving() {
		return economicDriving;
	}

	public void setEconomicDriving(String economicDriving) {
		this.economicDriving = economicDriving;
	}

	public String getSpeedAdherencePercent() {
		return speedAdherencePercent;
	}

	public void setSpeedAdherencePercent(String speedAdherencePercent) {
		this.speedAdherencePercent = speedAdherencePercent;
	}

}
