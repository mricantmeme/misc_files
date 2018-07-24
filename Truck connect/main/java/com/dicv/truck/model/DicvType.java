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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the DICV_TYPE database table.
 *
 */
@Entity
@Table(name = "DICV_TYPE")
public class DicvType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DICV_TYPE_TYPEID_GENERATOR", sequenceName = "DICV_TYPE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DICV_TYPE_TYPEID_GENERATOR")
	@Column(name = "TYPE_ID")
	private Integer typeId;

	@Column(name = "SUB_TYPE_ID")
	private Integer subTypeId;

	@Column(name = "TYPE_NAME")
	private String typeName;

	@Column(name = "CREATED_DATE")
	private Timestamp createdOn;

	@Column(name = "MODIFIED_DATE")
	private Timestamp updatedOn;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "CREATED_BY")
	private DicvUser createdByUser;

	@Column(name = "UPDATED_BY")
	private Integer updatedByUser;

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

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public DicvUser getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(DicvUser createdByUser) {
		this.createdByUser = createdByUser;
	}


	public DicvType() {
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getSubTypeId() {
		return this.subTypeId;
	}

	public void setSubTypeId(Integer subTypeId) {
		this.subTypeId = subTypeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getUpdatedByUser() {
		return updatedByUser;
	}

	public void setUpdatedByUser(Integer updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

}