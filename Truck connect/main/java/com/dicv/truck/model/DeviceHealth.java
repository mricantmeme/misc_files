package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICE_HEALTH_REPORT")
public class DeviceHealth implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DEVICE_HEALTH_DEVICEHEALTHID_GENERATOR", sequenceName = "DEVICE_HEALTH_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEVICE_HEALTH_DEVICEHEALTHID_GENERATOR")
	@Column(name = "DEVICE_HEALTH_ID")
	private Integer deviceHealthId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	@Column(name = "REGISTRATION_ID")
	private String registrationId;

	@Column(name = "GPS_IMEI")
	private Long gpsImei;

	@Column(name = "GROUP_NAME")
	private String groupName;

	@Column(name = "SIGNAL_STRENGTH")
	private Integer signalStrength;

	@Column(name = "DEVICE_BATTERY")
	private Double deviceBatery;

	@Column(name = "GPS_VOLT")
	private Double gpsVolt;

	@Column(name = "GPS_TIME")
	private Timestamp gpsTime;

	@Column(name = "CAN_VEH_UPDATED_TIME")
	private Timestamp canVehicleUpdateTime;

	@Column(name = "UPDATED_TIME")
	private Timestamp updateTime;

	public Integer getDeviceHealthId() {
		return deviceHealthId;
	}

	public void setDeviceHealthId(Integer deviceHealthId) {
		this.deviceHealthId = deviceHealthId;
	}

	public Integer getSignalStrength() {
		return signalStrength;
	}

	public void setSignalStrength(Integer signalStrength) {
		this.signalStrength = signalStrength;
	}

	public Double getDeviceBatery() {
		return deviceBatery;
	}

	public void setDeviceBatery(Double deviceBatery) {
		this.deviceBatery = deviceBatery;
	}

	public Double getGpsVolt() {
		return gpsVolt;
	}

	public void setGpsVolt(Double gpsVolt) {
		this.gpsVolt = gpsVolt;
	}

	public Timestamp getGpsTime() {
		return gpsTime;
	}

	public void setGpsTime(Timestamp gpsTime) {
		this.gpsTime = gpsTime;
	}

	public Timestamp getCanVehicleUpdateTime() {
		return canVehicleUpdateTime;
	}

	public void setCanVehicleUpdateTime(Timestamp canVehicleUpdateTime) {
		this.canVehicleUpdateTime = canVehicleUpdateTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Long getGpsImei() {
		return gpsImei;
	}

	public void setGpsImei(Long gpsImei) {
		this.gpsImei = gpsImei;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
