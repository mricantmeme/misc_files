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

import com.tcl.property.dto.OccupantDeleteReqestDto;
import com.tcl.property.dto.OccupantRegisterRequestDto;
import com.tcl.property.dto.OccupantRegisterResponseDto;
import com.tcl.property.dto.OccupantResponseDto;
import com.tcl.property.dto.OccupantStatusRequestDto;
import com.tcl.property.dto.OccupantUpdateReqestDto;
import com.tcl.property.dto.PropertyResponseDto;
import com.tcl.property.exceptionhandler.NoDataFoundException;
import com.tcl.property.model.Occupant;
import com.tcl.property.model.Property;
import com.tcl.property.repo.OccupantRepo;
import com.tcl.property.response.ListResponse;
import com.tcl.property.response.Response;
import com.tcl.property.util.Helper;
import com.tcl.property.util.OccupantConstants;
import com.tcl.property.util.PropertyConstants;

@Service
public class OccupantService {

	@Autowired
	private ModelMapper modelMapper;


	@Autowired
	private OccupantRepo occupantRepo;
	
	@Autowired
	private MongoOperations mongoOperation;


	public Response<OccupantRegisterResponseDto> createOccupant(OccupantRegisterRequestDto requestDto) {
		Response<OccupantRegisterResponseDto> responseDto=new Response<OccupantRegisterResponseDto>();
		Occupant newOccupant=addNewOccupant(requestDto);
		OccupantRegisterResponseDto response=new OccupantRegisterResponseDto();
		response.setOccupantId(newOccupant.getOccupantId());
		responseDto.setData(response);	
		responseDto.setMessage(OccupantConstants.OCCUPANT_CREATED_SUCCESSFULLY);
		responseDto.setStatus(HttpServletResponse.SC_OK);
		responseDto.setError(false);
		return responseDto;
	}

	private Occupant addNewOccupant(OccupantRegisterRequestDto requestDto) {
		String occupantId=null;
		Optional<Occupant> getOccupant=occupantRepo.findFirstByOrderByCreatedDateDesc();
		if(getOccupant.isPresent()) {
			if(getOccupant.get().getOccupantId().charAt(0)== OccupantConstants.OCCUPANT_STARTING)
				occupantId=Helper.generateNextOccupantId(getOccupant.get().getOccupantId());
			else
				occupantId=Helper.generateUniqueOccupantId(OccupantConstants.OCCUPANT_STARTING);

		}else {
			occupantId=Helper.generateUniqueOccupantId(OccupantConstants.OCCUPANT_STARTING);

		}

		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		Occupant addOccupant=modelMapper.map(requestDto, Occupant.class);
		addOccupant.setOccupantId(occupantId);
		addOccupant.setActive(true);
		addOccupant.setCreatedBy(requestDto.getUserId());
		addOccupant.setCreatedDate(new Date());
		addOccupant.setModifiedBy(requestDto.getUserId());
		addOccupant.setModifiedDate(new Date());
		return occupantRepo.save(addOccupant);
	}

	public Response<String> updateOccupant(OccupantUpdateReqestDto requestDto) {
		Response<String> responseDto=new Response<String>();
		Optional<Occupant> occupant=occupantRepo.findByOccupantId(requestDto.getOccupantId());
		if(occupant.isPresent()) {
			Occupant occupantDetail=occupant.get();
			requestDto.setCreatedBy(occupantDetail.getCreatedBy());
			requestDto.setCreatedDate(occupantDetail.getCreatedDate());
			occupantDetail=modelMapper.map(requestDto,Occupant.class);
			occupantDetail.setOccupantStatus(requestDto.getOccupantStatus());
			occupantDetail.setModifiedBy(requestDto.getUserId());
			occupantDetail.setModifiedDate(new Date());
			occupantDetail.setActive(true);
			occupantRepo.save(occupantDetail); 
			responseDto.setMessage(OccupantConstants.OCCUPANT_UPDATED_SUCCESSFULLY);
			responseDto.setStatus(HttpServletResponse.SC_OK);
			responseDto.setError(false);

		}else {
			responseDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			responseDto.setError(true);
			responseDto.setMessage(OccupantConstants.OCCUPANT_NOT_FOUND);
		}
		return responseDto;
	}

