package com.dicv.truck.dto;

public class UserDto {

	private Integer userId;

	private String userName;

	private String firstName;

	private String lastName;

	private String addressLine1;

	private String cityName;

	private Integer countryId;

	private String countryCode;

	private Integer phoneCode;

	private String zipCode;

	private String mobileNumber;

	private String emailId;

	private String emergencyContact1;

	private String emergencyContact2;

	private String landlineno;

	private Integer createdOrUpdatedByUserId;

	private String drivingLicenseNo;

	private String licenseExpireDate;

	private String imageSource;

	private Integer roleId;

	private String role;

	private Integer groupId;

	private String groupName;

	private String genericUser;

	private String recordStatus;

	private String companyAddress;

	private Integer companyId;

	private String companyName;

	private String stateName;

	private Integer customerAdminId;

	private Integer fleetManagerId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmergencyContact1() {
		return emergencyContact1;
	}

	public void setEmergencyContact1(String emergencyContact1) {
		this.emergencyContact1 = emergencyContact1;
	}

	public String getEmergencyContact2() {
		return emergencyContact2;
	}

	public void setEmergencyContact2(String emergencyContact2) {
		this.emergencyContact2 = emergencyContact2;
	}

	public String getLandlineno() {
		return landlineno;
	}

	public void setLandlineno(String landlineno) {
		this.landlineno = landlineno;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGenericUser() {
		return genericUser;
	}

	public void setGenericUser(String genericUser) {
		this.genericUser = genericUser;
	}

	public Integer getCreatedOrUpdatedByUserId() {
		return createdOrUpdatedByUserId;
	}

	public void setCreatedOrUpdatedByUserId(Integer createdOrUpdatedByUserId) {
		this.createdOrUpdatedByUserId = createdOrUpdatedByUserId;
	}

	public String getDrivingLicenseNo() {
		return drivingLicenseNo;
	}

	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}

	public String getImageSource() {
		return imageSource;
	}

	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getLicenseExpireDate() {
		return licenseExpireDate;
	}

	public void setLicenseExpireDate(String licenseExpireDate) {
		this.licenseExpireDate = licenseExpireDate;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Integer getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(Integer phoneCode) {
		this.phoneCode = phoneCode;
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

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", addressLine1=" + addressLine1 + ", cityName=" + cityName + ", countryId=" + countryId
				+ ", countryCode=" + countryCode + ", phoneCode=" + phoneCode + ", zipCode=" + zipCode
				+ ", mobileNumber=" + mobileNumber + ", emailId=" + emailId + ", emergencyContact1=" + emergencyContact1
				+ ", emergencyContact2=" + emergencyContact2 + ", landlineno=" + landlineno
				+ ", createdOrUpdatedByUserId=" + createdOrUpdatedByUserId + ", drivingLicenseNo=" + drivingLicenseNo
				+ ", licenseExpireDate=" + licenseExpireDate + ", imageSource=" + imageSource + ", roleId=" + roleId
				+ ", role=" + role + ", groupId=" + groupId + ", groupName=" + groupName + ", genericUser="
				+ genericUser + ", recordStatus=" + recordStatus + ", companyName=" + companyName + ", companyAddress="
				+ companyAddress + ", stateName=" + stateName + ", customerAdminId=" + customerAdminId
				+ ", fleetManagerId=" + fleetManagerId + "]";
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}
