package com.dicv.truck.dto;

import java.util.List;

public class ErmVehicleListDto extends StatusMessageDto {
	
	private List<ErmVehicleDto> vehicleList;

	public List<ErmVehicleDto> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<ErmVehicleDto> vehicleList) {
		this.vehicleList = vehicleList;
	}

}
