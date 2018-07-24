/*
 * @category DeviceManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.devices.util;

/*
 * This is a generic class, that acts as a helper for the method, that creates a new device.
 * It is used to define a new device's ID.
 * It generates an Id and prefixes it with a predefined prefix. 
 */
public class Helper {
	private Helper() {
	}

	public static String generateNewDeviceEui() {
		return DeviceConstants.FIRST_DEVICE_ID;
	}

	public static String generateNextDeviceEui(String deviceId) {

		deviceId = deviceId.substring(3);
		Long uniqueId = Long.valueOf(deviceId);
		uniqueId++;
		deviceId = String.format("%08d", uniqueId);
		return DeviceConstants.DEVICE_PREFIX + deviceId;
	}

}
