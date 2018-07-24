/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.property.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PropertyStatusReqDto {

	@NotNull(message = "PropertyId cannot be empty.")
	private String propertyId;

	private String userId;

	@NotNull(message = "Property status cannot be empty.")
	private Boolean propertyStatus;

}
