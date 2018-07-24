/*
 * @category DeviceManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.devices.controller;

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

import com.tcl.devices.dto.AssignDeviceRequestDto;
import com.tcl.devices.dto.CreateDeviceResponseDto;
import com.tcl.devices.dto.DeviceDeleteRequestDto;
import com.tcl.devices.dto.DeviceHistoryResponseDto;
import com.tcl.devices.dto.DeviceResponseDto;
import com.tcl.devices.dto.DeviceStatusResponseDto;
import com.tcl.devices.dto.DeviceStatusUpdateDto;
import com.tcl.devices.dto.DeviceUpdateRequestDto;
import com.tcl.devices.dto.DeviceUpdateResponseDto;
import com.tcl.devices.dto.NewDeviceRequestDto;
import com.tcl.devices.model.DeviceStatus;
import com.tcl.devices.response.ListResponse;
import com.tcl.devices.response.Response;
import com.tcl.devices.service.DeviceService;

/*
 * This controller takes care of the device management API. Every request
 * mentioned here gets mapped and the respective functions are called. The
 * user(whoever has logged in) can get the list of devices and add , delete,
 * edit devices.
 * 
 */
@RestController
@RequestMapping("/devices")
public class DeviceController {

	/*
	 * dependency injection and object initializtion using Autowired annotation
	 */
	@Autowired
	DeviceService deviceService;

	/*
	 * Test method to see if the API service is working or not
	 */
	@GetMapping("/ping")
	public String ping() {
		return "PONG";
	}

	/*
	 * This method when called sends the request received to the service and calls
	 * that service method. It is used to add a new device.
	 */
	@PostMapping()
	public Response<CreateDeviceResponseDto> addDevice(@Valid @RequestBody NewDeviceRequestDto request,
			HttpServletRequest requestDto) {
		request.setUserId(requestDto.getParameter("userId"));
		return deviceService.addDevice(request);
	}

	/*
	 * This method when called, calls the method that updates the device details.
	 * And sends the request in the method call.
	 */
	@PutMapping()
	public Response<DeviceUpdateResponseDto> updateDevice(@Valid @RequestBody DeviceUpdateRequestDto request,
			HttpServletRequest requestDto) {
		request.setUserId(requestDto.getParameter("userId"));
		return deviceService.updateDevice(request);
	}

	/*
	 * This method when called, calls the method that lists the devices. If any
	 * search queries are given in the url, it will filter the search result and
	 * return. Else all the devices that have true status for IsActive field will be
	 * listed and sent in the response.
	 */
	@GetMapping()
	public ListResponse<List<DeviceResponseDto>> listDevice(@RequestParam(required = false) String search,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		return deviceService.listDevice(search, page, size);
	}

	/*
	 * This method when called, calls the delete method. And then returns the status
	 * of the delete process.
	 */
	@DeleteMapping
	public Response<Boolean> deleteDevice(@Valid @RequestBody DeviceDeleteRequestDto request) {
		return deviceService.deleteDevice(request);
	}

	/*
	 * This method when called,returns the device details of the deviceID thats
	 * passed in the URL.
	 */
	@GetMapping("/{deviceId}")
	public Response<DeviceResponseDto> getOneDevice(@PathVariable("deviceId") String deviceId) {
		return deviceService.getOneDevice(deviceId);
	}

	/*
	 * This method when called, calls the method that lists all the active devices.
	 * The list of devices are sent in a list in the response with pagination
	 * details.
	 */
	@GetMapping("/active")
	public ListResponse<List<DeviceResponseDto>> getActiveDevices(@RequestParam("page") int page,
			@RequestParam("size") int size) {
		return deviceService.getActiveDevices(page, size);
	}

	@GetMapping("/history")
	public ListResponse<List<DeviceHistoryResponseDto>> deviceHistory(@RequestParam("deviceId") String deviceId,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		return deviceService.deviceHistory(deviceId, page, size);
	}

	/*
	 * This method when called, gets all the status of the devices to select from
	 * them. It acts as a masterdata controller.
	 */
	@GetMapping("/status")
	public Response<List<DeviceStatusResponseDto>> getDeviceStatus() {
		return deviceService.getDeviceStatus();
	}

	/*
	 * This method when called, invokes a service method that can add a new status
	 * to the DB.
	 */
	@PostMapping("/status")
	public Response<Boolean> addStatus(@Valid @RequestBody DeviceStatus request) {
		return deviceService.addStatus(request);
	}

	/*
	 * This method when called, will assign a device to the user that is mentioned
	 * in the request body.
	 */
	@PostMapping("/add/assigned")
	public Response<Boolean> addNewAssign(@Valid @RequestBody AssignDeviceRequestDto request,
			HttpServletRequest requestDto) {
		request.setUserId(requestDto.getParameter("userId"));
		return deviceService.addNewAssign(request);
	}

	/*
	 * This method is used for filtering and sorting the details of the devices.
	 * Pagination and sorting are done as follows : If you extend
	 * PagingAndSortingRepository<T, ID> and access the list of all entities, you’ll
	 * get links to the first 20 entities. To set the page size to any other number,
	 * add a size parameter
	 */
	@GetMapping("/list")
	public @ResponseBody ListResponse<List<DeviceResponseDto>> filterDeviceList(
			@RequestParam(required = false) List<String> filters,
			@RequestParam(required = false) String sortByFieldName, @RequestParam(required = false) String sortType,
			@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		return deviceService.filterListCustomers(filters, sortByFieldName, sortType, page, size);
	}

	/*
	 * This method when called, will change the device status. It is used to change
	 * the device status only.
	 *
	 */
	@PutMapping("/status/update")
	public Response<Boolean> updateDeviceStatus(@Valid @RequestBody DeviceStatusUpdateDto request,
			HttpServletRequest requestDto) {
		request.setUserId(requestDto.getParameter("userId"));
		return deviceService.updateDeviceStatus(request);
	}
}
