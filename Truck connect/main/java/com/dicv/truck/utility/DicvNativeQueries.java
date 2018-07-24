package com.dicv.truck.utility;

public class DicvNativeQueries {

	public static final String OWNER_ALLDRIVERS_ANALYSISFROMTRIP = "SELECT DRIVER_NAME,DRIVER_ID,GROUP_NAME,SPEED_VIOLATION,SPEED_ADHERENCE,ECONOMIC_BAND,NON_IDLE_TIME,IDLE_TIME,HARSH_DRIVING,FUEL_EFFICIENCY,TOTAL_DRIVE_TIME,SPEED_VIOLATION_TIME,  NVL2(DECODE(ECONOMIC_BAND,0,NULL,ECONOMIC_BAND),ROUND((SPEED_ADHERENCE + ECONOMIC_BAND +NON_IDLE_TIME + HARSH_DRIVING + FUEL_EFFICIENCY)/5,2),ROUND((SPEED_ADHERENCE + NON_IDLE_TIME + HARSH_DRIVING + FUEL_EFFICIENCY)/4,2)) AS DRIVER_SCORE,  NVL2(DECODE(ECONOMIC_BAND,0,NULL,ECONOMIC_BAND),ROUND(((SPEED_ADHERENCE + ECONOMIC_BAND +NON_IDLE_TIME + HARSH_DRIVING + FUEL_EFFICIENCY)/5)/20),ROUND(((SPEED_ADHERENCE + NON_IDLE_TIME + HARSH_DRIVING + FUEL_EFFICIENCY)/4)/20)) AS STAR_COUNT,  SPEEDBAND_0_TO_20KM,  SPEEDBAND_21_TO_40KM,  SPEEDBAND_41_TO_60KM,  SPEEDBAND_TO_80KM,  SPEEDBAND_OVER_80KM,  TRIP_DISTANCE,  TRUNC(DECODE(TOTAL_DRIVE_TIME,0,0,TRIP_DISTANCE/(TOTAL_DRIVE_TIME/3600)),1) AS AVERAGE_VEHICLE_SPEED FROM   (SELECT D.USER_NAME   AS DRIVER_NAME,    D.USER_ID  AS DRIVER_ID,    TRUNC (100 -(DECODE(TOTAL_DRIVE_TIME,0,0,NVL(SPEED_VIOLATION_TIME,0)/TOTAL_DRIVE_TIME)*100),2) AS SPEED_ADHERENCE,    TRUNC ((DECODE(TOTAL_DRIVE_TIME,0,0,NVL(SPEED_VIOLATION_TIME,0)/TOTAL_DRIVE_TIME)*100),2)   AS SPEED_VIOLATION,    TRUNC (DECODE((GREEN_BAND_TIME  + YELLOW_BAND_TIME+ RED_BAND_TIME),0,0,DECODE(ENGINE_RUN_HOURS,0,0,(GREEN_BAND_TIME+YELLOW_BAND_TIME)/ENGINE_RUN_HOURS)*100),2) AS ECONOMIC_BAND,    DECODE(ENGINE_RUN_HOURS,0,0, TRUNC(100  -((ENGINE_IDLE_TIME/ENGINE_RUN_HOURS)* 100),2))   AS NON_IDLE_TIME,    TRUNC ((DECODE(ENGINE_RUN_HOURS,0,0,(NVL(ENGINE_IDLE_TIME,0)   /ENGINE_RUN_HOURS)* 100)),2)   AS IDLE_TIME,    TRUNC (TOTAL_DRIVE_TIME)   AS TOTAL_DRIVE_TIME,    TRUNC (speed_violation_time)   AS SPEED_VIOLATION_TIME,    TRUNC (ENGINE_IDLE_TIME)  AS VEHICLE_IDLE_TIME,    70 AS HARSH_DRIVING,    80  AS FUEL_EFFICIENCY,    DG.GROUP_NAME    AS GROUP_NAME,    SPEEDBAND_0_TO_20KM,    SPEEDBAND_21_TO_40KM,    SPEEDBAND_41_TO_60KM,SPEEDBAND_TO_80KM,SPEEDBAND_OVER_80KM,TRIP_DISTANCE  FROM DICV_USER D,(SELECT TL.USER_ID AS DRIVER_ID, NVL(SUM(T.ENGINE_IDLE_TIME),0) AS ENGINE_IDLE_TIME, NVL(SUM(T.ENGINE_RUN_HOURS),0) AS ENGINE_RUN_HOURS, NVL(SUM(DRIVING_TIME),0) AS TOTAL_DRIVE_TIME, NVL(SUM (T.SPEED_VIOLATION_TIME),0) AS SPEED_VIOLATION_TIME, NVL(SUM (T.GREEN_BAND_TIME),0) AS GREEN_BAND_TIME,NVL(SUM (T.YELLOW_BAND_TIME),0) AS YELLOW_BAND_TIME, NVL(SUM (T.RED_BAND_TIME),0) AS RED_BAND_TIME, NVL(SUM (T.TIME_IN_0_TO_20KM),0)AS SPEEDBAND_0_TO_20KM, NVL(SUM (T.TIME_IN_21_TO_40KM),0)AS SPEEDBAND_21_TO_40KM, NVL(SUM (T.TIME_IN_41_TO_60KM),0) AS SPEEDBAND_41_TO_60KM, NVL(SUM (T.TIME_IN_61_TO_80KM),0) AS SPEEDBAND_TO_80KM, NVL(SUM (T.TIME_IN_OVER_80KM),0) AS SPEEDBAND_OVER_80KM, NVL(SUM (t.trip_distance),0) AS TRIP_DISTANCE  FROM trip t, trip_leg tl, dicv_user du  WHERE TL.USER_ID = DU.USER_ID  AND du.created_by  = :owner_id  AND t.trip_id  = tl.trip_id AND TRUNC(t.TRIP_START_TIME) >= :startDate AND TRUNC(T.TRIP_END_TIME) <= :endDate GROUP BY tl.user_id ) sq,dicv_groups dg  WHERE D.USER_ID = SQ.DRIVER_ID  AND d.GROUP_ID  =dg.GROUP_ID )ORDER BY DRIVER_SCORE DESC";

