package com.dicv.truck.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dicv.truck.dto.AppRegistrationDto;
import com.dicv.truck.dto.GpsImeiDtlsDto;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.dto.TripImageUpload;
import com.dicv.truck.dto.TripStartDto;
import com.dicv.truck.dto.TripStopDto;
import com.dicv.truck.service.TriverAppService;

@RestController
@RequestMapping("/onlinefleetmngmt/dicv")
public class TriverAppController {

	@Autowired
	private TriverAppService mobileAppService;

	@GetMapping("/getVehicleImei")
	public @ResponseBody GpsImeiDtlsDto getVehicleImei(@RequestParam Long tabletImei) {

		return mobileAppService.getVehicleImei(tabletImei);

	}

	@PostMapping("/registerForPushNotification")
	public @ResponseBody StatusMessageDto registerForPushNotification(@RequestBody AppRegistrationDto appRegistration) {
		return mobileAppService.registerForPushNotification(appRegistration);

	}

	@PostMapping("/startTrip")
	public @ResponseBody TripStartDto startTrip(@RequestBody TripStartDto tripStartDto) {
		return mobileAppService.startTrip(tripStartDto);

	}

	@PostMapping("/stopTrip")
	public @ResponseBody StatusMessageDto stopTrip(@RequestBody TripStopDto tripStopDto) {
		return mobileAppService.stopTrip(tripStopDto);

	}

	@PostMapping("/imageUpload")
	public @ResponseBody StatusMessageDto imageUpload(@RequestBody TripImageUpload tripImageUpload) {
		if (!tripImageUpload.getFile().isEmpty() && tripImageUpload.getTripId() != null
				&& tripImageUpload.getType() != null) {
			return mobileAppService.imageUpload(tripImageUpload);
		} else {
			StatusMessageDto statusMessageDto = new StatusMessageDto();
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage("Enter All Mandatory Details !!!");
			return statusMessageDto;

		}

	}

}
