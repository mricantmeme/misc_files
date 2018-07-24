package com.dicv.truck.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicv.truck.dto.GeoFenceDeleteDto;
import com.dicv.truck.dto.GeoFenceDeleteListDto;
import com.dicv.truck.dto.GeoFenceDto;
import com.dicv.truck.dto.GeoFenceListDto;
import com.dicv.truck.dto.GeoFenceTypeDto;
import com.dicv.truck.dto.GeoFenceTypeListDto;
import com.dicv.truck.dto.ManageGeoFenceDto;
import com.dicv.truck.dto.StatusMessageDto;
import com.dicv.truck.dto.VehicleInfoDto;
import com.dicv.truck.model.DicvGeoFenceType;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.GeoFenceCircle;
import com.dicv.truck.model.GeoFenceInfo;
import com.dicv.truck.model.GeoFencePolygon;
import com.dicv.truck.model.GeoFenceShape;
import com.dicv.truck.model.Vehicle;
import com.dicv.truck.model.VehicleToGeoFence;
import com.dicv.truck.repo.DicvGeoFenceTypeRepo;
import com.dicv.truck.repo.GeoFenceRepo;
import com.dicv.truck.repo.GeoFenceShapeRepo;
import com.dicv.truck.repo.VehicleToGeoFenceRepo;
import com.dicv.truck.utility.DicvConstants;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.EnumUserType;

@Service
public class GeoFenceService {

	@Autowired
	private UserService userService;

	@Autowired
	private VehicleServices vehicleServices;

	@Autowired
	private GeoFenceRepo geoFenceRepo;

	@Autowired
	private GeoFenceShapeRepo geoFenceShapeRepo;

	@Autowired
	private VehicleToGeoFenceRepo vehicleToGeoFenceRepo;

	@Autowired
	private DicvGeoFenceTypeRepo dicvGeoFenceTypeRepo;

	private static final Logger LOGGER = Logger.getLogger(GeoFenceService.class);

