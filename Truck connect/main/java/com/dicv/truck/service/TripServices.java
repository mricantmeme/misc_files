package com.dicv.truck.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dicv.truck.dto.TripDataDto;
import com.dicv.truck.dto.TripDataListDto;
import com.dicv.truck.dto.TripDetailDto;
import com.dicv.truck.dto.TripDetailsListDto;
import com.dicv.truck.dto.TripDriverAnalysisDto;
import com.dicv.truck.dto.TripInsightsSpeedingLocationDto;
import com.dicv.truck.dto.TripPickUpAndDispatchList;
import com.dicv.truck.dto.TripPickUpDispatchData;
import com.dicv.truck.dto.TripPickupDispatchDtlsDto;
import com.dicv.truck.dto.TripRecordDto;
import com.dicv.truck.dto.TripRecordListDto;
import com.dicv.truck.exception.DataNotFoundException;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.InvoicePhoto;
import com.dicv.truck.model.LoadCategory;
import com.dicv.truck.model.ScheduledTrip;
import com.dicv.truck.model.Trip;
import com.dicv.truck.model.TripStopLocation;
import com.dicv.truck.model.Vehicle;
import com.dicv.truck.repo.InvoicePhotoRepo;
import com.dicv.truck.repo.TripDao;
import com.dicv.truck.repo.TripStopLocationRepo;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.EnumUserType;

@Service
public class TripServices {

	@Autowired
	private UserService userService;

	@Autowired
	private TripDao tripDao;

	@Autowired
	private TripStopLocationRepo tripStopRepo;

	@Autowired
	private InvoicePhotoRepo invoiceRepo;

	private static final Logger LOGGER = LoggerFactory.getLogger(TripServices.class);

