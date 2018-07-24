/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.customer.dto;

import com.tcl.customer.model.Address;
import com.tcl.customer.model.AlertColour;

import lombok.Data;

@Data
public class CustomerResponseDto {
	private String customerId;
	private String customerName;
	private String customerDescription;
	private String customerEmailId;
	private String customerUtilityId;
	private String customerGroupEmailId;
	private String customerMobileNumber;
	private String customerLogo;
	private Boolean customerStatus;
	private AlertColour customerColourConfig;
	private Address address;

	private Boolean businessType;

	private String billingType;
	private String customerPhoneNumber;

}
