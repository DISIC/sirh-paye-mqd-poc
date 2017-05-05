package com.sirh.mqd.reporting.core.generation;

import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.chart.PieChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.FillerBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.datasource.DRDataSource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.traces.api.IFacadeLogs;
import com.sirh.mqd.commons.traces.logs.FacadeLogFactory;
import com.sirh.mqd.commons.traces.logs.LogWorkflowFactory;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.enums.ReferenceRapport;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;

/**
 * Classe gérant la création du dashboard 4
 *
 * @see doc.story.ref1663
 *
 */
@Component
public class Rapport4 extends AbstractRapport {

	/**
	 *
	 */
	private static final String LIGNES_À_USAGE_NORMAL = "Lignes à usage normal";

	/**
	 *
	 */
	private static final String LIGNES_TRÈS_SOLLICITÉES = "Lignes très sollicitées";

	/**
	 *
	 */
	private static final String LIGNES_PEU_SOLLICITÉES = "Lignes peu sollicitées";

	/**
	 *
	 */
	private static final String LIGNES_TOTALEMENT_ABSENTES = "Lignes totalement absentes";

	/**
	 *
	 */
	private static final String ARRÊTS_À_USAGE_NORMAL = "Arrêts à usage normal";

	/**
	 *
	 */
	private static final String ARRÊTS_TRÈS_SOLLICITÉS = "Arrêts très sollicités";

	/**
	 *
	 */
	private static final String ARRÊTS_PEU_SOLLICITÉS = "Arrêts peu sollicités";

	/**
	 *
	 */
	private static final String ARRÊTS_TOTALEMENT_ABSENTS = "Arrêts totalement absents";

	private final String TITLE = "Taux d’usage métier";

	private final String FOOTER_LIBELLE = "Sollicitation du RELAIS par arrêt non croisée";

	private final String FOOTER_LIBELLE2 = "Sollicitation du RELAIS par ligne non croisée";

	private final String FOOTER_LIBELLE3 = "Sollicitation du RELAIS par arrêt croisée";

	private final String FOOTER_LIBELLE4 = "Sollicitation du RELAIS par ligne croisée";

	private static final IFacadeLogs LOG = FacadeLogFactory.getLogger(Rapport4.class);

	public Rapport4() {

	}

	@Override
	protected void buildRapport(final JasperReportBuilder report) {
		LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("*************Build " + TITLE + "*************"));
		TextColumnBuilder<String> itemColumn = col.column("Entité", "item", type.stringType());
		TextColumnBuilder<Integer> countColumn = col.column("count arret", "count", type.integerType());

		PieChartBuilder pieChart1 = cht.pieChart();
		pieChart1.setTitle("Sollicitation par arrêt: acquisition mode requête - émission mode requête");
		pieChart1.setSubtitle("");
		pieChart1.setTitleFont(boldFont);
		pieChart1.series(cht.serie(countColumn));
		pieChart1.setKey(itemColumn);
		pieChart1.setDataSource(createDataSourceRapport1());
		pieChart1.setShowPercentages(true);
		pieChart1.addSeriesColorByName(ARRÊTS_PEU_SOLLICITÉS, Color.BLUE);
		pieChart1.addSeriesColorByName(ARRÊTS_À_USAGE_NORMAL, Color.GREEN);
		pieChart1.addSeriesColorByName(ARRÊTS_TOTALEMENT_ABSENTS, Color.GRAY);
		pieChart1.addSeriesColorByName(ARRÊTS_TRÈS_SOLLICITÉS, Color.RED);

		itemColumn = col.column("Entité", "item", type.stringType());
		countColumn = col.column("count arrêt", "count", type.integerType());

		PieChartBuilder pieChart2 = cht.pieChart();
		pieChart2.setTitle("Sollicitation par arrêt: acquisition mode abonnement - émission mode abonnement");
		pieChart2.setSubtitle("");
		pieChart2.setTitleFont(boldFont);
		pieChart2.series(cht.serie(countColumn));
		pieChart2.setKey(itemColumn);
		pieChart2.setDataSource(createDataSourceRapport2());
		pieChart2.setShowPercentages(true);
		pieChart2.addSeriesColorByName(ARRÊTS_PEU_SOLLICITÉS, Color.BLUE);
		pieChart2.addSeriesColorByName(ARRÊTS_À_USAGE_NORMAL, Color.GREEN);
		pieChart2.addSeriesColorByName(ARRÊTS_TOTALEMENT_ABSENTS, Color.GRAY);
		pieChart2.addSeriesColorByName(ARRÊTS_TRÈS_SOLLICITÉS, Color.RED);

