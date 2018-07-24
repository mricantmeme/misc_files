package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

public class TripPickUpAndDispatchList extends StatusMessageDto {

	private List<TripPickUpDispatchData> tripList = new ArrayList<TripPickUpDispatchData>();

	public List<TripPickUpDispatchData> getTripList() {
		return tripList;
	}

	public void setTripList(List<TripPickUpDispatchData> tripList) {
		this.tripList = tripList;
	}

}
