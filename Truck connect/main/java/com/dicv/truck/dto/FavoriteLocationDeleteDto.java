package com.dicv.truck.dto;

/**
 * This class is responsible to delete specific favorite location based on owner
 * id and favorite id.
 * 
 * @author aut7kor
 * 
 */
public class FavoriteLocationDeleteDto {

	private Integer userId;
	private Integer favoriteId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
	}

	@Override
	public String toString() {
		return "FavoriteLocationDelete [userId=" + userId + ", favoriteId="
				+ favoriteId + "]";
	}

}
