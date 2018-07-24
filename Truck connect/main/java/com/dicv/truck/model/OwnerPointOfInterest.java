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
 * The persistent class for the OWNER_POINT_OF_INTEREST database table.
 * 
 */
@Entity
@Table(name = "OWNER_POINT_OF_INTEREST")
@NamedQuery(name = "OwnerPointOfInterest.findAll", query = "SELECT o FROM OwnerPointOfInterest o")
public class OwnerPointOfInterest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "LATITUDE")
	private double latitude;

	@Column(name = "LONGITUDE")
	private double longitude;

	@Id
	@SequenceGenerator(name = "OWNER_POINT_OF_INTEREST_ID_GENERATOR", sequenceName = "OWNER_POINT_OF_INTEREST_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OWNER_POINT_OF_INTEREST_ID_GENERATOR")
	@Column(name = "OWNER_POINT_OF_INTEREST_ID")
	private Integer ownerPointOfInterestId;

	@Column(name = "TYPE_OF_POI")
	private String typeOfPoi;

	public OwnerPointOfInterest() {
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Integer getOwnerPointOfInterestId() {
		return this.ownerPointOfInterestId;
	}

	public void setOwnerPointOfInterestId(Integer ownerPointOfInterestId) {
		this.ownerPointOfInterestId = ownerPointOfInterestId;
	}

	public String getTypeOfPoi() {
		return this.typeOfPoi;
	}

	public void setTypeOfPoi(String typeOfPoi) {
		this.typeOfPoi = typeOfPoi;
	}

}