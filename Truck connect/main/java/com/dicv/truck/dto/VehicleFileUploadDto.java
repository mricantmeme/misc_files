package com.dicv.truck.dto;

public class VehicleFileUploadDto extends StatusMessageDto {

	private Integer fileId;

	private String fileSize;

	private String fileName;

	private String content;

	private String description;

	private String fileType;

	public VehicleFileUploadDto() {
		super();
	}

	public VehicleFileUploadDto(Integer fileId, String fileSize, String fileName, String description, String fileType) {
		super();
		this.fileId = fileId;
		this.fileSize = fileSize;
		this.fileName = fileName;
		this.description = description;
		this.fileType = fileType;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
