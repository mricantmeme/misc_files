package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * This class is responsible to persist data into trip table after trip start
 * till stop trip.
 * 
 * @author aut7kor
 * 
 */
@Entity
@Table(name = "TRIP")
public class Trip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TRIP_TRIPID_GENERATOR", sequenceName = "TRIP_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRIP_TRIPID_GENERATOR")
	@Column(name = "TRIP_ID")
	private Long tripId;

	@Column(name = "AVERAGE_VEHICLE_SPEED")
	private Double averageVehicleSpeed;

	@Column(name = "CURRENT_VEHICLE_TIME")
	private Timestamp currentVehicleTime;

	@Column(name = "ELAPSED_TIME")
	private Integer elapsedTime;

	@Column(name = "ENGINE_RUN_HOURS")
	private Integer engineRunHours;

	@Column(name = "GREEN_BAND_TIME")
	private Long greenBandTime;

	@Column(name = "LAST_UPDATE_RECVD_TIME")
	private Timestamp lastUpdateRecvdTime;

	@Column(name = "NO_OF_STOP")
	private Integer nosOfStop;

	@Column(name = "RED_BAND_TIME")
	private Long redBandTime;

	@Column(name = "SPEED_VIOLATION_TIME")
	private Long speedViolationTime;

	@Column(name = "TIME_IN_0_TO_20KM")
	private Long timeIn0To20km;

	@Column(name = "TIME_IN_21_TO_40KM")
	private Long timeIn21To40km;

	@Column(name = "TIME_IN_41_TO_60KM")
	private Long timeIn41To60km;

	@Column(name = "TIME_IN_61_TO_80KM")
	private Long timeIn61To80km;

	@Column(name = "TIME_IN_OVER_80KM")
	private Long timeInOver80km;

	@Column(name = "TRIP_DISTANCE")
	private Double tripDistance;

	@Column(name = "TRIP_END_TIME")
	private Timestamp tripEndTime;

	@Column(name = "TRIP_START_TIME")
	private Timestamp tripStartTime;

	@Column(name = "TRIP_STATUS")
	private String tripStatus;

	@Column(name = "VEHICLE_IDLE_TIME")
	private Integer vehicleIdleTime;

	@Column(name = "YELLOW_BAND_TIME")
	private Long yellowBandTime;

	@Column(name = "CURRENT_IDLE_TIME")
	private Long currentIdleTime;

	@Column(name = "DRIVING_TIME")
	private Long drivingTime;

	@Column(name = "ENGINE_IDLE_TIME")
	private Long engineIdleTimeRpm;

	@Column(name = "IS_DELETED")
	private Integer isDeleted;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;

	@Column(name = "MAXIMUM_SPEED")
	private Integer maximumSpeed;

	@Column(name = "MAX_SPEED_LAT")
	private Double maxSpeedLat;

	@Column(name = "MAX_SPEED_LONG")
	private Double maxSpeedLong;

	@Column(name = "GREEN_BAND_DISTANCE")
	private Double greenBandDistance;

	@Column(name = "ECONOMY_BAND_TIME")
	private Long economyBandTime;

	@Column(name = "ECONOMY_BAND_DISTANCE")
	private Double economyBandDistance;

	@Column(name = "MAX_SPEED_TIME")
	private Timestamp maxSpeedTime;

	@Column(name = "IS_CAN_PARAMETER")
	private Integer isCanParam;

	@Column(name = "ECONOMIC_DRIVING")
	private Double economicDriving;

	@Column(name = "PROCESS_STATUS")
	private Integer processStatus;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "SCHEDULED_TRIP_ID")
	private ScheduledTrip scheduledTrip;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "trip")
	private Dispatch dispatches;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "CREATED_BY")
	private DicvUser tripDriverUser;

	@Column(name = "FROM_ADDRESS")
	private String fromAddress;

	@Column(name = "TO_ADDRESS")
	private String toAddress;

	@Column(name = "FROM_CITY")
	private String fromCity;

	@Column(name = "TO_CITY")
	private String toCity;

	@Column(name = "SPEED_ADHERENCE")
	private Double speeding;

	@Column(name = "IDLING")
	private Double idling;

	@Column(name = "STOP_LOCATION_LAT")
	private Double stopLocationLat;

	@Column(name = "STOP_LOCATION_LONG")
	private Double stopLocationLong;

	@Column(name = "START_LOCATION_LAT")
	private Double startLocationLat;

	@Column(name = "START_LOCATION_LONG")
	private Double startLocationLong;

	public Trip() {
	}

	public Long getTripId() {
		return this.tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

	public Timestamp getCurrentVehicleTime() {
		return this.currentVehicleTime;
	}

	public void setCurrentVehicleTime(Timestamp currentVehicleTime) {
		this.currentVehicleTime = currentVehicleTime;
	}

	public Double getAverageVehicleSpeed() {
		return averageVehicleSpeed;
	}

	public void setAverageVehicleSpeed(Double averageVehicleSpeed) {
		this.averageVehicleSpeed = averageVehicleSpeed;
	}

	public Integer getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(Integer elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public Integer getEngineRunHours() {
		return engineRunHours;
	}

	public void setEngineRunHours(Integer engineRunHours) {
		this.engineRunHours = engineRunHours;
	}

	public Long getGreenBandTime() {
		return greenBandTime;
	}

	public void setGreenBandTime(Long greenBandTime) {
		this.greenBandTime = greenBandTime;
	}

	public Timestamp getLastUpdateRecvdTime() {
		return lastUpdateRecvdTime;
	}

	public void setLastUpdateRecvdTime(Timestamp lastUpdateRecvdTime) {
		this.lastUpdateRecvdTime = lastUpdateRecvdTime;
	}

	public Integer getNosOfStop() {
		return nosOfStop;
	}

	public void setNosOfStop(Integer nosOfStop) {
		this.nosOfStop = nosOfStop;
	}

	public Long getRedBandTime() {
		return redBandTime;
	}

	public void setRedBandTime(Long redBandTime) {
		this.redBandTime = redBandTime;
	}

	public Long getSpeedViolationTime() {
		return speedViolationTime;
	}

	public void setSpeedViolationTime(Long speedViolationTime) {
		this.speedViolationTime = speedViolationTime;
	}

	public Long getTimeIn0To20km() {
		return timeIn0To20km;
	}

	public void setTimeIn0To20km(Long timeIn0To20km) {
		this.timeIn0To20km = timeIn0To20km;
	}

	public Long getTimeIn21To40km() {
		return timeIn21To40km;
	}

	public void setTimeIn21To40km(Long timeIn21To40km) {
		this.timeIn21To40km = timeIn21To40km;
	}

	public Long getTimeIn41To60km() {
		return timeIn41To60km;
	}

	public void setTimeIn41To60km(Long timeIn41To60km) {
		this.timeIn41To60km = timeIn41To60km;
	}

	public Long getTimeIn61To80km() {
		return timeIn61To80km;
	}

	public void setTimeIn61To80km(Long timeIn61To80km) {
		this.timeIn61To80km = timeIn61To80km;
	}

	public Long getTimeInOver80km() {
		return timeInOver80km;
	}

	public void setTimeInOver80km(Long timeInOver80km) {
		this.timeInOver80km = timeInOver80km;
	}

	public Double getTripDistance() {
		return tripDistance;
	}

	public void setTripDistance(Double tripDistance) {
		this.tripDistance = tripDistance;
	}

	public Timestamp getTripEndTime() {
		return tripEndTime;
	}

	public void setTripEndTime(Timestamp tripEndTime) {
		this.tripEndTime = tripEndTime;
	}

	public Timestamp getTripStartTime() {
		return tripStartTime;
	}

	public void setTripStartTime(Timestamp tripStartTime) {
		this.tripStartTime = tripStartTime;
	}

	public String getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(String tripStatus) {
		this.tripStatus = tripStatus;
	}

	public Integer getVehicleIdleTime() {
		return vehicleIdleTime;
	}

	public void setVehicleIdleTime(Integer vehicleIdleTime) {
		this.vehicleIdleTime = vehicleIdleTime;
	}

	public Long getYellowBandTime() {
		return yellowBandTime;
	}

	public void setYellowBandTime(Long yellowBandTime) {
		this.yellowBandTime = yellowBandTime;
	}

	public Long getCurrentIdleTime() {
		return currentIdleTime;
	}

	public void setCurrentIdleTime(Long currentIdleTime) {
		this.currentIdleTime = currentIdleTime;
	}

	public Long getDrivingTime() {
		return drivingTime;
	}

	public void setDrivingTime(Long drivingTime) {
		this.drivingTime = drivingTime;
	}

	public Long getEngineIdleTimeRpm() {
		return engineIdleTimeRpm;
	}

	public void setEngineIdleTimeRpm(Long engineIdleTimeRpm) {
		this.engineIdleTimeRpm = engineIdleTimeRpm;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getMaximumSpeed() {
		return maximumSpeed;
	}

	public void setMaximumSpeed(Integer maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	public Double getMaxSpeedLat() {
		return maxSpeedLat;
	}

	public void setMaxSpeedLat(Double maxSpeedLat) {
		this.maxSpeedLat = maxSpeedLat;
	}

	public Double getMaxSpeedLong() {
		return maxSpeedLong;
	}

	public void setMaxSpeedLong(Double maxSpeedLong) {
		this.maxSpeedLong = maxSpeedLong;
	}

	public Double getGreenBandDistance() {
		return greenBandDistance;
	}

	public void setGreenBandDistance(Double greenBandDistance) {
		this.greenBandDistance = greenBandDistance;
	}

	public Long getEconomyBandTime() {
		return economyBandTime;
	}

	public void setEconomyBandTime(Long economyBandTime) {
		this.economyBandTime = economyBandTime;
	}

	public Double getEconomyBandDistance() {
		return economyBandDistance;
	}

	public void setEconomyBandDistance(Double economyBandDistance) {
		this.economyBandDistance = economyBandDistance;
	}

	public Timestamp getMaxSpeedTime() {
		return maxSpeedTime;
	}

	public void setMaxSpeedTime(Timestamp maxSpeedTime) {
		this.maxSpeedTime = maxSpeedTime;
	}

	public Integer getIsCanParam() {
		return isCanParam;
	}

	public void setIsCanParam(Integer isCanParam) {
		this.isCanParam = isCanParam;
	}

	public Double getEconomicDriving() {
		return economicDriving;
	}

	public void setEconomicDriving(Double economicDriving) {
		this.economicDriving = economicDriving;
	}

	public Integer getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}

	public ScheduledTrip getScheduledTrip() {
		return scheduledTrip;
	}

	public void setScheduledTrip(ScheduledTrip scheduledTrip) {
		this.scheduledTrip = scheduledTrip;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
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

	public Double getSpeeding() {
		return speeding;
	}

	public void setSpeeding(Double speeding) {
		this.speeding = speeding;
	}

	public Double getIdling() {
		return idling;
	}

	public void setIdling(Double idling) {
		this.idling = idling;
	}

	public Double getStopLocationLat() {
		return stopLocationLat;
	}

	public void setStopLocationLat(Double stopLocationLat) {
		this.stopLocationLat = stopLocationLat;
	}

	public Double getStopLocationLong() {
		return stopLocationLong;
	}

	public void setStopLocationLong(Double stopLocationLong) {
		this.stopLocationLong = stopLocationLong;
	}

	public Double getStartLocationLat() {
		return startLocationLat;
	}

	public void setStartLocationLat(Double startLocationLat) {
		this.startLocationLat = startLocationLat;
	}

	public Double getStartLocationLong() {
		return startLocationLong;
	}

	public void setStartLocationLong(Double startLocationLong) {
		this.startLocationLong = startLocationLong;
	}

	public DicvUser getTripDriverUser() {
		return tripDriverUser;
	}

	public void setTripDriverUser(DicvUser tripDriverUser) {
		this.tripDriverUser = tripDriverUser;
	}

	public Dispatch getDispatches() {
		return dispatches;
	}

	public void setDispatches(Dispatch dispatches) {
		this.dispatches = dispatches;
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