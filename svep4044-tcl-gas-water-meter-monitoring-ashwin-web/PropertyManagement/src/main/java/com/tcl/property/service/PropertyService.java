package com.tcl.property.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import com.tcl.property.dto.PropertyDeleteReqDto;
import com.tcl.property.dto.PropertyRegRequestDto;
import com.tcl.property.dto.PropertyRegResponseDto;
import com.tcl.property.dto.PropertyResponseDto;
import com.tcl.property.dto.PropertyStatusReqDto;
import com.tcl.property.dto.PropertyUpdateReqDto;
import com.tcl.property.exceptionhandler.NoDataFoundException;
import com.tcl.property.model.Property;
import com.tcl.property.repo.PropertyRepo;
import com.tcl.property.response.ListResponse;
import com.tcl.property.response.Response;
import com.tcl.property.util.Helper;
import com.tcl.property.util.PropertyConstants;

@Service
public class PropertyService {

	@Autowired
	private ModelMapper modelMapper;


	@Autowired
	private PropertyRepo propertyRepo;
	
	@Autowired
	private MongoOperations mongoOperation;


	public Response<PropertyRegResponseDto> createProperty(PropertyRegRequestDto requestDto) {
		Response<PropertyRegResponseDto> responseDto=new Response<PropertyRegResponseDto>();
		Property newProperty=addNewProperty(requestDto);
		PropertyRegResponseDto response=new PropertyRegResponseDto();
		response.setPropertyId(newProperty.getPropertyId());
		responseDto.setData(response);	
		responseDto.setMessage(PropertyConstants.PROPERTY_CREATED_SUCCESSFULLY);
		responseDto.setStatus(HttpServletResponse.SC_OK);
		responseDto.setError(false);
		return responseDto;
	}

	private Property addNewProperty(PropertyRegRequestDto requestDto) {
		String propertyId=null;
		Optional<Property> getProperty=propertyRepo.findFirstByOrderByCreatedDateDesc();
		if(getProperty.isPresent()) {
			if(getProperty.get().getPropertyId().charAt(0) == PropertyConstants.PROPERTY_STARTING)
				propertyId=Helper.generateNextPropertyId(getProperty.get().getPropertyId());
			else
				propertyId=Helper.generateUniquePropertyId(PropertyConstants.PROPERTY_STARTING);

		}else {
			propertyId=Helper.generateUniquePropertyId(PropertyConstants.PROPERTY_STARTING);

		}
		
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		Property addProperty=modelMapper.map(requestDto, Property.class);
		addProperty.setPropertyId(propertyId);
		addProperty.setPropertyStatus(true);
		addProperty.setCreatedBy(requestDto.getUserId());
		addProperty.setCreatedDate(new Date());
		addProperty.setModifiedBy(requestDto.getUserId());
		addProperty.setModifiedDate(new Date());
		return propertyRepo.save(addProperty);
	}

	public Response<String> updateProperty(PropertyUpdateReqDto requestDto) {
		Response<String> responseDto=new Response<String>();
		Optional<Property> property=propertyRepo.findByPropertyId(requestDto.getPropertyId());
		if(property.isPresent()) {
			Property propertyDetail=property.get();
			requestDto.setCreatedBy(requestDto.getUserId());
			propertyDetail=modelMapper.map(requestDto,Property.class);
			propertyDetail.setPropertyStatus(true);
			propertyDetail.setModifiedBy(requestDto.getPropertyId());
			propertyDetail.setModifiedDate(new Date());
			propertyRepo.save(propertyDetail); 
			responseDto.setMessage(PropertyConstants.PROPERTY_UPDATED_SUCCESSFULLY);
			responseDto.setStatus(HttpServletResponse.SC_OK);
			responseDto.setError(false);

		}else {
			responseDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			responseDto.setError(true);
			responseDto.setMessage(PropertyConstants.PROPERTY_NOT_FOUND);
		}
		return responseDto;
	}
	
