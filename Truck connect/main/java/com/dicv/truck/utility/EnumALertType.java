package com.dicv.truck.utility;

public enum EnumALertType {

	BATTERY_DISCONNECT("BATTERY_DISCONNECT"), VEHICLE_IDLE_ALERT("VEHICLE_IDLE_ALERT"), PICKUP("PICKUP"), DELIVERY(
			"DELIVERY"), GEOFENCE_IN("GEOFENCE_IN"), GEOFENCE_OUT("GEOFENCE_OUT"), LOW_BATTERY(
					"LOW_BATTERY"),BATTERY_HEALTh(
							"LOW_BATTERY"), FUEL_DROP_ALERT("FUEL_DROP_ALERT"), OVER_SPEED("OVER_SPEED");

	private String alertType;

	EnumALertType(String alertType) {
		this.alertType = alertType;

	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

}
