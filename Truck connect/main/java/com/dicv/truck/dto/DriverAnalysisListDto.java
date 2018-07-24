package com.dicv.truck.dto;

import java.util.List;

public class DriverAnalysisListDto extends StatusMessageDto {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private List<DriverAnalysisDto> driverAnalysisList;

	/**
	 * @return the driverAnalysisList
	 */
	public List<DriverAnalysisDto> getDriverAnalysisList() {
		return driverAnalysisList;
	}

	/**
	 * @param driverAnalysisList
	 *            the tripAnalysisList to set
	 */
	public void setDriverAnalysisList(List<DriverAnalysisDto> driverAnalysisList) {
		this.driverAnalysisList = driverAnalysisList;
	}

}
