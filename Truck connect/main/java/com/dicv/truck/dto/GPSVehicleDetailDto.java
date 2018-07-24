package com.dicv.truck.dto;

import java.util.Calendar;
import java.util.Date;


public class GPSVehicleDetailDto extends StatusMessageDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6820290286280059517L;

	private Integer vehicleId;
	private String vehicleImage;
	private String vehicleStatus;
	private String registrationId;
	private Double currentLat;
	private Double currentLong;
	private String identificationNum;
	private String description;
	private Integer currentVehicleSpeed;
	private Integer currentEngineSpeed;
	private Integer userId;
	private String vehicleUpdateTimeStr;
	private Integer gcmId;
	private Long imeiId;
	private String vehicleVariant;
	private String vin;
	private String vehicleTripStatus;
	private VehicleCanParamDto vehicleCanParam;
	private Integer gpsHdop;
	private Float gpsCog;
	private String vehicleDirection;
	private Integer vehicleIdleTime; // in milliseconds
	private Date vehicleUpdateTime;

	private Calendar modifiedDateTime;

	private Integer regionId;

	private Integer groupId;

	private String vehicleLastUpdateTime;

	private boolean isShowParam = false;

	private Integer companyId;

	public GPSVehicleDetailDto() {

	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public Double getCurrentLat() {
		return currentLat;
	}

	public void setCurrentLat(Double currentLat) {
		this.currentLat = currentLat;
	}

	public Double getCurrentLong() {
		return currentLong;
	}

	public void setCurrentLong(Double currentLong) {
		this.currentLong = currentLong;
	}

	public String getIdentificationNum() {
		return identificationNum;
	}

	public void setIdentificationNum(String identificationNum) {
		this.identificationNum = identificationNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCurrentEngineSpeed() {
		return currentEngineSpeed;
	}

	public void setCurrentEngineSpeed(Integer currentEngineSpeed) {
		this.currentEngineSpeed = currentEngineSpeed;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getVehicleUpdateTimeStr() {
		return vehicleUpdateTimeStr;
	}

	public void setVehicleUpdateTimeStr(String vehicleUpdateTimeStr) {
		this.vehicleUpdateTimeStr = vehicleUpdateTimeStr;
	}

	public String getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public Integer getGcmId() {
		return gcmId;
	}

	public void setGcmId(Integer gcmId) {
		this.gcmId = gcmId;
	}

	public Integer getVehicleIdleTime() {
		return vehicleIdleTime;
	}

	public void setVehicleIdleTime(Integer vehicleIdleTime) {
		this.vehicleIdleTime = vehicleIdleTime;
	}

	public Date getVehicleUpdateTime() {
		return vehicleUpdateTime;
	}

	public void setVehicleUpdateTime(Date vehicleUpdateTime) {
		this.vehicleUpdateTime = vehicleUpdateTime;
	}

	public Calendar getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Calendar modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public Long getImeiId() {
		return imeiId;
	}

	public void setImeiId(Long imeiId) {
		this.imeiId = imeiId;
	}

	public String getVehicleTripStatus() {
		return vehicleTripStatus;
	}

	public void setVehicleTripStatus(String vehicleTripStatus) {
		this.vehicleTripStatus = vehicleTripStatus;
	}

	/**
	 * @return the gpsHdop
	 */
	public Integer getGpsHdop() {
		return gpsHdop;
	}

	/**
	 * @param gpsHdop
	 *            the gpsHdop to set
	 */
	public void setGpsHdop(Integer gpsHdop) {
		this.gpsHdop = gpsHdop;
	}

	/**
	 * @return the gpsCog
	 */
	public Float getGpsCog() {
		return gpsCog;
	}

	/**
	 * @param gpsCog
	 *            the gpsCog to set
	 */
	public void setGpsCog(Float gpsCog) {
		this.gpsCog = gpsCog;
	}

	/**
	 * @return the vehicleDirection
	 */
	public String getVehicleDirection() {
		return vehicleDirection;
	}

	/**
	 * @param vehicleDirection
	 *            the vehicleDirection to set
	 */
	public void setVehicleDirection(String vehicleDirection) {
		this.vehicleDirection = vehicleDirection;
	}

	public String getVehicleImage() {
		return vehicleImage;
	}

	public void setVehicleImage(String vehicleImage) {
		this.vehicleImage = vehicleImage;
	}

	public String getVehicleVariant() {
		return vehicleVariant;
	}

	public void setVehicleVariant(String vehicleVariant) {
		this.vehicleVariant = vehicleVariant;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getVehicleLastUpdateTime() {
		return vehicleLastUpdateTime;
	}

	public void setVehicleLastUpdateTime(String vehicleLastUpdateTime) {
		this.vehicleLastUpdateTime = vehicleLastUpdateTime;
	}

	public VehicleCanParamDto getVehicleCanParam() {
		return vehicleCanParam;
	}

	public void setVehicleCanParam(VehicleCanParamDto vehicleCanParam) {
		this.vehicleCanParam = vehicleCanParam;
	}

	@Override
	public String toString() {
		return "GPSVehicleDetailDto [vehicleId=" + vehicleId + ", vehicleImage=" + vehicleImage + ", vehicleStatus="
				+ vehicleStatus + ", registrationId=" + registrationId + ", currentLat=" + currentLat + ", currentLong="
				+ currentLong + ", identificationNum=" + identificationNum + ", description=" + description
				+ ", currentVehicleSpeed=" + currentVehicleSpeed + ", currentEngineSpeed=" + currentEngineSpeed
				+ ", userId=" + userId + ", vehicleUpdateTimeStr=" + vehicleUpdateTimeStr + ", gcmId=" + gcmId
				+ ", imeiId=" + imeiId + ", vehicleVariant=" + vehicleVariant + ", vin=" + vin + ", vehicleTripStatus="
				+ vehicleTripStatus + ", vehicleCanParam=" + vehicleCanParam + ", gpsHdop=" + gpsHdop + ", gpsCog="
				+ gpsCog + ", vehicleDirection=" + vehicleDirection + ", vehicleIdleTime=" + vehicleIdleTime
				+ ", vehicleUpdateTime=" + vehicleUpdateTime + ", modifiedDateTime=" + modifiedDateTime + ", regionId="
				+ regionId + ", groupId=" + groupId + ", vehicleLastUpdateTime=" + vehicleLastUpdateTime + "]";
	}

	public boolean isShowParam() {
		return isShowParam;
	}

	public void setShowParam(boolean isShowParam) {
		this.isShowParam = isShowParam;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getCurrentVehicleSpeed() {
		return currentVehicleSpeed;
	}

	public void setCurrentVehicleSpeed(Integer currentVehicleSpeed) {
		this.currentVehicleSpeed = currentVehicleSpeed;
	}

}
