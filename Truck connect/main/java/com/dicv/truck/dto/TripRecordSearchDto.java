package com.dicv.truck.dto;
/**
 * This class responsible to keep trip record search input parameters.
 * @author aut7kor
 *
 */
public class TripRecordSearchDto {
	private String textSearch;
	private int startRow;
	private int endRow;
	private Integer userId;

	public String getTextSearch() {
		return textSearch;
	}

	public void setTextSearch(String textSearch) {
		this.textSearch = textSearch;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "TripRecordSearchDto [textSearch=" + textSearch + ", startRow="
				+ startRow + ", endRow=" + endRow + ", userId=" + userId + "]";
	}

}
