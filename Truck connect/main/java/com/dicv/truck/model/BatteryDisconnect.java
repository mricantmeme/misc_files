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
@Table(name = "BATTERY_DISCONNECT")
public class BatteryDisconnect implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6886890758304042058L;

	@Id
	@SequenceGenerator(name = "BATTERY_DISCONNECT_SEQ", sequenceName = "BATTERY_DISCONNECT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BATTERY_DISCONNECT_SEQ")
	@Column(name = "BATTERY_DISCONNECT_ID")
	private Long batteryHealthId;

	@ManyToOne
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	@Column(name = "GPS_TIME")
	private Calendar gpsTime;

	@Column(name = "CREATED_AT")
	private Timestamp createdAt;

	@Column(name = "LATITUDE")
	private Double latitude;

	@Column(name = "LONGITUDE")
	private Double longitude;
	
	@Column(name = "LOCATION")
	private String location;

	@Column(name = "EMAIL_SENT")
	private Integer emailSent;
	
	@Column(name = "GEO_RESPONSE")
	private Integer geoResponse;

	public Long getBatteryHealthId() {
		return batteryHealthId;
	}

	public void setBatteryHealthId(Long batteryHealthId) {
		this.batteryHealthId = batteryHealthId;
	}



	public Calendar getGpsTime() {
		return gpsTime;
	}

	public void setGpsTime(Calendar gpsTime) {
		this.gpsTime = gpsTime;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getGeoResponse() {
		return geoResponse;
	}

	public void setGeoResponse(Integer geoResponse) {
		this.geoResponse = geoResponse;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
