package com.dicv.truck.repo;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dicv.truck.dto.DriverAnalysisDto;
import com.dicv.truck.dto.DriverAnalysisLineChartDto;
import com.dicv.truck.dto.DriverAnalysisLineChartListDto;
import com.dicv.truck.dto.DriverAnalysisListDto;
import com.dicv.truck.dto.DriverTripPlaybackDto;
import com.dicv.truck.dto.DriverTripPlaybackListDto;
import com.dicv.truck.dto.SpeedCountDto;
import com.dicv.truck.exception.DataNotFoundException;
import com.dicv.truck.model.DicvUser;
import com.dicv.truck.model.DriverAnalysisList;
import com.dicv.truck.utility.DicvNativeQueries;
import com.dicv.truck.utility.DicvUtil;
import com.dicv.truck.utility.UserStatus;

import oracle.jdbc.OracleTypes;

@Repository
public class DriverImplDao implements DriverDao {

	@PersistenceContext
	public EntityManager entityManager;

	@Autowired
	private UserRepo userRepo;

	private static final Logger LOGGER = Logger.getLogger(DriverImplDao.class);

	@Override
	public DriverAnalysisListDto getDriverAnalysisFromTrip(Date startDate, Date endDate, int userId, String driverId) {
		List<DriverAnalysisDto> driverAnalysisList = null;

		DriverAnalysisListDto driverAnalysisListDto = null;

		DriverAnalysisDto driverAnalysis = null;

		CallableStatement callableStatement = null;

		ResultSet rs = null;

		Connection conn = null;
		try {

			SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-YY");

			String fromDate = dateformat.format(startDate);

			String toDate = dateformat.format(endDate);

			Session session = entityManager.unwrap(Session.class);
			SessionImplementor sessionImplementor = (SessionImplementor) session;
			conn = sessionImplementor.getJdbcConnectionAccess().obtainConnection();

			callableStatement = conn.prepareCall("{call DRIVER_ANALYSIS(?,?,?,?,?,?,?,?)}");

			callableStatement.setString(1, driverId);

			callableStatement.setInt(2, userId);

			callableStatement.setString(3, fromDate);
			callableStatement.setString(4, toDate);
			callableStatement.setString(5, null);

			callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
			callableStatement.registerOutParameter(7, OracleTypes.NUMBER);
			callableStatement.registerOutParameter(8, OracleTypes.VARCHAR);

			callableStatement.execute();

			String str = callableStatement.getString(8);

			if (str.equals("Success")) {
				rs = (ResultSet) callableStatement.getObject(6);
				driverAnalysisList = new ArrayList<DriverAnalysisDto>();

			} else {
				throw new DataNotFoundException(str);
			}
			while (rs.next()) {
				if (rs.getBigDecimal("TRIP_DISTANCE") != null && rs.getBigDecimal("TRIP_DISTANCE").longValue() >= 10) {
					driverAnalysis = driverAnalysis(rs, driverAnalysis);
					DicvUser dicvUser = userRepo.findOne(driverAnalysis.getDriverId());
					if (dicvUser != null
							&& !dicvUser.getRecordStatus().equals(UserStatus.DELETED.getRecordStatusCode())) {
						if (dicvUser.getDicvGroup() != null)
							driverAnalysis.setGroupName(dicvUser.getDicvGroup().getGroupName());
						driverAnalysisList.add(driverAnalysis);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(" Exception in Driver Analysis ", e);
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (callableStatement != null) {
					callableStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				LOGGER.error("Exception in Driver Analysis ", e);
				return null;
			}
		}
		if (!driverAnalysisList.isEmpty()) {
			driverAnalysisListDto = new DriverAnalysisListDto();
			driverAnalysisListDto.setDriverAnalysisList(driverAnalysisList);
			return driverAnalysisListDto;
		}
		return null;

	}

	private DriverAnalysisDto driverAnalysis(ResultSet rs, DriverAnalysisDto driverAnalysis) throws SQLException {

		try {

			driverAnalysis = new DriverAnalysisDto();
			driverAnalysis.setDriverName(rs.getString("DRIVER_NAME"));
			driverAnalysis.setDriverId(rs.getInt("DRIVER_ID"));
			BigDecimal speedAdherence = rs.getBigDecimal("SPEED_ADHERENCE");

			if (speedAdherence != null) {
				driverAnalysis.setSpeedAdherencePercent(speedAdherence.toString());
			}

			BigDecimal ecomomyBand = rs.getBigDecimal("ECONOMIC_BAND");
			if (ecomomyBand != null) {
				driverAnalysis.setEconomyBandPercent(ecomomyBand.toString());
			} else {
				driverAnalysis.setEconomyBandPercent("NA");
			}

			BigDecimal economyDriving = rs.getBigDecimal("ECONOMIC_DRIVING");
			if (economyDriving != null) {
				driverAnalysis.setEconomyDriving(economyDriving.toString());
			} else {
				driverAnalysis.setEconomyDriving("NA");
			}

			BigDecimal engineNonIdleTime = rs.getBigDecimal("ENGINE_NON_IDLE_TIME_PERCENT");
			if (engineNonIdleTime != null) {
				driverAnalysis.setEngineNonIdleTimePercent(engineNonIdleTime.toString());
			} else {
				driverAnalysis.setEngineNonIdleTimePercent("NA");
			}
			BigDecimal engineIdleTime = rs.getBigDecimal("ENGINE_IDLE_TIME_PERCENT");
			if (engineNonIdleTime != null) {
				driverAnalysis.setEngineIdleTimePercent(engineIdleTime.toString());
			} else {
				driverAnalysis.setEngineIdleTimePercent("NA");
			}
			BigDecimal harshDriving = rs.getBigDecimal("HARSH_DRIVING");
			if (harshDriving != null)
				driverAnalysis.setHarshDrivingPercent(harshDriving.toString());

			BigDecimal fuelEfficiency = rs.getBigDecimal("FUEL_EFFICIENCY");
			if (fuelEfficiency != null)
				driverAnalysis.setFuelEfficiencyPercent(fuelEfficiency.toString());
			BigDecimal totalDriveTime = rs.getBigDecimal("TOTAL_DRIVE_TIME");
			if (totalDriveTime != null)
				driverAnalysis.setDrivingDuration(
						DicvUtil.convertFromMillisToHoursMinsSecs(totalDriveTime.longValue() * 1000));
			driverAnalysis.setDriverScore(rs.getDouble("DRIVER_SCORE"));
			if (driverAnalysis.getDriverScore() != null)
				driverAnalysis.setDriverScore(Math.round(driverAnalysis.getDriverScore() * 100d) / 100d);
			driverAnalysis.setStarCount(rs.getDouble("STAR_COUNT"));
			BigDecimal speedBand0To20 = rs.getBigDecimal("SPEEDBAND_0_TO_20KM");

			BigDecimal speedBand21To40 = rs.getBigDecimal("SPEEDBAND_21_TO_40KM");

			BigDecimal speedBand41To60 = rs.getBigDecimal("SPEEDBAND_41_TO_60KM");

			BigDecimal speedBand61To80 = rs.getBigDecimal("SPEEDBAND_TO_80KM");

			BigDecimal speedBandOver80 = rs.getBigDecimal("SPEEDBAND_OVER_80KM");

			if (speedBand0To20 != null)

				driverAnalysis.setSpeedBand0To20(
						DicvUtil.convertFromMillisToHoursMinsSecs(speedBand0To20.longValue() * 1000));

			if (speedBand21To40 != null)

				driverAnalysis.setSpeedBand21To40(
						DicvUtil.convertFromMillisToHoursMinsSecs(speedBand21To40.longValue() * 1000));

			if (speedBand41To60 != null)

				driverAnalysis.setSpeedBand41To60(
						DicvUtil.convertFromMillisToHoursMinsSecs(speedBand41To60.longValue() * 1000));

			if (speedBand61To80 != null)

				driverAnalysis.setSpeedBand61To80(
						DicvUtil.convertFromMillisToHoursMinsSecs(speedBand61To80.longValue() * 1000));

			if (speedBandOver80 != null)

				driverAnalysis.setSpeedBandOver80(
						DicvUtil.convertFromMillisToHoursMinsSecs(speedBandOver80.longValue() * 1000));

			driverAnalysis.setDistanceTravelled(rs.getDouble("TRIP_DISTANCE"));
			if (driverAnalysis.getDistanceTravelled() != null)
				driverAnalysis.setDistanceTravelled(Math.round(driverAnalysis.getDistanceTravelled() * 100d) / 100d);

			driverAnalysis.setEconomyBandDistance(rs.getDouble("ECONOMY_BAND_DISTANCE"));

			driverAnalysis.setAverageSpeed(rs.getDouble("AVERAGE_VEHICLE_SPEED"));

			if (rs.getBigDecimal("ECONOMIC_DRIVING") != null && rs.getBigDecimal("SPEED_ADHERENCE") != null
					&& rs.getBigDecimal("ENGINE_IDLE_TIME_PERCENT") != null) {
				BigDecimal total = new BigDecimal(0);
				total = total.add(rs.getBigDecimal("ECONOMIC_DRIVING"));
				total = total.add(rs.getBigDecimal("ENGINE_IDLE_TIME_PERCENT"));
				total = total.add(rs.getBigDecimal("SPEED_ADHERENCE"));
				total = total.divide(new BigDecimal("3"), 2, BigDecimal.ROUND_HALF_UP);
				driverAnalysis.setDriverScore(total.doubleValue());
			}

		} catch (SQLException e) {
			LOGGER.error("Error  ", e);
			throw new SQLException();
		}
		return driverAnalysis;

	}

	@Override
	public DriverAnalysisListDto getDriverAnalysisFromTrip(Date startDate, Date endDate, int userId, Integer groupId) {
		List<DriverAnalysisDto> driverAnalysisList = null;

		DriverAnalysisListDto driverAnalysisListDto = null;

		DriverAnalysisDto driverAnalysis = null;

		CallableStatement callableStatement = null;

		ResultSet rs = null;

		Connection conn = null;
		try {

			SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-YY");

			String fromDate = dateformat.format(startDate);

			String toDate = dateformat.format(endDate);

			Session session = entityManager.unwrap(Session.class);
			SessionImplementor sessionImplementor = (SessionImplementor) session;
			conn = sessionImplementor.getJdbcConnectionAccess().obtainConnection();

			callableStatement = conn.prepareCall("{call DRIVER_ANALYSIS(?,?,?,?,?,?,?,?)}");

			callableStatement.setString(1, null);

			callableStatement.setInt(2, userId);

			callableStatement.setString(3, fromDate);
			callableStatement.setString(4, toDate);
			if (null != groupId) {

				callableStatement.setInt(5, groupId);
			} else {

				callableStatement.setString(5, null);
			}

			callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
			callableStatement.registerOutParameter(7, OracleTypes.NUMBER);
			callableStatement.registerOutParameter(8, OracleTypes.VARCHAR);

			callableStatement.execute();

			String str = callableStatement.getString(8);

			if (str.equals("Success")) {
				rs = (ResultSet) callableStatement.getObject(6);
				driverAnalysisList = new ArrayList<DriverAnalysisDto>();

			} else {
				throw new DataNotFoundException(str);
			}
			while (rs.next()) {
				if (rs.getBigDecimal("TRIP_DISTANCE") != null && rs.getBigDecimal("TRIP_DISTANCE").longValue() >= 10) {
					driverAnalysis = driverAnalysis(rs, driverAnalysis);
					DicvUser dicvUser = userRepo.findOne(driverAnalysis.getDriverId());
					if (dicvUser != null
							&& !dicvUser.getRecordStatus().equals(UserStatus.DELETED.getRecordStatusCode())) {
						if (dicvUser.getDicvGroup() != null)
							driverAnalysis.setGroupName(dicvUser.getDicvGroup().getGroupName());
						driverAnalysisList.add(driverAnalysis);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(" Exception in Driver Analysis ", e);
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (callableStatement != null) {
					callableStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				LOGGER.error("Exception in Driver Analysis ", e);
				return null;
			}
		}
		if (!driverAnalysisList.isEmpty()) {
			driverAnalysisListDto = new DriverAnalysisListDto();
			driverAnalysisListDto.setDriverAnalysisList(driverAnalysisList);
			return driverAnalysisListDto;
		}
		return null;

	}

	public DriverAnalysisLineChartListDto getDriverAnalysisForPerformanceGraph(Date startDate, Date endDate, int userId,
			int driverId) {

		DriverAnalysisLineChartListDto driverAnalysisLineChartList = new DriverAnalysisLineChartListDto();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<DriverAnalysisLineChartDto> criteriaQuery = criteriaBuilder
				.createQuery(DriverAnalysisLineChartDto.class);

		Root<DriverAnalysisList> driverAnalysisList = criteriaQuery.from(DriverAnalysisList.class);

		Join<DriverAnalysisList, DicvUser> dicvUser_driverAnalysis = driverAnalysisList.join("dicvUserDriverAnalysis");

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(criteriaBuilder.greaterThanOrEqualTo(driverAnalysisList.<Date>get("reportDate"), startDate));
		predicates.add(criteriaBuilder.lessThanOrEqualTo(driverAnalysisList.<Date>get("reportDate"), endDate));
		predicates.add(criteriaBuilder.isNotNull(driverAnalysisList.get("engineRunTime")));
		predicates.add(criteriaBuilder.equal(dicvUser_driverAnalysis.get("userId"), driverId));

		Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);

		criteriaQuery.where(criteriaBuilder.and(predicatesArray));

		criteriaQuery.select(
				criteriaBuilder.construct(DriverAnalysisLineChartDto.class, dicvUser_driverAnalysis.get("userId"),
						driverAnalysisList.get("tripDistance"), driverAnalysisList.get("speedAdherence"),
						driverAnalysisList.get("economyDriving"), driverAnalysisList.get("engineNonIdleTimePercent"),
						driverAnalysisList.get("engineIdleTimePercent"), driverAnalysisList.get("harshDriving"),
						driverAnalysisList.get("fuelEfficiency"), driverAnalysisList.get("maximumSpeed"),
						driverAnalysisList.get("maxSpeedTime"), driverAnalysisList.get("reportDate"),
						driverAnalysisList.get("maxSpeedLat"), driverAnalysisList.get("maxSpeedLong"),
						driverAnalysisList.get("totalDriveTime"), driverAnalysisList.get("economyBandDistance")));

		criteriaQuery.orderBy(criteriaBuilder.asc(driverAnalysisList.get("reportDate")));

		TypedQuery<DriverAnalysisLineChartDto> typedQuery = entityManager.createQuery(criteriaQuery);

		List<DriverAnalysisLineChartDto> driverAnalysisResponse = typedQuery.getResultList();

		driverAnalysisLineChartList.setDriverAnalysisLineChart(driverAnalysisResponse);

		return driverAnalysisLineChartList;
	}

	public DriverTripPlaybackListDto getDriverSpeedingAndStopLocations(Date startDate, Date endDate, Integer ownerId,
			Integer driverId) {

		int noOfStops = 0;
		DriverTripPlaybackDto driverTripPlaybackDto = null;
		DriverTripPlaybackListDto driverTripPlaybackListDto = null;
		List<DriverTripPlaybackDto> driverSpeedingLocations = new ArrayList<DriverTripPlaybackDto>();

		List<DriverTripPlaybackDto> driverStopLocations = new ArrayList<DriverTripPlaybackDto>();

		Query tripPlaybackQuery = entityManager.createNativeQuery(DicvNativeQueries.DRIVER_SPEEDING_STOPLOCATIONS);
		tripPlaybackQuery.setParameter("DRIVER_ID", driverId);
		tripPlaybackQuery.setParameter("OWNER_ID", ownerId);
		tripPlaybackQuery.setParameter("FROM_DATE", startDate);
		tripPlaybackQuery.setParameter("TO_DATE", endDate);
		try {
			List<Object[]> objList = tripPlaybackQuery.getResultList();
			if (objList.isEmpty()) {
				return null;
			}
			for (Object[] objects : objList) {
				Double gpsLatitude = (Double) objects[0];
				Double gpsLongitude = (Double) objects[1];
				Double gpsSpkm = (Double) objects[2];
				Double stopDuration = (Double) objects[3];
				Timestamp gpsTime = (Timestamp) objects[4];
				driverTripPlaybackDto = new DriverTripPlaybackDto();
				driverTripPlaybackDto.setGpsLatitude(gpsLatitude);
				driverTripPlaybackDto.setGpsLongitude(gpsLongitude);
				driverTripPlaybackDto.setGpsTime(gpsTime);
				driverTripPlaybackDto.setGpsSpkm(gpsSpkm.intValue());
				// If speed is 0 then do not show DriverTripPlaybackDto object
				// in driverSpeedingLocations list object
				if (gpsSpkm.intValue() == 0) {
					driverTripPlaybackDto.setStopDuration(
							DicvUtil.convertFromMillisToHoursMinsSecs(stopDuration.longValue() * 1000));
					driverStopLocations.add(driverTripPlaybackDto);
					noOfStops += 1;
				} else {
					driverSpeedingLocations.add(driverTripPlaybackDto);
				}
			}
			driverTripPlaybackListDto = new DriverTripPlaybackListDto();
			driverTripPlaybackListDto.setDriverSpeedingLocations(driverSpeedingLocations);
			driverTripPlaybackListDto.setDriverStopLocations(driverStopLocations);
			driverTripPlaybackListDto.setNoOfStops(noOfStops);
			return driverTripPlaybackListDto;
		} catch (Exception e) {
			return null;
		}

	}

	public SpeedCountDto getSpeedingCountForDriver(Integer driverId, Date startDate, Date endDate) {

		SpeedCountDto speedCount = new SpeedCountDto();
		try {
			Query query = entityManager.createQuery(
					"select SUM(t.speedingCount) from DriverAnalysisList t where t.dicvUserDriverAnalysis.userId=:userId"
							+ " and t.reportDate>=:startTime and t.reportDate<=:endDate");
			query.setParameter("startTime", startDate);
			query.setParameter("endDate", endDate);
			query.setParameter("userId", driverId);
			Long speed = (Long) query.getSingleResult();
			if (speed == null)
				speed = 0l;
			speedCount.setSpeedingCount(speed.intValue());
		} catch (Exception e) {
			LOGGER.error(e);
			return null;
		}
		return speedCount;
	}

}