	public static final String DRIVERANALYSIS_FROMTRIP = "SELECT DRIVER_NAME,  DRIVER_ID,  GROUP_NAME,  SPEED_VIOLATION,  SPEED_ADHERENCE,  ECONOMIC_BAND,  NON_IDLE_TIME,  IDLE_TIME,  HARSH_DRIVING,  FUEL_EFFICIENCY,  TOTAL_DRIVE_TIME,  SPEED_VIOLATION_TIME,  NVL2(DECODE(ECONOMIC_BAND,0,NULL,ECONOMIC_BAND),ROUND((SPEED_ADHERENCE  + ECONOMIC_BAND +NON_IDLE_TIME + HARSH_DRIVING + FUEL_EFFICIENCY)/5,2),ROUND((SPEED_ADHERENCE + NON_IDLE_TIME + HARSH_DRIVING + FUEL_EFFICIENCY)/4,2))      AS DRIVER_SCORE,  NVL2(DECODE(ECONOMIC_BAND,0,NULL,ECONOMIC_BAND),ROUND(((SPEED_ADHERENCE + ECONOMIC_BAND +NON_IDLE_TIME + HARSH_DRIVING + FUEL_EFFICIENCY)/5)/20),ROUND(((SPEED_ADHERENCE + NON_IDLE_TIME + HARSH_DRIVING + FUEL_EFFICIENCY)/4)/20)) AS STAR_COUNT,  SPEEDBAND_0_TO_20KM,  SPEEDBAND_21_TO_40KM,  SPEEDBAND_41_TO_60KM,  SPEEDBAND_TO_80KM,  SPEEDBAND_OVER_80KM,  TRIP_DISTANCE,  trunc(decode(TOTAL_DRIVE_TIME,0,0,TRIP_DISTANCE/(TOTAL_DRIVE_TIME/3600)),1) AS AVERAGE_VEHICLE_SPEED FROM  (SELECT D.USER_NAME AS DRIVER_NAME, D.USER_ID AS DRIVER_ID, TRUNC (100-(DECODE(TOTAL_DRIVE_TIME,0,0,NVL(SPEED_VIOLATION_TIME,0)/TOTAL_DRIVE_TIME)*100),2) AS SPEED_ADHERENCE, TRUNC ((DECODE(TOTAL_DRIVE_TIME,0,0,NVL(SPEED_VIOLATION_TIME,0)/TOTAL_DRIVE_TIME)*100),2) AS SPEED_VIOLATION, TRUNC (DECODE((GREEN_BAND_TIME + YELLOW_BAND_TIME+ RED_BAND_TIME),0,0,DECODE(ENGINE_RUN_HOURS,0,0,(GREEN_BAND_TIME+YELLOW_BAND_TIME)/ENGINE_RUN_HOURS)*100),2) AS ECONOMIC_BAND, DECODE(ENGINE_RUN_HOURS,0,0, TRUNC(100 -((ENGINE_IDLE_TIME/ENGINE_RUN_HOURS)* 100),2)) AS NON_IDLE_TIME, TRUNC ((DECODE(ENGINE_RUN_HOURS,0,0,(NVL(ENGINE_IDLE_TIME,0)/ENGINE_RUN_HOURS)* 100)),2) AS IDLE_TIME, TRUNC (TOTAL_DRIVE_TIME) AS TOTAL_DRIVE_TIME, trunc (speed_violation_time) AS SPEED_VIOLATION_TIME, TRUNC (ENGINE_IDLE_TIME) AS VEHICLE_IDLE_TIME, 70 AS HARSH_DRIVING, 80 AS FUEL_EFFICIENCY, DG.GROUP_NAME AS GROUP_NAME, SPEEDBAND_0_TO_20KM,SPEEDBAND_21_TO_40KM,SPEEDBAND_41_TO_60KM, SPEEDBAND_TO_80KM, SPEEDBAND_OVER_80KM, TRIP_DISTANCE FROM DICV_USER D,(SELECT TL.USER_ID AS DRIVER_ID,NVL(SUM(T.ENGINE_IDLE_TIME),0) AS ENGINE_IDLE_TIME,NVL(SUM(T.ENGINE_RUN_HOURS),0) AS ENGINE_RUN_HOURS, NVL(SUM(DRIVING_TIME),0) AS TOTAL_DRIVE_TIME, NVL(SUM (T.SPEED_VIOLATION_TIME),0) AS SPEED_VIOLATION_TIME, NVL(SUM (T.GREEN_BAND_TIME),0) AS GREEN_BAND_TIME,    NVL(SUM (T.YELLOW_BAND_TIME),0) AS YELLOW_BAND_TIME, NVL(SUM (T.RED_BAND_TIME),0) AS RED_BAND_TIME,NVL(SUM (T.TIME_IN_0_TO_20KM),0) AS SPEEDBAND_0_TO_20KM, NVL(SUM (T.TIME_IN_21_TO_40KM),0) AS SPEEDBAND_21_TO_40KM, NVL(SUM (T.TIME_IN_41_TO_60KM),0) AS SPEEDBAND_41_TO_60KM, NVL(SUM (T.TIME_IN_61_TO_80KM),0) AS SPEEDBAND_TO_80KM, NVL(SUM (T.TIME_IN_OVER_80KM),0) AS SPEEDBAND_OVER_80KM, nvl(SUM (t.trip_distance),0) AS TRIP_DISTANCE FROM trip t, trip_leg tl, dicv_user du WHERE TL.USER_ID = DU.USER_ID AND du.created_by = :owner_id AND tl.user_id = :driverId AND t.trip_id = tl.trip_id AND TRUNC(t.TRIP_START_TIME) >= :startDate AND TRUNC(T.TRIP_END_TIME)   <= :endDate GROUP BY tl.user_id) sq, dicv_groups dg WHERE D.USER_ID = SQ.DRIVER_ID AND d.GROUP_ID =dg.GROUP_ID) ORDER BY DRIVER_SCORE DESC";

