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
 * The persistent class for the DICV_CATEGORY database table.
 *
 */
@Entity
@Table(name = "DICV_CATEGORY")
public class DicvCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DICV_CATEGORY_GENERATOR", sequenceName = "DICV_CATEGORY_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DICV_CATEGORY_GENERATOR")
	@Column(name = "CATEGORY_ID")
	private Integer categoryId;

	@Column(name = "CATEGORY_NAME")
	private String categoryName;

	@Column(name = "SUB_CATEGORY_ID")
	private Integer subCategoryId;

	@Column(name = "CREATED_DATE")
	private Timestamp createdOn;

	@Column(name = "MODIFIED_DATE")
	private Timestamp updatedOn;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

	public DicvCategory() {
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getSubCategoryId() {
		return this.subCategoryId;
	}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Integer getUpdatedByUser() {
		return updatedByUser;
	}

	public void setUpdatedByUser(Integer updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

}