package com.dicv.truck.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicv.truck.dto.AlertReportDto;
import com.dicv.truck.dto.AlertReportJrListDto;
import com.dicv.truck.dto.AlertsReportJrDto;
import com.dicv.truck.dto.DeviceHealthReportDto;
import com.dicv.truck.dto.GeoFenceJrReportDto;
import com.dicv.truck.dto.GeoFenceReportDto;
import com.dicv.truck.dto.InActiveVehicleDto;
import com.dicv.truck.dto.InActiveVehicleList;
import com.dicv.truck.dto.MyFleetInstantReport;
import com.dicv.truck.dto.MyFleetVehicleReportListDto;
import com.dicv.truck.dto.NightDrivingJrReportDto;
import com.dicv.truck.dto.NightDrivingReportDto;
import com.dicv.truck.dto.NightDrivingReportListDto;
import com.dicv.truck.dto.OverSpeedJrReportDto;
import com.dicv.truck.dto.OverSpeedReportDto;
import com.dicv.truck.dto.OwnerDtlsDto;
import com.dicv.truck.dto.SpeedReportDto;
import com.dicv.truck.dto.SpeedReportLayoutDto;
import com.dicv.truck.dto.UsersDto;
import com.dicv.truck.dto.VehicleSummaryDto;
import com.dicv.truck.dto.VehicleSummaryReportVO;
import com.dicv.truck.model.DeviceHealth;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.Notification;
import com.dicv.truck.model.Vehicle;
import com.dicv.truck.model.VehicleUtilization;
import com.dicv.truck.repo.DeviceHealthRepo;
import com.dicv.truck.repo.GeoFenceReportRepo;
import com.dicv.truck.repo.NotificationRepo;
import com.dicv.truck.repo.ReportDao;
import com.dicv.truck.repo.VehicleEventReportRepo;
import com.dicv.truck.report.AlertReport;
import com.dicv.truck.report.CustomerAdminReport;
import com.dicv.truck.report.DealerReport;
import com.dicv.truck.report.DeviceReport;
import com.dicv.truck.report.GeoFenceDataReport;
import com.dicv.truck.report.InActiveVehicleReport;
import com.dicv.truck.report.MyFleetReport;
import com.dicv.truck.report.NightDrivingDataReport;
import com.dicv.truck.report.OverSpeedDataReport;
import com.dicv.truck.report.UserReport;
import com.dicv.truck.report.VehicleReport;
import com.dicv.truck.report.VehicleSummaryReport;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.EnumUserType;
import com.dicv.truck.utility.NightDrivingTiming;
import com.dicv.truck.utility.ReportType;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class ReportService {

	@Autowired
	private VehicleDataService vehicleDataService;

	@Autowired
	private GeoFenceReportRepo geoFenceRepo;

	@Autowired
	private ReportDao reportDao;

	@Autowired
	private UserService userService;

	@Autowired
	private VehicleEventReportRepo vehEventRepo;;

	@Autowired
	private NotificationRepo notificationRepo;

	@Autowired
	private DeviceHealthRepo deviceHealthRepo;

	@Autowired
	private AlertService alertService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);

	public void generateVehicleSummaryReport(VehicleSummaryDto vehicleSummaryDto, HttpServletResponse response) {
		try {
			Map<Integer, String> vehicleMap = vehicleDataService.loadVehicleNameAndId(vehicleSummaryDto);
			if (vehicleMap == null || vehicleMap.isEmpty()) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
			} else {
				Map<Integer, List<VehicleUtilization>> map = vehicleDataService
						.vehicleUtilizationSummary(vehicleSummaryDto);
				if (map == null || map.isEmpty()) {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
					response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
				} else {
					List<VehicleSummaryReportVO> vehicleSummaryReportVOList = new ArrayList<VehicleSummaryReportVO>();
					utilizationCalculation(vehicleMap, map, vehicleSummaryReportVOList);
					VehicleSummaryReport rep = new VehicleSummaryReport(vehicleSummaryReportVOList);
					JasperPrint jp = rep.getReport();
					writeXlsxReport(jp, response, "Vehicle Summary");
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error in Vehicle Utilization Report ", e);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
	}

	private void utilizationCalculation(Map<Integer, String> vehicleMap, Map<Integer, List<VehicleUtilization>> map,
			List<VehicleSummaryReportVO> vehicleSummaryReportVOList) {
		Integer count;
		String mapUrl;
		for (Integer vehicleId : map.keySet()) {
			try {
				VehicleSummaryReportVO vehicleSummaryReportVO = new VehicleSummaryReportVO();
				Double totalDistance = 0d;
				Long totalDrivingTime = 0l;
				Double maximumSpeed = 0d;
				Double averageSpeed = 0d;
				Integer numberOfStops = 0;
				Long maxIdleTime = 0l;
				Double maxIdleLatitude = 0d;
				Double maxIdleLongitude = 0d;
				Long totalIdleTime = 0l;
				count = 0;
				mapUrl = null;
				if (map.get(vehicleId) != null) {
					for (VehicleUtilization vehUtil : map.get(vehicleId)) {
						if (vehUtil.getTotalDrivingTime() != null && vehUtil.getAverageSpeed() != null
								&& vehUtil.getMaximumSpeed() != null && vehUtil.getTotalDistance() != null) {
							count = count + 1;
							numberOfStops = numberOfStops + vehUtil.getNoOfStops();
							totalDistance = totalDistance + vehUtil.getTotalDistance().intValue();
							totalDrivingTime = totalDrivingTime + vehUtil.getTotalDrivingTime();
							totalIdleTime = totalIdleTime + vehUtil.getTotalIdleTime();
							maximumSpeed = maximumSpeed < vehUtil.getMaximumSpeed().intValue()
									? vehUtil.getMaximumSpeed().intValue()
									: maximumSpeed;

							if (maxIdleTime < vehUtil.getMaxIdleTime()) {
								maxIdleTime = vehUtil.getMaxIdleTime();
								maxIdleLatitude = vehUtil.getMaxIdleLatitude();
								maxIdleLongitude = vehUtil.getMaxIdleLongtitude();
								mapUrl = vehUtil.getIdleLocation();
							}

						}
					}
				}
				if (totalDistance != null && totalDrivingTime != null && totalDistance > 0 && totalDrivingTime > 0) {
					averageSpeed = (totalDistance.doubleValue() / (totalDrivingTime.doubleValue() / 3600));
					averageSpeed = Math.round(averageSpeed * 100D) / 100D;
				}
				mapUrl = mapUrl != null ? mapUrl
						: DicvConstants.GOOGLE_MAP_URL + maxIdleLatitude + "," + maxIdleLongitude;
				if (maxIdleLatitude > 0 && maxIdleLongitude > 0)
					vehicleSummaryReportVO.setMaxIdleLocation(mapUrl);
				vehicleSummaryReportVO
						.setLatLong(DicvConstants.GOOGLE_MAP_URL + maxIdleLatitude + "," + maxIdleLongitude);
				vehicleSummaryReportVO.setMaximumSpeed(maximumSpeed.toString());
				vehicleSummaryReportVO.setMaxIdleTime(DicvUtil.getTimeFromSeconds(maxIdleTime.intValue()));
				vehicleSummaryReportVO.setTotalIdleTime(DicvUtil.getTimeFromSeconds(totalIdleTime.intValue()));
				vehicleSummaryReportVO.setRegistrationId(vehicleMap.get(vehicleId));
				vehicleSummaryReportVO.setTotalDistance(totalDistance.toString());
				if (totalDrivingTime != null)
					vehicleSummaryReportVO
							.setTotalDrivingTime(DicvUtil.getTimeFromSeconds(totalDrivingTime.intValue()));
				else
					vehicleSummaryReportVO.setTotalDrivingTime("");
				vehicleSummaryReportVO.setAverageSpeed(averageSpeed.toString());
				vehicleSummaryReportVO.setNumberOfStops(numberOfStops.toString());
				vehicleSummaryReportVOList.add(vehicleSummaryReportVO);
			} catch (Exception ex) {
				LOGGER.info("Exception in Vehicle Iteration ", ex);
			}
		}
	}

	public void generateGeoFenceReport(GeoFenceJrReportDto geoFenceJrReportDto, String fromDateStr, String toDateStr,
			HttpServletResponse response) {

		try {
			String[] geoIds = geoFenceJrReportDto.getGeoFenceId().split(",");
			List<Integer> geoFenceIds = new ArrayList<Integer>();
			for (String s : geoIds) {
				geoFenceIds.add(Integer.valueOf(s));
			}
			String[] vehIds = geoFenceJrReportDto.getVehicleId().split(",");
			List<Integer> vehicleIds = new ArrayList<Integer>();
			for (String s : vehIds) {
				vehicleIds.add(Integer.valueOf(s));
			}
			Date fromDate = DicvUtil.getStartTimeOfDate(new SimpleDateFormat("dd-MMM-yy").parse(fromDateStr));
			Date toDate = DicvUtil.getEndTimeOfDate(new SimpleDateFormat("dd-MMM-yy").parse(toDateStr));
			List<GeoFenceReportDto> geoFenceReportDtos = geoFenceRepo.getGeoFenceReport(geoFenceJrReportDto.getUserId(),
					vehicleIds, geoFenceIds, fromDate, toDate);
			if (null == geoFenceReportDtos | geoFenceReportDtos.isEmpty()) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				response.sendError(HttpServletResponse.SC_NO_CONTENT, "GeoFence Report Not Availble for Selected Date");
			} else {
				List<GeoFenceReportDto> geoFenceReportDtoList = new ArrayList<GeoFenceReportDto>();
				geoFenceReportDtos.forEach(g -> geoFenceReportDtoList.add(new GeoFenceReportDto(g.getGeoFenceName(),
						g.getRegistrationId(), g.getUserId(), DicvUtil.getStringForTimestamp(g.getGeoFenceEntryTime()),
						DicvUtil.getStringForTimestamp(g.getGeoFenceExitTime()),
						DicvUtil.convertFromMillisToHoursMinsSecs(
								g.getGeoFenceExitTime().getTime() - g.getGeoFenceEntryTime().getTime()))));
				GeoFenceDataReport rep = new GeoFenceDataReport(geoFenceReportDtoList);
				JasperPrint jp = rep.getReport();
				writeXlsxReport(jp, response, "DeviceReport");
			}
		} catch (Exception e) {
			LOGGER.error("Error in Fetching Geo Fence Report" + e);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			try {
				response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
			} catch (Exception ex) {

			}
		}
	}

	public void generateOverSpeedReport(OverSpeedJrReportDto overSpeedJrReportDto, HttpServletResponse response) {
		try {
			if (overSpeedJrReportDto.getSpeedLimit() == null)
				overSpeedJrReportDto.setSpeedLimit(80);
			List<SpeedReportDto> speedList = reportDao.getSpeedReport(overSpeedJrReportDto.getVehicleIds(),
					overSpeedJrReportDto.getFromDate(), overSpeedJrReportDto.getToDate(),
					overSpeedJrReportDto.getSpeedLimit());
			if (null == speedList || speedList.isEmpty()) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				response.sendError(HttpServletResponse.SC_NO_CONTENT,
						"Over Speed Report Not Available For Selected Date");
			} else {
				List<OverSpeedReportDto> overSpeedpeedList = reportDao.getSpeedReportMaxLocation(
						overSpeedJrReportDto.getVehicleIds(), overSpeedJrReportDto.getFromDate(),
						overSpeedJrReportDto.getToDate(), overSpeedJrReportDto.getSpeedLimit());

				Map<Integer, Vehicle> vehMap = vehicleDataService
						.getVehicleByVehicleIds(overSpeedJrReportDto.getVehicleIds());

				Map<Integer, OverSpeedReportDto> map = new HashMap<Integer, OverSpeedReportDto>();
				OverSpeedReportDto overSpeedReportDto = new OverSpeedReportDto();
				for (OverSpeedReportDto object : overSpeedpeedList) {
					if (map.get(object.getVehicleId()) != null) {
						overSpeedReportDto = map.get(object.getVehicleId());
						if (object.getMaxSpeed() > overSpeedReportDto.getMaxSpeed()) {
							overSpeedReportDto.setMaxSpeed(object.getMaxSpeed());
							overSpeedReportDto.setLatitude(object.getLatitude());
							overSpeedReportDto.setLocation(object.getLocation());
							overSpeedReportDto.setLongitude(object.getLongitude());
							map.put(object.getVehicleId(), overSpeedReportDto);
						}
					} else {
						map.put(object.getVehicleId(), object);
					}

				}
				List<SpeedReportLayoutDto> layoutList = new ArrayList<SpeedReportLayoutDto>();
				if (map != null && map.size() > 0) {

					for (SpeedReportDto speed : speedList) {

						if (map.get(speed.getVehicleId()).getMaxSpeed() > overSpeedJrReportDto.getSpeedLimit()) {

							OverSpeedReportDto dto = map.get(speed.getVehicleId());
							SpeedReportLayoutDto reportDto = new SpeedReportLayoutDto(
									vehMap.get(speed.getVehicleId()).getRegistrationId(),
									vehMap.get(speed.getVehicleId()).getVehicleMaxSpeed().toString(),
									speed.getTotalDistance().toString(), speed.getSpeedDistance().toString(),
									DicvUtil.getTimeFromSeconds(speed.getSpeedDuration().intValue()).toString(),
									dto.getMaxSpeed().toString(),
									DicvConstants.GOOGLE_MAP_URL + dto.getLatitude() + "," + dto.getLongitude());
							if (dto.getLocation() != null)
								reportDto.setMapUrl(dto.getLocation());
							reportDto.setLatLong(
									DicvConstants.GOOGLE_MAP_URL + dto.getLatitude() + "," + dto.getLongitude());

							layoutList.add(reportDto);
						}
					}
					OverSpeedDataReport rep = new OverSpeedDataReport(layoutList);
					JasperPrint jp = rep.getReport();
					writeXlsxReport(jp, response, "OverSpeed Report");
				} else {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
					response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Speed Report " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			try {
				response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
			} catch (IOException e1) {
			}
		}
	}

	private ReportType getReportTypeByRole(String role) {
		ReportType reportType = ReportType.USER_REPORT;

		if (role != null && EnumUserType.CUSTOMERADMIN.getUserType().equalsIgnoreCase(role)) {
			reportType = ReportType.CUSTOMER_ADMIN_REPORT;
		}

		if (role != null && EnumUserType.DEALER.getUserType().equalsIgnoreCase(role)) {
			reportType = ReportType.DEALER_REPORT;
		}

		return reportType;
	}

	public void generateUserReport(Integer userId, String role, HttpServletResponse response) {
		try {
			DicvUser user = userService.getUser(userId, "generateUserReport");
			if (user == null) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
			} else {
				UsersDto userDto = userService.getUserDtoList(role, userId, user.getUserType().getUserType());
				ReportType reportType = getReportTypeByRole(role);
				if (null == userDto || null == userDto.getUsers() || userDto.getUsers().isEmpty()) {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
					response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
				} else {
					if (reportType.getReportType().equalsIgnoreCase(ReportType.USER_REPORT.getReportType())) {
						UserReport rep = new UserReport(userDto.getUsers());
						JasperPrint jp = rep.getReport();
						writeXlsxReport(jp, response, ReportType.USER_REPORT.getReportType());
					} else if (reportType.getReportType()
							.equalsIgnoreCase(ReportType.CUSTOMER_ADMIN_REPORT.getReportType())) {
						CustomerAdminReport rep = new CustomerAdminReport(userDto.getUsers());
						JasperPrint jp = rep.getReport();
						writeXlsxReport(jp, response, ReportType.CUSTOMER_ADMIN_REPORT.getReportType());
					} else if (reportType.getReportType().equalsIgnoreCase(ReportType.DEALER_REPORT.getReportType())) {
						DealerReport rep = new DealerReport(userDto.getUsers());
						JasperPrint jp = rep.getReport();
						writeXlsxReport(jp, response, ReportType.DEALER_REPORT.getReportType());
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception in User Report " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			try {
				response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
			} catch (IOException e1) {
			}
		}
	}

	public void generateVehicleReport(Integer userId, HttpServletResponse response) {
		try {
			DicvUser user = userService.getUser(userId, "generateVehicleReport");
			if (user == null) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
			} else {
				OwnerDtlsDto vehicleDetail = vehicleDataService.getVehicleList(userId,
						user.getUserType().getUserType());
				if (null == vehicleDetail || null == vehicleDetail.getVehicles()
						|| vehicleDetail.getVehicles().isEmpty()) {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
					response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
				} else {
					VehicleReport rep = new VehicleReport(vehicleDetail.getVehicles());
					JasperPrint jp = rep.getReport();
					writeXlsxReport(jp, response, "Veicle  Report");
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Vehicle Report " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			try {
				response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
			} catch (IOException e1) {
				LOGGER.error("Exception in Vehicle Report " + e1.getMessage());
			}
		}

	}

	public void generateAlertReport(AlertReportDto alertReportDto, HttpServletResponse response) {
		DicvUser user = userService.getUser(alertReportDto.getUserId(), "generateAlertReport");
		try {
			if (user == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			} else {
				AlertReportJrListDto alertReportListDto = new AlertReportJrListDto();
				SimpleDateFormat dateformat = new SimpleDateFormat(DicvConstants.REPORTS_DATE_FORMAT);
				String fromDate = dateformat.format(alertReportDto.getFromDate());
				String toDate = dateformat.format(alertReportDto.getToDate());

				String[] alertIds = alertReportDto.getAlertReportType().split(",");
				List<Integer> alertTypeIdlist = new ArrayList<Integer>();
				for (String s : alertIds) {
					alertTypeIdlist.add(Integer.valueOf(s));
				}
				String[] vehIds = alertReportDto.getVehicleId().split(",");
				List<Integer> vehicleIds = new ArrayList<Integer>();
				for (String s : vehIds) {
					vehicleIds.add(Integer.valueOf(s));
				}
				List<Notification> alertReport = notificationRepo.getNotificationList(alertReportDto.getUserId(),
						vehicleIds, alertTypeIdlist, DicvUtil.getDateForStartTime(fromDate),
						DicvUtil.getDateForToTime(toDate));
				if (null == alertReport || alertReport.isEmpty()) {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
					response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
				} else {

					List<AlertsReportJrDto> alertsReportJrDtos = new ArrayList<AlertsReportJrDto>();
					alertReport.forEach(notification -> alertsReportJrDtos.add(new AlertsReportJrDto(
							notification.getVehicle().getRegistrationId(), notification.getAlertType().getAlertType(),
							getLocationUrl(notification.getGeoLatitude(), notification.getGeoLongitute()),
							DicvUtil.getStringForCalendar(notification.getReceivedDateTime()),
							notification.getLocation() != null ? notification.getLocation()
									: getLocationUrl(notification.getGeoLatitude(), notification.getGeoLongitute()))));
					alertReportListDto.setAlerts(alertsReportJrDtos);
					AlertReport rep = new AlertReport(alertsReportJrDtos);
					JasperPrint jp = rep.getReport();
					writeXlsxReport(jp, response, "Alert  Report");
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Alert Report " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			try {
				response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
			} catch (IOException e1) {
			}
		}

	}

	private String getLocationUrl(Double lat, Double longitude) {

		if (lat == null || longitude == null) {
			return null;
		}
		return DicvConstants.GOOGLE_MAP_URL + lat.toString() + "," + longitude.toString();

	}

	public void generateInactiveVehicleReport(Integer userId, String startDate, String endDate,
			HttpServletResponse response) {

		try {
			DicvUser user = userService.getUser(userId, "generateInactiveVehicleReport");
			if (user == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				try {
					response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
				} catch (IOException e1) {
					LOGGER.info("User Not Found");
				}
			} else {
				Date fromDate = new Date();
				Date toDate = new Date();
				if (startDate == null) {
					SimpleDateFormat dateformat = new SimpleDateFormat(DicvConstants.REPORTS_DATE_FORMAT);
					Calendar cal = Calendar.getInstance();
					endDate = dateformat.format(cal.getTime());
					cal.add(Calendar.DATE, -7);
					startDate = dateformat.format(cal.getTime());
				}
				fromDate = new SimpleDateFormat("dd-MMM-yy").parse(startDate);
				toDate = new SimpleDateFormat("dd-MMM-yy").parse(endDate);
				List<Integer> vehicleList = vehicleDataService.getVehicleIdList(userId,
						user.getUserType().getUserType());
				InActiveVehicleList inActiveVehicle = new InActiveVehicleList();
				List<InActiveVehicleDto> inActiveVehicleList = new ArrayList<InActiveVehicleDto>();
				List<Object[]> list = vehEventRepo.getInactiveVehicleList(vehicleList, fromDate, toDate);
				if (list == null || list.isEmpty()) {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				} else {
					Map<Integer, Vehicle> map = vehicleDataService.getVehicleByVehicleIds(vehicleList);
					for (Object[] obj : list) {

						Integer vehicleId = (Integer) obj[0];
						Long count = (Long) obj[1];
						if (count == 0) {
							Vehicle vehicle = map.get(vehicleId);
							if (vehicle == null || vehicle.getGpsImei() == null)
								continue;
							inActiveVehicleList.add(new InActiveVehicleDto(vehicle.getRegistrationId(),
									vehicle.getGpsImei().getGpsImei().toString(), vehicle.getVin()));

						}

					}
					inActiveVehicle.setInActiveVehicleList(inActiveVehicleList);
					InActiveVehicleReport rep = new InActiveVehicleReport(inActiveVehicleList);
					JasperPrint jp = rep.getReport();
					writeXlsxReport(jp, response, "InActiveVehicleReport");
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Vehicle Inactive Report " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			try {
				response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
			} catch (IOException e1) {
			}
		}

	}

	public void generateDeviceHealthReport(Integer userId, HttpServletResponse response) {
		try {
			DicvUser user = userService.getUser(userId, "generateDeviceHealthReport");
			if (user == null || !user.getUserType().getUserType().equals(EnumUserType.ROOTADMIN.getUserType())) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
			List<DeviceHealth> deviceHealthList = deviceHealthRepo.getDeviceHealthReport();
			if (deviceHealthList != null && deviceHealthList.size() > 0) {
				DeviceHealthReportDto deviceHealthReportDto = null;
				List<DeviceHealthReportDto> report = new ArrayList<DeviceHealthReportDto>();
				if (deviceHealthList != null) {
					for (DeviceHealth deviceHealth : deviceHealthList) {
						deviceHealthReportDto = new DeviceHealthReportDto();
						deviceHealthReportDto.setDeviceBattery(deviceHealth.getDeviceBatery());
						if (deviceHealth.getGpsTime() != null)
							deviceHealthReportDto.setGpsTime(DicvUtil.getStringForTimestamp(deviceHealth.getGpsTime()));
						if (deviceHealth.getCanVehicleUpdateTime() != null)
							deviceHealthReportDto.setVehicleLastUpdateON(
									DicvUtil.getStringForTimestamp(deviceHealth.getCanVehicleUpdateTime()));
						deviceHealthReportDto.setGpsVolt(deviceHealth.getGpsVolt());
						deviceHealthReportDto.setSignalStrength(deviceHealth.getSignalStrength());
						deviceHealthReportDto.setGroupName(deviceHealth.getGroupName());
						deviceHealthReportDto.setRegistrationId(deviceHealth.getRegistrationId());
						deviceHealthReportDto.setGpsImei(deviceHealth.getGpsImei());
						report.add(deviceHealthReportDto);
					}
				}
				if (report != null && report.size() > 0) {
					DeviceReport rep = new DeviceReport(report);
					JasperPrint jp = rep.getReport();
					writeXlsxReport(jp, response, "DeviceReport");
				} else {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
					response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Vehicle Inactive Report " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			try {
				response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
			} catch (IOException e1) {
			}
		}

	}

	public void writeXlsxReport(JasperPrint jp, HttpServletResponse response, final String reportName)
			throws IOException, JRException {
		// response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-disposition",
				"inline; filename=" + (reportName == null ? jp.getName() : reportName).replace('"', '_') + ".xls");
		response.setContentType("application/vnd.ms-excel");
		ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jp));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
		configuration.setWhitePageBackground(true);
		configuration.setDetectCellType(true);
		configuration.setFontSizeFixEnabled(true);
		// set all your configuration like above
		exporter.setConfiguration(configuration);
		exporter.exportReport();
		final byte[] rawBytes = xlsReport.toByteArray();
		response.setContentLength(rawBytes.length);

		final ByteArrayInputStream bais = new ByteArrayInputStream(rawBytes);

		final OutputStream outStream = response.getOutputStream();
		IOUtils.copy(bais, outStream);

		outStream.flush();

		IOUtils.closeQuietly(xlsReport);
		IOUtils.closeQuietly(bais);
		IOUtils.closeQuietly(outStream);
	}

	public void generateNightDrivingReport(NightDrivingJrReportDto nightDrivingJrReportDto,
			HttpServletResponse response) {

		// String fromDate = dateformat.format(nightDrivingJrReportDto.getFromDate());
		// String toDate = dateformat.format(nightDrivingJrReportDto.getToDate());
		try {

			String query = NightDrivingTiming.getNightSpeedQuery(nightDrivingJrReportDto.getStartTime(),
					nightDrivingJrReportDto.getEndTime());
			List<NightDrivingReportDto> nightDrivingReport = reportDao.getNightDrivingReport(
					nightDrivingJrReportDto.getUserId(), nightDrivingJrReportDto.getVehicleIds(),
					nightDrivingJrReportDto.getFromDate(), nightDrivingJrReportDto.getToDate(), query);
			if (null == nightDrivingReport || nightDrivingReport.isEmpty()) {

				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				response.sendError(HttpServletResponse.SC_NO_CONTENT,
						"Night Driving Report Not Available For Selected Date");

			} else {

				Map<Integer, Vehicle> vehicleMap = vehicleDataService
						.getVehicleByVehicleIds(nightDrivingJrReportDto.getVehicleIds());

				NightDrivingReportListDto report = new NightDrivingReportListDto();
				List<NightDrivingReportDto> resp = new ArrayList<NightDrivingReportDto>();
				NightDrivingReportDto obj = new NightDrivingReportDto();
				for (NightDrivingReportDto n : nightDrivingReport) {
					obj = new NightDrivingReportDto();
					Vehicle veh = vehicleMap.get(n.getVehicleId());
					if (veh == null)
						continue;
					if (n.getTotalDuration() != null)
						obj.setDuration(DicvUtil.getTimeFromSeconds(n.getTotalDuration().intValue()));
					obj.setRegistrationId(veh.getRegistrationId());
					obj.setTotalDistance(n.getTotalDistance());
					resp.add(obj);
				}
				report.setNightDrivingReportDtoList(resp);
				NightDrivingDataReport rep = new NightDrivingDataReport(resp);
				JasperPrint jp = rep.getReport();
				writeXlsxReport(jp, response, "Night Driving");

			}
		} catch (Exception e) {
			LOGGER.error("Exception in Vehicle Night Driving Report " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			try {
				response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
			} catch (IOException e1) {
			}
		}
	}

	public void myFleetVehicleReport(MyFleetInstantReport myFleetInstantReport, HttpServletResponse response) {
		try {
			DicvUser user = userService.getUser(myFleetInstantReport.getUserId(), "myFleetVehicleReport");
			if (user == null) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
			} else {
				MyFleetVehicleReportListDto myFleetVehicleReportListDto = vehicleDataService.getMyFleetVehicleList(
						myFleetInstantReport.getUserId(), user.getUserType().getUserType(),
						myFleetInstantReport.getVehicleIds());
				if (null == myFleetVehicleReportListDto
						|| null == myFleetVehicleReportListDto.getMyFleetVehicleReport()) {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
					response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
				} else {
					MyFleetReport rep = new MyFleetReport(myFleetVehicleReportListDto.getMyFleetVehicleReport());
					JasperPrint jp = rep.getReport();
					writeXlsxReport(jp, response, "My Fleet");
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception in MyFleet Report " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			try {
				response.sendError(HttpServletResponse.SC_NO_CONTENT, DicvConstants.DATA_NOT_FOUND_MSG);
			} catch (IOException e1) {
				LOGGER.error("Exception in MyFleet Report " + e1.getMessage());
			}
		}

	}

}
