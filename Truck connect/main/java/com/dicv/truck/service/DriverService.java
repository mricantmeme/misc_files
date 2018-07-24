package com.dicv.truck.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicv.truck.dto.DriverAnalysisDto;
import com.dicv.truck.dto.DriverAnalysisInsightsDto;
import com.dicv.truck.dto.DriverAnalysisLineChartDto;
import com.dicv.truck.dto.DriverAnalysisLineChartListDto;
import com.dicv.truck.dto.DriverAnalysisListDto;
import com.dicv.truck.dto.DriverTripPlaybackDto;
import com.dicv.truck.dto.DriverTripPlaybackListDto;
import com.dicv.truck.dto.SpeedCountDto;
import com.dicv.truck.dto.UserGroupDto;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.TripStopLocation;
import com.dicv.truck.model.Vehicle;
import com.dicv.truck.repo.DriverDao;
import com.dicv.truck.repo.GpsRepo;
import com.dicv.truck.repo.TripStopLocationRepo;
import com.dicv.truck.repo.UserRepo;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.EnumUserType;

@Service
public class DriverService {

	@Autowired
	private DriverDao driverRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private VehicleServices vehicleService;

	@Autowired
	private GpsRepo gpsRepo;

	@Autowired
	private TripStopLocationRepo tripStopLocationRepo;

	private static final Logger LOGGER = Logger.getLogger(DriverService.class);

	public DriverAnalysisListDto getDriverAnalysisFromTrip(Date startDate, Date endDate, int userId, String driverId) {
		// TODO Auto-generated method stub
		LOGGER.info("Driver Analysis ");
		DriverAnalysisListDto driverAnalysisListDto = driverRepo.getDriverAnalysisFromTrip(startDate, endDate, userId,
				driverId);

		if (driverAnalysisListDto != null && driverAnalysisListDto.getDriverAnalysisList() != null
				&& !driverAnalysisListDto.getDriverAnalysisList().isEmpty()) {

			driverAnalysisListDto.setStatus(HttpServletResponse.SC_OK);
			driverAnalysisListDto.setMessage(DicvConstants.DATA_FOUND_MSG);
		} else {
			driverAnalysisListDto = new DriverAnalysisListDto();
			driverAnalysisListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			driverAnalysisListDto.setMessage("Driver Analysis Data Not Available for Selected Date");
		}
		return driverAnalysisListDto;
	}

	public DriverAnalysisListDto getDriverAnalysisFromTrip(Date startDate, Date endDate, int userId, Integer groupId) {
		// TODO Auto-generated method stub
		LOGGER.info("Driver Analysis Group " + groupId);
		DicvUser user = userService.getUser(userId, "getDriverAnalysisFromTrip");
		DriverAnalysisListDto driverAnalysisListDto = driverRepo.getDriverAnalysisFromTrip(startDate, endDate, userId,
				groupId);

		if (driverAnalysisListDto != null && driverAnalysisListDto.getDriverAnalysisList() != null
				&& !driverAnalysisListDto.getDriverAnalysisList().isEmpty()) {

			Map<Integer, UserGroupDto> map = getUserAndGroupMap(userId, user.getUserType().getUserType());
			List<DriverAnalysisDto> dtoList = new ArrayList<DriverAnalysisDto>();
			if (groupId != null) {
				for (DriverAnalysisDto dto : driverAnalysisListDto.getDriverAnalysisList()) {
					if (map.get(dto.getDriverId()) != null && map.get(dto.getDriverId()).getGroupId().equals(groupId)) {
						dto.setGroupName(map.get(dto.getDriverId()).getGroupName());
						dtoList.add(dto);
					}
				}
			} else {
				for (DriverAnalysisDto dto : driverAnalysisListDto.getDriverAnalysisList()) {
					if (map.get(dto.getDriverId()) != null) {
						dto.setGroupName(map.get(dto.getDriverId()).getGroupName());
					}
					dtoList.add(dto);
				}
			}
			if (dtoList != null && !dtoList.isEmpty()) {
				driverAnalysisListDto.setDriverAnalysisList(dtoList);
				driverAnalysisListDto.setStatus(HttpServletResponse.SC_OK);
				driverAnalysisListDto.setMessage(DicvConstants.DATA_FOUND_MSG);
			} else {
				driverAnalysisListDto.setDriverAnalysisList(null);
				driverAnalysisListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
				driverAnalysisListDto.setMessage("Driver Analysis Data Not Available for Selected Group");
			}
		} else {
			driverAnalysisListDto = new DriverAnalysisListDto();
			driverAnalysisListDto.setStatus(HttpServletResponse.SC_NO_CONTENT);
			driverAnalysisListDto.setMessage("Driver Analysis Data Not Available for Selected Date");
		}
		return driverAnalysisListDto;
	}

