package com.dicv.truck.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the NOTIFICATION database table.
 * 
 */
@Entity
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "NOTIFICATION_NOTIFICATIONID_GENERATOR", sequenceName = "NOTIFICATION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTIFICATION_NOTIFICATIONID_GENERATOR")
	@Column(name = "NOTIFICATION_ID")
	private Integer notificationId;

	@Column(name = "MESSAGE")
	private String message;

	@Column(name = "NOTIFICATION_TYPE")
	private String notificationType;

	@Column(name = "READ_DATE_TIME")
	private Calendar readDateTime;

	@Column(name = "RECEIVED_DATE_TIME")
	private Calendar receivedDateTime;

	private String status;

	@ManyToOne
	@JoinColumn(name = "CREATED_BY")
	private DicvUser dicvUser;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ALERT_TYPE_ID")
	private AlertType alertType;

	@Column(name = "EMAIL_ALERT")
	private Integer emailAlert;

	@Column(name = "EVENT_GPS_TIME")
	private Calendar eventGpsTime;

	@Column(name = "GEO_LATITUDE")
	private Double geoLatitude;

	@Column(name = "GEO_LONGITUTE")
	private Double geoLongitute;
	

	@Column(name = "SMS_ALERT")
	private Integer smsAlert;

	@Column(name = "WEB_ALERT")
	private Integer webAlert;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "GEO_RESPONSE")
	private Integer geoResponse;
	
	@Column(name = "RECEIVED_VALUE")
	private Integer receivedValue;

	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public Calendar getReadDateTime() {
		return readDateTime;
	}

	public void setReadDateTime(Calendar readDateTime) {
		this.readDateTime = readDateTime;
	}

	public Calendar getReceivedDateTime() {
		return receivedDateTime;
	}

	public void setReceivedDateTime(Calendar receivedDateTime) {
		this.receivedDateTime = receivedDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public AlertType getAlertType() {
		return alertType;
	}

	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}

	public Integer getEmailAlert() {
		return emailAlert;
	}

	public void setEmailAlert(Integer emailAlert) {
		this.emailAlert = emailAlert;
	}

	public Calendar getEventGpsTime() {
		return eventGpsTime;
	}

	public void setEventGpsTime(Calendar eventGpsTime) {
		this.eventGpsTime = eventGpsTime;
	}

	public Double getGeoLatitude() {
		return geoLatitude;
	}

	public void setGeoLatitude(Double geoLatitude) {
		this.geoLatitude = geoLatitude;
	}

	public Double getGeoLongitute() {
		return geoLongitute;
	}

	public void setGeoLongitute(Double geoLongitute) {
		this.geoLongitute = geoLongitute;
	}

	public Integer getSmsAlert() {
		return smsAlert;
	}

	public void setSmsAlert(Integer smsAlert) {
		this.smsAlert = smsAlert;
	}

	public Integer getWebAlert() {
		return webAlert;
	}

	public void setWebAlert(Integer webAlert) {
		this.webAlert = webAlert;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getGeoResponse() {
		return geoResponse;
	}

	public void setGeoResponse(Integer geoResponse) {
		this.geoResponse = geoResponse;
	}

	public Integer getReceivedValue() {
		return receivedValue;
	}

	public void setReceivedValue(Integer receivedValue) {
		this.receivedValue = receivedValue;
	}

}