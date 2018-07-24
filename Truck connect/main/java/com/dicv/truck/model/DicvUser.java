package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DICV_USER")
public class DicvUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DICV_USER_SEQ", sequenceName = "DICV_USER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DICV_USER_SEQ")
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "ADDRESS_LINE1")
	private String addressLine1;

	@Column(name = "ADDRESS_LINE2")
	private String addressLine2;

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "CREATED_BY")
	private Integer createdBy;

	@Column(name = "LICENSE_EXPIRE_DATE")
	private Timestamp licenseExpireDate;

	@Column(name = "DRIVING_LICENSE_NO")
	private String drivingLicenseNo;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "EMERGENCY_CONTACTNO_1")
	private String emergencyContactno1;

	@Column(name = "EMERGENCY_CONTACTNO_2")
	private String emergencyContactno2;

	@Column(name = "EMERGENCY_CONTACTNO_3")
	private String emergencyContactno3;

	@Column(name = "LANDLINE_NO")
	private String landlineno;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "MOBILE")
	private String mobile;

	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name = "USER_PASSWORD")
	private String userPassword;

	@Column(name = "RECORD_STATUS")
	private String recordStatus;

	@Column(name = "UPDATED_BY")
	private Integer updatedBy;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "VERSION_NO")
	private Long versionNo;

	@Column(name = "USER_STATUS")
	private String userStatus;

	@Column(name = "MANAGER_ID")
	private Integer managerId;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "ZIP_CODE")
	private String zipCode;

	@Column(name = "CHANGE_PASSWORD")
	private Integer changePassword;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_TYPE")
	private UserType userType;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "COUNTRY_ID")
	private DicvCountry dicvCountry;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "GROUP_ID")
	private DicvGroup dicvGroup;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "PHOTO_ID")
	private Photo photo;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "COMPANY_ID")
	private DicvCompany dicvCompany;

	public DicvUser() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getDrivingLicenseNo() {
		return this.drivingLicenseNo;
	}

	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmergencyContactno1() {
		return this.emergencyContactno1;
	}

	public void setEmergencyContactno1(String emergencyContactno1) {
		this.emergencyContactno1 = emergencyContactno1;
	}

	public String getEmergencyContactno2() {
		return this.emergencyContactno2;
	}

	public void setEmergencyContactno2(String emergencyContactno2) {
		this.emergencyContactno2 = emergencyContactno2;
	}

	public String getEmergencyContactno3() {
		return this.emergencyContactno3;
	}

	public void setEmergencyContactno3(String emergencyContactno3) {
		this.emergencyContactno3 = emergencyContactno3;
	}

	public String getLandlineno() {
		return this.landlineno;
	}

	public void setLandlineno(String landlineno) {
		this.landlineno = landlineno;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public DicvGroup getDicvGroup() {
		return this.dicvGroup;
	}

	public void setDicvGroup(DicvGroup dicvGroup) {
		this.dicvGroup = dicvGroup;
	}

	public Photo getPhoto() {
		return this.photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public Timestamp getLicenseExpireDate() {
		return licenseExpireDate;
	}

	public void setLicenseExpireDate(Timestamp licenseExpireDate) {
		this.licenseExpireDate = licenseExpireDate;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public DicvCountry getDicvCountry() {
		return dicvCountry;
	}

	public void setDicvCountry(DicvCountry dicvCountry) {
		this.dicvCountry = dicvCountry;
	}

	public DicvCompany getDicvCompany() {
		return dicvCompany;
	}

	public void setDicvCompany(DicvCompany dicvCompany) {
		this.dicvCompany = dicvCompany;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(Integer changePassword) {
		this.changePassword = changePassword;
	}

}