package com.dicv.truck.repo;

import java.util.Date;
import java.util.List;

import com.dicv.truck.dto.NightDrivingReportDto;
import com.dicv.truck.dto.OverSpeedReportDto;
import com.dicv.truck.dto.SpeedReportDto;

public interface ReportDao {

	public List<SpeedReportDto> getSpeedReport(List<Integer> vehicle, Date fromDate, Date toDate, Integer speedLimit);

	public List<OverSpeedReportDto> getSpeedReportMaxLocation(List<Integer> vehicle, Date fromDate, Date toDate,
			Integer speedLimit);

	public List<NightDrivingReportDto> getNightDrivingReport(Integer userId, List<Integer> vehicleIds, Date fromDate,
			Date toDate, String query);

}
