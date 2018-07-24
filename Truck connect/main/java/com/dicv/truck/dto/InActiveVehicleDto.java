package com.dicv.truck.dto;

public class InActiveVehicleDto {

	public InActiveVehicleDto(String registrationId, String gpsImei, String vin) {
		super();
		this.registrationId = registrationId;
		this.gpsImei = gpsImei;
		this.vin = vin;
	}

	private String registrationId;

	private String gpsImei;

	private String vin;

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getGpsImei() {
		return gpsImei;
	}

	public void setGpsImei(String gpsImei) {
		this.gpsImei = gpsImei;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}
	
	
	

}
