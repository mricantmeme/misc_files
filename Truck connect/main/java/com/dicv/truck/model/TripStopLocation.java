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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TRIP_STOP_LOCATIONS")
public class TripStopLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TRIP_TRIPSTOPID_GENERATOR", sequenceName = "TRIP_STOP_LOCATIONS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRIP_TRIPSTOPID_GENERATOR")
	@Column(name = "STOP_LOC_ID")
	private Long tripStopLocationId;

	@ManyToOne
	@JoinColumn(name = "TRIP_ID")
	private Trip trip;

	@Column(name = "LATTITUDE")
	private Double stopLatitude;

	@Column(name = "LONGITUDE")
	private Double stopLongitude;

	@Column(name = "STOP_DURATION")
	private Long duration;

	@Column(name = "STOP_TIME")
	private Timestamp stopTime;

	@Column(name = "START_TIME")
	private Timestamp startTime;

	@Column(name = "IS_STOPPED")
	private Integer isStopped;

	public Long getTripStopLocationId() {
		return tripStopLocationId;
	}

	public void setTripStopLocationId(Long tripStopLocationId) {
		this.tripStopLocationId = tripStopLocationId;
	}


	public Double getStopLatitude() {
		return stopLatitude;
	}

	public void setStopLatitude(Double stopLatitude) {
		this.stopLatitude = stopLatitude;
	}

	public Double getStopLongitude() {
		return stopLongitude;
	}

	public void setStopLongitude(Double stopLongitude) {
		this.stopLongitude = stopLongitude;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Timestamp getStopTime() {
		return stopTime;
	}

	public void setStopTime(Timestamp stopTime) {
		this.stopTime = stopTime;
	}

	public Integer getIsStopped() {
		return isStopped;
	}

	public void setIsStopped(Integer isStopped) {
		this.isStopped = isStopped;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
}
