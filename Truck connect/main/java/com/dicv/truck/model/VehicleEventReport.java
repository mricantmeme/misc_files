package com.dicv.truck.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE_GPS_EVENT_REPORT")
public class VehicleEventReport {

	@Id
	@SequenceGenerator(name = "VEHICLE_GPS_EVENT_ID_GENERATOR", sequenceName = "VEHICLE_GPS_EVENT_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEHICLE_GPS_EVENT_ID_GENERATOR")
	@Column(name = "VEHICLE_GPS_EVENT_ID")
	private Long vehicleGpsEventId;

	@Column(name = "COUNT")
	private Integer gpsCount;

	@Column(name = "VEHICLE_ID")
	private Integer vehicleId;

	@Column(name = "UPDATED_TIME")
	private Timestamp modifiedDateTime;

	@Column(name = "GPS_DATE")
	private Date gpsDate;

	public Long getVehicleGpsEventId() {
		return vehicleGpsEventId;
	}

	public void setVehicleGpsEventId(Long vehicleGpsEventId) {
		this.vehicleGpsEventId = vehicleGpsEventId;
	}

	public Integer getGpsCount() {
		return gpsCount;
	}

	public void setGpsCount(Integer gpsCount) {
		this.gpsCount = gpsCount;
	}

	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public Date getGpsDate() {
		return gpsDate;
	}

	public void setGpsDate(Date gpsDate) {
		this.gpsDate = gpsDate;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}



}
