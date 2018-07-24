package com.dicv.truck.dto;

import java.util.List;

public class DriverDtlsListDto extends StatusMessageDto {

	private List<DriverInfoDto> drivers;

	public List<DriverInfoDto> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<DriverInfoDto> drivers) {
		this.drivers = drivers;
	}

	

	
}
