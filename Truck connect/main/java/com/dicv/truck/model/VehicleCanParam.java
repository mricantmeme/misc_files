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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE_CAN_PARAM")
@NamedQuery(name = "VehicleCanParam.findAll", query = "SELECT v FROM VehicleCanParam v")
public class VehicleCanParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "VEH_CAN_PARAM_ID_GENERATOR", sequenceName = "VEH_CAN_PARAM_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEH_CAN_PARAM_ID_GENERATOR")
	@Column(name = "VEH_CAN_PARAM_ID")
	private Integer vehicleCanParamId;

	@Column(name = "VEHICLE_ENGINE_RPM")
	private Long vehicleEngineRpm;

	@Column(name = "VEHICLE_ENGINE_COOLANT_TEMP")
	private Double vehicleEngineCoolantTemp;

	@Column(name = "VEHICLE_SPEED_CAN")
	private Long vehicleSpeedCan;

	@Column(name = "FUEL_TANK_RELATIVE_VALUE")
	private Integer fuelTankLevel;

	@Column(name = "BATTERY_HEALTH")
	private Integer batteryHealth;

	@Column(name = "UPDATED_DATE_TIME")
	private Timestamp updatedDateTime;

	@Column(name = "RECEIVED_DATE_TIME")
	private Timestamp receivedDateTime;

	@Column(name = "LAST_ENGINE_ON")
	private Timestamp lastEngineOnTime;

	@Column(name = "CAN_VEHICLE_UPDATED_ON")
	private Timestamp vehicleLastUpdateON;

	@Column(name = "ADBLUE_LEVEL")
	private Double adblueLevel;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	public VehicleCanParam() {
	}

	public Integer getVehicleCanParamId() {
		return vehicleCanParamId;
	}

	public void setVehicleCanParamId(Integer vehicleCanParamId) {
		this.vehicleCanParamId = vehicleCanParamId;
	}

	public Long getVehicleEngineRpm() {
		return vehicleEngineRpm;
	}

	public void setVehicleEngineRpm(Long vehicleEngineRpm) {
		this.vehicleEngineRpm = vehicleEngineRpm;
	}

	public Double getVehicleEngineCoolantTemp() {
		return vehicleEngineCoolantTemp;
	}

	public void setVehicleEngineCoolantTemp(Double vehicleEngineCoolantTemp) {
		this.vehicleEngineCoolantTemp = vehicleEngineCoolantTemp;
	}

	public Long getVehicleSpeedCan() {
		return vehicleSpeedCan;
	}

	public void setVehicleSpeedCan(Long vehicleSpeedCan) {
		this.vehicleSpeedCan = vehicleSpeedCan;
	}

	public Integer getFuelTankLevel() {
		return fuelTankLevel;
	}

	public void setFuelTankLevel(Integer fuelTankLevel) {
		this.fuelTankLevel = fuelTankLevel;
	}

	public Integer getBatteryHealth() {
		return batteryHealth;
	}

	public void setBatteryHealth(Integer batteryHealth) {
		this.batteryHealth = batteryHealth;
	}

	public Timestamp getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Timestamp updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public Timestamp getReceivedDateTime() {
		return receivedDateTime;
	}

	public void setReceivedDateTime(Timestamp receivedDateTime) {
		this.receivedDateTime = receivedDateTime;
	}

	public Timestamp getLastEngineOnTime() {
		return lastEngineOnTime;
	}

	public void setLastEngineOnTime(Timestamp lastEngineOnTime) {
		this.lastEngineOnTime = lastEngineOnTime;
	}

	public Timestamp getVehicleLastUpdateON() {
		return vehicleLastUpdateON;
	}

	public void setVehicleLastUpdateON(Timestamp vehicleLastUpdateON) {
		this.vehicleLastUpdateON = vehicleLastUpdateON;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Double getAdblueLevel() {
		return adblueLevel;
	}

	public void setAdblueLevel(Double adblueLevel) {
		this.adblueLevel = adblueLevel;
	}

}