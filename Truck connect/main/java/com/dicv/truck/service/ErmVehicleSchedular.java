package com.dicv.truck.service;

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

import com.dicv.truck.model.ErmVehicle;
import com.dicv.truck.model.GpsImei;
import com.dicv.truck.model.GpsVehicleParameter;
import com.dicv.truck.repo.ErmVehicleRepo;
import com.dicv.truck.repo.GpsImeiRepo;
import com.dicv.truck.repo.GpsRepo;
import com.dicv.truck.utility.DicvUtil;

@Component
public class ErmVehicleSchedular {

	@Autowired
	private GpsRepo gpsRepo;

	@Autowired
	private ErmVehicleRepo ermRepo;

	@Autowired
	private GpsImeiRepo gpsImeiRepo;

	@Value("${erm_imei_time}")
	private String ermImeiTime;

	private static final Logger LOGGER = LoggerFactory.getLogger(ErmVehicleSchedular.class);

	@Scheduled(fixedDelay = 60000)
	private void checkGpsData() {
		LOGGER.info("Erm Schedular Started  " + ermImeiTime);
		if (ermImeiTime != null && ermImeiTime.equals("Yes")) {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			List<GpsImei> ermList = gpsImeiRepo.getErmVehicleByStatus();
			if (ermList != null && ermList.size() > 0) {
				for (GpsImei gpsImei : ermList) {
					try {
						updateLastGpsTransmission(gpsImei);
					} catch (Exception ex) {
						LOGGER.error("Error in Erm Schedular ", ex);
					}
				}

			}
			stopWatch.stop();
			LOGGER.info("Erm Schedular Completed In  " + stopWatch.getTotalTimeSeconds() + " Sec ");
		}
	}

	@Transactional
	private void updateLastGpsTransmission(GpsImei gpsImei) {
		List<GpsVehicleParameter> gpsData = gpsRepo.getLastGpsParameter(gpsImei.getGpsImei(), new PageRequest(0, 1));
		if (gpsData != null && !gpsData.isEmpty()) {
			if (gpsData.get(0).getGpsTime() != null) {
				ErmVehicle erm = ermRepo.findOne(gpsImei.getErmVehicle().getErmVehicleId());
				erm.setLastReceivedTime(new Timestamp(gpsData.get(0).getGpsTime().getTime()));
				erm.setUpdatedTime(DicvUtil.getTimestamp());
				ermRepo.save(erm);
			}
		}
	}

}
