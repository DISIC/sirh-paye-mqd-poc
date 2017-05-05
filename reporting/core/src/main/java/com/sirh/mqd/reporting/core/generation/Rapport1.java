package com.sirh.mqd.reporting.core.generation;

import static net.sf.dynamicreports.report.builder.DynamicReports.asc;
import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.chart.AxisFormatBuilder;
import net.sf.dynamicreports.report.builder.chart.TimeSeriesChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.datasource.DRDataSource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.exchanges.enums.EnumService;
import com.sirh.mqd.commons.exchanges.enums.ModeEchange;
import com.sirh.mqd.commons.exchanges.enums.ModuleEnum;
import com.sirh.mqd.commons.exchanges.factory.LogFactory;
import com.sirh.mqd.commons.traces.api.IFacadeLogs;
import com.sirh.mqd.commons.traces.logs.FacadeLogFactory;
import com.sirh.mqd.commons.traces.logs.LogWorkflowFactory;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.enums.ReferenceRapport;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;

/**
 * Classe gérant la création du dashboard 1
 *
 * @see doc.story.ref1155
 *
 *      Attention : Le dashboard 9 est issu du 1 : Rapport9 extends Rapport1
 *
 */
@Component
public class Rapport1 extends AbstractRapport {

	protected String TITLE = "Volumétrie en nombre de messages";

	protected String FOOTER_LIBELLE = "Rapport volumétrie";

	protected String VALUE_AXIS_TITLE = "Nb de message";

	protected TimePeriod timePeriod = TimePeriod.HOUR;

	private static final IFacadeLogs LOG = FacadeLogFactory.getLogger(Rapport1.class);

	protected Boolean SHOW_SHAPES = true;

	/**
	 * Parameter for easily grouping data by graph (more meaningful than grouping by week)<br/>
	 * STIF-344
	 */
	private static final String EXCHANGE_TYPE = "ExchangeType";

	/**
	 * Nom de la colonne au sens DynamicReport, qui compte le nombre de données.<br/>
	 * Chaque ligne pour la génération du rapport 1 aura pour "count : 1<br/>
	 * Chaque ligne pour la génération du rapport 9 aura pour "count : la taille de l'échange en Ko.<br/>
	 * STIF-344
	 */
	private static final String COUNT_COLUMN = "count";

	/**
	 * Intervalle de la requête elasticSearch. On observe qu'il est plus rapide de requêter plusieurs fois pour un petit nombre de réponses,
	 * plutôt qu'une seule fois avec un nombre gigantesque de réponses.
	 */
	private static final int intervalleRequeteEnHeures = 1;

	public Rapport1() {
		NULL_DATA_SWITCH = false;
	}

	/**
	 * STIF-344
	 */
	private ColumnGroupBuilder groupChart() {
		final TextColumnBuilder<java.util.Date> dateColumn = col.column("Date", "date", type.dateType());
		final TextColumnBuilder<Integer> countColumn = col.column("Nombre d'échanges", COUNT_COLUMN, type.integerType());
		final TextColumnBuilder<String> exchangeTypeColumn = col.column("type d'échange", EXCHANGE_TYPE, type.stringType());

		final TimeSeriesChartBuilder lineChart = cht.timeSeriesChart();
		lineChart.setTitle(" ");//titre commun à tous les graphes. non nul de façon à créer un espace avec le graphe suivant
		lineChart.setSubtitle(" ");
		lineChart.setTimePeriod(dateColumn);
		lineChart.setTimePeriodType(timePeriod);
		lineChart.setShowShapes(SHOW_SHAPES);
		lineChart.series(cht.serie(countColumn).setLabel(exchangeTypeColumn.getColumn()));
		final AxisFormatBuilder timeAxis = cht.axisFormat().setLabel("Date");
		if (TIME_TICK_MASK != null) {
			timeAxis.setTickLabelMask(TIME_TICK_MASK);
		}
		lineChart.setTimeAxisFormat(timeAxis);
		lineChart.setValueAxisFormat(cht.axisFormat().setLabel(VALUE_AXIS_TITLE).setTickLabelMask("#"));
		return grp.group(exchangeTypeColumn).setHideColumn(true).footer(lineChart).setHeaderLayout(GroupHeaderLayout.EMPTY);
	}

