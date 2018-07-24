package com.dicv.truck.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicv.truck.dto.Address;
import com.dicv.truck.dto.MyFleetVehicleReport;
import com.dicv.truck.dto.MyFleetVehicleReportListDto;
import com.dicv.truck.dto.OwnerDtlsDto;
import com.dicv.truck.dto.VehicleDtlsDto;
import com.dicv.truck.dto.VehicleListDto;
import com.dicv.truck.dto.VehicleSummaryDto;
import com.dicv.truck.model.Vehicle;
import com.dicv.truck.model.VehicleUtilization;
import com.dicv.truck.repo.VehicleRepo;
import com.dicv.truck.repo.VehicleUtilizationRepo;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.EnumUserType;

@Service
public class VehicleDataService {

	@Autowired
	private VehicleRepo vehicleRepo;

	@Autowired
	private GoogleAPIService googleAPIService;

	@Autowired
	private VehicleUtilizationRepo vehicleUtilRepo;

	public Map<Integer, String> loadVehicleNameAndId(VehicleSummaryDto vehicleSummaryDto) {
		List<VehicleListDto> vehicleList = vehicleRepo.loadVehicleWithNameAndId(vehicleSummaryDto.getVehicleIds());
		Map<Integer, String> vehicleMap = new HashMap<Integer, String>();
		for (VehicleListDto veh : vehicleList) {
			vehicleMap.put(veh.getVehicleId(), veh.getRegistrationId());
		}
		return vehicleMap;
	}

	public Map<Integer, List<VehicleUtilization>> vehicleUtilizationSummary(VehicleSummaryDto vehicleSummaryDto) {
		List<VehicleUtilization> summaryList = vehicleUtilRepo.getVehicleUtilization(vehicleSummaryDto.getVehicleIds(),
				vehicleSummaryDto.getFromDate(), vehicleSummaryDto.getToDate());
		Map<Integer, List<VehicleUtilization>> map = new HashMap<Integer, List<VehicleUtilization>>();
		for (VehicleUtilization vehicleUtilTemp : summaryList) {
			if (map.get(vehicleUtilTemp.getVehicle().getVehicleId()) != null) {
				List<VehicleUtilization> list = map.get(vehicleUtilTemp.getVehicle().getVehicleId());
				list.add(vehicleUtilTemp);
				map.put(vehicleUtilTemp.getVehicle().getVehicleId(), list);

			} else {
				List<VehicleUtilization> list = new ArrayList<VehicleUtilization>();
				list.add(vehicleUtilTemp);
				map.put(vehicleUtilTemp.getVehicle().getVehicleId(), list);
			}
		}
		return map;
	}

	public Map<Integer, Vehicle> getVehicleByVehicleIds(List<Integer> vehicleIds) {
		List<Vehicle> vehicleList = vehicleRepo.getByVehicleIds(vehicleIds);
		Map<Integer, Vehicle> vehicleMap = new HashMap<Integer, Vehicle>();
		for (Vehicle veh : vehicleList) {
			vehicleMap.put(veh.getVehicleId(), veh);
		}
		return vehicleMap;
	}

	public List<Integer> getVehicleIdList(Integer userId, String userType) {
		List<Integer> vehicleList = null;
		if (userType.equals(EnumUserType.ROOTADMIN.getUserType())) {
			vehicleList = vehicleRepo.getVehicleIds();
		} else if (userType.equals(EnumUserType.CUSTOMERADMIN.getUserType())) {
			vehicleList = vehicleRepo.getVehicleIds(userId);
		}
		if (vehicleList == null || vehicleList.isEmpty())
			return null;
		return vehicleList;
	}

