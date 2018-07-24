/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.customer.dto;

import javax.validation.constraints.NotNull;

import com.tcl.customer.model.Address;
import com.tcl.customer.model.AlertColour;

import lombok.Data;

@Data
public class CustomerUpdateReqDto {

	@NotNull(message = "CustomerId cannot be empty.")
	private String customerId;

	@NotNull(message = "Customer name cannot be empty.")
	private String customerName;

	private String customerDescription;

	private String customerEmailId;

	@NotNull(message = "Customer utility cannot be empty.")
	private String customerUtilityId;

	private String customerGroupEmailId;

	@NotNull(message = "Customer mobile number cannot be empty.")
	private String customerMobileNumber;

	private String customerPhoneNumber;

	private String customerLogo;

	private Boolean customerStatus;

	private AlertColour customerColourConfig;

	@NotNull(message = "Address cannot be empty.")
	private Address address;


	private Boolean businessType;

	private String billingType;

}
