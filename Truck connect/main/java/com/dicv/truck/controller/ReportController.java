package com.dicv.truck.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dicv.truck.dto.AlertReportDto;
import com.dicv.truck.dto.GeoFenceJrReportDto;
import com.dicv.truck.dto.MyFleetInstantReport;
import com.dicv.truck.dto.NightDrivingJrReportDto;
import com.dicv.truck.dto.OverSpeedJrReportDto;
import com.dicv.truck.dto.VehicleSummaryDto;
import com.dicv.truck.exception.InvalidValueException;
import com.dicv.truck.service.ReportService;
import com.dicv.truck.utility.DicvConstants;

@RestController
@RequestMapping("/onlinefleetmngmt/dicv")
public class ReportController {

	@Autowired
	private ReportService reportService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);

	@GetMapping("/generateVehicleSummaryReportNew")
	public void generateVehicleSummaryReport(HttpServletResponse response, Model model,
			VehicleSummaryDto vehicleSummaryDto) {
		if ((null == vehicleSummaryDto.getUserId() || vehicleSummaryDto.getUserId() <= 0)
				|| (null == vehicleSummaryDto.getFromDate()) || (null == vehicleSummaryDto.getToDate())) {
			throw new InvalidValueException("Please provide valid userId/date/vehicleIds");
		} else {
			reportService.generateVehicleSummaryReport(vehicleSummaryDto, response);
		}
	}

	@GetMapping("/generateGeoFenceReport")
	public void generateGeoFenceReport(HttpServletResponse response, Model model,
			GeoFenceJrReportDto geoFenceJrReportDto) {
		if (null == geoFenceJrReportDto.getFromDate() && null == geoFenceJrReportDto.getToDate()) {
			throw new InvalidValueException("Invalid OwnerId/Fromdate/Todate !!!");
		} else {
			SimpleDateFormat dateformat = new SimpleDateFormat(DicvConstants.REPORTS_DATE_FORMAT);
			String fromDate = dateformat.format(geoFenceJrReportDto.getFromDate());
			String toDate = dateformat.format(geoFenceJrReportDto.getToDate());
			if (!validateDate(geoFenceJrReportDto.getFromDate(), geoFenceJrReportDto.getToDate())) {
				throw new InvalidValueException("Please specify proper date range");
			}
			reportService.generateGeoFenceReport(geoFenceJrReportDto, fromDate, toDate, response);
		}
	}

	@GetMapping("/generateOverSpeedReport")
	public void generateOverSpeedReport(HttpServletResponse response, Model model,
			OverSpeedJrReportDto overSpeedJrReportDto) {
		if (null == overSpeedJrReportDto.getFromDate() && null == overSpeedJrReportDto.getToDate()) {
			throw new InvalidValueException("Invalid OwnerId/Fromdate/Todate !!!");
		} else {
			if (!validateDate(overSpeedJrReportDto.getFromDate(), overSpeedJrReportDto.getToDate())) {
				throw new InvalidValueException("Please specify proper date range");
			} else {
				reportService.generateOverSpeedReport(overSpeedJrReportDto, response);
			}
		}
	}

	@GetMapping("/generateUserReport")
	public void generateUserReport(HttpServletResponse response, Model model, Integer userId,
			@RequestParam String role) {
		LOGGER.info("Generating User Report ");
		if (null == userId || userId <= 0) {
			throw new InvalidValueException("Invalid User !!!");
		} else {
			reportService.generateUserReport(userId, role, response);

		}
	}

	@GetMapping("/generateVehicleReport")
	public void generateVehicleReport(HttpServletResponse response, Model model, @RequestParam Integer userId) {
		LOGGER.info("Generating Vehicle Report ");
		if (null == userId || userId <= 0) {
			throw new InvalidValueException("Invalid User !!!");
		} else {
			reportService.generateVehicleReport(userId, response);

		}
	}

	@GetMapping("/generateAlertReport")
	public void generateAlertReport(HttpServletResponse response, Model model, AlertReportDto alertReportDto) {
		if (null == alertReportDto.getUserId() && alertReportDto.getUserId() <= 0) {
			throw new InvalidValueException("Invalid User !!!");
		} else {
			reportService.generateAlertReport(alertReportDto, response);

		}
	}

	@GetMapping(value = "/generateVehicleUtilizationReport")
	public void generateInactiveVehicleReport(HttpServletResponse response, @RequestParam Integer userId,
			@RequestParam String startDate, @RequestParam String endDate) {
		LOGGER.info("Received Inactive " + userId + " " + startDate + "  " + endDate);
		if (null == userId || userId <= 0) {
			throw new InvalidValueException("Invalid User !!!");
		} else {
			reportService.generateInactiveVehicleReport(userId, startDate, endDate, response);

		}
	}

	@GetMapping(value = "/generateDeviceHealthReport", produces = "application/pdf")
	public void generateDeviceHealthReport(HttpServletResponse response, Model model, @RequestParam Integer userId) {
		if (null == userId || userId <= 0) {
			throw new InvalidValueException("Invalid User !!!");
		} else {
			reportService.generateDeviceHealthReport(userId, response);
		}
	}

	@GetMapping("/myFleetVehicleReport")
	public void myFleetReport(HttpServletResponse response, Model model, MyFleetInstantReport myFleetInstantReport) {
		if (null == myFleetInstantReport || myFleetInstantReport.getUserId() <= 0) {
			throw new InvalidValueException("Invalid User !!!");
		} else {
			reportService.myFleetVehicleReport(myFleetInstantReport, response);
		}
	}

	@GetMapping("/generatenightDrivingReport")
	public void generatenightDrivingReport(HttpServletResponse response, Model model,
			NightDrivingJrReportDto nightDrivingJrReportDto) {
		reportService.generateNightDrivingReport(nightDrivingJrReportDto, response);
	}

	private boolean validateDate(Date startDate, Date endDate) {
		boolean isValidDate = true;
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar currentDate = Calendar.getInstance();

		fromDate.setTime(startDate);
		toDate.setTime(endDate);
		currentDate.setTimeInMillis(System.currentTimeMillis());

		if (fromDate.after(toDate)) {
			isValidDate = false;
		} else if (toDate.after(currentDate) || toDate.equals(currentDate)) {
			isValidDate = false;
		}

		return isValidDate;

	}

}
