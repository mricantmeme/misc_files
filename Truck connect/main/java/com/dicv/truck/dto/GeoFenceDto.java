package com.dicv.truck.dto;

import java.util.List;

/**
 * 
 * @author PJN6KOR
 * 
 */

public class GeoFenceDto {

	private Integer geoFenceId;
	private String shapeType;
	private List<List<Float>> geoFencePolygonCoordinates;
	private String geoFenceName;
	private Integer dicvUserId;
	private Float geoFenceCircleLatitude;
	private Float geoFenceCircleLongitude;
	private Float geoFenceCircleRadiusInMeters;
	private Boolean applyToAllVehicles;
	private Integer typeId;
	private String validFrom;
	private String validTo;
	private Boolean entryAlert;
	private Boolean exitAlert;
	// private Boolean emailMode;
	private Boolean skippedAlert;
	// private Boolean webMode;
	private String entryNotificationTime;
	private String exitNotificationTime;
	private List<VehicleDtlsDto> vehicleDtoList;
	private List<VehicleInfoDto> vehicleInfoList;
	private List<Integer> vehicleList;

	public Integer getGeoFenceId() {
		return geoFenceId;
	}

	public void setGeoFenceId(Integer geoFenceId) {
		this.geoFenceId = geoFenceId;
	}

	public String getShapeType() {
		return shapeType;
	}

	public void setShapeType(String shapeType) {
		this.shapeType = shapeType;
	}

	public List<List<Float>> getGeoFencePolygonCoordinates() {
		return geoFencePolygonCoordinates;
	}

	public void setGeoFencePolygonCoordinates(
			List<List<Float>> geoFencePolygonCoordinates) {
		this.geoFencePolygonCoordinates = geoFencePolygonCoordinates;
	}

	public String getGeoFenceName() {
		return geoFenceName;
	}

	public void setGeoFenceName(String geoFenceName) {
		this.geoFenceName = geoFenceName;
	}

	public Integer getDicvUserId() {
		return dicvUserId;
	}

	public void setDicvUserId(Integer dicvUserId) {
		this.dicvUserId = dicvUserId;
	}

	public Float getGeoFenceCircleLatitude() {
		return geoFenceCircleLatitude;
	}

	public void setGeoFenceCircleLatitude(Float geoFenceCircleLatitude) {
		this.geoFenceCircleLatitude = geoFenceCircleLatitude;
	}

	public Float getGeoFenceCircleLongitude() {
		return geoFenceCircleLongitude;
	}

	public void setGeoFenceCircleLongitude(Float geoFenceCircleLongitude) {
		this.geoFenceCircleLongitude = geoFenceCircleLongitude;
	}

	public Float getGeoFenceCircleRadiusInMeters() {
		return geoFenceCircleRadiusInMeters;
	}

	public void setGeoFenceCircleRadiusInMeters(
			Float geoFenceCircleRadiusInMeters) {
		this.geoFenceCircleRadiusInMeters = geoFenceCircleRadiusInMeters;
	}

	public Boolean getApplyToAllVehicles() {
		return applyToAllVehicles;
	}

	public void setApplyToAllVehicles(Boolean applyToAllVehicles) {
		this.applyToAllVehicles = applyToAllVehicles;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public Boolean getEntryAlert() {
		return entryAlert;
	}

	public void setEntryAlert(Boolean entryAlert) {
		this.entryAlert = entryAlert;
	}

	public Boolean getExitAlert() {
		return exitAlert;
	}

	public void setExitAlert(Boolean exitAlert) {
		this.exitAlert = exitAlert;
	}

	// public Boolean getEmailMode() {
	// return emailMode;
	// }
	// public void setEmailMode(Boolean emailMode) {
	// this.emailMode = emailMode;
	// }
	public Boolean getSkippedAlert() {
		return skippedAlert;
	}

	public void setSkippedAlert(Boolean skippedAlert) {
		this.skippedAlert = skippedAlert;
	}

	// public Boolean getWebMode() {
	// return webMode;
	// }
	// public void setWebMode(Boolean webMode) {
	// this.webMode = webMode;
	// }
	public String getEntryNotificationTime() {
		return entryNotificationTime;
	}

	public void setEntryNotificationTime(String entryNotificationTime) {
		this.entryNotificationTime = entryNotificationTime;
	}

	public String getExitNotificationTime() {
		return exitNotificationTime;
	}

	public void setExitNotificationTime(String exitNotificationTime) {
		this.exitNotificationTime = exitNotificationTime;
	}

	public List<VehicleDtlsDto> getVehicleDtoList() {
		return vehicleDtoList;
	}

	public void setVehicleDtoList(List<VehicleDtlsDto> vehicleDtoList) {
		this.vehicleDtoList = vehicleDtoList;
	}

	public List<VehicleInfoDto> getVehicleInfoList() {
		return vehicleInfoList;
	}

	public void setVehicleInfoList(List<VehicleInfoDto> vehicleInfoList) {
		this.vehicleInfoList = vehicleInfoList;
	}

	public List<Integer> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<Integer> vehicleList) {
		this.vehicleList = vehicleList;
	}

	@Override
	public String toString() {
		return "GeoFenceDto [geoFenceId=" + geoFenceId + ", shapeType="
				+ shapeType + ", geoFencePolygonCoordinates="
				+ geoFencePolygonCoordinates + ", geoFenceName=" + geoFenceName
				+ ", dicvUserId=" + dicvUserId + ", geoFenceCircleLatitude="
				+ geoFenceCircleLatitude + ", geoFenceCircleLongitude="
				+ geoFenceCircleLongitude + ", geoFenceCircleRadiusInMeters="
				+ geoFenceCircleRadiusInMeters + ", applyToAllVehicles="
				+ applyToAllVehicles + ", typeId=" + typeId + ", validFrom="
				+ validFrom + ", validTo=" + validTo
				+ ", entryAlert="
				+ entryAlert
				+ ", exitAlert="
				+ exitAlert
				+
				// "," + " emailMode=" + emailMode +
				", skippedAlert="
				+ skippedAlert
				+
				// ", webMode="+ webMode +
				", entryNotificationTime=" + entryNotificationTime
				+ ", exitNotificationTime=" + exitNotificationTime
				+ ", vehicleDtoList=" + vehicleDtoList + ", vehicleInfoList="
				+ vehicleInfoList + ", vehicleList=" + vehicleList + "]";
	}

}
