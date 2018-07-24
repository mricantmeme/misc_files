package com.dicv.truck.dto;

import java.util.List;

public class GPSOwnerDetailDto extends StatusMessageDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2257220976532400823L;

	private Integer userId;
	private List<GPSVehicleDetailDto> vehicles;

	public GPSOwnerDetailDto() {

	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<GPSVehicleDetailDto> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<GPSVehicleDetailDto> vehicles) {
		this.vehicles = vehicles;
	}

}
