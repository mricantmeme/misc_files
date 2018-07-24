package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

public class NightDrivingReportListDto {

	private List<NightDrivingReportDto> nightDrivingReportDtoList = new ArrayList<NightDrivingReportDto>();

	public List<NightDrivingReportDto> getNightDrivingReportDtoList() {
		return nightDrivingReportDtoList;
	}

	public void setNightDrivingReportDtoList(
			List<NightDrivingReportDto> nightDrivingReportDtoList) {
		this.nightDrivingReportDtoList = nightDrivingReportDtoList;
	}
}
