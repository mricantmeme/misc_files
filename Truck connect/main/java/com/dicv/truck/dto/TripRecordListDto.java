package com.dicv.truck.dto;

import java.util.List;

/**
 * This class is responsible to provides trip record details based on user
 * input.
 * 
 * @author aut7kor
 * 
 */
public class TripRecordListDto extends StatusMessageDto {

	private List<TripRecordDto> tripRecords;
	private boolean isNextPageRequired = false; // for pagination

	public TripRecordListDto() {

	}

	public List<TripRecordDto> getTripRecords() {
		return tripRecords;
	}

	public void setTripRecords(List<TripRecordDto> tripRecords) {
		this.tripRecords = tripRecords;
	}

	public boolean isNextPageRequired() {
		return isNextPageRequired;
	}

	public void setNextPageRequired(boolean isNextPageRequired) {
		this.isNextPageRequired = isNextPageRequired;
	}
}
