package com.dicv.truck.dto;

import java.sql.Timestamp;
import java.util.Date;

public class TripRecordDto {

	private Integer userId;
	private Integer vehicleId; // input,output
	private Integer driverId;
	private Date fromDate;
	private Date toDate;
	private int startRow;
	private int endRow;
	private String tripStatus; // input,output
	private Long gpsImei;
	// output
	private String fromDateStr;
	private String toDateStr;
	private Double startPointLat;
	private Double startPointLong;
	private Double endPointLat;
	private Double endPointLong;
	private Long tripId;
	private String fromAddress;
	private String toAddress;
	private String fromCity;
	private String toCity;
	private String registrationId;
	private String vehicleDescription;
	private Integer distance;
	private String keyword;
	private Integer scheduledTripFlag = -1; // input // Based on flag value
	// scheduledTrip(scheduledTripFlag=1)/unScheduledTrip(scheduledTripFlag=0).
	private TripPickupDispatchDtlsDto tripPickupDispatchDtlsDto; // Adding
																	// pickup
																	// and
																	// dispatch
																	// object to
																	// trip
																	// record.
	private Timestamp actualTripStartDate;
	private Timestamp actualTripEndDate;
	private Long scheduledTripId;

	public Long getScheduledTripId() {
		return scheduledTripId;
	}

	public void setScheduledTripId(Long scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	public TripRecordDto() {

	}

	public TripRecordDto(String tripStatus, Double startPointLat, Double startPointLong, Double endPointLat,
			Double endPointLong, Integer userId, Integer driverId, Integer vehicleId, Long tripId, Long scheduledTripId,
			Date fromDate, Date toDate, String fromAddress, String toAddress, String registrationId,
			String vehicleDescription, Integer scheduledTripFlag) {
		this.tripStatus = tripStatus;
		this.startPointLat = startPointLat;
		this.startPointLong = startPointLong;
		this.endPointLat = endPointLat;
		this.endPointLong = endPointLong;
		this.userId = userId;
		this.driverId = driverId;
		this.vehicleId = vehicleId;
		this.tripId = tripId;
		this.scheduledTripId = scheduledTripId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.registrationId = registrationId;
		this.vehicleDescription = vehicleDescription;
		this.scheduledTripFlag = scheduledTripFlag;

	}

	public TripRecordDto(String tripStatus, Double startPointLat, Double startPointLong, Double endPointLat,
			Double endPointLong, Integer userId, Integer driverId, Integer vehicleId, Long scheduledTripId,
			Date fromDate, Date toDate, String fromAddress, String toAddress, String registrationId,
			String vehicleDescription, Integer scheduledTripFlag, Long gpsImei) {
		this.tripStatus = tripStatus;
		this.startPointLat = startPointLat;
		this.startPointLong = startPointLong;
		this.endPointLat = endPointLat;
		this.endPointLong = endPointLong;
		this.userId = userId;
		this.driverId = driverId;
		this.vehicleId = vehicleId;
		this.scheduledTripId = scheduledTripId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.registrationId = registrationId;
		this.vehicleDescription = vehicleDescription;
		this.scheduledTripFlag = scheduledTripFlag;
		this.gpsImei = gpsImei;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public String getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(String tripStatus) {
		this.tripStatus = tripStatus;
	}

	public String getFromDateStr() {
		return fromDateStr;
	}

	public void setFromDateStr(String fromDateStr) {
		this.fromDateStr = fromDateStr;
	}

	public String getToDateStr() {
		return toDateStr;
	}

	public void setToDateStr(String toDateStr) {
		this.toDateStr = toDateStr;
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

	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
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

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getVehicleDescription() {
		return vehicleDescription;
	}

	public void setVehicleDescription(String vehicleDescription) {
		this.vehicleDescription = vehicleDescription;
	}

	public TripPickupDispatchDtlsDto getTripPickupDispatchDtlsDto() {
		return tripPickupDispatchDtlsDto;
	}

	public void setTripPickupDispatchDtlsDto(TripPickupDispatchDtlsDto tripPickupDispatchDtlsDto) {
		this.tripPickupDispatchDtlsDto = tripPickupDispatchDtlsDto;
	}

	public Timestamp getActualTripStartDate() {
		return actualTripStartDate;
	}

	public void setActualTripStartDate(Timestamp actualTripStartDate) {
		this.actualTripStartDate = actualTripStartDate;
	}

	public Timestamp getActualTripEndDate() {
		return actualTripEndDate;
	}

	public void setActualTripEndDate(Timestamp actualTripEndDate) {
		this.actualTripEndDate = actualTripEndDate;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getGpsImei() {
		return gpsImei;
	}

	public void setGpsImei(Long gpsImei) {
		this.gpsImei = gpsImei;
	}

	@Override
	public String toString() {
		return "TripRecordDto [userId=" + userId + ", vehicleId=" + vehicleId + ", driverId=" + driverId + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", startRow=" + startRow + ", endRow=" + endRow + ", tripStatus="
				+ tripStatus + ", fromDateStr=" + fromDateStr + ", toDateStr=" + toDateStr + ", startPointLat="
				+ startPointLat + ", startPointLong=" + startPointLong + ", endPointLat=" + endPointLat
				+ ", endPointLong=" + endPointLong + ", tripId=" + tripId + ", fromAddress=" + fromAddress
				+ ", toAddress=" + toAddress + ", registrationId=" + registrationId + ", vehicleDescription="
				+ vehicleDescription + ", scheduledTripFlag=" + scheduledTripFlag + ", tripPickupDispatchDtlsDto="
				+ tripPickupDispatchDtlsDto + ", actualTripStartDate=" + actualTripStartDate + ", actualTripEndDate="
				+ actualTripEndDate + ", scheduledTripId=" + scheduledTripId + "]";
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getScheduledTripFlag() {
		return scheduledTripFlag;
	}

	public void setScheduledTripFlag(Integer scheduledTripFlag) {
		this.scheduledTripFlag = scheduledTripFlag;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

}
