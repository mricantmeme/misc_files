package com.dicv.truck.dto;

import java.util.ArrayList;
import java.util.List;

public class VehicleFileUploadListDto extends StatusMessageDto {

	private List<VeicleUploadFileDto> fileList = new ArrayList<VeicleUploadFileDto>();

	private Integer userId;

	private Integer vehicleId;

	public List<VeicleUploadFileDto> getFileList() {
		return fileList;
	}

	public void setFileList(List<VeicleUploadFileDto> fileList) {
		this.fileList = fileList;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

}
