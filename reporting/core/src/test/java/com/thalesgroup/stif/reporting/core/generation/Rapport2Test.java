package com.thalesgroup.stif.reporting.core.generation;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import net.sf.dynamicreports.report.exception.DRException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;

import com.sirh.mqd.commons.storage.dao.IParticipantTamponDAO;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.generation.Rapport2;
import com.sirh.mqd.reporting.persistence.bc.ElasticsearchBC;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;
import com.sirh.mqd.reporting.persistence.entities.WSMetierSuccessEntity;
import com.sun.tools.javac.util.Pair;
import com.thalesgroup.stif.commons.echange.dto.participant.ParticipantTamponDTO;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration({ "classpath:/application-context-test.xml" })
public class Rapport2Test {

	private static final int PAS = 15;

	@InjectMocks
	@Autowired
	private Rapport2 rapport2;

	@Mock
	protected Properties properties;

	@Mock
	ElasticsearchBC elasticsearchBC;

	@Mock
	IParticipantTamponDAO participantTamponDAO;

	Date dateDebut = null;

	Date dateFin = null;

	@Before
	public void setUp() throws ParseException {

		MockitoAnnotations.initMocks(this);
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		// 2014-11-12T19:28:55.053Z
		dateDebut = formatter.parse("2015-11-12T12:00:00.000Z");
		dateFin = formatter.parse("2015-12-01T23:00:00.000Z");
		rapport2.setRepertoire("/tmp");
	}

	@Test
	public void buildRapportVisibleEtPropreTestKO() throws NullPointerException, DRException, InterruptedException, FileNotFoundException,
	ParseException {

		final Set<String> propertieName = new HashSet<String>();
		propertieName.add("plage.partenaire.debut.SNCF-ACCES");
		propertieName.add("plage.partenaire.fin.SNCF-ACCES");
		propertieName.add("plage.partenaire.debut.100WSIVSIRI");
		propertieName.add("plage.partenaire.fin.100WSIVSIRI");
		Mockito.when(properties.stringPropertyNames()).thenReturn(propertieName);
		Mockito.when(properties.getProperty("plage.partenaire.debut.SNCF-ACCES")).thenReturn("12:00");
		Mockito.when(properties.getProperty("plage.partenaire.fin.SNCF-ACCES")).thenReturn("14:00");
		Mockito.when(properties.getProperty("plage.partenaire.debut.100WSIVSIRI")).thenReturn("12:00");
		Mockito.when(properties.getProperty("plage.partenaire.fin.100WSIVSIRI")).thenReturn("14:00");
		Mockito.when(elasticsearchBC.findNotif(Mockito.any(Date.class), Mockito.any(Date.class))).then(
				new Answer<PageImpl<WSGenericMetierEntity>>() {

					@Override
					public PageImpl<WSGenericMetierEntity> answer(final InvocationOnMock invocation) throws Throwable {

						return new PageImpl<WSGenericMetierEntity>(initLog());
					}
				});
		Mockito.when(participantTamponDAO.remonterAllParticipantInfo()).then(new Answer<List<ParticipantTamponDTO>>() {

			@Override
			public List<ParticipantTamponDTO> answer(final InvocationOnMock invocation) throws Throwable {

				final List<ParticipantTamponDTO> listParticipants = new ArrayList<ParticipantTamponDTO>();

				//Tampon 1 vide
				final ParticipantTamponDTO participantTampon1 = new ParticipantTamponDTO();
				listParticipants.add(participantTampon1);

				//Tampon 2 vide
				final ParticipantTamponDTO participantTampon2 = new ParticipantTamponDTO();
				listParticipants.add(participantTampon2);

				return listParticipants;
			}
		});
		rapport2.build(dateDebut, dateFin);
		// Si le path est nul, le fichier n'est pas généré
		Assert.assertNull(rapport2.getFilePath());
	}

