package com.sirh.mqd.reporting.core.generation;

import static net.sf.dynamicreports.report.builder.DynamicReports.asc;
import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.chart.TimeSeriesChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.datasource.DRDataSource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.exchanges.enums.EnumService;
import com.sirh.mqd.commons.exchanges.enums.ModeEchange;
import com.sirh.mqd.commons.exchanges.enums.ModuleEnum;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.enums.ReferenceRapport;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;

/**
 * Classe gérant la création du dashboard 6
 *
 * @see doc.story.ref1639
 *
 */
@Component
public class Rapport6 extends AbstractRapport {

	private final String TITLE = "Disponibilité des partenaires producteur";

	private final String FOOTER_LIBELLE = "Disponibilité des partenaires producteur";

	private final String VALUE_AXIS_TITLE = "Taux de disponniblité";

	private final TimePeriod timePeriod = TimePeriod.HOUR;

	private static final Boolean SHOW_SHAPES = false;

	public Rapport6() {

	}

	@Override
	protected void buildRapport(final JasperReportBuilder report) {

		TextColumnBuilder<java.util.Date> dateColumn = col.column("Date", "date", type.dateType());
		TextColumnBuilder<String> weekColumn = col.column("Semaine", "Week", type.stringType());
		TextColumnBuilder<String> producerColumn = col.column("Producteur", "Producer", type.stringType());

		TimeSeriesChartBuilder lineChartCS = cht.timeSeriesChart();
		lineChartCS.setTitle("Disponibilité de partenaire producteur ");
		lineChartCS.setSubtitle("");
		lineChartCS.setTitleFont(boldFont);
		lineChartCS.setTimePeriod(dateColumn);
		lineChartCS.setTimePeriodType(timePeriod);
		lineChartCS.setShowShapes(SHOW_SHAPES);
		lineChartCS.series(cht.serie(col.column("CheckStatus", "CheckStatusRequeteReceivedCount", type.integerType())));
		lineChartCS.setTimeAxisFormat(cht.axisFormat().setLabel("Date"));
		lineChartCS.setValueAxisFormat(cht.axisFormat().setLabel(VALUE_AXIS_TITLE));
		lineChartCS.setPercentValuePattern("#0.00%");

		report.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);

		report.sortBy(asc(producerColumn), asc(dateColumn));

		report.groupBy(grp.group(producerColumn), grp.group(weekColumn).footer(lineChartCS));

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
				"CheckStatusRequeteReceivedCount",
				// date du log
				"date");

		Set<String> producer = new HashSet<String>();

		Set<Date> date = new HashSet<Date>();

		int hours = DateUtils.hoursBetween(startDate, endDate);

		Calendar cStart = DateUtils.getCalendarInstance();
		cStart.clear();
		cStart.setTime(startDate);
		cStart.set(Calendar.MILLISECOND, 0);
		cStart.set(Calendar.SECOND, 0);
		cStart.set(Calendar.MINUTE, 0);

		Calendar cEnd = DateUtils.getCalendarInstance();
		cEnd.clear();
		cEnd.setTime(startDate);
		cEnd.set(Calendar.MILLISECOND, 0);
		cEnd.set(Calendar.SECOND, 0);
		cEnd.set(Calendar.MINUTE, 0);

		// on ajoute 1h de décalage dès le debut
		cEnd.add(Calendar.HOUR, 1);

		// on fait une recherche heure par heure
		for (int i = 0; i < hours; i++) {

			//			System.out.println(cStart.getTime() + " ---- " + cEnd.getTime());

			// CheckStatus OK
			Page<WSGenericMetierEntity> dataGetSiriAcquisitionRequeteReceivedOk = elasticsearchBC.findByServiceAndSensAndStatus(
					ModeEchange.REQUETE, ModuleEnum.ACQUISITION, EnumService.CheckStatus.getServiceName(), "Sens_reception", Boolean.TRUE,
					cStart.getTime(), cEnd.getTime());

			// CheckStatus KO
			Page<WSGenericMetierEntity> dataGetSiriAcquisitionRequeteReceivedKo = elasticsearchBC.findByServiceAndSensAndStatus(
					ModeEchange.REQUETE, ModuleEnum.ACQUISITION, EnumService.CheckStatus.getServiceName(), "Sens_reception", Boolean.FALSE,
					cStart.getTime(), cEnd.getTime());

			int totalOk = dataGetSiriAcquisitionRequeteReceivedOk.getNumberOfElements();
			int totalKo = dataGetSiriAcquisitionRequeteReceivedKo.getNumberOfElements();
			int total = totalOk + totalKo;

			int tauxDispo = 0;

			if (total > 0) {
				float d = ((float) totalOk / (float) total) * 100;
				if (totalOk != total) {
					System.out.println(d);
				}
				tauxDispo = (int) Math.floor(d);
			}

			Iterator<WSGenericMetierEntity> itGetSiriAcquisitionRequeteReceived = dataGetSiriAcquisitionRequeteReceivedOk.iterator();

			// si aucune log n'est present, on stock la date
			if (!itGetSiriAcquisitionRequeteReceived.hasNext()) {
				date.add(cStart.getTime());
			}

			Set<String> prod = new HashSet<String>();

			while (itGetSiriAcquisitionRequeteReceived.hasNext()) {

				WSGenericMetierEntity echange = itGetSiriAcquisitionRequeteReceived.next();

				if (!prod.contains(echange.getProducer_ref())) {
					prod.add(echange.getProducer_ref());
					dataSource.add(String.valueOf("Semaine " + cStart.get(Calendar.WEEK_OF_YEAR)), echange.getProducer_ref(), tauxDispo,
							cStart.getTime());
				}

				producer.add(echange.getProducer_ref());
			}

			cEnd.add(Calendar.HOUR, 1);
			cStart.add(Calendar.HOUR, 1);

		}

		// Pour chaque partenaire, on set à 0 la valeur pour la date sans log
		for (String prod : producer) {

			for (Date logDate : date) {
				Calendar c = DateUtils.getCalendarInstance();
				c.setTime(logDate);
				dataSource.add(String.valueOf("Semaine " + c.get(Calendar.WEEK_OF_YEAR)), prod, 0, c.getTime());

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
		return ReferenceRapport.RAPPORT_6.name();
	}

}