	public Response<Boolean> deleteOccupant(OccupantDeleteReqestDto requestDto) {
		Response<Boolean> responseDto=new Response<Boolean>();

		List<Occupant> occupant=occupantRepo.findByOccupantIdInAndIsActive(requestDto.getOccupantId(),true);
		if(!occupant.isEmpty()) {
			for(Occupant occupantDetail:occupant) {			
				occupantDetail.setActive(false);
				occupantDetail.setModifiedDate(new Date());
				}
			occupantRepo.save(occupant);
			responseDto.setMessage(OccupantConstants.OCCUPANT_DELETED_SUCCESSFULLY);
			responseDto.setStatus(HttpServletResponse.SC_OK);
			responseDto.setError(false);

		}else {
			responseDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			responseDto.setError(true);
			responseDto.setMessage(OccupantConstants.OCCUPANT_NOT_FOUND);
		}
		return responseDto;

	}
	public ListResponse<List<OccupantResponseDto>> listOccupants(String propertyId,String search,int page,int size) {
		ListResponse<List<OccupantResponseDto>> responseDto=new ListResponse<List<OccupantResponseDto>>();
		List<Occupant> occupantDetails=null;
		if(search.isEmpty() || search == null)
			occupantDetails=occupantRepo.findByIsActiveAndPropertyId(true,propertyId,createPageRequest(page,size));

		else
			occupantDetails=occupantRepo.findByFirstNameContainsIgnoreCaseOrMiddleNameContainsIgnoreCaseOrLastNameContainsIgnoreCaseOrOccupantEmailIdContainsIgnoreCaseAndIsActiveAndPropertyId(search,search,search,search,true,propertyId,createPageRequest(page,size));

		if(!occupantDetails.isEmpty()) {
			List<OccupantResponseDto> lstEntity=new ArrayList<>();
			occupantDetails.forEach(u -> lstEntity.add(convertToOccupantDetailsDto(u)));
			responseDto.setData(lstEntity);	
			responseDto.setMessage(OccupantConstants.OCCUPANT_List);
			responseDto.setStatus(HttpServletResponse.SC_OK);
			responseDto.setError(false);
			responseDto.setCount(occupantDetails.size());
			responseDto.setTotalRecords(getTotalRecords(propertyId,search));

		}else {
			responseDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			responseDto.setError(true);
			responseDto.setMessage(OccupantConstants.OCCUPANT_NOT_FOUND);
		}

		return responseDto;

	}

	private int getTotalRecords(String propertyId,String search) {
		if(search.isEmpty() || search == null)
			return  occupantRepo.findByIsActiveAndPropertyId(true,propertyId).size();

		else
			return occupantRepo.findByFirstNameContainsIgnoreCaseOrMiddleNameContainsIgnoreCaseOrLastNameContainsIgnoreCaseOrOccupantEmailIdContainsIgnoreCaseAndIsActiveAndPropertyId(search,search,search,search,true,propertyId).size();
	}

	private OccupantResponseDto convertToOccupantDetailsDto(Occupant occupant) {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		OccupantResponseDto responseDto = modelMapper.map(occupant, OccupantResponseDto.class);
		return responseDto;
	}
	private Pageable createPageRequest(int page, int size) {
		return new PageRequest(page,size,new Sort(Sort.Direction.DESC, "_id"));
	}
	public Response<OccupantResponseDto> getOccupantDetails(String occupantId) {
		Response<OccupantResponseDto> responseDto=new Response<OccupantResponseDto>();
		Optional<Occupant> getOccupantDetail=occupantRepo.findByOccupantId(occupantId);
		if(!getOccupantDetail.isPresent())
			throw new NoDataFoundException(OccupantConstants.OCCUPANT_NOT_FOUND);
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		OccupantResponseDto response = modelMapper.map(getOccupantDetail.get(), OccupantResponseDto.class);
		responseDto.setData(response);
		responseDto.setError(false);
		responseDto.setMessage(OccupantConstants.OCCUPANT_DETAILS);
		responseDto.setStatus(HttpServletResponse.SC_OK);
		return responseDto;
	}
	public Response<Boolean> updateOccupantStatus(OccupantStatusRequestDto requestDto) {
		Response<Boolean> responseDto=new Response<Boolean>();
		Optional<Occupant> getOccupant=occupantRepo.findByOccupantId(requestDto.getOccupantId());
		if(!getOccupant.isPresent())
			throw new NoDataFoundException(OccupantConstants.OCCUPANT_NOT_FOUND);
		Occupant updateStatus=getOccupant.get();
		updateStatus.setOccupantStatus(requestDto.getOccupantStatus());
		updateStatus.setModifiedBy(requestDto.getUserId());
		updateStatus.setModifiedDate(new Date());
		occupantRepo.save(updateStatus);
		responseDto.setError(false);
		responseDto.setMessage(OccupantConstants.OCCUPANT_STATUS);
		responseDto.setStatus(HttpServletResponse.SC_OK);
		return responseDto;
	}