	public Response<Boolean> deleteProperty(PropertyDeleteReqDto requestDto) {
		Response<Boolean> responseDto=new Response<Boolean>();

		List<Property> property=propertyRepo.findByPropertyIdIn(requestDto.getPropertyId());
		if(!property.isEmpty()) {
			for(Property propertyDetail:property) {			
				propertyDetail.setPropertyStatus(false);
				propertyDetail.setModifiedDate(new Date());}
			propertyRepo.save(property);
			responseDto.setMessage(PropertyConstants.PROPERTY_DELETED_SUCCESSFULLY);
			responseDto.setStatus(HttpServletResponse.SC_OK);
			responseDto.setError(false);
		}
		else {
			responseDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			responseDto.setError(true);
			responseDto.setMessage(PropertyConstants.PROPERTY_NOT_FOUND);
		}
		return responseDto;

	}
	public ListResponse<List<PropertyResponseDto>> listProperties(String search,int page,int size) {
		ListResponse<List<PropertyResponseDto>> responseDto=new ListResponse<List<PropertyResponseDto>>();
		List<Property> propertyDetails=null;
		if(search.isEmpty() || search == null)
			propertyDetails=propertyRepo.findByPropertyStatus(true,createPageRequest(page,size));

		else
			propertyDetails=propertyRepo.findByOwnerNameContainsIgnoreCaseOrMobileNumberContainsIgnoreCaseAndPropertyStatus(search,search,true,createPageRequest(page,size));

		if(!propertyDetails.isEmpty()) {
			List<PropertyResponseDto> lstEntity=new ArrayList<>();
			propertyDetails.forEach(u -> lstEntity.add(convertToPropertyDetailsDto(u)));
			responseDto.setData(lstEntity);	
			responseDto.setMessage(PropertyConstants.PROPERTY_List);
			responseDto.setStatus(HttpServletResponse.SC_OK);
			responseDto.setError(false);
			responseDto.setCount(propertyDetails.size());
			responseDto.setTotalRecords(getTotalRecords(search));

		}else {
			responseDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			responseDto.setError(true);
			responseDto.setMessage(PropertyConstants.PROPERTY_NOT_FOUND);
		}

		return responseDto;

	}

	private int getTotalRecords(String search) {
		if(search.isEmpty() || search == null)
			return  propertyRepo.findByPropertyStatus(true).size();

		else
			return propertyRepo.findByOwnerNameContainsIgnoreCaseOrMobileNumberContainsIgnoreCaseAndPropertyStatus(search,search,true).size();
	}

	private PropertyResponseDto convertToPropertyDetailsDto(Property property) {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		PropertyResponseDto responseDto = modelMapper.map(property, PropertyResponseDto.class);
		return responseDto;
	}
	private Pageable createPageRequest(int page, int size) {
		return new PageRequest(page,size,new Sort(Sort.Direction.DESC, "_id"));
	}

	public Response<PropertyResponseDto> getPropertyDetails(String propertyId) {
		Response<PropertyResponseDto> responseDto=new Response<PropertyResponseDto>();
		Optional<Property> getPropertyDetail=propertyRepo.findByPropertyId(propertyId);
		if(!getPropertyDetail.isPresent())
			throw new NoDataFoundException(PropertyConstants.PROPERTY_NOT_FOUND);
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		PropertyResponseDto response = modelMapper.map(getPropertyDetail.get(), PropertyResponseDto.class);
		responseDto.setData(response);
		responseDto.setError(false);
		responseDto.setMessage(PropertyConstants.PROPERTY_DETAILS);
		responseDto.setStatus(HttpServletResponse.SC_OK);
		return responseDto;
	}
	public Response<Boolean> updatePropertyStatus(PropertyStatusReqDto requestDto) {
		Response<Boolean> responseDto=new Response<Boolean>();
		Optional<Property> getProperty=propertyRepo.findByPropertyId(requestDto.getPropertyId());
		if(!getProperty.isPresent())
			throw new NoDataFoundException(PropertyConstants.PROPERTY_NOT_FOUND);
		Property updateStatus=getProperty.get();
		updateStatus.setPropertyStatus(requestDto.getPropertyStatus());
		updateStatus.setModifiedBy(requestDto.getUserId());
		updateStatus.setModifiedDate(new Date());
		propertyRepo.save(updateStatus);
		responseDto.setError(false);
		responseDto.setMessage(PropertyConstants.PROPERTY_STATUS);
		responseDto.setStatus(HttpServletResponse.SC_OK);
		return responseDto;
	}

