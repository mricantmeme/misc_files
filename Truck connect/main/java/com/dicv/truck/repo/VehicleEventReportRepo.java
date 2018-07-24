package com.dicv.truck.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.model.VehicleEventReport;

@Repository
public interface VehicleEventReportRepo extends JpaRepository<VehicleEventReport, Long> {

	@Query("select v.vehicleId,SUM(v.gpsCount) from VehicleEventReport v where  v.vehicleId IN :vehicleIds and v.gpsDate >= :fromDate and "
			+ "v.gpsDate <= :toDate  group by vehicleId")
	public List<Object[]> getInactiveVehicleList(@Param("vehicleIds") List<Integer> vehicleIds,
			@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

}
