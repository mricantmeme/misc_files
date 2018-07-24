package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

public class GeoFenceListDto extends StatusMessageDto {

	private List<GeoFenceReportDto> geoFenceReportDtoList;
	private List<GeoFenceReportDto> geoFenceListDto = new ArrayList<GeoFenceReportDto>();
	private ArrayList<GeoFenceDto> geoFenceList;

	public List<GeoFenceReportDto> getGeoFenceListDto() {
		return geoFenceListDto;
	}

	public GeoFenceListDto setGeoFenceListDto(
			List<GeoFenceReportDto> geoFenceListDto) {
		this.geoFenceListDto = geoFenceListDto;
		return null;
	}

	public ArrayList<GeoFenceDto> getGeoFenceList() {
		return geoFenceList;
	}

	public void setGeoFenceList(ArrayList<GeoFenceDto> geoFenceList) {
		this.geoFenceList = geoFenceList;
	}

	public List<GeoFenceReportDto> getGeoFenceReportDtoList() {
		return geoFenceReportDtoList;
	}

	public void setGeoFenceReportDtoList(
			List<GeoFenceReportDto> geoFenceReportDtoList) {
		this.geoFenceReportDtoList = geoFenceReportDtoList;
	}

}