	@Test
	public void buildRapportVisibleEtPropreTestOK() throws NullPointerException, DRException, InterruptedException, FileNotFoundException,
	ParseException {

		final Set<String> propertieName = new HashSet<String>();
		propertieName.add("plage.partenaire.debut.SNCF-ACCES");
		propertieName.add("plage.partenaire.fin.SNCF-ACCES");
		propertieName.add("plage.partenaire.debut.100WSIVSIRI");
		propertieName.add("plage.partenaire.fin.100WSIVSIRI");
		Mockito.when(properties.stringPropertyNames()).thenReturn(propertieName);
		Mockito.when(properties.getProperty("plage.partenaire.debut.SNCF-ACCES")).thenReturn("12:00");
		Mockito.when(properties.getProperty("plage.partenaire.fin.SNCF-ACCES")).thenReturn("14:00");
		Mockito.when(properties.getProperty("plage.partenaire.debut.100WSIVSIRI")).thenReturn("12:00");
		Mockito.when(properties.getProperty("plage.partenaire.fin.100WSIVSIRI")).thenReturn("14:00");
		Mockito.when(elasticsearchBC.findNotif(Mockito.any(Date.class), Mockito.any(Date.class))).then(
				new Answer<PageImpl<WSGenericMetierEntity>>() {

					@Override
					public PageImpl<WSGenericMetierEntity> answer(final InvocationOnMock invocation) throws Throwable {

						return new PageImpl<WSGenericMetierEntity>(initLog());
					}
				});
		Mockito.when(participantTamponDAO.remonterAllParticipantInfo()).then(new Answer<List<ParticipantTamponDTO>>() {

			@Override
			public List<ParticipantTamponDTO> answer(final InvocationOnMock invocation) throws Throwable {

				final List<ParticipantTamponDTO> listParticipants = new ArrayList<ParticipantTamponDTO>();

				final ParticipantTamponDTO participantTampon1 = new ParticipantTamponDTO();
				participantTampon1.setName("SNCF");
				participantTampon1.setParticipantRef("SNCF-ACCES");
				participantTampon1.setPlagedebut("12:00");
				participantTampon1.setPlagefin("23:00");
				listParticipants.add(participantTampon1);

				final ParticipantTamponDTO participantTampon2 = new ParticipantTamponDTO();
				participantTampon2.setName("SNCF");
				participantTampon2.setParticipantRef("KEOLIS");
				participantTampon2.setPlagedebut("10:00");
				participantTampon2.setPlagefin("21:00");
				listParticipants.add(participantTampon2);

				return listParticipants;
			}
		});
		rapport2.build(dateDebut, dateFin);

		final File file = new File(rapport2.getFilePath());
		System.out.println(rapport2.getFilePath());
		//Generation d'un visuel
		Assert.assertTrue(file.exists());
		Assert.assertTrue(file.length() > 0);
	}

	/**
	 * Initialise les données du graph à pour un partenaire spécifique
	 *
	 * @param plageEntry
	 *            partenaire accompagné de sa plage d'initialisation
	 * @throws ParseException
	 */
	private List<WSGenericMetierEntity> initLog() throws ParseException {

		//Définition des index de la datasource
		final List<WSGenericMetierEntity> data = new ArrayList<WSGenericMetierEntity>();

		final HashMap<String, Pair<Date, Date>> plagesPartenaire = new HashMap<>();

		final Date plageDebutSNCF = DateUtils.parseHoraire("12:00", dateDebut);
		final Date plageFinSNCF = DateUtils.parseHoraire("14:00", dateDebut);
		final Pair<Date, Date> plageSNCF = new Pair<Date, Date>(plageDebutSNCF, plageFinSNCF);
		plagesPartenaire.put("SNCF-ACCES", plageSNCF);

		final Date plageDebutRATP = DateUtils.parseHoraire("13:00", dateDebut);
		final Date plageFinRATP = DateUtils.parseHoraire("15:00", dateDebut);
		final Pair<Date, Date> plageRATP = new Pair<Date, Date>(plageDebutRATP, plageFinRATP);
		plagesPartenaire.put("100WSIVSIRI", plageRATP);

		//Graph partenaire 1
		populateLog(plagesPartenaire, data);
		return data;

	}

