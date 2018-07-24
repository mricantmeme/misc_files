package com.dicv.truck.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SPEED_REPORT")
public class SpeedReport implements Serializable {
	public SpeedReport() {
	}


	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SPEED_REPORT_ID_SPEEDREPORTID_GENERATOR", sequenceName = "SPEED_REPORT_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPEED_REPORT_ID_SPEEDREPORTID_GENERATOR")
	@Column(name = "SPEED_REPORT_ID")
	private Long speedReportId;

	@Column(name = "VEHICLE_ID")
	private Integer vehicleId;

	@Temporal(TemporalType.DATE)
	@Column(name = "REPORT_DATE")
	private Date reportDate;

	@Column(name = "TIME_0_10")
	private Long timeIn0To10km;

	@Column(name = "TIME_11_20")
	private Long timeIn11To20km;

	@Column(name = "TIME_21_30")
	private Long timeIn21To30km;

	@Column(name = "TIME_31_40")
	private Long timeIn31To40km;

	@Column(name = "TIME_41_50")
	private Long timeIn41To50km;

	@Column(name = "TIME_51_60")
	private Long timeIn51To60km;

	@Column(name = "TIME_61_70")
	private Long timeIn61To70km;

	@Column(name = "TIME_71_80")
	private Long timeIn71To80km;

	@Column(name = "TIME_81_90")
	private Long timeIn81To90km;

	@Column(name = "TIME_91_100")
	private Long timeIn91To100km;

	@Column(name = "TIME_101_110")
	private Long timeIn101To110km;

	@Column(name = "TIME_OVER_110")
	private Long timeInOver110km;

	@Column(name = "DIST_0_10")
	private Double distIn0To10km;

	@Column(name = "DIST_11_20")
	private Double distIn11To20km;

	@Column(name = "DIST_21_30")
	private Double distIn21To30km;

	@Column(name = "DIST_31_40")
	private Double distIn31To40km;

	@Column(name = "DIST_41_50")
	private Double distIn41To50km;

	@Column(name = "DIST_51_60")
	private Double distIn51To60km;

	@Column(name = "DIST_61_70")
	private Double distIn61To70km;

	@Column(name = "DIST_71_80")
	private Double disteIn71To80km;

	@Column(name = "DIST_81_90")
	private Double distIn81To90km;

	@Column(name = "DIST_91_100")
	private Double distIn91To100km;

	@Column(name = "DIST_101_110")
	private Double distIn101To110km;

	@Column(name = "DIST_OVER_110")
	private Double distInOver110km;

	@Column(name = "MAX_SPEED")
	private Double maxSpeed;

	@Column(name = "TOTAL_DISTANCE")
	private Double totalDistance;

	@Column(name = "MAX_SPEED_LATITUDE")
	private Double maxSpeedLat;

	@Column(name = "MAX_SPEED_LONGITUDE")
	private Double maxSpeedLong;
	
	@Column(name = "LOCATION")
	private String location;

	public Long getSpeedReportId() {
		return speedReportId;
	}

	public void setSpeedReportId(Long speedReportId) {
		this.speedReportId = speedReportId;
	}


	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Long getTimeIn0To10km() {
		return timeIn0To10km;
	}

	public void setTimeIn0To10km(Long timeIn0To10km) {
		this.timeIn0To10km = timeIn0To10km;
	}

	public Long getTimeIn11To20km() {
		return timeIn11To20km;
	}

	public void setTimeIn11To20km(Long timeIn11To20km) {
		this.timeIn11To20km = timeIn11To20km;
	}

	public Long getTimeIn21To30km() {
		return timeIn21To30km;
	}

	public void setTimeIn21To30km(Long timeIn21To30km) {
		this.timeIn21To30km = timeIn21To30km;
	}

	public Long getTimeIn31To40km() {
		return timeIn31To40km;
	}

	public void setTimeIn31To40km(Long timeIn31To40km) {
		this.timeIn31To40km = timeIn31To40km;
	}

	public Long getTimeIn41To50km() {
		return timeIn41To50km;
	}

