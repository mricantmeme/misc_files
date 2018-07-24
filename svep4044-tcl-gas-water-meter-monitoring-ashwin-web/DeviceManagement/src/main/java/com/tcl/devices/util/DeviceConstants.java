/*
 * @category DeviceManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.devices.util;

/*
 * This class contains some generic and non generic responses that can be used in other classes and packages to define strings.
 * Whenever we need to change these default values, all we need to do is, change it here.
 */
public class DeviceConstants {

	private DeviceConstants() {

	}

	public static final String FIRST_DEVICE_ID = "DV-00000001";

	public static final String DEVICE_ADDED_SUCCESSFULY = "Device added successfully.";
	public static final String DEVICE_ALREADY_PRESENT = "Device already present.";

	public static final String DEVICE_UPDATED_SUCCESSFULLY = "Device updated successfully.";

	public static final String INVALID_DEVICE_ID = "Device not found ";

	public static final String DEVICE_NOT_FOUND = "Device details not found.";

	public static final String DEVICE_LIST_RESPONSE = "List of devices.";

	public static final String DEVICE_DELETED_SUCCESSFULLY = "Device deletion successfull.";

	public static final String DEVICE_DETAILS = "Device details.";

	public static final String DEVICE_PREFIX = "DV-";

	public static final String DEVICE_STATUS_RESPONSE = "Master data for device status";

	public static final String DEVICE_ADD_STATUS_RESPONSE = "Status added successfully";

	public static final String DEVICE_ASSIGN_RESPONSE = "Device(s) assigned to user successfully";

	public static final String INVALID_DEVICE_STATUSID = "Invalid Device status";

	public static final String DEVICE_HISTORY_RESPONSE = "Device History";

}
