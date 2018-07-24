package com.dicv.truck.dto;

import java.util.List;

public class VehicleSpeedListDto extends StatusMessageDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6528517961304352891L;
	
	
	private List<VehicleStatusGraphReportDto> graphReport;


	public List<VehicleStatusGraphReportDto> getGraphReport() {
		return graphReport;
	}


	public void setGraphReport(List<VehicleStatusGraphReportDto> graphReport) {
		this.graphReport = graphReport;
	}



}
