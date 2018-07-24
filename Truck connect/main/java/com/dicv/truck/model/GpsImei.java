package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;

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

@Entity
@Table(name = "GPS_IMEI")
public class GpsImei implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "GPS_IMEI_GPSIMEIID_GENERATOR", sequenceName = "GPS_IMEI_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GPS_IMEI_GPSIMEIID_GENERATOR")
	@Column(name = "GPS_IMEI_ID")
	private Integer gpsImeiId;

	private String description;

	@Column(name = "GPS_IMEI")
	private Long gpsImei;

	@Column(name = "GPS_PROVIDER")
	private String gpsProvider;

	@Column(name = "GPS_SIM_NUMBER")
	private Long gpsSimNumber;

	@Column(name = "TABLET_IMEI")
	private Long tabletImei;

	@Column(name = "TABLET_PROVIDER")
	private String tabletProvider;

	@Column(name = "TABLET_SIM_NUMBER")
	private Long tabletSimNumber;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "MODIFIED_DATE")
	private Timestamp updatedDate;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@Column(name = "GPS_TRANSMITTED_TIME")
	private Timestamp gpsTransmittedTime;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ERM_VEHICLE_ID")
	private ErmVehicle ermVehicle;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "TABLET_COUNTRY_ID")
	private DicvCountry dicvCountry1;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "GPS_COUNTRY_ID")
	private DicvCountry dicvCountry2;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CREATED_BY")
	private DicvUser dicvUserCreatedBy;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "UPDATED_BY")
	private DicvUser dicvUserUpdatedBy;

	public GpsImei() {
	}

	public Integer getGpsImeiId() {
		return this.gpsImeiId;
	}

	public void setGpsImeiId(Integer gpsImeiId) {
		this.gpsImeiId = gpsImeiId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGpsProvider() {
		return this.gpsProvider;
	}

	public void setGpsProvider(String gpsProvider) {
		this.gpsProvider = gpsProvider;
	}

	public String getTabletProvider() {
		return this.tabletProvider;
	}

	public void setTabletProvider(String tabletProvider) {
		this.tabletProvider = tabletProvider;
	}

	public DicvCountry getDicvCountry1() {
		return this.dicvCountry1;
	}

	public void setDicvCountry1(DicvCountry dicvCountry1) {
		this.dicvCountry1 = dicvCountry1;
	}

	public DicvCountry getDicvCountry2() {
		return this.dicvCountry2;
	}

	public void setDicvCountry2(DicvCountry dicvCountry2) {
		this.dicvCountry2 = dicvCountry2;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public DicvUser getDicvUserCreatedBy() {
		return dicvUserCreatedBy;
	}

	public void setDicvUserCreatedBy(DicvUser dicvUserCreatedBy) {
		this.dicvUserCreatedBy = dicvUserCreatedBy;
	}

	public DicvUser getDicvUserUpdatedBy() {
		return dicvUserUpdatedBy;
	}

	public void setDicvUserUpdatedBy(DicvUser dicvUserUpdatedBy) {
		this.dicvUserUpdatedBy = dicvUserUpdatedBy;
	}

	public Long getGpsImei() {
		return gpsImei;
	}

	public void setGpsImei(Long gpsImei) {
		this.gpsImei = gpsImei;
	}

	public Long getGpsSimNumber() {
		return gpsSimNumber;
	}

	public void setGpsSimNumber(Long gpsSimNumber) {
		this.gpsSimNumber = gpsSimNumber;
	}

	public Long getTabletImei() {
		return tabletImei;
	}

	public void setTabletImei(Long tabletImei) {
		this.tabletImei = tabletImei;
	}

	public Long getTabletSimNumber() {
		return tabletSimNumber;
	}

	public void setTabletSimNumber(Long tabletSimNumber) {
		this.tabletSimNumber = tabletSimNumber;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public ErmVehicle getErmVehicle() {
		return ermVehicle;
	}

	public void setErmVehicle(ErmVehicle ermVehicle) {
		this.ermVehicle = ermVehicle;
	}

	public Timestamp getGpsTransmittedTime() {
		return gpsTransmittedTime;
	}

	public void setGpsTransmittedTime(Timestamp gpsTransmittedTime) {
		this.gpsTransmittedTime = gpsTransmittedTime;
	}

}