package com.dicv.truck.utility;


public enum RecordStatus {

	IS_NOT_DELETED(0), DELETED(1), IS_ENABLE(1), IS_NOT_ENABLE(0);

	private int state;

	RecordStatus(int state) {
		this.state = state;

	}

	public int getStatus() {
		return state;
	}

}
