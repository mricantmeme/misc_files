package com.tcl.devices.dto;


import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ConsumptionDetailUpdateReqDto {
	
	
	@NotNull(message = "Property Id cannot be empty")
	private String propertyId;
	
	private String manualAdjustment;
	private String consumption;
	
	

}
