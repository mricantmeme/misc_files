/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.dto;

import javax.validation.constraints.NotNull;
import com.tcl.site.model.Address;

import lombok.Data;

@Data
public class SiteUpdateRequestDto {

	@NotNull(message = "Site id cannot be empty.")
	private String siteId;

	@NotNull(message = "Site name cannot be empty.")
	private String siteName;

	@NotNull(message = "Customer Id cannot be empty.")
	private String customerId;

	private String siteDescription;

	private String emailId;

	@NotNull(message = "Site utility cannot be empty.")
	private String siteUtilityId;

	@NotNull(message = "Site mobile number cannot be empty.")
	private String siteMobileNumber;

	private String siteLogo;

	private Boolean siteStatus;

	@NotNull(message = "Address cannot be empty.")
	private Address address;


	private String phoneNumber;
	private String zoneName;
	

}