	public TripDataListDto getTripData(TripRecordDto tripRecord) {

		TripDataListDto tripDatas = new TripDataListDto();
		TripDetailsListDto tripDetailListDto = new TripDetailsListDto();
		List<TripDetailDto> tripDetailsList = new ArrayList<TripDetailDto>();

		List<TripDataDto> tripDataList = new ArrayList<TripDataDto>();

		TripRecordListDto tripRecordListDto = new TripRecordListDto();
		DicvUser user = userService.getUser(tripRecord.getUserId(), "getTripData");
		try {
			List<Trip> tripRecords = tripDao.getTripList(tripRecord, user.getUserType().getUserType());

			if (CollectionUtils.isEmpty(tripRecords)) {
				throw new DataNotFoundException(DicvConstants.DATA_NOT_FOUND_MSG);
			}

			int tripRecordsSize = tripRecords.size();

			if (tripRecordsSize > (tripRecord.getEndRow() - tripRecord.getStartRow())) {
				tripRecordListDto.setNextPageRequired(true);
				tripRecords.remove(tripRecordsSize - 1);
			}
			TripRecordDto tripRecordDto = new TripRecordDto();
			for (Trip trip : tripRecords) {
				tripRecordDto = new TripRecordDto();
				if (null != trip.getScheduledTrip().getFromDate()) {
					tripRecordDto.setFromDateStr(DicvUtil.dateToString(tripRecordDto.getFromDate()));
				}
				if (null != trip.getScheduledTrip().getToDate()) {
					tripRecordDto.setToDateStr(DicvUtil.dateToString(tripRecordDto.getToDate()));
				}
				TripDetailDto tripDetail = new TripDetailDto();
				tripDetail.setVehicleId(trip.getVehicle().getVehicleId());// 1
				tripDetail.setVehicleRegNum(trip.getVehicle().getRegistrationId());// 2
				if (trip.getTripDriverUser() != null) {
					tripDetail.setDriverId(trip.getTripDriverUser().getUserId());// 3
					tripDetail.setDriverName(trip.getTripDriverUser().getUserName());// 4
				}
				tripDetail.setScheduledTripId(trip.getTripId());// 5
				tripDetail.setIsSms(trip.getScheduledTrip().getIssms());// 8
				tripDetail.setIsEmail(trip.getScheduledTrip().getIsemail());// 9
				tripDetail.setScheduledTripFlag(trip.getScheduledTrip().getScheduledTripFlag());// 10
				TripPickupDispatchDtlsDto tripPickupDispatch = getTripPickupDispatch(trip.getScheduledTrip(),
						trip.getVehicle());
				tripDetail.setTripPickupDispatchDtlsDto(tripPickupDispatch);// 11
				TripDriverAnalysisDto tripDriverAnalysis = new TripDriverAnalysisDto();
				tripDetail.setFromAddress(trip.getFromAddress());
				tripDetail.setToAddress(trip.getToAddress());
				tripDetail.setToCity(trip.getToCity());
				tripDetail.setFromCity(trip.getFromCity());
				tripDetail.setTripDriverAnalysis(tripDriverAnalysis);// 12

				tripDetail.setTripId(trip.getTripId());

				if (trip.getVehicleIdleTime() != null) {
					tripDetail.setVehicleIdleTime(
							DicvUtil.convertFromMillisToHoursMinsSecs(trip.getVehicleIdleTime().longValue() * 1000));
				}
				if (trip.getAverageVehicleSpeed() != null) {
					tripDetail.setAverageVehicleSpeed(trip.getAverageVehicleSpeed());
				}
				if (trip.getTripDistance() != null) {
					tripDetail.setDistanceTravelled(trip.getTripDistance().intValue());
				}
				if (trip.getElapsedTime() != null) {
					tripDetail.setTimeElapsed(
							DicvUtil.convertFromMillisToHoursMinsSecs(trip.getElapsedTime().longValue() * 1000));
				}
				if (trip.getNosOfStop() != null) {
					tripDetail.setNosOfStop(trip.getNosOfStop());
				}
				if (trip.getEngineRunHours() != null) {
					tripDetail.setEngineRunHours(
							DicvUtil.convertFromMillisToHoursMinsSecs(trip.getEngineRunHours().longValue() * 1000));
				}
				if (trip.getTripStartTime() != null) {
					tripDetail.setActualTripStartDate(trip.getTripStartTime());
				}

				if (trip.getTripEndTime() != null) {
					tripDetail.setActualTripEndDate(trip.getTripEndTime());
				}
				tripDetailsList.add(tripDetail);
				tripDetailListDto.setTripDetails(tripDetailsList);
				TripDataDto tripDataDto = new TripDataDto();
				copyTripRecord(tripRecordDto, tripDataDto, trip.getTripId());
				tripDataDto.setTripDetails(tripDetail);
				tripDataList.add(tripDataDto);
			}

			if (tripDataList.size() <= 0) {
				throw new DataNotFoundException(DicvConstants.DATA_NOT_FOUND_MSG);
			}
			tripDatas.setTripRecords(tripDataList);
			return tripDatas;
		} catch (Exception e) {
			LOGGER.error("Error in Trip Data List  ", e);
			tripDatas.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			tripDatas.setMessage(DicvConstants.EXCEPTION_MSG);
			return tripDatas;
		}
	}

	private void copyTripRecord(TripRecordDto record, TripDataDto tripData, Long tripId) {
		tripData.setDriverId(record.getDriverId());
		tripData.setEndPointLat(record.getEndPointLat());
		tripData.setEndPointLong(record.getEndPointLong());
		tripData.setEndRow(record.getEndRow());
		tripData.setFromAddress(record.getFromAddress());
		tripData.setFromDate(record.getFromDate());
		tripData.setFromDateStr(record.getFromDateStr());
		tripData.setUserId(record.getUserId());
		tripData.setRegistrationId(record.getRegistrationId());
		tripData.setStartPointLat(record.getStartPointLat());
		tripData.setStartPointLong(record.getStartPointLong());
		tripData.setStartRow(record.getStartRow());
		tripData.setDriverId(record.getDriverId());
		tripData.setTripStatus(record.getTripStatus());
		tripData.setToAddress(record.getToAddress());
		tripData.setToDate(record.getToDate());
		tripData.setFromCity(record.getFromCity());
		tripData.setToCity(record.getToCity());
		tripData.setToDateStr(record.getToDateStr());
		tripData.setTripId(tripId);
		tripData.setVehicleId(record.getVehicleId());
	}

