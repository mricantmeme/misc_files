package com.dicv.truck.dto;

import java.util.List;

public class DashBoardListDto extends StatusMessageDto {
	
	
	
	private Integer registeredVehicles;
	
	
	private List<DashBoardDto> dashBoard;


	public Integer getRegisteredVehicles() {
		return registeredVehicles;
	}


	public void setRegisteredVehicles(Integer registeredVehicles) {
		this.registeredVehicles = registeredVehicles;
	}


	public List<DashBoardDto> getDashBoard() {
		return dashBoard;
	}


	public void setDashBoard(List<DashBoardDto> dashBoard) {
		this.dashBoard = dashBoard;
	}




}
