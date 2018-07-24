package com.dicv.truck.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dicv.truck.dto.GeoFenceDeleteListDto;
import com.dicv.truck.dto.GeoFenceDto;
import com.dicv.truck.dto.GeoFenceListDto;
import com.dicv.truck.dto.GeoFenceTypeDto;
import com.dicv.truck.dto.GeoFenceTypeListDto;
import com.dicv.truck.dto.ManageGeoFenceDto;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.exception.DataNotFoundException;
import com.dicv.truck.service.GeoFenceService;
import com.dicv.truck.utility.DicvConstants;

@RestController
@RequestMapping("/onlinefleetmngmt/dicv")
public class GeoFenceController {

	@Autowired
	private GeoFenceService geoFenceService;

	@GetMapping("/getGeoFenceList")
	public GeoFenceListDto getGeoFenceList(Integer userId, Integer vehicleId) {
		return geoFenceService.getGeoFenceList(userId, vehicleId);
	}

	@GetMapping("/getGeoFenceType")
	public GeoFenceTypeListDto getGeoFenceType(Integer userId) {
		return geoFenceService.getGeoFenceType(userId);
	}

	@PostMapping("/manageGeoFence")
	public ManageGeoFenceDto manageGeoFence(@RequestBody() GeoFenceDto geoFence) {
		ManageGeoFenceDto manageGeoFenceDto = new ManageGeoFenceDto();
		if (geoFence.getGeoFenceId() == null || geoFence.getGeoFenceId() <= 0) {

			if (null != geoFence.getGeoFenceName() && geoFence.getGeoFenceName() != ""
					&& geoFence.getShapeType() != null) {
				return geoFenceService.manageGeoFence(geoFence);

			} else {
				manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				manageGeoFenceDto.setMessage(DicvConstants.INVALIDINPUT_EXCEPTION_MSG);
				return manageGeoFenceDto;

			}
		} else {
			// update
			if (null != geoFence.getGeoFenceName() && geoFence.getGeoFenceName() != ""
					&& geoFence.getShapeType() != null) {
				return geoFenceService.editGeoFence(geoFence);

			} else {
				manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				manageGeoFenceDto.setMessage(DicvConstants.INVALIDINPUT_EXCEPTION_MSG);
				return manageGeoFenceDto;

			}
		}

	}

	@PostMapping("/deleteGeoFence")
	public StatusMessageDto deleteGeoFence(@RequestBody() GeoFenceDeleteListDto geoFenceDeleteList) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		if (geoFenceDeleteList != null) {
			geoFenceService.deleteGeoFence(geoFenceDeleteList);
			statusMessageDto.setStatus(HttpServletResponse.SC_OK);
			statusMessageDto.setMessage(DicvConstants.SUCCESS_DELETED);
			return statusMessageDto;
		} else {
			throw new DataNotFoundException(DicvConstants.DATA_NOT_FOUND_MSG);
		}
	}

	@PostMapping("/manageGeoFenceType")
	public StatusMessageDto manageGeoFenceType(@RequestBody() GeoFenceTypeDto type) {
		return geoFenceService.manageGeoFenceType(type);

	}

}
