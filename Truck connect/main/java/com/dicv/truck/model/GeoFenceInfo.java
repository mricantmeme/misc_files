package com.dicv.truck.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the GEO_FENCE_INFO database table.
 * 
 */
@Entity
@Table(name = "GEO_FENCE_INFO")
public class GeoFenceInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "GEO_FENCE_INFO_GEOFENCEID_GENERATOR", allocationSize = 1, sequenceName = "GEO_FENCE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEO_FENCE_INFO_GEOFENCEID_GENERATOR")
	@Column(name = "GEO_FENCE_ID")
	private Integer geoFenceId;

	@Column(name = "APPLY_TO_ALL_VEHICLES")
	private BigDecimal applyToAllVehicles;

	@Column(name = "CREATED_ON")
	private Calendar createdOn;

	@Column(name = "EMAIL_MODE")
	private Boolean emailMode;

	@Column(name = "ENTRY_ALERT")
	private Boolean entryAlert;

	@Column(name = "ENTRY_NOTIFICATION_TIME")
	private Date entryNotificationTime;

	@Column(name = "EXIT_ALERT")
	private Boolean exitAlert;

	@Column(name = "EXIT_NOTIFICATION_TIME")
	private Date exitNotificationTime;

	@Column(name = "GEO_FENCE_NAME")
	private String geoFenceName;

	@Column(name = "IS_DELETED")
	private Integer isDeleted;

	@Column(name = "LAST_UPDATED_ON")
	private Calendar lastUpdatedOn;

	@Column(name = "SKIPPED_ALERT")
	private Boolean skippedAlert;

	@Column(name = "SMS_MODE")
	private Boolean smsMode;

	@Column(name = "UPDATED_BY")
	private Integer updatedBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "VALID_FROM")
	private Date validFrom;

	@Temporal(TemporalType.DATE)
	@Column(name = "VALID_TO")
	private Date validTo;

	@Column(name = "WEB_MODE")
	private Boolean webMode;

	@ManyToOne
	@JoinColumn(name = "CREATED_BY")
	private DicvUser dicvUser;

	@ManyToOne
	@JoinColumn(name = "GEOFENCE_TYPE_ID")
	private DicvGeoFenceType dicvGeoFenceType;

	@OneToMany(mappedBy = "geoFenceInfo", cascade = CascadeType.ALL)
	private List<GeoFenceCircle> geoFenceCircles;

	@OneToMany(mappedBy = "geoFenceInfo", cascade = CascadeType.ALL)
	private List<VehicleToGeoFence> vehicleToGeoFences;

	@ManyToOne
	@JoinColumn(name = "GEO_FENCE_SHAPE_ID")
	private GeoFenceShape geoFenceShape;

	@OneToMany(mappedBy = "geoFenceInfo", cascade = CascadeType.ALL)
	private List<GeoFencePolygon> geoFencePolygons;

	public GeoFenceInfo() {
	}

	public Integer getGeoFenceId() {
		return this.geoFenceId;
	}

	public void setGeoFenceId(Integer geoFenceId) {
		this.geoFenceId = geoFenceId;
	}

	public BigDecimal getApplyToAllVehicles() {
		return this.applyToAllVehicles;
	}

	public void setApplyToAllVehicles(BigDecimal applyToAllVehicles) {
		this.applyToAllVehicles = applyToAllVehicles;
	}

	public DicvUser getDicvUser() {
		return this.dicvUser;
	}

	public void setDicvUser(DicvUser dicvUser) {
		this.dicvUser = dicvUser;
	}

	public Calendar getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public Boolean getEmailMode() {
		return this.emailMode;
	}

	public void setEmailMode(Boolean emailMode) {
		this.emailMode = emailMode;
	}

	public Boolean getEntryAlert() {
		return this.entryAlert;
	}

	public void setEntryAlert(Boolean entryAlert) {
		this.entryAlert = entryAlert;
	}

	public Date getEntryNotificationTime() {
		return this.entryNotificationTime;
	}

	public void setEntryNotificationTime(Date entryNotificationTime) {
		this.entryNotificationTime = entryNotificationTime;
	}

	public Boolean getExitAlert() {
		return this.exitAlert;
	}

	public void setExitAlert(Boolean exitAlert) {
		this.exitAlert = exitAlert;
	}

	public Date getExitNotificationTime() {
		return this.exitNotificationTime;
	}

	public void setExitNotificationTime(Date exitNotificationTime) {
		this.exitNotificationTime = exitNotificationTime;
	}

	public String getGeoFenceName() {
		return this.geoFenceName;
	}

	public void setGeoFenceName(String geoFenceName) {
		this.geoFenceName = geoFenceName;
	}

	public Integer getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Calendar getLastUpdatedOn() {
		return this.lastUpdatedOn;
	}

	public void setLastUpdatedOn(Calendar lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public Boolean getSkippedAlert() {
		return this.skippedAlert;
	}

	public void setSkippedAlert(Boolean skippedAlert) {
		this.skippedAlert = skippedAlert;
	}

	public Boolean getSmsMode() {
		return this.smsMode;
	}

	public void setSmsMode(Boolean smsMode) {
		this.smsMode = smsMode;
	}

	public DicvGeoFenceType getDicvGeoFenceType() {
		return dicvGeoFenceType;
	}

	public void setDicvGeoFenceType(DicvGeoFenceType dicvGeoFenceType) {
		this.dicvGeoFenceType = dicvGeoFenceType;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return this.validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Boolean getWebMode() {
		return this.webMode;
	}

	public void setWebMode(Boolean webMode) {
		this.webMode = webMode;
	}

	public List<VehicleToGeoFence> getVehicleToGeoFences() {
		return this.vehicleToGeoFences;
	}

	public void setVehicleToGeoFences(List<VehicleToGeoFence> vehicleToGeoFences) {
		this.vehicleToGeoFences = vehicleToGeoFences;
	}

	public VehicleToGeoFence removeVehicleToGeoFence(VehicleToGeoFence vehicleToGeoFence) {
		vehicleToGeoFence.setGeoFenceInfo(null);
		getVehicleToGeoFences().remove(vehicleToGeoFence);

		return vehicleToGeoFence;
	}

	public List<GeoFenceCircle> getGeoFenceCircles() {
		return this.geoFenceCircles;
	}

	public void setGeoFenceCircles(List<GeoFenceCircle> geoFenceCircles) {
		this.geoFenceCircles = geoFenceCircles;
	}

	public GeoFenceCircle addGeoFenceCircle(GeoFenceCircle geoFenceCircle) {
		getGeoFenceCircles().add(geoFenceCircle);
		geoFenceCircle.setGeoFenceInfo(this);

		return geoFenceCircle;
	}

	public GeoFenceCircle removeGeoFenceCircle(GeoFenceCircle geoFenceCircle) {
		getGeoFenceCircles().remove(geoFenceCircle);
		geoFenceCircle.setGeoFenceInfo(null);

		return geoFenceCircle;
	}

	public GeoFenceShape getGeoFenceShape() {
		return this.geoFenceShape;
	}

	public void setGeoFenceShape(GeoFenceShape geoFenceShape) {
		this.geoFenceShape = geoFenceShape;
	}

	public List<GeoFencePolygon> getGeoFencePolygons() {
		return this.geoFencePolygons;
	}

	public void setGeoFencePolygons(List<GeoFencePolygon> geoFencePolygons) {
		this.geoFencePolygons = geoFencePolygons;
	}

	public GeoFencePolygon addGeoFencePolygon(GeoFencePolygon geoFencePolygon) {
		getGeoFencePolygons().add(geoFencePolygon);
		geoFencePolygon.setGeoFenceInfo(this);

		return geoFencePolygon;
	}

	public GeoFencePolygon removeGeoFencePolygon(GeoFencePolygon geoFencePolygon) {
		getGeoFencePolygons().remove(geoFencePolygon);
		geoFencePolygon.setGeoFenceInfo(null);

		return geoFencePolygon;
	}

}