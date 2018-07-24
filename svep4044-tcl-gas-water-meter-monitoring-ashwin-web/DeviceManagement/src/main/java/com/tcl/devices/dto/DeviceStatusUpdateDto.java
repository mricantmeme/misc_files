/*
 * @category DeviceManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.devices.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class DeviceStatusUpdateDto {

	@NotBlank
	@NotNull(message = "Device Id cannot be empty")
	@NotEmpty
	private List<String> deviceId;

	@NotNull(message = "Status cannot be empty.")
	private String status;

	@NotNull(message = "User Id cannot be empty.")
	private String userId;
}
