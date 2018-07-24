package com.dicv.truck.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class Response<T> {

	ResponseStatus status;
	T data;
	String message;

	public ResponseStatus getStatus() {
		return status;
	}

	public T getData() {
		return data;
	}

	public Response(ResponseStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public Response() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSuccess(T data) {
		status = ResponseStatus.SUCCESS;
		this.data = data;
	}

}
