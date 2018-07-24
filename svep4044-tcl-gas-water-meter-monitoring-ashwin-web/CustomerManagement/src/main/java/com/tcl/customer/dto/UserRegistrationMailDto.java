/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.customer.dto;

import lombok.Data;

@Data
public class UserRegistrationMailDto extends BaseMailDto {
	private String firstName;

	private String userName;

	private String password;

}
