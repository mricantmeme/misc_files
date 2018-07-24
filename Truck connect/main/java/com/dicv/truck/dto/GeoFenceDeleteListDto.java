package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

public class GeoFenceDeleteListDto {

	List<GeoFenceDeleteDto> geoFenceDeleteList = new ArrayList<GeoFenceDeleteDto>();

	public List<GeoFenceDeleteDto> getGeoFenceDeleteList() {
		return geoFenceDeleteList;
	}

	public void setGeoFenceDeleteList(List<GeoFenceDeleteDto> geoFenceDeleteList) {
		this.geoFenceDeleteList = geoFenceDeleteList;
	}

}
