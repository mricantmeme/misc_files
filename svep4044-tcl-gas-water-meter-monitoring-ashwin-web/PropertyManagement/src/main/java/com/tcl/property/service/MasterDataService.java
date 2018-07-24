package com.tcl.property.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcl.property.dto.PropertyTypeResponseDto;
import com.tcl.property.model.PropertyType;
import com.tcl.property.repo.PropertyTypeRepo;
import com.tcl.property.response.Response;
import com.tcl.property.util.PropertyConstants;


@Service
public class MasterDataService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PropertyTypeRepo propertyTypeRepo;


	public Response<List<PropertyTypeResponseDto>> getPropertyTypeDetails() {
		Response<List<PropertyTypeResponseDto>> response = new Response<List<PropertyTypeResponseDto>>();
		List<PropertyType> getPropertyTypeList=propertyTypeRepo.findAll();
		if(getPropertyTypeList.isEmpty()) {
			response.setMessage(PropertyConstants.PROPERTY_NOT_FOUND);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}else {
			List<PropertyTypeResponseDto> propertyTypeDtoList = new ArrayList<PropertyTypeResponseDto>();
			getPropertyTypeList.forEach(u -> propertyTypeDtoList.add(convertToPropertyTypeResponse(u)));
			response.setData(propertyTypeDtoList);
			response.setStatus(HttpServletResponse.SC_OK);
			response.setMessage(PropertyConstants.MASTER_DATA_LIST);
		}
		return response;

	}
	private PropertyTypeResponseDto convertToPropertyTypeResponse(PropertyType propertyType) {
		PropertyTypeResponseDto propertyTypeResponse = modelMapper.map(propertyType, PropertyTypeResponseDto.class);
		return propertyTypeResponse;
	}


}
