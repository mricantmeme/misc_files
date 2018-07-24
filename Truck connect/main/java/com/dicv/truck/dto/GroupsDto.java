package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

public class GroupsDto extends StatusMessageDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4172671795966870364L;
	/**
	 * Holds list of groups.
	 */
	private List<GroupDto> groups = new ArrayList<GroupDto>();

	public List<GroupDto> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupDto> groups) {
		this.groups = groups;
	}

}
