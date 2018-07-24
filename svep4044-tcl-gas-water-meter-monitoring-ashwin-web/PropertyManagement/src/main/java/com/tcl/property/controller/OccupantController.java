/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.property.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcl.property.dto.OccupantDeleteReqestDto;
import com.tcl.property.dto.OccupantRegisterRequestDto;
import com.tcl.property.dto.OccupantRegisterResponseDto;
import com.tcl.property.dto.OccupantResponseDto;
import com.tcl.property.dto.OccupantStatusRequestDto;
import com.tcl.property.dto.OccupantUpdateReqestDto;
import com.tcl.property.response.ListResponse;
import com.tcl.property.response.Response;
import com.tcl.property.service.OccupantService;

@RestController
@RequestMapping("/occupant")
public class OccupantController {

	@Autowired
	private OccupantService occupantService;

	@GetMapping("/test")
	public @ResponseBody String test() {
		return "TestSuccess";
	}

	@PostMapping
	public @ResponseBody Response<OccupantRegisterResponseDto> createOccupant(@Valid @RequestBody OccupantRegisterRequestDto requestDto, HttpServletRequest request) {
		String userId = request.getParameter("userId");
		requestDto.setUserId(userId);
		return occupantService.createOccupant(requestDto);
	}

	@PutMapping
	public @ResponseBody Response<String> updateOccupant(@Valid @RequestBody OccupantUpdateReqestDto requestDto,HttpServletRequest request) {
		String userId = request.getParameter("userId");
		requestDto.setUserId(userId);
		return occupantService.updateOccupant(requestDto);
	}

	@DeleteMapping
	public @ResponseBody Response<Boolean> deleteOccupant(@Valid @RequestBody OccupantDeleteReqestDto requestDto) {
		return occupantService.deleteOccupant(requestDto);
	}

	@GetMapping
	public @ResponseBody ListResponse<List<OccupantResponseDto>> listOccupants(
			@RequestParam(required = true) String propertyId, @RequestParam(required = false) String search,
			@RequestParam int page, @RequestParam int size) {
		return occupantService.listOccupants(propertyId, search, page, size);
	}

	@GetMapping("/profile/{occupantId}")
	public @ResponseBody Response<OccupantResponseDto> getOccupantsDetails(
			@PathVariable("occupantId") String occupantId) {
		return occupantService.getOccupantDetails(occupantId);
	}

	@PutMapping("/status")
	public @ResponseBody Response<Boolean> updateOccupantStatus(@Valid @RequestBody OccupantStatusRequestDto requestDto,
			HttpServletRequest request) {
		String userId = request.getParameter("userId");
		requestDto.setUserId(userId);
		return occupantService.updateOccupantStatus(requestDto);
	}
	
	@GetMapping("/list")
	public @ResponseBody ListResponse<List<OccupantResponseDto>> filterListOccupant(@RequestParam(required = true)  String propertyId,@RequestParam(required = false) List<String> filters,@RequestParam(required = false) String sortByFieldName,@RequestParam(required = false) String sortType, @RequestParam("page") Integer page,@RequestParam("size") Integer size) {
		return occupantService.filterListOccupant(filters,sortByFieldName,sortType, page, size,propertyId);
	}
}
