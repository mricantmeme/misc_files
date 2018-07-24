/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.property.dto;

import lombok.Data;

@Data
public class OccupantListRequestDto {

	private String propertyId;

	private String search;

	private int page;

	private int size;

}