	public TripPickupDispatchDtlsDto getTripPickupDispatch(ScheduledTrip scheduledTrip, Vehicle vehicle) {

		TripPickupDispatchDtlsDto tripPickupDispatch = new TripPickupDispatchDtlsDto();
		tripPickupDispatch.setScheduledTripId(scheduledTrip.getScheduledTripId());

		if (null != vehicle) {
			tripPickupDispatch.setVehicleRegNum(vehicle.getRegistrationId());
			tripPickupDispatch.setVehicleDescription(vehicle.getDescription());
		}

		if (null != scheduledTrip.getFromDate()) {
			tripPickupDispatch.setPickupDate(DicvUtil.dateToStrings(scheduledTrip.getFromDate()));
		}
		if (null != scheduledTrip.getToDate()) {
			tripPickupDispatch.setDeliveryDate(DicvUtil.dateToStrings(scheduledTrip.getToDate()));
		}
		tripPickupDispatch.setTripStatus(scheduledTrip.getTripStatus());
		tripPickupDispatch.setScheduledTripId(scheduledTrip.getScheduledTripId());
		tripPickupDispatch.setVehicleRegNum(vehicle.getRegistrationId());

		if (null != scheduledTrip.getFromDate()) {
			tripPickupDispatch.setPickupDate(DicvUtil.dateToStrings(scheduledTrip.getFromDate()));
		}
		if (null != scheduledTrip.getToDate()) {
			tripPickupDispatch.setDeliveryDate(DicvUtil.dateToStrings(scheduledTrip.getToDate()));
		}

		tripPickupDispatch.setTripStatus(scheduledTrip.getTripStatus());

		return tripPickupDispatch;

	}

	public TripRecordListDto getTripList(TripRecordDto tripRecord) {
		TripRecordListDto tripRecordListDto = new TripRecordListDto();
		try {
			List<TripRecordDto> tripRecords = new ArrayList<TripRecordDto>();

			DicvUser user = userService.getUser(tripRecord.getUserId(), "getTripList");

			List<Trip> tripList = tripDao.getTripList(tripRecord, user.getUserType().getUserType());

			if (tripList == null || tripList.isEmpty()) {
				tripRecordListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				tripRecordListDto.setMessage("Trip Data Not Available");
				return tripRecordListDto;
			}
			return getTripData(tripRecord, tripRecordListDto, tripRecords, tripList);

		} catch (Exception ex) {
			LOGGER.error("Error in Trip List  ", ex);
			tripRecordListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			tripRecordListDto.setMessage(DicvConstants.EXCEPTION_MSG);
			return tripRecordListDto;
		}

	}

	public TripRecordListDto getTripListWithDistance(TripRecordDto tripRecord) {
		TripRecordListDto tripRecordListDto = new TripRecordListDto();
		try {
			List<TripRecordDto> tripRecords = new ArrayList<TripRecordDto>();

			DicvUser user = userService.getUser(tripRecord.getUserId(), "getTripList");

			List<Trip> tripList = tripDao.getTripListWithDistance(tripRecord, user.getUserType().getUserType());

			if (tripList == null || tripList.size() < 0) {
				tripRecordListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				tripRecordListDto.setMessage("Trip Data Not Available");
				return tripRecordListDto;
			}

			return getTripData(tripRecord, tripRecordListDto, tripRecords, tripList);

		} catch (Exception ex) {
			LOGGER.error("Error in getTripListWithDistance List  ", ex);
			tripRecordListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			tripRecordListDto.setMessage(DicvConstants.EXCEPTION_MSG);
			return tripRecordListDto;
		}

	}

