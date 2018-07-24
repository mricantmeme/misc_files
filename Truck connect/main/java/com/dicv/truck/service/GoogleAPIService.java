package com.dicv.truck.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dicv.truck.dto.Address;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

@Component
public class GoogleAPIService {

	@Value("${google_api_key}")
	private String googleApiKey;

	private static Logger LOGGER = Logger.getLogger(GoogleAPIService.class);

	public Address getAddress(Double latitude, Double longitude) {
		Address address = new Address();
		try {
			address.setResponse(false);
			if (latitude == null || longitude == null)
				return address;
			GeoApiContext context = new GeoApiContext.Builder().apiKey(googleApiKey)
					.build();
			LatLng latlng = new LatLng(latitude, longitude);
			GeocodingResult[] results = GeocodingApi.newRequest(context).latlng(latlng).await();
			if (results != null) {
				if (results[0].formattedAddress != null) {
					address.setAddress(results[0].formattedAddress);
					for (AddressComponent add : results[0].addressComponents) {
						if (add.types[0].equals(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_2)) {
							address.setCity(add.longName + " ,");
							address.setResponse(true);
						}
						if (add.types[0].equals(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1)) {
							address.setState(add.longName);
							address.setResponse(true);
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error Calling Google API ", e);
			return address;
		}
		return address;
	}

	public String getNearestLocation(Double lat, Double longitude) {
		String address = null;
		try {
			GeoApiContext context = new GeoApiContext.Builder().apiKey(googleApiKey).build();
			LatLng latlng = new LatLng(lat, longitude);
			GeocodingResult[] results = GeocodingApi.newRequest(context).latlng(latlng).await();

			if (results != null && results.length >= 1) {
				if (results[0].formattedAddress != null) {
					String town = null, city = null, state = null;
					for (AddressComponent add : results[0].addressComponents) {
						if (add.types[0].equals(AddressComponentType.LOCALITY)) {
							town = add.longName;
						}
						if (add.types[0].equals(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_2)) {
							city = add.longName;
						}
						if (add.types[0].equals(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1)) {
							state = add.longName;
						}
					}
					String destination = null;
					if (town != null) {
						destination = town;
						if (town.equals(city))
							address = town + "," + state;
						else
							address = town + "," + city + "," + state;
					} else {
						destination = city;
						address = city + "," + state;
					}
					if (destination != null) {
						DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(context).origins(latlng)
								.destinations(destination).await();
						if (distanceMatrix != null) {
							if (distanceMatrix.rows != null) {
								Double km = (distanceMatrix.rows[0].elements[0].distance.inMeters * 1.6) / 1000d;
								if (km >= 1) {
									address = (Math.round((km) * 100D) / 100D) + " Kms from " + address;
								}
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Error in Location API  " + ex.getMessage());
			return address;
		}
		return address;
	}
}
