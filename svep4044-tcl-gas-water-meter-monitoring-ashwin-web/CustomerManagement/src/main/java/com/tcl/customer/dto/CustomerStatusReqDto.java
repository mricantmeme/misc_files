/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.customer.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CustomerStatusReqDto {

	@NotNull(message = "CustomerId cannot be empty.")
	private String customerId;

	private String userId;

	@NotNull(message = "Customer status cannot be empty.")
	private Boolean customerStatus;

}
