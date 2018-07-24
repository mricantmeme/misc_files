package com.dicv.truck.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class LoginDetails {

	public LoginDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginDetails(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	private String username;

	private String password;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
