package com.dicv.truck.dto;

public class TripPickUpDispatchData {

	private Long tripId;

	private Long scheduleTripId;

	private String tripStatus;

	private String vehicleName;

	private String pickUpDate;

	private String deliveryDate;

	private String pickUpFile;

	private String deliveryFile;

	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

	public Long getScheduleTripId() {
		return scheduleTripId;
	}

	public void setScheduleTripId(Long scheduleTripId) {
		this.scheduleTripId = scheduleTripId;
	}

	public String getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(String tripStatus) {
		this.tripStatus = tripStatus;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(String pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getPickUpFile() {
		return pickUpFile;
	}

	public void setPickUpFile(String pickUpFile) {
		this.pickUpFile = pickUpFile;
	}

	public String getDeliveryFile() {
		return deliveryFile;
	}

	public void setDeliveryFile(String deliveryFile) {
		this.deliveryFile = deliveryFile;
	}
	
	
	

}
