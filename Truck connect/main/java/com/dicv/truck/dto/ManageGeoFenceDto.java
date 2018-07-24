package com.dicv.truck.dto;

public class ManageGeoFenceDto extends StatusMessageDto{
	
	private Integer geoFenceId;

	public Integer getGeoFenceId() {
		return geoFenceId;
	}

	public void setGeoFenceId(Integer geoFenceId) {
		this.geoFenceId = geoFenceId;
	}

	@Override
	public String toString() {
		return "ManageGeoFenceDto [geoFenceId=" + geoFenceId + "]";
	}
	

}
