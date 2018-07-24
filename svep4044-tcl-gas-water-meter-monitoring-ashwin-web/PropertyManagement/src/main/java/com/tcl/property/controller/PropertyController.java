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

import com.tcl.property.dto.PropertyDeleteReqDto;
import com.tcl.property.dto.PropertyRegRequestDto;
import com.tcl.property.dto.PropertyRegResponseDto;
import com.tcl.property.dto.PropertyResponseDto;
import com.tcl.property.dto.PropertyStatusReqDto;
import com.tcl.property.dto.PropertyUpdateReqDto;
import com.tcl.property.response.ListResponse;
import com.tcl.property.response.Response;
import com.tcl.property.service.PropertyService;

@RestController
@RequestMapping("/property")
public class PropertyController {

	@Autowired
	private PropertyService propertyService;

	@GetMapping("/test")
	public @ResponseBody String test() {
		return "TestSuccess";
	}

	@PostMapping
	public @ResponseBody Response<PropertyRegResponseDto> createProperty(@Valid @RequestBody PropertyRegRequestDto requestDto, HttpServletRequest request) {
		String userId = request.getParameter("userId");
		requestDto.setUserId(userId);
		return propertyService.createProperty(requestDto);
	}

	@PutMapping
	public @ResponseBody Response<String> updateProperty(@Valid @RequestBody PropertyUpdateReqDto requestDto,HttpServletRequest request) {
		String userId = request.getParameter("userId");
		requestDto.setUserId(userId);
		return propertyService.updateProperty(requestDto);
	}

	@DeleteMapping
	public @ResponseBody Response<Boolean> deleteProperty(@Valid @RequestBody PropertyDeleteReqDto requestDto) {
		return propertyService.deleteProperty(requestDto);
	}

	@GetMapping
	public @ResponseBody ListResponse<List<PropertyResponseDto>> listProperties(
			@RequestParam(required = false) String search, @RequestParam int page, @RequestParam int size) {
		return propertyService.listProperties(search, page, size);
	}

	@GetMapping("/profile/{propertyId}")
	public @ResponseBody Response<PropertyResponseDto> getPropertysDetails(
			@PathVariable("propertyId") String propertyId) {
		return propertyService.getPropertyDetails(propertyId);
	}

	@PutMapping("/status")
	public @ResponseBody Response<Boolean> updatePropertyStatus(@Valid @RequestBody PropertyStatusReqDto requestDto,HttpServletRequest request) {
		String userId = request.getParameter("userId");
		requestDto.setUserId(userId);
		return propertyService.updatePropertyStatus(requestDto);
	}
	
	@GetMapping("/list")
	public @ResponseBody ListResponse<List<PropertyResponseDto>> filterListProperty(@RequestParam(required = false) List<String> filters,@RequestParam(required = false) String sortByFieldName,@RequestParam(required = false) String sortType, @RequestParam("page") Integer page,@RequestParam("size") Integer size,@RequestParam(required = false)  String userId) {
		return propertyService.filterListProperty(filters,sortByFieldName,sortType, page, size,userId);
	}

}
