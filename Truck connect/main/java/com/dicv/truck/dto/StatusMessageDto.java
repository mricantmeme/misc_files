package com.dicv.truck.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

public class StatusMessageDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer status;
	private Integer identifier;
	private String message;
	private String description;

	public StatusMessageDto() {

	}

	public StatusMessageDto(Integer status, String message) {

		this.status = status;
		this.message = message;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

}
