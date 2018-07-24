package com.dicv.truck.dto;

public class GeoFenceDeleteDto {

	private Integer geoFenceId;
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGeoFenceId() {
		return geoFenceId;
	}

	public void setGeoFenceId(Integer geoFenceId) {
		this.geoFenceId = geoFenceId;
	}

	@Override
	public String toString() {
		return "GeoFenceDelete [geoFenceId=" + geoFenceId + "]";
	}

}
