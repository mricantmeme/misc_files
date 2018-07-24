package com.dicv.truck.dto;

/* This class is responsible to register vehicle for GCM push notification.
 * @author AUT7KOR
 *  
 */
public class AppRegistrationDto {

	private Integer vehicleId;
	private String regId;

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	@Override
	public String toString() {
		return "AppRegistration [vehicleId=" + vehicleId + ", regId=" + regId
				+ "]";
	}

}
