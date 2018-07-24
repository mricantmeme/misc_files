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

/**
 * The persistent class for the DEFAULT_DRIVER database table.
 * 
 */
@Entity
@Table(name = "DEFAULT_DRIVER")
public class DefaultDriver implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DEFAULT_DRIVER_DEFAULTDRIVERID_GENERATOR", sequenceName = "DEFAULT_DRIVER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEFAULT_DRIVER_DEFAULTDRIVERID_GENERATOR")
	@Column(name = "DEFAULT_DRIVER_ID")
	private Long defaultDriverId;

	@Column(name = "DEFAULT_DRIVER")
	private Integer driverId;

	@Column(name = "MODIFIED_DATE_TIME")
	private Timestamp modifiedDateTime;

	@Column(name = "IS_DELETED")
	private Integer isDeleted;

	@Column(name = "VEHICLE_ID")
	private Integer vehicleId;

	@Column(name = "CREATED_BY")
	private Integer createdBy;

	public DefaultDriver() {
	}



	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}


	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}




}