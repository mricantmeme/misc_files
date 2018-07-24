package com.dicv.truck.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DICV_USER_TYPE")
public class UserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_TYPE_ID")
	private Integer userTypeId;

	@Column(name = "USER_TYPE")
	private String userType;

	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	

}

