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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DICV_ALERT_SETTINGS")
public class AlertSettings implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5044457981038965036L;

	@Id
	@SequenceGenerator(name = "DICV_ALERT_SETTINGS_SETTIGSID_GENERATOR", sequenceName = "DICV_ALERT_SETTINGS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DICV_ALERT_SETTINGS_SETTIGSID_GENERATOR")
	@Column(name = "ALERT_SETTINGS_ID")
	private Integer alertId;

	@Column(name = "FUEL_DROP")
	private Integer fuelDrop;

	@Column(name = "BATTERY_HEALTH")
	private Integer batteryHealth;

	@Column(name = "VEHICLE_IDLE_ALERT")
	private Long vehicleIdleTime;

	@Column(name = "SUPPORT_MAIL")
	private String suppportMail;

	@Column(name = "UPDATED_BY")
	private Integer updatedBy;

	@Column(name = "UPDATED_TIME")
	private Timestamp updatedTime;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private DicvUser dicvUser;

	public Integer getAlertId() {
		return alertId;
	}

	public Integer getFuelDrop() {
		return fuelDrop;
	}

	public void setFuelDrop(Integer fuelDrop) {
		this.fuelDrop = fuelDrop;
	}

	public String getSuppportMail() {
		return suppportMail;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setAlertId(Integer alertId) {
		this.alertId = alertId;
	}

	public void setSuppportMail(String suppportMail) {
		this.suppportMail = suppportMail;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Override
	public String toString() {
		return "AlertSettings [alertId=" + alertId + ", fuelDrop=" + fuelDrop + ", batterHealth=" + batteryHealth
				+ ", suppportMail=" + suppportMail + ", updatedBy=" + updatedBy + ", updatedTime=" + updatedTime + "]";
	}

	public Integer getBatteryHealth() {
		return batteryHealth;
	}

	public void setBatteryHealth(Integer batteryHealth) {
		this.batteryHealth = batteryHealth;
	}

	public Long getVehicleIdleTime() {
		return vehicleIdleTime;
	}

	public void setVehicleIdleTime(Long vehicleIdleTime) {
		this.vehicleIdleTime = vehicleIdleTime;
	}

	public DicvUser getDicvUser() {
		return dicvUser;
	}

	public void setDicvUser(DicvUser dicvUser) {
		this.dicvUser = dicvUser;
	}

}
