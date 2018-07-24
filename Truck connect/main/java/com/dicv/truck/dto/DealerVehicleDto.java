package com.dicv.truck.dto;

public class DealerVehicleDto {

	private Integer vehicleId;

	private Long gpsSimNumber;

	private Long gpsImei;

	private String registrationId;

	private String vin;

	private Integer vehicleTypeId;

	private Integer vehicleCategory;

	private String vehicleTypeName;

	private String vehicleCategoryName;

	private String purchaseDate;

	private String description;

	private Integer maxVehicleSpeed;

	private Double maxPayLoadCapacity;

	private Boolean gpsTranmission = false;

	private Boolean vehicleCanParam = false;

	private Integer userId;

	private String companyName;

	private Integer companyId;

	private String ermUser;

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Long getGpsSimNumber() {
		return gpsSimNumber;
	}

	public void setGpsSimNumber(Long gpsSimNumber) {
		this.gpsSimNumber = gpsSimNumber;
	}

	public Long getGpsImei() {
		return gpsImei;
	}

	public void setGpsImei(Long gpsImei) {
		this.gpsImei = gpsImei;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Integer getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Integer vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public Integer getVehicleCategory() {
		return vehicleCategory;
	}

	public void setVehicleCategory(Integer vehicleCategory) {
		this.vehicleCategory = vehicleCategory;
	}

	public String getVehicleTypeName() {
		return vehicleTypeName;
	}

	public void setVehicleTypeName(String vehicleTypeName) {
		this.vehicleTypeName = vehicleTypeName;
	}

	public String getVehicleCategoryName() {
		return vehicleCategoryName;
	}

	public void setVehicleCategoryName(String vehicleCategoryName) {
		this.vehicleCategoryName = vehicleCategoryName;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaxVehicleSpeed() {
		return maxVehicleSpeed;
	}

	public void setMaxVehicleSpeed(Integer maxVehicleSpeed) {
		this.maxVehicleSpeed = maxVehicleSpeed;
	}

	public Double getMaxPayLoadCapacity() {
		return maxPayLoadCapacity;
	}

	public void setMaxPayLoadCapacity(Double maxPayLoadCapacity) {
		this.maxPayLoadCapacity = maxPayLoadCapacity;
	}

	public Boolean getGpsTranmission() {
		return gpsTranmission;
	}

	public void setGpsTranmission(Boolean gpsTranmission) {
		this.gpsTranmission = gpsTranmission;
	}

	public Boolean getVehicleCanParam() {
		return vehicleCanParam;
	}

	public void setVehicleCanParam(Boolean vehicleCanParam) {
		this.vehicleCanParam = vehicleCanParam;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getErmUser() {
		return ermUser;
	}

	public void setErmUser(String ermUser) {
		this.ermUser = ermUser;
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

	@Override
	public String toString() {
		return "DealerVehicleDto [vehicleId=" + vehicleId + ", gpsSimNumber=" + gpsSimNumber + ", gpsImei=" + gpsImei
				+ ", registrationId=" + registrationId + ", vin=" + vin + ", vehicleTypeId=" + vehicleTypeId
				+ ", vehicleCategory=" + vehicleCategory + ", vehicleTypeName=" + vehicleTypeName
				+ ", vehicleCategoryName=" + vehicleCategoryName + ", purchaseDate=" + purchaseDate + ", description="
				+ description + ", maxVehicleSpeed=" + maxVehicleSpeed + ", maxPayLoadCapacity=" + maxPayLoadCapacity
				+ ", gpsTranmission=" + gpsTranmission + ", vehicleCanParam=" + vehicleCanParam + ", userId=" + userId
				+ ", companyName=" + companyName + ", companyId=" + companyId + ", ermUser=" + ermUser + "]";
	}

}
