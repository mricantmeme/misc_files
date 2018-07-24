package com.dicv.truck.dto;

public class VehicleDtlsDtoHome {

	private String countryCode;
	private String countryName;
	private Integer vehicleId;
	private String vehicleRunningStatus;

	public VehicleDtlsDtoHome() {

	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleRunningStatus() {
		return vehicleRunningStatus;
	}

	public void setVehicleRunningStatus(String vehicleRunningStatus) {
		this.vehicleRunningStatus = vehicleRunningStatus;
	}

	@Override
	public String toString() {
		return "VehicleDtlsDtoHome [countryCode=" + countryCode
				+ ", countryName=" + countryName + ", vehicleId=" + vehicleId
				+ ", vehicleRunningStatus=" + vehicleRunningStatus + "]";
	}

}
