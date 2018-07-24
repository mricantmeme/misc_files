/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.exceptionhandler;

public class BadDataException extends RuntimeException {
	public BadDataException(String message) {
		super(message);
	}
}
