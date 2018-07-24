package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

public class OwnerDtlsDto extends StatusMessageDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer ownerId;
	private String name;
	private String address;
	private String mobileNum;
	private String email;
	private String emergencyNum1;
	private String emergencyNum2;
	private String emergencyNum3;
	private Integer userId;
	private boolean hasNextPage;
	private List<DriverDtlsDto> drivers;
	private List<VehicleDtlsDto> vehicles;
	private Long totalCount = null;
	private List<VehicleDtlsDtoHome> vehicleDtlsDtoHomeList = new ArrayList<VehicleDtlsDtoHome>();

	public OwnerDtlsDto() {

	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmergencyNum1() {
		return emergencyNum1;
	}

	public void setEmergencyNum1(String emergencyNum1) {
		this.emergencyNum1 = emergencyNum1;
	}

	public String getEmergencyNum2() {
		return emergencyNum2;
	}

	public void setEmergencyNum2(String emergencyNum2) {
		this.emergencyNum2 = emergencyNum2;
	}

	public String getEmergencyNum3() {
		return emergencyNum3;
	}

	public void setEmergencyNum3(String emergencyNum3) {
		this.emergencyNum3 = emergencyNum3;
	}

	public List<DriverDtlsDto> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<DriverDtlsDto> drivers) {
		this.drivers = drivers;
	}

	public List<VehicleDtlsDto> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<VehicleDtlsDto> vehicles) {
		this.vehicles = vehicles;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<VehicleDtlsDtoHome> getVehicleDtlsDtoHomeList() {
		return vehicleDtlsDtoHomeList;
	}

	public void setVehicleDtlsDtoHomeList(
			List<VehicleDtlsDtoHome> vehicleDtlsDtoHomeList) {
		this.vehicleDtlsDtoHomeList = vehicleDtlsDtoHomeList;
	}

	@Override
	public String toString() {
		return "OwnerDtls [ownerId=" + ownerId + ", name=" + name
				+ ", address=" + address + ", mobileNum=" + mobileNum
				+ ", email=" + email + ", emergencyNum1=" + emergencyNum1
				+ ", emergencyNum2=" + emergencyNum2 + ", emergencyNum3="
				+ emergencyNum3 + ", userId=" + userId + ", drivers=" + drivers
				+ ", vehicles=" + vehicles + "]";
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
}
