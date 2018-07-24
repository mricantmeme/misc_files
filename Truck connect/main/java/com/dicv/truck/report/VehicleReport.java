package com.dicv.truck.report;

import java.util.ArrayList;
import java.util.Collection;

import com.dicv.truck.dto.VehicleDtlsDto;

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

public class VehicleReport {

	private final Collection<VehicleDtlsDto> list = new ArrayList<>();

	public VehicleReport(Collection<VehicleDtlsDto> c) {
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

		AbstractColumn vehicleName = createColumn("registrationId", String.class, "Vehicle Registration Number",
				headerStyle, detailTextStyle, 140);
		AbstractColumn vehicleDescription = createColumn("description", String.class, "Description", headerStyle,
				detailTextStyle, 140);
		AbstractColumn vin = createColumn("vin", String.class, "Vin Number", headerStyle, detailTextStyle, 140);
		AbstractColumn maxVehicleSpeed = createColumn("maxVehicleSpeed", Integer.class, "Over Speed Limit", headerStyle,
				detailTextStyle, 140);
		AbstractColumn gpsImei = createColumn("gpsImei", String.class, "GPS IMEI", headerStyle, detailTextStyle, 140);
		AbstractColumn gpsSimNumber = createColumn("gpsSimNumber", String.class, "GPS SIM", headerStyle,
				detailTextStyle, 140);
		AbstractColumn defaultDriver = createColumn("defaultDriver", String.class, "Default Driver", headerStyle,
				detailTextStyle, 140);
		AbstractColumn companyName = createColumn("companyName", String.class, "Company", headerStyle, detailTextStyle,
				140);
		AbstractColumn customerAdmin = createColumn("customerAdmin", String.class, "Customer Admin", headerStyle,
				detailTextStyle, 140);
		AbstractColumn dealerName = createColumn("dealerName", String.class, "Dealer", headerStyle, detailTextStyle,
				140);
		AbstractColumn category = createColumn("vehicleCategoryDesc", String.class, "Vehicle Category", headerStyle,
				detailTextStyle, 160);
		AbstractColumn gpsFitmentDate = createColumn("gpsFitmentDate", String.class, "GpsFitment Date", headerStyle,
				detailTextStyle, 160);
		report.setReportName("VehicleReport").addColumn(vehicleName).addColumn(vehicleDescription).addColumn(vin)
				.addColumn(category).addColumn(maxVehicleSpeed).addColumn(gpsImei).addColumn(gpsSimNumber)
				.addColumn(defaultDriver).addColumn(companyName).addColumn(customerAdmin).addColumn(dealerName)
				.addColumn(gpsFitmentDate).setUseFullPageWidth(true);
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
