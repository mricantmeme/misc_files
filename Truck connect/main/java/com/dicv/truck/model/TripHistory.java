package com.dicv.truck.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the TRIP_HISTORY database table.
 * 
 */
@Entity
@Table(name = "TRIP_HISTORY")
@NamedQuery(name = "TripHistory.findAll", query = "SELECT t FROM TripHistory t")
public class TripHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TRIP_HISTORY_TRIPHISTORYID_GENERATOR", sequenceName = "TRIP_HISTORY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRIP_HISTORY_TRIPHISTORYID_GENERATOR")
	@Column(name = "TRIP_HISTORY_ID")
	private Integer tripHistoryId;

	@Column(name = "ENGINE_SPEED")
	private BigDecimal engineSpeed;

	private Double latitude;

	private Double longitude;

	@Column(name = "UPDATED_DATE_TIME")
	private Timestamp updatedDateTime;

	@Column(name = "VEHICLE_SPEED")
	private BigDecimal vehicleSpeed;

	public TripHistory() {
	}

	public Integer getTripHistoryId() {
		return this.tripHistoryId;
	}

	public void setTripHistoryId(Integer tripHistoryId) {
		this.tripHistoryId = tripHistoryId;
	}

	public BigDecimal getEngineSpeed() {
		return this.engineSpeed;
	}

	public void setEngineSpeed(BigDecimal engineSpeed) {
		this.engineSpeed = engineSpeed;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Timestamp getUpdatedDateTime() {
		return this.updatedDateTime;
	}

	public void setUpdatedDateTime(Timestamp updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public BigDecimal getVehicleSpeed() {
		return this.vehicleSpeed;
	}

	public void setVehicleSpeed(BigDecimal vehicleSpeed) {
		this.vehicleSpeed = vehicleSpeed;
	}
}