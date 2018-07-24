package com.dicv.truck.token;

import java.io.Serializable;

public class TokenDetails implements Serializable {

	/**
	 * HARI KRISHNAN
	 */
	private static final long serialVersionUID = -3891781107230128322L;
	private String userName;
	private Long userId;
	private String token;
	private String clientIp;
	private long tokenExpiryTime;

	public String getUserName() {
		return userName;
	}

	public String getToken() {
		return token;
	}

	public String getClientIp() {
		return clientIp;
	}

	public long getTokenExpiryTime() {
		return tokenExpiryTime;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public void setTokenExpiryTime(long tokenExpiryTime) {
		this.tokenExpiryTime = tokenExpiryTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
