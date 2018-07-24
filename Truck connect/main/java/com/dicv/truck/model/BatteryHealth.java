package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BATTERY_HEALTH")
public class BatteryHealth implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5534296632613717284L;

	@Id
	@SequenceGenerator(name = "BATTERY_HEALTH_ID_SEQ", sequenceName = "BATTERY_HEALTH_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BATTERY_HEALTH_ID_SEQ")
	@Column(name = "BATTERY_HEALTH_ID")
	private Long batteryHealthId;

	@ManyToOne
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	@Column(name = "GPS_TIME")
	private Calendar gpsTime;
	
	@Column(name = "BATTERY_LEVEL")
	private Integer batteryLevel;

	@Column(name = "CREATED_AT")
	private Timestamp createdAt;

	@Column(name = "LATITUDE")
	private Double latitude;

	@Column(name = "LONGITUDE")
	private Double longitude;
	
	@Column(name = "EMAIL_SENT")
	private Integer emailSent;

	public Long getBatteryHealthId() {
		return batteryHealthId;
	}

	public void setBatteryHealthId(Long batteryHealthId) {
		this.batteryHealthId = batteryHealthId;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Calendar getGpsTime() {
		return gpsTime;
	}

	public void setGpsTime(Calendar gpsTime) {
		this.gpsTime = gpsTime;
	}

	public Integer getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(Integer batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getEmailSent() {
		return emailSent;
	}

	public void setEmailSent(Integer emailSent) {
		this.emailSent = emailSent;
	}



}
