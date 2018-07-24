package com.dicv.truck.dto;

public class RegionDto {

	/**
	 * Holds region id.
	 */
	Integer regionId;

	/**
	 * Holds region name.
	 */
	String regionName;

	/**
	 * Holds sub region id.
	 */
	Integer subregionId;

	public Boolean isDelete;
	
	private Integer userId
	;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getSubregionId() {
		return subregionId;
	}

	public void setSubregionId(Integer subregionId) {
		this.subregionId = subregionId;
	}

	@Override
	public String toString() {
		return "Region [regionId=" + regionId + ", regionName=" + regionName
				+ ", subregionId=" + subregionId + "]";
	}

}
