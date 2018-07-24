package com.dicv.truck.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the POINT_OF_INTEREST database table.
 * 
 */
@Entity
@Table(name = "POINT_OF_INTEREST")
@NamedQuery(name = "PointOfInterest.findAll", query = "SELECT p FROM PointOfInterest p")
public class PointOfInterest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "POINT_OF_INTEREST_POINTOFINTERESTID_GENERATOR", sequenceName = "POINT_OF_INTREST_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POINT_OF_INTEREST_POINTOFINTERESTID_GENERATOR")
	@Column(name = "POINT_OF_INTEREST_ID")
	private Integer pointOfInterestId;

	private Double latitude;

	private Double longitude;

	@Column(name = "POI_TYPE")
	private String poiType;

	public PointOfInterest() {
	}

	public Integer getPointOfInterestId() {
		return this.pointOfInterestId;
	}

	public void setPointOfInterestId(Integer pointOfInterestId) {
		this.pointOfInterestId = pointOfInterestId;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getPoiType() {
		return this.poiType;
	}

	public void setPoiType(String poiType) {
		this.poiType = poiType;
	}

}