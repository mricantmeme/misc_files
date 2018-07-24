package com.dicv.truck.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dicv.truck.dto.NightDrivingReportDto;
import com.dicv.truck.dto.OverSpeedReportDto;
import com.dicv.truck.dto.SpeedReportDto;

@Repository
public class ReportDaoImpl implements ReportDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportDaoImpl.class);

	@PersistenceContext
	public EntityManager entityManager;

	@Override
	public List<SpeedReportDto> getSpeedReport(List<Integer> vehicle, Date fromDate, Date toDate, Integer speedLimit) {
		String queryStr = "Select new com.dicv.truck.dto.SpeedReportDto(v.vehicleId,";
		try {
			switch (speedLimit) {
			case 20:
				queryStr = queryStr
						+ "SUM(v.timeIn21To30km+v.timeIn31To40km+v.timeIn41To50km+v.timeIn51To60km+v.timeIn61To70km+v.timeIn71To80km+v.timeIn81To90km+"
						+ "v.timeIn91To100km+v.timeIn101To110km+v.timeInOver110km),SUM(v.distIn21To30km+v.distIn31To40km+"
						+ "v.distIn41To50km+v.distIn51To60km+v.distIn61To70km+v.disteIn71To80km+v.distIn81To90km+"
						+ "v.distIn91To100km+v.distIn101To110km+v.distInOver110km)";
				break;
			case 30:
				queryStr = queryStr
						+ "SUM(v.timeIn31To40km+v.timeIn41To50km+v.timeIn51To60km+v.timeIn61To70km+v.timeIn71To80km+v.timeIn81To90km+"
						+ "v.timeIn91To100km+v.timeIn101To110km+v.timeInOver110km),SUM(v.distIn31To40km+"
						+ "v.distIn41To50km+v.distIn51To60km+v.distIn61To70km+v.disteIn71To80km+v.distIn81To90km+"
						+ "v.distIn91To100km+v.distIn101To110km+v.distInOver110km)";
				break;
			case 40:
				queryStr = queryStr
						+ "SUM(v.timeIn41To50km+v.timeIn51To60km+v.timeIn61To70km+v.timeIn71To80km+v.timeIn81To90km+"
						+ "v.timeIn91To100km+v.timeIn101To110km+v.timeInOver110km),SUM("
						+ "v.distIn41To50km+v.distIn51To60km+v.distIn61To70km+v.disteIn71To80km+v.distIn81To90km+"
						+ "v.distIn91To100km+v.distIn101To110km+v.distInOver110km)";
				break;
			case 50:
				queryStr = queryStr + "SUM(v.timeIn51To60km+v.timeIn61To70km+v.timeIn71To80km+v.timeIn81To90km+"
						+ "v.timeIn91To100km+v.timeIn101To110km+v.timeInOver110km),SUM(v.distIn51To60km+v.distIn61To70km+v.disteIn71To80km+v.distIn81To90km+"
						+ "v.distIn91To100km+v.distIn101To110km+v.distInOver110km)";
				break;
			case 60:
				queryStr = queryStr + "SUM(v.timeIn61To70km+v.timeIn71To80km+v.timeIn81To90km+"
						+ "v.timeIn91To100km+v.timeIn101To110km+v.timeInOver110km),SUM(v.distIn61To70km+v.disteIn71To80km+v.distIn81To90km+"
						+ "v.distIn91To100km+v.distIn101To110km+v.distInOver110km)";
				break;
			case 70:
				queryStr = queryStr + "SUM(v.timeIn71To80km+v.timeIn81To90km+"
						+ "v.timeIn91To100km+v.timeIn101To110km+v.timeInOver110km),SUM(v.disteIn71To80km+v.distIn81To90km+"
						+ "v.distIn91To100km+v.distIn101To110km+v.distInOver110km)";
				break;
			case 80:
				queryStr = queryStr + "SUM(v.timeIn81To90km+"
						+ "v.timeIn91To100km+v.timeIn101To110km+v.timeInOver110km),SUM(v.distIn81To90km+"
						+ "v.distIn91To100km+v.distIn101To110km+v.distInOver110km)";
				break;
			case 90:
				queryStr = queryStr
						+ "SUM(v.timeIn91To100km+v.timeIn101To110km+v.timeInOver110km),SUM(v.distIn91To100km+v.distIn101To110km+v.distInOver110km)";
				break;
			case 100:
				queryStr = queryStr
						+ "SUM(v.timeIn101To110km+v.timeInOver110km),SUM(v.distIn101To110km+v.distInOver110km)";
				break;
			}

			queryStr = queryStr + ",SUM(v.totalDistance)) from SpeedReport v where v.vehicleId IN :vehicleIds "
					+ "and v.reportDate >= :fromDate and v.reportDate <= :toDate group by v.vehicleId";
			Query query = entityManager.createQuery(queryStr);
			query.setParameter("vehicleIds", vehicle);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			List<SpeedReportDto> summaryList = new ArrayList<SpeedReportDto>();
			summaryList = query.getResultList();
			if (summaryList != null && summaryList.size() > 0) {
				return summaryList;
			}
			LOGGER.info("Vehicle SpeedReport  is Empty");
		} catch (PersistenceException ex) {
			LOGGER.info("PersistenceException in getting SpeedReport  " + queryStr + " " + ex);
			ex.printStackTrace();
		} catch (Exception ex) {
			LOGGER.info("Exception in getting SpeedReport  " + queryStr + " " + ex);
		}
		return null;

	}

	@Override
	public List<OverSpeedReportDto> getSpeedReportMaxLocation(List<Integer> vehicle, Date fromDate, Date toDate,
			Integer speedLimit) {
		try {
			Query query = entityManager.createQuery(
					"select new com.dicv.truck.dto.OverSpeedReportDto(v.vehicleId,v.maxSpeed,v.maxSpeedLat,v.maxSpeedLong,v.location) from SpeedReport v where "
							+ " v.vehicleId IN :vehicleIds and v.reportDate >= :fromDate and v.reportDate <= :toDate");
			query.setParameter("vehicleIds", vehicle);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			List<OverSpeedReportDto> summaryList = new ArrayList<OverSpeedReportDto>();
			summaryList = query.getResultList();
			if (summaryList != null && summaryList.size() > 0) {
				return summaryList;
			}
			LOGGER.info("Vehicle MAX Speed Vehicle List  is Empty");
		} catch (PersistenceException ex) {
			LOGGER.info("PersistenceException in getting MAX SpeedReport  " + ex);
		} catch (Exception ex) {
			LOGGER.info("Exception in getting SpeedReport MAX SpeedReport  " + ex);
		}
		return null;

	}

	@Override
	public List<NightDrivingReportDto> getNightDrivingReport(Integer userId, List<Integer> vehicleIds, Date fromDate,
			Date toDate, String speedQuery) {

		String queryStr = "Select new com.dicv.truck.dto.NightDrivingReportDto(v.vehicleId," + speedQuery;
		try {
			queryStr = queryStr + ") from NightDriving v where v.vehicleId IN :vehicleIds "
					+ "and v.reportDate >= :fromDate and v.reportDate <= :toDate group by v.vehicleId";
			Query query = entityManager.createQuery(queryStr);
			query.setParameter("vehicleIds", vehicleIds);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			List<NightDrivingReportDto> summaryList = query.getResultList();
			if (summaryList != null && summaryList.size() > 0) {
				LOGGER.info("Vehicle NightDriving List  size :: " + summaryList.size());
				return summaryList;
			}
			LOGGER.info("Vehicle SpeedReport  is Empty");
		} catch (PersistenceException ex) {
			LOGGER.info("PersistenceException in getting NightDriving  " + queryStr + " " + ex);
			ex.printStackTrace();
		} catch (Exception ex) {
			LOGGER.info("Exception in getting NightDriving  " + queryStr + " " + ex);
		}
		return null;

	}

}