		itemColumn = col.column("Entité", "item", type.stringType());
		countColumn = col.column("count arrêt", "count", type.integerType());

		PieChartBuilder pieChart3 = cht.pieChart();
		pieChart3.setTitle("Sollicitation par ligne: acquisition mode requête - émission mode requête");
		pieChart3.setSubtitle("");
		pieChart3.setTitleFont(boldFont);
		pieChart3.series(cht.serie(countColumn));
		pieChart3.setKey(itemColumn);
		pieChart3.setDataSource(createDataSourceRapport3());
		pieChart3.setShowPercentages(true);
		pieChart3.addSeriesColorByName(LIGNES_PEU_SOLLICITÉES, Color.BLUE);
		pieChart3.addSeriesColorByName(LIGNES_À_USAGE_NORMAL, Color.GREEN);
		pieChart3.addSeriesColorByName(LIGNES_TOTALEMENT_ABSENTES, Color.GRAY);
		pieChart3.addSeriesColorByName(LIGNES_TRÈS_SOLLICITÉES, Color.RED);

		itemColumn = col.column("Entité", "item", type.stringType());
		countColumn = col.column("count arrêt", "count", type.integerType());

		PieChartBuilder pieChart4 = cht.pieChart();
		pieChart4.setTitle("Sollicitation par ligne: acquisition mode abonnement - émission mode abonnement");
		pieChart4.setSubtitle("");
		pieChart4.setTitleFont(boldFont);
		pieChart4.series(cht.serie(countColumn));
		pieChart4.setKey(itemColumn);
		pieChart4.setDataSource(createDataSourceRapport4());
		pieChart4.setShowPercentages(true);
		pieChart4.addSeriesColorByName(LIGNES_PEU_SOLLICITÉES, Color.BLUE);
		pieChart4.addSeriesColorByName(LIGNES_À_USAGE_NORMAL, Color.GREEN);
		pieChart4.addSeriesColorByName(LIGNES_TOTALEMENT_ABSENTES, Color.GRAY);
		pieChart4.addSeriesColorByName(LIGNES_TRÈS_SOLLICITÉES, Color.RED);

		PieChartBuilder pieChart1p = cht.pieChart();
		pieChart1p.setTitle("Sollicitation par arrêt: acquisition mode requête - émission mode abonnement");
		pieChart1p.setSubtitle("");
		pieChart1p.setTitleFont(boldFont);
		pieChart1p.series(cht.serie(countColumn));
		pieChart1p.setKey(itemColumn);
		pieChart1p.setDataSource(createDataSourceRapport1p());
		pieChart1p.setShowPercentages(true);
		pieChart1p.addSeriesColorByName(ARRÊTS_PEU_SOLLICITÉS, Color.BLUE);
		pieChart1p.addSeriesColorByName(ARRÊTS_À_USAGE_NORMAL, Color.GREEN);
		pieChart1p.addSeriesColorByName(ARRÊTS_TOTALEMENT_ABSENTS, Color.GRAY);
		pieChart1p.addSeriesColorByName(ARRÊTS_TRÈS_SOLLICITÉS, Color.RED);

		itemColumn = col.column("Entité", "item", type.stringType());
		countColumn = col.column("count arrêt", "count", type.integerType());

		PieChartBuilder pieChart2p = cht.pieChart();
		pieChart2p.setTitle("Sollicitation par arrêt: acquisition mode abonnement - émission mode requête");
		pieChart2p.setSubtitle("");
		pieChart2p.setTitleFont(boldFont);
		pieChart2p.series(cht.serie(countColumn));
		pieChart2p.setKey(itemColumn);
		pieChart2p.setDataSource(createDataSourceRapport2p());
		pieChart2p.setShowPercentages(true);
		pieChart2p.addSeriesColorByName(ARRÊTS_PEU_SOLLICITÉS, Color.BLUE);
		pieChart2p.addSeriesColorByName(ARRÊTS_À_USAGE_NORMAL, Color.GREEN);
		pieChart2p.addSeriesColorByName(ARRÊTS_TOTALEMENT_ABSENTS, Color.GRAY);
		pieChart2p.addSeriesColorByName(ARRÊTS_TRÈS_SOLLICITÉS, Color.RED);

