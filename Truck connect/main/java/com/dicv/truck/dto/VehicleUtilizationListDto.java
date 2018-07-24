/**
 * 
 */
package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IMT5KOR
 * 
 */
public class VehicleUtilizationListDto extends StatusMessageDto {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private List<VehicleUtilizationResponseDto> vehicleUtilizationReport = new ArrayList<VehicleUtilizationResponseDto>();

	public VehicleUtilizationListDto() {

	}

	public List<VehicleUtilizationResponseDto> getVehicleUtilizationReport() {
		return vehicleUtilizationReport;
	}

	public void setVehicleUtilizationReport(
			List<VehicleUtilizationResponseDto> vehicleUtilizationReport) {
		this.vehicleUtilizationReport = vehicleUtilizationReport;
	}

}
