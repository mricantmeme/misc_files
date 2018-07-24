package com.dicv.truck.dto;

import java.util.List;

public class DealerVehicleDtoList extends StatusMessageDto {

	private List<DealerVehicleDto> vehicle;

	public List<DealerVehicleDto> getVehicle() {
		return vehicle;
	}

	public void setVehicle(List<DealerVehicleDto> vehicle) {
		this.vehicle = vehicle;
	}

	
	

}
