package com.tcl.devices.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "device_history")
public class DeviceHistory {

	@Field("device_id")
	private String deviceId;

	@Field("status")
	private String status;

	@Field("created_date")
	private Date createdDate;

	@Field("modified_date")
	private Date modifiedDate;

	@Field("created_by")
	private String createdBy;

	@Field("modified_by")
	private String modifiedBy;
}
