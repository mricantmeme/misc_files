package com.dicv.truck.dto;

public class TypeDto {
	public TypeDto() {
	}

	private Integer typeId;
	private String typeName;
	private Integer subTypeId;
	private Integer isDelete = 0;

	public TypeDto(Integer typeId, String typeName, Integer subTypeId, Integer userId) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.subTypeId = subTypeId;
		this.userId = userId;
	}

	private Integer userId;

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

	public Integer getSubTypeId() {
		return subTypeId;
	}

	public void setSubTypeId(Integer subTypeId) {
		this.subTypeId = subTypeId;
	}

	@Override
	public String toString() {
		return "Type [dicvTypeId=" + typeId + ", typeName=" + typeName + ", subTypeId=" + subTypeId + "]";
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}
