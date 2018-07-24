/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.property.dto;

import javax.validation.constraints.NotNull;

import com.tcl.property.model.Address;

import lombok.Data;

@Data
public class PropertyRegRequestDto {

	// @NotNull(message="User id can not be empty")
	private String userId;

	private String propertyId;

	@NotNull(message = "property type can not be empty")
	private String propertyType;

	@NotNull(message = "address can not be null")
	private Address address;

	@NotNull(message = "owner name can not be null")
	private String ownerName;

	@NotNull(message = "owner email id can not be null")
	private String ownerEmailId;

	@NotNull(message = "owner mobile no can not be null")
	private String mobileNumber;

	private String phoneNumber;

}