	public static final String VEHICLE_UTILIZATION = "select gi.VEHICLE_ID,vp.GPS_TIME,vp.gps_spkm,TRUNC(vp.GPS_TIME) "
			+ "from GPS_VEHICLE_PARAMETERS vp JOIN GPS_IMEI gi on gi.gps_imei=vp.gps_imei "
			+ "and gi.vehicle_id in (:vehicleIds) and TRUNC(vp.GPS_TIME) >= :fromDate "
			+ "and TRUNC(vp.GPS_TIME) <= :toDate order by vehicle_id, gps_time";

	public static final String GEO_FENCE_REPORT = "select get_geo_fence_name(geo_fence_id) as geo_fence_name,"
			+ "get_vehicle_name(vehicle_id) as vehicle_name,user_id,geo_fence_entry_time,geo_fence_exit_time, "
			+ "convert_seconds_hhmmss(time_spent)  as time_spent, get_registration_id(vehicle_id) as registration_Id from geo_fence_report "
			+ "where vehicle_id in (:vehicle_id_list) and user_id = :user_id and"
			+ " geo_fence_id in (:geo_fence_id_list) and trunc(nvl(geo_fence_entry_time,:from_date)) >= :from_date "
			+ "and trunc(nvl(geo_fence_entry_time,:to_date))  <= :to_date";