		itemColumn = col.column("Entité", "item", type.stringType());
		countColumn = col.column("count arrêt", "count", type.integerType());

		PieChartBuilder pieChart3p = cht.pieChart();
		pieChart3p.setTitle("Sollicitation par ligne: acquisition mode requête - émission mode abonnement");
		pieChart3p.setSubtitle("");
		pieChart3p.setTitleFont(boldFont);
		pieChart3p.series(cht.serie(countColumn));
		pieChart3p.setKey(itemColumn);
		pieChart3p.setDataSource(createDataSourceRapport3p());
		pieChart3p.setShowPercentages(true);
		pieChart3p.addSeriesColorByName(LIGNES_PEU_SOLLICITÉES, Color.BLUE);
		pieChart3p.addSeriesColorByName(LIGNES_À_USAGE_NORMAL, Color.GREEN);
		pieChart3p.addSeriesColorByName(LIGNES_TOTALEMENT_ABSENTES, Color.GRAY);
		pieChart3p.addSeriesColorByName(LIGNES_TRÈS_SOLLICITÉES, Color.RED);

		itemColumn = col.column("Entité", "item", type.stringType());
		countColumn = col.column("count arrêt", "count", type.integerType());

		PieChartBuilder pieChart4p = cht.pieChart();
		pieChart4p.setTitle("Sollicitation par ligne: acquisition mode abonnement - émission mode requête");
		pieChart4p.setSubtitle("");
		pieChart4p.setTitleFont(boldFont);
		pieChart4p.series(cht.serie(countColumn));
		pieChart4p.setKey(itemColumn);
		pieChart4p.setDataSource(createDataSourceRapport4p());
		pieChart4p.setShowPercentages(true);
		pieChart4p.addSeriesColorByName(LIGNES_PEU_SOLLICITÉES, Color.BLUE);
		pieChart4p.addSeriesColorByName(LIGNES_À_USAGE_NORMAL, Color.GREEN);
		pieChart4p.addSeriesColorByName(LIGNES_TOTALEMENT_ABSENTES, Color.GRAY);
		pieChart4p.addSeriesColorByName(LIGNES_TRÈS_SOLLICITÉES, Color.RED);

