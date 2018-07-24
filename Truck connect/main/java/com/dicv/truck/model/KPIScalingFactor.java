package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "KPI_SCALING_FACTOR")
public class KPIScalingFactor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1078705146198258080L;

	@Id
	@SequenceGenerator(name = "KPI_SCALING_FACTOR_GENERATOR", sequenceName = "KPISCALING_FACTOR_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KPI_SCALING_FACTOR_GENERATOR")
	@Column(name = "KPI_SCALING_FACTOR_ID")
	private Integer gcmId;

	@Column(name = "SPEEDING")
	private Integer speeding;

	@Column(name = "ECONOMIC_DRIVING")
	private Integer economicDriving;

	@Column(name = "IDLING")
	private Integer idling;

	@Column(name = "ECONOMIC_BAND")
	private Integer economicBand;
	

	@Column(name = "HARSH_BRAKING")
	private Integer harshBraking;

	@Column(name = "HARSH_ACCELERATION")
	private Integer harshAcceleration;

	@Column(name = "HARSH_CORNERING")
	private Integer harshCornering;

	@Column(name = "LAST_UPDATED_TIME")
	private Timestamp lastUpdatedTime;

	@Column(name = "UPDATED_BY")
	private Integer updatedBy;

	public Integer getGcmId() {
		return gcmId;
	}

	public void setGcmId(Integer gcmId) {
		this.gcmId = gcmId;
	}

	public Integer getSpeeding() {
		return speeding;
	}

	public void setSpeeding(Integer speeding) {
		this.speeding = speeding;
	}

	public Integer getEconomicDriving() {
		return economicDriving;
	}

	public void setEconomicDriving(Integer economicDriving) {
		this.economicDriving = economicDriving;
	}

	public Integer getIdling() {
		return idling;
	}

	public void setIdling(Integer idling) {
		this.idling = idling;
	}

	public Integer getEconomicBand() {
		return economicBand;
	}

	public void setEconomicBand(Integer economicBand) {
		this.economicBand = economicBand;
	}

	public Timestamp getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "KPIScalingFactor [gcmId=" + gcmId + ", speeding=" + speeding + ", economicDriving=" + economicDriving
				+ ", idling=" + idling + ", economicBand=" + economicBand + ", lastUpdatedTime=" + lastUpdatedTime
				+ ", updatedBy=" + updatedBy + "]";
	}

	public Integer getHarshBraking() {
		return harshBraking;
	}

	public void setHarshBraking(Integer harshBraking) {
		this.harshBraking = harshBraking;
	}

	public Integer getHarshAcceleration() {
		return harshAcceleration;
	}

	public void setHarshAcceleration(Integer harshAcceleration) {
		this.harshAcceleration = harshAcceleration;
	}

	public Integer getHarshCornering() {
		return harshCornering;
	}

	public void setHarshCornering(Integer harshCornering) {
		this.harshCornering = harshCornering;
	}

}
