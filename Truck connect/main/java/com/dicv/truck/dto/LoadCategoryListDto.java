package com.dicv.truck.dto;

import java.util.List;

/**
 * This class is responsible to keep list of load category.
 * 
 * @author aut7kor
 * 
 */
public class LoadCategoryListDto extends StatusMessageDto {
	/*
	 * List of load category.
	 */
	List<LoadCategoryDtlsDto> loadCategoryList;

	/**
	 * @return the loadCategoryList
	 */
	public List<LoadCategoryDtlsDto> getLoadCategoryList() {
		return loadCategoryList;
	}

	/**
	 * @param loadCategoryList
	 *            the loadCategoryList to set
	 */
	public void setLoadCategoryList(List<LoadCategoryDtlsDto> loadCategoryList) {
		this.loadCategoryList = loadCategoryList;
	}

}
