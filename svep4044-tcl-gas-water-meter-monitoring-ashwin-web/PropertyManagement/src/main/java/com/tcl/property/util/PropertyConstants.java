package com.tcl.property.util;

public class PropertyConstants {

	
	public final static String PROPERTY_CREATED_SUCCESSFULLY = "Property created successfully";
	public final static String PROPERTY_UPDATED_SUCCESSFULLY = "Property updated successfully";
	public final static String PROPERTY_NOT_FOUND = "Property not found";
	public final static String PROPERTY_DELETED_SUCCESSFULLY = "Property deleted successfully";
	public final static String PROPERTY_List = "List of Properties";
	public final static String PROPERTY_AlREADY_EXISTS = "Property already exists";
	public final static String PROPERTY_DETAILS = "Property Details";
	public final static String MASTER_DATA_LIST = "List of master data";
	public final static Character PROPERTY_STARTING='P';
	public final static Long PROPERTY_STARTING_ID = 00001L;
	public final static String REGISTER_TYPE = "PROPERTY";
	public final static String  PROPERTY_STATUS="Property status updated successfully";

	public final static String PROPERTY_DEFAULT_QUERY="{$and: [%s,{\"is_active\":true}]}.sort(%s)";
	public final static String PROPERTY_DEFAULT_FILTER_QUERY="{$and: [%s,{\"is_active\":true}]}.sort({\"_id\":-1})";
	public final static String PROPERTY_DEFAULT_SORT_QUERY="{$and:[%s{\"is_active\":true}]}.sort(%s)";

	public final static String PROPERTY_DEFAULT_ACTIVE_QUERY="{$and:[%s{\"is_active\":true}]}.sort({\"_id\":-1})";


}
