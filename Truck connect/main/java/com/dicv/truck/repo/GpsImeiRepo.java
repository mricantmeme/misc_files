package com.dicv.truck.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dicv.truck.model.GpsImei;

@Repository
public interface GpsImeiRepo extends CrudRepository<GpsImei, Integer> {

	@Query("Select count(g.gpsImeiId) FROM GpsImei g where g.gpsImei=:gpsImei and g.isDeleted=0 ")
	public Long findByGpsImeiNumber(@Param("gpsImei") Long gpsImei);

	@Query("Select count(g.gpsImeiId) FROM GpsImei g where g.gpsImei=:gpsImei and g.isDeleted=0")
	public Long findByERMGpsImeiNumber(@Param("gpsImei") Long gpsImei);

	@Query("Select g FROM GpsImei g where g.tabletImei=:tabletImei and g.isDeleted=:isDeleted")
	public List<GpsImei> getImeiByTabletImei(@Param("tabletImei") Long tabletImei);

	@Query("Select e from GpsImei e where e.isDeleted=0 and e.gpsTransmittedTime is NULL")
	public List<GpsImei> gpsGpsImeiByTransmittedTime();

	@Query("Select e from GpsImei e where e.isDeleted=0 where e.ermVehicle.ermVehicleId is NOT NULL")
	public List<GpsImei> getErmVehicleByStatus();

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update GpsImei v set v.gpsTransmittedTime=:gpsTransmittedTime,v.updatedDate=:updatedDate where v.gpsImeiId =:gpsImeiId")
	public void updateGpsTime(@Param("gpsTransmittedTime") Timestamp gpsTransmittedTime,
			@Param("updatedDate") Timestamp updatedDate, @Param("gpsImeiId") Integer gpsImeiId);

}
