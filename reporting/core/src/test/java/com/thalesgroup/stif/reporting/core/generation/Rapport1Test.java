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

import com.sirh.mqd.commons.exchanges.enums.EnumService;
import com.sirh.mqd.commons.exchanges.enums.ModeEchange;
import com.sirh.mqd.commons.exchanges.enums.ModuleEnum;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.generation.Rapport1;
import com.sirh.mqd.reporting.persistence.bc.ElasticsearchBC;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;
import com.sirh.mqd.reporting.persistence.entities.WSMetierSuccessEntity;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration({ "classpath:/application-context-test.xml" })
public class Rapport1Test {

	@InjectMocks
	@Autowired
	private Rapport1 rapport1;

	@Mock
	ElasticsearchBC elasticsearchBC;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		rapport1.setRepertoire("/tmp");
	}

	@Test
	public void buildRapportTest() throws DRException, InterruptedException, FileNotFoundException, ParseException {

		/*
		 * ****************************************************************************
		 * *************************** GetSiri ****************************************
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE_24), Mockito.eq(ModuleEnum.ACQUISITION),
						Mockito.eq(EnumService.GetSiri.getServiceName()), Mockito.eq("Sens_reception"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception", EnumService.GetSiri.getServiceName())));

		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE_24), Mockito.eq(ModuleEnum.ACQUISITION),
						Mockito.eq(EnumService.GetSiri.getServiceName()), Mockito.eq("Sens_emission"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_emission", EnumService.GetSiri.getServiceName())));

		/*
		 * ****************************************************************************
		 * *************************** GetMultipleStopMonitoring **********************
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE_22), Mockito.eq(ModuleEnum.ACQUISITION),
						Mockito.eq(EnumService.GetMultipleStopMonitoring.getServiceName()), Mockito.eq("Sens_reception"),
						Mockito.any(Date.class), Mockito.any(Date.class)))
				.thenReturn(
						new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception",
								EnumService.GetMultipleStopMonitoring.getServiceName())));

		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE_22), Mockito.eq(ModuleEnum.ACQUISITION),
						Mockito.eq(EnumService.GetMultipleStopMonitoring.getServiceName()), Mockito.eq("Sens_emission"),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_emission", EnumService.GetMultipleStopMonitoring.getServiceName())));

		/*
		 * ****************************************************************************
		 * *************************** GeneralMessage *********************************
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE), Mockito.eq(ModuleEnum.ACQUISITION),
						Mockito.eq(EnumService.GetGeneralMessage.getServiceName()), Mockito.eq("Sens_reception"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception", EnumService.GetGeneralMessage.getServiceName())));

		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE), Mockito.eq(ModuleEnum.ACQUISITION),
						Mockito.eq(EnumService.GetGeneralMessage.getServiceName()), Mockito.eq("Sens_emission"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_emission", EnumService.GetGeneralMessage.getServiceName())));

		/*
		 * ****************************************************************************
		 * *************************** StopPointDiscovery *****************************
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE), Mockito.eq(ModuleEnum.ACQUISITION),
						Mockito.eq(EnumService.StopPointsDiscovery.getServiceName()), Mockito.eq("Sens_reception"),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception", EnumService.StopPointsDiscovery.getServiceName())));

		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE), Mockito.eq(ModuleEnum.ACQUISITION),
						Mockito.eq(EnumService.StopPointsDiscovery.getServiceName()), Mockito.eq("Sens_emission"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_emission", EnumService.StopPointsDiscovery.getServiceName())));

		/*
		 * ****************************************************************************
		 * *************************** EstimatedTimeTable *****************************
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE), Mockito.eq(ModuleEnum.ACQUISITION),
						Mockito.eq(EnumService.GetEstimatedTimetable.getServiceName()), Mockito.eq("Sens_reception"),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception", EnumService.GetEstimatedTimetable.getServiceName())));

		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE), Mockito.eq(ModuleEnum.ACQUISITION),
						Mockito.eq(EnumService.GetEstimatedTimetable.getServiceName()), Mockito.eq("Sens_emission"),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_emission", EnumService.GetEstimatedTimetable.getServiceName())));

		/*
		 * ****************************************************************************
		 * *************************** GeneralMessage *********************************
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE), Mockito.eq(ModuleEnum.EMISSION),
						Mockito.eq(EnumService.GetGeneralMessage.getServiceName()), Mockito.eq("Sens_reception"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception", EnumService.GetGeneralMessage.getServiceName())));

		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE), Mockito.eq(ModuleEnum.EMISSION),
						Mockito.eq(EnumService.GetGeneralMessage.getServiceName()), Mockito.eq("Sens_emission"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_emission", EnumService.GetGeneralMessage.getServiceName())));

		/*
		 * ****************************************************************************
		 * *************************** StopPointDiscovery *****************************
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE), Mockito.eq(ModuleEnum.EMISSION),
						Mockito.eq(EnumService.StopPointsDiscovery.getServiceName()), Mockito.eq("Sens_reception"),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception", EnumService.StopPointsDiscovery.getServiceName())));

		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE), Mockito.eq(ModuleEnum.EMISSION),
						Mockito.eq(EnumService.StopPointsDiscovery.getServiceName()), Mockito.eq("Sens_emission"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_emission", EnumService.StopPointsDiscovery.getServiceName())));

		/*
		 * ****************************************************************************
		 * *************************** LineDiscovery *****************************
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE), Mockito.eq(ModuleEnum.EMISSION),
						Mockito.eq(EnumService.LinesDiscovery.getServiceName()), Mockito.eq("Sens_reception"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception", "LineDiscovery")));

		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE), Mockito.eq(ModuleEnum.EMISSION),
						Mockito.eq(EnumService.LinesDiscovery.getServiceName()), Mockito.eq("Sens_emission"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_emission", "LineDiscovery")));

		/*
		 * ****************************************************************************
		 * *************************** GetSiri ****************************************
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE_24), Mockito.eq(ModuleEnum.EMISSION),
						Mockito.eq(EnumService.GetSiri.getServiceName()), Mockito.eq("Sens_reception"), Mockito.any(Date.class),
						Mockito.any(Date.class)))
				.thenReturn(new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception", "GetSiri")));

		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE_24), Mockito.eq(ModuleEnum.EMISSION),
						Mockito.eq(EnumService.GetSiri.getServiceName()), Mockito.eq("Sens_emission"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(new PageImpl<WSGenericMetierEntity>(generateData("Sens_emission", "GetSiri")));

		/*
		 * ****************************************************************************
		 * *************************** GetMultipleStopMonitoring **********************
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE_22), Mockito.eq(ModuleEnum.EMISSION),
						Mockito.eq(EnumService.GetMultipleStopMonitoring.getServiceName()), Mockito.eq("Sens_reception"),
						Mockito.any(Date.class), Mockito.any(Date.class)))
				.thenReturn(
						new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception",
								EnumService.GetMultipleStopMonitoring.getServiceName())));

		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.REQUETE_22), Mockito.eq(ModuleEnum.EMISSION),
						Mockito.eq(EnumService.GetMultipleStopMonitoring.getServiceName()), Mockito.eq("Sens_emission"),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_emission", EnumService.GetMultipleStopMonitoring.getServiceName())));

		/*
		 * ****************************************************************************
		 * *************************** EstimatedTimeTable *****************************
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.NOTIFICATION), Mockito.eq(ModuleEnum.ACQUISITION),
						Mockito.eq(EnumService.GetEstimatedTimetable.getServiceName()), Mockito.eq("Sens_reception"),
						Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception", EnumService.GetEstimatedTimetable.getServiceName())));

		/*
		 * ****************************************************************************
		 * *************************** GeneralMessage *********************************
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.NOTIFICATION), Mockito.eq(ModuleEnum.ACQUISITION),
						Mockito.eq(EnumService.GetGeneralMessage.getServiceName()), Mockito.eq("Sens_reception"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception", EnumService.GetGeneralMessage.getServiceName())));

		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.NOTIFICATION), Mockito.eq(ModuleEnum.EMISSION),
						Mockito.eq(EnumService.GetGeneralMessage.getServiceName()), Mockito.eq("Sens_emission"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_emission", EnumService.GetGeneralMessage.getServiceName())));

		/*
		 * ****************************************************************************
		 * *************************** StopMonitoring *********************************
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.NOTIFICATION), Mockito.eq(ModuleEnum.ACQUISITION),
						Mockito.eq(EnumService.GetStopMonitoring.getServiceName()), Mockito.eq("Sens_reception"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_reception", EnumService.GetStopMonitoring.getServiceName())));

		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.NOTIFICATION), Mockito.eq(ModuleEnum.EMISSION),
						Mockito.eq(EnumService.GetStopMonitoring.getServiceName()), Mockito.eq("Sens_emission"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_emission", EnumService.GetStopMonitoring.getServiceName())));

		/*
		 * ****************************************************************************
		 * *************************** Subscribe orchestration **************************
		 * ****************************************************************************
		 */
		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.NOTIFICATION), Mockito.eq(ModuleEnum.ORCHESTRATION),
						Mockito.eq(EnumService.Subscribe.getServiceName()), Mockito.eq("Sens_emission"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_emission", EnumService.Subscribe.getServiceName())));

		Mockito.when(
				elasticsearchBC.findByServiceAndSens(Mockito.eq(ModeEchange.NOTIFICATION), Mockito.eq(ModuleEnum.ORCHESTRATION),
						Mockito.eq(EnumService.Subscribe.getServiceName()), Mockito.eq("Sens_reception"), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(
				new PageImpl<WSGenericMetierEntity>(generateData("Sens_emission", EnumService.Subscribe.getServiceName())));

		// creation du rapport
		Date startDateTest = DateUtils.computeDate(2015, 11, 13, 0);
		Date endDateTest = DateUtils.computeDate(2015, 11, 13, 1);

		rapport1.build(startDateTest, endDateTest);

		Assert.assertTrue(rapport1.dataSource != null);
		//
		final File file = new File(rapport1.getFilePath());
		System.out.println(rapport1.getFilePath());

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
		// Données du mock
		for (int i = 0; i < 100; i++) {

			final Random r = new Random();
			final int valeur = r.nextInt(10);

			for (int j = 0; j < valeur; j++) {

				final WSMetierSuccessEntity echange = new WSMetierSuccessEntity();

				echange.setId(String.valueOf(1));
				echange.setMessage("2014-11-12 19:28:55,053 [ERROR] c.t.s.log.metier.acquisition.error - [12/11/2014 18:28:55.042]-[WS]-[GeneralMessage]-[SIRI]-[Unknown]-[Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE]-[Sens_reception]-[VRAI]-[null]-[0]-[null]-[SNCF-ACCES:Message::1:LOC]-[null]-[null]-[null]-[null]-[null]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[SNCF-ACCES]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[IVTR_ERR_30]");
				//echange.setHost("stif-pc-dev-03");
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
				echange.setRequestor_ref("RELAI");
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
				echange.setProducer_ref("SNCF-ACCES");
				echange.setDescription("La version de la requête ne correspond pas à la version du partenaire.");
				echange.setStatus("ok");
				echange.setCode_erreur_ivtr("IVTR_ERR_30");
				//echange.setDuration(604800);

				content.add(echange);
			}
		}

		return content;
	}
}
