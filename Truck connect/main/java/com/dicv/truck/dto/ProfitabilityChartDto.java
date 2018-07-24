package com.dicv.truck.dto;

/**
 * This class is responsible to keep profitability chart details.
 * 
 * @author aut7kor
 * 
 */
public class ProfitabilityChartDto extends StatusMessageDto {

	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = -7460932341580153040L;

	private Integer userId;
	private Double driverCost;
	private Double fuelCost;
	private Double operationalCost;
	private Integer profitabilityChartId;

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the driverCost
	 */
	public Double getDriverCost() {
		return driverCost;
	}

	/**
	 * @param driverCost
	 *            the driverCost to set
	 */
	public void setDriverCost(Double driverCost) {
		this.driverCost = driverCost;
	}

	/**
	 * @return the fuelCost
	 */
	public Double getFuelCost() {
		return fuelCost;
	}

	/**
	 * @param fuelCost
	 *            the fuelCost to set
	 */
	public void setFuelCost(Double fuelCost) {
		this.fuelCost = fuelCost;
	}

	/**
	 * @return the operationalCost
	 */
	public Double getOperationalCost() {
		return operationalCost;
	}

	/**
	 * @param operationalCost
	 *            the operationalCost to set
	 */
	public void setOperationalCost(Double operationalCost) {
		this.operationalCost = operationalCost;
	}

	/**
	 * @return the profitabilityChartId
	 */
	public Integer getProfitabilityChartId() {
		return profitabilityChartId;
	}

	/**
	 * @param profitabilityChartId
	 *            the profitabilityChartId to set
	 */
	public void setProfitabilityChartId(Integer profitabilityChartId) {
		this.profitabilityChartId = profitabilityChartId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProfitabilityChartDto [userId=" + userId + ", driverCost="
				+ driverCost + ", fuelCost=" + fuelCost + ", operationalCost="
				+ operationalCost + ", profitabilityChartId="
				+ profitabilityChartId + "]";
	}

}