	@Override
	protected void buildRapport(final JasperReportBuilder report) {
		final TextColumnBuilder<String> exchangeTypeColumn = col.column("type d'échange", EXCHANGE_TYPE, type.stringType());

		report.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);

		report.sortBy(asc(exchangeTypeColumn));

		report.groupBy(groupChart());

		report.setDataSource(dataSource);
	}

	@Override
	protected void createDataSourceRapport() {
		LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("*************Datasource " + TITLE + "*************"));

		dataSource = new DRDataSource("Week",
				//
				COUNT_COLUMN,
				//
				EXCHANGE_TYPE,
				// date du log
				"date");

		final Date minDataDate = new Date(0L);
		final Date maxDataDate = new Date(0L);

		/*
		 * ****************************************************************************************************************************************************************************************
		 * ************************************************************************ ACQUISITION REQUETE
		 */

		/*
		 * ****************************************************************************
		 * *************************** GetSiri ****************************************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataGetSiriAcquisitionRequeteReceived = new HashMap<String, Object>();
		dataGetSiriAcquisitionRequeteReceived.put(MODE_ECHANGE, ModeEchange.REQUETE_24);
		dataGetSiriAcquisitionRequeteReceived.put(MODULES, ModuleEnum.ACQUISITION);
		dataGetSiriAcquisitionRequeteReceived.put(ENUM_SERVICE, EnumService.GetSiri.getServiceName());
		dataGetSiriAcquisitionRequeteReceived.put(SENS, LogFactory.SENS_RECEPTION);
		dataGetSiriAcquisitionRequeteReceived.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataGetSiriAcquisitionRequeteReceived));//"GetSiriAcquisitionRequeteReceivedCount");
		addData(dataGetSiriAcquisitionRequeteReceived, minDataDate, maxDataDate);

		final Map<String, Object> dataGetSiriAcquisitionRequeteSent = new HashMap<String, Object>();
		dataGetSiriAcquisitionRequeteSent.put(MODE_ECHANGE, ModeEchange.REQUETE_24);
		dataGetSiriAcquisitionRequeteSent.put(MODULES, ModuleEnum.ACQUISITION);
		dataGetSiriAcquisitionRequeteSent.put(ENUM_SERVICE, EnumService.GetSiri.getServiceName());
		dataGetSiriAcquisitionRequeteSent.put(SENS, LogFactory.SENS_EMISSION);
		dataGetSiriAcquisitionRequeteSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataGetSiriAcquisitionRequeteSent));
		addData(dataGetSiriAcquisitionRequeteSent, minDataDate, maxDataDate);

		/*
		 * ****************************************************************************
		 * *************************** GetMultipleStopMonitoring **********************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataGMSMAcquisitionRequeteReceived = new HashMap<String, Object>();
		dataGMSMAcquisitionRequeteReceived.put(MODE_ECHANGE, ModeEchange.REQUETE_22);
		dataGMSMAcquisitionRequeteReceived.put(MODULES, ModuleEnum.ACQUISITION);
		dataGMSMAcquisitionRequeteReceived.put(ENUM_SERVICE, EnumService.GetMultipleStopMonitoring.getServiceName());
		dataGMSMAcquisitionRequeteReceived.put(SENS, LogFactory.SENS_RECEPTION);
		dataGMSMAcquisitionRequeteReceived.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataGMSMAcquisitionRequeteReceived));
		addData(dataGMSMAcquisitionRequeteReceived, minDataDate, maxDataDate);

		final Map<String, Object> dataGMSMAcquisitionRequeteSent = new HashMap<String, Object>();
		dataGMSMAcquisitionRequeteSent.put(MODE_ECHANGE, ModeEchange.REQUETE_22);
		dataGMSMAcquisitionRequeteSent.put(MODULES, ModuleEnum.ACQUISITION);
		dataGMSMAcquisitionRequeteSent.put(ENUM_SERVICE, EnumService.GetMultipleStopMonitoring.getServiceName());
		dataGMSMAcquisitionRequeteSent.put(SENS, LogFactory.SENS_EMISSION);
		dataGMSMAcquisitionRequeteSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataGMSMAcquisitionRequeteSent));
		addData(dataGMSMAcquisitionRequeteSent, minDataDate, maxDataDate);

		/*
		 * ****************************************************************************
		 * *************************** GeneralMessage *********************************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataGMAcquisitionRequeteReceived = new HashMap<String, Object>();
		dataGMAcquisitionRequeteReceived.put(MODE_ECHANGE, ModeEchange.REQUETE);
		dataGMAcquisitionRequeteReceived.put(MODULES, ModuleEnum.ACQUISITION);
		dataGMAcquisitionRequeteReceived.put(ENUM_SERVICE, EnumService.GetGeneralMessage.getServiceName());
		dataGMAcquisitionRequeteReceived.put(SENS, LogFactory.SENS_RECEPTION);
		dataGMAcquisitionRequeteReceived.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataGMAcquisitionRequeteReceived));
		addData(dataGMAcquisitionRequeteReceived, minDataDate, maxDataDate);

		final Map<String, Object> dataGMAcquisitionRequeteSent = new HashMap<String, Object>();
		dataGMAcquisitionRequeteSent.put(MODE_ECHANGE, ModeEchange.REQUETE);
		dataGMAcquisitionRequeteSent.put(MODULES, ModuleEnum.ACQUISITION);
		dataGMAcquisitionRequeteSent.put(ENUM_SERVICE, EnumService.GetGeneralMessage.getServiceName());
		dataGMAcquisitionRequeteSent.put(SENS, LogFactory.SENS_EMISSION);
		dataGMAcquisitionRequeteSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataGMAcquisitionRequeteSent));
		addData(dataGMAcquisitionRequeteSent, minDataDate, maxDataDate);

		/*
		 * ****************************************************************************
		 * *************************** StopPointDiscovery *****************************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataSPDAcquisitionRequeteReceived = new HashMap<String, Object>();
		dataSPDAcquisitionRequeteReceived.put(MODE_ECHANGE, ModeEchange.REQUETE);
		dataSPDAcquisitionRequeteReceived.put(MODULES, ModuleEnum.ACQUISITION);
		dataSPDAcquisitionRequeteReceived.put(ENUM_SERVICE, EnumService.StopPointsDiscovery.getServiceName());
		dataSPDAcquisitionRequeteReceived.put(SENS, LogFactory.SENS_RECEPTION);
		dataSPDAcquisitionRequeteReceived.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataSPDAcquisitionRequeteReceived));
		addData(dataSPDAcquisitionRequeteReceived, minDataDate, maxDataDate);

		final Map<String, Object> dataSPDAcquisitionRequeteSent = new HashMap<String, Object>();
		dataSPDAcquisitionRequeteSent.put(MODE_ECHANGE, ModeEchange.REQUETE);
		dataSPDAcquisitionRequeteSent.put(MODULES, ModuleEnum.ACQUISITION);
		dataSPDAcquisitionRequeteSent.put(ENUM_SERVICE, EnumService.StopPointsDiscovery.getServiceName());
		dataSPDAcquisitionRequeteSent.put(SENS, LogFactory.SENS_EMISSION);
		dataSPDAcquisitionRequeteSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataSPDAcquisitionRequeteSent));
		addData(dataSPDAcquisitionRequeteSent, minDataDate, maxDataDate);

		/*
		 * ****************************************************************************
		 * *************************** EstimatedTimeTable *****************************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataETAcquisitionRequeteReceived = new HashMap<String, Object>();
		dataETAcquisitionRequeteReceived.put(MODE_ECHANGE, ModeEchange.REQUETE);
		dataETAcquisitionRequeteReceived.put(MODULES, ModuleEnum.ACQUISITION);
		dataETAcquisitionRequeteReceived.put(ENUM_SERVICE, EnumService.GetEstimatedTimetable.getServiceName());
		dataETAcquisitionRequeteReceived.put(SENS, LogFactory.SENS_RECEPTION);
		dataETAcquisitionRequeteReceived.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataETAcquisitionRequeteReceived));
		addData(dataETAcquisitionRequeteReceived, minDataDate, maxDataDate);

		final Map<String, Object> dataETAcquisitionRequeteSent = new HashMap<String, Object>();
		dataETAcquisitionRequeteSent.put(MODE_ECHANGE, ModeEchange.REQUETE);
		dataETAcquisitionRequeteSent.put(MODULES, ModuleEnum.ACQUISITION);
		dataETAcquisitionRequeteSent.put(ENUM_SERVICE, EnumService.GetEstimatedTimetable.getServiceName());
		dataETAcquisitionRequeteSent.put(SENS, LogFactory.SENS_EMISSION);
		dataETAcquisitionRequeteSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataETAcquisitionRequeteSent));
		addData(dataETAcquisitionRequeteSent, minDataDate, maxDataDate);

		/*
		 * ****************************************************************************************************************************************************************************************
		 * ************************************************************************ EMISSION ** REQUETE
		 */

		/*
		 * ****************************************************************************
		 * *************************** GeneralMessage *********************************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataGMEmissionRequeteReceived = new HashMap<String, Object>();
		dataGMEmissionRequeteReceived.put(MODE_ECHANGE, ModeEchange.REQUETE);
		dataGMEmissionRequeteReceived.put(MODULES, ModuleEnum.EMISSION);
		dataGMEmissionRequeteReceived.put(ENUM_SERVICE, EnumService.GetGeneralMessage.getServiceName());
		dataGMEmissionRequeteReceived.put(SENS, LogFactory.SENS_RECEPTION);
		dataGMEmissionRequeteReceived.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataGMEmissionRequeteReceived));
		addData(dataGMEmissionRequeteReceived, minDataDate, maxDataDate);

		final Map<String, Object> dataGMEmissionRequeteSent = new HashMap<String, Object>();
		dataGMEmissionRequeteSent.put(MODE_ECHANGE, ModeEchange.REQUETE);
		dataGMEmissionRequeteSent.put(MODULES, ModuleEnum.EMISSION);
		dataGMEmissionRequeteSent.put(ENUM_SERVICE, EnumService.GetGeneralMessage.getServiceName());
		dataGMEmissionRequeteSent.put(SENS, LogFactory.SENS_EMISSION);
		dataGMEmissionRequeteSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataGMEmissionRequeteSent));
		addData(dataGMEmissionRequeteSent, minDataDate, maxDataDate);

		/*
		 * ****************************************************************************
		 * *************************** StopPointDiscovery *****************************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataSPDEmissionRequeteReceived = new HashMap<String, Object>();
		dataSPDEmissionRequeteReceived.put(MODE_ECHANGE, ModeEchange.REQUETE);
		dataSPDEmissionRequeteReceived.put(MODULES, ModuleEnum.EMISSION);
		dataSPDEmissionRequeteReceived.put(ENUM_SERVICE, EnumService.StopPointsDiscovery.getServiceName());
		dataSPDEmissionRequeteReceived.put(SENS, LogFactory.SENS_EMISSION);
		dataSPDEmissionRequeteReceived.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataSPDEmissionRequeteReceived));
		addData(dataSPDEmissionRequeteReceived, minDataDate, maxDataDate);

		final Map<String, Object> dataSPDEmissionRequeteSent = new HashMap<String, Object>();
		dataSPDEmissionRequeteSent.put(MODE_ECHANGE, ModeEchange.REQUETE);
		dataSPDEmissionRequeteSent.put(MODULES, ModuleEnum.EMISSION);
		dataSPDEmissionRequeteSent.put(ENUM_SERVICE, EnumService.StopPointsDiscovery.getServiceName());
		dataSPDEmissionRequeteSent.put(SENS, LogFactory.SENS_EMISSION);
		dataSPDEmissionRequeteSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataSPDEmissionRequeteSent));
		addData(dataSPDEmissionRequeteSent, minDataDate, maxDataDate);

		/*
		 * ****************************************************************************
		 * *************************** LineDiscovery **********************************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataLDEmissionRequeteReceived = new HashMap<String, Object>();
		dataLDEmissionRequeteReceived.put(MODE_ECHANGE, ModeEchange.REQUETE);
		dataLDEmissionRequeteReceived.put(MODULES, ModuleEnum.EMISSION);
		dataLDEmissionRequeteReceived.put(ENUM_SERVICE, EnumService.LinesDiscovery.getServiceName());
		dataLDEmissionRequeteReceived.put(SENS, LogFactory.SENS_RECEPTION);
		dataLDEmissionRequeteReceived.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataLDEmissionRequeteReceived));
		addData(dataLDEmissionRequeteReceived, minDataDate, maxDataDate);

		final Map<String, Object> dataLDEmissionRequeteSent = new HashMap<String, Object>();
		dataLDEmissionRequeteSent.put(MODE_ECHANGE, ModeEchange.REQUETE);
		dataLDEmissionRequeteSent.put(MODULES, ModuleEnum.EMISSION);
		dataLDEmissionRequeteSent.put(ENUM_SERVICE, EnumService.LinesDiscovery.getServiceName());
		dataLDEmissionRequeteSent.put(SENS, LogFactory.SENS_EMISSION);
		dataLDEmissionRequeteSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataLDEmissionRequeteSent));
		addData(dataLDEmissionRequeteSent, minDataDate, maxDataDate);

		/*
		 * ****************************************************************************
		 * *************************** GetSiri ****************************************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataGetSiriEmissionRequeteReceived = new HashMap<String, Object>();
		dataGetSiriEmissionRequeteReceived.put(MODE_ECHANGE, ModeEchange.REQUETE_24);
		dataGetSiriEmissionRequeteReceived.put(MODULES, ModuleEnum.EMISSION);
		dataGetSiriEmissionRequeteReceived.put(ENUM_SERVICE, EnumService.GetSiri.getServiceName());
		dataGetSiriEmissionRequeteReceived.put(SENS, LogFactory.SENS_RECEPTION);
		dataGetSiriEmissionRequeteReceived.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataGetSiriEmissionRequeteReceived));
		addData(dataGetSiriEmissionRequeteReceived, minDataDate, maxDataDate);

		final Map<String, Object> dataGetSiriEmissionRequeteSent = new HashMap<String, Object>();
		dataGetSiriEmissionRequeteSent.put(MODE_ECHANGE, ModeEchange.REQUETE_24);
		dataGetSiriEmissionRequeteSent.put(MODULES, ModuleEnum.EMISSION);
		dataGetSiriEmissionRequeteSent.put(ENUM_SERVICE, EnumService.GetSiri.getServiceName());
		dataGetSiriEmissionRequeteSent.put(SENS, LogFactory.SENS_EMISSION);
		dataGetSiriEmissionRequeteSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataGetSiriEmissionRequeteSent));
		addData(dataGetSiriEmissionRequeteSent, minDataDate, maxDataDate);

		/*
		 * ****************************************************************************
		 * *************************** GetMultipleStopMonitoring **********************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataGMSMEmissionRequeteReceived = new HashMap<String, Object>();
		dataGMSMEmissionRequeteReceived.put(MODE_ECHANGE, ModeEchange.REQUETE_22);
		dataGMSMEmissionRequeteReceived.put(MODULES, ModuleEnum.EMISSION);
		dataGMSMEmissionRequeteReceived.put(ENUM_SERVICE, EnumService.GetMultipleStopMonitoring.getServiceName());
		dataGMSMEmissionRequeteReceived.put(SENS, LogFactory.SENS_RECEPTION);
		dataGMSMEmissionRequeteReceived.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataGMSMEmissionRequeteReceived));
		addData(dataGMSMEmissionRequeteReceived, minDataDate, maxDataDate);

		final Map<String, Object> dataGMSMEmissionRequeteSent = new HashMap<String, Object>();
		dataGMSMEmissionRequeteSent.put(MODE_ECHANGE, ModeEchange.REQUETE_22);
		dataGMSMEmissionRequeteSent.put(MODULES, ModuleEnum.EMISSION);
		dataGMSMEmissionRequeteSent.put(ENUM_SERVICE, EnumService.GetMultipleStopMonitoring.getServiceName());
		dataGMSMEmissionRequeteSent.put(SENS, LogFactory.SENS_EMISSION);
		dataGMSMEmissionRequeteSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataGMSMEmissionRequeteSent));
		addData(dataGMSMEmissionRequeteSent, minDataDate, maxDataDate);

		/*
		 * ****************************************************************************************************************************************************************************************
		 * ************************************************************************ ACQUISITION ** NOTIFICATION
		 * ***********************************************************************************
		 */

		/*
		 * ****************************************************************************
		 * *************************** EstimatedTimeTable *****************************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataETAcquisitionNotificationSent = new HashMap<String, Object>();
		dataETAcquisitionNotificationSent.put(MODE_ECHANGE, ModeEchange.NOTIFICATION);
		dataETAcquisitionNotificationSent.put(MODULES, ModuleEnum.ACQUISITION);
		dataETAcquisitionNotificationSent.put(ENUM_SERVICE, EnumService.GetEstimatedTimetable.getServiceName());
		dataETAcquisitionNotificationSent.put(SENS, LogFactory.SENS_RECEPTION);
		dataETAcquisitionNotificationSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataETAcquisitionNotificationSent));
		addData(dataETAcquisitionNotificationSent, minDataDate, maxDataDate);

		/*
		 * ****************************************************************************
		 * *************************** GeneralMessage *********************************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataGMAcquisitionNotificationSent = new HashMap<String, Object>();
		dataGMAcquisitionNotificationSent.put(MODE_ECHANGE, ModeEchange.NOTIFICATION);
		dataGMAcquisitionNotificationSent.put(MODULES, ModuleEnum.ACQUISITION);
		dataGMAcquisitionNotificationSent.put(ENUM_SERVICE, EnumService.GetGeneralMessage.getServiceName());
		dataGMAcquisitionNotificationSent.put(SENS, LogFactory.SENS_RECEPTION);
		dataGMAcquisitionNotificationSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataGMAcquisitionNotificationSent));
		addData(dataGMAcquisitionNotificationSent, minDataDate, maxDataDate);

		/*
		 * ****************************************************************************
		 * *************************** StopMonitoring *********************************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataSMAcquisitionNotificationSent = new HashMap<String, Object>();
		dataSMAcquisitionNotificationSent.put(MODE_ECHANGE, ModeEchange.NOTIFICATION);
		dataSMAcquisitionNotificationSent.put(MODULES, ModuleEnum.ACQUISITION);
		dataSMAcquisitionNotificationSent.put(ENUM_SERVICE, EnumService.GetStopMonitoring.getServiceName());
		dataSMAcquisitionNotificationSent.put(SENS, LogFactory.SENS_RECEPTION);
		dataSMAcquisitionNotificationSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataSMAcquisitionNotificationSent));
		addData(dataSMAcquisitionNotificationSent, minDataDate, maxDataDate);

		/*
		 * ****************************************************************************************************************************************************************************************
		 * ************************************************************************ EMISSION ** NOTIFICATION
		 * ***********************************************************************************
		 */

		/*
		 * ****************************************************************************
		 * *************************** GeneralMessage *********************************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataGMEmissionNotificationSent = new HashMap<String, Object>();
		dataGMEmissionNotificationSent.put(MODE_ECHANGE, ModeEchange.NOTIFICATION);
		dataGMEmissionNotificationSent.put(MODULES, ModuleEnum.EMISSION);
		dataGMEmissionNotificationSent.put(ENUM_SERVICE, EnumService.GetGeneralMessage.getServiceName());
		dataGMEmissionNotificationSent.put(SENS, LogFactory.SENS_EMISSION);
		dataGMEmissionNotificationSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataGMEmissionNotificationSent));
		addData(dataGMEmissionNotificationSent, minDataDate, maxDataDate);

		/*
		 * ****************************************************************************
		 * *************************** StopMonitoring *********************************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataSMEmissionNotificationSent = new HashMap<String, Object>();
		dataSMEmissionNotificationSent.put(MODE_ECHANGE, ModeEchange.NOTIFICATION);
		dataSMEmissionNotificationSent.put(MODULES, ModuleEnum.EMISSION);
		dataSMEmissionNotificationSent.put(ENUM_SERVICE, EnumService.GetStopMonitoring.getServiceName());
		dataSMEmissionNotificationSent.put(SENS, LogFactory.SENS_EMISSION);
		dataSMEmissionNotificationSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataSMEmissionNotificationSent));
		addData(dataSMEmissionNotificationSent, minDataDate, maxDataDate);

		/*
		 * ****************************************************************************
		 * *************************** Subscribe orchestration **************************
		 * ****************************************************************************
		 */
		final Map<String, Object> dataSubscribeProducteurNotificationSent = new HashMap<String, Object>();
		dataSubscribeProducteurNotificationSent.put(MODE_ECHANGE, ModeEchange.NOTIFICATION);
		dataSubscribeProducteurNotificationSent.put(MODULES, ModuleEnum.ORCHESTRATION);
		dataSubscribeProducteurNotificationSent.put(ENUM_SERVICE, EnumService.Subscribe.getServiceName());
		dataSubscribeProducteurNotificationSent.put(SENS, LogFactory.SENS_EMISSION);
		dataSubscribeProducteurNotificationSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataSubscribeProducteurNotificationSent));
		addData(dataSubscribeProducteurNotificationSent, minDataDate, maxDataDate);

		final Map<String, Object> dataSubscribeDiffuseurNotificationSent = new HashMap<String, Object>();
		dataSubscribeDiffuseurNotificationSent.put(MODE_ECHANGE, ModeEchange.NOTIFICATION);
		dataSubscribeDiffuseurNotificationSent.put(MODULES, ModuleEnum.ORCHESTRATION);
		dataSubscribeDiffuseurNotificationSent.put(ENUM_SERVICE, EnumService.Subscribe.getServiceName());
		dataSubscribeDiffuseurNotificationSent.put(SENS, LogFactory.SENS_RECEPTION);
		dataSubscribeDiffuseurNotificationSent.put(EXCHANGE_TYPE, makeExchangeTypeTitle(dataSubscribeDiffuseurNotificationSent));
		addData(dataSubscribeDiffuseurNotificationSent, minDataDate, maxDataDate);

	}

	/**
	 * Appends the title of the legend, from parameters of the map<br/>
	 * STIF-344
	 *
	 * @param dataMap
	 *            parameters defining what data will be queried to be print. It MUST have
	 *            <ul>
	 *            <li>{@link AbstractRapport#MODE_ECHANGE} (type {@link ModeEchange})</li>
	 *            <li> {@link AbstractRapport#MODULES} (type {@link ModuleEnum})</li>
	 *            <li> {@link AbstractRapport#ENUM_SERVICE} (type {@link String} from {@link EnumService#getServiceName()})</li>
	 *            <li> {@link AbstractRapport#SENS} (type {@link String} from {@link LogFactory#SENS_EMISSION} or
	 *            {@link LogFactory#SENS_RECEPTION})</li>
	 *            </ul>
	 *            as parameters.
	 * @return the String title which will be used to order charts : change the append order to sort charts as needed
	 */
	private Object makeExchangeTypeTitle(final Map<String, Object> dataMap) {
		final String domaine = (String) dataMap.get(ENUM_SERVICE); //EnumService.toString()
		final String module = ((ModuleEnum) dataMap.get(MODULES)).name(); //ex:ACQUISITION
		final String sens = (String) dataMap.get(SENS);//ex:LogFactory.SENS_RECEPTION

		final ModeEchange modeEchange = ((ModeEchange) dataMap.get(MODE_ECHANGE));
		String modeEchangeSimpl = ModeEchange.INCONNU.name();
		if (modeEchange == ModeEchange.REPONSE_22 || modeEchange == ModeEchange.REPONSE_24) {
			modeEchangeSimpl = "REPONSE";
		} else if (modeEchange == ModeEchange.REQUETE || modeEchange == ModeEchange.REQUETE_22 || modeEchange == ModeEchange.REQUETE_24) {
			modeEchangeSimpl = "REQUETE";
		} else if (modeEchange == ModeEchange.NOTIFICATION) {

			if (domaine.equals(EnumService.Subscribe.name())) {
				modeEchangeSimpl = "DEMANDE D'ABONNEMENT";
			} else {
				modeEchangeSimpl = ModeEchange.NOTIFICATION.name();
			}

		}

		return module + " / " + modeEchangeSimpl + " / " + domaine + " / " + sens;
	}

	/**
	 * @param param
	 *            parametres de requetes elastic-search
	 * @param minDataDate
	 *            un marqueur permettant de déterminer la date des premières données (en terme de temps/âge). Le but est d'optimiser l'axe
	 *            des abscisses
	 * @param maxDataDate
	 *            un marqueur permettant de déterminer la date des dernières données (en terme de temps/âge). Le but est d'optimiser l'axe
	 *            des abscisses
	 */
	private void addData(final Map<String, Object> param, final Date minDataDate, final Date maxDataDate) {

		final Long endTime = endDate.getTime();
		Date fromDate = startDate;
		Date toDate = null;
		Date nextToDate = null;
		boolean stopLoop = false;

		while (false == stopLoop) {

			if (null != toDate) {
				fromDate = new Date(toDate.getTime() + 1);
			}
			nextToDate = DateUtils.addTime(fromDate, intervalleRequeteEnHeures, 0, 0);
			if (nextToDate.getTime() > endTime) {
				nextToDate = new Date(endTime - toDate.getTime());
				stopLoop = true;
			}
			toDate = nextToDate;

			final Page<WSGenericMetierEntity> dataResult = elasticsearchBC.findByServiceAndSens((ModeEchange) param.get(MODE_ECHANGE),
					(ModuleEnum) param.get(MODULES), (String) param.get(ENUM_SERVICE), (String) param.get(SENS), fromDate, toDate);

			final Iterator<WSGenericMetierEntity> dataIterator = dataResult.iterator();

			while (dataIterator.hasNext()) {
				final WSGenericMetierEntity echange = dataIterator.next();
				final Date echangeDate = DateUtils.toDate(echange.getLog_date());
				DateUtils.compareDatesBoundaries(echangeDate, minDataDate, maxDataDate);
				dataSource.add(buildObjectArrayForDataSource(echange, (String) param.get(EXCHANGE_TYPE)));

			}
			defineTimeTickMask(minDataDate, maxDataDate);
		}
	}

	/**
	 * on ajoute des données à {@link AbstractRapport#dataSource} en lui passant plusieurs paramètres correspondant aux colonnes
	 * prédéfinies. Cette méthode renvoit un Array, plus facile à générer à la place des nombreux paramètres. Et surtout facile à Override,
	 * en particulier pour le rapport 9 {@link Rapport9#buildObjectArrayForDataSource(WSGenericMetierEntity, String)}<br/>
	 * STIF-344
	 *
	 * @param echange
	 *            l'entité de la donnée. utilisée en fait que pour la date
	 * @param exchangeType
	 *            le type d'echange, dont l'ensemble des données pour ce type forme au final un graphe
	 * @return the array of objects to pass into {@link AbstractRapport#dataSource}, "non-useful" data included (week/date)
	 */
	protected Object[] buildObjectArrayForDataSource(final WSGenericMetierEntity echange, final String exchangeType) {
		return new Object[] {
				//col 1 : Week
				String.valueOf("Semaine " + DateUtils.toWeekOfYear(echange.getLog_date())),
				//col 2 : COUNT_COLUMN
				1, // Le rapport 1 analyse la volumétrie en nombre de messages : ceci est 1 message
					//col 3 : EXCHANGE_TYPE
				exchangeType,
				//col 4 : date
				DateUtils.toDate(echange.getLog_date()) };
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
		return ReferenceRapport.RAPPORT_1.name();
	}

}
