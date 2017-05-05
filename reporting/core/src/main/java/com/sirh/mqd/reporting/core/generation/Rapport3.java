package com.sirh.mqd.reporting.core.generation;

import static net.sf.dynamicreports.report.builder.DynamicReports.asc;
import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.chart.AxisFormatBuilder;
import net.sf.dynamicreports.report.builder.chart.TimeSeriesChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.datasource.DRDataSource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.exchanges.enums.EnumErrorCondition;
import com.sirh.mqd.commons.exchanges.enums.EnumService;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.enums.ReferenceRapport;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;

/**
 * Classe gérant la création du dashboard 3. Ce dashboard comporte deux parties
 *
 * @see Rapport3
 * @see doc.story.ref1625
 *
 */
@Component
public class Rapport3 extends AbstractRapport {

	private final String TITLE = "Rapport d'erreurs";

	private final String FOOTER_LIBELLE = "Rapport par type d'erreur";

	private final String VALUE_AXIS_TITLE = "Nb d'erreur";

	private final TimePeriod timePeriod = TimePeriod.HOUR;

	private static final Boolean SHOW_SHAPES = true;

	/**
	 * The columns count. Directly linked to {@link AbstractRapport#dataSource} construction : each Rapport has its own. 18 data columns + 2
	 * about dates (week and date) + 1 for the Error name. useful data will be between dataSource.column[2] and dataSource.column[16]
	 */
	private final DataSourceFormat dataSourceSize = new DataSourceFormat(18, 2, 16);

	public Rapport3() {
		NULL_DATA_SWITCH = true;
	}

	@Override
	protected void buildRapport(final JasperReportBuilder report) {

		final TextColumnBuilder<java.util.Date> dateColumn = col.column("Date", "date", type.dateType());
		final TextColumnBuilder<String> erreurColumn = col.column("Erreur", "Erreur", type.stringType());

		final TimeSeriesChartBuilder lineChart = cht.timeSeriesChart();
		//		lineChart.setTitle("Erreurs");
		lineChart.setSubtitle("");
		lineChart.setTitleFont(boldFont);
		lineChart.setTimePeriod(dateColumn);
		lineChart.setTimePeriodType(timePeriod);
		lineChart.setShowShapes(SHOW_SHAPES);

		/*
		 * Erreurs par errorCondition
		 */

		lineChart
		.series(cht.serie(col.column(EnumErrorCondition.ACCESS_NOT_ALLOWED_ERROR.getErrorConditionStructure(), type.integerType())));
		lineChart.series(cht.serie(col.column(EnumErrorCondition.ALLOWED_RESOURCE_USAGE_EXCEEDED_ERROR.getErrorConditionStructure(),
				type.integerType())));
		lineChart.series(cht.serie(col.column("Dépassement de période ",
				EnumErrorCondition.BEYOND_DATA_HORIZON_ERROR.getErrorConditionStructure(), type.integerType())));
		lineChart.series(cht.serie(col.column("Fonctionnalité non supportée ",
				EnumErrorCondition.CAPABILITY_NOT_SUPPORTED_ERROR.getErrorConditionStructure(), type.integerType())));
		lineChart.series(cht.serie(col.column("Identifiant inconnu ",
				EnumErrorCondition.INVALID_DATA_REFERENCES_ERROR.getErrorConditionStructure(), type.integerType())));
		lineChart.series(cht.serie(col.column("Données non disponibles ",
				EnumErrorCondition.NO_INFO_FOR_TOPIC_ERROR.getErrorConditionStructure(), type.integerType())));
		lineChart.series(cht.serie(col.column("Autres erreurs ", EnumErrorCondition.OTHER_ERROR.getErrorConditionStructure(),
				type.integerType())));
		lineChart.series(cht.serie(col.column("Paramètres ignorés ",
				EnumErrorCondition.PARAMETERS_IGNORED_ERROR.getErrorConditionStructure(), type.integerType())));
		lineChart.series(cht.serie(col.column("Indisponibilité du service ",
				EnumErrorCondition.SERVICE_NOTAVAILABLE_ERROR.getErrorConditionStructure(), type.integerType())));
		lineChart.series(cht.serie(col.column("Extension ignorée ",
				EnumErrorCondition.UNKNOWN_EXTENSIONS_ERROR.getErrorConditionStructure(), type.integerType())));

		/*
		 * Erreurs par service Siri
		 */
		lineChart.series(cht.serie(col.column("CheckStatus ", EnumService.CheckStatus.getServiceName(), type.integerType())));
		lineChart.series(cht.serie(col.column("Subscribe ", EnumService.Subscribe.getServiceName(), type.integerType())));
		lineChart.series(cht.serie(col.column("StopMonitoring ", EnumService.GetStopMonitoring.getServiceName(), type.integerType())));
		lineChart.series(cht.serie(col.column("GeneralMessage ", EnumService.GetGeneralMessage.getServiceName(), type.integerType())));
		lineChart
				.series(cht.serie(col.column("EstimatedTimetable ", EnumService.GetEstimatedTimetable.getServiceName(), type.integerType())));

		final AxisFormatBuilder timeAxis = cht.axisFormat().setLabel("Date");
		if (TIME_TICK_MASK != null) {
			timeAxis.setTickLabelMask(TIME_TICK_MASK);
		}
		lineChart.setTimeAxisFormat(timeAxis);
		lineChart.setValueAxisFormat(cht.axisFormat().setLabel(VALUE_AXIS_TITLE));
		lineChart.setShowLegend(false);

		report.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);

