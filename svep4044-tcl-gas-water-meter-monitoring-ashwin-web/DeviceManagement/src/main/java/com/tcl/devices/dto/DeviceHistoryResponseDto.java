package com.tcl.devices.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DeviceHistoryResponseDto {

	private String deviceId;

	private String modifiedBy;

	private String status;

	private Date modifiedDate;

}
