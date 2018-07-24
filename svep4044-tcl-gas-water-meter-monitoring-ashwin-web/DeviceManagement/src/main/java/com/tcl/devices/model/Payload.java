package com.tcl.devices.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Document(collection = "payload")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payload {
	
	@Id
	@Field("payload_id")
	private String payloadId;
	
	
	@Field("meter_reading_single_id")
	private String meterReadingId;
	
	@Field("server_id")
	private String serverId;
	
	@Field("server_timestamp")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date serverDate;
	
	@Field("user_id")
	private String userId;
	
	@Field("device_id")
	private String deviceId;
	
	@Field("device_address")
	private String deviceAddress;
	
	@Field("customer_id")
	private String customerId;
	
	@Field("site_id")
	private String siteId;
	
	@Field("zone_id")
	private String zoneId;
	
	@Field("data_frame")
	private DataFrame dataFrame;
	
	@Field("frequency")
	private String frequency;
	
	@Field("decryption")
	private String decryption;
	
	@Field("status_name")
	private String status;

	@Field("isActive")
	private Boolean isActive;

	
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

}
