package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

public class DriversStatus extends StatusMessageDto {

	List<DriverCurrentStatus> driverList = new ArrayList<>();

	public List<DriverCurrentStatus> getDriverList() {
		return driverList;
	}

	public void setDriverList(List<DriverCurrentStatus> driverList) {
		this.driverList = driverList;
	}

	public DriverCurrentStatus getDriverStatus(Integer driverId) {
		for (DriverCurrentStatus dcs : driverList) {
			if (dcs.getDriverId().equals(driverId)) {
				return dcs;
			}
		}
		return null;
	}

}