	public OwnerDtlsDto getVehicleList(Integer userId, String userType) {
		List<Vehicle> vehicleList = null;
		if (userType.equals(EnumUserType.ROOTADMIN.getUserType())) {
			vehicleList = vehicleRepo.getVehicleList();
		} else if (userType.equals(EnumUserType.CUSTOMERADMIN.getUserType())) {
			vehicleList = vehicleRepo.getVehicleList(userId);
		}
		if (vehicleList == null || vehicleList.isEmpty())
			return null;

		List<VehicleDtlsDto> vehicles = new ArrayList<VehicleDtlsDto>();
		VehicleDtlsDto vehicleDtlsDto = null;
		for (Vehicle veh : vehicleList) {
			vehicleDtlsDto = new VehicleDtlsDto();
			vehicleDtlsDto.setRegistrationId(veh.getRegistrationId());
			vehicleDtlsDto.setDescription(veh.getDescription());
			vehicleDtlsDto.setVin(veh.getVin());
			if (veh.getGpsImei() != null && veh.getGpsImei().getGpsTransmittedTime() != null)
				vehicleDtlsDto
						.setGpsFitmentDate(DicvUtil.getStringForTimestamp(veh.getGpsImei().getGpsTransmittedTime()));
			if (veh.getDicvCategory() != null)
				vehicleDtlsDto.setVehicleCategoryDesc(veh.getDicvCategory().getCategoryName());
			vehicleDtlsDto.setMaxVehicleSpeed(veh.getVehicleMaxSpeed());
			if (veh.getGpsImei() != null) {
				if (veh.getGpsImei().getGpsImei() != null)
					vehicleDtlsDto.setGpsImei(veh.getGpsImei().getGpsImei().toString());
				if (veh.getGpsImei().getGpsSimNumber() != null)
					vehicleDtlsDto.setGpsSimNumber(veh.getGpsImei().getGpsSimNumber().toString());
			}
			if (veh.getDicvCompany() != null)
				vehicleDtlsDto.setCompanyName(veh.getDicvCompany().getCompanyName());
			if (veh.getDealerUser() != null)
				vehicleDtlsDto.setDealerName(veh.getDealerUser().getFirstName() + veh.getDealerUser().getLastName());
			if (veh.getDefaultDriver() != null)
				vehicleDtlsDto
						.setDefaultDriver(veh.getDefaultDriver().getUserName());
			if (veh.getDicvUser() != null)
				vehicleDtlsDto.setCustomerAdmin(veh.getDicvUser().getFirstName() + veh.getDicvUser().getLastName());
			vehicles.add(vehicleDtlsDto);
		}
		OwnerDtlsDto ownerDtlsDto = new OwnerDtlsDto();
		ownerDtlsDto.setVehicles(vehicles);

		return ownerDtlsDto;
	}

	public MyFleetVehicleReportListDto getMyFleetVehicleList(Integer userId, String userType,
			List<Integer> vehicleIds) {
		List<Vehicle> vehicleList = vehicleRepo.getByVehicleIds(vehicleIds);
		if (vehicleList == null || vehicleList.isEmpty())
			return null;
		MyFleetVehicleReportListDto myFleetVehicleReportListDto = new MyFleetVehicleReportListDto();
		List<MyFleetVehicleReport> myFleetVehicleReportList = new ArrayList<MyFleetVehicleReport>();
		MyFleetVehicleReport myFleetVehicleReport = new MyFleetVehicleReport();
		for (Vehicle veh : vehicleList) {
			myFleetVehicleReport = new MyFleetVehicleReport();
			myFleetVehicleReport.setRegistrationId(veh.getRegistrationId());
			myFleetVehicleReport.setRunningStatus(veh.getRunningStatus());
			myFleetVehicleReport.setVehicleId(veh.getVehicleId());
			if (veh.getVehicleUpdateTime() != null)
				myFleetVehicleReport.setGpsTime(DicvUtil.getStringForTimestamp(veh.getVehicleUpdateTime()));
			if (veh.getVehicleCanParam() != null)
				myFleetVehicleReport.setGpsSpkm(veh.getCurrentVehicleSpeed());
			if (veh.getCurrentLat() != null && veh.getCurrentLong() != null) {
				myFleetVehicleReport.setLatLong(DicvConstants.GOOGLE_MAP_URL + veh.getCurrentLat().toString() + ","
						+ veh.getCurrentLong().toString());
				String address = googleAPIService.getNearestLocation(veh.getCurrentLat(), veh.getCurrentLong());
				if (address != null) {
					myFleetVehicleReport.setCurrentLocation(address);
				} else {
					Address addRess = googleAPIService.getAddress(veh.getCurrentLat(), veh.getCurrentLong());
					if (addRess.getResponse()) {
						myFleetVehicleReport.setCurrentLocation(addRess.getAddress());
					}
				}
			}
			if (veh.getDefaultDriver() != null)
				myFleetVehicleReport.setDefaultDriver(veh.getDefaultDriver().getUserName());
			myFleetVehicleReportList.add(myFleetVehicleReport);
		}
		myFleetVehicleReportListDto.setMyFleetVehicleReport(myFleetVehicleReportList);

		return myFleetVehicleReportListDto;
	}

}
