package com.dicv.truck.dto;

import java.util.List;

public class CountrysDto extends StatusMessageDto {

	private List<CountryDto> countries;

	public List<CountryDto> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryDto> countries) {
		this.countries = countries;
	}

}
