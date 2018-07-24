package com.dicv.truck.report;

import java.util.ArrayList;
import java.util.Collection;

import com.dicv.truck.dto.DeviceHealthReportDto;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class DeviceReport {

	private final Collection<DeviceHealthReportDto> list = new ArrayList<>();

	public DeviceReport(Collection<DeviceHealthReportDto> c) {
		list.addAll(c);
	}

	public JasperPrint getReport() throws ColumnBuilderException, JRException, ClassNotFoundException {
		Style headerStyle = ReportLayout.createHeaderStyle();
		Style detailTextStyle = ReportLayout.createDetailTextStyle();
		Style detailNumberStyle = ReportLayout.createDetailNumberStyle();
		DynamicReport dynaReport = getReport(headerStyle, detailTextStyle, detailNumberStyle);
		JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dynaReport, new ClassicLayoutManager(),
				new JRBeanCollectionDataSource(list));
		return jp;
	}


	public AbstractColumn createColumn(String property, Class<?> type, String title, Style headerStyle,
			Style detailStyle, int width) throws ColumnBuilderException {
		AbstractColumn columnState = ColumnBuilder.getNew().setColumnProperty(property, type.getName()).setTitle(title)
				.setStyle(detailStyle).setHeaderStyle(headerStyle).setWidth(Integer.valueOf(width)).setFixedWidth(true)
				.build();
		return columnState;

	}

	private DynamicReport getReport(Style headerStyle, Style detailTextStyle, Style detailNumStyle)
			throws ColumnBuilderException, ClassNotFoundException {

		DynamicReportBuilder report = new DynamicReportBuilder();

		AbstractColumn groupName = createColumn("groupName", String.class, "Group", headerStyle, detailTextStyle, 160);

		AbstractColumn registrationId = createColumn("registrationId", String.class, "Registration Number", headerStyle,
				detailTextStyle, 160);

		AbstractColumn gpsImei = createColumn("gpsImei", Long.class, "GPS IMEI", headerStyle, detailTextStyle, 180);
		AbstractColumn gpsTime = createColumn("gpsTime", String.class, "Last GPS Transmission", headerStyle,
				detailTextStyle, 180);
		AbstractColumn vehicleLastUpdateON = createColumn("vehicleLastUpdateON", String.class,
				"Last CAN Transmission", headerStyle, detailTextStyle, 180);
		AbstractColumn signalStrength = createColumn("signalStrength", Integer.class, "Signal Strength", headerStyle,
				detailTextStyle, 100);

		AbstractColumn gpsVolt = createColumn("gpsVolt", Double.class, "Vehicle Battery(V)", headerStyle,
				detailTextStyle, 160);

		AbstractColumn deviceBattery = createColumn("deviceBattery", Double.class, "Device Battery(V)", headerStyle,
				detailTextStyle, 100);

		report.setReportName("DeviceHealthMonitoring").addColumn(groupName).addColumn(registrationId).addColumn(gpsImei)
				.addColumn(gpsTime).addColumn(vehicleLastUpdateON).addColumn(signalStrength).addColumn(gpsVolt)
				.addColumn(deviceBattery).setUseFullPageWidth(true);

		StyleBuilder titleStyle = new StyleBuilder(true);
		titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		titleStyle.setFont(new Font(20, Font._FONT_GEORGIA, true));

		StyleBuilder subTitleStyle = new StyleBuilder(true);
		subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		subTitleStyle.setFont(new Font(Font.MEDIUM, Font._FONT_GEORGIA, true));
		// report.setTitle("Device Health Report");
		report.setTitleStyle(titleStyle.build());
		// report.setSubtitleStyle(subTitleStyle.build());
		report.setUseFullPageWidth(true);
		report.setIgnorePagination(true);
		return report.build();
	}
}
