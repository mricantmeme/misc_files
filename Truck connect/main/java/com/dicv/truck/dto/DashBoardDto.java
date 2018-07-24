package com.dicv.truck.dto;

public class DashBoardDto  {

	private Long totalVehicles;

	private String countryName;
	
	private String countryCode;

	private Long activeVehicles=0l;

	public Long getTotalVehicles() {
		return totalVehicles;
	}

	public void setTotalVehicles(Long totalVehicles) {
		this.totalVehicles = totalVehicles;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Long getActiveVehicles() {
		return activeVehicles;
	}

	public void setActiveVehicles(Long activeVehicles) {
		this.activeVehicles = activeVehicles;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
