/*
 * @category DeviceManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.devices.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DeviceResponseDto {

	private String deviceId;

	private String deviceEui;

	private String deviceName;

	private String serialNumber;

	private String modelNumber;

	private String hardwareVersion;

	private String firmwareVersion;

	private String manufacturerName;

	private String customerId;

	private String status;

	private String assigned;

	private Date modifiedDate;

}
