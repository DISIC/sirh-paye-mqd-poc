package com.sirh.mqd.reporting.core.generation;

import static net.sf.dynamicreports.report.builder.DynamicReports.asc;
import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.chart.DifferenceChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.datasource.DRDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IParticipantTamponDAO;
import com.sirh.mqd.commons.traces.api.IFacadeLogs;
import com.sirh.mqd.commons.traces.logs.FacadeLogFactory;
import com.sirh.mqd.commons.traces.logs.LogWorkflowFactory;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.commons.utils.Pair;
import com.sirh.mqd.reporting.core.enums.ReferenceRapport;
import com.sirh.mqd.reporting.persistence.bc.ElasticsearchBC;
import com.sirh.mqd.reporting.persistence.constantes.PersistenceReportingConstantes;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;
import com.thalesgroup.stif.commons.echange.dto.participant.ParticipantTamponDTO;

/**
 * Classe gérant la création des rappors
 *
 * @see doc.story.ref1618
 *
 */
@Component
public class Rapport2 extends AbstractRapport {

	@Autowired
	@Qualifier(PersistenceReportingConstantes.ELASTICSEARCH_BC)
	private ElasticsearchBC elasticsearchBC;

	/**
	 * acces aux données des participants dans le tampon
	 */
	@Autowired
	@Qualifier(PersistenceConstantes.PARTICIPANT_TAMPON_DAO)
	IParticipantTamponDAO participantTamponDAO;

	private static final IFacadeLogs LOG = FacadeLogFactory.getLogger(Rapport2.class);

	private final String TITLE = "Initialisation / contrats de service";

	private final String FOOTER_LIBELLE = "Initialisation / contrats de service";

	/**
	 * Variable locale permettant de savoir si le rapport doit être géneré ou non
	 *
	 * Elle est réinitialisée à chaque fois que la méthode createDataSourceRapport est invoquée
	 */
	private static boolean dataSourceEmpty = false;

	public Rapport2() {
	}

