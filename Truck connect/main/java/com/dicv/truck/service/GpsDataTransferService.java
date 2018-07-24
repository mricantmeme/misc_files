package com.dicv.truck.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import com.dicv.truck.dto.GPSData;
import com.dicv.truck.dto.VehicleRequestDto;
import com.dicv.truck.model.Vehicle;
import com.dicv.truck.repo.VehicleRepo;

@Component
public class GpsDataTransferService {

	@Value("${authenticator_header_name}")
	private String authName;

	@Value("${send_fedex_data}")
	private String sendFedexData;

	@Value("${authentication_key}")
	private String authKey;

	@Value("${resource_url}")
	private String resourceUrl;

	@Autowired
	private VehicleRepo vehicleRepo;

	@Autowired
	private GoogleAPIService googleAPIService;

	private static final Logger LOGGER = LoggerFactory.getLogger(GpsDataTransferService.class);

	@Scheduled(fixedDelay = 1000)
	public void getFedExVehicleList() {
		if (sendFedexData.equals("Yes")) {
			LOGGER.info("Fedex API Started");
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			List<Vehicle> fedExVehicle = vehicleRepo.getVehicleFedexList();
			for (Vehicle vehicle : fedExVehicle) {
				try {
					getVehicleRequestDto(vehicle);
				} catch (Exception ex) {
					LOGGER.error("Error in Fedex Vehicle " + vehicle.getVehicleId(), ex);
				}
			}
			stopWatch.stop();
			LOGGER.info("Fedex API Completed :: Timetaken" + stopWatch.getTotalTimeSeconds() + " Sec ");
		}
	}

	public void getVehicleRequestDto(Vehicle vehicle) {
		GPSData gpsData = new GPSData();
		gpsData.setGPSDateTime(
				new SimpleDateFormat("dd MMM yyyy HH:mm").format(new Date(vehicle.getVehicleUpdateTime().getTime())));
		gpsData.setLatitude(vehicle.getCurrentLat());
		gpsData.setLongitude(vehicle.getCurrentLong());
		gpsData.setLocation(googleAPIService.getNearestLocation(gpsData.getLatitude(), gpsData.getLongitude()));
		gpsData.setSpeed(vehicle.getCurrentVehicleSpeed());
		VehicleRequestDto vehicleRequestDto = new VehicleRequestDto();
		// vehicleRequestDto.setCustomerID();//must be given by fedEx
		vehicleRequestDto.setDateTime(new SimpleDateFormat("dd MMM yyyy hh:mm a").format(new Date()));
		vehicleRequestDto.setGPSData(gpsData);
		vehicleRequestDto.setVehicleID(vehicle.getRegistrationId());
		apiDataPush(vehicleRequestDto);
	}

	public void apiDataPush(VehicleRequestDto vehicleRequest) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.set(authName, authKey);
		HttpEntity<VehicleRequestDto> headerEntity = new HttpEntity<>(vehicleRequest, header);

		LOGGER.info("Fedex API Started");
		ResponseEntity<String> response = restTemplate.exchange(resourceUrl, HttpMethod.POST, headerEntity,
				String.class);
		if (response.getStatusCodeValue() == HttpServletResponse.SC_OK) {
			// update in db
			// success

			// failure
		} else {

		}
	}

}
