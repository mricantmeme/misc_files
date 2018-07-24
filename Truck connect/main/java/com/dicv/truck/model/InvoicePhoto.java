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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The class is responsible to persist invoice image for pickup and delivery.
 * 
 * @author aut7kor
 * 
 */
@Entity
@Table(name = "INVOICE_PHOTO")
public class InvoicePhoto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "INVOICEPHOTO_SEQ", sequenceName = "INVOICEPHOTO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVOICEPHOTO_SEQ")
	@Column(name = "INVOICE_PHOTO_ID")
	private Integer invoicePhotoId;

	@Lob
	@Column(name = "INVOICE_PHOTO")
	private byte[] invoicePhoto;

	@Column(name = "INVOICE_TYPE")
	private String invoiceType;

	@Column(name = "UPDATE_DATE_TIME")
	private Timestamp updateDateTime;

	@Column(name = "IS_DELETED")
	private int isDeleted;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "DISPATCH_ID")
	private Dispatch dispatch;

	@Column(name = "CREATED_BY")
	private Integer dicvUserCreatedBy;

	@Column(name = "UPDATED_BY")
	private Integer dicvUserUpdatedBy;
	
	public InvoicePhoto() {
	}

	public Integer getInvoicePhotoId() {
		return this.invoicePhotoId;
	}

	public void setInvoicePhotoId(Integer invoicePhotoId) {
		this.invoicePhotoId = invoicePhotoId;
	}

	public byte[] getInvoicePhoto() {
		return this.invoicePhoto;
	}

	public void setInvoicePhoto(byte[] invoicePhoto) {
		this.invoicePhoto = invoicePhoto;
	}

	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public Timestamp getUpdateDateTime() {
		return this.updateDateTime;
	}

	public void setUpdateDateTime(Timestamp updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public Dispatch getDispatch() {
		return this.dispatch;
	}

	public void setDispatch(Dispatch dispatch) {
		this.dispatch = dispatch;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getDicvUserUpdatedBy() {
		return dicvUserUpdatedBy;
	}

	public void setDicvUserUpdatedBy(Integer dicvUserUpdatedBy) {
		this.dicvUserUpdatedBy = dicvUserUpdatedBy;
	}

	public Integer getDicvUserCreatedBy() {
		return dicvUserCreatedBy;
	}

	public void setDicvUserCreatedBy(Integer dicvUserCreatedBy) {
		this.dicvUserCreatedBy = dicvUserCreatedBy;
	}

}