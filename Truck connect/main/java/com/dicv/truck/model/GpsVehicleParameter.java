package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the GPS_VEHICLE_PARAMETERS database table.
 * 
 */
@Entity
@Table(name = "GPS_VEHICLE_PARAMETERS")
public class GpsVehicleParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GPS_VEH_PARAM_SEQ")
	@SequenceGenerator(name = "GPS_VEH_PARAM_SEQ", sequenceName = "GPS_VEH_PARAM_SEQ")
	@Column(name = "GPS_VEH_PARAM_ID")
	private Long gpsVehicleParamId;

	@Column(name = "CAN_COOLANT_TEMP")
	private Float canCoolantTemp;

	@Column(name = "CAN_ENGINE_SPEED")
	private Float canEngineSpeed;

	@Column(name = "CAN_FUEL_CONTENT_ABS")
	private Float canFuelContentAbs;

	@Column(name = "CAN_OUTSIDE_AIRTEMP")
	private Float canOutSideTemp;

	@Column(name = "CAN_VEHICLE_SPEED")
	private Float canVehicleSpeed;

	@Column(name = "CREATED_ON")
	private Timestamp createdOn;

	@Column(name = "GPS_ALTITUDE")
	private Double gpsAltitude;

	@Column(name = "GPS_COG")
	private Float gpsCog;

	@Column(name = "GPS_DATE")
	private Date gpsDate;

	@Column(name = "GPS_DATE_ISO")
	private Date gpsDateISO;

	@Column(name = "GPS_HDOP")
	private Integer gpsHdop;

	@Column(name = "GPS_IGNITION")
	private Integer gpsIgnition;

	@Column(name = "GPS_IMEI")
	private Long gpsImei;

	@Column(name = "GPS_KEY")
	private String gpsKey;

	@Column(name = "GPS_LATITUDE")
	private Double gpsLatitude;

	@Column(name = "GPS_LONGITUDE")
	private Double gpsLongitude;

	@Column(name = "GPS_NSAT")
	private Integer gpsNsat;

	@Column(name = "GPS_PROCESS_STATUS")
	private Integer gpsProcessStatus;

	@Column(name = "GPS_SPKM")
	private Integer gpsSpkm;

	@Column(name = "GPS_TIME")
	private Timestamp gpsTime;

	@Column(name = "GPS_TIME_ISO")
	private Timestamp gpsTimeISO;

	@Column(name = "GPS_UPTIME")
	private Integer gpsUptime;

	@Column(name = "GPS_VOLT")
	private Double gpsVolt;

	@Column(name = "LAST_UPDATED_ON")
	private Timestamp lastUpdatedOn;

	@Column(name = "VEHICLE_VARIANT")
	private String vehiclevariant;

	@Column(name = "OPENTRACKER_RUNNINGSECONDS")
	private Long opentracker_runningseconds;

	@Column(name = "FIRMWARE_VERSION")
	private String firmware_version;

	@Column(name = "ENGINE_FUEL_RATE")
	private Float engFuelRate;

	@Column(name = "ENGINE_INSTANT_FUEL_ECO")
	private Float engInstFuelEco;

	@Column(name = "ENGINE_EXH_BRH_LAMP")
	private Float engExhBrkLamp;

	@Column(name = "ACT_VAL_FUEL_CONSUMPTION")
	private Float actualValueFuelConsuption;

	@Column(name = "CAN_FUEL_CONTENT_REL")
	private Integer fuelTankLevel;

	@Column(name = "CAN_VEHICLE_UPDATED_ON")
	private Timestamp vehicleLastUpdateON;

	@Column(name = "ENGINE_ON")
	private Integer engineON;

	@Column(name = "DEVICE_BATTERY")
	private Double deviceBattery;

	@Column(name = "BATTERY_HEALTH")
	private Integer batteryHealth;

	@Column(name = "SIGNAL_STRENGTH")
	private Integer signalStrength;
	
	

	public Float getCanCoolantTemp() {
		return canCoolantTemp;
	}

	public void setCanCoolantTemp(Float canCoolantTemp) {
		this.canCoolantTemp = canCoolantTemp;
	}

	public Float getCanEngineSpeed() {
		return canEngineSpeed;
	}

	public void setCanEngineSpeed(Float canEngineSpeed) {
		this.canEngineSpeed = canEngineSpeed;
	}

	public Float getCanFuelContentAbs() {
		return canFuelContentAbs;
	}

	public void setCanFuelContentAbs(Float canFuelContentAbs) {
		this.canFuelContentAbs = canFuelContentAbs;
	}

	public Float getCanOutSideTemp() {
		return canOutSideTemp;
	}

	public void setCanOutSideTemp(Float canOutSideTemp) {
		this.canOutSideTemp = canOutSideTemp;
	}

	public Float getCanVehicleSpeed() {
		return canVehicleSpeed;
	}

	public void setCanVehicleSpeed(Float canVehicleSpeed) {
		this.canVehicleSpeed = canVehicleSpeed;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Double getGpsAltitude() {
		return gpsAltitude;
	}

	public void setGpsAltitude(Double gpsAltitude) {
		this.gpsAltitude = gpsAltitude;
	}

	public Float getGpsCog() {
		return gpsCog;
	}

	public void setGpsCog(Float gpsCog) {
		this.gpsCog = gpsCog;
	}

	public Date getGpsDate() {
		return gpsDate;
	}

	public void setGpsDate(Date gpsDate) {
		this.gpsDate = gpsDate;
	}

	public Date getGpsDateISO() {
		return gpsDateISO;
	}

	public void setGpsDateISO(Date gpsDateISO) {
		this.gpsDateISO = gpsDateISO;
	}

	public Integer getGpsHdop() {
		return gpsHdop;
	}

	public void setGpsHdop(Integer gpsHdop) {
		this.gpsHdop = gpsHdop;
	}

	public Integer getGpsIgnition() {
		return gpsIgnition;
	}

	public void setGpsIgnition(Integer gpsIgnition) {
		this.gpsIgnition = gpsIgnition;
	}

	public Long getGpsImei() {
		return gpsImei;
	}

	public void setGpsImei(Long gpsImei) {
		this.gpsImei = gpsImei;
	}

	public String getGpsKey() {
		return gpsKey;
	}

	public void setGpsKey(String gpsKey) {
		this.gpsKey = gpsKey;
	}

	public Double getGpsLatitude() {
		return gpsLatitude;
	}

	public void setGpsLatitude(Double gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	public Double getGpsLongitude() {
		return gpsLongitude;
	}

	public void setGpsLongitude(Double gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	public Integer getGpsNsat() {
		return gpsNsat;
	}

	public void setGpsNsat(Integer gpsNsat) {
		this.gpsNsat = gpsNsat;
	}

	public Integer getGpsProcessStatus() {
		return gpsProcessStatus;
	}

	public void setGpsProcessStatus(Integer gpsProcessStatus) {
		this.gpsProcessStatus = gpsProcessStatus;
	}

	public Integer getGpsSpkm() {
		return gpsSpkm;
	}

	public void setGpsSpkm(Integer gpsSpkm) {
		this.gpsSpkm = gpsSpkm;
	}

	public Timestamp getGpsTime() {
		return gpsTime;
	}

	public void setGpsTime(Timestamp gpsTime) {
		this.gpsTime = gpsTime;
	}

	public Timestamp getGpsTimeISO() {
		return gpsTimeISO;
	}

	public void setGpsTimeISO(Timestamp gpsTimeISO) {
		this.gpsTimeISO = gpsTimeISO;
	}

	public Integer getGpsUptime() {
		return gpsUptime;
	}

	public void setGpsUptime(Integer gpsUptime) {
		this.gpsUptime = gpsUptime;
	}

	public Double getGpsVolt() {
		return gpsVolt;
	}

	public void setGpsVolt(Double gpsVolt) {
		this.gpsVolt = gpsVolt;
	}

	public Timestamp getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Timestamp lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public String getVehiclevariant() {
		return vehiclevariant;
	}

	public void setVehiclevariant(String vehiclevariant) {
		this.vehiclevariant = vehiclevariant;
	}

	public Long getOpentracker_runningseconds() {
		return opentracker_runningseconds;
	}

	public void setOpentracker_runningseconds(Long opentracker_runningseconds) {
		this.opentracker_runningseconds = opentracker_runningseconds;
	}

	public String getFirmware_version() {
		return firmware_version;
	}

	public void setFirmware_version(String firmware_version) {
		this.firmware_version = firmware_version;
	}

	public Float getEngFuelRate() {
		return engFuelRate;
	}

	public void setEngFuelRate(Float engFuelRate) {
		this.engFuelRate = engFuelRate;
	}

	public Float getEngInstFuelEco() {
		return engInstFuelEco;
	}

	public void setEngInstFuelEco(Float engInstFuelEco) {
		this.engInstFuelEco = engInstFuelEco;
	}

	public Float getEngExhBrkLamp() {
		return engExhBrkLamp;
	}

	public void setEngExhBrkLamp(Float engExhBrkLamp) {
		this.engExhBrkLamp = engExhBrkLamp;
	}

	public Float getActualValueFuelConsuption() {
		return actualValueFuelConsuption;
	}

	public void setActualValueFuelConsuption(Float actualValueFuelConsuption) {
		this.actualValueFuelConsuption = actualValueFuelConsuption;
	}

	public Integer getFuelTankLevel() {
		return fuelTankLevel;
	}

	public void setFuelTankLevel(Integer fuelTankLevel) {
		this.fuelTankLevel = fuelTankLevel;
	}

	public Timestamp getVehicleLastUpdateON() {
		return vehicleLastUpdateON;
	}

	public void setVehicleLastUpdateON(Timestamp vehicleLastUpdateON) {
		this.vehicleLastUpdateON = vehicleLastUpdateON;
	}

	public Integer getEngineON() {
		return engineON;
	}

	public void setEngineON(Integer engineON) {
		this.engineON = engineON;
	}

	public Integer getSignalStrength() {
		return signalStrength;
	}

	public void setSignalStrength(Integer signalStrength) {
		this.signalStrength = signalStrength;
	}

	public Integer getBatteryHealth() {
		return batteryHealth;
	}

	public void setBatteryHealth(Integer batteryHealth) {
		this.batteryHealth = batteryHealth;
	}

	public Long getGpsVehicleParamId() {
		return gpsVehicleParamId;
	}

	public void setGpsVehicleParamId(Long gpsVehicleParamId) {
		this.gpsVehicleParamId = gpsVehicleParamId;
	}

	public Double getDeviceBattery() {
		return deviceBattery;
	}

	public void setDeviceBattery(Double deviceBattery) {
		this.deviceBattery = deviceBattery;
	}

}