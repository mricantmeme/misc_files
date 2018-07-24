package com.dicv.truck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dicv.truck.dto.ErmVehicleDto;
import com.dicv.truck.dto.ErmVehicleListDto;
import com.dicv.truck.dto.ErmVehicleSubmit;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.exception.InvalidValueException;
import com.dicv.truck.service.ErmService;

@RestController
@RequestMapping("/dicv/erm/")
public class ErmController {

	@Autowired
	private ErmService ermService;

	@PostMapping()
	public @ResponseBody StatusMessageDto save(@RequestBody ErmVehicleDto ermVehicleDto) {
		return ermService.save(ermVehicleDto);
	}

	@PostMapping("/submit")
	public @ResponseBody StatusMessageDto submit(@RequestBody ErmVehicleSubmit ermVehicleSubmit) {
		return ermService.submit(ermVehicleSubmit);
	}

	@GetMapping()
	public @ResponseBody ErmVehicleListDto getVehicleDetail(@RequestParam Integer userId,
			@RequestParam(required = false) Integer ermVehicleId) {
		if (null == userId || userId <= 0) {
			throw new InvalidValueException("Kindly provide valid vehicle or user information.");
		}
		return ermService.getVehicleDetail(userId, ermVehicleId);
	}

}
