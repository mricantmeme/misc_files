package com.dicv.truck.utility;

import java.util.HashMap;
import java.util.Map;

public class NightDrivingTiming {

	public static String getNightSpeedQuery(Integer startTime, Integer endTime) {

		Map<Integer, String> timeMap = new HashMap<Integer, String>();
		timeMap.put(1, "v.timeIn_1_2");
		timeMap.put(2, "v.timeIn_2_3");
		timeMap.put(3, "v.timeIn_3_4");
		timeMap.put(4, "v.timeIn_4_5");
		timeMap.put(5, "v.timeIn_5_6");
		timeMap.put(9, "v.timeIn_9_10");
		timeMap.put(10, "v.timeIn_10_11");
		timeMap.put(11, "v.timeIn_11_12");
		timeMap.put(0, "v.timeIn_12_1");
		Map<Integer, String> distMap = new HashMap<Integer, String>();
		distMap.put(1, "v.distIn_1_2");
		distMap.put(2, "v.distIn_2_3");
		distMap.put(3, "v.distIn_3_4");
		distMap.put(4, "v.distIn_4_5");
		distMap.put(5, "v.distIn_5_6");
		distMap.put(9, "v.distIn_9_10");
		distMap.put(10, "v.distIn_10_11");
		distMap.put(11, "v.distIn_11_12");
		distMap.put(0, "v.distIn_12_1");
		String timeStr = "SUM(v.timeIn_8_9";
		String distStr = "SUM(v.distIn_8_9";
		endTime = endTime - 1;

		// Start Time
		if (startTime >= 9) {
			if (endTime > 5) {
				for (int i = startTime; i <= endTime; i++) {
					timeStr = timeStr + "+" + timeMap.get(i);
					distStr = distStr + "+" + distMap.get(i);
				}
			}

			if (endTime <= 5) {
				for (int i = startTime; i <= 11; i++) {
					timeStr = timeStr + "+" + timeMap.get(i);
					distStr = distStr + "+" + distMap.get(i);
				}

				for (int i = 0; i <= endTime; i++) {
					timeStr = timeStr + "+" + timeMap.get(i);
					distStr = distStr + "+" + distMap.get(i);
				}
			}
		}
		// End Time
		if (startTime >= 0 && startTime < 9) {
			if (endTime > 5) {
				for (int i = startTime; i <= endTime; i++) {
					timeStr = timeStr + "+" + timeMap.get(i);
					distStr = distStr + "+" + distMap.get(i);
				}
			}

		}
		timeStr = timeStr + ")";
		distStr = distStr + ")";
		return timeStr + "," + distStr;

	}

}
