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

/**
 * The persistent class for the ALERT_PREFERENCE database table.
 * 
 */
@Entity
@Table(name = "ALERT_PREFERENCE")
public class AlertPreference implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ALERT_PREFERENCE_ALERTID_GENERATOR", sequenceName = "ALERT_PREFERENCE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALERT_PREFERENCE_ALERTID_GENERATOR")
	@Column(name = "ALERT_ID")
	private Integer alertId;

	@Column(name = "DELIVERY_EMAIL_ALERT")
	private int deliveryEmailAlert;

	@Column(name = "DELIVERY_SMS_ALERT")
	private int deliverySmsAlert;

	@Column(name = "DELIVERY_WEB_ALERT")
	private int deliveryWebAlert;

	@Column(name = "GEOFENCE_IN_EMAIL_ALERT")
	private int geofenceInEmailAlert;

	@Column(name = "GEOFENCE_IN_SMS_ALERT")
	private int geofenceInSmsAlert;

	@Column(name = "GEOFENCE_IN_WEB_ALERT")
	private int geofenceInWebAlert;

	@Column(name = "GEOFENCE_OUT_EMAIL_ALERT")
	private int geofenceOutEmailAlert;

	@Column(name = "GEOFENCE_OUT_SMS_ALERT")
	private int geofenceOutSmsAlert;

	@Column(name = "GEOFENCE_OUT_WEB_ALERT")
	private int geofenceOutWebAlert;

	@Column(name = "LOW_BATTERY_EMAIL_ALERT")
	private int lowBatteryEmailAlert;

	@Column(name = "LOW_BATTERY_SMS_ALERT")
	private int lowBatterySmsAlert;

	@Column(name = "LOW_BATTERY_WEB_ALERT")
	private int lowBatteryWebAlert;

	@Column(name = "OVER_SPEED_EMAIL_ALERT")
	private int overSpeedEmailAlert;

	@Column(name = "OVER_SPEED_SMS_ALERT")
	private int overSpeedSmsAlert;

	@Column(name = "OVER_SPEED_WEB_ALERT")
	private int overSpeedWebAlert;

	@Column(name = "PICKUP_EMAIL_ALERT")
	private int pickupEmailAlert;

	@Column(name = "PICKUP_SMS_ALERT")
	private int pickupSmsAlert;

	@Column(name = "PICKUP_WEB_ALERT")
	private int pickupWebAlert;

	@Column(name = "TRIP_START_EMAIL_ALERT")
	private int tripStartEmailAlert;

	@Column(name = "TRIP_START_SMS_ALERT")
	private int tripStartSmsAlert;

	@Column(name = "TRIP_START_WEB_ALERT")
	private int tripStartWebAlert;

	@Column(name = "TRIP_STOP_EMAIL_ALERT")
	private int tripStopEmailAlert;

	@Column(name = "TRIP_STOP_SMS_ALERT")
	private int tripStopSmsAlert;

	@Column(name = "TRIP_STOP_WEB_ALERT")
	private int tripStopWebAlert;

	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@Column(name = "UPDATED_BY")
	private Integer updatedBy;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private DicvUser dicvUser;

	@Column(name = "CREATED_BY")
	private Integer createdBy;

	public AlertPreference() {
	}

	public Integer getAlertId() {
		return this.alertId;
	}

	public void setDeliveryEmailAlert(int deliveryEmailAlert) {
		this.deliveryEmailAlert = deliveryEmailAlert;
	}

	public void setDeliverySmsAlert(int deliverySmsAlert) {
		this.deliverySmsAlert = deliverySmsAlert;
	}

	public void setDeliveryWebAlert(int deliveryWebAlert) {
		this.deliveryWebAlert = deliveryWebAlert;
	}

	public void setGeofenceInEmailAlert(int geofenceInEmailAlert) {
		this.geofenceInEmailAlert = geofenceInEmailAlert;
	}

	public void setGeofenceInSmsAlert(int geofenceInSmsAlert) {
		this.geofenceInSmsAlert = geofenceInSmsAlert;
	}

	public void setGeofenceInWebAlert(int geofenceInWebAlert) {
		this.geofenceInWebAlert = geofenceInWebAlert;
	}

	public void setGeofenceOutEmailAlert(int geofenceOutEmailAlert) {
		this.geofenceOutEmailAlert = geofenceOutEmailAlert;
	}

	public void setGeofenceOutSmsAlert(int geofenceOutSmsAlert) {
		this.geofenceOutSmsAlert = geofenceOutSmsAlert;
	}

	public void setGeofenceOutWebAlert(int geofenceOutWebAlert) {
		this.geofenceOutWebAlert = geofenceOutWebAlert;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setLowBatteryEmailAlert(int lowBatteryEmailAlert) {
		this.lowBatteryEmailAlert = lowBatteryEmailAlert;
	}

	public void setLowBatterySmsAlert(int lowBatterySmsAlert) {
		this.lowBatterySmsAlert = lowBatterySmsAlert;
	}

	public void setLowBatteryWebAlert(int lowBatteryWebAlert) {
		this.lowBatteryWebAlert = lowBatteryWebAlert;
	}

	public void setOverSpeedEmailAlert(int overSpeedEmailAlert) {
		this.overSpeedEmailAlert = overSpeedEmailAlert;
	}

	public void setOverSpeedSmsAlert(int overSpeedSmsAlert) {
		this.overSpeedSmsAlert = overSpeedSmsAlert;
	}

	public void setOverSpeedWebAlert(int overSpeedWebAlert) {
		this.overSpeedWebAlert = overSpeedWebAlert;
	}

	public void setPickupEmailAlert(int pickupEmailAlert) {
		this.pickupEmailAlert = pickupEmailAlert;
	}

	public void setPickupSmsAlert(int pickupSmsAlert) {
		this.pickupSmsAlert = pickupSmsAlert;
	}

	public void setPickupWebAlert(int pickupWebAlert) {
		this.pickupWebAlert = pickupWebAlert;
	}

	public void setTripStartEmailAlert(int tripStartEmailAlert) {
		this.tripStartEmailAlert = tripStartEmailAlert;
	}

	public void setTripStartSmsAlert(int tripStartSmsAlert) {
		this.tripStartSmsAlert = tripStartSmsAlert;
	}

	public void setTripStartWebAlert(int tripStartWebAlert) {
		this.tripStartWebAlert = tripStartWebAlert;
	}

	public void setTripStopEmailAlert(int tripStopEmailAlert) {
		this.tripStopEmailAlert = tripStopEmailAlert;
	}

	public void setTripStopSmsAlert(int tripStopSmsAlert) {
		this.tripStopSmsAlert = tripStopSmsAlert;
	}

	public void setTripStopWebAlert(int tripStopWebAlert) {
		this.tripStopWebAlert = tripStopWebAlert;
	}

	public void setAlertId(Integer alertId) {
		this.alertId = alertId;
	}


	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getDeliveryEmailAlert() {
		return deliveryEmailAlert;
	}

	public int getDeliverySmsAlert() {
		return deliverySmsAlert;
	}

	public int getDeliveryWebAlert() {
		return deliveryWebAlert;
	}

	public int getGeofenceInEmailAlert() {
		return geofenceInEmailAlert;
	}

	public int getGeofenceInSmsAlert() {
		return geofenceInSmsAlert;
	}

	public int getGeofenceInWebAlert() {
		return geofenceInWebAlert;
	}

	public int getGeofenceOutEmailAlert() {
		return geofenceOutEmailAlert;
	}

	public int getGeofenceOutSmsAlert() {
		return geofenceOutSmsAlert;
	}

	public int getGeofenceOutWebAlert() {
		return geofenceOutWebAlert;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public int getLowBatteryEmailAlert() {
		return lowBatteryEmailAlert;
	}

	public int getLowBatterySmsAlert() {
		return lowBatterySmsAlert;
	}

	public int getLowBatteryWebAlert() {
		return lowBatteryWebAlert;
	}

	public int getOverSpeedEmailAlert() {
		return overSpeedEmailAlert;
	}

	public int getOverSpeedSmsAlert() {
		return overSpeedSmsAlert;
	}

	public int getOverSpeedWebAlert() {
		return overSpeedWebAlert;
	}

	public int getPickupEmailAlert() {
		return pickupEmailAlert;
	}

	public int getPickupSmsAlert() {
		return pickupSmsAlert;
	}

	public int getPickupWebAlert() {
		return pickupWebAlert;
	}

	public int getTripStartEmailAlert() {
		return tripStartEmailAlert;
	}

	public int getTripStartSmsAlert() {
		return tripStartSmsAlert;
	}

	public int getTripStartWebAlert() {
		return tripStartWebAlert;
	}

	public int getTripStopEmailAlert() {
		return tripStopEmailAlert;
	}

	public int getTripStopSmsAlert() {
		return tripStopSmsAlert;
	}

	public int getTripStopWebAlert() {
		return tripStopWebAlert;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public DicvUser getDicvUser() {
		return dicvUser;
	}

	public void setDicvUser(DicvUser dicvUser) {
		this.dicvUser = dicvUser;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}


}