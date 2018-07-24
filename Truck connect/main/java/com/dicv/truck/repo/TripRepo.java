package com.dicv.truck.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.Trip;

@Repository
public interface TripRepo extends JpaRepository<Trip, Long> {

	@Query("Select count(t.tripId) from Trip t where t.scheduledTrip IS NOT NULL and t.tripStatus IN :tripStatus and t.tripDriverUser.userId=:userId")
	public Long getActiveTripCount(@Param("userId") Integer userId, @Param("tripStatus") List<String> tripStatus);

	@Query("Select t.tripStatus from Trip t where t.scheduledTrip IS NOT NULL and t.tripStatus IN :tripStatus and t.vehicle.vehicleId=:vehicleId")
	public Long getActiveTripByVehicle(@Param("vehicleId") Integer vehicleId,
			@Param("tripStatus") List<String> tripStatus);

	@Query("select t from Trip t where t.tripDriverUser.userId=:userId "
			+ " and t.tripStartTime>=:startTime and t.tripEndTime<=:endDate and t.scheduledTrip is not null")
	public List<Trip> getActiveTripList(@Param("userId") Integer userId, @Param("startTime") Date startTime,
			@Param("endDate") Date endDate);

	@Query("Select t from Trip t where t.tripId=:tripId and t.tripStartTime>=:startTime and t.tripEndTime<=:endDate")
	public Trip getRoutePlan(@Param("tripId") Long tripId, @Param("startTime") Date startTime,
			@Param("endDate") Date endDate);
}
