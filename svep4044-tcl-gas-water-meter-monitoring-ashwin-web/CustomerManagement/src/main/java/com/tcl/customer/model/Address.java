/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.customer.model;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class Address {

	@Field("address1")
	private String addressLine1;

	@Field("address2")
	private String addressLine2;

	@Field("city")
	private String customerCity;

	@Field("state")
	private String customerState;

	@Field("country")
	private String customerCountry;

	@Field("pin_code")
	private String customerPinCode;

}
