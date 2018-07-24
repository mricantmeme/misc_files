package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

public class VehiclesStatus extends StatusMessageDto {

	List<VehicleCurrentStatus> vcsList = new ArrayList<VehicleCurrentStatus>();

	public List<VehicleCurrentStatus> getVcsList() {
		return vcsList;
	}

	public void setVcsList(List<VehicleCurrentStatus> vcsList) {
		this.vcsList = vcsList;
	}

	public VehicleCurrentStatus getVehicleStatus(Integer vehicleId) {
		for (VehicleCurrentStatus vcs : vcsList) {
			if (vcs.getVehicleId().equals(vehicleId)) {
				return vcs;
			}
		}
		return null;
	}

}
