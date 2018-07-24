package com.dicv.truck.utility;

public enum EnumUserType {

	FLEETMANAGER("FLEETMANAGER"), ROOTADMIN("ROOTADMIN"), DRIVER("DRIVER"), MANAGER("MANAGER"), OEMMANAGER(
			"OEMMANAGER"), CUSTOMERADMIN("CUSTOMERADMIN"), DEALER("DEALER"), ERM("ERM");

	private String userType;

	EnumUserType(String statusCode) {
		this.userType = statusCode;

	}

	public String getUserType() {
		return userType;
	}

}