	public void setTimeIn41To50km(Long timeIn41To50km) {
		this.timeIn41To50km = timeIn41To50km;
	}

	public Long getTimeIn51To60km() {
		return timeIn51To60km;
	}

	public void setTimeIn51To60km(Long timeIn51To60km) {
		this.timeIn51To60km = timeIn51To60km;
	}

	public Long getTimeIn61To70km() {
		return timeIn61To70km;
	}

	public void setTimeIn61To70km(Long timeIn61To70km) {
		this.timeIn61To70km = timeIn61To70km;
	}

	public Long getTimeIn71To80km() {
		return timeIn71To80km;
	}

	public void setTimeIn71To80km(Long timeIn71To80km) {
		this.timeIn71To80km = timeIn71To80km;
	}

	public Long getTimeIn81To90km() {
		return timeIn81To90km;
	}

	public void setTimeIn81To90km(Long timeIn81To90km) {
		this.timeIn81To90km = timeIn81To90km;
	}

	public Long getTimeIn91To100km() {
		return timeIn91To100km;
	}

	public void setTimeIn91To100km(Long timeIn91To100km) {
		this.timeIn91To100km = timeIn91To100km;
	}

	public Long getTimeIn101To110km() {
		return timeIn101To110km;
	}

	public void setTimeIn101To110km(Long timeIn101To110km) {
		this.timeIn101To110km = timeIn101To110km;
	}

	public Long getTimeInOver110km() {
		return timeInOver110km;
	}

	public void setTimeInOver110km(Long timeInOver110km) {
		this.timeInOver110km = timeInOver110km;
	}

	public Double getDistIn0To10km() {
		return distIn0To10km;
	}

	public void setDistIn0To10km(Double distIn0To10km) {
		this.distIn0To10km = distIn0To10km;
	}

	public Double getDistIn11To20km() {
		return distIn11To20km;
	}

	public void setDistIn11To20km(Double distIn11To20km) {
		this.distIn11To20km = distIn11To20km;
	}

	public Double getDistIn21To30km() {
		return distIn21To30km;
	}

	public void setDistIn21To30km(Double distIn21To30km) {
		this.distIn21To30km = distIn21To30km;
	}

	public Double getDistIn31To40km() {
		return distIn31To40km;
	}

	public void setDistIn31To40km(Double distIn31To40km) {
		this.distIn31To40km = distIn31To40km;
	}

	public Double getDistIn41To50km() {
		return distIn41To50km;
	}

	public void setDistIn41To50km(Double distIn41To50km) {
		this.distIn41To50km = distIn41To50km;
	}

	public Double getDistIn51To60km() {
		return distIn51To60km;
	}

	public void setDistIn51To60km(Double distIn51To60km) {
		this.distIn51To60km = distIn51To60km;
	}

	public Double getDistIn61To70km() {
		return distIn61To70km;
	}

	public void setDistIn61To70km(Double distIn61To70km) {
		this.distIn61To70km = distIn61To70km;
	}

	public Double getDisteIn71To80km() {
		return disteIn71To80km;
	}

	public void setDisteIn71To80km(Double disteIn71To80km) {
		this.disteIn71To80km = disteIn71To80km;
	}

	public Double getDistIn81To90km() {
		return distIn81To90km;
	}

	public void setDistIn81To90km(Double distIn81To90km) {
		this.distIn81To90km = distIn81To90km;
	}

	public Double getDistIn91To100km() {
		return distIn91To100km;
	}

	public void setDistIn91To100km(Double distIn91To100km) {
		this.distIn91To100km = distIn91To100km;
	}

	public Double getDistIn101To110km() {
		return distIn101To110km;
	}

	public void setDistIn101To110km(Double distIn101To110km) {
		this.distIn101To110km = distIn101To110km;
	}

	public Double getDistInOver110km() {
		return distInOver110km;
	}

	public void setDistInOver110km(Double distInOver110km) {
		this.distInOver110km = distInOver110km;
	}

	public Double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Double getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(Double totalDistance) {
		this.totalDistance = totalDistance;
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

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}



	
	

}
