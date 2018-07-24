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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ERM_VEHICLE")
public class ErmVehicle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ERM_VEHICLE_ERM_VEHICLE_ID_GENERATOR", sequenceName = "ERM_VEHICLE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ERM_VEHICLE_ERM_VEHICLE_ID_GENERATOR")
	@Column(name = "ERM_VEHICLE_ID")
	private Integer ermVehicleId;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "STATUS")
	private Integer status;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private DicvUser dicvUser;

	@Column(name = "CREATED_TIME")
	private Timestamp createdTime;

	@Column(name = "UPDATED_TIME")
	private Timestamp updatedTime;

	@Column(name = "SUBMITTED_TIME")
	private Timestamp submittedTime;
	
	@Column(name = "LAST_RECEIVED_TIME")
	private Timestamp lastReceivedTime;


	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ermVehicle")
	private GpsImei gpsImei;

	public Integer getErmVehicleId() {
		return ermVehicleId;
	}

	public void setErmVehicleId(Integer ermVehicleId) {
		this.ermVehicleId = ermVehicleId;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Timestamp getSubmittedTime() {
		return submittedTime;
	}

	public void setSubmittedTime(Timestamp submittedTime) {
		this.submittedTime = submittedTime;
	}

	public DicvUser getDicvUser() {
		return dicvUser;
	}

	public void setDicvUser(DicvUser dicvUser) {
		this.dicvUser = dicvUser;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GpsImei getGpsImei() {
		return gpsImei;
	}

	public void setGpsImei(GpsImei gpsImei) {
		this.gpsImei = gpsImei;
	}

	public Timestamp getLastReceivedTime() {
		return lastReceivedTime;
	}

	public void setLastReceivedTime(Timestamp lastReceivedTime) {
		this.lastReceivedTime = lastReceivedTime;
	}
}
