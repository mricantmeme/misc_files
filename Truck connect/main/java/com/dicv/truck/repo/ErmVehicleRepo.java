package com.dicv.truck.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dicv.truck.model.ErmVehicle;

@Repository
public interface ErmVehicleRepo extends CrudRepository<ErmVehicle, Integer> {

	@Query("Select e from ErmVehicle e where e.dicvUser.userId=:userId")
	public List<ErmVehicle> getErmVehicleList(@Param("userId") Integer userId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update ErmVehicle v set v.lastReceivedTime=:lastReceivedTime,v.updatedTime=:updatedTime where v.ermVehicleId =:ermVehicleId")
	public void updateLastGpsTransmission(@Param("lastReceivedTime") Timestamp lastReceivedTime,
			@Param("updatedTime") Timestamp updatedTime, @Param("ermVehicleId") Integer ermVehicleId);

}
