package com.thalesgroup.stif.reporting.core.generation;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.sirh.mqd.commons.exchanges.enums.EnumErreursMetier;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.generation.Rapport8;
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
public class Rapport8Test {

	@InjectMocks
	@Autowired
	private Rapport8 rapport8;

	@Mock
	ElasticsearchBC elasticsearchBC;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		rapport8.setRepertoire("/tmp");
	}

	@Test
	public void buildRapportTest() throws DRException, InterruptedException, FileNotFoundException, ParseException {

		/*
		 * ****************************************************************************
		 * *************************** Erreur ivtr ****************************************
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByErreurMetier(Mockito.eq("Sens_reception"), Mockito.eq(Arrays.asList(EnumErreursMetier.IVTR_ERR_90,
						EnumErreursMetier.IVTR_ERR_39, EnumErreursMetier.IVTR_ERR_77)), Mockito.any(Date.class), Mockito.any(Date.class)))
						.thenReturn(new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception", "CheckStatus")));

		//creation du rapport
		rapport8.build(null, null);

		Assert.assertTrue(rapport8.dataSource != null);

		final File file = new File(rapport8.getFilePath());
		System.out.println(rapport8.getFilePath());

		Assert.assertTrue(file.exists());
		Assert.assertTrue(file.length() > 0);

	}

	/**
	 * @param content
	 * @throws ParseException
	 */
	private List<WSGenericMetierEntity> generateData(final String sens, final String service) throws ParseException {
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		final Date dateLog = formatter.parse("2014-11-12T19:28:55.053Z");
		final List<WSGenericMetierEntity> content = new ArrayList<WSGenericMetierEntity>();

		final List<String> producer = new ArrayList<String>();
		producer.add("SNCF-ACCESS");
		producer.add("RATP");
		producer.add("PROD-1");

		final List<String> erreurs = new ArrayList<String>();
		erreurs.add("IVTR_ERR_39");
		erreurs.add("IVTR_ERR_77");
		erreurs.add("IVTR_ERR_90");

		// Données du mock
		for (int i = 0; i < 100; i++) {

			final Random r = new Random();
			final int valeur = r.nextInt(10);

			for (int j = 0; j < valeur; j++) {

				final WSMetierSuccessEntity echange = new WSMetierSuccessEntity();

				echange.setId(String.valueOf(1));
				echange.setMessage("2014-11-12 19:28:55,053 [ERROR] c.t.s.log.metier.acquisition.error - [12/11/2014 18:28:55.042]-[WS]-[GeneralMessage]-[SIRI]-[Unknown]-[Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE]-[Sens_reception]-[VRAI]-[null]-[0]-[null]-[SNCF-ACCES:Message::1:LOC]-[null]-[null]-[null]-[null]-[null]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[SNCF-ACCES]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[IVTR_ERR_30]");
				//	echange.setHost("stif-pc-dev-03");
				echange.setPath("/var/log/stif/metier/acquisition-relais-error.log");
				echange.setLog_date(formatter.format(DateUtils.addTime(dateLog, i, 0, 0)));
				echange.setLog_level("ERROR");
				echange.setClassname("c.t.s.log.metier.acquisition.error");
				echange.setRequest_timestamp("2014-11-12 19:28:55,053");
				echange.setType_message("WS");
				echange.setService(service);
				echange.setDomaine("SIRI");
				echange.setType_structure("Unknown");
				echange.setAction_value("Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE");
				echange.setMode("REQUETE");
				echange.setSens(sens);
				echange.setContrat("VRAI");
				echange.setRequestor_ref("RELAIS");

				final Random rand = new Random();
				final int randTaille = 10 + rand.nextInt(300 - 10);

				echange.setTaille(randTaille);
				echange.setLine_ref("lineRef");
				echange.setMessage_identifier("SNCF-ACCES:Message::1:LOC");
				echange.setMonitoring_ref("monitoring_ref");
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
				//	echange.setDuration(604800);

				content.add(echange);
			}
		}

		return content;
	}
}
