package com.dicv.truck.dto;

import java.util.List;

public class AlertTypeListDto extends StatusMessageDto {

	private List<AlertTypeDto> alertTypes;

	public List<AlertTypeDto> getAlertTypes() {
		return alertTypes;
	}

	public void setAlertTypes(List<AlertTypeDto> alertTypes) {
		this.alertTypes = alertTypes;
	}

}
