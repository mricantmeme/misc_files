package com.dicv.truck.dto;

import java.util.List;

public class CitiesDto extends StatusMessageDto {

	private List<CityDto> cities;

	public List<CityDto> getCities() {
		return cities;
	}

	public void setCities(List<CityDto> cities) {
		this.cities = cities;
	}
}
