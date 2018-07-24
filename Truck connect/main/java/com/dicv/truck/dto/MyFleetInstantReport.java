package com.dicv.truck.dto;

import java.util.List;

public class MyFleetInstantReport {
	
	private Integer userId;
	
	private List<Integer> vehicleIds;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Integer> getVehicleIds() {
		return vehicleIds;
	}

	public void setVehicleIds(List<Integer> vehicleIds) {
		this.vehicleIds = vehicleIds;
	}

	
	
}
