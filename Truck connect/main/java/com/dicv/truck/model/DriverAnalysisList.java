package com.dicv.truck.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the DRIVER_ANALYSIS_LIST database table.
 * 
 */
@Entity
@Table(name = "DRIVER_ANALYSIS_LIST")
@NamedQuery(name = "DriverAnalysisList.findAll", query = "SELECT d FROM DriverAnalysisList d")
public class DriverAnalysisList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DRIVER_ANALYSIS_LIST_DRIVERANALYSISID_GENERATOR", sequenceName = "DRIVERANALYSIS_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DRIVER_ANALYSIS_LIST_DRIVERANALYSISID_GENERATOR")
	@Column(name = "DRIVER_ANALYSIS_ID")
	private Long driverAnalysisId;

	@Column(name = "AVERAGE_VEHICLE_SPEED")
	private BigDecimal averageVehicleSpeed;

	/*
	 * @Column(name="DRIVER_ID") private Integer driverId;
	 */

	@Lob
	@Column(name = "DRIVER_IMAGE")
	private byte[] driverImage;

	@Column(name = "DRIVER_NAME")
	private String driverName;

	@Column(name = "DRIVER_SCORE")
	private BigDecimal driverScore;

	@Column(name = "ECONOMIC_BAND")
	private BigDecimal economicBand;

	@Column(name = "FUEL_EFFICIENCY")
	private Integer fuelEfficiency;

	@Column(name = "GREEN_BAND_DISTANCE")
	private float greenBandDistance;

	@Column(name = "GROUP_ID")
	private BigDecimal groupId;

	@Column(name = "GROUP_NAME")
	private String groupName;

	@Column(name = "HARSH_DRIVING")
	private Integer harshDriving;
	
	//Column name renamed from IDLE_TIME to ENGINE_IDLE_TIME_PERCENT	
	@Column(name = "ENGINE_IDLE_TIME_PERCENT")
	private BigDecimal engineIdleTimePercent;

	@Column(name = "MAX_SPEED_LAT")
	private double maxSpeedLat;

	@Column(name = "MAX_SPEED_LONG")
	private double maxSpeedLong;

	@Column(name = "MAX_SPEED_TIME")
	private Timestamp maxSpeedTime;

	@Column(name = "MAXIMUM_SPEED")
	private BigDecimal maximumSpeed;

	//Column name renamed from NON_IDLE_TIME to ENGINE_NON_IDLE_TIME_PERCENT	
	@Column(name = "ENGINE_NON_IDLE_TIME_PERCENT")
	private BigDecimal engineNonIdleTimePercent;

	@Column(name = "RECORD_TIMESTAMP")
	private Timestamp recordTimestamp;

	@Temporal(TemporalType.DATE)
	@Column(name = "REPORT_DATE")
	private Date reportDate;

	@Column(name = "SPEED_ADHERENCE")
	private BigDecimal speedAdherence;

	@Column(name = "SPEED_VIOLATION")
	private BigDecimal speedViolation;

	@Column(name = "SPEED_VIOLATION_TIME")
	private BigDecimal speedViolationTime;

	@Column(name = "SPEEDBAND_0_TO_20KM")
	private BigDecimal speedband0To20km;

	@Column(name = "SPEEDBAND_21_TO_40KM")
	private BigDecimal speedband21To40km;

	@Column(name = "SPEEDBAND_41_TO_60KM")
	private BigDecimal speedband41To60km;

	@Column(name = "SPEEDBAND_OVER_80KM")
	private BigDecimal speedbandOver80km;

	@Column(name = "SPEEDBAND_TO_80KM")
	private BigDecimal speedbandTo80km;

	@Column(name = "STAR_COUNT")
	private BigDecimal starCount;

	@Column(name = "TOTAL_DRIVE_TIME")
	private long totalDriveTime;

	@Column(name = "TRIP_DISTANCE")
	private BigDecimal tripDistance;

	@Column(name = "GREEN_BAND_TIME")
	private Integer greenBandTime;

	@Column(name = "YELLOW_BAND_TIME")
	private Integer yellowBandTime;

	@Column(name = "RED_BAND_TIME")
	private Integer redBandTime;

	@Column(name = "ENGINE_RUN_TIME")
	private Integer engineRunTime;

	@Column(name = "ENGINE_IDLE_TIME")
	private Integer engineIdleTime;

	@Column(name = "OWNER_ID")
	private Integer ownerId;

	@Column(name = "LAST_UPD_TS")
	private Timestamp lastUpdTs;
	
	@Column(name = "IS_CAN_PARAMETER")
	private int isCanParam;
	
	@Column(name = "ECONOMY_BAND_DISTANCE")
	private double economyBandDistance;
	

	@Column(name = "ECONOMIC_DRIVING")
	private Double economyDriving;
	

	@ManyToOne
	@JoinColumn(name = "DRIVER_ID")
	private DicvUser dicvUserDriverAnalysis;
	
	
	@Column(name = "SPEEDING_COUNT")
	private Integer speedingCount;

	public DriverAnalysisList() {
	}

	public Long getDriverAnalysisId() {
		return driverAnalysisId;
	}

	public void setDriverAnalysisId(Long driverAnalysisId) {
		this.driverAnalysisId = driverAnalysisId;
	}

	public BigDecimal getAverageVehicleSpeed() {
		return averageVehicleSpeed;
	}

	public void setAverageVehicleSpeed(BigDecimal averageVehicleSpeed) {
		this.averageVehicleSpeed = averageVehicleSpeed;
	}

	public byte[] getDriverImage() {
		return driverImage;
	}

	public void setDriverImage(byte[] driverImage) {
		this.driverImage = driverImage;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public BigDecimal getDriverScore() {
		return driverScore;
	}

	public void setDriverScore(BigDecimal driverScore) {
		this.driverScore = driverScore;
	}

	public BigDecimal getEconomicBand() {
		return economicBand;
	}

	public void setEconomicBand(BigDecimal economicBand) {
		this.economicBand = economicBand;
	}

	public Integer getFuelEfficiency() {
		return fuelEfficiency;
	}

	public void setFuelEfficiency(Integer fuelEfficiency) {
		this.fuelEfficiency = fuelEfficiency;
	}

	public float getGreenBandDistance() {
		return greenBandDistance;
	}

	public void setGreenBandDistance(float greenBandDistance) {
		this.greenBandDistance = greenBandDistance;
	}

	public BigDecimal getGroupId() {
		return groupId;
	}

	public void setGroupId(BigDecimal groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getHarshDriving() {
		return harshDriving;
	}

	public void setHarshDriving(Integer harshDriving) {
		this.harshDriving = harshDriving;
	}

	public BigDecimal getEngineIdleTimePercent() {
		return engineIdleTimePercent;
	}

	public void setEngineIdleTimePercent(BigDecimal engineIdleTimePercent) {
		this.engineIdleTimePercent = engineIdleTimePercent;
	}

	public double getMaxSpeedLat() {
		return maxSpeedLat;
	}

	public void setMaxSpeedLat(double maxSpeedLat) {
		this.maxSpeedLat = maxSpeedLat;
	}

	public double getMaxSpeedLong() {
		return maxSpeedLong;
	}

	public void setMaxSpeedLong(double maxSpeedLong) {
		this.maxSpeedLong = maxSpeedLong;
	}

	public Timestamp getMaxSpeedTime() {
		return maxSpeedTime;
	}

	public void setMaxSpeedTime(Timestamp maxSpeedTime) {
		this.maxSpeedTime = maxSpeedTime;
	}

	public BigDecimal getMaximumSpeed() {
		return maximumSpeed;
	}

	public void setMaximumSpeed(BigDecimal maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	public BigDecimal getEngineNonIdleTimePercent() {
		return engineNonIdleTimePercent;
	}

	public void setEngineNonIdleTimePercent(BigDecimal engineNonIdleTimePercent) {
		this.engineNonIdleTimePercent = engineNonIdleTimePercent;
	}

	public Timestamp getRecordTimestamp() {
		return recordTimestamp;
	}

	public void setRecordTimestamp(Timestamp recordTimestamp) {
		this.recordTimestamp = recordTimestamp;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public BigDecimal getSpeedAdherence() {
		return speedAdherence;
	}

	public void setSpeedAdherence(BigDecimal speedAdherence) {
		this.speedAdherence = speedAdherence;
	}

	public BigDecimal getSpeedViolation() {
		return speedViolation;
	}

	public void setSpeedViolation(BigDecimal speedViolation) {
		this.speedViolation = speedViolation;
	}

	public BigDecimal getSpeedViolationTime() {
		return speedViolationTime;
	}

	public void setSpeedViolationTime(BigDecimal speedViolationTime) {
		this.speedViolationTime = speedViolationTime;
	}

	public BigDecimal getSpeedband0To20km() {
		return speedband0To20km;
	}

	public void setSpeedband0To20km(BigDecimal speedband0To20km) {
		this.speedband0To20km = speedband0To20km;
	}

	public BigDecimal getSpeedband21To40km() {
		return speedband21To40km;
	}

	public void setSpeedband21To40km(BigDecimal speedband21To40km) {
		this.speedband21To40km = speedband21To40km;
	}

	public BigDecimal getSpeedband41To60km() {
		return speedband41To60km;
	}

	public void setSpeedband41To60km(BigDecimal speedband41To60km) {
		this.speedband41To60km = speedband41To60km;
	}

	public BigDecimal getSpeedbandOver80km() {
		return speedbandOver80km;
	}

	public void setSpeedbandOver80km(BigDecimal speedbandOver80km) {
		this.speedbandOver80km = speedbandOver80km;
	}

	public BigDecimal getSpeedbandTo80km() {
		return speedbandTo80km;
	}

	public void setSpeedbandTo80km(BigDecimal speedbandTo80km) {
		this.speedbandTo80km = speedbandTo80km;
	}

	public BigDecimal getStarCount() {
		return starCount;
	}

	public void setStarCount(BigDecimal starCount) {
		this.starCount = starCount;
	}

	public long getTotalDriveTime() {
		return totalDriveTime;
	}

	public void setTotalDriveTime(long totalDriveTime) {
		this.totalDriveTime = totalDriveTime;
	}

	public BigDecimal getTripDistance() {
		return tripDistance;
	}

	public void setTripDistance(BigDecimal tripDistance) {
		this.tripDistance = tripDistance;
	}

	public DicvUser getDicvUserDriverAnalysis() {
		return dicvUserDriverAnalysis;
	}

	public void setDicvUserDriverAnalysis(DicvUser dicvUserDriverAnalysis) {
		this.dicvUserDriverAnalysis = dicvUserDriverAnalysis;
	}

	public double getEconomyBandDistance() {
		return economyBandDistance;
	}

	public void setEconomyBandDistance(double economyBandDistance) {
		this.economyBandDistance = economyBandDistance;
	}

	public Integer getGreenBandTime() {
		return greenBandTime;
	}

	public void setGreenBandTime(Integer greenBandTime) {
		this.greenBandTime = greenBandTime;
	}

	public Integer getYellowBandTime() {
		return yellowBandTime;
	}

	public void setYellowBandTime(Integer yellowBandTime) {
		this.yellowBandTime = yellowBandTime;
	}

	public Integer getRedBandTime() {
		return redBandTime;
	}

	public void setRedBandTime(Integer redBandTime) {
		this.redBandTime = redBandTime;
	}

	public Integer getEngineRunTime() {
		return engineRunTime;
	}

	public void setEngineRunTime(Integer engineRunTime) {
		this.engineRunTime = engineRunTime;
	}

	public Integer getEngineIdleTime() {
		return engineIdleTime;
	}

	public void setEngineIdleTime(Integer engineIdleTime) {
		this.engineIdleTime = engineIdleTime;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Timestamp getLastUpdTs() {
		return lastUpdTs;
	}

	public void setLastUpdTs(Timestamp lastUpdTs) {
		this.lastUpdTs = lastUpdTs;
	}

	public int getIsCanParam() {
		return isCanParam;
	}

	public void setIsCanParam(int isCanParam) {
		this.isCanParam = isCanParam;
	}

	public Double getEconomyDriving() {
		return economyDriving;
	}

	public void setEconomyDriving(Double economyDriving) {
		this.economyDriving = economyDriving;
	}

	public Integer getSpeedingCount() {
		return speedingCount;
	}

	public void setSpeedingCount(Integer speedingCount) {
		this.speedingCount = speedingCount;
	}
	
	

}