package com.tcl.devices.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcl.devices.dto.ConsumptionDetailUpdateReqDto;
import com.tcl.devices.dto.ConsumptionResponseDto;
import com.tcl.devices.response.ListResponse;
import com.tcl.devices.response.Response;
import com.tcl.devices.service.ConsumptionService;

@RestController
@RequestMapping("/consumption")
public class ConsumptionController {

	@Autowired
	private ConsumptionService consumptionService;

	@GetMapping("/list")
	public @ResponseBody ListResponse<List<ConsumptionResponseDto>> filterListConsumptionDetails(
			@RequestParam(required = false) List<String> filters,
			@RequestParam(required = false) String sortByFieldName, @RequestParam(required = false) String sortType,
			@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		return consumptionService.filterListConsumptionDetails(filters, sortByFieldName, sortType, page, size);
	}

	@PutMapping
	public @ResponseBody Response<Boolean> updateConsumptionDetail(
			@Valid @RequestBody ConsumptionDetailUpdateReqDto requestDto) {
		return consumptionService.updateConsumptionDetail(requestDto);
	}

}