	public GeoFenceListDto getGeoFenceList(Integer userId, Integer vehicleId) {
		GeoFenceListDto geoFenceDtoList = new GeoFenceListDto();
		try {

			DicvUser users = userService.getUser(userId, "getGeoFenceList");
			if (users == null) {
				geoFenceDtoList.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				geoFenceDtoList.setMessage(DicvConstants.USER_DOESNOT_EXIST);
				return geoFenceDtoList;
			}
			GeoFenceDto geoFenceDto = null;
			ArrayList<GeoFenceDto> geoFenceDtoList1 = new ArrayList<GeoFenceDto>();
			List<GeoFenceInfo> geoFenceList = null;
			LOGGER.info("GeoFence List " + userId + " Vehicle " + vehicleId);
			if (vehicleId == null) {
				geoFenceList = geoFenceRepo.getGeoFenceList(userId);
			} else {
				geoFenceList = vehicleToGeoFenceRepo.getGeoFenceListByVehicle(userId, vehicleId);
			}
			if (geoFenceList != null && !geoFenceList.isEmpty()) {
				for (GeoFenceInfo geoFence : geoFenceList) {
					geoFenceDto = new GeoFenceDto();
					if (geoFence.getDicvUser().getUserId() != null)
						geoFenceDto.setDicvUserId(geoFence.getDicvUser().getUserId());
					if (geoFence.getGeoFenceName() != null)
						geoFenceDto.setGeoFenceName(geoFence.getGeoFenceName());

					if (geoFence.getEntryAlert() != null)
						geoFenceDto.setEntryAlert(geoFence.getEntryAlert());

					DateFormat dateFormate = new SimpleDateFormat("dd-MM-yy hh:mm:ss");

					if (geoFence.getEntryNotificationTime() != null) {
						geoFenceDto.setEntryNotificationTime(
								dateFormate.format((Date) geoFence.getEntryNotificationTime()));
					}

					if (geoFence.getExitNotificationTime() != null) {
						geoFenceDto
								.setExitNotificationTime(dateFormate.format((Date) geoFence.getExitNotificationTime()));
					}

					if (geoFence.getExitAlert() != null)
						geoFenceDto.setExitAlert(geoFence.getExitAlert());

					if (geoFence.getGeoFenceShape() != null && geoFence.getGeoFenceCircles().size() > 0
							&& geoFence.getGeoFenceShape().getGeoFenceShapeName().equalsIgnoreCase("circle")
							&& geoFence.getGeoFenceCircles().get(0).getGeoLatitude() != null
							&& geoFence.getGeoFenceCircles().size() == 1) {
						geoFenceDto.setGeoFenceCircleLatitude(geoFence.getGeoFenceCircles().get(0).getGeoLatitude());
					}

					if (geoFence.getGeoFenceShape() != null && geoFence.getGeoFenceCircles().size() > 0
							&& geoFence.getGeoFenceShape().getGeoFenceShapeName().equalsIgnoreCase("circle")
							&& geoFence.getGeoFenceCircles().get(0).getGeoLongitude() != null
							&& geoFence.getGeoFenceCircles().size() == 1)
						geoFenceDto.setGeoFenceCircleLongitude(geoFence.getGeoFenceCircles().get(0).getGeoLongitude());

					if (geoFence.getGeoFenceShape() != null && geoFence.getGeoFenceCircles().size() > 0
							&& geoFence.getGeoFenceShape().getGeoFenceShapeName().equalsIgnoreCase("circle")
							&& geoFence.getGeoFenceCircles().get(0).getGeoRadiusInMeters() != null
							&& geoFence.getGeoFenceCircles().size() == 1)
						geoFenceDto.setGeoFenceCircleRadiusInMeters(
								geoFence.getGeoFenceCircles().get(0).getGeoRadiusInMeters());

					if (geoFence.getGeoFenceId() != null)
						geoFenceDto.setGeoFenceId(geoFence.getGeoFenceId());

					if (geoFenceDto.getGeoFenceName() != null)
						geoFenceDto.setGeoFenceName(geoFenceDto.getGeoFenceName());

					if (geoFence.getGeoFenceShape() != null && geoFence.getGeoFencePolygons().size() > 0
							&& geoFence.getGeoFenceShape().getGeoFenceShapeName().equalsIgnoreCase("polygon")
							&& geoFence.getGeoFencePolygons().get(0).getGeoCordinates() != null
							&& geoFence.getGeoFencePolygons().size() == 1) {
						String strlatlng = geoFence.getGeoFencePolygons().get(0).getGeoCordinates();
						String[] arr = strlatlng.split("!");

						List<Float> list = null;
						List<List<Float>> finalList = new LinkedList<List<Float>>();
						for (int i = 0; i <= arr.length - 1; i++) {
							list = new LinkedList<Float>();
							String latlng = arr[i];
							String[] arrlatlng = latlng.split(",");
							for (String cordinate : arrlatlng) {
								list.add(Float.parseFloat(cordinate));
							}
							finalList.add(list);
						}
						geoFenceDto.setGeoFencePolygonCoordinates(finalList);
					}

					if (geoFence.getGeoFenceShape() != null)
						geoFenceDto.setShapeType(geoFence.getGeoFenceShape().getGeoFenceShapeName());

					if (geoFence.getSkippedAlert() != null)
						geoFenceDto.setSkippedAlert(geoFence.getSkippedAlert());

					if (geoFence.getDicvGeoFenceType() != null
							&& geoFence.getDicvGeoFenceType().getGeoFenceTypeId() != null)
						geoFenceDto.setTypeId(geoFence.getDicvGeoFenceType().getGeoFenceTypeId());

					if (geoFence.getValidFrom() != null)
						geoFenceDto.setValidFrom(dateFormate.format((Date) geoFence.getValidFrom()));

					if (geoFence.getValidTo() != null)
						geoFenceDto.setValidTo(dateFormate.format((Date) geoFence.getValidTo()));

					List<Vehicle> vehicleList = vehicleToGeoFenceRepo.getVehicleList(geoFence.getGeoFenceId());
					ArrayList<VehicleInfoDto> vehicleInfoList = new ArrayList<VehicleInfoDto>();

					VehicleInfoDto vehicleInfoDto = null;
					for (Vehicle vehicle : vehicleList) {
						vehicleInfoDto = new VehicleInfoDto();
						if (vehicle.getVehicleId() != null) {
							vehicleInfoDto.setVehicleId(vehicle.getVehicleId());
						}
						if (vehicle.getRegistrationId() != null) {
							vehicleInfoDto.setRegistrationId(vehicle.getRegistrationId());
						}
						vehicleInfoList.add(vehicleInfoDto);
					}
					geoFenceDto.setVehicleInfoList(vehicleInfoList);
					geoFenceDtoList1.add(geoFenceDto);
				}
				geoFenceDtoList.setGeoFenceList(geoFenceDtoList1);
				geoFenceDtoList.setStatus(HttpServletResponse.SC_OK);
				geoFenceDtoList.setMessage(DicvConstants.DATA_FOUND_MSG);
				return geoFenceDtoList;
			}
			geoFenceDtoList.setStatus(HttpServletResponse.SC_OK);
			geoFenceDtoList.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);

		} catch (Exception e) {
			LOGGER.error("Error in GeoFence List ", e);
			geoFenceDtoList.setStatus(HttpServletResponse.SC_NO_CONTENT);
			geoFenceDtoList.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
		}
		return geoFenceDtoList;
	}

	public GeoFenceTypeListDto getGeoFenceType(Integer userId) {

		GeoFenceTypeListDto typeLists = new GeoFenceTypeListDto();
		try {
			DicvUser dicvUser = userService.getUser(userId, "getGeoFenceType");
			List<DicvGeoFenceType> typeList = dicvGeoFenceTypeRepo.getGeoFenceTypeList(dicvUser.getUserId());// query.getResultList();
			if (typeList != null && typeList.size() > 0) {
				int typeListSize = typeList.size();
				ArrayList<GeoFenceTypeDto> list = new ArrayList<GeoFenceTypeDto>(typeListSize);
				for (DicvGeoFenceType dicvGeoFenceType : typeList) {
					GeoFenceTypeDto type = new GeoFenceTypeDto();
					type.setTypeId(dicvGeoFenceType.getGeoFenceTypeId());
					type.setTypeName(dicvGeoFenceType.getTypeName());
					type.setUserId(dicvGeoFenceType.getUpdatedByUser());
					list.add(type);
				}
				typeLists = new GeoFenceTypeListDto();
				typeLists.setTypeList(list);
				typeLists.setStatus(HttpServletResponse.SC_OK);
				typeLists.setMessage(DicvConstants.DATA_FOUND_MSG);
			} else {
				typeLists.setStatus(HttpServletResponse.SC_OK);
				typeLists.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			}
			return typeLists;
		} catch (Exception e) {
			LOGGER.error("Error in GeoFenceType List ", e);
			typeLists.setStatus(HttpServletResponse.SC_NO_CONTENT);
			typeLists.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
			return typeLists;
		}
	}

	@Transactional
	public ManageGeoFenceDto manageGeoFence(GeoFenceDto geoFence) {
		ManageGeoFenceDto manageGeoFenceDto = new ManageGeoFenceDto();
		try {
			List<Integer> vehicleList = geoFence.getVehicleList();

			if (vehicleList.size() <= 0) {
				manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				manageGeoFenceDto.setMessage("Please Select Atleast One Vehicle");
				return manageGeoFenceDto;
			}
			GeoFenceInfo geoFenceEntity = new GeoFenceInfo();

			LOGGER.info("user Id not null " + geoFence.getDicvUserId());

			// Get user Object
			DicvUser users = userService.getUser(geoFence.getDicvUserId(), "manageGeoFence");

			// Get Geofence type object
			DicvGeoFenceType type = null;
			if (geoFence.getTypeId() != null) {
				type = dicvGeoFenceTypeRepo.findOne(geoFence.getTypeId());
				if (null == type || type.getIsDeleted() != 0) {
					manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					manageGeoFenceDto.setMessage("Given geoFenceType is not valid");
					return manageGeoFenceDto;
				}
			}

			// Check user role as only Manager and Owner can create Geofence
			if (!users.getUserType().getUserType().equals(EnumUserType.CUSTOMERADMIN.getUserType())
					&& !users.getUserType().getUserType().equals(EnumUserType.ROOTADMIN.getUserType())) {
				manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				manageGeoFenceDto.setMessage(DicvConstants.UNAUTHORIZED_USER);
				return manageGeoFenceDto;
			}

			// Geofence Name can't be more than 50 characters and is Unique for
			// user
			if (geoFenceRepo.checkGeoFenceNameExist(geoFence.getDicvUserId(), geoFence.getGeoFenceName()) == 0) {
				geoFenceEntity.setGeoFenceName(geoFence.getGeoFenceName());
			} else {
				manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				manageGeoFenceDto.setMessage("GeoFence Already Exists With this Name");
				return manageGeoFenceDto;
			}

			// Circular geofence
			if (DicvConstants.GEOFENCE_CIRCLE.equalsIgnoreCase(geoFence.getShapeType())) {
				if (geoFence.getGeoFenceCircleLongitude() != null && geoFence.getGeoFenceCircleLatitude() != null
						&& geoFence.getGeoFenceCircleRadiusInMeters() != null) {
					List<GeoFenceCircle> geoFenceCircleList = new ArrayList<GeoFenceCircle>();
					GeoFenceCircle geoFenceCircleEntity = new GeoFenceCircle();
					geoFenceCircleEntity.setCreatedDate(Calendar.getInstance());
					geoFenceCircleEntity.setModifiedDate(Calendar.getInstance());
					geoFenceCircleEntity.setGeoLongitude(geoFence.getGeoFenceCircleLongitude());
					geoFenceCircleEntity.setGeoLatitude(geoFence.getGeoFenceCircleLatitude());
					geoFenceCircleEntity.setGeoRadiusInMeters(geoFence.getGeoFenceCircleRadiusInMeters());
					geoFenceCircleEntity.setCreatedBy(users.getUserId());
					geoFenceCircleEntity.setUpdatedBy(users.getUserId());
					geoFenceCircleEntity.setIsDeleted(0);
					geoFenceCircleEntity.setGeoFenceInfo(geoFenceEntity);
					geoFenceCircleList.add(geoFenceCircleEntity);
					geoFenceEntity.setGeoFenceCircles(geoFenceCircleList);
					List<GeoFenceShape> shape = geoFenceShapeRepo.checkGeoFenceShape(DicvConstants.GEOFENCE_CIRCLE);
					geoFenceEntity.setGeoFenceShape(shape.get(0));
				} else {
					manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					manageGeoFenceDto.setMessage(DicvConstants.INVALIDINPUT_EXCEPTION_MSG);
					return manageGeoFenceDto;
				}

			}

			// Polygonal geofence
			if (DicvConstants.GEOFENCE_POLYGON.equalsIgnoreCase(geoFence.getShapeType())) {
				List<List<Float>> cordinates = null;

				// Convert coordinates to string
				if (geoFence.getGeoFencePolygonCoordinates() != null) {
					cordinates = geoFence.getGeoFencePolygonCoordinates();
					StringBuffer sBuffer = new StringBuffer();
					String firstLatLong = null;
					boolean isFirst = true;
					for (List<Float> list : cordinates) {
						boolean flag = true;
						for (Float cordinate : list) {
							sBuffer.append(cordinate);
							if (flag) {
								sBuffer.append(",");
							}
							flag = false;
						}
						sBuffer.append("!");
						if (isFirst) {
							firstLatLong = sBuffer.toString();
							isFirst = false;
						}
					}

					String cordinatesToString = sBuffer.toString();
					cordinatesToString = cordinatesToString + firstLatLong;
					List<GeoFencePolygon> geoFencePolygonList = new ArrayList<GeoFencePolygon>();
					GeoFencePolygon geoFencePolygonEntity = new GeoFencePolygon();
					geoFencePolygonEntity.setCreatedDate(Calendar.getInstance());
					geoFencePolygonEntity.setModifiedDate(Calendar.getInstance());
					geoFencePolygonEntity.setGeoCordinates(cordinatesToString);
					geoFencePolygonEntity.setCreatedBy(users.getUserId());
					geoFencePolygonEntity.setUpdatedBy(users.getUserId());
					geoFencePolygonEntity.setIsDeleted(0);
					geoFencePolygonEntity.setGeoFenceInfo(geoFenceEntity);
					geoFencePolygonList.add(geoFencePolygonEntity);
					geoFenceEntity.setGeoFencePolygons(geoFencePolygonList);
					List<GeoFenceShape> shape = geoFenceShapeRepo.checkGeoFenceShape(DicvConstants.GEOFENCE_POLYGON);
					geoFenceEntity.setGeoFenceShape(shape.get(0));
				} else {
					manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					manageGeoFenceDto.setMessage(DicvConstants.INVALIDINPUT_EXCEPTION_MSG);
					return manageGeoFenceDto;
				}
			}

			geoFenceEntity.setDicvUser(users);
			geoFenceEntity.setUpdatedBy(users.getUserId());

			geoFenceEntity.setCreatedOn(Calendar.getInstance());
			geoFenceEntity.setLastUpdatedOn(Calendar.getInstance());
			geoFenceEntity.setIsDeleted(0);
			geoFenceEntity.setDicvGeoFenceType(type);

			if (geoFence.getValidFrom() != null) {
				geoFenceEntity.setValidFrom(stringToDate(geoFence.getValidFrom()));
			}
			if (geoFence.getValidTo() != null) {
				geoFenceEntity.setValidTo(stringToDate(geoFence.getValidTo()));
			}
			if (geoFence.getEntryAlert() != null) {
				geoFenceEntity.setEntryAlert(geoFence.getEntryAlert());
			}
			if (geoFence.getExitAlert() != null) {
				geoFenceEntity.setExitAlert(geoFence.getExitAlert());
			}
			if (geoFence.getSkippedAlert() != null) {
				geoFenceEntity.setSkippedAlert(geoFence.getSkippedAlert());
			}
			if (geoFence.getEntryNotificationTime() != null) {
				geoFenceEntity.setEntryNotificationTime(stringToDateTime(geoFence.getEntryNotificationTime()));
			}
			if (geoFence.getExitNotificationTime() != null) {
				geoFenceEntity.setExitNotificationTime(stringToDateTime(geoFence.getExitNotificationTime()));
			}

			// For selected vehicles
			VehicleToGeoFence vehicleToGeoFenceModel;
			List<VehicleToGeoFence> vehToGeoList = new ArrayList<>();
			if (vehicleList.size() > 0) {

				List<Vehicle> Vehicles = vehicleServices.getVehicleByVehicleIds(vehicleList);

				for (Vehicle vehicle : Vehicles) {
					// To check for user vehicle mapping
					if (users.getUserId() == vehicle.getDicvUser().getUserId()) {
						vehicleToGeoFenceModel = new VehicleToGeoFence();
						vehicleToGeoFenceModel.setCreatedOn(Calendar.getInstance());
						vehicleToGeoFenceModel.setLastUpdatedOn(Calendar.getInstance());
						vehicleToGeoFenceModel.setCreatedBy(users.getUserId());
						vehicleToGeoFenceModel.setUpdatedBy(users.getUserId());
						vehicleToGeoFenceModel.setIsDeleted(0);
						vehicleToGeoFenceModel.setVehicle(vehicle);
						vehicleToGeoFenceModel.setGeoFenceInfo(geoFenceEntity);
						vehToGeoList.add(vehicleToGeoFenceModel);
						geoFenceEntity.setVehicleToGeoFences(vehToGeoList);
					} else {
						manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						manageGeoFenceDto.setMessage("Vehicle not mapped to the User");
						return manageGeoFenceDto;

					}
				}
			}
			geoFenceRepo.save(geoFenceEntity);
			manageGeoFenceDto.setStatus(HttpServletResponse.SC_OK);
			manageGeoFenceDto.setMessage(DicvConstants.SUCCESS_CREATED);
			manageGeoFenceDto.setIdentifier(manageGeoFenceDto.getGeoFenceId());
			return manageGeoFenceDto;

		} catch (Exception e) {
			LOGGER.log(Level.ERROR, "Exception in Create GeoFence" + e.getMessage());
			manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			manageGeoFenceDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return manageGeoFenceDto;
		}

	}

	@Transactional
	public ManageGeoFenceDto editGeoFence(GeoFenceDto geoFenceEdit) {
		ManageGeoFenceDto manageGeoFenceDto = new ManageGeoFenceDto();
		try {
			LOGGER.info("Inside Update GeoFence " + geoFenceEdit);
			// Get Geofence From Geofence Id
			GeoFenceInfo geoFence = geoFenceRepo.findOne(geoFenceEdit.getGeoFenceId());

			if (geoFence == null || geoFence.getDicvUser() == null
					|| !geoFence.getDicvUser().getUserId().equals(geoFenceEdit.getDicvUserId())) {
				manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				manageGeoFenceDto.setMessage("GeoFence Not Found");
				return manageGeoFenceDto;

			}

			DicvUser users = userService.getUser(geoFenceEdit.getDicvUserId(), "editGeoFence");
			DicvGeoFenceType type = null;

			if (null == users) {
				manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				manageGeoFenceDto.setMessage(DicvConstants.UNAUTHORIZED_USER);
				return manageGeoFenceDto;
			}
			if (null != geoFenceEdit.getTypeId()) {
				type = dicvGeoFenceTypeRepo.findOne(geoFenceEdit.getTypeId());
				if (type == null) {
					manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					manageGeoFenceDto.setMessage("GeoFenceType  Id is not valid");
					return manageGeoFenceDto;
				}
			}
			// Geofence Name can't be more than 50 characters and is Unique
			// for user
			if (null != geoFenceEdit.getGeoFenceName() && geoFenceEdit.getGeoFenceName() != ""
					&& (!geoFence.getGeoFenceName().equals(geoFenceEdit.getGeoFenceName()))) {
				if (geoFenceRepo.checkGeoFenceNameExist(geoFenceEdit.getDicvUserId(), geoFenceEdit.getGeoFenceId(),
						geoFenceEdit.getGeoFenceName()) > 0) {
					manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					manageGeoFenceDto.setMessage("GeoFence Already Exists With this Name");
					return manageGeoFenceDto;
				}
				geoFence.setGeoFenceName(geoFenceEdit.getGeoFenceName());

				// Edited Vehicle List
				List<Integer> newVehicleList = geoFenceEdit.getVehicleList();

				if (newVehicleList.size() <= 0) {
					manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					manageGeoFenceDto.setMessage("Please Select Atleast One Vehicle");
					return manageGeoFenceDto;
				}

				geoFence.setUpdatedBy(users.getUserId());

				if (DicvConstants.GEOFENCE_CIRCLE
						.equalsIgnoreCase(geoFence.getGeoFenceShape().getGeoFenceShapeName())) {
					if (geoFenceEdit.getGeoFenceCircleLongitude() != null
							|| geoFenceEdit.getGeoFenceCircleLatitude() != null
							|| geoFenceEdit.getGeoFenceCircleRadiusInMeters() != null) {
						List<GeoFenceCircle> geoFenceCircleList = new ArrayList<GeoFenceCircle>();
						GeoFenceCircle geoFenceCircleEntity = geoFence.getGeoFenceCircles().get(0);
						geoFenceCircleEntity.setModifiedDate(Calendar.getInstance());
						if (geoFenceEdit.getGeoFenceCircleLongitude() != null) {
							geoFenceCircleEntity.setGeoLongitude(geoFenceEdit.getGeoFenceCircleLongitude());
						}
						geoFenceCircleEntity.setUpdatedBy(users.getUserId());
						if (geoFenceEdit.getGeoFenceCircleLatitude() != null) {
							geoFenceCircleEntity.setGeoLatitude(geoFenceEdit.getGeoFenceCircleLatitude());
						}
						if (geoFenceEdit.getGeoFenceCircleRadiusInMeters() != null) {
							geoFenceCircleEntity.setGeoRadiusInMeters(geoFenceEdit.getGeoFenceCircleRadiusInMeters());
						}
						geoFenceCircleList.add(geoFenceCircleEntity);
						geoFence.setGeoFenceCircles(geoFenceCircleList);
					}
				}

				if (DicvConstants.GEOFENCE_POLYGON
						.equalsIgnoreCase(geoFence.getGeoFenceShape().getGeoFenceShapeName())) {
					List<List<Float>> cordinates = null;
					if (geoFenceEdit.getGeoFencePolygonCoordinates() != null) {
						cordinates = geoFenceEdit.getGeoFencePolygonCoordinates();
						StringBuffer sBuffer = new StringBuffer();
						for (List<Float> list : cordinates) {
							boolean flag = true;
							for (Float cordinate : list) {
								sBuffer.append(cordinate);
								if (flag) {
									sBuffer.append(",");
								}
								flag = false;
							}
							sBuffer.append("!");
						}
						String cordinatesToString = sBuffer.toString();
						;
						List<GeoFencePolygon> geoFencePolygonList = new ArrayList<GeoFencePolygon>();
						GeoFencePolygon geoFencePolygonEntity = geoFence.getGeoFencePolygons().get(0);
						geoFencePolygonEntity.setModifiedDate(Calendar.getInstance());
						geoFencePolygonEntity.setUpdatedBy(users.getUserId());
						geoFencePolygonEntity.setGeoCordinates(cordinatesToString);
						geoFencePolygonList.add(geoFencePolygonEntity);
						geoFence.setGeoFencePolygons(geoFencePolygonList);
					}
				}

				geoFence.setLastUpdatedOn(Calendar.getInstance());

				geoFence.setDicvGeoFenceType(type);// setDicvType(dicvType);

				if (geoFenceEdit.getValidFrom() != null) {
					geoFence.setValidFrom(stringToDate(geoFenceEdit.getValidFrom()));
				}
				if (geoFenceEdit.getValidTo() != null) {
					geoFence.setValidTo(stringToDate(geoFenceEdit.getValidTo()));
				}
				if (null != geoFenceEdit.getEntryAlert()) {
					geoFence.setEntryAlert(geoFenceEdit.getEntryAlert());
				}
				if (null != geoFenceEdit.getExitAlert()) {
					geoFence.setExitAlert(geoFenceEdit.getExitAlert());
				}
				if (null != geoFenceEdit.getSkippedAlert()) {
					geoFence.setSkippedAlert(geoFenceEdit.getSkippedAlert());
				}

				// Existing Vehicles in Geofence- existingGeoFencedVehicles
				List<VehicleToGeoFence> existingGeoFencedVehicles = vehicleToGeoFenceRepo
						.getVehicleToGeoFenceList(geoFence.getGeoFenceId());

				// Merging existing vehicle list and new vehicle list
				for (VehicleToGeoFence existingGeoFencedVehicle : existingGeoFencedVehicles) {
					if (!newVehicleList.contains(existingGeoFencedVehicle.getVehicle().getVehicleId())) {
						existingGeoFencedVehicle.setIsDeleted(1);
						existingGeoFencedVehicle.setLastUpdatedOn(Calendar.getInstance());
					}
					if (newVehicleList.contains(existingGeoFencedVehicle.getVehicle().getVehicleId())) {
						newVehicleList.remove(existingGeoFencedVehicle.getVehicle().getVehicleId());
					}
				}

				// Editing VehicleToGeoFence table according to new Vehicle List
				VehicleToGeoFence vehicleToGeoFenceModel;
				List<VehicleToGeoFence> vehToGeoList = new ArrayList<>();
				if (newVehicleList.size() > 0) {
					List<Vehicle> Vehicles = vehicleServices.getVehicleByVehicleIds(newVehicleList);

					for (Vehicle vehicle : Vehicles) {
						if (users.getUserId() == vehicle.getDicvUser().getUserId()) {
							vehicleToGeoFenceModel = new VehicleToGeoFence();
							vehicleToGeoFenceModel.setCreatedOn(Calendar.getInstance());
							vehicleToGeoFenceModel.setCreatedBy(users.getUserId());
							vehicleToGeoFenceModel.setLastUpdatedOn(Calendar.getInstance());
							vehicleToGeoFenceModel.setIsDeleted(0);
							vehicleToGeoFenceModel.setUpdatedBy(users.getUserId());
							vehicleToGeoFenceModel.setVehicle(vehicle);
							vehicleToGeoFenceModel.setGeoFenceInfo(geoFence);
							vehToGeoList.add(vehicleToGeoFenceModel);
							geoFence.setVehicleToGeoFences(vehToGeoList);
						} else {
							manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
							manageGeoFenceDto.setMessage(DicvConstants.VEHICLE_NOT_FOUND_FOR_USER);
							return manageGeoFenceDto;
						}
					}
				}
			}
			geoFenceRepo.save(geoFence);
			manageGeoFenceDto.setStatus(HttpServletResponse.SC_OK);
			manageGeoFenceDto.setMessage(DicvConstants.SUCCESS_UPDATED);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, "Exception in Create GeoFence" + e.getMessage());
			manageGeoFenceDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			manageGeoFenceDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return manageGeoFenceDto;
		}
		return manageGeoFenceDto;
	}

	public String dateToString(Date date) {
		DateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		return (sdf.format(date));
	}

	public Date stringToDate(String date) throws ParseException {
		DateFormat dateFormate = new SimpleDateFormat("dd-MMM-yyyy");
		return (dateFormate.parse(date));
	}

	public Date stringToDateTime(String date) throws ParseException {
		DateFormat dateFormate = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
		return (dateFormate.parse(date));
	}

	@Transactional
	public StatusMessageDto manageGeoFenceType(GeoFenceTypeDto type) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			if (type.getIsDelete()) {
				DicvGeoFenceType typeModel = dicvGeoFenceTypeRepo.findOne(type.getTypeId());
				if (typeModel == null) {
					statusMessageDto.setStatus(HttpServletResponse.SC_OK);
					statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
					return statusMessageDto;
				}
				typeModel.setUpdatedOn(DicvUtil.getTimestamp());
				typeModel.setIsDeleted(1);
				dicvGeoFenceTypeRepo.save(typeModel);
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.SUCCESS_DELETED);
				return statusMessageDto;
			}
			DicvUser dicvUser = userService.getUser(type.getUserId(), "manageGeoFenceType");
			if (dicvUser == null) {
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
				return statusMessageDto;
			}
			if (type.getTypeId() == null || type.getTypeId() <= 0) {
				return addGeoFenceType(type, statusMessageDto, dicvUser);
			} else {
				DicvGeoFenceType typeModel = dicvGeoFenceTypeRepo.findOne(type.getTypeId());
				if (typeModel == null) {
					statusMessageDto.setStatus(HttpServletResponse.SC_OK);
					statusMessageDto.setMessage(DicvConstants.DATA_NOT_FOUND_MSG);
					return statusMessageDto;
				}

				if (type.getTypeName() != null) {
					typeModel.setTypeName(type.getTypeName());
				}
				statusMessageDto.setMessage(DicvConstants.SUCCESS_UPDATED);
				if (type.getIsDelete()) {
					typeModel.setIsDeleted(1);
					statusMessageDto.setMessage(DicvConstants.SUCCESS_DELETED);
				}
				typeModel.setUpdatedByUser(dicvUser.getUserId());
				typeModel.setUpdatedOn(DicvUtil.getTimestamp());
				dicvGeoFenceTypeRepo.save(typeModel);
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				return statusMessageDto;
			}

		} catch (Exception e) {
			LOGGER.error("Exception in Add/Update GeoFence Type :: " + e);
			statusMessageDto.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			statusMessageDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return statusMessageDto;
		}
	}

	@Transactional
	private StatusMessageDto addGeoFenceType(GeoFenceTypeDto type, StatusMessageDto statusMessageDto,
			DicvUser dicvUser) {
		List<String> getTypeName = dicvGeoFenceTypeRepo.checkGeoFenceTypeNameExist(type.getTypeName());
		if (getTypeName != null && !getTypeName.isEmpty()) {
			statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			statusMessageDto.setMessage("GeoFence Type Name " + type.getTypeName() + " already exist");
			return statusMessageDto;
		} else {
			DicvGeoFenceType typeModel = new DicvGeoFenceType();
			if (type.getTypeName() != null && type.getTypeName().trim() != "") {
				typeModel.setTypeName(type.getTypeName());
			} else {
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage("Please Enter Proper Type Name");
				return statusMessageDto;
			}
			typeModel.setCreatedByUser(dicvUser);
			typeModel.setUpdatedByUser(dicvUser.getUserId());
			typeModel.setIsDeleted(0);
			typeModel.setCreatedOn(DicvUtil.getTimestamp());
			typeModel.setUpdatedOn(DicvUtil.getTimestamp());
			dicvGeoFenceTypeRepo.save(typeModel);
			statusMessageDto.setStatus(HttpServletResponse.SC_OK);
			statusMessageDto.setMessage(DicvConstants.SUCCESS_CREATED);
			return statusMessageDto;
		}
	}

	public StatusMessageDto deleteGeoFence(GeoFenceDeleteListDto geoFenceDeleteList) {
		StatusMessageDto statusMessageDto = new StatusMessageDto();
		try {
			if (null != geoFenceDeleteList) {
				for (GeoFenceDeleteDto geoFenceDelete : geoFenceDeleteList.getGeoFenceDeleteList()) {
					try {
						GeoFenceInfo geoFence = geoFenceRepo.getOne(geoFenceDelete.getGeoFenceId());
						geoFence.setIsDeleted(1);
						for (GeoFenceCircle geoFenceCircle : geoFence.getGeoFenceCircles()) {
							geoFenceCircle.setIsDeleted(1);
							geoFenceCircle.setUpdatedBy(geoFenceDelete.getUserId());
							geoFenceCircle.setModifiedDate(Calendar.getInstance());
						}

						for (GeoFencePolygon geoFencePolygon : geoFence.getGeoFencePolygons()) {
							geoFencePolygon.setIsDeleted(1);
							geoFencePolygon.setUpdatedBy(geoFenceDelete.getUserId());
							geoFencePolygon.setModifiedDate(Calendar.getInstance());
						}

						for (VehicleToGeoFence vehicleToGeoFence : geoFence.getVehicleToGeoFences()) {
							vehicleToGeoFence.setIsDeleted(1);
							vehicleToGeoFence.setUpdatedBy(geoFenceDelete.getUserId());
							vehicleToGeoFence.setLastUpdatedOn(Calendar.getInstance());
						}
						geoFence.setLastUpdatedOn(Calendar.getInstance());
						geoFenceRepo.save(geoFence);
					} catch (Exception e) {
						LOGGER.error("GeoFence Not Available " + e);
						continue;
					}

				}
				statusMessageDto.setStatus(HttpServletResponse.SC_OK);
				statusMessageDto.setMessage(DicvConstants.SUCCESS_DELETED);
				return statusMessageDto;
			} else

			{
				statusMessageDto.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				statusMessageDto.setMessage("No Data Available");
				return statusMessageDto;
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Delete GeoFence Type :: " + e);
			statusMessageDto.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			statusMessageDto.setMessage(DicvConstants.SERVER_EXCEPTION_MSG);
			return statusMessageDto;
		}
	}

}