		report.sortBy(asc(erreurColumn));

		report.groupBy(grp.group(erreurColumn).footer(lineChart));

		report.setDataSource(dataSource);

	}

	@Override
	protected void createDataSourceRapport() {

		dataSource = new DRDataSource(
				//
				"Week",
				//
				"Erreur",
				//
				EnumErrorCondition.ACCESS_NOT_ALLOWED_ERROR.getErrorConditionStructure(),
				//
				EnumErrorCondition.ALLOWED_RESOURCE_USAGE_EXCEEDED_ERROR.getErrorConditionStructure(),
				//
				EnumErrorCondition.BEYOND_DATA_HORIZON_ERROR.getErrorConditionStructure(),
				//
				EnumErrorCondition.CAPABILITY_NOT_SUPPORTED_ERROR.getErrorConditionStructure(),
				//
				EnumErrorCondition.INVALID_DATA_REFERENCES_ERROR.getErrorConditionStructure(),
				//
				EnumErrorCondition.NO_INFO_FOR_TOPIC_ERROR.getErrorConditionStructure(),
				//
				EnumErrorCondition.OTHER_ERROR.getErrorConditionStructure(),
				//
				EnumErrorCondition.PARAMETERS_IGNORED_ERROR.getErrorConditionStructure(),
				//
				EnumErrorCondition.SERVICE_NOTAVAILABLE_ERROR.getErrorConditionStructure(),
				//
				EnumErrorCondition.UNKNOWN_EXTENSIONS_ERROR.getErrorConditionStructure(),
				//
				EnumService.CheckStatus.getServiceName(),
				//
				EnumService.Subscribe.getServiceName(),
				//
				EnumService.GetStopMonitoring.getServiceName(),
				//
				EnumService.GetGeneralMessage.getServiceName(),
				//
				EnumService.GetEstimatedTimetable.getServiceName(),
				// date du log
				"date");

		final Date minDataDate = new Date(0L);
		final Date maxDataDate = new Date(0L);

		/*
		 * Get ErrorCondition data
		 */

		for (final EnumErrorCondition error : EnumErrorCondition.values()) {

			final Page<WSGenericMetierEntity> data = elasticsearchBC.findByErreurCondition(Arrays.asList(error), startDate, endDate);

			final Iterator<WSGenericMetierEntity> iterator = data.iterator();

			while (iterator.hasNext()) {
				final WSGenericMetierEntity echange = iterator.next();
				final Date echangeDate = DateUtils.toDate(echange.getLog_date());
				DateUtils.compareDatesBoundaries(echangeDate, minDataDate, maxDataDate);

				switch (error) {
					case ACCESS_NOT_ALLOWED_ERROR:
						addData(echange, error, dataSourceSize.getUsefulDataStartIndex() + 0);
						break;

					case ALLOWED_RESOURCE_USAGE_EXCEEDED_ERROR:
						addData(echange, error, dataSourceSize.getUsefulDataStartIndex() + 1);
						break;

					case BEYOND_DATA_HORIZON_ERROR:
						addData(echange, error, dataSourceSize.getUsefulDataStartIndex() + 2);
						break;

					case CAPABILITY_NOT_SUPPORTED_ERROR:
						addData(echange, error, dataSourceSize.getUsefulDataStartIndex() + 3);
						break;

					case INVALID_DATA_REFERENCES_ERROR:
						addData(echange, error, dataSourceSize.getUsefulDataStartIndex() + 4);
						break;

					case NO_INFO_FOR_TOPIC_ERROR:
						addData(echange, error, dataSourceSize.getUsefulDataStartIndex() + 5);
						break;

					case OTHER_ERROR:
						addData(echange, error, dataSourceSize.getUsefulDataStartIndex() + 6);
						break;

					case PARAMETERS_IGNORED_ERROR:
						addData(echange, error, dataSourceSize.getUsefulDataStartIndex() + 7);
						break;

					case SERVICE_NOTAVAILABLE_ERROR:
						addData(echange, error, dataSourceSize.getUsefulDataStartIndex() + 8);
						break;

					case UNKNOWN_EXTENSIONS_ERROR:
						addData(echange, error, dataSourceSize.getUsefulDataStartIndex() + 9);
						break;
					default:
						throw new IllegalArgumentException("Unsupported Error Condition in Report 3");
				}

			}
		}

		/*
		 * Get Error by Siri Service data
		 */

		final Page<WSGenericMetierEntity> data = elasticsearchBC.findByErreurConditionAndService(
				Arrays.asList(EnumErrorCondition.values()), Arrays.asList(EnumService.values()), startDate, endDate);
		//		final Page<WSGenericMetierEntity> data = elasticsearchBC.findByErreurConditionAndService(null, Arrays.asList(EnumService.values()),
		//				startDate, endDate);

		final Iterator<WSGenericMetierEntity> iterator = data.iterator();

		while (iterator.hasNext()) {
			final WSGenericMetierEntity echange = iterator.next();
			final Date echangeDate = DateUtils.toDate(echange.getLog_date());
			DateUtils.compareDatesBoundaries(echangeDate, minDataDate, maxDataDate);

			if (echange.getService().equals(EnumService.CheckStatus.getServiceName())) {
				addData(echange, dataSourceSize.getUsefulDataStartIndex() + 10);
			}
			if (echange.getService().equals(EnumService.Subscribe.getServiceName())) {
				addData(echange, dataSourceSize.getUsefulDataStartIndex() + 11);
			}
			if (echange.getService().equals(EnumService.GetStopMonitoring.getServiceName())) {
				addData(echange, dataSourceSize.getUsefulDataStartIndex() + 12);
			}
			if (echange.getService().equals(EnumService.GetGeneralMessage.getServiceName())) {
				addData(echange, dataSourceSize.getUsefulDataStartIndex() + 13);
			}
			if (echange.getService().equals(EnumService.GetEstimatedTimetable.getServiceName())) {
				addData(echange, dataSourceSize.getUsefulDataStartIndex() + 14); // dataSourceSize.getUsefulDataStartIndex() + 14 == dataSourceSize.getUsefulDataEndIndex()
			}

		}

		defineTimeTickMask(minDataDate, maxDataDate);

	}

	/**
	 * @param echange
	 *            l'entité de la donnée. utilisée pour la date et le nom de l'erreur
	 *
	 * @param columnIndex
	 *            the index of the current data to be indicated. Column at this index should be 1, whereas the others should be 0 or null.
	 */
	private void addData(final WSGenericMetierEntity echange, final int columnIndex) {
		addData(echange, null, columnIndex);
	}

	/**
	 * @param echange
	 *            l'entité de la donnée. utilisée pour la date et le nom de l'erreur
	 *
	 * @param error
	 *            errorCondition (à faire apparaître dans la 2e colonne de {@link AbstractRapport#dataSource})
	 * @param columnIndex
	 *            the index of the current data to be indicated. Column at this index should be 1, whereas the others should be 0 or null.
	 */
	private void addData(final WSGenericMetierEntity echange, final EnumErrorCondition error, final int columnIndex) {

		if (columnIndex < dataSourceSize.getUsefulDataStartIndex() || columnIndex > dataSourceSize.getUsefulDataEndIndex()) { // column index must be between start and end index of useful data columns
			throw new IllegalArgumentException("Check dataSource construction and dataSourceSize parameter");
		}
		dataSource.add(columnsAsArray(echange, error, columnIndex));
	}

	/**
	 * on ajoute des données à dataSource en lui passant plusieurs paramètres correspondant aux colonnes prédéfinies. Cette méthode renvoit
	 * un Array, plus facile à générer à la place des nombreux paramètres.
	 *
	 * @param echange
	 *            l'entité de la donnée. utilisée pour la date et le nom de l'erreur
	 * @param error
	 *            l'errorCondition de la donnée
	 * @param columnIndex
	 *            l'index de la colonne avec la donnée (flag=1), les autres colonnes devraient être à 0 ou null. L'index est celui de la
	 *            colonne de dataSource, en incluant les "non-useful" colonnes, c'est-à-dire que toutes les colonnes (week/dates) comptent.
	 * @return the array of objects to pass into dataSource, "non-useful" data included (week/date)
	 */
	private Object[] columnsAsArray(final WSGenericMetierEntity echange, final EnumErrorCondition error, final int columnIndex) {
		final Object[] columnRepartition = new Object[dataSourceSize.getDataSourceSize()];
		Arrays.fill(columnRepartition, NULL_DATA_SWITCH ? null : 0); // 0 ou null sur toutes les colonnes
		columnRepartition[0] = String.valueOf("Semaine " + DateUtils.toWeekOfYear(echange.getLog_date())); // la première colonne indique la semaine
		if (null == error) {
			// dans ce cas, les données sont les services Siri
			columnRepartition[1] = echange.getService();
		} else {
			// dans ce cas, les données sont des errorCondition
			columnRepartition[1] = error.getErrorConditionStructure();
		}
		columnRepartition[columnIndex] = 1; // la donnée utile
		columnRepartition[dataSourceSize.getDataSourceSize() - 1] = DateUtils.toDate(echange.getLog_date()); // la dernière colonne indique la date
		return columnRepartition;
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
		return ReferenceRapport.RAPPORT_3.name();
	}

}
