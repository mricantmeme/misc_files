package com.dicv.truck.utility;

public enum ReportType {

	VEHICLE_SUMMARY_REPORT("VEHICLE_SUMMARY_REPORT"), GEO_FENCE_REPORT("GEO_FENCE_REPORT"), NIGHT_DRIVING_REPORT_SP(
			"NIGHT_DRIVING_REPORT_SP"), VEHICLE_OVERSPEED_REPORT("VEHICLE_OVERSPEED_REPORT"), USER_REPORT(
					"USER_REPORT"), VEHICLE_REPORT("VEHICLE_REPORT"), ALERT_REPORT(
							"ALERT_REPORT"), VEHICLE_INACTIVE_REPORT("VEHICLE_INACTIVE_REPORT"), CUSTOMER_ADMIN_REPORT(
									"CUSTOMER_ADMIN_REPORT"), DEALER_REPORT("DEALER_REPORT"), DEVICE_HEALTH_REPORT(
											"DEVICE_HEALTH_REPORT"), VEHICLE_UTILIZATION_SUMMARY_REPORT(
													"VEHICLE_UTILIZATION_SUMMARY_REPORT"),MY_FLEET_REPORT("MY_FLEET_REPORT"), VEHICLE_STATISTICS_REPORT(
															"VEHICLE_STATISTICS_REPORT");

	private String reportType;

	ReportType(String statusCode) {
		this.reportType = statusCode;

	}

	public String getReportType() {
		return reportType;
	}

}
