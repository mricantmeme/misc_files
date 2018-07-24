package com.dicv.truck.dto;

/* 
 * @author AUT7KOR
 *  
 */

public class RoutePlanDtlsDto extends StatusMessageDto {

	private Integer scheduledTripId;
	private Integer scheduledTripLegId;
	private Double startPointLat;
	private Double startPointLong;
	private Double endPointLat;
	private Double endPointLong;
	private String fromAddress;
	private String toAddress;
	private String isSms;
	private String isEmail;
	private String startDate;
	private String endDate;
	private int scheduledTripFlag = -1;

	public Integer getScheduledTripId() {
		return scheduledTripId;
	}

	public void setScheduledTripId(Integer scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	public Integer getScheduledTripLegId() {
		return scheduledTripLegId;
	}

	public void setScheduledTripLegId(Integer scheduledTripLegId) {
		this.scheduledTripLegId = scheduledTripLegId;
	}

	public Double getStartPointLat() {
		return startPointLat;
	}

	public void setStartPointLat(Double startPointLat) {
		this.startPointLat = startPointLat;
	}

	public Double getStartPointLong() {
		return startPointLong;
	}

	public void setStartPointLong(Double startPointLong) {
		this.startPointLong = startPointLong;
	}

	public Double getEndPointLat() {
		return endPointLat;
	}

	public void setEndPointLat(Double endPointLat) {
		this.endPointLat = endPointLat;
	}

	public Double getEndPointLong() {
		return endPointLong;
	}

	public void setEndPointLong(Double endPointLong) {
		this.endPointLong = endPointLong;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getIsSms() {
		return isSms;
	}

	public void setIsSms(String isSms) {
		this.isSms = isSms;
	}

	public String getIsEmail() {
		return isEmail;
	}

	public void setIsEmail(String isEmail) {
		this.isEmail = isEmail;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getScheduledTripFlag() {
		return scheduledTripFlag;
	}

	public void setScheduledTripFlag(int scheduledTripFlag) {
		this.scheduledTripFlag = scheduledTripFlag;
	}

	@Override
	public String toString() {
		return "RoutePlanDtlsDto [scheduledTripId=" + scheduledTripId
				+ ", scheduledTripLegId=" + scheduledTripLegId
				+ ", startPointLat=" + startPointLat + ", startPointLong="
				+ startPointLong + ", endPointLat=" + endPointLat
				+ ", endPointLong=" + endPointLong + ", fromAddress="
				+ fromAddress + ", toAddress=" + toAddress + ", isSms=" + isSms
				+ ", isEmail=" + isEmail + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", scheduledTripFlag="
				+ scheduledTripFlag + "]";
	}

}