	private TripRecordListDto getTripData(TripRecordDto tripRecord, TripRecordListDto tripRecordListDto,
			List<TripRecordDto> tripRecords, List<Trip> tripList) {
		int tripRecordsSize = tripList.size();

		if (tripRecordsSize > (tripRecord.getEndRow() - tripRecord.getStartRow())) {
			tripRecordListDto.setNextPageRequired(true);
			tripList.remove(tripRecordsSize - 1); // remove last element
		}
		for (Trip trip : tripList) {
			TripRecordDto dto = new TripRecordDto();
			dto.setTripStatus(trip.getTripStatus());
			dto.setStartPointLat(trip.getStartLocationLat());
			dto.setStartPointLong(trip.getStartLocationLong());
			dto.setEndPointLat(trip.getStopLocationLat());
			dto.setEndPointLong(trip.getStopLocationLong());
			dto.setScheduledTripId(trip.getTripId());
			if (trip.getVehicle() != null) {
				dto.setVehicleId(trip.getVehicle().getVehicleId());

				dto.setVehicleDescription(trip.getVehicle().getDescription());
				dto.setRegistrationId(trip.getVehicle().getRegistrationId());
				if (trip.getVehicle().getGpsImei() != null)
					dto.setGpsImei(trip.getVehicle().getGpsImei().getGpsImei());
				if (trip.getVehicle().getDicvUser() != null)
					dto.setUserId(trip.getVehicle().getDicvUser().getUserId());
			}

			if (trip.getTripDriverUser() != null) {
				dto.setDriverId(trip.getTripDriverUser().getUserId());
			}
			dto.setScheduledTripId(trip.getTripId());
			dto.setScheduledTripFlag(0);
			dto.setTripId(trip.getTripId());
			if (trip.getScheduledTrip() != null)
				dto.setScheduledTripFlag(trip.getScheduledTrip().getScheduledTripFlag());

			if (DicvConstants.TRIPSTATUS_COMPLETED.equals(trip.getTripStatus())) {
				if (trip.getTripDistance() != null)
					dto.setDistance(trip.getTripDistance().intValue());
				if (trip.getTripStartTime() != null) {
					dto.setActualTripStartDate(trip.getTripStartTime());
				}
				if (trip.getTripEndTime() != null) {
					dto.setActualTripEndDate(trip.getTripEndTime());
				}
			}
			if (trip.getTripStartTime() != null) {
				dto.setFromDateStr(DicvUtil.dateToStrings(trip.getTripStartTime()));
				dto.setFromDate(trip.getTripStartTime());
			}
			if (trip.getTripEndTime() != null) {
				dto.setToDateStr(DicvUtil.dateToStrings(trip.getTripEndTime()));
				dto.setToDate(trip.getTripEndTime());
			}
			dto.setFromAddress(trip.getFromAddress());
			dto.setToAddress(trip.getToAddress());
			dto.setFromCity(trip.getFromCity());
			dto.setToCity(trip.getToCity());
			tripRecords.add(dto);
		}
		tripRecordListDto.setStatus(HttpServletResponse.SC_OK);
		tripRecordListDto.setMessage(DicvConstants.DATA_FOUND_MSG);
		tripRecordListDto.setTripRecords(tripRecords);
		return tripRecordListDto;
	}

