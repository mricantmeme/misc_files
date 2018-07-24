package com.dicv.truck.repo;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.BatteryHealth;

@Repository
public interface BatteryHealthRepo extends JpaRepository<BatteryHealth, Long> {

	@Query("Select v from BatteryHealth v where v.vehicle.dicvUser.userId=:userId and v.vehicle.isDeleted=0 and v.gpsTime >=:gpsTime "
			+ " and v.batteryLevel<:batteryLevel")
	public List<BatteryHealth> getBatteryHealthAlert(@Param(value = "userId") Integer userId,
			@Param(value = "gpsTime") Calendar gpsTime, @Param(value = "batteryLevel") Integer batteryLevel);

	@Query("Select v from BatteryHealth v where v.vehicle.isDeleted=0 and v.emailSent =0 "
			+ " and v.batteryLevel<:batteryLevel")
	public List<BatteryHealth> getBatteryHealthAlert(@Param(value = "batteryLevel") Integer batteryLevel,
			Pageable pageable);

}
