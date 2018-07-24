/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.customer.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.tcl.customer.model.Address;
import com.tcl.customer.model.AlertColour;

import lombok.Data;

@Data
public class CustomerRegRequestDto {

	private String userId;

	private String customerId;

	@NotNull(message = "Customer name cannot be empty.")
	private String customerName;

	@NotNull(message = "Customer username cannot be empty.")
	private String customerUserName;

	private String customerDescription;

	@NotNull(message = "Customer email id cannot be empty.")
	@Email
	private String customerEmailId;

	@NotNull(message = "Customer utility cannot be empty.")
	private String customerUtilityId;

	private String customerGroupEmailId;

	private Boolean businessType;

	private String billingType;

	@NotNull(message = "Customer mobile number cannot be empty.")
	private String customerMobileNumber;

	private String customerPhoneNumber;

	@NotNull(message = "Password cannot be empty.")
	@Size(min = 6, max = 20, message = "Please ensure the password has at least {min} characters, with at least one uppercase, one numeric and one special characters")
	private String password;

	@NotNull(message = "confirm password cannot be empty.")
	@Size(min = 6, max = 20, message = "Please ensure the confirm password has at least {min} characters, with at least one uppercase, one numeric and one special characters")
	private String confirmPassword;

	private String customerLogo;

	private Boolean customerStatus;

	private AlertColour customerColourConfig;

	@NotNull(message = "Address cannot be empty.")
	private Address address;
	
	private String roleId;

}
