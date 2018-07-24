/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.property.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Document(collection = "property")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Property {

	private static final long serialVersionUID = -4240718002259642160L;

	@Id
	@Field("property_id")
	private String propertyId;

	@Field("propertytype_id")
	private String propertyType;

	@Field("address")
	private Address address;

	@Field("property_status")
	private Boolean propertyStatus;

	@Field("owner_name")
	private String ownerName;

	@Field("owner_email_id")
	private String ownerEmailId;

	@Field("owner_mobile_number")
	private String mobileNumber;

	@Field("owner_phone_number")
	private String phoneNumber;

	@Field("is_active")
	private Boolean isActive;

	@Field("created_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdDate;

	@Field("modified_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date modifiedDate;

	@Field("created_by")
	private String createdBy;

	@Field("modified_by")
	private String modifiedBy;

}
