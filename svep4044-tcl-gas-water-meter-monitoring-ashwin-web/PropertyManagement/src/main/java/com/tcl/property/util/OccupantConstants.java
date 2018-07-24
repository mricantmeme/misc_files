package com.tcl.property.util;

public class OccupantConstants {

	
	public final static String OCCUPANT_CREATED_SUCCESSFULLY = "Occupant created successfully";
	public final static String OCCUPANT_UPDATED_SUCCESSFULLY = "Occupant updated successfully";
	public final static String OCCUPANT_NOT_FOUND = "Occupant not found";
	public final static String OCCUPANT_DELETED_SUCCESSFULLY = "Occupant deleted successfully";
	public final static String OCCUPANT_List = "List of Occupants";
	public final static String OCCUPANT_AlREADY_EXISTS = "Occupant already exists";
	public final static String OCCUPANT_DETAILS = "Occupant Details";
	public final static String MASTER_DATA_LIST = "List of master data";
	public final static Character OCCUPANT_STARTING='O';
	public final static Long OCCUPANT_STARTING_ID = 00001L;
	public final static String REGISTER_TYPE = "OCCUPANT";
	public final static String  OCCUPANT_STATUS="Occupant status updated successfully";
	public final static String OCCUPANT_DEFAULT_QUERY="{$and: [%s,{\"is_active\":true}]}.sort(%s)";
	public final static String OCCUPANT_DEFAULT_FILTER_QUERY="{$and: [%s,{\"is_active\":true}]}.sort({\"_id\":-1})";
	public final static String OCCUPANT_DEFAULT_SORT_QUERY="{$and:[%s{\"is_active\":true}]}.sort(%s)";

	public final static String OCCUPANT_DEFAULT_ACTIVE_QUERY="{$and:[%s{\"is_active\":true}]}.sort({\"_id\":-1})";



}
