package com.dicv.truck.report;

import java.util.ArrayList;
import java.util.Collection;

import com.dicv.truck.dto.GeoFenceReportDto;

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

public class GeoFenceDataReport {

	private final Collection<GeoFenceReportDto> list = new ArrayList<>();

	public GeoFenceDataReport(Collection<GeoFenceReportDto> c) {
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

		AbstractColumn vehicleName = createColumn("registrationId", String.class, "Registration Number", headerStyle,
				detailTextStyle, 140);
		AbstractColumn geoFenceName = createColumn("geoFenceName", String.class, "Geofence Name", headerStyle,
				detailTextStyle, 100);
		AbstractColumn entryTime = createColumn("entryTime", String.class, "Geofence Entry Time(MM/DD/YY HH:MM)",
				headerStyle, detailTextStyle, 160);
		AbstractColumn exitTime = createColumn("exitTime", String.class, "Geofence Exit Time(MM/DD/YY HH:MM)",
				headerStyle, detailTextStyle, 160);
		AbstractColumn timeInhrs = createColumn("timeInhrs", String.class, "Time Spent in Geofence(HH:MM:SS)",
				headerStyle, detailTextStyle, 160);
		report.setReportName("GeofenceReport").addColumn(vehicleName).addColumn(geoFenceName).addColumn(entryTime)
				.addColumn(exitTime).addColumn(timeInhrs).setUseFullPageWidth(true);

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
