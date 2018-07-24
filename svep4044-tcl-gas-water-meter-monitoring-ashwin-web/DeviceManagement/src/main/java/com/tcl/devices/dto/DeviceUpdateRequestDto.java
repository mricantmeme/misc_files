/*
 * @category DeviceManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.devices.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DeviceUpdateRequestDto {

	private String userId;

	@NotNull(message = "Device Id cannot be empty")
	private String deviceId;

	@NotNull(message = "Device name cannot be empty.")
	private String deviceName;

	@NotNull(message = "Device Eui cannot be empty.")
	private String deviceEui;

	@NotNull(message = "Serial number cannot be empty.")
	private String serialNumber;

	@NotNull(message = "Model number cannot be empty.")
	private String modelNumber;

	@NotNull(message = "Hardware version cannot be empty.")
	private String hardwareVersion;

	@NotNull(message = "Firmware version cannot be empty.")
	private String firmwareVersion;

	@NotNull(message = "Manufacturer name cannot be empty")
	private String manufacturerName;

	@NotNull(message = "Status cannot be empty.")
	private String status;

	private String assigned;

}
