package com.dicv.truck.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.dto.GeoFenceReportDto;
import com.dicv.truck.model.GeoFenceReport;

@Repository
public interface GeoFenceReportRepo extends JpaRepository<GeoFenceReport, Long> {

	@Query("Select new com.dicv.truck.dto.GeoFenceReportDto(g.geoFenceInfo.geoFenceName,g.vehicle.registrationId,g.dicvUser.userId, g.geoFenceEntryTime,"
			+ "	g.geoFenceExitTime,g.timeSpent) from GeoFenceReport g where g.vehicle.vehicleId IN :vehicleIds and g.geoFenceInfo.geoFenceId IN :geoFenceIds "
			+ "  and g.dicvUser.userId =:userId AND g.geoFenceEntryTime>= :fromDate AND g.geoFenceExitTime <=:toDate ")
	public List<GeoFenceReportDto> getGeoFenceReport(@Param("userId") Integer userId,
			@Param("vehicleIds") List<Integer> vehicleIds, @Param("geoFenceIds") List<Integer> geoFenceIds,
			@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

}
