package com.tcl.property.util;


public class Helper {

	public static String generateNextPropertyId(String propertyId) {
		propertyId=propertyId.substring(1);
		Long uniqueId=Long.valueOf(propertyId);
		uniqueId++;
		return PropertyConstants.PROPERTY_STARTING+String.format("%05d", uniqueId);

	}

	public static String generateUniquePropertyId(Character propertyStarting) {
		Long propertyId=PropertyConstants.PROPERTY_STARTING_ID;
		return PropertyConstants.PROPERTY_STARTING+String.format("%05d", propertyId);
	}
	public static String generateNextOccupantId(String occupantId) {
		occupantId=occupantId.substring(1);
		Long uniqueId=Long.valueOf(occupantId);
		uniqueId++;
		return OccupantConstants.OCCUPANT_STARTING+String.format("%05d", uniqueId);

	}

	public static String generateUniqueOccupantId(Character occupantStarting) {
		Long occupantId=OccupantConstants.OCCUPANT_STARTING_ID;
		return OccupantConstants.OCCUPANT_STARTING+String.format("%05d", occupantId);
	}

}