	public ListResponse<List<PropertyResponseDto>> filterListProperty(List<String> filters, String sortByFieldName,
			String sortType, Integer page, Integer size, String userId) {
		ListResponse<List<PropertyResponseDto>> responseDto=new ListResponse<>();
		String propertySearchFields="";
		List<Property> totalPropertyDetails=null;
		List<PropertyResponseDto> propertyDetails=new ArrayList<>();
		List<PropertyResponseDto> lstEntity=new ArrayList<>();

		BasicQuery executeQuery=null;
		String filter=null;
		String soryBy=null;
		String id=null;

		if(filters.size() >0 && sortByFieldName != "" && sortType != "") {
			filter=filters.stream().map(Object::toString).collect(Collectors.joining("},{","{","}"));
			id="{"+"'created_by'"+":"+"'"+userId+"'"+"},";
			filter=id.concat(filter);
			propertySearchFields=PropertyConstants.PROPERTY_DEFAULT_QUERY;
			soryBy="{"+sortByFieldName+":"+sortType+"}";
			propertySearchFields=String.format(propertySearchFields, filter,soryBy);

		}else if (filters.size() >0 && sortByFieldName == "") {
			filter=filters.stream().map(Object::toString).collect(Collectors.joining("},{","{","}"));
			id="{"+"'created_by'"+":"+"'"+userId+"'"+"},";
			filter=id.concat(filter);
			propertySearchFields=PropertyConstants.PROPERTY_DEFAULT_FILTER_QUERY;
			propertySearchFields=String.format(propertySearchFields, filter);


		}else if (sortByFieldName != "" && sortType != "") {
			id="{"+"'created_by'"+":"+"'"+userId+"'"+"},";
			soryBy="{"+sortByFieldName+":"+sortType+"}";
			propertySearchFields=PropertyConstants.PROPERTY_DEFAULT_SORT_QUERY;
			propertySearchFields=String.format(propertySearchFields,id, soryBy);
		}else {
			id="{"+"'created_by'"+":"+"'"+userId+"'"+"},";
			propertySearchFields=PropertyConstants.PROPERTY_DEFAULT_ACTIVE_QUERY;
			propertySearchFields=String.format(propertySearchFields, id);}

		executeQuery = new BasicQuery(propertySearchFields);
		totalPropertyDetails = mongoOperation.find(executeQuery, Property.class);


		if(totalPropertyDetails.isEmpty()) {
			responseDto.setData(lstEntity);
			responseDto.setCount(0);
			responseDto.setError(false);
			responseDto.setMessage(PropertyConstants.PROPERTY_NOT_FOUND);
			responseDto.setStatus(HttpServletResponse.SC_OK);
			responseDto.setTotalRecords(0);
			return 	responseDto;	
		}
		totalPropertyDetails.forEach(u -> lstEntity.add(convertToPropertyDetailsDto(u)));

		// generate pageNation
		if(page==0)
			page=1;
		else
			page++;
		Long startNumber = (long) (((size * page) - size)+1);
		Long endNumber = (long) (size * page);
		if(lstEntity.size() >= 0)
		{

			int fromIndex = (startNumber.intValue() - 1) ;
			int toIndex = endNumber.intValue() ;

			if(lstEntity.size() <=  endNumber.intValue())
			{
				toIndex = lstEntity.size();
			}

			propertyDetails = lstEntity.subList(fromIndex , toIndex);
		}
		propertyDetails = propertyDetails.stream().collect(Collectors.toList());
		responseDto.setData(propertyDetails);
		responseDto.setCount(propertyDetails.size());
		responseDto.setTotalRecords(lstEntity.size());
		responseDto.setError(false);
		responseDto.setMessage(PropertyConstants.PROPERTY_List);

		return responseDto;

	}



}
