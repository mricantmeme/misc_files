package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the DICV_COUNTRY database table.
 */
@Entity
@Table(name = "DICV_COUNTRY")
@NamedQuery(name = "findAllCountry", query = "SELECT d FROM DicvCountry d")
public class DicvCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DICV_COUNTRY_COUNTRYID_GENERATOR", sequenceName = "DICV_COUNTRY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DICV_COUNTRY_COUNTRYID_GENERATOR")
	@Column(name = "COUNTRY_ID")
	private Integer countryId;

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "ISO_CODE")
	private String isoCode;

	@Column(name = "COUNTRY_NAME")
	private String countryName;

	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name = "PHONECODE")
	private Integer phoneCode;

	@Column(name = "IS_DELETED")
	private Integer isDeleted;

	@Column(name = "CREATED_BY")
	private Integer dicvUserCreatedBy;

	@Column(name = "UPDATED_BY")
	private Integer dicvUserUpdatedBy;

	public DicvCountry() {
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(Integer phoneCode) {
		this.phoneCode = phoneCode;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getDicvUserCreatedBy() {
		return dicvUserCreatedBy;
	}

	public void setDicvUserCreatedBy(Integer dicvUserCreatedBy) {
		this.dicvUserCreatedBy = dicvUserCreatedBy;
	}

	public Integer getDicvUserUpdatedBy() {
		return dicvUserUpdatedBy;
	}

	public void setDicvUserUpdatedBy(Integer dicvUserUpdatedBy) {
		this.dicvUserUpdatedBy = dicvUserUpdatedBy;
	}

}