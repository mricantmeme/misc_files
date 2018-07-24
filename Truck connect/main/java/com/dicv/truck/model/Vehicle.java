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

/**
 * The persistent class for the VEHICLE database table.
 */
@Entity
@Table(name = "VEHICLE")
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "VEHICLE_VEHICLEID_GENERATOR", sequenceName = "VEHICLE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEHICLE_VEHICLEID_GENERATOR")
	@Column(name = "VEHICLE_ID")
	private Integer vehicleId;

	@Column(name = "CURRENT_ENGINE_SPEED")
	private Integer currentEngineSpeed;

	@Column(name = "CURRENT_LAT")
	private Double currentLat;

	@Column(name = "CURRENT_LONG")
	private Double currentLong;

	@Column(name = "CURRENT_VEHICLE_SPEED")
	private Integer currentVehicleSpeed;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "GPS_COG")
	private Float gpsCog;

	@Column(name = "GPS_HDOP")
	private Integer gpsHdop;

	@Column(name = "MAKE")
	private String make;

	@Column(name = "MODEL")
	private String model;

	@Column(name = "VEH_AVG_SPEED")
	private Integer vehicleMaxSpeed;

	@Column(name = "MODIFIED_DATE_TIME")
	private Timestamp modifiedDateTime;

	@Column(name = "PURCHASE_DATE")
	private Timestamp purchaseDate;

	@Column(name = "REGISTRATION_ID")
	private String registrationId;

	@Column(name = "ROUTE_NUMBER")
	private String routeNumber;

	@Column(name = "RUNNING_STATUS")
	private String runningStatus;

	@Column(name = "VARIANT")
	private String variant;

	@Column(name = "VEHICLE_STATUS")
	private String vehicleStatus;

	@Column(name = "VEHICLE_UPDATE_TIME")
	private Timestamp vehicleUpdateTime;

	@Column(name = "VIN")
	private String vin;

	@Column(name = "ENABLED")
	private Integer enabled;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@Column(name = "MAX_PAYLOAD_CAPACITY")
	private Double maxPayloadCapacity;


	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTRY_ID")
	private DicvCountry dicvCountry;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CATEGORY_ID")
	private DicvCategory dicvCategory;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "PHOTO_ID")
	private Photo photo;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "DEFAULT_DRIVER")
	private DicvUser defaultDriver;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "vehicle")
	private GpsImei gpsImei;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "REGION_ID")
	private DicvRegion dicvRegion;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private DicvUser dicvUser;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "COMPANY_ID")
	private DicvCompany dicvCompany;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DEALER_ID")
	private DicvUser dealerUser;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "vehicle")
	private VehicleCanParam vehicleCanParam;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "VEHICLE_TYPE_ID")
	private DicvType dicvType;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "GROUP_ID")
	private DicvGroup dicvGroup;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ROOT_ADMIN_GROUP_ID")
	private DicvGroup rootAdminGroup;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ERM_ID")
	private ErmVehicle ermVehicle;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY")
	private DicvUser createdByUser;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "GCM_ID")
	private Gcm gcm;

	public Vehicle() {
	}

	public Integer getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Integer getCurrentEngineSpeed() {
		return this.currentEngineSpeed;
	}

	public void setCurrentEngineSpeed(Integer currentEngineSpeed) {
		this.currentEngineSpeed = currentEngineSpeed;
	}

	public Double getCurrentLat() {
		return this.currentLat;
	}

	public void setCurrentLat(Double currentLat) {
		this.currentLat = currentLat;
	}

	public Double getCurrentLong() {
		return this.currentLong;
	}

	public void setCurrentLong(Double currentLong) {
		this.currentLong = currentLong;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getGpsCog() {
		return this.gpsCog;
	}

	public void setGpsCog(Float gpsCog) {
		this.gpsCog = gpsCog;
	}

	public Integer getGpsHdop() {
		return this.gpsHdop;
	}

	public void setGpsHdop(Integer gpsHdop) {
		this.gpsHdop = gpsHdop;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Timestamp getModifiedDateTime() {
		return this.modifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public Timestamp getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getRegistrationId() {
		return this.registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getRouteNumber() {
		return this.routeNumber;
	}

	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}

	public String getRunningStatus() {
		return this.runningStatus;
	}

	public void setRunningStatus(String runningStatus) {
		this.runningStatus = runningStatus;
	}

	public String getVariant() {
		return this.variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public String getVehicleStatus() {
		return this.vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public Timestamp getVehicleUpdateTime() {
		return this.vehicleUpdateTime;
	}

	public void setVehicleUpdateTime(Timestamp vehicleUpdateTime) {
		this.vehicleUpdateTime = vehicleUpdateTime;
	}

	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public DicvRegion getDicvRegion() {
		return this.dicvRegion;
	}

	public void setDicvRegion(DicvRegion dicvRegion) {
		this.dicvRegion = dicvRegion;
	}

	public DicvUser getDicvUser() {
		return this.dicvUser;
	}

	public void setDicvUser(DicvUser dicvUser) {
		this.dicvUser = dicvUser;
	}

	public VehicleCanParam getVehicleCanParam() {
		return this.vehicleCanParam;
	}

	public void setVehicleCanParam(VehicleCanParam vehicleCanParam) {
		this.vehicleCanParam = vehicleCanParam;
	}

	public GpsImei getGpsImei() {
		return gpsImei;
	}

	public void setGpsImei(GpsImei gpsImeis) {
		this.gpsImei = gpsImeis;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public DicvType getDicvType() {
		return this.dicvType;
	}

	public void setDicvType(DicvType dicvType) {
		this.dicvType = dicvType;
	}

	public DicvCategory getDicvCategory() {
		return dicvCategory;
	}

	public void setDicvCategory(DicvCategory dicvCategory) {
		this.dicvCategory = dicvCategory;
	}

	public DicvCountry getDicvCountry() {
		return dicvCountry;
	}

	public void setDicvCountry(DicvCountry dicvCountry) {
		this.dicvCountry = dicvCountry;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public DicvGroup getDicvGroup() {
		return dicvGroup;
	}

	public void setDicvGroup(DicvGroup dicvGroup) {
		this.dicvGroup = dicvGroup;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public DicvUser getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(DicvUser createdByUser) {
		this.createdByUser = createdByUser;
	}

	public Double getMaxPayloadCapacity() {
		return maxPayloadCapacity;
	}

	public void setMaxPayloadCapacity(Double maxPayloadCapacity) {
		this.maxPayloadCapacity = maxPayloadCapacity;
	}

	public Integer getCurrentVehicleSpeed() {
		return currentVehicleSpeed;
	}

	public void setCurrentVehicleSpeed(Integer currentVehicleSpeed) {
		this.currentVehicleSpeed = currentVehicleSpeed;
	}

	public Integer getVehicleMaxSpeed() {
		return vehicleMaxSpeed;
	}

	public void setVehicleMaxSpeed(Integer vehicleMaxSpeed) {
		this.vehicleMaxSpeed = vehicleMaxSpeed;
	}

	public DicvGroup getRootAdminGroup() {
		return rootAdminGroup;
	}

	public void setRootAdminGroup(DicvGroup rootAdminGroup) {
		this.rootAdminGroup = rootAdminGroup;
	}

	public Gcm getGcm() {
		return gcm;
	}

	public void setGcm(Gcm gcm) {
		this.gcm = gcm;
	}

	public DicvUser getDealerUser() {
		return dealerUser;
	}

	public void setDealerUser(DicvUser dealerUser) {
		this.dealerUser = dealerUser;
	}

	public ErmVehicle getErmVehicle() {
		return ermVehicle;
	}

	public void setErmVehicle(ErmVehicle ermVehicle) {
		this.ermVehicle = ermVehicle;
	}

	public DicvCompany getDicvCompany() {
		return dicvCompany;
	}

	public void setDicvCompany(DicvCompany dicvCompany) {
		this.dicvCompany = dicvCompany;
	}

	public DicvUser getDefaultDriver() {
		return defaultDriver;
	}

	public void setDefaultDriver(DicvUser defaultDriver) {
		this.defaultDriver = defaultDriver;
	}
}