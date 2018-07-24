package com.dicv.truck.repo;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dicv.truck.dto.TripRecordDto;
import com.dicv.truck.model.Trip;
import com.dicv.truck.service.TripServices;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.EnumUserType;
import com.dicv.truck.utility.TripStatus;

@Repository
public class TripDaoImpl implements TripDao {

	@PersistenceContext
	private EntityManager entityManager;

	private static final Logger LOGGER = LoggerFactory.getLogger(TripServices.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> getTripList(TripRecordDto tripRecord, String userType) {
		List<Trip> tripRecords;
		String queryStr = "Select t from Trip t where t.isDeleted=0 and t.scheduledTrip is NOT NULL ";
		Date newFromDate = DicvUtil.getStartTimeOfDate(new Date());
		Date newToDate = DicvUtil.getEndTimeOfDate(new Date());
		if (tripRecord.getFromDate() != null && tripRecord.getToDate() != null) {
			newFromDate = DicvUtil.getStartTimeOfDate(tripRecord.getFromDate());
			newToDate = DicvUtil.getEndTimeOfDate(tripRecord.getToDate());
		}
		if ((!StringUtils.isEmpty(tripRecord.getTripStatus()))
				&& (tripRecord.getTripStatus().equals(TripStatus.PLANNED.getStatusCode())
						|| tripRecord.getTripStatus().equals(TripStatus.RUNNING.getStatusCode()))) {
			if (tripRecord.getFromDate() != null && tripRecord.getToDate() != null)
				queryStr = queryStr + " and t.scheduledTrip.fromDate >=:fromDate ";
		}
		if (!StringUtils.isEmpty(tripRecord.getTripStatus())
				&& tripRecord.getTripStatus().equals(TripStatus.COMPLETED.getStatusCode())) {
			if (tripRecord.getFromDate() != null && tripRecord.getToDate() != null)
				queryStr = queryStr + " and t.tripStartTime >=:fromDate and t.tripEndTime <=:toDate";
		}
		if (null != tripRecord.getDriverId()) {
			queryStr = queryStr + " and  t.tripDriverUser.userId=:driverId";
		}
		if (!StringUtils.isEmpty(tripRecord.getTripStatus())) {
			queryStr = queryStr + " and t.tripStatus=:tripStatus ";
		}
		try {
			queryStr = queryStr + "  order by tripId desc";
			LOGGER.info("Trip Query " + queryStr + " " + newFromDate + " - " + tripRecord);
			Query query = entityManager.createQuery(queryStr);
			
			if ((!StringUtils.isEmpty(tripRecord.getTripStatus()))
					&& (tripRecord.getTripStatus().equals(TripStatus.PLANNED.getStatusCode())
							|| tripRecord.getTripStatus().equals(TripStatus.RUNNING.getStatusCode()))) {
				if (tripRecord.getFromDate() != null && tripRecord.getToDate() != null)
					query.setParameter("fromDate", newFromDate);
			}
			
			if (!StringUtils.isEmpty(tripRecord.getTripStatus())
					&& tripRecord.getTripStatus().equals(TripStatus.COMPLETED.getStatusCode())) {
				if (tripRecord.getFromDate() != null && tripRecord.getToDate() != null)
					query.setParameter("toDate", newToDate);
			}
			
			if (null != tripRecord.getDriverId())
				query.setParameter("driverId", tripRecord.getDriverId());
			if (null != tripRecord.getVehicleId())
				query.setParameter("vehicleId", tripRecord.getVehicleId());
			if (!StringUtils.isEmpty(tripRecord.getTripStatus()))
				query.setParameter("tripStatus", tripRecord.getTripStatus());
			query.setFirstResult(tripRecord.getStartRow());
			query.setMaxResults((tripRecord.getEndRow() - tripRecord.getStartRow()) + 1);
			tripRecords = query.getResultList();
			return tripRecords;
		} catch (Exception e) {
			LOGGER.error("Exception in getTripList ", e);
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> getTripListWithDistance(TripRecordDto tripRecord, String userType) {
		List<Trip> tripRecords;
		String queryStr = "Select t from Trip t where t.isDeleted=0 ";
		Date newFromDate = DicvUtil.getStartTimeOfDate(new Date());
		Date newToDate = DicvUtil.getEndTimeOfDate(new Date());
		if (tripRecord.getFromDate() != null && tripRecord.getToDate() != null) {
			newFromDate = DicvUtil.getStartTimeOfDate(tripRecord.getFromDate());
			newToDate = DicvUtil.getEndTimeOfDate(tripRecord.getToDate());
		}
		if (tripRecord.getFromDate() != null && tripRecord.getToDate() != null)
			queryStr = queryStr + " and t.tripStartTime >=:fromDate and t.tripEndTime <=:toDate";
		if (null != tripRecord.getDriverId()) {
			queryStr = queryStr + " and  t.tripDriverUser.userId=:driverId";
		}
		if (null != tripRecord.getVehicleId()) {
			queryStr = queryStr + " and  t.vehicle.vehicleId=:vehicleId";
		}

		if (userType.equals(EnumUserType.CUSTOMERADMIN.getUserType())) {
			queryStr = queryStr + " and  t.vehicle.dicvUser.userId=:userId";
		}

		if (!StringUtils.isEmpty(tripRecord.getTripStatus())) {
			queryStr = queryStr + " and t.tripStatus=:tripStatus ";
			if (tripRecord.getTripStatus() != TripStatus.PLANNED.getStatusCode())
				queryStr = queryStr + " and t.tripDistance>=100 ";

		} else {
			queryStr = queryStr + " and (t.tripDistance>=10 OR t.tripStatus='PLANNED') ";
		}

		if (tripRecord.getKeyword() != null) {
			queryStr = queryStr + " AND( lower(trim(both from t.vehicle.registrationId)) like '%"
					+ tripRecord.getKeyword().toLowerCase() + "%'  OR CAST( t.tripId AS string ) LIKE '%"
					+ tripRecord.getKeyword() + "%' ) ";
		}
		try {
			queryStr = queryStr + "  order by tripEndTime desc";
			Query query = entityManager.createQuery(queryStr);
			if (tripRecord.getFromDate() != null && tripRecord.getToDate() != null) {
				query.setParameter("fromDate", newFromDate);
				query.setParameter("toDate", newToDate);
			}
			if (null != tripRecord.getDriverId())
				query.setParameter("driverId", tripRecord.getDriverId());
			if (null != tripRecord.getVehicleId())
				query.setParameter("vehicleId", tripRecord.getVehicleId());
			if (!StringUtils.isEmpty(tripRecord.getTripStatus()))
				query.setParameter("tripStatus", tripRecord.getTripStatus());
			if (userType.equals(EnumUserType.CUSTOMERADMIN.getUserType())) {
				query.setParameter("userId", tripRecord.getUserId());
			}
			query.setFirstResult(tripRecord.getStartRow());
			query.setMaxResults((tripRecord.getEndRow() - tripRecord.getStartRow()) + 1);
			tripRecords = query.getResultList();
			if (CollectionUtils.isEmpty(tripRecords)) {
				return null;
			}
		} catch (Exception e) {
			LOGGER.error("Exception in getTripListWithDistance ", e);
			return null;
		}
		return tripRecords;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Trip getTripDetails(Integer userId, Long tripId, boolean userRoleCheck) {
		Trip trip = null;
		try {
			String queryString = "Select s from Trip s where s.tripId=:tripId  ";
			if (userRoleCheck)
				queryString = queryString + " and s.vehicle.dicvUser.userId=:userId";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("tripId", tripId);
			if (userRoleCheck)
				query.setParameter("userId", userId);
			List<Trip> result = query.getResultList();

			if (result != null && result.size() > 0) {
				trip = (Trip) result.get(0);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in Getting Trip Details ", e);
			return null;
		}
		return trip;
	}

	@Override
	public List<Integer> getScheduledTripList() {
		try {
			Query tripQuery = entityManager.createQuery(
					"Select t.vehicle.vehicleId FROM Trip t where t.scheduledTrip IS NOT NULL and t.vehicle.isDeleted=0 and "
							+ " t.isDeleted=0 and t.tripStatus!=:tripCompleted and " + " t.tripStatus!=:tripCancelled");
			tripQuery.setParameter("tripCompleted", TripStatus.COMPLETED.toString());
			tripQuery.setParameter("tripCancelled", TripStatus.CANCELED.toString());
			List<Integer> tripList = tripQuery.getResultList();
			if (tripList.size() > 0) {
				return tripList;
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in getScheduledTripList ", ex);
			return null;
		}
		return Collections.EMPTY_LIST;
	}

}
