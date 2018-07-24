package com.dicv.truck.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.TripStopLocation;

@Repository
public interface TripStopLocationRepo extends JpaRepository<TripStopLocation, Long> {

	@Query("Select s from TripStopLocation s where s.trip.tripId=:tripId")
	public List<TripStopLocation> getTripStopLocation(@Param("tripId") Long tripId);

	@Query("select t from TripStopLocation t where t.trip.vehicle.vehicleId=:vehicleId  and t.startTime>=:startTime and "
			+ " t.stopTime<=:endDate and t.duration>60000 order by startTime asc")
	public List<TripStopLocation> getTripStopLocation(@Param("vehicleId") Integer vehicleId,
			@Param("startTime") Date startTime, @Param("endDate") Date endDate);

}
