/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.property.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcl.property.dto.PropertyTypeResponseDto;
import com.tcl.property.response.Response;
import com.tcl.property.service.MasterDataService;

@RestController
@RequestMapping("/property")
public class MasterDataController {

	@Autowired
	private MasterDataService masterDataService;

	@GetMapping("/type")
	public @ResponseBody Response<List<PropertyTypeResponseDto>> propertyTypeDetails() {
		return masterDataService.getPropertyTypeDetails();
	}

}
