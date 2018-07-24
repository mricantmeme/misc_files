/*
 * @category DeviceManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.devices.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/*
 * This is a generic class, that acts as a container for any list of data, that needs to be sent in the response.
 */
@JsonInclude(value = Include.NON_EMPTY)
public class ListResponse<T> {

	public ListResponse(Integer status, String message, Boolean error, Integer count, Integer totalRecords) {
		super();
		this.status = status;
		this.message = message;
		this.error = error;
		this.count = count;
		this.totalRecords = totalRecords;
	}

	public ListResponse(Integer status, T data, String message, Boolean error, Integer count, Integer totalRecords) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
		this.error = error;
		this.count = count;
		this.totalRecords = totalRecords;
	}

	public ListResponse(T data) {
		super();
		this.data = data;
	}

	public ListResponse() {
	}

	Integer status;
	T data;
	String message;
	Boolean error;
	Integer count;
	Integer totalRecords;

	public Integer getCount() {
		return count;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public Integer getStatus() {
		return status;
	}

	public T getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
