package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible to delete list of favorite location details based
 * on owner id and favorite id.
 * 
 * @author aut7kor
 * 
 */
public class FavoriteLocationDeleteListDto extends StatusMessageDto {

	List<FavoriteLocationDeleteDto> favoriteLocationList = new ArrayList<FavoriteLocationDeleteDto>();

	public List<FavoriteLocationDeleteDto> getFavoriteLocationList() {
		return favoriteLocationList;
	}

	public void setFavoriteLocationList(
			List<FavoriteLocationDeleteDto> favoriteLocationList) {
		this.favoriteLocationList = favoriteLocationList;
	}

	@Override
	public String toString() {
		String result = "";
		for (FavoriteLocationDeleteDto fld : favoriteLocationList) {
			result += fld.toString() + "\n";
		}
		return result;
	}
}
