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
 * The persistent class for the DICV_GROUPS database table.
 * 
 */
@Entity
@Table(name = "DICV_GROUPS")
public class DicvGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DICV_GROUPS_GROUPID_GENERATOR", sequenceName = "DICV_GROUPS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DICV_GROUPS_GROUPID_GENERATOR")
	@Column(name = "GROUP_ID")
	private Integer groupId;

	@Column(name = "GROUP_NAME")
	private String groupName;

	@Column(name = "SUB_GROUP_ID")
	private Integer subGroupId;

	@Column(name = "CREATED_DATE")
	private Timestamp createdOn;

	@Column(name = "MODIFIED_DATE")
	private Timestamp updatedOn;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY")
	private DicvUser createdByUser;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_BY")
	private DicvUser updatedByUser;

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

	public DicvUser getUpdatedByUser() {
		return updatedByUser;
	}

	public void setUpdatedByUser(DicvUser updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

	public DicvGroup() {
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getSubGroupId() {
		return this.subGroupId;
	}

	public void setSubGroupId(Integer subGroupId) {
		this.subGroupId = subGroupId;
	}

	@Override
	public String toString() {
		return "DicvGroup [groupId=" + groupId + ", groupName=" + groupName + ", subGroupId=" + subGroupId
				+ ", isDeleted=" + isDeleted + ", createdByUser=" + createdByUser + "]";
	}

}