package com.dicv.truck.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.GeoFenceInfo;
import com.dicv.truck.model.Vehicle;
import com.dicv.truck.model.VehicleToGeoFence;

@Repository
public interface VehicleToGeoFenceRepo extends JpaRepository<VehicleToGeoFence, Integer> {


	@Query("Select v FROM VehicleToGeoFence v where v.vehicle.vehicleId=:vehicleId and "
			+ " v.vehicle.isDeleted=0 and v.isDeleted=0")
	public Long getGeoFenceListCountByVehicle(@Param("vehicleId") Integer vehicleId);

	@Query("select v.geoFenceInfo from VehicleToGeoFence v where v.geoFenceInfo.isDeleted = 0 "
			+ "and v.isDeleted=0 and v.geoFenceInfo.dicvUser.userId=:userId and v.vehicle.vehicleId=:vehicleId")
	public List<GeoFenceInfo> getGeoFenceListByVehicle(@Param("userId") Integer userId,
			@Param("vehicleId") Integer vehicleId);

	@Query("select v.vehicle from VehicleToGeoFence v where v.isDeleted=0 and  v.geoFenceInfo.geoFenceId =:geoFenceId and v.vehicle.isDeleted = 0")
	public List<Vehicle> getVehicleList(@Param("geoFenceId") Integer geoFenceId);
	
	@Query("select v from VehicleToGeoFence v where v.isDeleted=0 and  v.geoFenceInfo.geoFenceId =:geoFenceId and v.vehicle.isDeleted = 0")
	public List<VehicleToGeoFence> getVehicleToGeoFenceList(@Param("geoFenceId") Integer geoFenceId);
}
