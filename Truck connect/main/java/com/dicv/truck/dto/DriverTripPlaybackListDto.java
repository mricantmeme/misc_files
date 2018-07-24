/**
 * This Class provides list of GPS records for the given vehicleId/tripId/Date in modules TRIP,DRIVER AND PLAYBACK.
 */
package com.dicv.truck.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author aut7kor
 * 
 */
public class DriverTripPlaybackListDto extends StatusMessageDto {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -8756902049194992682L;
	private List<DriverTripPlaybackDto> driverTripPlaybackList = null;
	
	private List<DriverTripPlaybackDto> driverSpeedingLocations = null;
	
	private List<DriverTripPlaybackDto> driverStopLocations = null;
	
	private int noOfStops;

	/**
	 * @return the driverTripPlaybackList
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public List<DriverTripPlaybackDto> getDriverTripPlaybackList() {
		return driverTripPlaybackList;
	}

	/**
	 * @param driverTripPlaybackList
	 *            the driverTripPlaybackList to set
	 */
	public void setDriverTripPlaybackList(
			List<DriverTripPlaybackDto> driverTripPlaybackList) {
		this.driverTripPlaybackList = driverTripPlaybackList;
	}

	/**
	 * @return the noOfStops
	 */
	public int getNoOfStops() {
		return noOfStops;
	}

	/**
	 * @param noOfStops
	 *            the noOfStops to set
	 */
	public void setNoOfStops(int noOfStops) {
		this.noOfStops = noOfStops;
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public List<DriverTripPlaybackDto> getDriverSpeedingLocations() {
		return driverSpeedingLocations;
	}

	public void setDriverSpeedingLocations(
			List<DriverTripPlaybackDto> driverSpeedingLocations) {
		this.driverSpeedingLocations = driverSpeedingLocations;
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public List<DriverTripPlaybackDto> getDriverStopLocations() {
		return driverStopLocations;
	}

	public void setDriverStopLocations(
			List<DriverTripPlaybackDto> driverStopLocations) {
		this.driverStopLocations = driverStopLocations;
	}

		
}
