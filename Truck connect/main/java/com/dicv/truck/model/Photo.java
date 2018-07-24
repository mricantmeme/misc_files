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
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the PHOTOS database table.
 * 
 */
@Entity
@Table(name = "PHOTOS")
public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PHOTOS_PHOTOID_GENERATOR", sequenceName = "PHOTOS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHOTOS_PHOTOID_GENERATOR")
	@Column(name = "PHOTO_ID")
	private Integer photoId;

	@Column(name = "CREATED_BY")
	private Integer createdBy;

	@Lob
	private byte[] image;

	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@Column(name = "UPDATED_BY")
	private Integer updatedBy;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	// bi-directional many-to-one association to DicvUser
	@OneToOne(mappedBy = "photo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private DicvUser dicvUsers;

	// bi-directional many-to-one association to DicvUser
	@OneToOne(mappedBy = "photo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Vehicle vehicle;

	public Photo() {
	}

	public Integer getPhotoId() {
		return this.photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public DicvUser getDicvUsers() {
		return this.dicvUsers;
	}

	public void setDicvUsers(DicvUser dicvUsers) {
		this.dicvUsers = dicvUsers;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

}