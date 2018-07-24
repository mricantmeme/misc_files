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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the GEO_FENCE_POLYGON database table.
 * 
 */
@Entity
@Table(name = "GEO_FENCE_POLYGON")
public class GeoFencePolygon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "GEO_FENCE_POLYGON_FENCEPOLYGONID_GENERATOR", allocationSize = 1, sequenceName = "GEO_FENCE_POLYGON_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEO_FENCE_POLYGON_FENCEPOLYGONID_GENERATOR")
	@Column(name = "FENCE_POLYGON_ID")
	private Integer fencePolygonId;

	@Column(name = "CREATED_BY")
	private Integer createdBy;

	@Column(name = "CREATED_DATE")
	private Calendar createdDate;

	@Column(name = "GEO_CORDINATES")
	private String geoCordinates;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@Column(name = "MODIFIED_DATE")
	private Calendar modifiedDate;

	@Column(name = "UPDATED_BY")
	private Integer updatedBy;

	@ManyToOne
	@JoinColumn(name = "GEO_FENCE_ID")
	private GeoFenceInfo geoFenceInfo;

	public GeoFencePolygon() {
	}

	public Integer getFencePolygonId() {
		return this.fencePolygonId;
	}

	public void setFencePolygonId(Integer fencePolygonId) {
		this.fencePolygonId = fencePolygonId;
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

	public String getGeoCordinates() {
		return this.geoCordinates;
	}

	public void setGeoCordinates(String geoCordinates) {
		this.geoCordinates = geoCordinates;
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