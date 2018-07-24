package com.tcl.devices.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import com.tcl.devices.dto.ConsumptionDetailUpdateReqDto;
import com.tcl.devices.dto.ConsumptionResponseDto;
import com.tcl.devices.exception.DeviceAlreadyPresent;
import com.tcl.devices.exception.NoDataFoundException;
import com.tcl.devices.model.ConsumptionDetail;
import com.tcl.devices.repository.ConsumptionDetailsRepo;
import com.tcl.devices.response.ListResponse;
import com.tcl.devices.response.Response;
import com.tcl.devices.util.ConsumptionConstants;
import com.tcl.devices.util.DeviceConstants;

@Service
public class ConsumptionService {
	
	@Autowired
	private ModelMapper modelMapper;

	/*
	 * dependency injection and object initializtion using Autowired annotation
	 */
	
	@Autowired
	private MongoOperations mongoOperation;

	@Autowired
	private ConsumptionDetailsRepo consumptionDetailsRepo;

	

	public ListResponse<List<ConsumptionResponseDto>> filterListConsumptionDetails(List<String> filters,String sortByFieldName, String sortType, Integer page, Integer size) {
		ListResponse<List<ConsumptionResponseDto>> responseDto=new ListResponse<>();
		List<ConsumptionResponseDto> lstEntity=new ArrayList<>();

		String consumptionSearchFields="";
		List<ConsumptionDetail> totalConsumptionDetails=null;
		List<ConsumptionResponseDto> consumptionDetails=new ArrayList<>();

		BasicQuery executeQuery=null;
		String filter=null;
		String soryBy=null;

		if(filters.size() >0 && sortByFieldName != "" && sortType != "") {
			filter=filters.stream().map(Object::toString).collect(Collectors.joining("},{","{","}"));
			consumptionSearchFields=ConsumptionConstants.CONSUMPTION_DEFAULT_QUERY;
			soryBy="{"+sortByFieldName+":"+sortType+"}";
			consumptionSearchFields=String.format(consumptionSearchFields, filter,soryBy);

		}else if (filters.size() >0 && sortByFieldName == "") {
			filter=filters.stream().map(Object::toString).collect(Collectors.joining("},{","{","}"));
			consumptionSearchFields=ConsumptionConstants.CONSUMPTION_DEFAULT_FILTER_QUERY;
			consumptionSearchFields=String.format(consumptionSearchFields, filter);


		}else if (sortByFieldName != "" && sortType != "") {
			soryBy="{"+sortByFieldName+":"+sortType+"}";
			consumptionSearchFields=ConsumptionConstants.CONSUMPTION_DEFAULT_SORT_QUERY;
			consumptionSearchFields=String.format(consumptionSearchFields, soryBy);
		}else

			consumptionSearchFields=ConsumptionConstants.CONSUMPTION_DEFAULT_ACTIVE_QUERY;
		executeQuery = new BasicQuery(consumptionSearchFields);
		totalConsumptionDetails = mongoOperation.find(executeQuery, ConsumptionDetail.class);


		if(totalConsumptionDetails.isEmpty()) {
				responseDto.setData(lstEntity);
				responseDto.setCount(0);
				responseDto.setError(false);
				responseDto.setMessage(ConsumptionConstants.CONSUMPTION_NOT_FOUND);
				responseDto.setStatus(HttpServletResponse.SC_OK);
				responseDto.setTotalRecords(0);
				return 	responseDto;
		}

		totalConsumptionDetails.forEach(u -> lstEntity.add(convertToConsumptionDetailsDto(u)));

		if (page == 0)
			page = 1;
		else
			page++;

		Long startNumber = (long) (((size * page) - size) + 1);
		Long endNumber = (long) (size * page);
		if (lstEntity.size() >= 0) {

			int fromIndex = (startNumber.intValue() - 1);
			int toIndex = endNumber.intValue();

			if (lstEntity.size() <= endNumber.intValue()) {
				toIndex = lstEntity.size();
			}

			consumptionDetails = lstEntity.subList(fromIndex, toIndex);
		}
		consumptionDetails = consumptionDetails.stream().collect(Collectors.toList());
		responseDto.setData(consumptionDetails);
		responseDto.setCount(consumptionDetails.size());
		responseDto.setTotalRecords(lstEntity.size());
		responseDto.setError(false);
		responseDto.setMessage(ConsumptionConstants.CONSUMPTION_LIST);
		responseDto.setStatus(HttpServletResponse.SC_OK);

		return responseDto;

	}


	private ConsumptionResponseDto convertToConsumptionDetailsDto(ConsumptionDetail consumptionDetail) {
		return modelMapper.map(consumptionDetail, ConsumptionResponseDto.class);

	}


	public Response<Boolean> updateConsumptionDetail(ConsumptionDetailUpdateReqDto requestDto) {
		Response<Boolean> responseDto=new Response<>();
		Optional<ConsumptionDetail> consumptionDetail=consumptionDetailsRepo.findByPropertyIdAndIsActive(requestDto.getPropertyId(),true);
		if(!consumptionDetail.isPresent())
			throw new NoDataFoundException(ConsumptionConstants.CONSUMPTION_NOT_FOUND);
		
		ConsumptionDetail updateConsumption=consumptionDetail.get();
		updateConsumption.setManualAdjustment(requestDto.getManualAdjustment());
		updateConsumption.setConsumption(requestDto.getConsumption());
		updateConsumption.setModifiedDate(new Date());
		consumptionDetailsRepo.save(updateConsumption);
		responseDto.setError(false);
		responseDto.setMessage(ConsumptionConstants.CONSUMPTION_UPDATE_SUCCESSFULL);
		responseDto.setStatus(HttpServletResponse.SC_OK);
		return responseDto;
		
	}

}
