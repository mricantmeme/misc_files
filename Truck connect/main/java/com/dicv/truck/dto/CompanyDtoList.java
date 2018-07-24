package com.dicv.truck.dto;

import java.util.List;

public class CompanyDtoList extends StatusMessageDto {

	private List<CompanyDto> companyList;

	public List<CompanyDto> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<CompanyDto> companyList) {
		this.companyList = companyList;
	}
	
	
}
