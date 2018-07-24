/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.dto;

import com.tcl.site.model.Address;

import lombok.Data;

@Data
public class SiteResponseDto {

	private String siteId;

	private String siteName;

	private String siteDescription;

	private String emailId;

	private String siteUtilityId;

	private String siteMobileNumber;

	private String siteLogo;

	private Boolean siteStatus;

	private Address address;

	private String customerId;

	private String phoneNumber;
	private String zoneName;

}
