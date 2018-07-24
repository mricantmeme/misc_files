package com.dicv.truck.repo;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.FuelDropAlert;

@Repository
public interface FuelDropRepo extends JpaRepository<FuelDropAlert, Integer> {

	@Query("Select v from FuelDropAlert v where v.vehicle.dicvUser.userId=:userId and v.vehicle.isDeleted=0 and v.fuelDropTime >=:fuelDropTime "
			+ " and (v.fromLevel-v.toLevel)>=:dropLevel")
	public List<FuelDropAlert> getFuelDropAlert(@Param(value = "userId") Integer userId,
			@Param(value = "fuelDropTime") Calendar fuelDropTime, @Param(value = "dropLevel") Integer dropLevel);

	@Query("Select v from FuelDropAlert v where  v.vehicle.isDeleted=0 and v.emailSent=0 "
			+ " and (v.fromLevel-v.toLevel)>=:dropLevel")
	public List<FuelDropAlert> getFuelDropMailAlert(@Param(value = "dropLevel") Integer dropLevel, Pageable pageable);

}
