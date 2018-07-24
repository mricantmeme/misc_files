package com.dicv.truck.dto;

import java.util.List;

public class RegionListDto extends StatusMessageDto {

	private List<RegionDto> regions;

	public List<RegionDto> getRegions() {
		return regions;
	}

	public void setRegions(List<RegionDto> regions) {
		this.regions = regions;
	}

}
