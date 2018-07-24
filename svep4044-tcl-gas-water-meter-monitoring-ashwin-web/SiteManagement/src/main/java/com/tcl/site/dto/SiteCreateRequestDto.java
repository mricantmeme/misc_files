/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.tcl.site.model.Address;

import lombok.Data;

@Data
public class SiteCreateRequestDto {

	private String userId;

	@NotNull(message = "Customer id cannot be empty.")
	private String customerId;

	private String siteId;

	@NotNull(message = "Site name cannot be empty.")
	private String siteName;

	@NotNull(message = "Site username cannot be empty.")
	private String siteUserName;

	private String siteDescription;

	@NotNull(message = "Site email id cannot be empty.")
	@Email
	private String emailId;

	@NotNull(message = "Site utility cannot be empty.")
	private String siteUtilityId;

	@NotNull(message = "Site mobile number cannot be empty.")
	private String siteMobileNumber;

	private String phoneNumber;
	private String zoneName;

	@NotNull(message = "Password cannot be empty.")
	@Size(min = 8, max = 15, message = "Password length must be between {min} and {max}")
	private String password;

	@NotNull(message = "confirm password cannot be empty.")
	@Size(min = 8, max = 15, message = "Confirm password length must be between {min} and {max}")
	private String confirmPassword;

	private String siteLogo;

	private Boolean siteStatus;

	@NotNull(message = "Address cannot be empty.")
	private Address address;
	
	private String roleId;

}
