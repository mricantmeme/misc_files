/**
 * This class is responsible to keep as constant(public static final) variable.
 */
package com.dicv.truck.utility;

public final class DicvConstants {

	public static final String AUTH_TOKEN_STRING = "Auth-Token";

	/**
	 * Truck status as AVAILABLE ONLINE
	 */
	public static final String AVAILABLE_ONLINE = "AVAILABLE_ONLINE";
	/**
	 * Truck status as NOT AVAILABLE ONLINE
	 */
	public static final String NOTAVAILABLE_ONLINE = "NOTAVAILABLE_ONLINE";
	/**
	 * Truck status as AVAILABLE OFFLINE
	 */
	public static final String AVAILABLE_OFFLINE = "AVAILABLE_OFFLINE";
	/**
	 * Truck status as NOT AVAILABLE OFFLINE
	 */
	public static final String NOTAVAILABLE_OFFLINE = "NOTAVAILABLE_OFFLINE";
	/**
	 * Truck status as IN TRIP
	 */
	public static final String IN_TRIP = "IN_TRIP";
	/**
	 * Truck status as NOT IN TRIP
	 */
	public static final String NOT_IN_TRIP = "NOT_IN_TRIP";
	/**
	 * Truck variant as MDT
	 */
	public static final String VEHICLET_VARIANT_MDT = "MDT";
	/**
	 * Truck variant as HDT
	 */
	public static final String VEHICLE_VARIANT_HDT = "HDT";
	/**
	 * Truck variant as THUNDERBOLT
	 */
	public static final String VEHICLE_THUNDERBOLT = "Thunderbolt";
	/**
	 * Header key as Authorization
	 */
	public static final String HEADER_AUTHORIZATION = "Authorization";
	/**
	 * Header value with name Bearer
	 */
	public static final String AUTHORIZATION_BEARER = "Bearer";

	// public static final String SIGNKEY = "rBjk+WZF1uScCJK2gO3shQ==";
	/**
	 * Generating token using SIGNKEY
	 */
	public static final String SIGNKEY = "0MCZaqj6J5/a2DIMKdPwig==";
	/**
	 * Token validation message as INVALID_TOKEN
	 */
	public static final String INVALID_TOKEN = "Unauthorized request. Please login";
	/**
	 * Token validation message as INVALID_TOKEN
	 */
	public static final String EXPIRED_TOKEN = "Session has expired.Please login again";
	/**
	 * Token validation message as EXPIRED_SESSION
	 */
	public static final String EXPIRED_SESSION = "Session has expired.Please login again";
	/**
	 * Token validation message as CUSTOMBEARERTOKEN_FAIL
	 */
	public static final String CUSTOMBEARERTOKEN_FAIL = "CustomBearer Token Authentication Failed";
	/**
	 * User credential validation message as UNAUTHENTICATED_USER
	 */
	public static final String UNAUTHENTICATED_USER = "You are not logged in yet.Please login";
	/**
	 * User credential validation message as UNAUTHORIZED_USER
	 */
	public static final String UNAUTHORIZED_USER = "User not authorized to create geofence";
	/**
	 * User credential validation message as INVALID_CREDENTIALS
	 */
	public static final String INVALID_CREDENTIALS = "Enter valid credentials";
	/**
	 * User credential validation message as ANANYMOUS_USER
	 */
	public static final String ANANYMOUS_USER = "anonymousUser";
	/**
	 * User logout message as LOGOUT_SUCCESS_MSG
	 */
	public static final String LOGOUT_SUCCESS_MSG = "You have been logged out successfully from DICVAPP";
	/**
	 * Data validation message as INVALID_DATA
	 */
	public static final String INVALID_DATA = "Vehicle id does not exist.";
	/**
	 * Trip status as TRIPSTATUS_RUNNING
	 */
	public static final String TRIPSTATUS_RUNNING = "RUNNING";
	/**
	 * Trip status as TRIPSTATUS_PLANNED
	 */
	public static final String TRIPSTATUS_PLANNED = "PLANNED";
	/**
	 * Trip status as TRIPSTATUS_COMPLETED
	 */
	public static final String TRIPSTATUS_COMPLETED = "COMPLETED";
	/**
	 * Driver status as DRIVERSTATUS_AVAILABLE
	 */
	public static final String DRIVERSTATUS_AVAILABLE = "AVAILABLE";
	/**
	 * Driver status as DRIVERSTATUS_NOTAVAILABLE
	 */
	public static final String DRIVERSTATUS_NOTAVAILABLE = "NOTAVAILABLE";
	/**
	 * Vehicle status as VEHICLESTATUS_AVAILABLE
	 */
	public static final String VEHICLESTATUS_AVAILABLE = "AVAILABLE";
	/**
	 * Vehicle status as VEHICLESTATUS_NOTAVAILABLE
	 */
	public static final String VEHICLESTATUS_NOTAVAILABLE = "NOTAVAILABLE";
	/**
	 * Vehicle ,User validation message as VEHICLE_NOT_FOUND_FOR_USER
	 */
	public static final String VEHICLE_NOT_FOUND_FOR_USER = "No vehicle mapped to the given user";
	/**
	 * Data validation message
	 */
	public static final String DATA_NOT_FOUND_MSG = "No data found for the given inputs";

