package com.dicv.truck.dto;

import java.util.List;

/**
 * This class contains list of TripData.
 * 
 * @author SEG3KOR
 * 
 */
public class TripDataListDto extends StatusMessageDto {

	/*
	 * List of trip data.
	 */
	private List<TripDataDto> tripRecords;

	public List<TripDataDto> getTripRecords() {
		return tripRecords;
	}

	public void setTripRecords(List<TripDataDto> tripRecords) {
		this.tripRecords = tripRecords;
	}

}
