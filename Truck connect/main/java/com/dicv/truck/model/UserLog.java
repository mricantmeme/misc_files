package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USER_LOG")
public class UserLog implements Serializable {

	public UserLog() {
	}

	public UserLog(Integer userLogId, Integer userId, Integer logVehicleId, Integer logUserId, String logType,
			Timestamp createdTime, Integer logImeiId) {
		super();
		this.userLogId = userLogId;
		this.userId = userId;
		this.logVehicleId = logVehicleId;
		this.logUserId = logUserId;
		this.logType = logType;
		this.createdTime = createdTime;
		this.logImeiId = logImeiId;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USER_LOG_SEQ", sequenceName = "USER_LOG_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_LOG_SEQ")
	@Column(name = "USER_LOG_ID")
	private Integer userLogId;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "LOG_VEHICLE_ID")
	private Integer logVehicleId;

	@Column(name = "LOG_USER_ID")
	private Integer logUserId;
	
	@Column(name = "ERM_VEHICLE_ID")
	private Integer ermVehicleId;
	
	@Column(name = "LOG_TYPE")
	private String logType;

	@Column(name = "CREATED_TIME")
	private Timestamp createdTime;

	@Column(name = "LOG_IMEI_ID")
	private Integer logImeiId;

	public Integer getUserLogId() {
		return userLogId;
	}

	public void setUserLogId(Integer userLogId) {
		this.userLogId = userLogId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLogVehicleId() {
		return logVehicleId;
	}

	public void setLogVehicleId(Integer logVehicleId) {
		this.logVehicleId = logVehicleId;
	}

	public Integer getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(Integer logUserId) {
		this.logUserId = logUserId;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getLogImeiId() {
		return logImeiId;
	}

	public void setLogImeiId(Integer logImeiId) {
		this.logImeiId = logImeiId;
	}

	public Integer getErmVehicleId() {
		return ermVehicleId;
	}

	public void setErmVehicleId(Integer ermVehicleId) {
		this.ermVehicleId = ermVehicleId;
	}

}
