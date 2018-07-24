package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible to return list of user based on input parameter
 * like user id. If the user role as ADMIN then returns list of owner. If the
 * user role as OWNER then returns list of driver.
 * 
 * @author aut7kor
 * 
 */

public class UserListDto extends StatusMessageDto {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -6720793556753225803L;

	List<UserDetailsDto> userList = new ArrayList<UserDetailsDto>();
	
	private Long totalCount = null;

	public List<UserDetailsDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDetailsDto> userList) {
		this.userList = userList;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		String result = "";
		for (UserDetailsDto udd : userList) {
			result += udd.toString() + "\n";
		}
		return result;
	}
}