		report.setSummarySplitType(SplitType.IMMEDIATE);
		report.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);
		FillerBuilder filler = cmp.filler().setStyle(stl.style().setTopBorder(stl.pen())).setFixedHeight(120);
		if (startDate != null && endDate != null) {
			report.summary(
					pieChart1,
					pieChart2,
					pieChart3,
					pieChart4,
					filler,
					cmp.text(FOOTER_LIBELLE2).setHorizontalAlignment(HorizontalAlignment.CENTER),
					cmp.text(
							"Date début: " + DateUtils.formateDateJJMMAAAAhhmmss(startDate) + " Date fin: "
									+ DateUtils.formateDateJJMMAAAAhhmmss(endDate) + " Date génération: "
									+ DateUtils.formateDateJJMMAAAAhhmmss(DateUtils.getCalendarInstance().getTime()))
									.setHorizontalAlignment(HorizontalAlignment.CENTER),
									cmp.text("2/4").setHorizontalAlignment(HorizontalAlignment.CENTER),
									pieChart1p,
									pieChart2p,
									filler,
									cmp.text(FOOTER_LIBELLE2).setHorizontalAlignment(HorizontalAlignment.CENTER),
									cmp.text(
							"Date début: " + DateUtils.formateDateJJMMAAAAhhmmss(startDate) + " Date fin: "
									+ DateUtils.formateDateJJMMAAAAhhmmss(endDate) + " Date génération: "
									+ DateUtils.formateDateJJMMAAAAhhmmss(DateUtils.getCalendarInstance().getTime()))
													.setHorizontalAlignment(HorizontalAlignment.CENTER),
													cmp.text(" 3/4").setHorizontalAlignment(HorizontalAlignment.CENTER),
													pieChart3p,
													pieChart4p,
													filler,
													cmp.text(FOOTER_LIBELLE2).setHorizontalAlignment(HorizontalAlignment.CENTER),
													cmp.text(
							"Date début: " + DateUtils.formateDateJJMMAAAAhhmmss(startDate) + " Date fin: "
									+ DateUtils.formateDateJJMMAAAAhhmmss(endDate) + " Date génération: "
									+ DateUtils.formateDateJJMMAAAAhhmmss(DateUtils.getCalendarInstance().getTime()))
																	.setHorizontalAlignment(HorizontalAlignment.CENTER),
																	cmp.text(" 4/4").setHorizontalAlignment(HorizontalAlignment.CENTER));
		} else {
			report.summary(
					pieChart1,
					pieChart2,
					pieChart3,
					pieChart4,
					filler,
					cmp.text(FOOTER_LIBELLE2 + " " + DateUtils.formateDateJJMMAAAAhhmmss(DateUtils.getCalendarInstance().getTime())
							+ "                                	   	2 of 4"),
							pieChart1p,
							pieChart2p,
							filler,
							cmp.text(FOOTER_LIBELLE2 + " " + DateUtils.formateDateJJMMAAAAhhmmss(DateUtils.getCalendarInstance().getTime())
									+ "                                	   	3 of 4"),
									pieChart3p,
									pieChart4p,
									filler,
									cmp.text(FOOTER_LIBELLE2 + " " + DateUtils.formateDateJJMMAAAAhhmmss(DateUtils.getCalendarInstance().getTime())
											+ "                                	   	4 of 4"));
		}

	}

	protected DRDataSource createDataSourceRapport1() {
		LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("*************Datasource " + TITLE + "*************"));

		DRDataSource dataSource1 = new DRDataSource("item", "count");

		Page<WSGenericMetierEntity> dataEmission = elasticsearchBC.findforRapport4(true, false, true, startDate, endDate);
		Page<WSGenericMetierEntity> dataAcquisition = elasticsearchBC.findforRapport4(true, true, true, startDate, endDate);
		Map<String, Integer> acquisitionData = new HashMap<>();
		Map<String, Integer> emissionData = new HashMap<>();
		if (dataAcquisition != null && dataEmission != null) {
			remplirMapArret(dataEmission, dataAcquisition, acquisitionData, emissionData);

			for (String acquisitionDataTemp : acquisitionData.keySet()) {
				boolean flagKO = false;
				if (acquisitionDataTemp != null) {
					if (emissionData.containsKey(acquisitionDataTemp)) {
						flagKO = true;
					}
					if (!flagKO) {
						//absent en emission
						dataSource1.add(ARRÊTS_TOTALEMENT_ABSENTS, 1);
					}
					if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) < acquisitionData.get(acquisitionDataTemp)) {
						//arrêt peu sollicité côté Emission (nombre d'apparitions côté Emission < nombre d'apparitions côté Acquisition) ;
						dataSource1.add(ARRÊTS_PEU_SOLLICITÉS, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) > 5 * acquisitionData.get(acquisitionDataTemp)) {
						//arrêt très sollicité côté Emission (nombre d'apparitions côté Emission > 5*nombre d'apparitions côté Acquisition).
						dataSource1.add(ARRÊTS_TRÈS_SOLLICITÉS, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) >= acquisitionData.get(acquisitionDataTemp)
							&& emissionData.get(acquisitionDataTemp) <= 5 * acquisitionData.get(acquisitionDataTemp)) {
						//arrêt en usage normal (tout le reste);
						dataSource1.add(ARRÊTS_À_USAGE_NORMAL, 1);
					}
				}
			}
		}

		return dataSource1;
	}

	protected DRDataSource createDataSourceRapport2() {
		LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("*************Datasource " + TITLE + "*************"));
		DRDataSource dataSource2 = new DRDataSource("item", "count");
		Page<WSGenericMetierEntity> dataEmission = elasticsearchBC.findforRapport4(true, false, false, startDate, endDate);
		Page<WSGenericMetierEntity> dataAcquisition = elasticsearchBC.findforRapport4(true, true, false, startDate, endDate);
		Map<String, Integer> acquisitionData = new HashMap<>();
		Map<String, Integer> emissionData = new HashMap<>();
		if (dataAcquisition != null && dataEmission != null) {
			remplirMapArret(dataEmission, dataAcquisition, acquisitionData, emissionData);

			for (String acquisitionDataTemp : acquisitionData.keySet()) {
				boolean flagKO = false;
				if (acquisitionDataTemp != null) {
					if (emissionData.containsKey(acquisitionDataTemp)) {
						flagKO = true;
					}
					if (!flagKO) {
						//absent en emission
						dataSource2.add(ARRÊTS_TOTALEMENT_ABSENTS, 1);
					}
					if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) < acquisitionData.get(acquisitionDataTemp)) {
						//arrêt peu sollicité côté Emission (nombre d'apparitions côté Emission < nombre d'apparitions côté Acquisition) ;
						dataSource2.add(ARRÊTS_PEU_SOLLICITÉS, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) > 5 * acquisitionData.get(acquisitionDataTemp)) {
						//arrêt très sollicité côté Emission (nombre d'apparitions côté Emission > 5*nombre d'apparitions côté Acquisition).
						dataSource2.add(ARRÊTS_TRÈS_SOLLICITÉS, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) >= acquisitionData.get(acquisitionDataTemp)
							&& emissionData.get(acquisitionDataTemp) <= 5 * acquisitionData.get(acquisitionDataTemp)) {
						dataSource2.add(ARRÊTS_À_USAGE_NORMAL, 1);
					}
				}
			}
		}

		return dataSource2;
	}

	protected DRDataSource createDataSourceRapport3() {

		DRDataSource dataSource3 = new DRDataSource("item", "count");
		Page<WSGenericMetierEntity> dataEmission = elasticsearchBC.findforRapport4(false, false, true, startDate, endDate);
		Page<WSGenericMetierEntity> dataAcquisition = elasticsearchBC.findforRapport4(false, true, true, startDate, endDate);
		Map<String, Integer> acquisitionData = new HashMap<>();
		Map<String, Integer> emissionData = new HashMap<>();
		if (dataAcquisition != null && dataEmission != null) {
			remplirMapLigne(dataEmission, dataAcquisition, acquisitionData, emissionData);

			for (String acquisitionDataTemp : acquisitionData.keySet()) {
				boolean flagKO = false;
				if (acquisitionDataTemp != null) {
					if (emissionData.containsKey(acquisitionDataTemp)) {
						flagKO = true;
					}
					if (!flagKO) {
						//absent en emission
						dataSource3.add(LIGNES_TOTALEMENT_ABSENTES, 1);
					}
					if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) < acquisitionData.get(acquisitionDataTemp)) {
						//ligne peu sollicité côté Emission (nombre d'apparitions côté Emission < nombre d'apparitions côté Acquisition) ;
						dataSource3.add(LIGNES_PEU_SOLLICITÉES, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) > 5 * acquisitionData.get(acquisitionDataTemp)) {
						//ligne très sollicité côté Emission (nombre d'apparitions côté Emission > 5*nombre d'apparitions côté Acquisition).
						dataSource3.add(LIGNES_TRÈS_SOLLICITÉES, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) >= acquisitionData.get(acquisitionDataTemp)
							&& emissionData.get(acquisitionDataTemp) <= 5 * acquisitionData.get(acquisitionDataTemp)) {
						//ligne en usage normal (tout le reste);
						dataSource3.add(LIGNES_À_USAGE_NORMAL, 1);
					}
				}
			}
		}
		return dataSource3;
	}

	protected DRDataSource createDataSourceRapport4() {

		DRDataSource dataSource4 = new DRDataSource("item", "count");
		Page<WSGenericMetierEntity> dataEmission = elasticsearchBC.findforRapport4(false, false, false, startDate, endDate);
		Page<WSGenericMetierEntity> dataAcquisition = elasticsearchBC.findforRapport4(false, true, false, startDate, endDate);
		Map<String, Integer> acquisitionData = new HashMap<>();
		Map<String, Integer> emissionData = new HashMap<>();
		if (dataAcquisition != null && dataEmission != null) {
			remplirMapLigne(dataEmission, dataAcquisition, acquisitionData, emissionData);

			for (String acquisitionDataTemp : acquisitionData.keySet()) {
				boolean flagKO = false;
				if (acquisitionDataTemp != null) {
					if (emissionData.containsKey(acquisitionDataTemp)) {
						flagKO = true;
					}
					if (!flagKO) {
						//absent en emission
						dataSource4.add(LIGNES_TOTALEMENT_ABSENTES, 1);
					}
					if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) < acquisitionData.get(acquisitionDataTemp)) {
						//ligne peu sollicité côté Emission (nombre d'apparitions côté Emission < nombre d'apparitions côté Acquisition) ;
						dataSource4.add(LIGNES_PEU_SOLLICITÉES, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) > 5 * acquisitionData.get(acquisitionDataTemp)) {
						//ligne très sollicité côté Emission (nombre d'apparitions côté Emission > 5*nombre d'apparitions côté Acquisition).
						dataSource4.add(LIGNES_TRÈS_SOLLICITÉES, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) >= acquisitionData.get(acquisitionDataTemp)
							&& emissionData.get(acquisitionDataTemp) <= 5 * acquisitionData.get(acquisitionDataTemp)) {
						//ligne en usage normal (tout le reste);
						dataSource4.add(LIGNES_À_USAGE_NORMAL, 1);
					}
				}
			}
		}
		return dataSource4;
	}

	protected DRDataSource createDataSourceRapport1p() {

		DRDataSource dataSource1 = new DRDataSource("item", "count");

		Page<WSGenericMetierEntity> dataEmission = elasticsearchBC.findforRapport4(true, false, false, startDate, endDate);
		Page<WSGenericMetierEntity> dataAcquisition = elasticsearchBC.findforRapport4(true, true, true, startDate, endDate);
		Map<String, Integer> acquisitionData = new HashMap<>();
		Map<String, Integer> emissionData = new HashMap<>();
		if (dataAcquisition != null && dataEmission != null) {
			remplirMapArret(dataEmission, dataAcquisition, acquisitionData, emissionData);

			for (String acquisitionDataTemp : acquisitionData.keySet()) {
				boolean flagKO = false;
				if (acquisitionDataTemp != null) {
					if (emissionData.containsKey(acquisitionDataTemp)) {
						flagKO = true;
					}
					if (!flagKO) {
						//absent en emission
						dataSource1.add(ARRÊTS_TOTALEMENT_ABSENTS, 1);
					}
					if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) < acquisitionData.get(acquisitionDataTemp)) {
						//arrêt peu sollicité côté Emission (nombre d'apparitions côté Emission < nombre d'apparitions côté Acquisition) ;
						dataSource1.add(ARRÊTS_PEU_SOLLICITÉS, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) > 5 * acquisitionData.get(acquisitionDataTemp)) {
						//arrêt très sollicité côté Emission (nombre d'apparitions côté Emission > 5*nombre d'apparitions côté Acquisition).
						dataSource1.add(ARRÊTS_TRÈS_SOLLICITÉS, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) >= acquisitionData.get(acquisitionDataTemp)
							&& emissionData.get(acquisitionDataTemp) <= 5 * acquisitionData.get(acquisitionDataTemp)) {
						//arrêt en usage normal (tout le reste);
						dataSource1.add(ARRÊTS_À_USAGE_NORMAL, 1);
					}
				}
			}
		}

		return dataSource1;
	}

	protected DRDataSource createDataSourceRapport2p() {
		DRDataSource dataSource2 = new DRDataSource("item", "count");
		Page<WSGenericMetierEntity> dataEmission = elasticsearchBC.findforRapport4(true, false, true, startDate, endDate);
		Page<WSGenericMetierEntity> dataAcquisition = elasticsearchBC.findforRapport4(true, true, false, startDate, endDate);
		Map<String, Integer> acquisitionData = new HashMap<>();
		Map<String, Integer> emissionData = new HashMap<>();
		if (dataAcquisition != null && dataEmission != null) {
			remplirMapArret(dataEmission, dataAcquisition, acquisitionData, emissionData);

			for (String acquisitionDataTemp : acquisitionData.keySet()) {
				boolean flagKO = false;
				if (acquisitionDataTemp != null) {
					if (emissionData.containsKey(acquisitionDataTemp)) {
						flagKO = true;
					}
					if (!flagKO) {
						//absent en emission
						dataSource2.add(ARRÊTS_TOTALEMENT_ABSENTS, 1);
					}
					if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) < acquisitionData.get(acquisitionDataTemp)) {
						//arrêt peu sollicité côté Emission (nombre d'apparitions côté Emission < nombre d'apparitions côté Acquisition) ;
						dataSource2.add(ARRÊTS_PEU_SOLLICITÉS, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) > 5 * acquisitionData.get(acquisitionDataTemp)) {
						//arrêt très sollicité côté Emission (nombre d'apparitions côté Emission > 5*nombre d'apparitions côté Acquisition).
						dataSource2.add(ARRÊTS_TRÈS_SOLLICITÉS, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) >= acquisitionData.get(acquisitionDataTemp)
							&& emissionData.get(acquisitionDataTemp) <= 5 * acquisitionData.get(acquisitionDataTemp)) {
						dataSource2.add(ARRÊTS_À_USAGE_NORMAL, 1);
					}
				}
			}
		}

		return dataSource2;
	}

	protected DRDataSource createDataSourceRapport3p() {

		DRDataSource dataSource3 = new DRDataSource("item", "count");
		Page<WSGenericMetierEntity> dataEmission = elasticsearchBC.findforRapport4(false, false, false, startDate, endDate);
		Page<WSGenericMetierEntity> dataAcquisition = elasticsearchBC.findforRapport4(false, true, true, startDate, endDate);
		Map<String, Integer> acquisitionData = new HashMap<>();
		Map<String, Integer> emissionData = new HashMap<>();
		if (dataAcquisition != null && dataEmission != null) {
			remplirMapLigne(dataEmission, dataAcquisition, acquisitionData, emissionData);

			for (String acquisitionDataTemp : acquisitionData.keySet()) {
				boolean flagKO = false;
				if (acquisitionDataTemp != null) {
					if (emissionData.containsKey(acquisitionDataTemp)) {
						flagKO = true;
					}
					if (!flagKO) {
						//absent en emission
						dataSource3.add(LIGNES_TOTALEMENT_ABSENTES, 1);
					}
					if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) < acquisitionData.get(acquisitionDataTemp)) {
						//ligne peu sollicité côté Emission (nombre d'apparitions côté Emission < nombre d'apparitions côté Acquisition) ;
						dataSource3.add(LIGNES_PEU_SOLLICITÉES, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) > 5 * acquisitionData.get(acquisitionDataTemp)) {
						//ligne très sollicité côté Emission (nombre d'apparitions côté Emission > 5*nombre d'apparitions côté Acquisition).
						dataSource3.add(LIGNES_TRÈS_SOLLICITÉES, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) >= acquisitionData.get(acquisitionDataTemp)
							&& emissionData.get(acquisitionDataTemp) <= 5 * acquisitionData.get(acquisitionDataTemp)) {
						//ligne en usage normal (tout le reste);
						dataSource3.add(LIGNES_À_USAGE_NORMAL, 1);
					}
				}
			}
		}
		return dataSource3;
	}

	protected DRDataSource createDataSourceRapport4p() {

		DRDataSource dataSource4 = new DRDataSource("item", "count");
		Page<WSGenericMetierEntity> dataEmission = elasticsearchBC.findforRapport4(false, false, true, startDate, endDate);
		Page<WSGenericMetierEntity> dataAcquisition = elasticsearchBC.findforRapport4(false, true, false, startDate, endDate);
		Map<String, Integer> acquisitionData = new HashMap<>();
		Map<String, Integer> emissionData = new HashMap<>();
		if (dataAcquisition != null && dataEmission != null) {
			remplirMapLigne(dataEmission, dataAcquisition, acquisitionData, emissionData);

			for (String acquisitionDataTemp : acquisitionData.keySet()) {
				boolean flagKO = false;
				if (acquisitionDataTemp != null) {
					if (emissionData.containsKey(acquisitionDataTemp)) {
						flagKO = true;
					}
					if (!flagKO) {
						//absent en emission
						dataSource4.add(LIGNES_TOTALEMENT_ABSENTES, 1);
					}
					if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) < acquisitionData.get(acquisitionDataTemp)) {
						//ligne peu sollicité côté Emission (nombre d'apparitions côté Emission < nombre d'apparitions côté Acquisition) ;
						dataSource4.add(LIGNES_PEU_SOLLICITÉES, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) > 5 * acquisitionData.get(acquisitionDataTemp)) {
						//ligne très sollicité côté Emission (nombre d'apparitions côté Emission > 5*nombre d'apparitions côté Acquisition).
						dataSource4.add(LIGNES_TRÈS_SOLLICITÉES, 1);
					} else if (emissionData.get(acquisitionDataTemp) != null && acquisitionData.get(acquisitionDataTemp) != null
							&& emissionData.get(acquisitionDataTemp) >= acquisitionData.get(acquisitionDataTemp)
							&& emissionData.get(acquisitionDataTemp) <= 5 * acquisitionData.get(acquisitionDataTemp)) {
						//ligne en usage normal (tout le reste);
						dataSource4.add(LIGNES_À_USAGE_NORMAL, 1);
					}
				}
			}
		}
		return dataSource4;
	}

	/**
	 * remplirMapArret
	 *
	 * @param dataEmission
	 * @param dataAcquisition
	 * @param acquisitionData
	 * @param emissionData
	 */
	private void remplirMapArret(final Page<WSGenericMetierEntity> dataEmission, final Page<WSGenericMetierEntity> dataAcquisition,
			final Map<String, Integer> acquisitionData, final Map<String, Integer> emissionData) {
		Iterator<WSGenericMetierEntity> itAcquisition;
		Iterator<WSGenericMetierEntity> itEmission;
		itAcquisition = dataAcquisition.iterator();
		while (itAcquisition.hasNext()) {
			WSGenericMetierEntity echangeAcquisition = itAcquisition.next();
			if (echangeAcquisition.getMonitoring_ref() != null && !echangeAcquisition.getMonitoring_ref().equals("null")) {
				//on compte les occurences dans acquisition
				if (acquisitionData.containsKey(echangeAcquisition.getMonitoring_ref())) {
					acquisitionData.put(echangeAcquisition.getMonitoring_ref(),
							acquisitionData.get(echangeAcquisition.getMonitoring_ref()) + 1);

				} else {
					acquisitionData.put(echangeAcquisition.getMonitoring_ref(), 1);
				}
			}
		}
		itEmission = dataEmission.iterator();

		while (itEmission.hasNext()) {
			WSGenericMetierEntity echangeEmission = itEmission.next();
			//on compte les occurences dans emission
			if (echangeEmission.getMonitoring_ref() != null && !echangeEmission.getMonitoring_ref().equals("null")) {
				if (emissionData.containsKey(echangeEmission.getMonitoring_ref())) {
					emissionData.put(echangeEmission.getMonitoring_ref(), emissionData.get(echangeEmission.getMonitoring_ref()) + 1);

				} else {
					emissionData.put(echangeEmission.getMonitoring_ref(), 1);
				}
			}
		}
	}

	/**
	 * remplirMapLigne
	 *
	 * @param dataEmission
	 * @param dataAcquisition
	 * @param acquisitionData
	 * @param emissionData
	 */
	private void remplirMapLigne(final Page<WSGenericMetierEntity> dataEmission, final Page<WSGenericMetierEntity> dataAcquisition,
			final Map<String, Integer> acquisitionData, final Map<String, Integer> emissionData) {
		Iterator<WSGenericMetierEntity> itAcquisition;
		Iterator<WSGenericMetierEntity> itEmission;
		itAcquisition = dataAcquisition.iterator();
		while (itAcquisition.hasNext()) {
			WSGenericMetierEntity echangeAcquisition = itAcquisition.next();
			//on compte les occurences dans acquisition
			if (echangeAcquisition.getLine_ref() != null && !echangeAcquisition.getLine_ref().equals("null")) {
				if (acquisitionData.containsKey(echangeAcquisition.getLine_ref())) {
					acquisitionData.put(echangeAcquisition.getLine_ref(), acquisitionData.get(echangeAcquisition.getLine_ref()) + 1);

				} else {
					acquisitionData.put(echangeAcquisition.getLine_ref(), 1);
				}
			}
		}
		itEmission = dataEmission.iterator();

		while (itEmission.hasNext()) {
			WSGenericMetierEntity echangeEmission = itEmission.next();
			//on compte les occurences dans emission
			if (echangeEmission.getLine_ref() != null && !echangeEmission.getLine_ref().equals("null")) {
				if (emissionData.containsKey(echangeEmission.getLine_ref())) {
					emissionData.put(echangeEmission.getLine_ref(), emissionData.get(echangeEmission.getLine_ref()) + 1);

				} else {
					emissionData.put(echangeEmission.getLine_ref(), 1);
				}
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
		return ReferenceRapport.RAPPORT_4.name();
	}
}
