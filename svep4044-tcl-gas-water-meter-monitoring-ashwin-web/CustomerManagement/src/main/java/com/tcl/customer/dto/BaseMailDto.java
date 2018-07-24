/*
 * @category CustomerManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */
package com.tcl.customer.dto;

import com.tcl.customer.util.EmailTemplateType;

import lombok.Data;

@Data
public class BaseMailDto {

	private String toAddress;

	private String ccAddress;

	private String subject;

	private EmailTemplateType emailTemplateType;

}
