package com.dicv.truck.dto;

public class GroupDto {

	public GroupDto(Integer groupId, String groupName) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
	}

	public GroupDto(Integer groupId, String groupName, Integer subGroupId, Boolean isDelete, Integer userId) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.subGroupId = subGroupId;
		this.isDelete = isDelete;
		this.userId = userId;
	}

	private Integer groupId;

	private String groupName;

	private Integer subGroupId;

	private Boolean isDelete;

	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public GroupDto() {

	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getSubGroupId() {
		return subGroupId;
	}

	public void setSubGroupId(Integer subGroupId) {
		this.subGroupId = subGroupId;
	}

	@Override
	public String toString() {
		return "Groups [groupId=" + groupId + ", groupName=" + groupName + ", subGroupId=" + subGroupId + "]";
	}
}
