package com.dicv.truck.dto;

/**
 * This class is responsible to add new favorite location for specific owner
 * during schedule trip.
 * 
 * @author aut7kor
 * 
 */
public class FavoriteLocationDtlsDto extends StatusMessageDto {

	private Integer userId;
	private String favoriteTagName;
	private String address;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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

	@Override
	public String toString() {
		return "FavoriteLocationDtls [userId=" + userId + ", favoriteTagName="
				+ favoriteTagName + ", address=" + address + "]";
	}
}
