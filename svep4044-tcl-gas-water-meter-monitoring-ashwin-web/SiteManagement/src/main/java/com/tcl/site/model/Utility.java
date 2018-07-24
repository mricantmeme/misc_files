/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.model;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Document(collection = "utility")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Utility {

	private static final long serialVersionUID = -4240718002259642160L;

	@Id
	@Field("utility_id")
	@Pattern(regexp = ".+@.+\\.[a-z]+")
	private String utilityId;

	@Field("utility_name")
	private String utilityName;

	@Field("is_active")
	private boolean isActive = true;

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

}
