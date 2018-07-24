package com.dicv.truck.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the VEHICLE_TO_GEO_FENCE database table.
 * 
 */
@Entity
@Table(name = "VEHICLE_TO_GEO_FENCE")
public class VehicleToGeoFence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "VEHICLE_TO_GEO_FENCE_VEHGEOFENCEID_GENERATOR", sequenceName = "VEHICLE_TO_GEO_FENCE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEHICLE_TO_GEO_FENCE_VEHGEOFENCEID_GENERATOR")
	@Column(name = "VEH_GEO_FENCE_ID")
	private Integer vehGeoFenceId;

	@Column(name = "CREATED_ON")
	private Calendar createdOn;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@Column(name = "LAST_UPDATED_ON")
	private Calendar lastUpdatedOn;

	@Column(name = "CREATED_BY")
	private Integer createdBy;

	@Column(name = "UPDATED_BY")
	private Integer updatedBy;

	@ManyToOne
	@JoinColumn(name = "GEO_FENCE_ID")
	private GeoFenceInfo geoFenceInfo;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	public VehicleToGeoFence() {
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getVehGeoFenceId() {
		return this.vehGeoFenceId;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Calendar getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Calendar lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public void setVehGeoFenceId(Integer vehGeoFenceId) {
		this.vehGeoFenceId = vehGeoFenceId;
	}

	public GeoFenceInfo getGeoFenceInfo() {
		return this.geoFenceInfo;
	}

	public void setGeoFenceInfo(GeoFenceInfo geoFenceInfo) {
		this.geoFenceInfo = geoFenceInfo;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}