package com.thalesgroup.stif;

import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.chart.Pie3DChartBuilder;
import net.sf.dynamicreports.report.builder.chart.TimeSeriesChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.FillerBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.thalesgroup.stif.data.CustomData;

/**
 * Hello world!
 * 
 */
public class App {

	private static String LOGO = "/home/adile/Workspace/stif/administration/webapp/src/main/resources/com/thalesgroup/stif/administration/client/resources/images/banner/logo.png";

	public static void main(final String[] args) {
		System.out.println("Hello World!");

		try {

			StyleBuilder boldStyle = stl.style().bold();
			StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
			StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);
			StyleBuilder titleStyle = stl.style(boldCenteredStyle).setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(15);
			FontBuilder boldFont = stl.fontArialBold().setFontSize(12);

			TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType()).setStyle(boldCenteredStyle);
			TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType());
			TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit price", "price", type.bigDecimalType());
			TextColumnBuilder<java.util.Date> orderDateColumn = col.column("Order date", "orderDate", type.dateYearToMonthType());

			Bar3DChartBuilder itemChart = cht.bar3DChart();
			itemChart.setTitle("3D bar chart");
			itemChart.setCategory(itemColumn);
			itemChart.series(cht.serie(unitPriceColumn), cht.serie(quantityColumn));

			TimeSeriesChartBuilder lineChart = cht.timeSeriesChart();
			lineChart.setTitle("XY line chart");
			lineChart.setTitleFont(boldFont);
			lineChart.setTimePeriod(orderDateColumn);
			lineChart.setTimePeriodType(TimePeriod.YEAR);
			lineChart.series(cht.serie(quantityColumn), cht.serie(unitPriceColumn));
			lineChart.setTimeAxisFormat(cht.axisFormat().setLabel("Date"));

			Pie3DChartBuilder pieChart = cht.pie3DChart();
			pieChart.setTitle("Fromageee");
			pieChart.setKey(itemColumn);
			pieChart.series(cht.serie(quantityColumn), cht.serie(unitPriceColumn));

			// Generation du rapport global

			JasperReportBuilder report = DynamicReports.report();

			ImageBuilder logo = cmp.image(new FileInputStream(LOGO)).setFixedDimension(160, 80);
			TextFieldBuilder<String> titre = cmp.text("Mon titre");
			FillerBuilder filler = cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10);

			report.title(cmp.horizontalList().add(logo, cmp.text("Mon titre").setHorizontalAlignment(HorizontalAlignment.LEFT).setStyle(titleStyle)).newRow().add(filler));

			//			report.setTemplateDesign(new File("/home/adile/JaspersoftWorkspace/MyReports/template1.jrxml"));

			report.setColumnTitleStyle(columnTitleStyle);

			report.columns(itemColumn, quantityColumn, unitPriceColumn, orderDateColumn);

			report.summary(itemChart, lineChart, pieChart);

			report.pageFooter(cmp.horizontalList().add(cmp.text("Getting started"), cmp.currentDate(), cmp.pageXofY()));

			//			report.setDataSource(new JREmptyDataSource());
			Collection<CustomData> collection = fillCustomData();
			report.setDataSource(new JRBeanCollectionDataSource(collection));

			report.show();

			//export the report to a pdf file
			report.toPdf(new FileOutputStream("/home/adile/jasper/report.pdf"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Hello World!");
	}

	private static Collection<CustomData> fillCustomData() {

		Collection<CustomData> list = new ArrayList<CustomData>();

		for (int i = 0; i < 10; i++) {
			CustomData data = new CustomData();
			data.setItem("Item " + i);
			data.setPrice(new BigDecimal((int) (Math.random() * 10)));
			data.setQuantity((int) (Math.random() * 10));
			data.setOrderDate(toDate(2010 + data.getQuantity(), 1 + i));

			list.add(data);
		}

		return list;
	}

	private static Date toDate(final int year, final int month) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		return c.getTime();
	}
}
