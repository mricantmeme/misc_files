package com.dicv.truck.model;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE_IDLE")
public class VehicleIdleAlert {

	@Id
	@SequenceGenerator(name = "VEHICLE_IDLE_ID_GENERATOR", sequenceName = "VEHICLE_IDLE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEHICLE_IDLE_ID_GENERATOR")
	@Column(name = "VEHICLE_IDLE_ID")
	private Long vehicleIdleId;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	@Column(name = "IDLE_START_TIME")
	private Calendar idleStartTime;

	@Column(name = "IDLE_STOP_TIME")
	private Calendar idleStopTime;

	@Column(name = "CREATED_AT")
	private Timestamp createdAt;

	@Column(name = "TIME_SPENT")
	private Long timeSpent;

	public Long getVehicleIdleId() {
		return vehicleIdleId;
	}

	public void setVehicleIdleId(Long vehicleIdleId) {
		this.vehicleIdleId = vehicleIdleId;
	}

	public Long getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(Long timeSpent) {
		this.timeSpent = timeSpent;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Calendar getIdleStartTime() {
		return idleStartTime;
	}

	public void setIdleStartTime(Calendar idleStartTime) {
		this.idleStartTime = idleStartTime;
	}

	public Calendar getIdleStopTime() {
		return idleStopTime;
	}

	public void setIdleStopTime(Calendar idleStopTime) {
		this.idleStopTime = idleStopTime;
	}

}
