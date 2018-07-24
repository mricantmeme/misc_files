/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.property.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Document(collection = "occupant")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Occupant {
	private static final long serialVersionUID = -4240718002259642160L;

	@Id
	@Field("occupant_id")
	private String occupantId;

	@Field("property_Id")
	private String propertyId;

	@Field("first_name")
	private String firstName;

	@Field("middle_name")
	private String middleName;

	@Field("last_name")
	private String lastName;

	@Field("occupant_mobile")
	private String mobileNumber;

	@Field("occupant_emailId")
	private String occupantEmailId;

	@Field("occupant_phone")
	private String phoneNumber;

	@Field("is_active")
	private boolean isActive = true;

	@Field("occupant_status")
	private boolean occupantStatus;

	@Field("start_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date startDate;

	@Field("end_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date endDate;

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
