package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the DEVICE database table.
 * 
 */
@Entity
@Table(name = "DEVICE")
@NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d")
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DEVICE_DEVICEID_GENERATOR", sequenceName = "DEVICE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEVICE_DEVICEID_GENERATOR")
	@Column(name = "DEVICE_ID")
	private Integer deviceId;

	private String details;

	@Column(name = "DEVICE_NAME")
	private String deviceName;

	@Column(name = "DONGLE_SERIAL_NUMBER")
	private String dongleSerialNumber;

	private String status;

	@Column(name = "IS_DELETED")
	private int isDelete;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;

	// bi-directional many-to-one association to DicvUser for column created by
	@ManyToOne
	@JoinColumn(name = "CREATED_BY")
	private DicvUser dicvUserCreatedBy;

	// bi-directional many-to-one association to DicvUser for column updated by
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private DicvUser dicvUserUpdatedBy;

	// bi-directional many-to-one association to Vehicle
	@ManyToOne
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	public Device() {
	}

	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDongleSerialNumber() {
		return this.dongleSerialNumber;
	}

	public void setDongleSerialNumber(String dongleSerialNumber) {
		this.dongleSerialNumber = dongleSerialNumber;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public DicvUser getDicvUserCreatedBy() {
		return dicvUserCreatedBy;
	}

	public void setDicvUserCreatedBy(DicvUser dicvUserCreatedBy) {
		this.dicvUserCreatedBy = dicvUserCreatedBy;
	}

	public DicvUser getDicvUserUpdatedBy() {
		return dicvUserUpdatedBy;
	}

	public void setDicvUserUpdatedBy(DicvUser dicvUserUpdatedBy) {
		this.dicvUserUpdatedBy = dicvUserUpdatedBy;
	}

}