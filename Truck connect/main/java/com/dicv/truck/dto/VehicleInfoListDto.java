package com.dicv.truck.dto;

import java.util.List;

public class VehicleInfoListDto extends StatusMessageDto {
	
	private List<VehicleInfoDto> vehicles;

	public List<VehicleInfoDto> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<VehicleInfoDto> vehicles) {
		this.vehicles = vehicles;
	}

	

	
}
