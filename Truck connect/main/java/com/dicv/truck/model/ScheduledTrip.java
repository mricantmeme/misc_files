package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the SCHEDULED_TRIP database table.
 * 
 */
@Entity
@Table(name = "SCHEDULED_TRIP")
public class ScheduledTrip implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Unique key
	 */
	@Id
	@SequenceGenerator(name = "SCHEDULED_TRIP_SCHEDULEDTRIPID_GENERATOR", sequenceName = "SCHEDULEDTRIP_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULED_TRIP_SCHEDULEDTRIPID_GENERATOR")
	@Column(name = "SCHEDULED_TRIP_ID")
	private Integer scheduledTripId;

	@Temporal(TemporalType.DATE)
	@Column(name = "FROM_DATE")
	private Date fromDate;

	private String isemail;

	private String issms;

	@Column(name = "MODIFIED_DATE_TIME")
	private Timestamp modifiedDateTime;

	@Temporal(TemporalType.DATE)
	@Column(name = "TO_DATE")
	private Date toDate;

	@Column(name = "TRIP_DATE_TIME")
	private Timestamp tripDateTime;

	@Column(name = "TRIP_STATUS")
	private String tripStatus;

	@Column(name = "SCHEDULED_TRIP_FLAG")
	private Integer scheduledTripFlag = -1;

	@Column(name = "IS_DELETED")
	private Integer isDeleted;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private DicvUser dicvUser;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "scheduledTrip")
	private Trip trip;

	@Column(name = "UPDATED_BY")
	private Integer dicvUserUpdatedBy;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAD_CATEGORY_ID")
	private LoadCategory loadCategory;

	@Column(name = "VOLUME")
	private Integer volume;

	@Column(name = "LOAD_WEIGHT")
	private Integer loadWeight;

	@Column(name = "REVENUE")
	private Integer revenue;

	@Column(name = "DRIVER_COST")
	private Double driverCost;

	@Column(name = "OPERATIONAL_COST")
	private Double operationalCost;

	@Column(name = "FUEL_COST")
	private Double fuelCost;

	@Column(name = "DISTANCE")
	private Double distance;

	@Column(name = "DURATION")
	private Integer duration;
	
	@Column(name = "FROM_ADDRESS")
	private String fromAddress;

	@Column(name = "TO_ADDRESS")
	private String toAddress;

	@Column(name = "STOP_LOCATION_LAT")
	private Double stopLocationLat;

	@Column(name = "STOP_LOCATION_LONG")
	private Double stopLocationLong;

	@Column(name = "START_LOCATION_LAT")
	private Double startLocationLat;

	@Column(name = "START_LOCATION_LONG")
	private Double startLocationLong;

	public ScheduledTrip() {
	}

	public Integer getScheduledTripId() {
		return scheduledTripId;
	}

	public void setScheduledTripId(Integer scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getIsemail() {
		return isemail;
	}

	public void setIsemail(String isemail) {
		this.isemail = isemail;
	}

	public String getIssms() {
		return issms;
	}

	public void setIssms(String issms) {
		this.issms = issms;
	}

	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Timestamp getTripDateTime() {
		return tripDateTime;
	}

	public void setTripDateTime(Timestamp tripDateTime) {
		this.tripDateTime = tripDateTime;
	}

	public String getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(String tripStatus) {
		this.tripStatus = tripStatus;
	}

	public Integer getScheduledTripFlag() {
		return scheduledTripFlag;
	}

	public void setScheduledTripFlag(Integer scheduledTripFlag) {
		this.scheduledTripFlag = scheduledTripFlag;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public DicvUser getDicvUser() {
		return dicvUser;
	}

	public void setDicvUser(DicvUser dicvUser) {
		this.dicvUser = dicvUser;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Integer getDicvUserUpdatedBy() {
		return dicvUserUpdatedBy;
	}

	public void setDicvUserUpdatedBy(Integer dicvUserUpdatedBy) {
		this.dicvUserUpdatedBy = dicvUserUpdatedBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public LoadCategory getLoadCategory() {
		return loadCategory;
	}

	public void setLoadCategory(LoadCategory loadCategory) {
		this.loadCategory = loadCategory;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public Integer getLoadWeight() {
		return loadWeight;
	}

	public void setLoadWeight(Integer loadWeight) {
		this.loadWeight = loadWeight;
	}

	public Integer getRevenue() {
		return revenue;
	}

	public void setRevenue(Integer revenue) {
		this.revenue = revenue;
	}

	public Double getDriverCost() {
		return driverCost;
	}

	public void setDriverCost(Double driverCost) {
		this.driverCost = driverCost;
	}

	public Double getOperationalCost() {
		return operationalCost;
	}

	public void setOperationalCost(Double operationalCost) {
		this.operationalCost = operationalCost;
	}

	public Double getFuelCost() {
		return fuelCost;
	}

	public void setFuelCost(Double fuelCost) {
		this.fuelCost = fuelCost;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public Double getStopLocationLat() {
		return stopLocationLat;
	}

	public void setStopLocationLat(Double stopLocationLat) {
		this.stopLocationLat = stopLocationLat;
	}

	public Double getStopLocationLong() {
		return stopLocationLong;
	}

	public void setStopLocationLong(Double stopLocationLong) {
		this.stopLocationLong = stopLocationLong;
	}

	public Double getStartLocationLat() {
		return startLocationLat;
	}

	public void setStartLocationLat(Double startLocationLat) {
		this.startLocationLat = startLocationLat;
	}

	public Double getStartLocationLong() {
		return startLocationLong;
	}

	public void setStartLocationLong(Double startLocationLong) {
		this.startLocationLong = startLocationLong;
	}

}