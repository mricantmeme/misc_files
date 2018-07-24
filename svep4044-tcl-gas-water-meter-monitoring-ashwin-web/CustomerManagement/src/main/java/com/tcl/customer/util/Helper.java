/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.customer.util;

/*
 * This is a generic class, that acts as a helper for the method, that creates a new customer.
 * It is used to define a new customer's ID.
 * It generates an Id and prefixes it with a predefined prefix. 
 */
public class Helper {

	public static String generateUniqueCustomerId(String utilityName) {
		Long customerId = CustomerConstants.CUSTOMER_STARTING_ID;
		String uniqueCustomerId = utilityName.substring(0, 1);
		return uniqueCustomerId + "-" + String.format("%05d", customerId);

	}

	public static String generateNextCustomerId(String customerId, String utilityName) {
		customerId = customerId.substring(2);
		Long uniqueId = Long.valueOf(customerId);
		uniqueId++;
		utilityName = utilityName.substring(0, 1);

		return utilityName + "-" + String.format("%05d", uniqueId);
	}

	public static String generateNextUserId(String userId) {
		userId = userId.substring(1);
		Long uniqueId = Long.valueOf(userId);
		uniqueId++;
		return CustomerConstants.CUSTOMER_USER_STARTING + String.format("%05d", uniqueId);

	}

	public static String generateUniqueUserId(Character customerUserStarting) {
		Long customerId = CustomerConstants.CUSTOMER_USER_STARTING_ID;
		return CustomerConstants.CUSTOMER_USER_STARTING + String.format("%05d", customerId);
	}

}
