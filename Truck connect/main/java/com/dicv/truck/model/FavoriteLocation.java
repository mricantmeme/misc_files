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
 * The persistent class for the FAVORITE_LOCATION database table.
 * 
 */
@Entity
@Table(name = "FAVORITE_LOCATION")
public class FavoriteLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "FAVORITE_LOCATION_FAVORITEID_GENERATOR", sequenceName = "FAVORITE_LOCATION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FAVORITE_LOCATION_FAVORITEID_GENERATOR")
	@Column(name = "FAVORITE_ID")
	private Integer favoriteId;

	private String address;

	@Column(name = "CREATED_ON")
	private Timestamp createdOn;

	@Column(name = "FAVORITE_TAG_NAME")
	private String favoriteTagName;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDateTime;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private DicvUser dicvUser;

	@Column(name = "UPDATED_BY")
	private Integer dicvUserUpdatedBy;

	public FavoriteLocation() {
	}

	public Integer getFavoriteId() {
		return this.favoriteId;
	}

	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getFavoriteTagName() {
		return this.favoriteTagName;
	}

	public void setFavoriteTagName(String favoriteTagName) {
		this.favoriteTagName = favoriteTagName;
	}

	public DicvUser getDicvUser() {
		return this.dicvUser;
	}

	public void setDicvUser(DicvUser dicvUser) {
		this.dicvUser = dicvUser;
	}

	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}


	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getDicvUserUpdatedBy() {
		return dicvUserUpdatedBy;
	}

	public void setDicvUserUpdatedBy(Integer dicvUserUpdatedBy) {
		this.dicvUserUpdatedBy = dicvUserUpdatedBy;
	}

}