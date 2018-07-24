package com.dicv.truck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dicv.truck.dto.AlertTypeListDto;
import com.dicv.truck.dto.GeoNotificationDtlsDto;
import com.dicv.truck.service.AlertService;

@RestController
@RequestMapping("/onlinefleetmngmt/dicv")
public class AlertController {

	@Autowired
	private AlertService alertServices;

	@GetMapping("/getNotification")
	public GeoNotificationDtlsDto getNotification(@RequestParam("userId") int userId) {
		return alertServices.getNotification(ifReadOnlyUserChangeToRootAdmin(userId));
	}
	
	@GetMapping("/getAlertType")
	public AlertTypeListDto getAlertType() {

		return alertServices.getAlertType();
	}

	private Integer ifReadOnlyUserChangeToRootAdmin(Integer userId) {
		return (userId != null && userId.intValue() == 3) ? 2 : userId;
	}

}
