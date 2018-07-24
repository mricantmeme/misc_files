package com.tcl.devices.dto;

import java.util.Date;

import lombok.Data;


@Data
public class ConsumptionResponseDto {
	
	private String consumptionId;
	private String customerName;
	private String zoneName;
	private String locationName;
	private String propertyId;
	private String consumerName;
	private Date consumptionDate;
	private String opBalance;
	private String loraData;
	private String colBalance;
	private String manualAdjustment;
	private String consumption;
	private Boolean consumptionStatus;
	
}