	private Map<Integer, UserGroupDto> getUserAndGroupMap(int userId, String userType) {
		Map<Integer, UserGroupDto> map = new HashMap<Integer, UserGroupDto>();

		List<UserGroupDto> list = null;

		if (EnumUserType.ROOTADMIN.getUserType().equals(userType)) {
			list = userRepo.getUserGroupDto(userType);
		} else if (EnumUserType.CUSTOMERADMIN.getUserType().equals(userType)) {
			list = userRepo.getUserGroupDto(userId, userType);
		}
		if (list != null) {
			for (UserGroupDto u : list) {
				map.put(u.getUserId(), u);
			}
		}
		return map;
	}

	public DriverAnalysisInsightsDto driverAnalysisForInsights(Date startDate, Date endDate, int userId,
			Integer driverId) {

		DriverAnalysisInsightsDto driverAnalysisInsights = null;

		DriverAnalysisLineChartListDto driverAnalysisInsightsList = driverRepo
				.getDriverAnalysisForPerformanceGraph(startDate, endDate, userId, driverId);

		if (driverAnalysisInsightsList != null && driverAnalysisInsightsList.getDriverAnalysisLineChart() != null
				&& !driverAnalysisInsightsList.getDriverAnalysisLineChart().isEmpty()) {

			List<DriverAnalysisLineChartDto> driverAnalysisList = driverAnalysisInsightsList
					.getDriverAnalysisLineChart();

			if (driverAnalysisList.size() > 0) {

				driverAnalysisInsights = new DriverAnalysisInsightsDto();

				Collections.sort(driverAnalysisList, new Comparator<DriverAnalysisLineChartDto>() {
					@Override
					public int compare(final DriverAnalysisLineChartDto object1,
							final DriverAnalysisLineChartDto object2) {
						return object2.getMaxSpeed().compareTo(object1.getMaxSpeed());
					}
				});
			}

			boolean isFirstRecord = true;
			double totalEconomyBandDist = 0;
			int count = 0;
			long totalDriveTime = 0;
			int avgDriveTimeDaily = 0;

			DateFormat df = new SimpleDateFormat("dd-MMM-YY");
			SimpleDateFormat localDateFormat = new SimpleDateFormat("HH");
			Date maxSpeedTime;
			String time;
			int intTime = 0;
			String timeAM_PM = null;

			for (DriverAnalysisLineChartDto driverAnalysis : driverAnalysisList) {

				count++;
				totalEconomyBandDist += driverAnalysis.getDistanceInEconomyBand();
				totalDriveTime += (driverAnalysis.getTotalDriveTime()) / (60 * 60);
				if (isFirstRecord) {
					if (driverAnalysisInsights != null) {
						driverAnalysisInsights.setMaxSpeed(driverAnalysis.getMaxSpeed());
						if (null != driverAnalysis.getMaxSpeedTime()) {
							String maxSpeedDate = df.format(driverAnalysis.getMaxSpeedTime());
							driverAnalysisInsights.setDate(maxSpeedDate.toUpperCase());
						}
					}
					if (null != driverAnalysis.getMaxSpeedTime()) {

						/*
						 * maxSpeedTime in Database is a 12hr time format
						 * 
						 * Hence the below code has been written by considering 12 Hrs format
						 * 
						 * The maxSpeedTime value in JSON Response will be in the format of "1 AM IST"
						 * or "12 AM IST" or "2 PM IST"
						 */

						maxSpeedTime = driverAnalysis.getMaxSpeedTime();
						time = localDateFormat.format(maxSpeedTime);
						intTime = Integer.parseInt(time);
						if (intTime == 0) {
							timeAM_PM = "12 AM IST";
						} else {
							timeAM_PM = (intTime >= 12)
									? ((intTime == 12) ? intTime + " PM IST" : (intTime - 12) + " PM IST")
									: intTime + " AM IST";
						}
						if (driverAnalysisInsights != null) {
							driverAnalysisInsights.setMaxSpeedTime(timeAM_PM);
						}
					}
					if (driverAnalysisInsights != null) {
						driverAnalysisInsights.setLatitude(driverAnalysis.getLatitude());
						driverAnalysisInsights.setLongitude(driverAnalysis.getLongitude());
					}

					isFirstRecord = false;
				}
				if (driverAnalysisInsights != null) {
					driverAnalysisInsights.setTotaleconomyBandDistance(totalEconomyBandDist);
				}

			}
			if (count != 0 && totalDriveTime != 0) {

				avgDriveTimeDaily = (int) ((totalDriveTime) / (count));
				if (driverAnalysisInsights != null) {
					driverAnalysisInsights.setDriveTimePerDay(avgDriveTimeDaily + " Hrs");
				}

			}
		}
		if (driverAnalysisInsights != null) {
			driverAnalysisInsights.setStatus(HttpServletResponse.SC_OK);
			driverAnalysisInsights.setMessage(DicvConstants.DATA_FOUND_MSG);
		} else {
			driverAnalysisInsights = new DriverAnalysisInsightsDto();
			driverAnalysisInsights.setStatus(HttpServletResponse.SC_OK);
			driverAnalysisInsights.setMessage("Driver Analysis Data Not Available for Selected Group");
		}
		return driverAnalysisInsights;
	}

