package com.dicv.truck.repo;

import java.util.Date;

import com.dicv.truck.dto.DriverAnalysisLineChartListDto;
import com.dicv.truck.dto.DriverAnalysisListDto;
import com.dicv.truck.dto.DriverTripPlaybackListDto;
import com.dicv.truck.dto.SpeedCountDto;

public interface DriverDao {

	public DriverAnalysisListDto getDriverAnalysisFromTrip(Date startDate, Date endDate, int userId, String driverId);

	public DriverAnalysisListDto getDriverAnalysisFromTrip(Date startDate, Date endDate, int userId, Integer groupId);

	public DriverAnalysisLineChartListDto getDriverAnalysisForPerformanceGraph(Date startDate, Date endDate, int userId,
			int driverId);

	public DriverTripPlaybackListDto getDriverSpeedingAndStopLocations(Date startDate, Date endDate, Integer ownerId,
			Integer driverId);

	public SpeedCountDto getSpeedingCountForDriver(Integer driverId, Date startDate, Date endDate);

}
