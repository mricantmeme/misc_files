/**
 * 
 */
package com.dicv.truck.utility;

/**
 * @author IMT5KOR
 *
 */
public enum VehicleCAN {
	
	
	CAN_UNAVAILABLE(0), CAN_AVAILABLE(1);

	private int state;

	VehicleCAN(int state) {
		this.state = state;

	}

	public int getState() {
		return state;
	}

}
