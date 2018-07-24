package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

public class ErmVehicleSubmit {

	private List<Integer> ermVehicleIds = new ArrayList<Integer>();

	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Integer> getErmVehicleIds() {
		return ermVehicleIds;
	}

	public void setErmVehicleIds(List<Integer> ermVehicleIds) {
		this.ermVehicleIds = ermVehicleIds;
	}


}
