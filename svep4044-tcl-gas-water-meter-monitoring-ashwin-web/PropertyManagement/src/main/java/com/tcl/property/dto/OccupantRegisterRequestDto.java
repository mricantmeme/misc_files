/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.property.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class OccupantRegisterRequestDto {

	private String userId;

	@NotNull(message = "Property id cannot be empty")
	private String propertyId;

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

	private String occupantStatus;

	private String phoneNumber;

	private Date startDate;

}
