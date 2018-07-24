package com.dicv.truck.response;

public enum ResponseStatus {

	SUCCESS(1, "SUCCESS"), FAILURE(0, "FAILURE"), WARNING(2, "WARNING");

	private int id;
	private String name;

	private ResponseStatus(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
