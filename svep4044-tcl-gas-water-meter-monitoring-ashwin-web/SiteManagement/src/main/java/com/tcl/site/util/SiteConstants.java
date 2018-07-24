/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.util;

public class SiteConstants {

	public final static String SITE_CREATED_SUCCESSFULLY = "Site created successfully";
	public final static String SITE_UPDATED_SUCCESSFULLY = "Site updated successfully";
	public final static String SITE_NOT_FOUND = "Site not found";
	public final static String SITE_DELETED_SUCCESSFULLY = "Site deleted successfully";
	public final static String SITE_List = "List of sites";
	public final static String USER_NOT_FOUND = "User not found";
	public final static String USER_EMAIL_ID_AlREADY_EXISTS = "User Email Id already exists";

	public final static String USER_NAME_AlREADY_EXISTS = "User name already exists";
	public final static String SITE_STATUS = "Site status updated successfully";

	public final static String SITE_ROLE = "2003";
	public final static String SITE_DETAILS = "Site Details";
	public final static String SITE_ADMIN_ROLE_ID = "2003";

	public final static String UTILITY_NOT_FOUND = "Utility not found";

	public final static Long SITE_STARTING_ID = 00001L;

	public final static String SITE_STARTING_KEY = "S";

	public final static Character SITE_STARTING = 'G';

	public final static Character SITE_USER_STARTING = 'U';
	public final static Long SITE_USER_STARTING_ID = 00001L;
	public final static String PASSWORD_CONFIRMATION = "Password is not match, please enter the correct password";

	public final static String SITE_DEFAULT_QUERY="{$and: [%s,{\"is_active\":true}]}.sort(%s)";
	public final static String SITE_DEFAULT_FILTER_QUERY="{$and: [%s,{\"is_active\":true}]}.sort({\"_id\":-1})";
	public final static String SITE_DEFAULT_SORT_QUERY="{$and:[%s{\"is_active\":true}]}.sort(%s)";

	public final static String SITE_DEFAULT_ACTIVE_QUERY="{$and:[%s{\"is_active\":true}]}.sort({\"_id\":-1})";

	
}
