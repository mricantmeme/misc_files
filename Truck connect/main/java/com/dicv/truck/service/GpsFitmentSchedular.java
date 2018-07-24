/*package com.dicv.truck.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import com.dicv.truck.model.GpsImei;
import com.dicv.truck.model.GpsVehicleParameter;
import com.dicv.truck.repo.GpsImeiRepo;
import com.dicv.truck.repo.GpsRepo;
import com.dicv.truck.utility.DicvUtil;

@Component
public class GpsFitmentSchedular {

	@Autowired
	private GpsRepo gpsRepo;

	@Autowired
	private GpsImeiRepo gpsImeiRepo;

	@Value("${erm_imei_time}")
	private String ermImeiTime;

	private static final Logger LOGGER = LoggerFactory.getLogger(GpsFitmentSchedular.class);

	@Scheduled(fixedDelay = 120000)
	private void checkGpsData() {
		LOGGER.info("GPS FITMENT Schedular Started  " + ermImeiTime);
		if (ermImeiTime != null && ermImeiTime.equals("Yes")) {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			List<GpsImei> ermList = gpsImeiRepo.gpsGpsImeiByTransmittedTime();
			if (ermList != null && ermList.size() > 0) {
				for (GpsImei gpsImei : ermList) {
					if (gpsImei.getErmVehicle() != null
							|| (gpsImei.getVehicle() != null && gpsImei.getVehicle().getDealerUser() != null)
									&& gpsImei.getGpsTransmittedTime() != null) {
						updateGpsFitmentDate(gpsImei);
					}
				}
				stopWatch.stop();
			}
		}
	}

	@Transactional
	private void updateGpsFitmentDate(GpsImei gpsImei) {
		List<GpsVehicleParameter> gpsData = gpsRepo.getGpsParameter(gpsImei.getGpsImei(), new PageRequest(0, 1));
		if (gpsData != null && !gpsData.isEmpty()) {
			if (gpsData.get(0).getGpsTime() != null) {
				gpsImeiRepo.updateGpsTime(new Timestamp(gpsData.get(0).getGpsTime().getTime()), DicvUtil.getTimestamp(),
						gpsImei.getGpsImeiId());
			}
		}
	}

}
*/