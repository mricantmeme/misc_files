package com.dicv.truck.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.dto.DriverTripPlaybackDto;
import com.dicv.truck.dto.VehicleStatusGraphReportDto;
import com.dicv.truck.model.GpsVehicleParameter;

@Repository
public interface GpsRepo extends JpaRepository<GpsVehicleParameter, Long> {

	@Query("Select new com.dicv.truck.dto.VehicleStatusGraphReportDto(g.gpsTime,g.gpsSpkm,g.gpsDate) from GpsVehicleParameter g where"
			+ " g.gpsImei =:imeiNo and g.gpsDate >=:fromDate and g.engineON=1 and g.gpsDate <=:toDate and g.gpsSpkm>0 order by gpsTime asc")
	public List<VehicleStatusGraphReportDto> getVehicleSpeedList(@Param("imeiNo") Long imeiNo,
			@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

	@Query("Select g from GpsVehicleParameter g order by gpsVehicleParamId desc")
	public List<GpsVehicleParameter> getGpsParameter(Pageable pageable);

	@Query("Select new com.dicv.truck.dto.DriverTripPlaybackDto(g.gpsSpkm,g.gpsTime,g.gpsLatitude,g.gpsLongitude) from "
			+ " GpsVehicleParameter g where g.gpsImei=:gpsImei and g.gpsSpkm>0 and g.gpsTime>= :fromDate and g.gpsTime<=:toDate order by gpsTime asc")
	public List<DriverTripPlaybackDto> getMyFleetDetails(@Param("gpsImei") Long gpsImei,
			@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

	@Query("Select new com.dicv.truck.dto.VehicleStatusGraphReportDto(g.gpsTime,g.gpsSpkm,g.gpsDate) from GpsVehicleParameter g where"
			+ " g.gpsImei =:imeiNo  order by gpsTime asc")
	public List<VehicleStatusGraphReportDto> testDateTime(@Param("imeiNo") Long imeiNo, Pageable pageable);

	@Query("Select g from GpsVehicleParameter g where g.gpsImei=:gpsImei order by gpsVehicleParamId asc")
	public List<GpsVehicleParameter> getGpsParameter(@Param("gpsImei") Long gpsImei, Pageable pageable);

	@Query("Select g from GpsVehicleParameter g where g.gpsImei=:gpsImei order by gpsVehicleParamId desc")
	public List<GpsVehicleParameter> getLastGpsParameter(@Param("gpsImei") Long gpsImei, Pageable pageable);
}
