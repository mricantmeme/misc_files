/*package com.dicv.truck.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetAddress {

	private static Logger LOGGER = Logger.getLogger(GetAddress.class);

	public static String getCityAndAddress(Double latitude, Double longitude, String mapUrlAPI) {
		String address = null;
		String urlstr = null;
		if (latitude == null || longitude == null || mapUrlAPI == null)
			return null;
		StringBuilder output = new StringBuilder();
		try {

			urlstr = mapUrlAPI + latitude + "," + longitude;
			URL url = new URL(urlstr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String line;
				while ((line = br.readLine()) != null) {
					output.append(line + '\n');
				}
				JSONParser parser = new JSONParser();
				org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(output.toString());
				Object results = json.get("results");
				String resultToString = results.toString();
				JSONArray jsonarr;
				jsonarr = new JSONArray(resultToString);
				org.codehaus.jettison.json.JSONObject jsonobj = jsonarr.getJSONObject(0);
				String address_components = jsonobj.getString("address_components");
				String cityName = null;
				String countryName = null;
				String state = null;
				String route = null;
				JSONArray addressArray = new JSONArray(address_components);
				for (int i = 0; i < addressArray.length(); i++) {
					JSONObject address_component = (JSONObject) addressArray.get(i);
					JSONArray type = (JSONArray) address_component.get("types");
					for (int j = 0; j < type.length(); j++) {
						if (type.get(j).equals("route")) {
							route = ((String) address_component.get("long_name"));
						}
						if (type.get(j).equals("locality")) {
							cityName = (String) address_component.get("long_name");
						}
						if (type.get(j).equals("country")) {
							countryName = (String) address_component.get("long_name");
						}
						if (type.get(j).equals("administrative_area_level_1")) {
							state = (String) address_component.get("long_name");
						}

					}
				}
				if (cityName != null && state != null && countryName != null) {
					address = cityName + "," + state + "," + countryName;
				}
				if (route != null && cityName != null && state != null && countryName != null) {
					address = route + "," + cityName + "," + state + "," + countryName;
				}
				if (cityName == null && state != null && countryName != null) {
					address = state + "," + countryName;
				}
				if (cityName == null && state != null && countryName == null) {
					address = countryName;
				}
				if (cityName != null && state == null && countryName != null) {
					address = cityName + "," + countryName;
				}
				LOGGER.debug("Lat&Long" + latitude + "," + longitude + " ADDRESS ::" + address);
			}
			conn.disconnect();
		} catch (java.net.SocketTimeoutException e) {
			LOGGER.info("URL  " + urlstr + " ADDRESS ::" + address);
			LOGGER.debug("Socket Exceptiion in Map Location", e);
		} catch (MalformedURLException e) {
			LOGGER.info("URL  " + urlstr + " ADDRESS ::" + address);
			LOGGER.debug("URL Malformed Exceptiion in Map Location ", e);
		} catch (IOException e) {
			LOGGER.info("URL  " + urlstr + " ADDRESS ::" + address);
			LOGGER.error("IO Exceptiion in Map Location", e);
		} catch (Exception e) {
			LOGGER.info("URL  " + urlstr + " ADDRESS ::" + address);
			LOGGER.debug("Exceptiion in  Map Location", e);
		}
		return address;
	}

}
*/