package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

public class GeoFenceReportListDto {

	private List<GeoFenceReportDto> geoFenceReportDtos = new ArrayList<GeoFenceReportDto>();

	public List<GeoFenceReportDto> getGeoFenceReportDtos() {
		return geoFenceReportDtos;
	}

	public void setGeoFenceReportDtos(List<GeoFenceReportDto> geoFenceReportDtos) {
		this.geoFenceReportDtos = geoFenceReportDtos;
	}

}
