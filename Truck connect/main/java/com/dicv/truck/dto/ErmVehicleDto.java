package com.dicv.truck.dto;

public class ErmVehicleDto {

	private Integer ermVehicleId;

	private Long gpsSimNumber;

	private Long gpsImei;

	private Boolean gpsTranmission = false;
	
	private Integer userId;
	
	private String gpsReceivedTime;
	
	private String lastGpsReceivedTime;
	
	private Integer status;

	public Long getGpsSimNumber() {
		return gpsSimNumber;
	}

	public void setGpsSimNumber(Long gpsSimNumber) {
		this.gpsSimNumber = gpsSimNumber;
	}

	public Long getGpsImei() {
		return gpsImei;
	}

	public void setGpsImei(Long gpsImei) {
		this.gpsImei = gpsImei;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getErmVehicleId() {
		return ermVehicleId;
	}

	public void setErmVehicleId(Integer ermVehicleId) {
		this.ermVehicleId = ermVehicleId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public Boolean getGpsTranmission() {
		return gpsTranmission;
	}

	public void setGpsTranmission(Boolean gpsTranmission) {
		this.gpsTranmission = gpsTranmission;
	}

	public String getGpsReceivedTime() {
		return gpsReceivedTime;
	}

	public void setGpsReceivedTime(String gpsReceivedTime) {
		this.gpsReceivedTime = gpsReceivedTime;
	}

	public String getLastGpsReceivedTime() {
		return lastGpsReceivedTime;
	}

	public void setLastGpsReceivedTime(String lastGpsReceivedTime) {
		this.lastGpsReceivedTime = lastGpsReceivedTime;
	}

}
