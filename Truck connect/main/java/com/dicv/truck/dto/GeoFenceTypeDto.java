package com.dicv.truck.dto;

public class GeoFenceTypeDto {
	private Integer typeId;
	private String typeName;
	// private Integer subTypeId;
	private Boolean isDelete;
	private Integer userId;

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/*
	 * public Integer getSubTypeId() { return subTypeId; } public void
	 * setSubTypeId(Integer subTypeId) { this.subTypeId = subTypeId; }
	 */
	@Override
	public String toString() {
		return "Type [geoFenceTypeId=" + typeId + ", geoFenceTypeName="
				+ typeName + "]";
	}
}
