package com.sirh.mqd.reporting.core.generation;

import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.chart.XyBarChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.axis.ValueAxis;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.exchanges.enums.ModuleEnum;
import com.sirh.mqd.reporting.core.enums.ReferenceRapport;
import com.sirh.mqd.reporting.persistence.entities.WSPerformanceEntity;

/**
 * Classe gérant la création du dashboard 7
 *
 * @see doc.story.ref1664
 *
 */
@Component
public class Rapport7 extends AbstractRapport {

	private static final int TIME_0200MS = 200;

	private static final int TIME_0400MS = 400;

	private static final int TIME_0600MS = 600;

	private static final int TIME_0800MS = 800;

	private static final int TIME_1000MS = 1000;

	private static final int TIME_1200MS = 1200;

	private static final int TIME_1400MS = 1400;

	private static final int TIME_1600MS = 1600;

	private static final int TIME_1800MS = 1800;

	private static final int TIME_2000MS = 2000;

	private static final int TIME_2200MS = 2200;

	private static final int TIME_2400MS = 2400;

	private static final int TIME_2600MS = 2600;

	private static final int TIME_2800MS = 2800;

	private final String TITLE = "Rapport du temps de traitement des services";

	private final String FOOTER_LIBELLE = "Rapport du temps de traitement des services";

	private final String VALUE_AXIS_TITLE = "Nombre de requête";

	private final String numberPattern = "#########";

	private static final String ABONNEMENT_SM = "Abonnement StopMonitoring";

	private static final String ABONNEMENT_GM = "Abonnement GeneralMessage";

	private static final String ABONNEMENT_ET = "Abonnement EstimatedTimetable";

	public Rapport7() {

	}

	@Override
	protected void buildRapport(final JasperReportBuilder report) {

		TextColumnBuilder<String> service = col.column("Service", "service", type.stringType());
		TextColumnBuilder<String> composant = col.column("Composant", "composant", type.stringType());
		TextColumnBuilder<Integer> duree = col.column("Durée", "duree", type.integerType());
		TextColumnBuilder<Integer> nbElement = col.column(VALUE_AXIS_TITLE, "total", type.integerType());

		XyBarChartBuilder lineChart = cht.xyBarChart();
		lineChart.setSubtitle("");
		lineChart.setTitleFont(boldFont);
		lineChart.setValuePattern(numberPattern);
		lineChart.setShowValues(true);

		lineChart.setXValue(duree);
		lineChart.series(cht.xySerie(nbElement));

		DRIChartCustomizer customizers = new DRIChartCustomizer() {

			@Override
			public void customize(final JFreeChart chart, final ReportParameters reportParameters) {
				ValueAxis axis = chart.getXYPlot().getDomainAxis();
				TickUnits tickUnits = new TickUnits();
				tickUnits.add(new NumberTickUnit(200));
				axis.setStandardTickUnits(tickUnits);

			}
		};
		lineChart.customizers(customizers);

		lineChart.setXAxisFormat(cht.axisFormat().setLabel("Repartition du temps de traitement").setTickLabelMask(numberPattern));

		lineChart.setYAxisFormat(cht.axisFormat().setLabel(VALUE_AXIS_TITLE).setTickLabelMask(numberPattern));

		report.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);

		report.summary();

		report.sortBy(composant, service, duree);

		report.groupBy(grp.group(composant), grp.group(service).footer(lineChart));

