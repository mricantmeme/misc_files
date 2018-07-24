/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.property.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class Address {

	@NotNull(message = "Address can not be empty")
	@Field("address1")
	private String addressLine1;

	@Field("address2")
	private String addressLine2;

	@NotNull(message = "location can not be empty")
	@Field("location")
	private String location;

	@NotNull(message = "city can not be empty")
	@Field("city")
	private String city;

	@NotNull(message = "state can not be empty")
	@Field("state")
	private String state;

	@NotNull(message = "country can not be empty")
	@Field("country")
	private String country;

	@NotNull(message = "pincode can not be empty")
	@Field("postal_code")
	private String pinCode;
}