	public TripDetailsListDto getTripDetails(Integer userId, Long tripId) {
		TripDetailsListDto tripDetailListDto = new TripDetailsListDto();
		try {
			TripDetailDto tripDetail = new TripDetailDto();
			List<TripDetailDto> tripDetailsList = new ArrayList<TripDetailDto>();
			Vehicle vehicle = null;
			DicvUser user = userService.getUser(userId, "getTripDetails");
			boolean userRoleCheck = false;
			if (!user.getUserType().getUserType().equals(EnumUserType.ROOTADMIN.getUserType())) {
				userRoleCheck = true;
			}

			Trip trip = tripDao.getTripDetails(userId, tripId, userRoleCheck);
			if (trip == null) {
				tripDetailListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				tripDetailListDto.setMessage("No Trip Data");
				return tripDetailListDto;
			}

			if (null != trip.getVehicle()) {
				vehicle = trip.getVehicle();

				tripDetail.setVehicleId(vehicle.getVehicleId());

				tripDetail.setVehicleRegNum(vehicle.getRegistrationId());
				tripDetail.setVehicleDescription(vehicle.getDescription());
				if (trip.getTripDriverUser() != null) {
					tripDetail.setDriverId(trip.getTripDriverUser().getUserId());
					tripDetail.setDriverName(
							trip.getTripDriverUser().getUserName());
				}
			}
			tripDetail.setScheduledTripId(trip.getTripId());

			if (null != trip.getTripStartTime()) {
				tripDetail.setFromDateStr(DicvUtil.dateToStrings(trip.getTripStartTime()));
			}

			if (null != trip.getTripEndTime()) {
				tripDetail.setToDateStr(DicvUtil.dateToStrings(trip.getTripEndTime()));
			}

			tripDetail.setScheduledTripFlag(-1);
			if (trip.getScheduledTrip() != null) {

				setScheduledTripDetails(tripDetail, trip);

			}
			if (trip.getTripDistance() != null)
				tripDetail.setDistance(trip.getTripDistance().intValue());

			tripDetail.setFromAddress(trip.getFromAddress());
			tripDetail.setToAddress(trip.getToAddress());
			tripDetail.setFromCity(trip.getFromCity());
			tripDetail.setToCity(trip.getToCity());
			tripDetail.setScheduledTripFlag(0);
			tripDetail.setStartLocationLat(trip.getStartLocationLat());
			tripDetail.setStartLocationLong(trip.getStartLocationLong());
			tripDetail.setStopLocationLat(trip.getStopLocationLat());
			tripDetail.setStopLocationLong(trip.getStopLocationLong());

			TripDriverAnalysisDto tripDriverAnalysis = getTripDriverAnalysis(trip);

			tripDetail.setTripDriverAnalysis(tripDriverAnalysis);

			TripInsightsSpeedingLocationDto tripInsightsSpeedingLocation = getInsightSpeedingLocation(trip);

			tripDetail.setTripInsightsSpeedingLocation(tripInsightsSpeedingLocation);

			tripDetail.setTripId(trip.getTripId());

			if (trip.getEngineIdleTimeRpm() != null) {
				tripDetail.setVehicleIdleTime(
						DicvUtil.convertFromMillisToHoursMinsSecs(trip.getEngineIdleTimeRpm().longValue() * 1000));
			}
			if (trip.getAverageVehicleSpeed() != null) {
				tripDetail.setAverageVehicleSpeed(trip.getAverageVehicleSpeed());
			}
			if (trip.getTripDistance() != null) {
				tripDetail.setDistanceTravelled(trip.getTripDistance().intValue());
			}
			if (trip.getElapsedTime() != null) {
				tripDetail.setTimeElapsed(
						DicvUtil.convertFromMillisToHoursMinsSecs(trip.getElapsedTime().longValue() * 1000));
			}

			setTripStopLocationDetails(tripDetail, trip);

			if (trip.getEngineRunHours() != null) {
				tripDetail.setEngineRunHours(
						DicvUtil.convertFromMillisToHoursMinsSecs(trip.getEngineRunHours().longValue() * 1000));
			}
			if (null != trip.getDrivingTime()) {
				tripDetail.setDrivingTime(
						DicvUtil.convertFromMillisToHoursMinsSecs(trip.getDrivingTime().longValue() * 1000));
			}
			if (trip.getTripStartTime() != null) {
				tripDetail.setActualTripStartDate(trip.getTripStartTime());
			}

			if (trip.getTripEndTime() != null) {
				tripDetail.setActualTripEndDate(trip.getTripEndTime());
			}
			tripDetailsList.add(tripDetail);
			tripDetailListDto.setTripDetails(tripDetailsList);
			tripDetailListDto.setStatus(HttpServletResponse.SC_OK);
			tripDetailListDto.setMessage(DicvConstants.DATA_FOUND_MSG);
			return tripDetailListDto;
		} catch (Exception ex) {
			LOGGER.error("Error in getTripDetails List  ", ex);
			tripDetailListDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			tripDetailListDto.setMessage(DicvConstants.EXCEPTION_MSG);
			return tripDetailListDto;

		}

	}

	private TripDriverAnalysisDto getTripDriverAnalysis(Trip trip) {
		TripDriverAnalysisDto tripDriverAnalysis = new TripDriverAnalysisDto();
		if (trip.getEconomicDriving() != null)
			tripDriverAnalysis.setEconomicDriving(trip.getEconomicDriving().toString());
		if (trip.getSpeeding() != null)
			tripDriverAnalysis.setSpeedAdherencePercent(trip.getSpeeding().toString());
		if (trip.getIdling() != null)
			tripDriverAnalysis.setEngineNonIdleTimePercent(trip.getIdling().toString());
		return tripDriverAnalysis;
	}

