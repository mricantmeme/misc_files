/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.property.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OccupantStatusRequestDto {

	@NotNull(message = "OccupantId cannot be empty.")
	private String occupantId;

	// @NotNull(message = "User Id cannot be empty.")
	private String userId;

	@NotNull(message = "Occupant status cannot be empty.")
	private Boolean occupantStatus;

}
