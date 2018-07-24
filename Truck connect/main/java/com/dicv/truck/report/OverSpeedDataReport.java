package com.dicv.truck.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.dicv.truck.dto.SpeedReportLayoutDto;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DJHyperLink;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.StringExpression;
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

public class OverSpeedDataReport {

	private final ArrayList<SpeedReportLayoutDto> list = new ArrayList<>();

	public OverSpeedDataReport(Collection<SpeedReportLayoutDto> c) {
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

		AbstractColumn vehicleName = createColumn("vehicleName", String.class, "Registration Number", headerStyle,
				detailTextStyle, 140);
		AbstractColumn speedLimit = createColumn("speedLimit", String.class, "Max Speed Limit", headerStyle,
				detailTextStyle, 140);
		AbstractColumn totalDistance = createColumn("totalDistance", String.class, "Total Distance (KM)", headerStyle,
				detailTextStyle, 140);
		AbstractColumn speedDistance = createColumn("speedDistance", String.class, "Over Speed Distance (KM)",
				headerStyle, detailTextStyle, 140);
		AbstractColumn speedDuration = createColumn("speedDuration", String.class, "Over Speed Duration (HH:MM:SS)",
				headerStyle, detailTextStyle, 140);
		AbstractColumn maxSpeed = createColumn("maxSpeed", String.class, "Max Speed", headerStyle, detailTextStyle,
				140);
		AbstractColumn mapUrl = createColumn("mapUrl", String.class, "Max Speed Location", headerStyle,
				ReportLayout.createLinkStyle(), 400);

		DJHyperLink djlink2 = new DJHyperLink();
		djlink2.setExpression(new StringExpression() {
			public Object evaluate(Map fields, Map variables, Map parameters) {
				return list.get(Integer.parseInt(variables.get("REPORT_COUNT").toString()) - 1).getLatLong();

			}
		});
		mapUrl.setLink(djlink2);
		mapUrl.setStyle(ReportLayout.createLinkStyle());
		report.setReportName("Vehicle Speed Report").addColumn(vehicleName).addColumn(speedLimit)
				.addColumn(totalDistance).addColumn(speedDistance).addColumn(speedDuration).addColumn(maxSpeed)
				.addColumn(mapUrl).setUseFullPageWidth(true);
		StyleBuilder titleStyle = new StyleBuilder(true);
		titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		titleStyle.setFont(new Font(20, Font._FONT_GEORGIA, true));
		report.setTitleStyle(titleStyle.build());
		report.setUseFullPageWidth(true);
		report.setIgnorePagination(true);
		return report.build();
	}

}