	public TripInsightsSpeedingLocationDto getInsightSpeedingLocation(Trip trip) {

		TripInsightsSpeedingLocationDto tripInsightsSpeedingLocation = new TripInsightsSpeedingLocationDto();
		if (null != trip.getMaximumSpeed()) {
			tripInsightsSpeedingLocation.setMaximumSpeed(trip.getMaximumSpeed());
		}
		if (null != trip.getMaxSpeedLat()) {
			tripInsightsSpeedingLocation.setMaxSpeedLat(trip.getMaxSpeedLat());
		}
		if (null != trip.getMaxSpeedLong()) {
			tripInsightsSpeedingLocation.setMaxSpeedLong(trip.getMaxSpeedLong());
		}
		if (null != trip.getEconomyBandDistance()) {
			tripInsightsSpeedingLocation.setEconomyBandDistance(trip.getEconomyBandDistance());
		}
		if (null != trip.getMaxSpeedTime()) {
			tripInsightsSpeedingLocation.setMaxSpeedTime(trip.getMaxSpeedTime());
		}
		if (null != trip.getTimeIn0To20km()) {
			tripInsightsSpeedingLocation.setSpeedBand0To20(
					DicvUtil.convertFromMillisToHoursMinsSecs(trip.getTimeIn0To20km().longValue() * 1000));
		}
		if (null != trip.getTimeIn21To40km()) {
			tripInsightsSpeedingLocation.setSpeedBand21To40(
					DicvUtil.convertFromMillisToHoursMinsSecs(trip.getTimeIn21To40km().longValue() * 1000));
		}
		if (null != trip.getTimeIn41To60km()) {
			tripInsightsSpeedingLocation.setSpeedBand41To60(
					DicvUtil.convertFromMillisToHoursMinsSecs(trip.getTimeIn41To60km().longValue() * 1000));
		}
		if (null != trip.getTimeIn61To80km()) {
			tripInsightsSpeedingLocation.setSpeedBand61To80(
					DicvUtil.convertFromMillisToHoursMinsSecs(trip.getTimeIn61To80km().longValue() * 1000));
		}
		if (null != trip.getTimeInOver80km()) {
			tripInsightsSpeedingLocation.setSpeedBandOver80(
					DicvUtil.convertFromMillisToHoursMinsSecs(trip.getTimeInOver80km().longValue() * 1000));
		}
		return tripInsightsSpeedingLocation;
	}

	private void setScheduledTripDetails(TripDetailDto tripDetail, Trip trip) {
		if (null != trip.getScheduledTrip().getVolume()) {
			tripDetail.setVolume(trip.getScheduledTrip().getVolume());
		}
		if (null != trip.getScheduledTrip().getLoadWeight()) {
			tripDetail.setLoadWeight(trip.getScheduledTrip().getLoadWeight());
		}
		if (null != trip.getScheduledTrip().getRevenue()) {
			tripDetail.setRevenue(trip.getScheduledTrip().getRevenue());
		}
		if (null != trip.getScheduledTrip().getCustomerName())
			tripDetail.setCustomerName(trip.getScheduledTrip().getCustomerName());
		if (null != trip.getScheduledTrip().getLoadCategory()) {

			LoadCategory loadCategory = trip.getScheduledTrip().getLoadCategory();
			tripDetail.setLoadCategoryId(loadCategory.getLoadCategoryId());
			tripDetail.setLoadCategoryName(loadCategory.getLoadCategoryName());

		}
		if (null != trip.getScheduledTrip().getDriverCost()) {
			tripDetail.setDriverCost(trip.getScheduledTrip().getDriverCost());
		}
		if (null != trip.getScheduledTrip().getFuelCost()) {
			tripDetail.setFuelCost(trip.getScheduledTrip().getFuelCost());
		}
		if (null != trip.getScheduledTrip().getOperationalCost()) {
			tripDetail.setOperationalCost(trip.getScheduledTrip().getOperationalCost());
		}
		if (null != trip.getScheduledTrip().getDistance()) {
			tripDetail.setDistance(trip.getScheduledTrip().getDistance().intValue());
		}
		if (null != trip.getScheduledTrip().getDuration()) {
			tripDetail.setDuration(trip.getScheduledTrip().getDuration());
		}
	}

