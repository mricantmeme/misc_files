package com.dicv.truck.dto;

public class CategoryDto {

	private Integer categoryId;

	private String categoryName;

	private Integer subCategoryId;

	private Integer userId;

	public CategoryDto(Integer categoryId, String categoryName, Integer userId) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
		return "CategoryDto [categoryId=" + categoryId + ", " + "categoryName=" + categoryName + ", subCategoryId="
				+ subCategoryId + "]";
	}

}
