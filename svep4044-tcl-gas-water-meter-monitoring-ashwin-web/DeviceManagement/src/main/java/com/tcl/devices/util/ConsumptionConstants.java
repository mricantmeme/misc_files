package com.tcl.devices.util;

public class ConsumptionConstants {
	
	public final static String CONSUMPTION_DEFAULT_QUERY="{$and: [%s,{\"is_active\":true}]}.sort(%s)";
	public final static String CONSUMPTION_DEFAULT_FILTER_QUERY="{$and: [%s,{\"is_active\":true}]}.sort({\"_id\":-1})";
	public final static String CONSUMPTION_DEFAULT_SORT_QUERY="{$and:[{\"is_active\":true}]}.sort(%s)";

	public final static String CONSUMPTION_DEFAULT_ACTIVE_QUERY="{$and:[{\"is_active\":true}]}.sort({\"_id\":-1})";

	public final static String CONSUMPTION_LIST = "List of consumptions";
	public final static String CONSUMPTION_NOT_FOUND="Consumption not found";
	public final static String CONSUMPTION_UPDATE_SUCCESSFULL="Consumption updated successfully";

}
