package com.dicv.truck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dicv.truck.dto.GPSOwnerDetailDto;
import com.dicv.truck.service.MyFleetService;

@RestController
@RequestMapping("/onlinefleetmngmt/dicv")
public class MyFleetController {

	@Autowired
	private MyFleetService myFleetService;

	@GetMapping("/getGpsMyFleet")
	public @ResponseBody GPSOwnerDetailDto getGpsMyFleet(@RequestParam Integer userId) {
		return myFleetService.getGpsMyFleet(userId);
		//return null;
	}

}
