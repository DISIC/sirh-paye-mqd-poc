package com.sirh.mqd.reporting.core.generation;

import static net.sf.dynamicreports.report.builder.DynamicReports.asc;
import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.Arrays;
import java.util.Iterator;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.chart.TimeSeriesChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.datasource.DRDataSource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.exchanges.enums.EnumErreursMetier;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.enums.ReferenceRapport;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;

/**
 * Classe gérant la création du dashboard 8
 *
 * @see doc.story.ref1646
 *
 */
@Component
public class Rapport8 extends AbstractRapport {

	private final String TITLE = "Accès diffuseur";

	private final String FOOTER_LIBELLE = "Accès diffuseur non autorisé";

	private final String VALUE_AXIS_TITLE = "Nb d'erreur";

	private final TimePeriod timePeriod = TimePeriod.HOUR;

	private static final Boolean SHOW_SHAPES = true;

	public Rapport8() {

	}

	@Override
	protected void buildRapport(final JasperReportBuilder report) {

		final TextColumnBuilder<java.util.Date> dateColumn = col.column("Date", "date", type.dateType());
		final TextColumnBuilder<String> weekColumn = col.column("Semaine", "Week", type.stringType());
		final TextColumnBuilder<String> producerColumn = col.column("Producteur", "Producer", type.stringType());

		final TimeSeriesChartBuilder lineChart = cht.timeSeriesChart();
		lineChart.setTitle("Accès diffuseur");
		lineChart.setSubtitle("");
		lineChart.setTitleFont(boldFont);
		lineChart.setTimePeriod(dateColumn);
		lineChart.setTimePeriodType(timePeriod);
		lineChart.setShowShapes(SHOW_SHAPES);
		lineChart.series(cht.serie(col.column("Arrêt non autorisé ", "AccesNotAllowedErrorReceivedCount", type.integerType())));
		lineChart.series(cht.serie(col.column("Demande sur son propre périmètre ", "AccesNotAllowedErrorOwnedZoneReceivedCount",
				type.integerType())));

		lineChart.setTimeAxisFormat(cht.axisFormat().setLabel("Date"));
		lineChart.setValueAxisFormat(cht.axisFormat().setLabel(VALUE_AXIS_TITLE));

		report.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);

		report.sortBy(asc(producerColumn), asc(dateColumn));

		report.groupBy(grp.group(producerColumn), grp.group(weekColumn).footer(lineChart));

		report.setDataSource(dataSource);
	}

	@Override
	protected void createDataSourceRapport() {

		dataSource = new DRDataSource(
				//
				"Week",
				//
				"Producer",
				//
				"AccesNotAllowedErrorOwnedZoneReceivedCount",
				//
				"AccesNotAllowedErrorReceivedCount",
				// date du log
				"date");

		final Page<WSGenericMetierEntity> dataAccesNotAllowedErrorReceived = elasticsearchBC.findByErreurMetier("Sens_reception",
				Arrays.asList(EnumErreursMetier.IVTR_ERR_90, EnumErreursMetier.IVTR_ERR_39, EnumErreursMetier.IVTR_ERR_77), startDate,
				endDate);

		final Iterator<WSGenericMetierEntity> itAccesNotAllowedErrorReceived = dataAccesNotAllowedErrorReceived.iterator();

		while (itAccesNotAllowedErrorReceived.hasNext()) {
			final WSGenericMetierEntity echange = itAccesNotAllowedErrorReceived.next();

			if (echange.getProducer_ref().equals("null")) {
				continue;
			}

			// Si c'est l'erreur metier 77
			if (echange.getCode_erreur_ivtr().toUpperCase().equals(EnumErreursMetier.IVTR_ERR_77.name().toUpperCase())) {
				dataSource.add(String.valueOf("Semaine " + DateUtils.toWeekOfYear(echange.getLog_date())), echange.getProducer_ref(), 1, 0,
						DateUtils.toDate(echange.getLog_date()));
			} else {
				// Sinon si c'est l'erreur metier 90 ou 39
				dataSource.add(String.valueOf("Semaine " + DateUtils.toWeekOfYear(echange.getLog_date())), echange.getProducer_ref(), 0, 1,
						DateUtils.toDate(echange.getLog_date()));
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
		return ReferenceRapport.RAPPORT_8.name();
	}

}
