/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.customer.util;

/*
 * This class contains some generic and non generic responses that can be used in other classes and packages to define strings.
 * Whenever we need to change these default values, all we need to do is, change it here.
 */

public class CustomerConstants {

	public final static String CUSTOMER_CREATED_SUCCESSFULLY = "Customer created successfully";
	public final static String CUSTOMER_UPDATED_SUCCESSFULLY = "Customer updated successfully";
	public final static String CUSTOMER_NOT_FOUND = "Customer not found";
	public final static String CUSTOMER_DELETED_SUCCESSFULLY = "Customer deleted successfully";
	public final static String CUSTOMER_LIST = "List of customers";
	public final static String USER_NOT_FOUND = "User not found";
	public final static String USER_EMAIL_ID_ALREADY_EXISTS = "User Email Id already exists";
	public final static String USER_NAME_ALREADY_EXISTS = "User name already exists";
	public final static String CUSTOMER_ROLE = "CUSTOMER";
	public final static String UTILITY_NOT_FOUND = "Utility not found";
	public final static String CUSTOMER_DETAILS = "Customer details";
	public final static Long CUSTOMER_STARTING_ID = 00001L;
	public final static String CUSTOMER_ADMIN_ROLE_ID = "2002";
	public final static Character CUSTOMER_USER_STARTING = 'U';
	public final static Long CUSTOMER_USER_STARTING_ID = 00001L;
	public final static String PASSWORD_CONFIRMATION = "Password is not match, please enter the correct password";

	public final static Character CUSTOMER_STARTING = 'G';
	public final static String CUSTOMER_STATUS = "Customer status updated successfully";
	public final static String CUSTOMER_UTILITY_TYPE = "Customer utility type";
	public final static String CUSTOMER_DEFAULT_QUERY="{$and: [%s,{\"is_active\":true}]}.sort(%s)";
	public final static String CUSTOMER_DEFAULT_FILTER_QUERY="{$and: [%s,{\"is_active\":true}]}.sort({\"_id\":-1})";
	public final static String CUSTOMER_DEFAULT_SORT_QUERY="{$and:[{\"is_active\":true}]}.sort(%s)";

	public final static String CUSTOMER_DEFAULT_ACTIVE_QUERY="{$and:[{\"is_active\":true}]}.sort({\"_id\":-1})";

}
