package com.dicv.truck.dto;

/**
 * This class is responsible to keep favoriteId,favoriteTagName and address
 * based on owner id.
 * 
 * @author aut7kor
 * 
 */
public class FavoriteLocationDto {
	private String favoriteTagName;
	private String address;
	private Integer favoriteId;

	public String getFavoriteTagName() {
		return favoriteTagName;
	}

	public void setFavoriteTagName(String favoriteTagName) {
		this.favoriteTagName = favoriteTagName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
	}

	@Override
	public String toString() {
		return "FavoriteLocation [favoriteTagName=" + favoriteTagName
				+ ", address=" + address + ", favoriteId=" + favoriteId + "]";
	}

}
