package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

/**
 * The persistent class for the DICV_STATE database table.
 * 
 */
@Entity
@Table(name = "DICV_STATE")
@NamedQueries({
		@NamedQuery(query = "SELECT d FROM DicvState d", name = "DicvState.findAll"),
		@NamedQuery(query = "SELECT d FROM DicvState d join d.dicvCountry c where c.countryId=:countryId", name = "findStateByCountryId") })
public class DicvState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DICV_STATE_SEQ", sequenceName = "DICV_STATE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DICV_STATE_SEQ")
	@Column(name = "STATE_ID")
	private Integer stateId;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name = "STATE_CODE")
	private String stateCode;

	@Column(name = "STATE_NAME")
	private String stateName;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CREATED_BY")
	private DicvUser dicvUserCreatedBy;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "UPDATED_BY")
	private DicvUser dicvUserUpdatedBy;

	// bi-directional many-to-one association to DicvCity
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "dicvState")
	@Where(clause = "IS_DELETED=0")
	private List<DicvCity> dicvCities;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "COUNTRY_ID")
	private DicvCountry dicvCountry;

	public DicvState() {
	}

	public Integer getStateId() {
		return this.stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public int getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStateCode() {
		return this.stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<DicvCity> getDicvCities() {
		return this.dicvCities;
	}

	public void setDicvCities(List<DicvCity> dicvCities) {
		this.dicvCities = dicvCities;
	}

	public DicvCity addDicvCity(DicvCity dicvCity) {
		getDicvCities().add(dicvCity);
		dicvCity.setDicvState(this);

		return dicvCity;
	}

	public DicvCity removeDicvCity(DicvCity dicvCity) {
		getDicvCities().remove(dicvCity);
		dicvCity.setDicvState(null);

		return dicvCity;
	}

	public DicvCountry getDicvCountry() {
		return dicvCountry;
	}

	public void setDicvCountry(DicvCountry dicvCountry) {
		this.dicvCountry = dicvCountry;
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