	private void populateLog(final HashMap<String, Pair<Date, Date>> plagesPartenaire, final List<WSGenericMetierEntity> data) {

		for (final Entry<String, Pair<Date, Date>> plageEntry : plagesPartenaire.entrySet()) {
			final String partenaire = plageEntry.getKey();

			//Récupération des spécificités partenaire
			Date dateDebutPlage = plageEntry.getValue().fst;
			Date dateFinPlage = plageEntry.getValue().snd;

			//Initialisation de l'environnement temporel
			Date date = dateDebut;

			//Population de la datasource
			while (dateFin.after(date)) {

				//Debut Bouchon********************************************
				int valeurCourante = ((int) (Math.random() * 10));
				//Fin Bouchon********************************************

				if (date.after(dateDebutPlage) && date.before(dateFinPlage)) {

					//Debut Bouchon********************************************
					valeurCourante = ((int) (Math.random() * 30) + 30);
					//Fin Bouchon********************************************

				} else if (date.after(dateFinPlage)) {
					dateDebutPlage = DateUtils.addDays(dateDebutPlage, 1);
					dateFinPlage = DateUtils.addDays(dateFinPlage, 1);
					valeurCourante = ((int) (Math.random() * 10));
				}

				for (int j = 0; j < valeurCourante; j++) {
					data.add(createFakeWSMetierSuccessEntity(date, partenaire));
				}
				date = new Date(DateUtils.addTime(date, 0, PAS, 0).getTime());
			}
		}
	}

	private WSMetierSuccessEntity createFakeWSMetierSuccessEntity(final Date date, final String partenaire) {
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		final WSMetierSuccessEntity echange = new WSMetierSuccessEntity();

		echange.setId(String.valueOf(1));
		echange.setMessage(" [ERROR] c.t.s.log.metier.acquisition.error - [12/11/2014 18:28:55.042]-[WS]-[GeneralMessage]-[SIRI]-[Unknown]-[Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[NOTIFICATION]-[Sens_reception]-[VRAI]-[null]-[0]-[null]-[SNCF-ACCES:Message::1:LOC]-[null]-[null]-[null]-[null]-[null]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[SNCF-ACCES]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[IVTR_ERR_30]");
		//echange.setHost("stif-pc-dev-03");
		echange.setPath("/var/log/stif/metier/acquisition-relais-error.log");
		echange.setLog_level("ERROR");
		echange.setClassname("c.t.s.log.metier.acquisition.error");
		echange.setRequest_timestamp("2014-11-12 19:28:55,053");
		echange.setType_message("WS");
		echange.setService("");
		echange.setDomaine("SIRI");
		echange.setType_structure("Unknown");
		echange.setAction_value("Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE");
		echange.setMode("REQUETE");
		echange.setSens("");
		echange.setContrat("VRAI");
		echange.setRequestor_ref("");
		echange.setTaille(25);
		echange.setLine_ref("lineRef");
		echange.setMessage_identifier("SNCF-ACCES:Message::1:LOC");
		echange.setMonitoring_ref("monitoring_ref");
		echange.setOperator_ref("operator_ref");
		echange.setSubscription_ref("subscription_ref");
		echange.setError_condition_description("description");
		echange.setError_condition_structure("structure");
		echange.setError_condition_error_text("La version de la requête ne correspond pas à la version du partenaire.");
		echange.setError_condition_specific_field("specific field");
		echange.setDescription("La version de la requête ne correspond pas à la version du partenaire.");
		echange.setStatus("ok");
		echange.setCode_erreur_ivtr("IVTR_ERR_30");
		//	echange.setDuration(604800);
		echange.setLog_date(formatter.format(date));
		echange.setProducer_ref(partenaire);
		return echange;

	}

}
