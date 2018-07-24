package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the USER_PROFILE database table.
 */
@Entity
@Table(name = "DICV_PROFILE")
public class UserProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1315325285821126027L;

	@Id
	@SequenceGenerator(name = "PROFILE_PREFERENCE_SEQ", sequenceName = "PROFILE_PREFERENCE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_PREFERENCE_SEQ")
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "USER_ROLE")
	private String userRole;

	@Column(name = "USER_LOCATION")
	private String userLocation;

	@Column(name = "USER_EMAIL")
	private String userEmail;

	@Column(name = "USER_ACTIVE")
	private Integer userActive;

	@Column(name = "CREATED_BY")
	private Integer createdBy;

	@Column(name = "MODIFIED_BY")
	private Integer modifiedBy;

	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	public Integer getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getUserRole() {
		return userRole;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public Integer getUserActive() {
		return userActive;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserActive(Integer userActive) {
		this.userActive = userActive;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

}
