/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Document(collection = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class User {

	private static final long serialVersionUID = -4240718002259642160L;
	@Id
	@Field("user_id")
	@Pattern(regexp = ".+@.+\\.[a-z]+")
	private String userId;

	@Field("user_name")
	private String userName;

	@Field("password")
	private String password;

	@Field("password_hash")
	private String passwordHash;

	@Field("is_active")
	private Boolean isActive;

	@Field("first_name")
	private String firstName;

	@Field("middle_name")
	private String middleName;

	@Field("last_name")
	private String lastName;

	@Field("phone_number")
	private String mobileNumber;

	@Field("email_id")
	@Pattern(regexp = ".+@.+\\.[a-z]+")
	private String emailId;

	@Field("customer")
	@DBRef
	private Customer customer;

	@Field("site")
	@DBRef
	private Site site;

	@Field("role_master")
	@DBRef
	private RoleMaster roleMaster;


	@Field("user_status")
	private Boolean userStatus;

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
	
	@Field("lock_status")
	private Boolean lockStatus;

	@Field("login_attempt")
	private Long loginAttempt;

	@Field("last_login_time")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date lastLoginTime;
}
