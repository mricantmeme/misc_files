/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.property.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OccupantResponseDto {

	private String propertyId;
	private String occupantId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobileNumber;
	private String occupantEmailId;
	private String phoneNumber;
	private Date startDate;
	private Date endDate;
	private boolean occupantStatus;
	private boolean isActive;

}
