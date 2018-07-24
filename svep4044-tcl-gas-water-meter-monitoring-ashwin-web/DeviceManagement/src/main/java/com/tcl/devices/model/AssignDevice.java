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

import lombok.Data;

@Data
@Document(collection = "device_assignment")
public class AssignDevice {

	@Id
	@Field("device_id")
	private String deviceId;

	@Field("assigned_to")
	private String assignedTo;

	@Field("created_date")
	private Date createdDate;

}
