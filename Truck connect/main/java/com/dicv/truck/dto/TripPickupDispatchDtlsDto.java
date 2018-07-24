package com.dicv.truck.dto;

/**
 * This class is responsible to get trip pickup and dispatch details.
 * 
 * @author aut7kor
 * 
 */
public class TripPickupDispatchDtlsDto {

	private Integer scheduledTripId;
	private String vehicleRegNum;
	private String vehicleDescription;
	private String pickupDate;
	private String deliveryDate;
	private String tripStatus;
	private Integer pickupFlag;
	private Integer deliveryFlag;
	private String pickupFile;
	private Integer deliveryFile;

	public Integer getScheduledTripId() {
		return scheduledTripId;
	}

	public void setScheduledTripId(Integer scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	public String getVehicleRegNum() {
		return vehicleRegNum;
	}

	public void setVehicleRegNum(String vehicleRegNum) {
		this.vehicleRegNum = vehicleRegNum;
	}

	public String getVehicleDescription() {
		return vehicleDescription;
	}

	public void setVehicleDescription(String vehicleDescription) {
		this.vehicleDescription = vehicleDescription;
	}

	public String getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(String tripStatus) {
		this.tripStatus = tripStatus;
	}

	@Override
	public String toString() {
		return "TripPickupDispatchDtlsDto [scheduledTripId=" + scheduledTripId + ", vehicleRegNum=" + vehicleRegNum
				+ ", vehicleDescription=" + vehicleDescription + ", pickupDate=" + pickupDate + ", deliveryDate="
				+ deliveryDate + ", tripStatus=" + tripStatus + ", pickupFlag=" + pickupFlag + ", deliveryFlag="
				+ deliveryFlag + "]";
	}

	public Integer getPickupFlag() {
		return pickupFlag;
	}

	public void setPickupFlag(Integer pickupFlag) {
		this.pickupFlag = pickupFlag;
	}

	public Integer getDeliveryFlag() {
		return deliveryFlag;
	}

	public void setDeliveryFlag(Integer deliveryFlag) {
		this.deliveryFlag = deliveryFlag;
	}

	public String getPickupFile() {
		return pickupFile;
	}

	public void setPickupFile(String pickupFile) {
		this.pickupFile = pickupFile;
	}

	public Integer getDeliveryFile() {
		return deliveryFile;
	}

	public void setDeliveryFile(Integer deliveryFile) {
		this.deliveryFile = deliveryFile;
	}

}
