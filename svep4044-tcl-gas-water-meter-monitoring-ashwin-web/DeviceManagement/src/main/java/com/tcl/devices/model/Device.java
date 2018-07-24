/*
 * @category DeviceManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.devices.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
@Document(collection = "devices")
public class Device {
	@Id
	@Field("device_id")
	private String deviceId;

	@Field("device_eui")
	private String deviceEui;

	@Field("device_name")
	private String deviceName;

	@Field("serial_no")
	private String serialNumber;

	@Field("model_no")
	private String modelNumber;

	@Field("hardware_version")
	private String hardwareVersion;

	@Field("firmware_version")
	private String firmwareVersion;

	@Field("manufacturer_name")
	private String manufacturerName;

	@Field("customer_name")
	private String customerId;

	@Field("status")
	private String status;

	@Field("assigned")
	private String assigned;

	@Field("created_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdDate;

	@Field("modified_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date modifiedDate;

	@Field("created_by")
	private String createdBy;

	@Field("modified_by")
	private String modifiedBy;

	@Field("is_active")
	private Boolean isActive;
}
