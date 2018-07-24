package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible to keep list of favoriteTagName and address based
 * on owner id.
 * 
 * @author aut7kor
 * 
 */
public class FavoriteLocationListDto extends StatusMessageDto {
	List<FavoriteLocationDto> favoriteLocationList = new ArrayList<FavoriteLocationDto>();

	public List<FavoriteLocationDto> getFavoriteLocationList() {
		return favoriteLocationList;
	}

	public void setFavoriteLocationList(
			List<FavoriteLocationDto> favoriteLocationList) {
		this.favoriteLocationList = favoriteLocationList;
	}

	@Override
	public String toString() {
		String result = "";
		for (FavoriteLocationDto favoriteLocation : favoriteLocationList) {
			result += favoriteLocation.toString() + "\n";
		}
		return result;
	}
}