	@Override
	protected void buildRapport(final JasperReportBuilder report) {

		if (!dataSourceEmpty) {
			FontBuilder boldFont = stl.fontArialBold().setFontSize(12);

			//Definition du type de graph
			DifferenceChartBuilder barchart = cht.differenceChart();

			//Définition du titre
			barchart.setTitle("Initialisation / contrats de service");
			barchart.setTitleFont(boldFont);

			//Definition de l'axe temporel, attention le 2ème champs 'date' est l'index de la datasource.
			TextColumnBuilder<Date> dateColumn = col.column("Date", "date", type.dateYearToMinuteType());
			barchart.setTimeAxisFormat(cht.axisFormat().setLabel("Date"));
			barchart.setTimePeriod(dateColumn);
			barchart.setTimePeriodType(TimePeriod.MINUTE);

			//Definition de l'axe des valeurs 1.attention le 2ème champs 'notif' est l'index de la datasource.
			TextColumnBuilder<Integer> notif = col.column("Nb notification", "notif", type.integerType());
			TextColumnBuilder<Integer> plage = col.column("Plage d'initialisation", "plage", type.integerType());
			barchart.series(cht.serie(notif), cht.serie(plage));

			//Regroupement des données par Partenaire et Semaine.
			TextColumnBuilder<String> partenaire = col.column("Partenaire", "partenaire", type.stringType());
			TextColumnBuilder<String> semaine = col.column("Semaine", "semaine", type.stringType());
			ColumnGroupBuilder groupSemaine = grp.group(semaine).footer(barchart);
			ColumnGroupBuilder groupPartenaire = grp.group(partenaire).footer(barchart);
			report.sortBy(asc(partenaire), asc(semaine), asc(dateColumn));
			report.groupBy(groupPartenaire, groupSemaine);

			//Affichage d'un tableau récapitulatif des valeurs
			//report.columns(dateColumn, notif);
			//Initialise le graph sur le rapport
			report.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);

			//Initialise les données dans le rapport.
			report.setDataSource(dataSource);
			//		try {

			//Affichage du rapport
			//			report.show();
			//		} catch (DRException e) {
			//			e.printStackTrace();
			//		}
		}
	}

	@Override
	protected void createDataSourceRapport() {

		dataSourceEmpty = false;

		//Utilisation d'un map destiné à contenir la plage des partenaires.
		HashMap<String, Pair<Date, Date>> plagesPartenaire = recupererPlagesPartenaire();

		//Définition des index de la datasource
		dataSource = new DRDataSource("partenaire", "semaine", "date", "notif", "plage");

		if (plagesPartenaire.isEmpty()) {
			dataSourceEmpty = true;
		} else {
			//Graph partenaire 1
			for (Entry<String, Pair<Date, Date>> plageEntry : plagesPartenaire.entrySet()) {

				initDataSource(plageEntry);

			}
		}

	}

	/**
	 * Récupère la liste des partenaires ainsi que leur plage d'initialisation
	 *
	 * @return liste des partenaires associés à leur plage d'initialisation
	 */
	protected HashMap<String, Pair<Date, Date>> recupererPlagesPartenaire() {
		HashMap<String, Pair<Date, Date>> plagesPartenaire = new HashMap<String, Pair<Date, Date>>();
		List<ParticipantTamponDTO> participants = participantTamponDAO.remonterAllParticipantInfo();
		for (ParticipantTamponDTO partdto : participants) {
			//si les deux champs sont renseignés --> cela montre que c'est un producteur
			if (partdto.getPlagefin() != null && partdto.getPlagedebut() != null) {
				String plagedebut = partdto.getPlagedebut();
				String plagefin = partdto.getPlagefin();
				LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("plage debut : " + plagedebut));
				LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("plage fin : " + plagefin));
				Date dateDebut = DateUtils.parseHoraire(plagedebut, startDate);
				Date dateFin = DateUtils.parseHoraire(plagefin, startDate);

				Pair<Date, Date> plage = new Pair<Date, Date>(dateDebut, dateFin);
				plagesPartenaire.put(partdto.getParticipantRef(), plage);
			}
		}

		return plagesPartenaire;
	}

	/**
	 * Initialise les données du graph à pour un partenaire spécifique
	 *
	 * @param plageEntry
	 *            partenaire accompagné de sa plage d'initialisation
	 * @throws ParseException
	 */
	private void initDataSource(final Entry<String, Pair<Date, Date>> plageEntry) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		//Récupération des spécificités partenaire
		String partenaire = plageEntry.getKey();
		Date dateDebutPlage = new Date(plageEntry.getValue().fst.getTime());
		Date dateFinPlage = new Date(plageEntry.getValue().snd.getTime());

		Date date = new Date(startDate.getTime());

		Page<WSGenericMetierEntity> listeCourante = elasticsearchBC.findNotif(startDate, endDate);

		Iterator<WSGenericMetierEntity> iterator = listeCourante.iterator();
		while (iterator.hasNext()) {
			WSGenericMetierEntity WSGenericMetierEntity = iterator.next();
			if (WSGenericMetierEntity.getProducer_ref() != null && WSGenericMetierEntity.getProducer_ref().equalsIgnoreCase(partenaire)) {
				long plageCourante = 0;

				int semaine = DateUtils.toWeekOfYear(WSGenericMetierEntity.getLog_date());

				try {
					date = formatter.parse(WSGenericMetierEntity.getLog_date());
				} catch (ParseException e) {
					date = DateUtils.getCalendarInstance().getTime();
				}

				//Entre le debut et la fin de la plage d'initialisation la plagecourante est à 0
				//si la plage courante est à 1, la courbe est remplis de vert.
				if (date.before(dateDebutPlage)) {
					plageCourante = 1;
				} else if (date.after(dateFinPlage)) {
					dateDebutPlage = DateUtils.addDays(dateDebutPlage, 1);
					dateFinPlage = DateUtils.addDays(dateFinPlage, 1);
				}
				dataSource.add(WSGenericMetierEntity.getProducer_ref(), "Semaine " + semaine, date, 1, plageCourante);
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
		return ReferenceRapport.RAPPORT_2.name();
	}

}
