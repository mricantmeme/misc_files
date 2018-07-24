package com.dicv.truck.dto;

/**
 * This class is responsible to return specific vehicleId,gpsImei based on
 * tabletImei.
 * 
 * @author aut7kor
 * 
 */
public class GpsImeiDtlsDto extends StatusMessageDto {

	private Integer vehicleId;
	private Long gpsImei;

	public Long getGpsImei() {
		return gpsImei;
	}

	public void setGpsImei(Long gpsImei) {
		this.gpsImei = gpsImei;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Override
	public String toString() {
		return "GpsImeiDtls [vehicleId=" + vehicleId + ", gpsImei=" + gpsImei
				+ "]";
	}

}
