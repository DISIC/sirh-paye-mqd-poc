package com.thalesgroup.stif.reporting.core.generation;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import net.sf.dynamicreports.report.exception.DRException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;

import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.generation.Rapport4;
import com.sirh.mqd.reporting.persistence.bc.ElasticsearchBC;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;
import com.sirh.mqd.reporting.persistence.entities.WSMetierSuccessEntity;

/**
 * @see doc.story.ref1646
 * @author adile
 *
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration({ "classpath:/application-context-test.xml" })
public class Rapport4Test {

	@InjectMocks
	@Autowired
	private Rapport4 rapport4;

	@Mock
	ElasticsearchBC elasticsearchBC;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		rapport4.setRepertoire("/tmp");
	}

	@Test
	public void buildRapportTest() throws DRException, InterruptedException, FileNotFoundException, ParseException {

		/*
		 * ****************************************************************************
		 * *************************** Erreur ivtr ****************************************
		 * ****************************************************************************
		 */
		Mockito.when(
				((PageImpl<WSGenericMetierEntity>) elasticsearchBC.findforRapport4(Mockito.anyBoolean(), Mockito.eq(true),
						Mockito.anyBoolean(), Mockito.any(Date.class), Mockito.any(Date.class)))).thenReturn(
								new PageImpl<WSGenericMetierEntity>(generateDataAcquisition()));
		Mockito.when(
				((PageImpl<WSGenericMetierEntity>) elasticsearchBC.findforRapport4(Mockito.anyBoolean(), Mockito.eq(false),
						Mockito.anyBoolean(), Mockito.any(Date.class), Mockito.any(Date.class)))).thenReturn(
								new PageImpl<WSGenericMetierEntity>(generateDataEmission()));
		//creation du rapport
		rapport4.build(DateUtils.getCalendarInstance().getTime(), DateUtils.getCalendarInstance().getTime());

		//		Assert.assertTrue(rapport4.dataSource != null);

		final File file = new File(rapport4.getFilePath());
		System.out.println(rapport4.getFilePath());

		Assert.assertTrue(file.exists());
		Assert.assertTrue(file.length() > 0);
	}

	/**
	 * @param content
	 * @throws ParseException
	 */
	private List<WSGenericMetierEntity> generateDataAcquisition() throws ParseException {
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,S");
		final Date dateLog = formatter.parse("2014-11-12 19:24:55,053");
		final List<WSGenericMetierEntity> content = new ArrayList<WSGenericMetierEntity>();

		final List<String> producer = new ArrayList<String>();
		producer.add("SNCF-ACCESS");
		producer.add("RATP");
		producer.add("PROD-1");

		final List<String> erreurs = new ArrayList<String>();
		//		erreurs.add("IVTR_ERR_39");
		erreurs.add("IVTR_ERR_77");
		erreurs.add("IVTR_ERR_90");

		// Données du mock
		final int i = 0;

		//requete
		//arrets**********************************
		//usage normal
		String str1 = "c.t.s.log.metier.acquisition.error";
		String str2 = "monitoring_ref1";

		generateRapport4(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		//usage peu sollicité
		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "monitoring_ref2";

		generateRapport4(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "monitoring_ref2";

		generateRapport4(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		//absent
		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "monitoring_ref3";

		generateRapport4(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		//tres sollicité
		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "monitoring_ref4";

		generateRapport4(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		//lignes**********************************
		//usage normal
		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "line_ref1";

		generateRapport4ligne(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		//usage peu sollicité
		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "line_ref2";

		generateRapport4ligne(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "line_ref2";

		generateRapport4ligne(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		//absent
		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "line_ref3";

		generateRapport4ligne(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		//tres sollicité
		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "line_ref4";

		generateRapport4ligne(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		//abo
		//arrets**********************************
		//usage normal
		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "monitoring_ref1";

		generateRapport4abo(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		//usage peu sollicité
		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "monitoring_ref2";

		generateRapport4abo(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "monitoring_ref2";

		generateRapport4abo(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		//absent
		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "monitoring_ref3";

		generateRapport4abo(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		//tres sollicité
		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "monitoring_ref4";

		generateRapport4abo(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		//lignes**********************************
		//usage normal
		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "line_ref1";

		generateRapport4ligneabo(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		//usage peu sollicité
		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "line_ref2";

		generateRapport4ligneabo(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "line_ref2";

		generateRapport4ligneabo(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		//absent
		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "line_ref3";

		generateRapport4ligneabo(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		//tres sollicité
		str1 = "c.t.s.log.metier.acquisition.error";
		str2 = "line_ref4";

		generateRapport4ligneabo(formatter, dateLog, content, producer, erreurs, i, str1, str2);

		return content;
	}

	/**
	 * @param content
	 * @throws ParseException
	 */
	private List<WSGenericMetierEntity> generateDataEmission() throws ParseException {
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,S");
		final Date dateLog = formatter.parse("2014-11-12 19:24:55,053");
		final List<WSGenericMetierEntity> content = new ArrayList<WSGenericMetierEntity>();

		final List<String> producer = new ArrayList<String>();
		producer.add("SNCF-ACCESS");
		producer.add("RATP");
		producer.add("PROD-1");

		final List<String> erreurs = new ArrayList<String>();
		//		erreurs.add("IVTR_ERR_39");
		erreurs.add("IVTR_ERR_77");
		erreurs.add("IVTR_ERR_90");

		// Données du mock
		final int i = 0;

		//requete
		//arrets**********************************
		//usage normal

		String str3 = "c.t.s.log.metier.emission.error";
		String str4 = "monitoring_ref1";
		generateRapport4(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		//usage peu sollicité

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "monitoring_ref2";
		generateRapport4(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		//absent

		//tres sollicité

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "monitoring_ref4";
		generateRapport4(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "monitoring_ref4";
		generateRapport4(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "monitoring_ref4";
		generateRapport4(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "monitoring_ref4";
		generateRapport4(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "monitoring_ref4";
		generateRapport4(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "monitoring_ref4";
		generateRapport4(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		//lignes**********************************
		//usage normal

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref1";
		generateRapport4ligne(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		//usage peu sollicité

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref2";
		generateRapport4ligne(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		//absent

		//tres sollicité

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref4";
		generateRapport4ligne(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref4";
		generateRapport4ligne(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref4";
		generateRapport4ligne(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref4";
		generateRapport4ligne(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref4";
		generateRapport4ligne(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref4";
		generateRapport4ligne(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		//abo
		//arrets**********************************
		//usage normal

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "monitoring_ref1";
		generateRapport4abo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		//usage peu sollicité

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "monitoring_ref2";
		generateRapport4abo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		//absent

		//tres sollicité

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "monitoring_ref4";
		generateRapport4abo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "monitoring_ref4";
		generateRapport4abo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "monitoring_ref4";
		generateRapport4abo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "monitoring_ref4";
		generateRapport4abo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "monitoring_ref4";
		generateRapport4abo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "monitoring_ref4";
		generateRapport4abo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		//lignes**********************************
		//usage normal

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref1";
		generateRapport4ligneabo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		//usage peu sollicité

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref2";
		generateRapport4ligneabo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		//absent

		//tres sollicité

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref4";
		generateRapport4ligneabo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref4";
		generateRapport4ligneabo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref4";
		generateRapport4ligneabo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref4";
		generateRapport4ligneabo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref4";
		generateRapport4ligneabo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		str3 = "c.t.s.log.metier.emission.error";
		str4 = "line_ref4";
		generateRapport4ligneabo(formatter, dateLog, content, producer, erreurs, i, str3, str4);

		return content;
	}

	/**
	 * @param formatter
	 * @param dateLog
	 * @param content
	 * @param producer
	 * @param erreurs
	 * @param i
	 * @param str1
	 * @param str2
	 */
	private void generateRapport4(final SimpleDateFormat formatter, final Date dateLog, final List<WSGenericMetierEntity> content,
			final List<String> producer, final List<String> erreurs, final int i, final String str1, final String str2) {
		final WSMetierSuccessEntity echange = new WSMetierSuccessEntity();

		echange.setId(String.valueOf(1));
		echange.setMessage("2014-11-12 19:24:55,053 [ERROR] c.t.s.log.metier.acquisition.error - [12/11/2014 14:24:55.042]-[WS]-[GeneralMessage]-[SIRI_SM]-[Unknown]-[Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE]-[Sens_reception]-[VRAI]-[null]-[0]-[null]-[SNCF-ACCES:Message::1:LOC]-[null]-[null]-[null]-[null]-[null]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[SNCF-ACCES]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[IVTR_ERR_30]");
		//echange.setHost("stif-pc-dev-03");
		echange.setPath("/var/log/stif/metier/acquisition-relais-error.log");
		echange.setLog_date(formatter.format(DateUtils.addTime(dateLog, i, 0, 0)));
		echange.setLog_level("ERROR");

		echange.setClassname(str1);

		echange.setRequest_timestamp("2014-11-12 19:24:55,053");
		echange.setType_message("WS");
		echange.setService("SM");
		echange.setDomaine("SIRI");
		echange.setType_structure("Unknown");
		echange.setAction_value("Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE");

		echange.setMode("REQUETE");

		echange.setSens("Sens_emission");
		echange.setContrat("VRAI");
		echange.setRequestor_ref("RELAI");

		final Random rand = new Random();
		final int randTaille = 10 + rand.nextInt(300 - 10);
		final int randTaille2 = rand.nextInt(10);
		echange.setTaille(randTaille);

		echange.setMonitoring_ref(str2);

		echange.setOperator_ref("operator_ref");
		echange.setSubscription_ref("subscription_ref");
		echange.setError_condition_description("description");
		echange.setError_condition_structure("structure");
		echange.setError_condition_error_text("La version de la requête ne correspond pas à la version du partenaire.");
		echange.setError_condition_specific_field("specific field");

		echange.setProducer_ref(producer.get(rand.nextInt(producer.size())));

		echange.setDescription("La version de la requête ne correspond pas à la version du partenaire.");
		echange.setStatus("ok");
		echange.setCode_erreur_ivtr(erreurs.get(rand.nextInt(erreurs.size())));
		//echange.setDuration(604400);

		content.add(echange);
	}

	/**
	 * @param formatter
	 * @param dateLog
	 * @param content
	 * @param producer
	 * @param erreurs
	 * @param i
	 * @param str1
	 * @param str2
	 */
	private void generateRapport4ligne(final SimpleDateFormat formatter, final Date dateLog, final List<WSGenericMetierEntity> content,
			final List<String> producer, final List<String> erreurs, final int i, final String str1, final String str2) {
		final WSMetierSuccessEntity echange = new WSMetierSuccessEntity();

		echange.setId(String.valueOf(1));
		echange.setMessage("2014-11-12 19:24:55,053 [ERROR] c.t.s.log.metier.acquisition.error - [12/11/2014 14:24:55.042]-[WS]-[GeneralMessage]-[SIRI_ET]-[Unknown]-[Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE]-[Sens_reception]-[VRAI]-[null]-[0]-[null]-[SNCF-ACCES:Message::1:LOC]-[null]-[null]-[null]-[null]-[null]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[SNCF-ACCES]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[IVTR_ERR_30]");
		//echange.setHost("stif-pc-dev-03");
		echange.setPath("/var/log/stif/metier/acquisition-relais-error.log");
		echange.setLog_date(formatter.format(DateUtils.addTime(dateLog, i, 0, 0)));
		echange.setLog_level("ERROR");

		echange.setClassname(str1);

		echange.setRequest_timestamp("2014-11-12 19:24:55,053");
		echange.setType_message("WS");
		echange.setService("SM");
		echange.setDomaine("SIRI");
		echange.setType_structure("Unknown");
		echange.setAction_value("Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE");

		echange.setMode("REQUETE");

		echange.setSens("Sens_emission");
		echange.setContrat("VRAI");
		echange.setRequestor_ref("RELAI");

		final Random rand = new Random();
		final int randTaille = 10 + rand.nextInt(300 - 10);
		final int randTaille2 = rand.nextInt(10);
		echange.setTaille(randTaille);

		echange.setLine_ref(str2);

		echange.setOperator_ref("operator_ref");
		echange.setSubscription_ref("subscription_ref");
		echange.setError_condition_description("description");
		echange.setError_condition_structure("structure");
		echange.setError_condition_error_text("La version de la requête ne correspond pas à la version du partenaire.");
		echange.setError_condition_specific_field("specific field");

		echange.setProducer_ref(producer.get(rand.nextInt(producer.size())));

		echange.setDescription("La version de la requête ne correspond pas à la version du partenaire.");
		echange.setStatus("ok");
		echange.setCode_erreur_ivtr(erreurs.get(rand.nextInt(erreurs.size())));
		//echange.setDuration(604400);

		content.add(echange);
	}

	/**
	 * @param formatter
	 * @param dateLog
	 * @param content
	 * @param producer
	 * @param erreurs
	 * @param i
	 * @param str1
	 * @param str2
	 */
	private void generateRapport4abo(final SimpleDateFormat formatter, final Date dateLog, final List<WSGenericMetierEntity> content,
			final List<String> producer, final List<String> erreurs, final int i, final String str1, final String str2) {
		final WSMetierSuccessEntity echange = new WSMetierSuccessEntity();

		echange.setId(String.valueOf(1));
		echange.setMessage("2014-11-12 19:24:55,053 [ERROR] c.t.s.log.metier.acquisition.error - [12/11/2014 14:24:55.042]-[WS]-[GeneralMessage]-[SIRI_SM]-[Unknown]-[Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE]-[Sens_reception]-[VRAI]-[null]-[0]-[null]-[SNCF-ACCES:Message::1:LOC]-[null]-[null]-[null]-[null]-[null]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[SNCF-ACCES]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[IVTR_ERR_30]");
		//echange.setHost("stif-pc-dev-03");
		echange.setPath("/var/log/stif/metier/acquisition-relais-error.log");
		echange.setLog_date(formatter.format(DateUtils.addTime(dateLog, i, 0, 0)));
		echange.setLog_level("ERROR");

		echange.setClassname(str1);

		echange.setRequest_timestamp("2014-11-12 19:24:55,053");
		echange.setType_message("WS");
		echange.setService("SM");
		echange.setDomaine("SIRI");
		echange.setType_structure("Unknown");
		echange.setAction_value("Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE");

		echange.setMode("ABONNEMENT");

		echange.setSens("Sens_emission");
		echange.setContrat("VRAI");
		echange.setRequestor_ref("RELAI");

		final Random rand = new Random();
		final int randTaille = 10 + rand.nextInt(300 - 10);
		final int randTaille2 = rand.nextInt(10);
		echange.setTaille(randTaille);

		echange.setMonitoring_ref(str2);

		echange.setOperator_ref("operator_ref");
		echange.setSubscription_ref("subscription_ref");
		echange.setError_condition_description("description");
		echange.setError_condition_structure("structure");
		echange.setError_condition_error_text("La version de la requête ne correspond pas à la version du partenaire.");
		echange.setError_condition_specific_field("specific field");

		echange.setProducer_ref(producer.get(rand.nextInt(producer.size())));

		echange.setDescription("La version de la requête ne correspond pas à la version du partenaire.");
		echange.setStatus("ok");
		echange.setCode_erreur_ivtr(erreurs.get(rand.nextInt(erreurs.size())));
		//echange.setDuration(604400);

		content.add(echange);
	}

	/**
	 * @param formatter
	 * @param dateLog
	 * @param content
	 * @param producer
	 * @param erreurs
	 * @param i
	 * @param str1
	 * @param str2
	 */
	private void generateRapport4ligneabo(final SimpleDateFormat formatter, final Date dateLog, final List<WSGenericMetierEntity> content,
			final List<String> producer, final List<String> erreurs, final int i, final String str1, final String str2) {
		final WSMetierSuccessEntity echange = new WSMetierSuccessEntity();

		echange.setId(String.valueOf(1));
		echange.setMessage("2014-11-12 19:24:55,053 [ERROR] c.t.s.log.metier.acquisition.error - [12/11/2014 14:24:55.042]-[WS]-[GeneralMessage]-[SIRI_ET]-[Unknown]-[Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE]-[Sens_reception]-[VRAI]-[null]-[0]-[null]-[SNCF-ACCES:Message::1:LOC]-[null]-[null]-[null]-[null]-[null]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[SNCF-ACCES]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[IVTR_ERR_30]");
		//echange.setHost("stif-pc-dev-03");
		echange.setPath("/var/log/stif/metier/acquisition-relais-error.log");
		echange.setLog_date(formatter.format(DateUtils.addTime(dateLog, i, 0, 0)));
		echange.setLog_level("ERROR");

		echange.setClassname(str1);

		echange.setRequest_timestamp("2014-11-12 19:24:55,053");
		echange.setType_message("WS");
		echange.setService("SM");
		echange.setDomaine("SIRI");
		echange.setType_structure("Unknown");
		echange.setAction_value("Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE");

		echange.setMode("ABONNEMENT");

		echange.setSens("Sens_emission");
		echange.setContrat("VRAI");
		echange.setRequestor_ref("RELAI");

		final Random rand = new Random();
		final int randTaille = 10 + rand.nextInt(300 - 10);
		final int randTaille2 = rand.nextInt(10);
		echange.setTaille(randTaille);

		echange.setLine_ref(str2);

		echange.setOperator_ref("operator_ref");
		echange.setSubscription_ref("subscription_ref");
		echange.setError_condition_description("description");
		echange.setError_condition_structure("structure");
		echange.setError_condition_error_text("La version de la requête ne correspond pas à la version du partenaire.");
		echange.setError_condition_specific_field("specific field");

		echange.setProducer_ref(producer.get(rand.nextInt(producer.size())));

		echange.setDescription("La version de la requête ne correspond pas à la version du partenaire.");
		echange.setStatus("ok");
		echange.setCode_erreur_ivtr(erreurs.get(rand.nextInt(erreurs.size())));
		//echange.setDuration(604400);

		content.add(echange);
	}

}
