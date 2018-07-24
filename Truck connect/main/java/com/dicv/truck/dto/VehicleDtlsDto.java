package com.dicv.truck.dto;

import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleDtlsDto {

	private Integer vehicleId;
	private String vehicleStatus;
	private String registrationId;
	private String vehicleIdleTimeStr; // HH:mm:ss
	private Double currentLat;
	private Double currentLong;
	private String identificationNum;
	private String description;
	private Integer currentVehicleSpeed;
	private Integer currentEngineSpeed;
	private Integer ownerId;
	private String vehicleUpdateTimeStr;
	private Integer gcmId;
	private Integer vehicleIdleTime; // in milliseconds
	private Date vehicleUpdateTime;
	private Calendar modifiedDateTime;
	private String vehicleRunningStatus;
	private String countryCode;
	private String countryName;

	/** the below fields are added newly by seema **/
	private String purchaseDate;
	private String routeNumber;
	private String vin;
	private Integer regionId;
	private String regionName;
	private String vehicleType;
	private String recordStatus;
	private String tabletIMEINumber;
	private String gpsImei;
	private String gpsImeiDescription;
	private String gpsSimNumber;
	private Integer gpsCountryId;
	private String gpsProvider;
	private String tabletSimNumber;
	private Integer tabletCountryId;
	private String tabletProvider;
	private String gcmRegistrationId;
	private String imageString;
	private Integer vehicleCategory;
	private String vehicleCategoryDesc;
	private Double maxPayLoadCapacity;
	private Integer defaultDriverId;
	private Integer maxVehicleSpeed;
	private Integer vehicleTypeId;
	private Integer groupId;
	private String groupName;
	private Integer customerAdminId;
	private Integer fleetManagerId;
	private String dealerName;
	private Integer rootAdminGroupId;
	private String rootAdminGroupName;
	private String defaultDriver;
	private String customerAdmin;
	private String companyName;
	private Integer companyId;
	private String gpsFitmentDate;

	public Integer getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Integer vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public VehicleDtlsDto() {

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

	public String getVehicleIdleTimeStr() {
		return vehicleIdleTimeStr;
	}

	public void setVehicleIdleTimeStr(String vehicleIdleTimeStr) {
		this.vehicleIdleTimeStr = vehicleIdleTimeStr;
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

	public Integer getCurrentVehicleSpeed() {
		return currentVehicleSpeed;
	}

	public void setCurrentVehicleSpeed(Integer currentVehicleSpeed) {
		this.currentVehicleSpeed = currentVehicleSpeed;
	}

	public Integer getCurrentEngineSpeed() {
		return currentEngineSpeed;
	}

	public void setCurrentEngineSpeed(Integer currentEngineSpeed) {
		this.currentEngineSpeed = currentEngineSpeed;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getVehicleUpdateTimeStr() {
		return vehicleUpdateTimeStr;
	}

	public void setVehicleUpdateTimeStr(String vehicleUpdateTimeStr) {
		this.vehicleUpdateTimeStr = vehicleUpdateTimeStr;
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

	public String getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
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

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getGpsImeiDescription() {
		return gpsImeiDescription;
	}

	public void setGpsImeiDescription(String gpsImeiDescription) {
		this.gpsImeiDescription = gpsImeiDescription;
	}

	public Integer getGpsCountryId() {
		return gpsCountryId;
	}

	public void setGpsCountryId(Integer gpsCountryId) {
		this.gpsCountryId = gpsCountryId;
	}

	public String getGpsProvider() {
		return gpsProvider;
	}

	public void setGpsProvider(String gpsProvider) {
		this.gpsProvider = gpsProvider;
	}

	public Integer getTabletCountryId() {
		return tabletCountryId;
	}

	public void setTabletCountryId(Integer tabletCountryId) {
		this.tabletCountryId = tabletCountryId;
	}

	public String getTabletProvider() {
		return tabletProvider;
	}

	public void setTabletProvider(String tabletProvider) {
		this.tabletProvider = tabletProvider;
	}

	public String getGcmRegistrationId() {
		return gcmRegistrationId;
	}

	public void setGcmRegistrationId(String gcmRegistrationId) {
		this.gcmRegistrationId = gcmRegistrationId;
	}

	public String getImageString() {
		return imageString;
	}

	public void setImageString(String imageString) {
		this.imageString = imageString;
	}

	public Integer getVehicleCategory() {
		return vehicleCategory;
	}

	public void setVehicleCategory(Integer vehicleCategory) {
		this.vehicleCategory = vehicleCategory;
	}

	public Integer getDefaultDriverId() {
		return defaultDriverId;
	}

	public void setDefaultDriverId(Integer defaultDriverId) {
		this.defaultDriverId = defaultDriverId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleCategoryDesc() {
		return vehicleCategoryDesc;
	}

	public void setVehicleCategoryDesc(String vehicleCategoryDesc) {
		this.vehicleCategoryDesc = vehicleCategoryDesc;
	}

	public String getVehicleRunningStatus() {
		return vehicleRunningStatus;
	}

	public void setVehicleRunningStatus(String vehicleRunningStatus) {
		this.vehicleRunningStatus = vehicleRunningStatus;
	}

	public Integer getCustomerAdminId() {
		return customerAdminId;
	}

	public void setCustomerAdminId(Integer customerAdminId) {
		this.customerAdminId = customerAdminId;
	}

	public Integer getFleetManagerId() {
		return fleetManagerId;
	}

	public void setFleetManagerId(Integer fleetManagerId) {
		this.fleetManagerId = fleetManagerId;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public Double getMaxPayLoadCapacity() {
		return maxPayLoadCapacity;
	}

	public void setMaxPayLoadCapacity(Double maxPayLoadCapacity) {
		this.maxPayLoadCapacity = maxPayLoadCapacity;
	}

	public String getTabletIMEINumber() {
		return tabletIMEINumber;
	}

	public void setTabletIMEINumber(String tabletIMEINumber) {
		this.tabletIMEINumber = tabletIMEINumber;
	}

	public String getGpsImei() {
		return gpsImei;
	}

	public void setGpsImei(String gpsImei) {
		this.gpsImei = gpsImei;
	}

	public String getGpsSimNumber() {
		return gpsSimNumber;
	}

	public void setGpsSimNumber(String gpsSimNumber) {
		this.gpsSimNumber = gpsSimNumber;
	}

	public String getTabletSimNumber() {
		return tabletSimNumber;
	}

	public void setTabletSimNumber(String tabletSimNumber) {
		this.tabletSimNumber = tabletSimNumber;
	}

	public Integer getMaxVehicleSpeed() {
		return maxVehicleSpeed;
	}

	public void setMaxVehicleSpeed(Integer maxVehicleSpeed) {
		this.maxVehicleSpeed = maxVehicleSpeed;
	}

	public Integer getRootAdminGroupId() {
		return rootAdminGroupId;
	}

	public void setRootAdminGroupId(Integer rootAdminGroupId) {
		this.rootAdminGroupId = rootAdminGroupId;
	}

	public String getRootAdminGroupName() {
		return rootAdminGroupName;
	}

	public void setRootAdminGroupName(String rootAdminGroupName) {
		this.rootAdminGroupName = rootAdminGroupName;
	}

	public String getDefaultDriver() {
		return defaultDriver;
	}

	public void setDefaultDriver(String defaultDriver) {
		this.defaultDriver = defaultDriver;
	}

	public String getCustomerAdmin() {
		return customerAdmin;
	}

	public void setCustomerAdmin(String customerAdmin) {
		this.customerAdmin = customerAdmin;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getGpsFitmentDate() {
		return gpsFitmentDate;
	}

	public void setGpsFitmentDate(String gpsFitmentDate) {
		this.gpsFitmentDate = gpsFitmentDate;
	}


}
