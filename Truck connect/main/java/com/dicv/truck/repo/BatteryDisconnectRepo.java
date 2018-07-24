package com.dicv.truck.repo;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.BatteryDisconnect;

@Repository
public interface BatteryDisconnectRepo extends JpaRepository<BatteryDisconnect, Long> {

	@Query("Select v from BatteryDisconnect v where v.vehicle.dicvUser.userId=:userId and v.vehicle.isDeleted=0 and v.gpsTime >=:gpsTime")
	public List<BatteryDisconnect> getBatteryDisconnectAlert(@Param(value = "userId") Integer userId,
			@Param(value = "gpsTime") Calendar gpsTime);

	@Query("Select v from BatteryDisconnect v where v.vehicle.isDeleted=0 and v.emailSent =0")
	public List<BatteryDisconnect> getBatteryDisconnectAlert(Pageable pageable);

}
