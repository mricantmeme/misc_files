package com.dicv.truck.dto;

public class UserGroupDto {

	public UserGroupDto(Integer userId, String groupName, Integer groupId) {
		super();
		this.userId = userId;
		this.groupName = groupName;
		this.groupId = groupId;
	}

	private Integer userId;

	private String groupName;

	private Integer groupId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	
}
