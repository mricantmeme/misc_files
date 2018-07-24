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
 * The persistent class for the PROFITABILITY_CHART database table.
 * 
 */
@Entity
@Table(name = "PROFITABILITY_CHART")
public class ProfitabilityChart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PROFITABILITY_CHART_SEQ", sequenceName = "PROFITABILITY_CHART_SEQ ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFITABILITY_CHART_SEQ")
	@Column(name = "PROFITABILITY_CHART_ID")
	private Integer profitabilityChartId;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "DRIVER_COST")
	private Double driverCost;

	@Column(name = "FUEL_COST")
	private Double fuelCost;

	@Column(name = "OPERATIONAL_COST")
	private Double operationalCost;

	@Column(name = "UPDATED_DATE")
	private Timestamp updatedDate;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "CREATED_BY")
	private DicvUser dicvUserCreatedBy;

	@Column(name = "UPDATED_BY")
	private Integer dicvUserUpdatedBy;

	public ProfitabilityChart() {
	}

	public Integer getProfitabilityChartId() {
		return this.profitabilityChartId;
	}

	public void setProfitabilityChartId(Integer profitabilityChartId) {
		this.profitabilityChartId = profitabilityChartId;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Double getDriverCost() {
		return this.driverCost;
	}

	public void setDriverCost(Double driverCost) {
		this.driverCost = driverCost;
	}

	public Double getFuelCost() {
		return this.fuelCost;
	}

	public void setFuelCost(Double fuelCost) {
		this.fuelCost = fuelCost;
	}

	public Double getOperationalCost() {
		return this.operationalCost;
	}

	public void setOperationalCost(Double operationalCost) {
		this.operationalCost = operationalCost;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the isDeleted
	 */
	public int getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the dicvUserCreatedBy
	 */
	public DicvUser getDicvUserCreatedBy() {
		return dicvUserCreatedBy;
	}

	/**
	 * @param dicvUserCreatedBy
	 *            the dicvUserCreatedBy to set
	 */
	public void setDicvUserCreatedBy(DicvUser dicvUserCreatedBy) {
		this.dicvUserCreatedBy = dicvUserCreatedBy;
	}

	public Integer getDicvUserUpdatedBy() {
		return dicvUserUpdatedBy;
	}

	public void setDicvUserUpdatedBy(Integer dicvUserUpdatedBy) {
		this.dicvUserUpdatedBy = dicvUserUpdatedBy;
	}


}