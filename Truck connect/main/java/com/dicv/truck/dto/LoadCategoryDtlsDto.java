package com.dicv.truck.dto;

/**
 * This class is responsible to update load category based on userId and
 * loadCategoryId.
 * 
 * @author aut7kor
 * 
 */
public class LoadCategoryDtlsDto extends StatusMessageDto {

	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = 1505474016103637761L;

	private Integer loadCategoryId;
	private String loadCategoryName;
	private Integer userId;

	/**
	 * @return the loadCategoryId
	 */
	public Integer getLoadCategoryId() {
		return loadCategoryId;
	}

	/**
	 * @param loadCategoryId
	 *            the loadCategoryId to set
	 */
	public void setLoadCategoryId(Integer loadCategoryId) {
		this.loadCategoryId = loadCategoryId;
	}

	/**
	 * @return the loadCategoryName
	 */
	public String getLoadCategoryName() {
		return loadCategoryName;
	}

	/**
	 * @param loadCategoryName
	 *            the loadCategoryName to set
	 */
	public void setLoadCategoryName(String loadCategoryName) {
		this.loadCategoryName = loadCategoryName;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
