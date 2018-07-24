package com.dicv.truck.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the GEO_FENCE_CIRCLE database table.
 * 
 */
@Entity
@Table(name = "GEO_FENCE_CIRCLE")
@NamedQuery(name = "GeoFenceCircle.findAll", query = "SELECT g FROM GeoFenceCircle g")
public class GeoFenceCircle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "GEO_FENCE_CIRCLE_FENCECIRCLEID_GENERATOR", allocationSize = 1, sequenceName = "GEO_FENCE_CIRCLE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEO_FENCE_CIRCLE_FENCECIRCLEID_GENERATOR")
	@Column(name = "FENCE_CIRCLE_ID")
	private Integer fenceCircleId;

	@Column(name = "CREATED_BY")
	private Integer createdBy;

	@Column(name = "CREATED_DATE")
	private Calendar createdDate;

	@Column(name = "GEO_LATITUDE")
	private Float geoLatitude;

	@Column(name = "GEO_LONGITUDE")
	private Float geoLongitude;

	@Column(name = "GEO_RADIUS_IN_METERS")
	private Float geoRadiusInMeters;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@Column(name = "MODIFIED_DATE")
	private Calendar modifiedDate;

	@Column(name = "UPDATED_BY")
	private Integer updatedBy;

	// bi-directional many-to-one association to GeoFenceInfo
	@ManyToOne
	@JoinColumn(name = "GEO_FENCE_ID")
	private GeoFenceInfo geoFenceInfo;

	public GeoFenceCircle() {
	}

	public Integer getFenceCircleId() {
		return this.fenceCircleId;
	}

	public void setFenceCircleId(Integer fenceCircleId) {
		this.fenceCircleId = fenceCircleId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Calendar getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	public Float getGeoLatitude() {
		return this.geoLatitude;
	}

	public void setGeoLatitude(Float geoLatitude) {
		this.geoLatitude = geoLatitude;
	}

	public Float getGeoLongitude() {
		return this.geoLongitude;
	}

	public void setGeoLongitude(Float geoLongitude) {
		this.geoLongitude = geoLongitude;
	}

	public Float getGeoRadiusInMeters() {
		return this.geoRadiusInMeters;
	}

	public void setGeoRadiusInMeters(Float geoRadiusInMeters) {
		this.geoRadiusInMeters = geoRadiusInMeters;
	}

	public int getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Calendar getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Calendar modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public GeoFenceInfo getGeoFenceInfo() {
		return this.geoFenceInfo;
	}

	public void setGeoFenceInfo(GeoFenceInfo geoFenceInfo) {
		this.geoFenceInfo = geoFenceInfo;
	}

}