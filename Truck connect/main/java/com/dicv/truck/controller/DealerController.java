package com.dicv.truck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dicv.truck.dto.DealerVehicleDto;
import com.dicv.truck.dto.DealerVehicleDtoList;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.dto.VehicleFileUploadDto;
import com.dicv.truck.dto.VehicleFileUploadListDto;
import com.dicv.truck.dto.VehicleUploadFileList;
import com.dicv.truck.exception.InvalidValueException;
import com.dicv.truck.service.DealerService;

@RestController
@RequestMapping("/dicv/dealer")
public class DealerController {

	@Autowired
	private DealerService dealerServices;

	@PostMapping()
	public @ResponseBody StatusMessageDto save(@RequestBody DealerVehicleDto vehicleDto) {
		return dealerServices.save(vehicleDto);
	}

	@GetMapping()
	public @ResponseBody DealerVehicleDtoList getVehicleDetail(@RequestParam Integer userId,
			@RequestParam(required = false) Integer vehicleId) {
		if (null == userId || userId <= 0) {
			throw new InvalidValueException("Kindly provide valid user information.");
		}
		return dealerServices.getVehicleDetail(userId, vehicleId);
	}

	@PostMapping("/upload")
	public @ResponseBody StatusMessageDto upload(@RequestBody VehicleUploadFileList vehicleDto) {
		return dealerServices.upload(vehicleDto);
	}

	@GetMapping("/viewfiles")
	public @ResponseBody VehicleFileUploadListDto viewfiles(@RequestParam Integer userId,
			@RequestParam(required = true) Integer vehicleId) {
		if (null == userId || userId <= 0) {
			throw new InvalidValueException("Kindly provide valid user information.");
		}
		return dealerServices.viewfiles(userId, vehicleId);
	}
	
	@GetMapping("/deleteFile")
	public @ResponseBody StatusMessageDto deleteFile(@RequestParam Integer userId,
			@RequestParam(required = true) Integer fileId) {
		if (null == userId || userId <= 0) {
			throw new InvalidValueException("Kindly provide valid user information.");
		}
		return dealerServices.deleteFile(userId, fileId);
	}

	@GetMapping("/downloadFile")
	public @ResponseBody VehicleFileUploadDto downloadFile(@RequestParam Integer userId,
			@RequestParam(required = true) Integer fileId) {
		if (null == userId || userId <= 0) {
			throw new InvalidValueException("Kindly provide valid user information.");
		}
		return dealerServices.downloadFile(userId, fileId);
	}

}
