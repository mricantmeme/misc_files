package com.dicv.truck.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

/**
 * This class is responsible to copy scheduled dispatch table data and persist
 * to dispatch table.
 * 
 * @author aut7kor
 * 
 */
@Entity
@Table(name = "DISPATCH")
public class Dispatch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DISPATCH_DISPATCHID_GENERATOR", sequenceName = "DISPATCH_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISPATCH_DISPATCHID_GENERATOR")
	@Column(name = "DISPATCH_ID")
	private Integer dispatchId;

	@Column(name = "DATE_TIME")
	private Timestamp dateTime;

	@Column(name = "DISPATCH_STATUS")
	private String dispatchStatus;

	@Column(name = "DISPATCH_TYPE")
	private String dispatchType;

	@Column(name = "FROM_ADDRESS")
	private String fromAddress;

	@Column(name = "GOODS_WEIGHT")
	private Double goodsWeight;

	private Double latitude;

	private Double longitude;

	@Column(name = "TO_ADDRESS")
	private String toAddress;

	@Column(name = "IS_DELETED")
	private Integer isDeleted;

	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDateTime;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "TRIP_ID")
	private Trip trip;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "dispatch")
	@Where(clause = "IS_DELETED=0")
	private List<InvoicePhoto> invoicePhotos;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CREATED_BY")
	private DicvUser dicvUserCreatedBy;

	@Column(name = "UPDATED_BY")
	private Integer dicvUserUpdatedBy;

	public Dispatch() {
	}

	public Integer getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(Integer dispatchId) {
		this.dispatchId = dispatchId;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public String getDispatchStatus() {
		return dispatchStatus;
	}

	public void setDispatchStatus(String dispatchStatus) {
		this.dispatchStatus = dispatchStatus;
	}

	public String getDispatchType() {
		return dispatchType;
	}

	public void setDispatchType(String dispatchType) {
		this.dispatchType = dispatchType;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public Double getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(Double goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public List<InvoicePhoto> getInvoicePhotos() {
		return invoicePhotos;
	}

	public void setInvoicePhotos(List<InvoicePhoto> invoicePhotos) {
		this.invoicePhotos = invoicePhotos;
	}

	public DicvUser getDicvUserCreatedBy() {
		return dicvUserCreatedBy;
	}

	public void setDicvUserCreatedBy(DicvUser dicvUserCreatedBy) {
		this.dicvUserCreatedBy = dicvUserCreatedBy;
	}

	public Integer getDicvUserUpdatedBy() {
		return dicvUserUpdatedBy;
	}

	public void setDicvUserUpdatedBy(Integer dicvUserUpdatedBy) {
		this.dicvUserUpdatedBy = dicvUserUpdatedBy;
	}

}