	public static final String ALERT_REPORTS = "SELECT get_vehicle_name(vehicle_id)  AS vehicle_name, get_alert_type_desc(alert_type_id) AS alert_type_desc,"
			+ " event_gps_time, geo_latitude, geo_longitute, GET_Registration_ID(vehicle_id) as registration_Id"
			+ " FROM notification " + "WHERE " + "TRUNC (event_gps_time) >= :from_date "
			+ "AND TRUNC (event_gps_time) <= :to_date " + "AND created_by = :user_id "
			+ "AND (vehicle_id IN (:vehicle_list)) " + "AND (alert_type_id  IN (:alert_type_list))";

	public static final String SCHEDULED_TRIP_SEARCH = "SELECT s.SCHEDULED_TRIP_ID,s.TRIP_STATUS,s.SCHEDULED_TRIP_FLAG,s.FROM_DATE,s.TO_DATE,v.REGISTRATION_ID,d.FROM_ADDRESS,d.TO_ADDRESS,"
			+ "t.TRIP_START_TIME,t.TRIP_END_TIME,t.TRIP_ID,t.TRIP_DISTANCE FROM SCHEDULED_TRIP s LEFT JOIN TRIP t ON t.SCHEDULED_TRIP_ID = s.SCHEDULED_TRIP_ID AND (t.IS_DELETED=:ACTIVE) "
			+ "INNER JOIN SCHEDULED_TRIP_LEG l ON l.SCHEDULED_TRIP_ID = s.SCHEDULED_TRIP_ID AND (l.IS_DELETED=:ACTIVE) INNER JOIN SCHEDULED_DISPATCH d "
			+ "ON d.SCHEDULED_TRIP_LEG_ID = l.SCHEDULED_TRIP_LEG_ID AND d.IS_DELETED=:ACTIVE INNER JOIN VEHICLE v ON v.VEHICLE_ID = l.VEHICLE_ID "
			+ "WHERE s.USER_ID= :USER_ID AND t.TRIP_DISTANCE>=10 AND EXISTS(SELECT 1 FROM DICV_USER o WHERE (o.RECORD_STATUS=:RECORD_STATUS) AND o.user_id=s.USER_ID) AND s.IS_DELETED=:ACTIVE "
			+ "AND (lower(s.SCHEDULED_TRIP_ID) like lower(:INPUT) OR lower(v.REGISTRATION_ID) like lower(:INPUT) OR lower(d.FROM_ADDRESS) "
			+ "like lower(:INPUT) OR (d.TO_ADDRESS) like lower(:INPUT)) ORDER BY s.SCHEDULED_TRIP_ID DESC";

	public static final String TRIP_PLAYBACK = "SELECT gvp.gps_latitude, gvp.gps_longitude, gvp.gps_spkm, 0 stop_duration, gvp.gps_time "
			+ "FROM gps_vehicle_parameters gvp, gps_imei gi, trip tp WHERE gvp.gps_imei = gi.gps_imei AND gvp.gps_spkm <> 0 "
			+ "AND gi.vehicle_ID = tp.vehicle_id AND tp.trip_id = :TRIP_ID AND tp.is_deleted = 0 AND EXISTS "
			+ "(SELECT 1 FROM vehicle veh WHERE veh.vehicle_id = tp.vehicle_ID AND veh.is_deleted =0 AND EXISTS"
			+ "(SELECT 1 FROM dicv_user du WHERE du.record_status = 'o' AND user_id = :OWNER_ID)) AND gvp.gps_time "
			+ "BETWEEN tp.trip_start_time AND tp.trip_end_time UNION ALL SELECT tsl.lattitude, tsl.longitude, 0 gps_spkm, "
			+ "tsl.stop_duration, tsl.stop_time FROM trip_stop_locations tsl,trip tp WHERE tsl.trip_ID = tp.trip_ID "
			+ "AND tp.trip_id = :TRIP_ID AND tp.is_deleted = 0 AND EXISTS (SELECT 1 FROM vehicle veh "
			+ "WHERE veh.vehicle_id = tp.vehicle_ID AND veh.is_deleted =0 AND EXISTS (SELECT 1 FROM dicv_user du WHERE du.record_status = 'o' AND user_id = :OWNER_ID)) "
			+ "AND tsl.stop_time BETWEEN tp.trip_start_time AND tp.trip_end_time ORDER BY gps_time";