	public static final String EXCEPTION_MSG = "There is some issue at server side";
	/**
	 * Data validation message
	 */
	public static final String DATA_FOUND_MSG = "Data has been retrieved successfully";
	/**
	 * Hibernate exception message
	 */
	public static final String HIBERNATE_EXCEPTION_MSG = "There is some issue in accessing Database";
	/**
	 * Persistance exception message
	 */
	public static final String PERSISTENCE_EXCEPTION_MSG = "Could not able to establish DB connection";
	/**
	 * Server exception message
	 */
	public static final String SERVER_EXCEPTION_MSG = "There is some issue at server side";
	/**
	 * Data validation message
	 */
	public static final String INVALIDINPUT_EXCEPTION_MSG = "Invalid input parameters for the current request";
	/**
	 * Data validation message
	 */
	public static final String DATA_ALREADYEXISTS_EXCEPTION_MSG = "Data already exists for the current request";
	/**
	 * Alert message for success
	 */
	public static final String ALERT_MSG = "Success";
	/**
	 * Hold the constant to denote that the trip has been picked up.
	 */
	public static final String PICKUP_MSG = "pickup";
	/**
	 * Hold the constant to denote that the trip has been dispatched.
	 */
	public static final String DISPATCH_MSG = "delivery";
	/**
	 * Hold the constant to denote that the record has been inserted.
	 */
	public static final String SUCCESS_CREATED = "Created successfully";
	/**
	 * Hold the constant to denote that the record has been updated
	 */
	public static final String SUCCESS_UPDATED = "Updated successfully";
	/**
	 * Hold the constant to denote that the record has been deleted
	 */
	public static final String SUCCESS_DELETED = "Deleted successfully";
	/**
	 * User validation message
	 */
	public static final String USER_DOESNOT_EXIST = "User does not exist";
	/**
	 * Hold the constant to denote that the geofence circle
	 */
	public static final String GEOFENCE_CIRCLE = "circle";
	/**
	 * Hold the constant to denote that the geofence polygon
	 */
	public static final String GEOFENCE_POLYGON = "polygon";

	/**
	 * Hold the constant to denote that the record need to be deleted.
	 */
	public static final String DELETE_RECORD = "delete";
	/**
	 * Hold the constant to denote that the access key pattern
	 */
	public static final String ACCESS_KEY_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";

	/**
	 * Date format used in report service.
	 */
	public static final String REPORTS_DATE_FORMAT = "dd-MMM-YY";
	/**
	 * Hold the constant to denote that the vehicle list home
	 */
	public static final String VEHICLELIST_HOME = "home";
	/**
	 * Hold the constant to denote that the profiles
	 */
	public static final String VEHICLELIST_PROFILES = "profiles";
	/**
	 * Hold the constant to denote that the vehicle registration pattern
	 */
	public static final String VEHICLE_REGISTRATION_PATTERN = "^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$";
	/**
	 * Hold the constant to denote that the user role
	 */
	public static final String DRIVER_ROLE = "DRIVER";

	/**
	 * Hold the constant to denote that the user role
	 */
	public static final String ROOT_ADMIN_ROLE = "ROOTADMIN";

	public static final String FLEET_MANAGER_ROLE = "FLEETMANAGER";

	public static final String CUSTOMER_ADMIN_ROLE = "CUSTOMERADMIN";

	public static final String VEH_STATUS_RUNNING = "RUNNING";

	public static final String VEH_STATUS_OFFLINE = "OFFLINE";

	public static final String VEH_STATUS_NEW = "NEW";

	public static final String VEH_STATUS_IDLE = "IDLE";

	public static final String CATEGORY_NOT_AVAILABLE = "Category Not Available";

	public static final String GOOGLE_MAP_URL = "https://www.google.com/maps/search/?api=1&query=";

	public static final String SUCCESS = "SUCCESS";

	public static final String USER = "USER";

	public static final String VEHICLE = "VEHICLE";

	public static final String IMEI = "IMEI";

	public static final String ERM = "ERM";

	public static final String DEALER = "DEALER";

	public static final String CREATE = "Created";

	public static final String UPDATE = "Updated";

	public static final String DELETE = "Deleted";

	private DicvConstants() {

	}

}
