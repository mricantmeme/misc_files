package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "NIGHT_DRIVING")
public class NightDriving implements Serializable {

	public NightDriving() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "NIGHT_DRIVING_ID_NIGHT_DRIVING_GENERATOR", sequenceName = "NIGHT_DRIVING_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NIGHT_DRIVING_ID_NIGHT_DRIVING_GENERATOR")
	@Column(name = "NIGHT_DRIVING_ID")
	private Long nightDrivingId;

	@Column(name = "VEHICLE_ID")
	private Integer vehicleId;

	@Temporal(TemporalType.DATE)
	@Column(name = "REPORT_DATE")
	private Date reportDate;

	@Column(name = "TIME_8_9")
	private Long timeIn_8_9;

	@Column(name = "DIST_8_9")
	private Long distIn_8_9;

	@Column(name = "TIME_9_10")
	private Long timeIn_9_10;

	@Column(name = "TIME_10_11")
	private Long timeIn_10_11;

	@Column(name = "TIME_11_12")
	private Long timeIn_11_12;

	@Column(name = "TIME_12_1")
	private Long timeIn_12_1;

	@Column(name = "TIME_1_2")
	private Long timeIn_1_2;

	@Column(name = "TIME_2_3")
	private Long timeIn_2_3;

	@Column(name = "TIME_3_4")
	private Long timeIn_3_4;

	@Column(name = "TIME_4_5")
	private Long timeIn_4_5;

	@Column(name = "TIME_5_6")
	private Long timeIn_5_6;

	@Column(name = "DIST_9_10")
	private Double distIn_9_10;

	@Column(name = "DIST_10_11")
	private Double distIn_10_11;

	@Column(name = "DIST_11_12")
	private Double distIn_11_12;

	@Column(name = "DIST_12_1")
	private Double distIn_12_1;

	@Column(name = "DIST_1_2")
	private Double distIn_1_2;

	@Column(name = "DIST_2_3")
	private Double distIn_2_3;

	@Column(name = "DIST_3_4")
	private Double distIn_3_4;

	@Column(name = "DIST_4_5")
	private Double distIn_4_5;

	@Column(name = "DIST_5_6")
	private Double distIn_5_6;

	@Column(name = "UPDATED_TIME")
	private Timestamp updatedTime;

	public Long getNightDrivingId() {
		return nightDrivingId;
	}

	public void setNightDrivingId(Long nightDrivingId) {
		this.nightDrivingId = nightDrivingId;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Long getTimeIn_9_10() {
		return timeIn_9_10;
	}

	public void setTimeIn_9_10(Long timeIn_9_10) {
		this.timeIn_9_10 = timeIn_9_10;
	}

	public Long getTimeIn_10_11() {
		return timeIn_10_11;
	}

	public void setTimeIn_10_11(Long timeIn_10_11) {
		this.timeIn_10_11 = timeIn_10_11;
	}

	public Long getTimeIn_11_12() {
		return timeIn_11_12;
	}

	public void setTimeIn_11_12(Long timeIn_11_12) {
		this.timeIn_11_12 = timeIn_11_12;
	}

	public Long getTimeIn_12_1() {
		return timeIn_12_1;
	}

	public void setTimeIn_12_1(Long timeIn_12_1) {
		this.timeIn_12_1 = timeIn_12_1;
	}

	public Long getTimeIn_1_2() {
		return timeIn_1_2;
	}

	public void setTimeIn_1_2(Long timeIn_1_2) {
		this.timeIn_1_2 = timeIn_1_2;
	}

	public Long getTimeIn_2_3() {
		return timeIn_2_3;
	}

	public void setTimeIn_2_3(Long timeIn_2_3) {
		this.timeIn_2_3 = timeIn_2_3;
	}

	public Long getTimeIn_3_4() {
		return timeIn_3_4;
	}

	public void setTimeIn_3_4(Long timeIn_3_4) {
		this.timeIn_3_4 = timeIn_3_4;
	}

	public Long getTimeIn_4_5() {
		return timeIn_4_5;
	}

	public void setTimeIn_4_5(Long timeIn_4_5) {
		this.timeIn_4_5 = timeIn_4_5;
	}

	public Long getTimeIn_5_6() {
		return timeIn_5_6;
	}

	public void setTimeIn_5_6(Long timeIn_5_6) {
		this.timeIn_5_6 = timeIn_5_6;
	}

	public Double getDistIn_9_10() {
		return distIn_9_10;
	}

	public void setDistIn_9_10(Double distIn_9_10) {
		this.distIn_9_10 = distIn_9_10;
	}

	public Double getDistIn_10_11() {
		return distIn_10_11;
	}

	public void setDistIn_10_11(Double distIn_10_11) {
		this.distIn_10_11 = distIn_10_11;
	}

	public Double getDistIn_11_12() {
		return distIn_11_12;
	}

	public void setDistIn_11_12(Double distIn_11_12) {
		this.distIn_11_12 = distIn_11_12;
	}

	public Double getDistIn_12_1() {
		return distIn_12_1;
	}

	public void setDistIn_12_1(Double distIn_12_1) {
		this.distIn_12_1 = distIn_12_1;
	}

	public Double getDistIn_1_2() {
		return distIn_1_2;
	}

	public void setDistIn_1_2(Double distIn_1_2) {
		this.distIn_1_2 = distIn_1_2;
	}

	public Double getDistIn_3_4() {
		return distIn_3_4;
	}

	public void setDistIn_3_4(Double distIn_3_4) {
		this.distIn_3_4 = distIn_3_4;
	}

	public Double getDistIn_4_5() {
		return distIn_4_5;
	}

	public void setDistIn_4_5(Double distIn_4_5) {
		this.distIn_4_5 = distIn_4_5;
	}

	public Double getDistIn_5_6() {
		return distIn_5_6;
	}

	public void setDistIn_5_6(Double distIn_5_6) {
		this.distIn_5_6 = distIn_5_6;
	}

	public Double getDistIn_2_3() {
		return distIn_2_3;
	}

	public void setDistIn_2_3(Double distIn_2_3) {
		this.distIn_2_3 = distIn_2_3;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}


	public Long getTimeIn_8_9() {
		return timeIn_8_9;
	}

	public void setTimeIn_8_9(Long timeIn_8_9) {
		this.timeIn_8_9 = timeIn_8_9;
	}

	public Long getDistIn_8_9() {
		return distIn_8_9;
	}

	public void setDistIn_8_9(Long distIn_8_9) {
		this.distIn_8_9 = distIn_8_9;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}


}
