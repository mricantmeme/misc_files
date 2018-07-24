/**
 * 
 */
package com.dicv.truck.dto;

import java.util.List;

/**
 * @author IMT5KOR
 * 
 */
public class DriverAnalysisLineChartListDto extends StatusMessageDto

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5825630617035646869L;

	private List<DriverAnalysisLineChartDto> driverAnalysisLineChart;

	public DriverAnalysisLineChartListDto() {

	}

	public List<DriverAnalysisLineChartDto> getDriverAnalysisLineChart() {
		return driverAnalysisLineChart;
	}

	public void setDriverAnalysisLineChart(
			List<DriverAnalysisLineChartDto> driverAnalysisLineChart) {
		this.driverAnalysisLineChart = driverAnalysisLineChart;
	}

}
