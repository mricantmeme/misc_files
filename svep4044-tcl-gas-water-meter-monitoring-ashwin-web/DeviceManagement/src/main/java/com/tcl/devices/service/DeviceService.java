/*
 * @category DeviceManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.devices.service;

import java.util.List;

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

/*
 *Service interface to implement abstraction in the code. 
 */
public interface DeviceService {

	Response<CreateDeviceResponseDto> addDevice(NewDeviceRequestDto request);

	Response<DeviceUpdateResponseDto> updateDevice(DeviceUpdateRequestDto request);

	ListResponse<List<DeviceResponseDto>> listDevice(String search, int page, int size);

	Response<Boolean> deleteDevice(DeviceDeleteRequestDto request);

	Response<DeviceResponseDto> getOneDevice(String deviceId);

	Response<List<DeviceStatusResponseDto>> getDeviceStatus();

	Response<Boolean> addStatus(DeviceStatus request);

	Response<Boolean> addNewAssign(AssignDeviceRequestDto request);

	ListResponse<List<DeviceResponseDto>> filterListCustomers(List<String> filters, String sortByFieldName,
			String sortType, Integer page, Integer size);

	ListResponse<List<DeviceResponseDto>> getActiveDevices(int page, int size);

	Response<Boolean> updateDeviceStatus(DeviceStatusUpdateDto request);

	ListResponse<List<DeviceHistoryResponseDto>> deviceHistory(String deviceId, int size, int size2);

}
