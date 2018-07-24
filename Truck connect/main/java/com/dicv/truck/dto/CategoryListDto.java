package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryListDto extends StatusMessageDto {

	private List<CategoryDto> categoryList = new ArrayList<CategoryDto>();

	public List<CategoryDto> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryDto> categoryList) {
		this.categoryList = categoryList;
	}

}
