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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the DICV_CITY database table.
 */
@Entity
@Table(name = "DICV_CITY")
@NamedQueries({
		@NamedQuery(name = "DicvCity.findAll", query = "SELECT d FROM DicvCity d"),
		@NamedQuery(query = "Select o FROM DicvCity o join o.dicvState a where a.stateId=:stateId ", name = "findByStateId") })
public class DicvCity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DICV_CITY_CITYID_GENERATOR", sequenceName = "DICV_CITY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DICV_CITY_CITYID_GENERATOR")
	@Column(name = "CITY_ID")
	private Integer cityId;

	@Column(name = "CITY_CODE")
	private String cityCode;

	@Column(name = "CITY_NAME")
	private String cityName;

	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	// bi-directional many-to-one association to DicvCountry
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "COUNTRY_ID")
	private DicvCountry dicvCountry;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CREATED_BY")
	private DicvUser dicvUserCreatedBy;

	// bi-directional many-to-one association to DicvUser
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "UPDATED_BY")
	private DicvUser dicvUserUpdatedBy;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "STATE_ID")
	private DicvState dicvState;

	public DicvCity() {
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public DicvCountry getDicvCountry() {
		return this.dicvCountry;
	}

	public void setDicvCountry(DicvCountry dicvCountry) {
		this.dicvCountry = dicvCountry;
	}

	public DicvState getDicvState() {
		return this.dicvState;
	}

	public void setDicvState(DicvState dicvState) {
		this.dicvState = dicvState;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public DicvUser getDicvUserCreatedBy() {
		return dicvUserCreatedBy;
	}

	public void setDicvUserCreatedBy(DicvUser dicvUserCreatedBy) {
		this.dicvUserCreatedBy = dicvUserCreatedBy;
	}

	public DicvUser getDicvUserUpdatedBy() {
		return dicvUserUpdatedBy;
	}

	public void setDicvUserUpdatedBy(DicvUser dicvUserUpdatedBy) {
		this.dicvUserUpdatedBy = dicvUserUpdatedBy;
	}

}