	private void setTripStopLocationDetails(TripDetailDto tripDetail, Trip trip) {
		List<TripStopLocation> list = tripStopRepo.getTripStopLocation(trip.getTripId());
		tripDetail.setNosOfStop(0);
		tripDetail.setStopTime(String.format("%02d:%02d:%02d", 0, 0, 0));
		if (list != null && list.size() > 0) {
			Integer noOfStop = 0, stop = 0;
			for (TripStopLocation tripStopLocation : list) {
				if (tripStopLocation.getStartTime() != null && tripStopLocation.getStopTime() != null) {

					stop = (int) (stop + tripStopLocation.getStopTime().getTime()
							- tripStopLocation.getStartTime().getTime());
					noOfStop = noOfStop + 1;
				}
			}
			if (stop > 0) {
				tripDetail.setNosOfStop(noOfStop);
				stop = stop / 1000;
				Integer hours = (int) stop / 3600;
				Integer remainder = (int) stop - hours * 3600;
				Integer mins = remainder / 60;
				remainder = remainder - mins * 60;
				Integer secs = remainder;
				tripDetail.setStopTime(String.format("%02d:%02d:%02d", hours, mins, secs));
			}
		}
	}

	public TripPickUpAndDispatchList getPickUpAndDispatch(TripRecordDto tripRecord) {
		TripPickUpAndDispatchList tripDatas = new TripPickUpAndDispatchList();
		List<TripPickUpDispatchData> tripList = new ArrayList<TripPickUpDispatchData>();
		DicvUser user = userService.getUser(tripRecord.getUserId(), "getPickupAndDispatch");
		try {
			List<Trip> tripRecords = tripDao.getTripList(tripRecord, user.getUserType().getUserType());
			if (CollectionUtils.isEmpty(tripRecords)) {
				tripDatas.setStatus(HttpServletResponse.SC_OK);
				tripDatas.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				return tripDatas;
			}
			List<Long> tripIds = new ArrayList<Long>();
			TripPickUpDispatchData tripPickUpDispatchData = new TripPickUpDispatchData();
			for (Trip trip : tripRecords) {
				tripIds.add(trip.getTripId());
			}
			List<InvoicePhoto> photoList = invoiceRepo.getInvoicePhoto(tripIds);
			Map<Long, List<InvoicePhoto>> map = new HashMap<Long, List<InvoicePhoto>>();
			List<InvoicePhoto> list = new ArrayList<InvoicePhoto>();
			for (Long tripId : tripIds) {
				list = new ArrayList<InvoicePhoto>();
				map.put(tripId, list);
				for (InvoicePhoto inPhoto : photoList) {
					if (inPhoto.getDispatch().getTrip().getTripId() == tripId) {
						list = map.get(tripId);
						list.add(inPhoto);
						map.put(tripId, list);
					}
				}
			}

			for (Trip trip : tripRecords) {
				tripPickUpDispatchData = new TripPickUpDispatchData();
				tripPickUpDispatchData.setTripId(trip.getTripId());
				tripPickUpDispatchData.setScheduleTripId(trip.getTripId());
				tripPickUpDispatchData.setTripStatus(trip.getTripStatus());
				tripPickUpDispatchData.setVehicleName(trip.getVehicle().getRegistrationId());
				list = map.get(trip.getTripId());
				if (list != null && list.size() > 0) {
					for (InvoicePhoto invoicePhoto : photoList) {
						if (invoicePhoto.getDispatch().getDispatchType() == DicvConstants.DISPATCH_MSG) {
							tripPickUpDispatchData.setDeliveryDate(DicvUtil.dateToStrings(trip.getTripStartTime()));
							tripPickUpDispatchData
									.setDeliveryFile(new String(Base64.encodeBase64(invoicePhoto.getInvoicePhoto())));
						}
						if (invoicePhoto.getDispatch().getDispatchType() == DicvConstants.PICKUP_MSG) {
							tripPickUpDispatchData.setPickUpDate(DicvUtil.dateToStrings(trip.getTripEndTime()));
							tripPickUpDispatchData
									.setPickUpFile(new String(Base64.encodeBase64(invoicePhoto.getInvoicePhoto())));
						}
					}
				}
				tripList.add(tripPickUpDispatchData);
			}
			tripDatas.setStatus(HttpServletResponse.SC_OK);
			tripDatas.setMessage(DicvConstants.DATA_FOUND_MSG);
			tripDatas.setTripList(tripList);
			return tripDatas;
		} catch (

		Exception e) {
			LOGGER.error("Error in Trip Data List  ", e);
			tripDatas.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			tripDatas.setMessage(DicvConstants.EXCEPTION_MSG);
			return tripDatas;
		}
	}
}
