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

/**
 * The persistent class for the GEO_FENCE_REPORT database table.
 * 
 */
@Entity
@Table(name = "GEO_FENCE_REPORT")
public class GeoFenceReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "GEO_FENCE_REPORT_GENERATOR", sequenceName = "GEO_FENCE_REPORT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEO_FENCE_REPORT_GENERATOR")
	@Column(name = "GEO_FENCE_REPORT_ID")
	private Long geoFenceReportId;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private DicvUser dicvUser;

	@ManyToOne
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	@ManyToOne
	@JoinColumn(name = "GEO_FENCE_ID")
	private GeoFenceInfo geoFenceInfo;

	@Column(name = "GEO_SYSTIMESTAMP")
	private Timestamp geoSystimestamp;

	@Column(name = "GEO_FENCE_ENTRY_TIME")
	private Timestamp geoFenceEntryTime;

	@Column(name = "GEO_FENCE_EXIT_TIME")
	private Timestamp geoFenceExitTime;

	@Column(name = "TIME_SPENT")
	private Long timeSpent;

	@Column(name = "VEHICLE_ENTRY")
	private Integer vehicleEntry;

	public Long getGeoFenceReportId() {
		return geoFenceReportId;
	}

	public void setGeoFenceReportId(Long geoFenceReportId) {
		this.geoFenceReportId = geoFenceReportId;
	}

	public DicvUser getDicvUser() {
		return dicvUser;
	}

	public void setDicvUser(DicvUser dicvUser) {
		this.dicvUser = dicvUser;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public GeoFenceInfo getGeoFenceInfo() {
		return geoFenceInfo;
	}

	public void setGeoFenceInfo(GeoFenceInfo geoFenceInfo) {
		this.geoFenceInfo = geoFenceInfo;
	}

	public Timestamp getGeoSystimestamp() {
		return geoSystimestamp;
	}

	public void setGeoSystimestamp(Timestamp geoSystimestamp) {
		this.geoSystimestamp = geoSystimestamp;
	}

	public Timestamp getGeoFenceEntryTime() {
		return geoFenceEntryTime;
	}

	public void setGeoFenceEntryTime(Timestamp geoFenceEntryTime) {
		this.geoFenceEntryTime = geoFenceEntryTime;
	}

	public Timestamp getGeoFenceExitTime() {
		return geoFenceExitTime;
	}

	public void setGeoFenceExitTime(Timestamp geoFenceExitTime) {
		this.geoFenceExitTime = geoFenceExitTime;
	}


	public Integer getVehicleEntry() {
		return vehicleEntry;
	}

	public void setVehicleEntry(Integer vehicleEntry) {
		this.vehicleEntry = vehicleEntry;
	}

	public Long getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(Long timeSpent) {
		this.timeSpent = timeSpent;
	}


}