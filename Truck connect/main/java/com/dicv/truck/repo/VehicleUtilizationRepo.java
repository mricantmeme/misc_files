package com.dicv.truck.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dicv.truck.dto.VehicleDashboardResponseDto;
import com.dicv.truck.dto.VehicleUtilizationResponseDto;
import com.dicv.truck.model.VehicleUtilization;

@Repository
public interface VehicleUtilizationRepo extends JpaRepository<VehicleUtilization, Long> {

	@Query("Select new com.dicv.truck.dto.VehicleDashboardResponseDto(v.vehicle.vehicleId,AVG(v.utilization)) from "
			+ " VehicleUtilization v where v.vehicle.vehicleId IN :vehicleIds and v.reportDate>=:fromDate "
			+ " and v.reportDate<=:toDate group by v.vehicle.vehicleId order by AVG(v.utilization) desc")
	public List<VehicleDashboardResponseDto> getAvgUtilization(@Param("vehicleIds") List<Integer> vehicleIds,
			@Param("fromDate") Date fromDate, @Param("toDate") Date toDate, Pageable pageable);

	@Query("Select new com.dicv.truck.dto.VehicleUtilizationResponseDto(v.vehicle.vehicleId,AVG(v.utilization),SUM(v.totalDrivingTime),"
			+ "MAX(v.maximumSpeed),SUM(v.totalDistance),SUM(v.totalUpTime)) from VehicleUtilization v where v.vehicle.vehicleId IN :vehicleIds and v.reportDate>=:fromDate"
			+ " and v.reportDate<=:toDate group by v.vehicle.vehicleId order by AVG(v.utilization) desc ")
	public List<VehicleUtilizationResponseDto> getCalucaltedVehicleUtilization(
			@Param("vehicleIds") List<Integer> vehicleIds, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate, Pageable pageable);

	@Query("select v from VehicleUtilization v where v.vehicle.vehicleId IN :vehicleIds and v.vehicle.isDeleted=0 and"
			+ " v.reportDate >= :fromDate and v.reportDate <= :toDate")
	public List<VehicleUtilization> getVehicleUtilization(@Param("vehicleIds") List<Integer> vehicleIds,
			@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

}
