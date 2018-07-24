package com.dicv.truck.dto;

import java.util.List;
import java.util.Map;

public class LoginResponseDto {

	public LoginResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginResponseDto(Integer id, String userName, Integer status, List<String> roles,
			Map<String, String> permissions, String userStatus, String token,Integer changePassword) {
		super();
		this.id = id;
		this.userName = userName;
		this.status = status;
		this.roles = roles;
		this.permissions = permissions;
		this.userStatus = userStatus;
		this.token = token;
		this.changePassword = changePassword;
	}

	private Integer id;

	private String userName;

	private Integer status;

	private String token;

	private List<String> roles;

	private Map<String, String> permissions;

	private String userStatus;

	private Integer changePassword;

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Map<String, String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Map<String, String> permissions) {
		this.permissions = permissions;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LoginResponseDto [id=" + id + ", userName=" + userName + ", status=" + status + ", roles=" + roles
				+ ", permissions=" + permissions + ", userStatus=" + userStatus + "]";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(Integer changePassword) {
		this.changePassword = changePassword;
	}

}
