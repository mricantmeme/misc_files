package com.dicv.truck.repo;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.VehicleIdleAlert;

@Repository
public interface VehicleIdleRepo extends JpaRepository<VehicleIdleAlert, Long> {

	@Query("Select v from VehicleIdleAlert v where v.vehicle.dicvUser.userId=:userId and v.idleStartTime >=:idleStartTime "
			+ " and v.idleStopTime IS NOT NULL and v.timeSpent>=:timeSpent")
	public List<VehicleIdleAlert> getVehicleIdle(@Param(value = "userId") Integer userId,
			@Param(value = "idleStartTime") Calendar idleStartTime, @Param(value = "timeSpent") Long timeSpent);

}
