/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.model;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class AlertColour {

	@Field("alert")
	private String alert;

	@Field("threshold")
	private String threshold;

	@Field("normal")
	private String normal;
}