	public DriverAnalysisLineChartListDto driverAnalysisForPerformanceGraph(Date startDate, Date endDate, int userId,
			Integer driverId) {

		DriverAnalysisLineChartListDto driverAnalysisList = driverRepo.getDriverAnalysisForPerformanceGraph(startDate,
				endDate, userId, driverId);
		if (driverAnalysisList != null && driverAnalysisList.getDriverAnalysisLineChart() != null
				&& !driverAnalysisList.getDriverAnalysisLineChart().isEmpty()) {
			driverAnalysisList.setStatus(HttpServletResponse.SC_OK);
			driverAnalysisList.setMessage(DicvConstants.DATA_FOUND_MSG);
		} else {
			driverAnalysisList = new DriverAnalysisLineChartListDto();
			driverAnalysisList.setStatus(HttpServletResponse.SC_OK);
			driverAnalysisList.setMessage("Driver Analysis Data Not Available for Selected Group");
		}
		return driverAnalysisList;
	}

	public SpeedCountDto getSpeedingCountForDriver(Integer driverId, Date startDate, Date endDate) {

		SpeedCountDto speedCountDto = driverRepo.getSpeedingCountForDriver(driverId, startDate, endDate);
		if (speedCountDto != null) {
			speedCountDto.setStatus(HttpServletResponse.SC_OK);
			speedCountDto.setMessage(DicvConstants.DATA_FOUND_MSG);
		} else {
			speedCountDto = new SpeedCountDto();
			speedCountDto.setStatus(HttpServletResponse.SC_OK);
			speedCountDto.setMessage("Driver Analysis Data Not Available for Selected Group");
		}
		return speedCountDto;
	}

	public DriverTripPlaybackListDto getMyFleetPlayback(Integer vehicleId, Integer userId, String fromDate,
			String toDate) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		Date startDate = null;
		Date endDate = null;
		DriverTripPlaybackListDto myFleetPlayback = new DriverTripPlaybackListDto();
		try {
			startDate = sdf1.parse(fromDate);
			endDate = sdf1.parse(toDate);
			Vehicle veh = vehicleService.getVehicleDetails(vehicleId);

			List<DriverTripPlaybackDto> playBack = gpsRepo.getMyFleetDetails(veh.getGpsImei().getGpsImei(), startDate,
					endDate);

			List<TripStopLocation> tripStop = tripStopLocationRepo.getTripStopLocation(veh.getVehicleId(), startDate,
					endDate);
			if (tripStop != null && tripStop.size() > 0) {
				for (TripStopLocation trip : tripStop) {
					DriverTripPlaybackDto dto = new DriverTripPlaybackDto();
					dto.setGpsLatitude(trip.getStopLatitude());
					dto.setGpsLongitude(trip.getStopLongitude());
					dto.setGpsSpkm(0);
					dto.setGpsTime(trip.getStartTime());
					dto.setStopDuration(DicvUtil.convertFromMillisToHoursMinsSecs(trip.getDuration()));
					playBack.add(dto);
				}
				myFleetPlayback.setNoOfStops(tripStop.size());
				Comparator<DriverTripPlaybackDto> playBackComparator = (o1, o2) -> o1.getGpsTime()
						.compareTo(o2.getGpsTime());
				playBack.sort(playBackComparator);
			}
			myFleetPlayback.setDriverTripPlaybackList(playBack);
			myFleetPlayback.setStatus(HttpServletResponse.SC_OK);
			myFleetPlayback.setMessage(DicvConstants.DATA_FOUND_MSG);
			return myFleetPlayback;
		} catch (Exception e) {
			LOGGER.error("Exception in MyFleet Pack ", e);
			myFleetPlayback.setStatus(HttpServletResponse.SC_OK);
			myFleetPlayback.setMessage("MyFleet Data Not Available for Selected Group");
			return myFleetPlayback;
		}
	}

}
