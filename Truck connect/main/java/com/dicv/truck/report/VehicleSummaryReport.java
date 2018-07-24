package com.dicv.truck.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.dicv.truck.dto.VehicleSummaryReportVO;

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

/**
 * Report layout for vehicle summary report.
 * 
 * @author Hari
 * 
 */
public class VehicleSummaryReport {

	private final ArrayList<VehicleSummaryReportVO> list = new ArrayList<VehicleSummaryReportVO>();

	public VehicleSummaryReport(Collection<VehicleSummaryReportVO> c) {
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
				detailTextStyle, 120);
		AbstractColumn totalDistance = createColumn("totalDistance", String.class, "Total Distance (KM)", headerStyle,
				detailTextStyle, 80);
		AbstractColumn totalDrivingTime = createColumn("totalDrivingTime", String.class, "Total Drive Time (HH:MM:SS)",
				headerStyle, detailTextStyle, 90);
		AbstractColumn maximumSpeed = createColumn("maximumSpeed", String.class, "Max Speed (KM/H)", headerStyle,
				detailTextStyle, 55);
		AbstractColumn averageSpeed = createColumn("averageSpeed", String.class, "Average Speed (KM/H)", headerStyle,
				detailTextStyle, 55);

		AbstractColumn numberOfStops = createColumn("numberOfStops", String.class, "Number Of Stops", headerStyle,
				detailTextStyle, 55);
		AbstractColumn maxIdleTime = createColumn("maxIdleTime", String.class, "Max Idle Time (HH:MM:SS)", headerStyle,
				detailTextStyle, 90);
		AbstractColumn maxIdleLocation = createColumn("maxIdleLocation", String.class, "Max Idle Location", headerStyle,
				ReportLayout.createLinkStyle(), 400);
		AbstractColumn totalIdleTime = createColumn("totalIdleTime", String.class, "Total Idle Time (HH:MM:SS)",
				headerStyle, detailTextStyle, 90);

		DJHyperLink djlink2 = new DJHyperLink();
		djlink2.setExpression(new StringExpression() {
			public Object evaluate(Map fields, Map variables, Map parameters) {
				return list.get(Integer.parseInt(variables.get("REPORT_COUNT").toString()) - 1).getLatLong();

			}
		});
		maxIdleLocation.setLink(djlink2);
		maxIdleLocation.setStyle(ReportLayout.createLinkStyle());
		report.setReportName("VehicleUtilizationSummaryReport").addColumn(vehicleName).addColumn(totalDistance)
				.addColumn(totalDrivingTime).addColumn(maximumSpeed).addColumn(averageSpeed).addColumn(numberOfStops)
				.addColumn(maxIdleTime).addColumn(maxIdleLocation).addColumn(totalIdleTime).setUseFullPageWidth(true);
		report.setIgnorePagination(true);
		StyleBuilder titleStyle = new StyleBuilder(true);
		titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		titleStyle.setFont(new Font(20, Font._FONT_GEORGIA, true));
		report.setTitleStyle(titleStyle.build());
		report.setUseFullPageWidth(true);
		return report.build();
	}

}
