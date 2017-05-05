package com.sirh.mqd.reporting.core.generation;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.FillerBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;

import org.elasticsearch.client.transport.NoNodeAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.annotations.VisibleForTesting;
import com.sirh.mqd.commons.traces.api.IFacadeLogs;
import com.sirh.mqd.commons.traces.enums.ExceptionType;
import com.sirh.mqd.commons.traces.enums.LogType;
import com.sirh.mqd.commons.traces.logs.FacadeLogFactory;
import com.sirh.mqd.commons.traces.logs.FacadeLogs;
import com.sirh.mqd.commons.traces.logs.LogExceptionFactory;
import com.sirh.mqd.commons.traces.logs.LogWorkflowFactory;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.enums.ReferenceRapport;
import com.sirh.mqd.reporting.persistence.bc.ElasticsearchBC;
import com.sirh.mqd.reporting.persistence.constantes.PersistenceReportingConstantes;

/**
 * Classe gérant la création des rapports
 *
 * @see doc.story.ref1157
 *
 */
public abstract class AbstractRapport {

	/**
	 * Injection des properties du fichier application.properties
	 */
	@Value("#{application['repertoire.rapports']}")
	public String repertoire;

	/**
	 * Injection des properties du fichier application.properties
	 */
	@Value("#{application}")
	protected Properties properties;

	@Autowired
	@Qualifier(PersistenceReportingConstantes.ELASTICSEARCH_BC)
	protected ElasticsearchBC elasticsearchBC;

	protected static String LOGO = "/logo.png";

	protected JasperReportBuilder report;

	@VisibleForTesting
	protected DRDataSource dataSource = new DRDataSource("defaut");

	protected StyleBuilder boldStyle = stl.style().bold();

