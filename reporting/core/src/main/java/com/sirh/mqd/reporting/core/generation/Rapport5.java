package com.sirh.mqd.reporting.core.generation;

import static net.sf.dynamicreports.report.builder.DynamicReports.asc;
import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.Date;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.chart.TimeSeriesChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.datasource.DRDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.traces.enums.EnumActionNature;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.enums.ReferenceRapport;
import com.sirh.mqd.reporting.persistence.bc.ElasticsearchBC;
import com.sirh.mqd.reporting.persistence.constantes.PersistenceReportingConstantes;

/**
 * Classe gérant la création des rapports
 *
 * @see doc.story.ref1618
 *
 */
@Component
public class Rapport5 extends AbstractRapport {

	/**
	 * Index 1 de la datasource : semaine
	 */
	private static final String INDEX_1 = "semaine";

	/**
	 * Index 2 de la datasource : date
	 */
	private static final String INDEX_2 = "date";

	/**
	 * Index 3 de la datasource : autorise
	 */
	private static final String INDEX_3 = "autorise";

	/**
	 * Index 4 de la datasource : illicite
	 */
	private static final String INDEX_4 = "illicite";

	/**
	 * Variable locale permettant de savoir si le rapport doit être géneré ou non
	 *
	 * Elle est réinitialisée à chaque fois que la méthode createDataSourceRapport est invoquée
	 */
	//STIFSCRUM-2549
	// On génére le rapport même s'il est vide
	// private static boolean dataSourceEmpty;

	@Autowired
	@Qualifier(PersistenceReportingConstantes.ELASTICSEARCH_BC)
	private ElasticsearchBC elasticsearchBC;

	private final String TITLE = "Relais IVTR - Accès au portail IHM";

	private final String FOOTER_LIBELLE = "Relais IVTR - Accès au portail IHM";

	/**
	 * Le pas en minute entre chaque relevé
	 */
	protected final int PAS = 15;

	/**
	 * Le nombre de jour affiché par page
	 */
	protected final int JOUR_PAGE = 7;

	public Rapport5() {
	}

	@Override
	protected void buildRapport(final JasperReportBuilder report) {

		FontBuilder boldFont = stl.fontArialBold().setFontSize(12);

		//Definition du type de graph
		TimeSeriesChartBuilder barchart = cht.timeSeriesChart();
		//DifferenceChartBuilder barchart = cht.differenceChart();

		//Définition du titre
		barchart.setTitle("Accès au portail IHM");
		barchart.setTitleFont(boldFont);

		//Definition de l'axe temporel, attention le 2ème champs 'date' est l'index de la datasource.
		TextColumnBuilder<Date> dateColumn = col.column("Date", INDEX_2, type.dateType());
		barchart.setTimeAxisFormat(cht.axisFormat().setLabel("Date"));
		barchart.setTimePeriod(dateColumn);
		barchart.setTimePeriodType(TimePeriod.HOUR);

		//Definition de l'axe des valeurs 1.attention le 2ème champs 'illicite' est l'index de la datasource.
		TextColumnBuilder<Integer> illicite = col.column("Nb d'accès illicites", INDEX_4, type.integerType());
		barchart.series(cht.serie(illicite));

		//Definition de l'axe des valeurs 1.attention le 2ème champs 'autorise' est l'index de la datasource.
		TextColumnBuilder<Integer> autorise = col.column("Nb d'accès autorisés", INDEX_3, type.integerType());
		barchart.series(cht.serie(autorise));

		barchart.setValueAxisFormat(cht.axisFormat().setLabel("Nb d'accès"));

		TextColumnBuilder<String> semaine = col.column("Semaine", INDEX_1, type.stringType());
		ColumnGroupBuilder groupSemaine = grp.group(semaine).footer(barchart);
		barchart.setShowShapes(false);

		report.sortBy(asc(dateColumn));
		report.groupBy(groupSemaine);

		//Affichage d'un tableau récapitulatif des valeurs
		//report.columns(dateColumn, autorise);
		//Initialise le graph sur le rapport
		report.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);

		//Initialise les données dans le rapport.
		report.setDataSource(dataSource);

	}

	@Override
	protected void createDataSourceRapport() {

		//Définition des index de la datasource
		dataSource = new DRDataSource(INDEX_1, INDEX_2, INDEX_3, INDEX_4);
		initDataSource();

	}

	/**
	 * Initialise les données du graph à pour un partenaire spécifique
	 *
	 * @param plageEntry
	 *            partenaire accompagné de sa plage d'initialisation
	 */
	private void initDataSource() {

		//Initialisation de l'environnement temporel
		Date date = startDate;
		String semainePattern = "Semaine %d";

		//Population de la datasource
		while (endDate.after(date)) {
			Date dateSuivante = DateUtils.addTime(date, 0, PAS, 0);

			Long actionAutorise = elasticsearchBC.findByActionNature(EnumActionNature.SUCCESS.name(), date, dateSuivante);
			long autorise = 0;
			boolean atLessOneSelected = false;

			if (actionAutorise != null && actionAutorise > 0) {
				autorise = actionAutorise;
				atLessOneSelected = true;
			}

			Long actionIlicite = elasticsearchBC.findByActionNature(EnumActionNature.ILLEGAL_ACCESS.name(), date, dateSuivante);
			long illicite = 0;
			if (actionIlicite != null && actionIlicite > 0) {
				illicite = actionIlicite;
				atLessOneSelected = true;
			}
			Integer semaineInt = DateUtils.toWeekOfYear(date);

			if (atLessOneSelected) {
				dataSource.add(semainePattern.replaceAll("%d", semaineInt.toString()), date, autorise, illicite);
			}

			date = dateSuivante;
		}
	}

	/**
	 * Renvois une chaine de charactère contenant une période (généralement une semaine). Exemple : Du 21/10/2014 au 28/10/2014
	 *
	 * @param date
	 * @return
	 */
	protected String formatSemaine(final Date date) {
		return "Du " + DateUtils.formateDateJJMMAAAA(DateUtils.addDays(date, 1)) + " au "
				+ DateUtils.formateDateJJMMAAAA(DateUtils.addDays(date, JOUR_PAGE));
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
		return ReferenceRapport.RAPPORT_5.name();
	}

}
