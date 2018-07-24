/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.property.dto;

import com.tcl.property.model.Address;

import lombok.Data;

@Data
public class PropertyResponseDto {

	private String propertyId;
	private String propertyType;
	private Address address;
	private String ownerName;
	private String ownerEmailId;
	private String mobileNumber;
	private String phoneNumber;
	private boolean propertyStatus;
	private String createdBy;

}