	protected StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);

	protected StyleBuilder titleStyle = stl.style(boldCenteredStyle).setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(15);

	protected FontBuilder boldFont = stl.fontArialBold().setFontSize(12);

	protected String filePath;

	protected Date startDate;

	protected Date endDate;

	/**
	 * Format/pattern des dates à afficher sur l'axe des abscisses
	 */
	protected String TIME_TICK_MASK;

	/**
	 * En deça de cette valeur (i.e le graphique représente moins de 9 jours sur l'axe des abscisses), le masque affiche l'heure de la
	 * journée. Si les données à afficher sont plus vastes (le domaine s'étend sur plus de 9 jours, alors le masque ne doit afficher que le
	 * jour (jour-mois) : on n'aura pas 2 ticks pour le même jour.<br/>
	 * Valeur empirique de 9 jours exprimé en millisecondes.
	 */
	protected static final long TIME_TICK_MASK_THRESHOLD = 9L * 24L * 3600L * 1000L;

	/**
	 *
	 * La facade de log de l'application
	 */
	private static final IFacadeLogs LOG = FacadeLogFactory.getLogger(AbstractRapport.class);

	/**
	 * Map key for sense parameter in elasticsearch request
	 */
	protected static final String SENS = "Sens";

	/**
	 * Map key for Siri Service parameter in elasticsearch request
	 */
	protected static final String ENUM_SERVICE = "EnumService";

	/**
	 * Map key for COMPONENT (ACQUISITION/EMISSION) parameter in elasticsearch request
	 */
	protected static final String MODULES = "Modules";

	/**
	 * Map key for exchange mode (REQUEST/NOTIFICATION) parameter in elasticsearch request
	 */
	protected static final String MODE_ECHANGE = "ModeEchange";

	/**
	 * Chaque rapport devrait définir ce boolean : S'il vaut false, alors les colonnes non utiles d'une série de dataSource seront remplies
	 * de 0 (zéros). S'il vaut true, elles seront remplies de null. <br/>
	 * Exemple concret : Rapport 3, n graphes avec une courbe utile pour chaque graphe.<br/>
	 * 1/ Avec ce flag à False, les courbes non utiles (qui ne correspondent pas au graphe en cours) apparaissent avec différentes couleurs
	 * et se chevauchent à 0 (le long de l'axe des abscisses). <br/>
	 * 2/ Avec ce flag à True, ces courbes n'apparaissent pas du tout. En revanche, les courbes sur les graphes distincts sont tracées avec
	 * la même couleur
	 */
	protected boolean NULL_DATA_SWITCH = false;

	/**
	 * inner class utility to define and work with {@link AbstractRapport#dataSource}, particularly generate data to add.
	 *
	 */
	/* package */class DataSourceFormat {

		private final int dataSourceSize;

		private final int usefulDataStartIndex;

		private final int usefulDataEndIndex;

		/* package */DataSourceFormat(final int dataSourceColumnCount, final int start, final int end) {
			dataSourceSize = dataSourceColumnCount;
			usefulDataStartIndex = start;
			usefulDataEndIndex = end;
		}

		public int getDataSourceSize() {
			return dataSourceSize;
		}

		public int getUsefulDataStartIndex() {
			return usefulDataStartIndex;
		}

		public int getUsefulDataEndIndex() {
			return usefulDataEndIndex;
		}
	}

	public AbstractRapport() {

	}

	public void build(final Date startDate, final Date endDate) {

		this.report = createTemplate(startDate, endDate);

		this.endDate = endDate;
		this.startDate = startDate;

		try {
			LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("Récupération des données du rapport :" + getRapportTitle()));
			createDataSourceRapport();
			LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("Ecriture du rapport :" + getRapportTitle()));
			buildRapport(report);

			// Contrôle des datasources pour autorisation de génération du fichier
			//
			// Cependant pour le rapport 4, aucun contrôle n'est requis (summary est utilisé à la place de setDataSource)
			//
			if (report.getDataSource() != null || getReference().contains(ReferenceRapport.RAPPORT_4.name())) {
				LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("Achèvement du rapport :" + getRapportTitle()));

				//export the report to a pdf file
				LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow(getRapportTitle() + " : Rapport terminé "));
				final Date now = DateUtils.getCalendarInstance().getTime();
				final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

				this.filePath = repertoire + "/" + getReference() + "-" + sdf.format(now) + ".pdf";
				report.toPdf(new FileOutputStream(this.filePath));
				LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow(getRapportTitle() + " : Rapport genéré au format pdf "));
			} else {
				LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("Données du rapport vides :" + getRapportTitle()));
			}

		} catch (NoNodeAvailableException e) {
			LOG.logException(LogExceptionFactory.getLogException(LogType.OTHER, this.getClass().getName(),
					ExceptionType.INVALID_COMMUNICATION_EXCEPTION, "Erreur critique rapport :" + getRapportTitle()
							+ " : PERTE DE CONNECTION ELASTICSEARCH : " + FacadeLogs.getTraceString(e)));
		} catch (FileNotFoundException | DRException e) {
			LOG.logException(LogExceptionFactory.getLogException(LogType.OTHER, this.getClass().getName(), ExceptionType.GLOBAL_EXCEPTION,
					"Erreur critique rapport :" + getRapportTitle() + " : " + FacadeLogs.getTraceString(e)));
		}
	}

	private JasperReportBuilder createTemplate(final Date startDate, final Date endDate) {
		LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("Paramétrage du template "));

		LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("Configuration du rapport " + getRapportTitle()));

		// Generation du rapport global

		final JasperReportBuilder report = DynamicReports.report();

		ImageBuilder logo;
		final InputStream in = AbstractRapport.class.getResourceAsStream(LOGO);

		logo = cmp.image(in).setFixedDimension(160, 80);
		final FillerBuilder filler = cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10);
		report.title(cmp.horizontalList()
				.add(logo, cmp.text(getRapportTitle()).setHorizontalAlignment(HorizontalAlignment.LEFT).setStyle(titleStyle)).newRow()
				.add(filler));
		if (startDate != null && endDate != null) {
			report.pageFooter(
					cmp.text(getRapportFooterLibelle()).setHorizontalAlignment(HorizontalAlignment.CENTER),
					cmp.text(
							"Date début: " + DateUtils.formateDateJJMMAAAAhhmmss(startDate) + " Date fin: "
									+ DateUtils.formateDateJJMMAAAAhhmmss(endDate) + " Date génération: "
									+ DateUtils.formateDateJJMMAAAAhhmmss(DateUtils.getCalendarInstance().getTime()))
									.setHorizontalAlignment(HorizontalAlignment.CENTER),
									cmp.pageXofY().setHorizontalAlignment(HorizontalAlignment.CENTER));
		} else {
			report.pageFooter(
					cmp.text(getRapportFooterLibelle()).setHorizontalAlignment(HorizontalAlignment.CENTER),
					cmp.text(DateUtils.formateDateJJMMAAAAhhmmss(DateUtils.getCalendarInstance().getTime())).setHorizontalAlignment(
							HorizontalAlignment.CENTER), cmp.pageXslashY().setHorizontalAlignment(HorizontalAlignment.CENTER));
		}

		LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("Configuration du template est terminé " + getRapportTitle()));
		return report;

	}

	@VisibleForTesting
	protected void buildRapport(final JasperReportBuilder report) {
	}

	protected void createDataSourceRapport() {
	}

	/**
	 * Titre du rapport
	 *
	 * @return
	 */
	public abstract String getRapportTitle();

	/**
	 * Libellé du footer
	 *
	 * @return
	 */
	public abstract String getRapportFooterLibelle();

	public String getFilePath() {
		return filePath;
	}

	/**
	 * @return the reference
	 */
	public abstract String getReference();

	/**
	 * @return the repertoire
	 */
	public String getRepertoire() {
		return this.repertoire;
	}

	/**
	 * @param repertoire
	 *            the repertoire to set
	 */
	public void setRepertoire(final String repertoire) {
		this.repertoire = repertoire;
	}

	/**
	 * Calculates the gap between the 2 boundary-dates, and decides which pattern has to be applied to
	 * {@link AbstractRapport#TIME_TICK_MASK}
	 *
	 * @param minDataDate
	 *            low boundary
	 * @param maxDataDate
	 *            high boundary
	 */
	protected void defineTimeTickMask(final Date minDataDate, final Date maxDataDate) {
		if (minDataDate != null && maxDataDate != null) {

			final Date timeDifference = new Date(maxDataDate.getTime() - minDataDate.getTime());
			if (timeDifference.getTime() > TIME_TICK_MASK_THRESHOLD) {
				TIME_TICK_MASK = "dd-MMM"; // only day/month. ex : 19-Jan
			} else {
				TIME_TICK_MASK = "dd-MMM, H'H'"; //day/month and hour of day. ex : 19-Jan, 12H
			}

		}
	}

}
