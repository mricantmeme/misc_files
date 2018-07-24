package com.dicv.truck.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dicv.truck.dto.TripDataListDto;
import com.dicv.truck.dto.TripDetailsListDto;
import com.dicv.truck.dto.TripPickUpAndDispatchList;
import com.dicv.truck.dto.TripRecordDto;
import com.dicv.truck.dto.TripRecordListDto;
import com.dicv.truck.exception.InvalidValueException;
import com.dicv.truck.exception.ServerException;
import com.dicv.truck.service.TripServices;
import com.dicv.truck.utility.DicvConstants;

@RestController
@RequestMapping("/onlinefleetmngmt/dicv")
public class TripController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TripController.class);

	@Autowired
	private TripServices tripServices;

	@GetMapping("/getTripData")
	public @ResponseBody TripDataListDto getTripData(@ModelAttribute TripRecordDto tripRecord) throws ServerException {

		return tripServices.getTripData(tripRecord);

	}

	@GetMapping("/getTripListWithDistance")
	public @ResponseBody TripRecordListDto getTripListWithDistance(@ModelAttribute TripRecordDto tripRecord) {
		if (null != tripRecord.getUserId()) {
			return tripServices.getTripListWithDistance(tripRecord);
		} else {
			throw new InvalidValueException(DicvConstants.INVALIDINPUT_EXCEPTION_MSG);
		}

	}

	@GetMapping("/getTripList")
	public @ResponseBody TripRecordListDto getTrip(@ModelAttribute TripRecordDto tripRecord) {
		if (null != tripRecord.getUserId()) {
			return tripServices.getTripList(tripRecord);
		} else {
			throw new InvalidValueException(DicvConstants.INVALIDINPUT_EXCEPTION_MSG);
		}

	}

	@GetMapping("/getTripDetails")
	public @ResponseBody TripDetailsListDto getTripDetails(@RequestParam Long scheduledTripId,
			@RequestParam Integer userId, @RequestParam(required = false) String status) {
		LOGGER.info("Trip Details " + scheduledTripId);
		return tripServices.getTripDetails(userId, scheduledTripId);
	}

	@GetMapping("/getPickUpAndDispatch")
	public @ResponseBody TripPickUpAndDispatchList getPickUpAndDispatch(@ModelAttribute TripRecordDto tripRecord)
			throws ServerException {

		return tripServices.getPickUpAndDispatch(tripRecord);

	}

}
