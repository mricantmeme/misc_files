package com.dicv.truck.dto;

/**
 * This class is responsible to receive scheduled trip id ,gpsImei and trip
 * startDateTime and after starting trip it's return trip id.
 * 
 * @author aut7kor
 * 
 */
public class TripStartDto extends StatusMessageDto {

	private Long scheduledTripId;
	private Long tripId;
	private Long gpsImei;
	private String startDateTime;
	private Boolean isNotificationSent;

	public Long getScheduledTripId() {
		return scheduledTripId;
	}

	public void setScheduledTripId(Long scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

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

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Boolean getIsNotificationSent() {
		return isNotificationSent;
	}

	public void setIsNotificationSent(Boolean isNotificationSent) {
		this.isNotificationSent = isNotificationSent;
	}

	@Override
	public String toString() {
		return "TripStartDto [scheduledTripId=" + scheduledTripId + ", tripId=" + tripId + ", gpsImei=" + gpsImei
				+ ", startDateTime=" + startDateTime + ", isNotificationSent=" + isNotificationSent + "]";
	}

}
