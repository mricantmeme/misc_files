/*
 * @category DeviceManagement/*
* @category DeviceManagement
* @copyright Copyright (C) 2018 Contus. All rights reserved.
* @license http://www.apache.org/licenses/LICENSE-2.0
*/
package com.tcl.devices.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AssignDeviceRequestDto {

	@NotNull(message = "DeviceId cannot be empty")
	private List<String> deviceId;

	@NotNull(message = "Assign engineer field cannot be empty")
	private String assignedTo;

	@NotNull(message = "User Id cannot be empty")
	private String userId;
}
