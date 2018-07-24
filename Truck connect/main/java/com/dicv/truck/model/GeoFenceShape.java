package com.dicv.truck.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the GEO_FENCE_SHAPE database table.
 * 
 */
@Entity
@Table(name = "GEO_FENCE_SHAPE")
public class GeoFenceShape implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "GEO_FENCE_SHAPE_ID")
	private Integer geoFenceShapeId;

	@Column(name = "CREATED_BY")
	private Integer createdBy;

	@Column(name = "CREATED_DATE")
	private Calendar createdDate;

	@Column(name = "GEO_FENCE_SHAPE_NAME")
	private String geoFenceShapeName;

	@Column(name = "IS_DELETED")
	private Boolean isDeleted;

	@Column(name = "MODIFIED_DATE")
	private Calendar modifiedDate;

	@Column(name = "UPDATED_BY")
	private Integer updatedBy;

	@OneToMany(mappedBy = "geoFenceShape")
	private List<GeoFenceInfo> geoFenceInfos;

	public GeoFenceShape() {
	}

	public Integer getGeoFenceShapeId() {
		return this.geoFenceShapeId;
	}

	public void setGeoFenceShapeId(Integer geoFenceShapeId) {
		this.geoFenceShapeId = geoFenceShapeId;
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

	public String getGeoFenceShapeName() {
		return this.geoFenceShapeName;
	}

	public void setGeoFenceShapeName(String geoFenceShapeName) {
		this.geoFenceShapeName = geoFenceShapeName;
	}

	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
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

	public List<GeoFenceInfo> getGeoFenceInfos() {
		return this.geoFenceInfos;
	}

	public void setGeoFenceInfos(List<GeoFenceInfo> geoFenceInfos) {
		this.geoFenceInfos = geoFenceInfos;
	}

	public GeoFenceInfo addGeoFenceInfo(GeoFenceInfo geoFenceInfo) {
		getGeoFenceInfos().add(geoFenceInfo);
		geoFenceInfo.setGeoFenceShape(this);

		return geoFenceInfo;
	}

	public GeoFenceInfo removeGeoFenceInfo(GeoFenceInfo geoFenceInfo) {
		getGeoFenceInfos().remove(geoFenceInfo);
		geoFenceInfo.setGeoFenceShape(null);

		return geoFenceInfo;
	}

}