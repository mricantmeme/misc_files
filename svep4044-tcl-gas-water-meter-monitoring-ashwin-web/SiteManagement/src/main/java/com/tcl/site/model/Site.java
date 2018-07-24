/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.model;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Document(collection = "site")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Site {

	private static final long serialVersionUID = -4240718002259642160L;

	@Id
	@Field("site_id")
	@Pattern(regexp = ".+@.+\\.[a-z]+")
	private String siteId;

	@Field("site_name")
	private String siteName;

	@Field("site_description")
	private String siteDescription;

	@Field("site_logo")
	private String siteLogo;

	@Field("email_id")
	@Pattern(regexp = ".+@.+\\.[a-z]+")
	private String emailId;

	@Field("phone_number")
	private String mobileNumber;

	@Field("mobile_number")
	private String phoneNumber;

	@Field("zone_name")
	private String zoneName;

	@Field("address")
	private Address address;

	@Field("is_active")
	private Boolean isActive;

	@Field("site_status")
	private Boolean siteStatus;

	@Field("created_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdDate;

	@Field("modified_date")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date modifiedDate;

	@Field("created_by")
	private String createdBy;

	@Field("modified_by")
	private String modifiedBy;

	@Field("customer")
	@DBRef
	private Customer customer;

	@Field("utility_id")
	private String siteUtilityId;

}
