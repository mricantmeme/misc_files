/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.property.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class OccupantUpdateReqestDto {

	// @NotNull(message="User id can not be empty")
	private String userId;

	private String propertyId;

	@NotNull(message = "Occupant Id can not be empty")
	private String occupantId;

	@NotNull(message = "Occupant name can not be empty")
	private String firstName;

	@NotNull(message = "Occupant name can not be empty")
	private String middleName;

	@NotNull(message = "Occupant name can not be empty")
	private String lastName;

	@NotNull(message = "Occupant mobile number can not be empty")
	private String mobileNumber;

	@NotNull(message = "Occupant emailId can not be empty")
	private String occupantEmailId;

	private String phoneNumber;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date startDate;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date endDate;

	private Boolean occupantStatus;

	private String createdBy;

	private Date createdDate;

}
