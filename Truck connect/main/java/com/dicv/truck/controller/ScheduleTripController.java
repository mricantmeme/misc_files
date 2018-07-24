package com.dicv.truck.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dicv.truck.dto.DriversStatus;
import com.dicv.truck.dto.FavoriteLocationDtlsDto;
import com.dicv.truck.dto.FavoriteLocationListDto;
import com.dicv.truck.dto.RoutePlanDtlsDto;
import com.dicv.truck.dto.RoutePlanDto;
import com.dicv.truck.dto.ScheduledTripEditDto;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.dto.VehiclesStatus;
import com.dicv.truck.service.ScheduleTripService;

@RestController
@RequestMapping("/onlinefleetmngmt/dicv")
public class ScheduleTripController {


	@Autowired
	private ScheduleTripService scheduleTripService;

	@GetMapping("/getAllDriverStatus")
	public @ResponseBody DriversStatus getAllDriverStatus(@RequestParam Integer userId, @RequestParam Date fromDate,
			@RequestParam Date toDate, @RequestParam(required = false) Integer scheduledTripId) {
		return scheduleTripService.getAllDriverStatus(userId, scheduledTripId, fromDate, toDate);
	}

	@GetMapping("/getAllVehicleStatus")
	public @ResponseBody VehiclesStatus getAllVehicleStatus(@RequestParam Integer userId, @RequestParam Date fromDate,
			@RequestParam Date toDate, @RequestParam(required = false) Integer scheduledTripId) {

		return scheduleTripService.getAllVehicleStatus(userId, scheduledTripId, fromDate, toDate);
	}

	@GetMapping("/getRoutePlan")
	public @ResponseBody RoutePlanDtlsDto getRoutePlan(@RequestParam Integer scheduledTripId,
			@RequestParam Date currentDate) {
		return scheduleTripService.getRoutePlan(scheduledTripId, currentDate);
	}
	
	@PostMapping("/setRoutePlan")
	public @ResponseBody StatusMessageDto setRoutePlan(@RequestBody RoutePlanDto routePlan) {
		return scheduleTripService.setRoutePlan(routePlan);
	}
	
	@PostMapping("/editRoutePlan")
	public @ResponseBody StatusMessageDto editRoutePlan(@RequestBody ScheduledTripEditDto scheduledTripEditDto) {
		return scheduleTripService.editRoutePlan(scheduledTripEditDto);
	}

	@GetMapping("/getFavoriteLocation")
	public @ResponseBody FavoriteLocationListDto getFavoriteLocation(@RequestParam Integer userId) {
		return scheduleTripService.getFavoriteLocation(userId);
	}
	
	@PostMapping("/saveFavoriteLocation")
	public @ResponseBody StatusMessageDto saveFavoriteLocation(@RequestBody FavoriteLocationDtlsDto favoriteLocationDtls) {
		return scheduleTripService.saveFavoriteLocation(favoriteLocationDtls);
	}

}
