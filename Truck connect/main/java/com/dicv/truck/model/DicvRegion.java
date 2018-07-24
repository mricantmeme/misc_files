package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the DICV_REGIONS database table.
 *
 */
@Entity
@Table(name = "DICV_REGIONS")
@NamedQueries({
		@NamedQuery(query = "Select g FROM DicvRegion g where g.subRegionId=:subRegionId", name = "DicvRegion.findBySubRegionId"),
		@NamedQuery(query = "Select g FROM DicvRegion g where g.regionId=:regionId", name = "DicvRegion.findRegionsById"),
		@NamedQuery(query = "Select o FROM DicvRegion o  where o.createdByUser in (:userIdList) and o.isDeleted=0", name = "DicvRegion.findAllRegions"),
		@NamedQuery(query = "Select r FROM DicvRegion r where r.regionName=:regionName  and r.isDeleted=0", name = "DicvRegion.findRegionByName"),
		@NamedQuery(query = "select t from DicvRegion t where t.regionName=:regionName  and t.isDeleted=0", name = "findRegionExist"),
		@NamedQuery(query = "select count(t) from DicvRegion t where t.createdByUser=:createdByUser", name = "findDicvRegionCountByUser") })
public class DicvRegion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DICV_REGIONS_REGIONID_GENERATOR", sequenceName = "DICV_REGIONS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DICV_REGIONS_REGIONID_GENERATOR")
	@Column(name = "REGION_ID")
	private Integer regionId;

	@Column(name = "REGION_NAME")
	private String regionName;

	@Column(name = "SUB_REGION_ID")
	private Integer subRegionId;

	@Column(name = "CREATED_DATE")
	private Timestamp createdOn;

	@Column(name = "MODIFIED_DATE")
	private Timestamp updatedOn;

	@Column(name = "IS_DELETED")
	private Integer isDeleted;

	@Column(name = "CREATED_BY")
	private Integer createdByUser;

	@Column(name = "UPDATED_BY")
	private Integer updatedByUser;

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getSubRegionId() {
		return subRegionId;
	}

	public void setSubRegionId(Integer subRegionId) {
		this.subRegionId = subRegionId;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(Integer createdByUser) {
		this.createdByUser = createdByUser;
	}

	public Integer getUpdatedByUser() {
		return updatedByUser;
	}

	public void setUpdatedByUser(Integer updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

}