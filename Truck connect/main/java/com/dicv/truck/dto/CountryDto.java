package com.dicv.truck.dto;

public class CountryDto {

	public CountryDto(Integer countryId, String countryName, String countryCode, Integer phoneCode) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.phoneCode = phoneCode;
	}

	private Integer countryId;
	private String countryName;
	private String countryCode;
	private Integer phoneCode;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryName="
				+ countryName + ", countryCode=" + countryCode + "]";
	}

    public Integer getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(Integer phoneCode) {
        this.phoneCode = phoneCode;
    }

}
