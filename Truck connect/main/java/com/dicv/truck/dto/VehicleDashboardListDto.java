/**
 * 
 */
package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class VehicleDashboardListDto extends StatusMessageDto {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private List<VehicleDashboardResponseDto> vehicleDashboardReport = new ArrayList<VehicleDashboardResponseDto>();

	public VehicleDashboardListDto() {

	}

	public List<VehicleDashboardResponseDto> getVehicleDashboardReport() {
		return vehicleDashboardReport;
	}

	public void setVehicleDashboardReport(
			List<VehicleDashboardResponseDto> vehicleDashboardReport) {
		this.vehicleDashboardReport = vehicleDashboardReport;
	}

}
