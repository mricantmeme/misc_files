package com.dicv.truck.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the DICV_USER_HISTORY database table.
 * 
 */
@Entity
@Table(name = "DICV_USER_HISTORY")
@NamedQuery(name = "DicvUserHistory.findAll", query = "SELECT d FROM DicvUserHistory d")
public class DicvUserHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DICV_USER_HISTORY_USERID_GENERATOR", sequenceName = "DICV_USER_HISTORY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DICV_USER_HISTORY_USERID_GENERATOR")
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "ADDRESS_LINE1")
	private String addressLine1;

	@Column(name = "ADDRESS_LINE2")
	private String addressLine2;

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "CREATED_BY")
	private BigDecimal createdBy;

	@Column(name = "DRIVING_LICENSE_NO")
	private String drivingLicenseNo;

	private String email;

	@Column(name = "EMERGENCY_CONTACTNO_1")
	private String emergencyContactno1;

	@Column(name = "EMERGENCY_CONTACTNO_2")
	private String emergencyContactno2;

	@Column(name = "EMERGENCY_CONTACTNO_3")
	private String emergencyContactno3;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	private String mobile;

	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;

	private String password;

	@Column(name = "RECORD_STATUS")
	private String recordStatus;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "VERSION_NO")
	private BigDecimal versionNo;

	// bi-directional many-to-one association to DicvCity
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CITY_ID")
	private DicvCity dicvCity;

	// bi-directional many-to-one association to DicvGroup
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "GROUP_ID")
	private DicvGroup dicvGroup;

	// bi-directional many-to-one association to DicvUser
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "UPDATED_BY")
	private DicvUser dicvUser;

	// bi-directional many-to-one association to Photo
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "PHOTO_ID")
	private Photo photo;

	public DicvUserHistory() {
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

	public BigDecimal getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getVersionNo() {
		return this.versionNo;
	}

	public void setVersionNo(BigDecimal versionNo) {
		this.versionNo = versionNo;
	}

	public DicvCity getDicvCity() {
		return this.dicvCity;
	}

	public void setDicvCity(DicvCity dicvCity) {
		this.dicvCity = dicvCity;
	}

	public DicvGroup getDicvGroup() {
		return this.dicvGroup;
	}

	public void setDicvGroup(DicvGroup dicvGroup) {
		this.dicvGroup = dicvGroup;
	}

	public DicvUser getDicvUser() {
		return this.dicvUser;
	}

	public void setDicvUser(DicvUser dicvUser) {
		this.dicvUser = dicvUser;
	}

	public Photo getPhoto() {
		return this.photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

}