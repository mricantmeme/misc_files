package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

public class UsersSelectedDto extends StatusMessageDto {

	private List<UserSelectedDto> users = new ArrayList<UserSelectedDto>();
	private boolean nextPageRequired;

	public boolean isNextPageRequired() {
		return nextPageRequired;
	}

	public void setNextPageRequired(boolean nextPageRequired) {
		this.nextPageRequired = nextPageRequired;
	}

	public List<UserSelectedDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserSelectedDto> users) {
		this.users = users;
	}

}
