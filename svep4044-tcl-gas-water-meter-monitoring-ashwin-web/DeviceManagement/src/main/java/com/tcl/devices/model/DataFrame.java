package com.tcl.devices.model;

import lombok.Data;

@Data
public class DataFrame {
	
	private String packetId;
	
	private String consumptionValue;
	
	private String tamperingCode;
	
	private String amrHealth;
	
	private String readingStartTime;
	
	private String readingEndTime;
	
}
