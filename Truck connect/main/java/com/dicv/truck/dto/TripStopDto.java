package com.dicv.truck.dto;

/**
 * 
 * @author aut7kor This class is responsible get trip id GpsImei and
 *         stopDateTime.
 */
public class TripStopDto {
	private Long tripId;
	private Long gpsImei;
	private String stopDateTime;

	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

	public Long getGpsImei() {
		return gpsImei;
	}

	public void setGpsImei(Long gpsImei) {
		this.gpsImei = gpsImei;
	}

	public String getStopDateTime() {
		return stopDateTime;
	}

	public void setStopDateTime(String stopDateTime) {
		this.stopDateTime = stopDateTime;
	}

	@Override
	public String toString() {
		return "TripStopDtls [tripId=" + tripId + ", gpsImei=" + gpsImei
				+ ", stopDateTime=" + stopDateTime + "]";
	}
}