	public static final String MY_FLEET_PLAYBACK = "SELECT gvp.gps_latitude, gvp.gps_longitude, gvp.gps_spkm, 0 stop_duration, gvp.gps_time FROM gps_vehicle_parameters gvp, gps_imei gi, trip tp "
			+ "WHERE gvp.gps_imei = gi.gps_imei AND gvp.gps_spkm <> 0 AND gi.vehicle_ID = tp.vehicle_ID AND tp.vehicle_ID = :VEHICLE_ID AND tp.is_deleted =0 "
			+ "AND (gvp.gps_time BETWEEN :FROM_DATE AND :TO_DATE) AND "
			+ "EXISTS (SELECT 1 FROM vehicle veh WHERE veh.vehicle_id = tp.vehicle_ID AND veh.is_deleted =0 AND EXISTS(SELECT 1 FROM dicv_user du WHERE du.record_status = 'o' AND user_id = :OWNER_ID))"
			+ "AND gvp.gps_time BETWEEN tp.trip_start_time AND tp.trip_end_time UNION ALL SELECT tsl.lattitude, tsl.longitude, 0 gps_skm, tsl.stop_duration, tsl.stop_time FROM trip_stop_locations tsl, trip tp "
			+ "WHERE tsl.trip_id = tp.trip_id AND tp.vehicle_ID = :VEHICLE_ID AND tp.is_deleted =0 AND (tsl.stop_time BETWEEN :FROM_DATE AND :TO_DATE) "
			+ "AND EXISTS (SELECT 1 FROM vehicle veh WHERE veh.vehicle_id = tp.vehicle_ID AND veh.is_deleted =0 AND EXISTS(SELECT 1 FROM dicv_user du WHERE du.record_status = 'o' AND user_id = :OWNER_ID)) ORDER BY gps_time";

	public static final String DRIVER_SPEEDING_STOPLOCATIONS = "SELECT tsl.lattitude gps_latitude, tsl.longitude gps_longitude, tsl.spkm gps_spkm, 0 stop_duration, tsl.speeding_gps_time gps_time "
			+ "FROM trip_speeding_locations tsl, trip_leg tl WHERE tsl.trip_id = tl.trip_id AND EXISTS (SELECT 1 FROM dicv_user du WHERE du.user_id = tl.user_id AND du.record_status IN ('o','n') "
			+ "AND manager_id = :OWNER_ID) AND tl.user_id = :DRIVER_ID AND TRUNC(tsl.speeding_gps_time) BETWEEN TRUNC(TO_DATE(:FROM_DATE)) AND TRUNC(TO_DATE(:TO_DATE)) "
			+ "UNION ALL "
			+ "SELECT tsl.lattitude, tsl.longitude, 0 gps_spkm, tsl.stop_duration, tsl.stop_time gps_time FROM trip_stop_locations tsl, "
			+ "trip_leg tl WHERE tsl.trip_id = tl.trip_id AND EXISTS (SELECT 1 FROM dicv_user du WHERE du.user_id = tl.user_id AND du.record_status IN ('o','n') "
			+ "AND manager_id = :OWNER_ID) AND tl.user_id = :DRIVER_ID AND TRUNC(tsl.stop_time) BETWEEN TRUNC(TO_DATE(:FROM_DATE)) AND TRUNC(TO_DATE(:TO_DATE)) ORDER BY gps_time";

	public static final String HIERARCHY_USER_LIST = "SELECT du.user_id FROM dicv_user du INNER JOIN dicv_user_role_mapping durm ON (du.user_id = durm.user_id) "
			+ "INNER JOIN dicv_roles dr ON (dr.role_id = durm.role_id) WHERE du.record_status = 'o' and durm.is_deleted = 0 "
			+ "and dr.is_deleted = 0 and du.manager_id = (select manager_id from dicv_user where user_id=:USER_ID)";

	public static final String CHANGE_DRIVER_ANALYSIS_OWNERSHIP = "UPDATE driver_analysis_list SET owner_id=:OWNERID where driver_id=:DRIVERID";
}
