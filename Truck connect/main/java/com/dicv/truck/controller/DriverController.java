package com.dicv.truck.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dicv.truck.dto.DriverAnalysisInsightsDto;
import com.dicv.truck.dto.DriverAnalysisLineChartListDto;
import com.dicv.truck.dto.DriverAnalysisListDto;
import com.dicv.truck.dto.DriverTripPlaybackListDto;
import com.dicv.truck.dto.SpeedCountDto;
import com.dicv.truck.service.DriverService;

@RestController
@RequestMapping("/onlinefleetmngmt/dicv")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@GetMapping("/getDriverAnalysisFromTrip")
	public @ResponseBody DriverAnalysisListDto getDriverAnalysisFromTrip(@RequestParam("startDate") Date startDate,
			@RequestParam("endDate") Date endDate, @RequestParam("userId") int userId,
			@RequestParam(name = "driverId", required = false) String driverId,
			@RequestParam(name = "groupId", required = false) Integer groupId) {

		if (groupId != null)
			return driverService.getDriverAnalysisFromTrip(startDate, endDate, userId, groupId);
		else
			return driverService.getDriverAnalysisFromTrip(startDate, endDate, userId, driverId);

	}

	@GetMapping("/getDriverAnalysisFromMobileTrip")
	public @ResponseBody DriverAnalysisListDto getDriverAnalysisFromMobileTrip(
			@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate,
			@RequestParam("userId") int userId, @RequestParam(name = "driverId", required = false) String driverId,
			@RequestParam(name = "groupId", required = false) Integer groupId) {

		if (groupId != null)
			return driverService.getDriverAnalysisFromTrip(startDate, endDate, userId, groupId);
		else
			return driverService.getDriverAnalysisFromTrip(startDate, endDate, userId, driverId);

	}

	@GetMapping("/driverAnalysisForInsights")
	public @ResponseBody DriverAnalysisInsightsDto driverAnalysisForInsights(@RequestParam("startDate") Date startDate,
			@RequestParam("endDate") Date endDate, @RequestParam("userId") int userId,
			@RequestParam(name = "driverId") Integer driverId) {

		return driverService.driverAnalysisForInsights(startDate, endDate, userId, driverId);

	}

	@GetMapping("/driverAnalysisForPerformanceGraph")
	public @ResponseBody DriverAnalysisLineChartListDto driverAnalysisForPerformanceGraph(
			@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate,
			@RequestParam("userId") int userId, @RequestParam(name = "driverId") Integer driverId) {

		return driverService.driverAnalysisForPerformanceGraph(startDate, endDate, userId, driverId);

	}

	@GetMapping("/getSpeedingCountForDriver")
	public @ResponseBody SpeedCountDto getSpeedingCountForDriver(Integer driverId, Date startDate, Date endDate) {

		return driverService.getSpeedingCountForDriver(driverId, startDate, endDate);

	}

	@GetMapping("/myFleetPlayback")
	public @ResponseBody DriverTripPlaybackListDto getMyFleetPlayback(Integer vehicleId, Integer userId,
			String fromDate, String toDate) {
		DriverTripPlaybackListDto driverAnalysisListDto = new DriverTripPlaybackListDto();
		if (null != vehicleId && null != fromDate && null != toDate && null != userId) {

			return driverService.getMyFleetPlayback(vehicleId, userId, fromDate, toDate);

		} else {
			driverAnalysisListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			driverAnalysisListDto.setMessage("Please provide valid detail !!!");
		}
		return driverAnalysisListDto;
	}

}
