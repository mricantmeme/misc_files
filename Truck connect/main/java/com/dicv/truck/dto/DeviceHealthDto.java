package com.dicv.truck.dto;

import java.io.Serializable;
import java.util.List;

public class DeviceHealthDto implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7928314270217884128L;
	
	
	private List<DeviceHealthReportDto> deviceHealthReportDto;

	public List<DeviceHealthReportDto> getDeviceHealthReportDto() {
		return deviceHealthReportDto;
	}

	public void setDeviceHealthReportDto(List<DeviceHealthReportDto> deviceHealthReportDto) {
		this.deviceHealthReportDto = deviceHealthReportDto;
	}
	
	

}
