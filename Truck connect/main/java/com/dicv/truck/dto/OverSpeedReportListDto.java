package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

public class OverSpeedReportListDto {

	private List<SpeedReportLayoutDto> overSpeedReportDtoList = new ArrayList<SpeedReportLayoutDto>();

	public List<SpeedReportLayoutDto> getOverSpeedReportDtoList() {
		return overSpeedReportDtoList;
	}

	public void setOverSpeedReportDtoList(List<SpeedReportLayoutDto> overSpeedReportDtoList) {
		this.overSpeedReportDtoList = overSpeedReportDtoList;
	};

}
