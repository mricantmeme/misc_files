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

import com.dicv.truck.dto.DashBoardListDto;
import com.dicv.truck.dto.OwnerDtlsDto;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.dto.VehicleDashboardListDto;
import com.dicv.truck.dto.VehicleDtlsDto;
import com.dicv.truck.dto.VehicleDto;
import com.dicv.truck.dto.VehicleInfoListDto;
import com.dicv.truck.dto.VehicleSpeedListDto;
import com.dicv.truck.dto.VehicleUtilizationInputDto;
import com.dicv.truck.dto.VehicleUtilizationListDto;
import com.dicv.truck.exception.DataNotFoundException;
import com.dicv.truck.exception.InvalidValueException;
import com.dicv.truck.exception.ServerException;
import com.dicv.truck.service.ValidationService;
import com.dicv.truck.service.VehicleServices;
import com.dicv.truck.utility.DicvConstants;

@RestController
@RequestMapping("/onlinefleetmngmt/dicv")
public class VehicleController {

	@Autowired
	private ValidationService validationService;

	@Autowired
	private VehicleServices vehicleServices;

	@PostMapping("/createVehicle")
	public @ResponseBody StatusMessageDto createVehicle(@RequestBody VehicleDto vehicleDto) throws ServerException {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		if (vehicleDto.getVehicleId() == null)
			vehicleDto.setVehicleId(0);
		String response = validationService.validationForVehicleModify(vehicleDto);
		if (response.equals(DicvConstants.SUCCESS)) {
			return vehicleServices.createVehicle(vehicleDto);
		} else {
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage(response);
		}
		return statusMessageDto;
	}

	@GetMapping("/getVehicleList")
	public @ResponseBody OwnerDtlsDto getVehicleList(@RequestParam Integer userId, @RequestParam String page,
			@RequestParam Integer startRow, @RequestParam Integer endRow) throws ServerException {
		OwnerDtlsDto ownerDtlsDto = new OwnerDtlsDto();
		if (null == userId || userId <= 0) {
			ownerDtlsDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			ownerDtlsDto.setMessage("Kindly provide valid vehicle or user information.");
			return ownerDtlsDto;
		}
		return vehicleServices.getVehicleList(userId, page, startRow, endRow);
	}

	@GetMapping("/getAllVehiclesList")
	public @ResponseBody OwnerDtlsDto getAllVehiclesList(@RequestParam Integer userId, @RequestParam Integer rowPerPage,
			@RequestParam Integer page, @RequestParam(required = false) String keyword) throws ServerException {
		OwnerDtlsDto ownerDtlsDto = new OwnerDtlsDto();
		if (null == userId || userId <= 0) {
			ownerDtlsDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			ownerDtlsDto.setMessage("Kindly provide valid vehicle or user information.");
			return ownerDtlsDto;
		}
		return vehicleServices.getAllVehiclesList(userId, keyword, page, rowPerPage);
	}

	@GetMapping("/getVehicleDetail")
	public @ResponseBody VehicleDtlsDto getVehicleDetail(@RequestParam Integer userId,
			@RequestParam Integer vehicleId) {
		if (null == userId || userId <= 0 || null == vehicleId || vehicleId <= 0) {
			throw new InvalidValueException("Kindly provide valid vehicle or user information.");
		}
		return vehicleServices.getVehicleDetail(userId, vehicleId);
	}

	@GetMapping("/getActiveVehicles")
	public @ResponseBody VehicleInfoListDto getActiveVehicles(@RequestParam Integer userId)
			throws DataNotFoundException, InvalidValueException, ServerException {
		VehicleInfoListDto vehicleInfoListDto = new VehicleInfoListDto();
		if (null == userId || userId <= 0) {
			vehicleInfoListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			vehicleInfoListDto.setMessage("Kindly provide valid vehicle or user information.");
			return vehicleInfoListDto;
		}
		return vehicleServices.getActiveVehicles(userId);
	}

