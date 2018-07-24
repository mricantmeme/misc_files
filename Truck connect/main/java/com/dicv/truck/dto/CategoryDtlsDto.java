package com.dicv.truck.dto;

public class CategoryDtlsDto {
	private Integer categoryId;
	private String categoryName;
	private Integer subCategoryId;
	private Integer userId;
	public Integer isDelete = 0;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	@Override
	public String toString() {
		return "CategoryDtlsDto [categoryId=" + categoryId + ", categoryName=" + categoryName + ", subCategoryId="
				+ subCategoryId + ", userId=" + userId + "]";
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