	public ListResponse<List<OccupantResponseDto>> filterListOccupant(List<String> filters, String sortByFieldName,String sortType, Integer page, Integer size, String propertyId) {
		ListResponse<List<OccupantResponseDto>> responseDto=new ListResponse<>();
		String occupantSearchFields="";
		List<Occupant> totalOccupantDetails=null;
		List<OccupantResponseDto> occupantDetails=new ArrayList<>();
		List<OccupantResponseDto> lstEntity=new ArrayList<>();

		BasicQuery executeQuery=null;
		String filter=null;
		String soryBy=null;
		String id=null;

		if(filters.size() >0 && sortByFieldName != "" && sortType != "") {
			filter=filters.stream().map(Object::toString).collect(Collectors.joining("},{","{","}"));
			id="{"+"'property_id'"+":"+"'"+propertyId+"'"+"},";
			filter=id.concat(filter);
			occupantSearchFields=OccupantConstants.OCCUPANT_DEFAULT_QUERY;
			soryBy="{"+sortByFieldName+":"+sortType+"}";
			occupantSearchFields=String.format(occupantSearchFields, filter,soryBy);

		}else if (filters.size() >0 && sortByFieldName == "") {
			filter=filters.stream().map(Object::toString).collect(Collectors.joining("},{","{","}"));
			id="{"+"'property_id'"+":"+"'"+propertyId+"'"+"},";
			filter=id.concat(filter);
			occupantSearchFields=OccupantConstants.OCCUPANT_DEFAULT_FILTER_QUERY;
			occupantSearchFields=String.format(occupantSearchFields, filter);


		}else if (sortByFieldName != "" && sortType != "") {
			id="{"+"'property_id'"+":"+"'"+propertyId+"'"+"},";
			soryBy="{"+sortByFieldName+":"+sortType+"}";
			occupantSearchFields=OccupantConstants.OCCUPANT_DEFAULT_SORT_QUERY;
			occupantSearchFields=String.format(occupantSearchFields,id, soryBy);
		}else {
			id="{"+"'property_id'"+":"+"'"+propertyId+"'"+"},";
			occupantSearchFields=OccupantConstants.OCCUPANT_DEFAULT_ACTIVE_QUERY;
			occupantSearchFields=String.format(occupantSearchFields, id);}

		executeQuery = new BasicQuery(occupantSearchFields);
		totalOccupantDetails = mongoOperation.find(executeQuery, Occupant.class);


		if(totalOccupantDetails.isEmpty()) {
			responseDto.setData(lstEntity);
			responseDto.setCount(0);
			responseDto.setError(false);
			responseDto.setMessage(OccupantConstants.OCCUPANT_NOT_FOUND);
			responseDto.setStatus(HttpServletResponse.SC_OK);
			responseDto.setTotalRecords(0);
			return 	responseDto;	
		}
		totalOccupantDetails.forEach(u -> lstEntity.add(convertToOccupantDetailsDto(u)));

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

			occupantDetails = lstEntity.subList(fromIndex , toIndex);
		}
		occupantDetails = occupantDetails.stream().collect(Collectors.toList());
		responseDto.setData(occupantDetails);
		responseDto.setCount(occupantDetails.size());
		responseDto.setTotalRecords(lstEntity.size());
		responseDto.setError(false);
		responseDto.setMessage(OccupantConstants.OCCUPANT_List);

		return responseDto;

	}


}
