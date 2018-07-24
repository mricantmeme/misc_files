package com.dicv.truck.dto;

/**
 * This class contains data related to trip for a given owner, from date, to
 * date and for a driver associated with the given owner.
 * 
 * @author SEG3KOR
 * 
 */
public class TripDataDto extends TripRecordDto {

	/**
	 * Contains list of trip details of completed status.
	 */
	private TripDetailDto tripDetails;

	public TripDetailDto getTripDetails() {
		return tripDetails;
	}

	public void setTripDetails(TripDetailDto tripDetails) {
		this.tripDetails = tripDetails;
	}

}
