package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for list of users.
 * 
 * 
 */
public class UsersDto extends StatusMessageDto {

	private List<UserDto> users = new ArrayList<UserDto>();
	private boolean nextPageRequired;

	public boolean isNextPageRequired() {
		return nextPageRequired;
	}

	public void setNextPageRequired(boolean nextPageRequired) {
		this.nextPageRequired = nextPageRequired;
	}

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

}
