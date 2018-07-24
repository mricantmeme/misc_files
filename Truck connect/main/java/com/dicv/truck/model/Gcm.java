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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the GCM database table.
 * 
 */
@Entity
@Table(name = "GCM")
public class Gcm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "GCM_GCMID_GENERATOR", sequenceName = "GCM_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GCM_GCMID_GENERATOR")
	@Column(name = "GCM_ID")
	private Integer gcmId;

	@Column(name = "MODIFIED_DATE_TIME")
	private Timestamp modifiedDateTime;

	@Column(name = "REGISTRATION_DATE")
	private Timestamp registrationDate;

	@Column(name = "REGISTRATION_ID")
	private String registrationId;

	@Column(name = "REGISTRATION_LOCATION")
	private String registrationLocation;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "gcm")
	private Vehicle vehicle;

	public Gcm() {
	}

	public Integer getGcmId() {
		return this.gcmId;
	}

	public void setGcmId(Integer gcmId) {
		this.gcmId = gcmId;
	}

	public Timestamp getModifiedDateTime() {
		return this.modifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public Timestamp getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRegistrationId() {
		return this.registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getRegistrationLocation() {
		return this.registrationLocation;
	}

	public void setRegistrationLocation(String registrationLocation) {
		this.registrationLocation = registrationLocation;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	/*
	 * public Vehicle addVehicle(Vehicle vehicle) { getVehicles().add(vehicle);
	 * vehicle.setGcm(this);
	 * 
	 * return vehicle; }
	 * 
	 * public Vehicle removeVehicle(Vehicle vehicle) {
	 * getVehicles().remove(vehicle); vehicle.setGcm(null);
	 * 
	 * return vehicle; }
	 */

}