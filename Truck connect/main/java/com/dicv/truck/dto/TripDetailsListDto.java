package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides scheduled trip details based on scheduled trip id and
 * tripStatus.
 * 
 * @author aut7kor
 * 
 */
public class TripDetailsListDto extends StatusMessageDto {

	private List<TripDetailDto> tripDetails = new ArrayList<>();

	public List<TripDetailDto> getTripDetails() {
		return tripDetails;
	}

	public void setTripDetails(List<TripDetailDto> tripDetails) {
		this.tripDetails = tripDetails;
	}

	@Override
	public String toString() {
		String ret = "";
		for (TripDetailDto tripDetail : tripDetails) {
			ret += tripDetail.toString() + "\n";
		}
		return ret;
	}
}