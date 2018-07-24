package com.dicv.truck.dto;

import java.util.List;

/**
 * This call represents the list of alerts for the given user.
 * 
 * @author seg3kor
 * 
 */
public class AlertReportJrListDto {
	/**
	 * Alert list.
	 */
	private List<AlertsReportJrDto> alerts;

	public List<AlertsReportJrDto> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<AlertsReportJrDto> alerts) {
		this.alerts = alerts;
	}

}
