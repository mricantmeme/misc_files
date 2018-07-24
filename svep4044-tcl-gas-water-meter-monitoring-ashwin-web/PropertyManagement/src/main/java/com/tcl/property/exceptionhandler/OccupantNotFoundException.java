/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.property.exceptionhandler;

/**
 * @author Contus
 */

public class OccupantNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OccupantNotFoundException(String message) {
		super(message);
	}
}
