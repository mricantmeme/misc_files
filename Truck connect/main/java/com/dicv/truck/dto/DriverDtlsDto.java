package com.dicv.truck.dto;

/*
 * @Author Gauthami P
 */
public class DriverDtlsDto {

	private Integer driverId;
	private String name;
	private String status;
	private String address;
	private String mobileNum;
	private String drivingLicense;
	private Integer ownerId;
	private Integer userId;

	public DriverDtlsDto() {

	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "DriverDtls [driverId=" + driverId + ", name=" + name
				+ ", status=" + status + ", address=" + address
				+ ", mobileNum=" + mobileNum + ", drivingLicense="
				+ drivingLicense + ", ownerId=" + ownerId + ", userId="
				+ userId + "]";
	}

}
