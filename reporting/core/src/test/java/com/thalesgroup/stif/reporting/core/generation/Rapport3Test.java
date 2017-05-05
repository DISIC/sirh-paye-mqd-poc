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

import com.sirh.mqd.commons.exchanges.enums.EnumErrorCondition;
import com.sirh.mqd.commons.exchanges.enums.EnumService;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.generation.Rapport3;
import com.sirh.mqd.reporting.persistence.bc.ElasticsearchBC;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;
import com.sirh.mqd.reporting.persistence.entities.WSMetierSuccessEntity;

/**
 * @see doc.story.ref1625
 * @author adile
 *
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration({ "classpath:/application-context-test.xml" })
public class Rapport3Test {

	@InjectMocks
	@Autowired
	private Rapport3 rapport3;

	@Mock
	ElasticsearchBC elasticsearchBC;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		rapport3.setRepertoire("/tmp");
	}

	@Test
	public void buildRapportTest() throws DRException, InterruptedException, FileNotFoundException, ParseException {

		/*
		 * ****************************************************************************
		 * ******** Action du Mock pour les appels de data à Erreur condition *********
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByErreurCondition(Mockito.eq(Arrays.asList(EnumErrorCondition.ACCESS_NOT_ALLOWED_ERROR)),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
								new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception")));

		Mockito.when(
				elasticsearchBC.findByErreurCondition(Mockito.eq(Arrays.asList(EnumErrorCondition.ALLOWED_RESOURCE_USAGE_EXCEEDED_ERROR)),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
								new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception")));

		Mockito.when(
				elasticsearchBC.findByErreurCondition(Mockito.eq(Arrays.asList(EnumErrorCondition.BEYOND_DATA_HORIZON_ERROR)),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
								new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception")));

		Mockito.when(
				elasticsearchBC.findByErreurCondition(Mockito.eq(Arrays.asList(EnumErrorCondition.CAPABILITY_NOT_SUPPORTED_ERROR)),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
								new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception")));

		Mockito.when(
				elasticsearchBC.findByErreurCondition(Mockito.eq(Arrays.asList(EnumErrorCondition.INVALID_DATA_REFERENCES_ERROR)),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
								new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception")));

		Mockito.when(
				elasticsearchBC.findByErreurCondition(Mockito.eq(Arrays.asList(EnumErrorCondition.NO_INFO_FOR_TOPIC_ERROR)),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
								new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception")));

		Mockito.when(
				elasticsearchBC.findByErreurCondition(Mockito.eq(Arrays.asList(EnumErrorCondition.OTHER_ERROR)), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception")));

		Mockito.when(
				elasticsearchBC.findByErreurCondition(Mockito.eq(Arrays.asList(EnumErrorCondition.PARAMETERS_IGNORED_ERROR)),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
								new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception")));

		Mockito.when(
				elasticsearchBC.findByErreurCondition(Mockito.eq(Arrays.asList(EnumErrorCondition.SERVICE_NOTAVAILABLE_ERROR)),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
								new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception")));

		Mockito.when(
				elasticsearchBC.findByErreurCondition(Mockito.eq(Arrays.asList(EnumErrorCondition.UNKNOWN_EXTENSIONS_ERROR)),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
								new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception")));

		/*
		 * ****************************************************************************
		 * ******* Action du Mock pour les appels de data à Erreur par service ********
		 * ****************************************************************************
		 */
		//		Mockito.when(
		//				elasticsearchBC.findByErreurConditionAndService(Mockito.eq((List<EnumErrorCondition>) null),
		//						Mockito.eq(Arrays.asList(EnumService.values())), Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
		//								new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception")));

		Mockito.when(
				elasticsearchBC.findByErreurConditionAndService(Mockito.eq(Arrays.asList(EnumErrorCondition.values())),
						Mockito.eq(Arrays.asList(EnumService.values())), Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
								new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception")));

		// creation du rapport
		rapport3.build(null, null);

		Assert.assertTrue(rapport3.dataSource != null);

		final File file = new File(rapport3.getFilePath());
		System.out.println(rapport3.getFilePath());

		Assert.assertTrue(file.exists());
		Assert.assertTrue(file.length() > 0);

	}

	/**
	 * @param sens
	 * @throws ParseException
	 */
	private List<WSGenericMetierEntity> generateData(final String sens) throws ParseException {
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		final Date dateLog = formatter.parse("2014-11-12T19:28:55.053Z");
		final List<WSGenericMetierEntity> content = new ArrayList<WSGenericMetierEntity>();

		final List<String> producer = new ArrayList<String>();
		producer.add("SNCF-ACCESS");
		producer.add("RATP");
		producer.add("PROD-1");

		final List<String> erreurs = new ArrayList<String>();
		//		erreurs.add("IVTR_ERR_39");
		erreurs.add("IVTR_ERR_77");
		erreurs.add("IVTR_ERR_90");

		final List<String> erreurStructure = new ArrayList<String>();
		for (final EnumErrorCondition error : EnumErrorCondition.values()) {
			erreurStructure.add(error.name());
		}

		final List<String> services = new ArrayList<String>();
		for (final EnumService service : EnumService.values()) {
			services.add(service.getServiceName());
		}

		// Données du mock
		for (int i = 0; i < 200; i++) {

			final Random r = new Random();
			final int valeur = r.nextInt(10);

			for (int j = 0; j < valeur; j++) {

				final Random rand = new Random();
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
				echange.setService(services.get(rand.nextInt(services.size())));
				echange.setDomaine("SIRI");
				echange.setType_structure("Unknown");
				echange.setAction_value("Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE");
				echange.setMode("REQUETE");
				echange.setSens(sens);
				echange.setContrat("VRAI");
				echange.setRequestor_ref("RELAI");

				final int randTaille = 10 + rand.nextInt(300 - 10);

				echange.setTaille(randTaille);
				echange.setLine_ref("lineRef");
				echange.setMessage_identifier("SNCF-ACCES:Message::1:LOC");
				echange.setMonitoring_ref("monitoring_ref");
				echange.setOperator_ref("operator_ref");
				echange.setSubscription_ref("subscription_ref");
				echange.setError_condition_description("description");
				echange.setError_condition_structure(erreurStructure.get(rand.nextInt(erreurStructure.size())));
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
