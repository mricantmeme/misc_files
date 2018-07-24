package com.dicv.truck.repo;

import java.util.List;

import com.dicv.truck.dto.TripRecordDto;
import com.dicv.truck.model.Trip;

public interface TripDao {

	public List<Trip> getTripList(TripRecordDto tripRecord, String userType);
	
	public List<Trip> getTripListWithDistance(TripRecordDto tripRecord, String userType);

	public Trip getTripDetails(Integer userId, Long tripId, boolean userRoleCheck);
	
	public List<Integer> getScheduledTripList();

}
