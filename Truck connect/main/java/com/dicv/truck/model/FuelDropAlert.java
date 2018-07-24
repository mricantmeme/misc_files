package com.dicv.truck.model;

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
@Table(name = "FUEL_DROP")
public class FuelDropAlert {
	
	@Id
	@SequenceGenerator(name = "FUEL_DROP_ID_GENERATOR", sequenceName = "FUEL_DROP_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FUEL_DROP_ID_GENERATOR")
	@Column(name = "FUEL_DROP_ID")
	private Long fuelDropId;

	@ManyToOne
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	@Column(name = "FUEL_DROP_TIME")
	private Calendar fuelDropTime;
	
	@Column(name = "TO_FUEL_DROP_TIME")
	private Calendar fuelDropToTime;

	@Column(name = "CREATED_AT")
	private Timestamp createdAt;

	@Column(name = "FROM_LEVEL")
	private Integer fromLevel;
	
	@Column(name = "TO_LEVEL")
	private Integer toLevel;
	
	@Column(name = "LATITUDE")
	private Double latitude;

	@Column(name = "LONGITUDE")
	private Double longitude;
	
	@Column(name = "TO_LATITUDE")
	private Double toLatitude;

	@Column(name = "TO_LONGITUDE")
	private Double toLongitude;
	
	@Column(name = "EMAIL_SENT")
	private Integer emailSent;
	

	public Long getFuelDropId() {
		return fuelDropId;
	}

	public void setFuelDropId(Long fuelDropId) {
		this.fuelDropId = fuelDropId;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Calendar getFuelDropTime() {
		return fuelDropTime;
	}

	public void setFuelDropTime(Calendar fuelDropTime) {
		this.fuelDropTime = fuelDropTime;
	}

	public Calendar getFuelDropToTime() {
		return fuelDropToTime;
	}

	public void setFuelDropToTime(Calendar fuelDropToTime) {
		this.fuelDropToTime = fuelDropToTime;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getFromLevel() {
		return fromLevel;
	}

	public void setFromLevel(Integer fromLevel) {
		this.fromLevel = fromLevel;
	}

	public Integer getToLevel() {
		return toLevel;
	}

	public void setToLevel(Integer toLevel) {
		this.toLevel = toLevel;
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

	public Double getToLatitude() {
		return toLatitude;
	}

	public void setToLatitude(Double toLatitude) {
		this.toLatitude = toLatitude;
	}

	public Double getToLongitude() {
		return toLongitude;
	}

	public void setToLongitude(Double toLongitude) {
		this.toLongitude = toLongitude;
	}

	public Integer getEmailSent() {
		return emailSent;
	}

	public void setEmailSent(Integer emailSent) {
		this.emailSent = emailSent;
	}

	
	

}
