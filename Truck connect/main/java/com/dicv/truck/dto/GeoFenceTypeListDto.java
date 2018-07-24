package com.dicv.truck.dto;

import java.util.ArrayList;

public class GeoFenceTypeListDto extends StatusMessageDto {

	private ArrayList<GeoFenceTypeDto> typeList = new ArrayList<>();

	public ArrayList<GeoFenceTypeDto> getTypeList() {
		return typeList;
	}

	public void setTypeList(ArrayList<GeoFenceTypeDto> typeList) {
		this.typeList = typeList;
	}

}