		report.setDataSource(dataSource);

	}

	@Override
	protected void createDataSourceRapport() {

		dataSource = new DRDataSource("composant", "service", "duree", "total");

		Map<String, String> nom = new HashMap<String, String>();
		nom.put("notifyStopMonitoringCommun", ABONNEMENT_SM);
		nom.put("notificationAbonnementVersDiffuseurs", ABONNEMENT_SM);
		nom.put("notifierAboGeneralMessageVersDiffuseursRpc", ABONNEMENT_GM);
		nom.put("notifyGeneralMessageCommun", ABONNEMENT_GM);
		nom.put("notifyEstimatedTimetableCommun", ABONNEMENT_ET);

		for (ModuleEnum module : ModuleEnum.values()) {

			if (module.equals(ModuleEnum.ORCHESTRATION)) {
				continue;
			}

			Page<WSPerformanceEntity> data = elasticsearchBC.find(module.name(), startDate, endDate);
			Iterator<WSPerformanceEntity> iterator = data.iterator();

			Map<String, Integer> results = new HashMap<String, Integer>();

			while (iterator.hasNext()) {
				WSPerformanceEntity echange = iterator.next();

				if (echange.getDuree() <= TIME_0200MS) {
					if (results.get(echange.getService() + "TIME_0200MS") == null) {
						results.put(echange.getService() + "TIME_0200MS", 0);
					}

					results.put(echange.getService() + "TIME_0200MS", results.get(echange.getService() + "TIME_0200MS") + 1);

				} else if (TIME_0200MS < echange.getDuree() && echange.getDuree() <= TIME_0400MS) {
					if (results.get(echange.getService() + "TIME_0400MS") == null) {
						results.put(echange.getService() + "TIME_0400MS", 0);
					}

					results.put(echange.getService() + "TIME_0400MS", results.get(echange.getService() + "TIME_0400MS") + 1);

				} else if (TIME_0400MS < echange.getDuree() && echange.getDuree() <= TIME_0600MS) {

					if (results.get(echange.getService() + "TIME_0600MS") == null) {
						results.put(echange.getService() + "TIME_0600MS", 0);
					}

					results.put(echange.getService() + "TIME_0600MS", results.get(echange.getService() + "TIME_0600MS") + 1);

				} else if (TIME_0600MS < echange.getDuree() && echange.getDuree() <= TIME_0800MS) {

					if (results.get(echange.getService() + "TIME_0800MS") == null) {
						results.put(echange.getService() + "TIME_0800MS", 0);
					}

					results.put(echange.getService() + "TIME_0800MS", results.get(echange.getService() + "TIME_0800MS") + 1);

				} else if (TIME_0800MS < echange.getDuree() && echange.getDuree() <= TIME_1000MS) {

					if (results.get(echange.getService() + "TIME_1000MS") == null) {
						results.put(echange.getService() + "TIME_1000MS", 0);
					}

					results.put(echange.getService() + "TIME_1000MS", results.get(echange.getService() + "TIME_1000MS") + 1);

				} else if (TIME_1000MS < echange.getDuree() && echange.getDuree() <= TIME_1200MS) {

					if (results.get(echange.getService() + "TIME_1200MS") == null) {
						results.put(echange.getService() + "TIME_1200MS", 0);
					}

					results.put(echange.getService() + "TIME_1200MS", results.get(echange.getService() + "TIME_1200MS") + 1);

				} else if (TIME_1200MS < echange.getDuree() && echange.getDuree() <= TIME_1400MS) {

					if (results.get(echange.getService() + "TIME_1400MS") == null) {
						results.put(echange.getService() + "TIME_1400MS", 0);
					}

					results.put(echange.getService() + "TIME_1400MS", results.get(echange.getService() + "TIME_1400MS") + 1);

				} else if (TIME_1400MS < echange.getDuree() && echange.getDuree() <= TIME_1600MS) {

					if (results.get(echange.getService() + "TIME_1600MS") == null) {
						results.put(echange.getService() + "TIME_1600MS", 0);
					}

					results.put(echange.getService() + "TIME_1600MS", results.get(echange.getService() + "TIME_1600MS") + 1);

				} else if (TIME_1600MS < echange.getDuree() && echange.getDuree() <= TIME_1800MS) {

					if (results.get(echange.getService() + "TIME_1800MS") == null) {
						results.put(echange.getService() + "TIME_1800MS", 0);
					}

					results.put(echange.getService() + "TIME_1800MS", results.get(echange.getService() + "TIME_1800MS") + 1);

				} else if (TIME_1800MS < echange.getDuree() && echange.getDuree() <= TIME_2000MS) {

					if (results.get(echange.getService() + "TIME_2000MS") == null) {
						results.put(echange.getService() + "TIME_2000MS", 0);
					}

					results.put(echange.getService() + "TIME_2000MS", results.get(echange.getService() + "TIME_2000MS") + 1);

				} else if (TIME_2000MS < echange.getDuree() && echange.getDuree() <= TIME_2200MS) {

					if (results.get(echange.getService() + "TIME_2200MS") == null) {
						results.put(echange.getService() + "TIME_2200MS", 0);
					}

					results.put(echange.getService() + "TIME_2200MS", results.get(echange.getService() + "TIME_2200MS") + 1);

				} else if (TIME_2200MS < echange.getDuree() && echange.getDuree() <= TIME_2400MS) {

					if (results.get(echange.getService() + "TIME_2400MS") == null) {
						results.put(echange.getService() + "TIME_2400MS", 0);
					}

					results.put(echange.getService() + "TIME_2400MS", results.get(echange.getService() + "TIME_2400MS") + 1);

				} else if (TIME_2400MS < echange.getDuree() && echange.getDuree() <= TIME_2600MS) {

					if (results.get(echange.getService() + "TIME_2600MS") == null) {
						results.put(echange.getService() + "TIME_2600MS", 0);
					}

					results.put(echange.getService() + "TIME_2600MS", results.get(echange.getService() + "TIME_2600MS") + 1);

				} else {

					if (results.get(echange.getService() + "TIME_2800MS") == null) {
						results.put(echange.getService() + "TIME_2800MS", 0);
					}

					results.put(echange.getService() + "TIME_2800MS", results.get(echange.getService() + "TIME_2800MS") + 1);
				}

			}

			for (String key : results.keySet()) {

				//			System.out.println(key + " " + results.get(key));

				int temps = 0;

				if (key.substring(key.length() - 11).contains("TIME_0200MS")) {
					temps = 200;
				} else if (key.substring(key.length() - 11).contains("TIME_0400MS")) {
					temps = 400;
				} else if (key.substring(key.length() - 11).contains("TIME_0600MS")) {
					temps = 600;
				} else if (key.substring(key.length() - 11).contains("TIME_0800MS")) {
					temps = 800;
				} else if (key.substring(key.length() - 11).contains("TIME_1000MS")) {
					temps = 1000;
				} else if (key.substring(key.length() - 11).contains("TIME_1200MS")) {
					temps = 1200;
				} else if (key.substring(key.length() - 11).contains("TIME_1400MS")) {
					temps = 1400;
				} else if (key.substring(key.length() - 11).contains("TIME_1600MS")) {
					temps = 1600;
				} else if (key.substring(key.length() - 11).contains("TIME_1800MS")) {
					temps = 1800;
				} else if (key.substring(key.length() - 11).contains("TIME_2000MS")) {
					temps = 2000;
				} else if (key.substring(key.length() - 11).contains("TIME_2200MS")) {
					temps = 2200;
				} else if (key.substring(key.length() - 11).contains("TIME_2400MS")) {
					temps = 2400;
				} else if (key.substring(key.length() - 11).contains("TIME_2600MS")) {
					temps = 2600;
				} else {
					temps = 2800;
				}

				//			getGeneralMessage

				String service = key.substring(0, key.length() - 11);

				//				String service = sub;
				if (nom.get(service) != null) {
					service = nom.get(service);
				}

				// si c'est une requete
				if (service.startsWith("get")) {
					service = "Requête " + service.substring(3, service.length());
				}

				dataSource.add(module.name(), service, temps, results.get(key));

			}

		}

	}

	@Override
	public String getRapportTitle() {
		return TITLE;
	}

	@Override
	public String getRapportFooterLibelle() {
		return FOOTER_LIBELLE;
	}

	@Override
	public String getReference() {
		// TODO Auto-generated method stub
		return ReferenceRapport.RAPPORT_7.name();
	}

}
