/*
 * @category CustomerManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */
package com.tcl.customer.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CustomerDeleteReqDto {

	@NotNull(message = "CustomerId cannot be empty.")
	private List<String> customerId;

}
