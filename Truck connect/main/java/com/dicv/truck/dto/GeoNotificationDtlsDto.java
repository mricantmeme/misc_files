package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

public class GeoNotificationDtlsDto extends StatusMessageDto {
	List<GeoNotificationDto> geoFencingMsgList = new ArrayList<GeoNotificationDto>();

	public List<GeoNotificationDto> getGeoFencingMsgList() {
		return geoFencingMsgList;
	}

	public void setGeoFencingMsgList(List<GeoNotificationDto> geoFencingMsgList) {
		this.geoFencingMsgList = geoFencingMsgList;
	}

	@Override
	public String toString() {
		String result = "";
		for (GeoNotificationDto geoNotification : geoFencingMsgList) {
			result += geoNotification.toString() + "\n";
		}
		return result;
	}

}
