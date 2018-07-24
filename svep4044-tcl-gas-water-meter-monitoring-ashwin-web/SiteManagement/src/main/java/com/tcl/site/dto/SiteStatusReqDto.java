/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SiteStatusReqDto {

	@NotNull(message = "SiteId cannot be empty.")
	private String siteId;

	// @NotNull(message = "User Id cannot be empty.")
	private String userId;

	@NotNull(message = "Site status cannot be empty.")
	private Boolean siteStatus;

}
