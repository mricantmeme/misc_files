package com.dicv.truck.dto;

public class DriverCurrentStatus {

	private Integer driverId;
	private String name;
	private String status;
	private String mobileNumber;
	private String groupName;

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public String toString() {
		return "DriverCurrentStatus [driverId=" + driverId + ", name=" + name
				+ ", status=" + status + ", mobileNumber=" + mobileNumber
				+ ", groupName=" + groupName + "]";
	}

}
