package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

/**
 * The persistent class for the LOAD_CATEGORY database table.
 *
 */
@Entity
@Table(name = "LOAD_CATEGORY")
public class LoadCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "LOAD_CATEGORY_ID_SEQ", sequenceName = "LOAD_CATEGORY_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOAD_CATEGORY_ID_SEQ")
	@Column(name = "LOAD_CATEGORY_ID")
	private Integer loadCategoryId;

	@Column(name = "LOAD_CATEGORY_NAME")
	private String loadCategoryName;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "UPDATED_DATE")
	private Timestamp updatedDate;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY")
	private DicvUser dicvUserCreatedBy;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_BY")
	private DicvUser dicvUserUpdatedBy;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "loadCategory")
	@Where(clause = "IS_DELETED=0")
	private List<ScheduledTrip> scheduledTrip;

	public LoadCategory() {
	}

	public Integer getLoadCategoryId() {
		return this.loadCategoryId;
	}

	public void setLoadCategoryId(Integer loadCategoryId) {
		this.loadCategoryId = loadCategoryId;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getLoadCategoryName() {
		return this.loadCategoryName;
	}

	public void setLoadCategoryName(String loadCategoryName) {
		this.loadCategoryName = loadCategoryName;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the isDeleted
	 */
	public int getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the dicvUserCreatedBy
	 */
	public DicvUser getDicvUserCreatedBy() {
		return dicvUserCreatedBy;
	}

	/**
	 * @param dicvUserCreatedBy
	 *            the dicvUserCreatedBy to set
	 */
	public void setDicvUserCreatedBy(DicvUser dicvUserCreatedBy) {
		this.dicvUserCreatedBy = dicvUserCreatedBy;
	}

	/**
	 * @return the dicvUserUpdatedBy
	 */
	public DicvUser getDicvUserUpdatedBy() {
		return dicvUserUpdatedBy;
	}

	/**
	 * @param dicvUserUpdatedBy
	 *            the dicvUserUpdatedBy to set
	 */
	public void setDicvUserUpdatedBy(DicvUser dicvUserUpdatedBy) {
		this.dicvUserUpdatedBy = dicvUserUpdatedBy;
	}

	/**
	 * @return the scheduledTrip
	 */
	public List<ScheduledTrip> getScheduledTrip() {
		return scheduledTrip;
	}

	/**
	 * @param scheduledTrip
	 *            the scheduledTrip to set
	 */
	public void setScheduledTrip(List<ScheduledTrip> scheduledTrip) {
		this.scheduledTrip = scheduledTrip;
	}

}