	@GetMapping("/activeVehicles")
	public @ResponseBody VehicleInfoListDto getVehicleWithGroupInfo(@RequestParam Integer userId)
			throws DataNotFoundException, InvalidValueException, ServerException {
		VehicleInfoListDto vehicleInfoListDto = new VehicleInfoListDto();
		if (null == userId || userId <= 0) {
			vehicleInfoListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			vehicleInfoListDto.setMessage("Kindly provide valid vehicle or user information.");
			return vehicleInfoListDto;
		}
		return vehicleServices.getVehicleWithGroupInfo(userId);
	}

	@GetMapping("/dashBoard")
	public @ResponseBody DashBoardListDto getDashBoardInfo(@RequestParam Integer userId) throws ServerException {
		return vehicleServices.getDashBoardInfo(userId);
	}

	@GetMapping("/vehicleDashboardReport")
	public @ResponseBody VehicleDashboardListDto vehicleDashboardReport(
			VehicleUtilizationInputDto vehicleUtilizationInputDto) {
		if (null != vehicleUtilizationInputDto.getUserId() && null != vehicleUtilizationInputDto.getFromDate()
				&& null != vehicleUtilizationInputDto.getToDate()) {
			return vehicleServices.vehicleDashboardReport(vehicleUtilizationInputDto);
		}
		if (null == vehicleUtilizationInputDto.getUserId()) {
			throw new InvalidValueException("Invalid UserId");
		}
		if (null == vehicleUtilizationInputDto.getFromDate()) {
			throw new InvalidValueException("Invalid FromDate");
		}
		if (null == vehicleUtilizationInputDto.getToDate()) {
			throw new InvalidValueException("Invalid ToDate");
		}
		return null;
	}

	@GetMapping("/vehicleUtilizationReport")
	public @ResponseBody VehicleUtilizationListDto vehicleUtilizationReport(
			VehicleUtilizationInputDto vehicleUtilizationInputDto) {
		VehicleUtilizationListDto vehicleUtilizationListDto = new VehicleUtilizationListDto();
		if (null != vehicleUtilizationInputDto.getUserId() && null != vehicleUtilizationInputDto.getFromDate()
				&& null != vehicleUtilizationInputDto.getToDate()) {
			return vehicleServices.vehicleUtilizationReport(vehicleUtilizationInputDto);
		} else {
			if (null == vehicleUtilizationInputDto.getUserId()) {
				vehicleUtilizationListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				vehicleUtilizationListDto.setMessage("User Not Available");
				return vehicleUtilizationListDto;
			}
			if (null == vehicleUtilizationInputDto.getFromDate()) {
				vehicleUtilizationListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				vehicleUtilizationListDto.setMessage("Invalid FromDate");
				return vehicleUtilizationListDto;
			}
			if (null == vehicleUtilizationInputDto.getToDate()) {
				vehicleUtilizationListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				vehicleUtilizationListDto.setMessage("Invalid ToDate");
				return vehicleUtilizationListDto;
			}
		}
		return vehicleUtilizationListDto;
	}

	@GetMapping("/vehicleUtilizationGraphReport")
	public @ResponseBody VehicleSpeedListDto vehicleUtilizationGraphReport(
			VehicleUtilizationInputDto vehicleUtilizationInputDto) throws DataNotFoundException, ServerException {
		VehicleSpeedListDto vehicleSpeedListDto = new VehicleSpeedListDto();
		if (null != vehicleUtilizationInputDto.getUserId() && null != vehicleUtilizationInputDto.getFromDate()
				&& null != vehicleUtilizationInputDto.getToDate()) {
			return vehicleServices.vehicleUtilizationGraphReport(vehicleUtilizationInputDto);
		} else {
			if (null == vehicleUtilizationInputDto.getUserId()) {
				vehicleSpeedListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				vehicleSpeedListDto.setMessage("Invalid UserId");
				return vehicleSpeedListDto;

			}
			if (null == vehicleUtilizationInputDto.getFromDate()) {
				vehicleSpeedListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				vehicleSpeedListDto.setMessage("Invalid FromDate");
				return vehicleSpeedListDto;
			}
			if (null == vehicleUtilizationInputDto.getToDate()) {
				vehicleSpeedListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				vehicleSpeedListDto.setMessage("Invalid ToDate");
				return vehicleSpeedListDto;
			}
		}
		return vehicleSpeedListDto;
	}

}
