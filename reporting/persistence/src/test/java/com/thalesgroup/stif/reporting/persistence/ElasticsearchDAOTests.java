package com.thalesgroup.stif.reporting.persistence;

import static org.hamcrest.core.Is.is;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sirh.mqd.commons.exchanges.enums.EnumErreursMetier;
import com.sirh.mqd.commons.exchanges.enums.EnumErrorCondition;
import com.sirh.mqd.commons.exchanges.enums.EnumService;
import com.sirh.mqd.commons.exchanges.enums.ModeEchange;
import com.sirh.mqd.commons.exchanges.enums.ModuleEnum;
import com.sirh.mqd.commons.traces.enums.EnumActionNature;
import com.sirh.mqd.commons.traces.enums.EnumActionType;
import com.sirh.mqd.commons.traces.enums.EnumEcranType;
import com.sirh.mqd.commons.traces.enums.UserActionEnum;
import com.sirh.mqd.reporting.persistence.dao.IElasticsearchDAO;
import com.sirh.mqd.reporting.persistence.entities.IHMActionEntity;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;
import com.sirh.mqd.reporting.persistence.entities.WSMetierSuccessEntity;
import com.sirh.mqd.reporting.persistence.entities.WSPerformanceEntity;

/**
 * Test DAO
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/persistence-test-elasticsearch-context.xml")
public class ElasticsearchDAOTests {

	@Autowired
	private ElasticsearchTemplate template;

	@Autowired
	private IElasticsearchDAO<WSGenericMetierEntity> echangeDao;

	@Before
	public void before() throws InterruptedException {
		template.deleteIndex(WSMetierSuccessEntity.class);
		template.createIndex(WSMetierSuccessEntity.class);
		template.putMapping(WSMetierSuccessEntity.class);
		Thread.sleep(500);

	}

	@Test
	public void findAllTest() {

		populateFindAll(EnumService.GetGeneralMessage.getServiceName(), "null");

		final Page<WSGenericMetierEntity> list = echangeDao.findAll();

		final Iterator<WSGenericMetierEntity> it = list.iterator();
		int i = 0;
		while (it.hasNext()) {
			final WSGenericMetierEntity e = it.next();
			System.out.println(i++ + " - " + e.getId() + " - " + e.getRequest_timestamp());
		}

		System.out.println(i + " elements");

		Assert.assertThat(list.getTotalElements(), is(5L));

	}

	@Test
	public void countNotificationByPartenaireTest() throws ParseException {

		populateFindAllNotification();
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		final Date dateDebut = formatter.parse("2013-11-12 19:28:55");
		final Date dateFin = formatter.parse("2015-11-13 19:28:55");

		// test avec un range date correcte
		final FacetedPage<WSGenericMetierEntity> result = echangeDao.findNotification(dateDebut, dateFin);

		Assert.assertEquals(25, result.getTotalElements());

	}

	@Test
	public void findByServiceTest() throws ParseException {

		populateFindAll(EnumService.GetGeneralMessage.getServiceName(), "null");

		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date dateDebut = formatter.parse("2013-11-12 19:28:55,053");
		Date dateFin = formatter.parse("2015-11-12 19:28:55,053");

		// test avec un range date correcte
		final Page<WSGenericMetierEntity> list = echangeDao.findByService(EnumService.GetGeneralMessage.getServiceName(), dateDebut,
				dateFin);

		Assert.assertThat(list.getTotalElements(), is(5L));

		dateDebut = formatter.parse("2012-11-12 19:28:55,053");
		dateFin = formatter.parse("2013-11-12 19:28:55,053");

		// test avec un range date sans aucune donnée
		final Page<WSGenericMetierEntity> list2 = echangeDao.findByService(EnumService.GetGeneralMessage.getServiceName(), dateDebut,
				dateFin);

		Assert.assertThat(list2.getTotalElements(), is(0L));

		dateDebut = formatter.parse("2013-11-12 19:28:55,053");
		dateFin = formatter.parse("2015-11-12 19:28:55,053");

		// test avec un nom de service inexistant
		final Page<WSGenericMetierEntity> list3 = echangeDao.findByService("generalXXXmessage", dateDebut, dateFin);

		Assert.assertThat(list3.getTotalElements(), is(0L));

	}

	@Test
	public void findByActionNature() throws ParseException {

		populateFindByActionNature();
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date dateDebut = formatter.parse("2013-11-12 19:28:55");
		Date dateFin = formatter.parse("2015-11-13 19:28:55");

		// test avec un range date correcte
		final Page<IHMActionEntity> list = echangeDao.findByActionNature(EnumActionNature.ILLEGAL_ACCESS.name(), dateDebut, dateFin);

		Assert.assertThat(list.getTotalElements(), is(17L));

		// test avec une autre nature
		final Page<IHMActionEntity> list3 = echangeDao.findByActionNature(EnumActionNature.SUCCESS.name(), dateDebut, dateFin);

		Assert.assertThat(list3.getTotalElements(), is(33L));

		// test avec un range date sans aucune donnée

		dateDebut = formatter.parse("2011-11-12 19:28:55");
		dateFin = formatter.parse("2012-11-13 19:28:55");

		final Page<IHMActionEntity> list2 = echangeDao.findByActionNature(EnumActionNature.ILLEGAL_ACCESS.name(), dateDebut, dateFin);

		Assert.assertThat(list2.getTotalElements(), is(0L));

	}

	@Test
	public void findByServiceAndSensTest() throws ParseException {

		populateFindAll(EnumService.GetStopMonitoring.getServiceName(), "null");

		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		final Date dateDebut = formatter.parse("2011-11-12 19:28:55,053");
		final Date dateFin = formatter.parse("2015-11-12 19:28:55,053");

		// test avec un range date correcte
		final Page<WSGenericMetierEntity> data = echangeDao.findByServiceAndSens(ModeEchange.REQUETE, ModuleEnum.ACQUISITION,
				EnumService.GetStopMonitoring.getServiceName(), "Sens_reception", dateDebut, dateFin);

		Assert.assertThat(data.getTotalElements(), is(5L));

	}

	@Test
	public void findByServiceAndSensAndStatusTest() throws ParseException {

		populateFindAll(EnumService.GetStopMonitoring.getServiceName(), "null");

		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		final Date dateDebut = formatter.parse("2011-11-12 19:28:55,053");
		final Date dateFin = formatter.parse("2015-11-12 19:28:55,053");

		// test avec un range date correcte
		final Page<WSGenericMetierEntity> data = echangeDao.findByServiceAndSensAndStatus(ModeEchange.REQUETE, ModuleEnum.ACQUISITION,
				EnumService.GetStopMonitoring.getServiceName(), "Sens_reception", Boolean.TRUE, dateDebut, dateFin);

		Assert.assertThat(data.getTotalElements(), is(5L));

	}

	@Test
	public void findByErreurMetierTest() throws ParseException {

		populateFindAll(EnumService.GetStopMonitoring.getServiceName(), EnumErreursMetier.IVTR_ERR_90.name());

		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		final Date dateDebut = formatter.parse("2011-11-12 19:28:55,053");
		final Date dateFin = formatter.parse("2015-11-12 19:28:55,053");

		// test avec un range date correcte
		final Page<WSGenericMetierEntity> data = echangeDao.findByErreurMetier("Sens_reception",
				Arrays.asList(EnumErreursMetier.IVTR_ERR_90, EnumErreursMetier.IVTR_ERR_39), dateDebut, dateFin);

		Assert.assertThat(data.getTotalElements(), is(5L));

	}

	@Test
	public void findByErreurConditionTest() throws ParseException {

		populateFindAll(EnumService.GetStopMonitoring.getServiceName(), "null");

		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		final Date dateDebut = formatter.parse("2011-11-12 19:28:55,053");
		final Date dateFin = formatter.parse("2015-11-12 19:28:55,053");

		// test avec un range date correcte
		final Page<WSGenericMetierEntity> data = echangeDao.findByErreurCondition(
				Arrays.asList(EnumErrorCondition.ACCESS_NOT_ALLOWED_ERROR), dateDebut, dateFin);

		Assert.assertThat(data.getTotalElements(), is(5L));

	}

	@Test
	public void findByErreurConditionAndServiceTest() throws ParseException {

		populateFindAll(EnumService.GetStopMonitoring.getServiceName(), "null");

		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		final Date dateDebut = formatter.parse("2011-11-12 19:28:55,053");
		final Date dateFin = formatter.parse("2015-11-12 19:28:55,053");

		// test avec un range date correcte
		final Page<WSGenericMetierEntity> data = echangeDao.findByErreurConditionAndService(Arrays.asList(EnumErrorCondition.values()),
				Arrays.asList(EnumService.values()), dateDebut, dateFin);

		Assert.assertThat(data.getTotalElements(), is(5L));

	}

	@Test
	public void findTest() throws ParseException {

		populateFindPerformance("getGeneralMessage");

		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		final Date dateDebut = formatter.parse("2013-11-12 19:28:55,053");
		final Date dateFin = formatter.parse("2015-11-12 19:28:55,053");

		// test avec un range date correcte
		final Page<WSPerformanceEntity> list = echangeDao.find(ModuleEnum.ACQUISITION.name(), dateDebut, dateFin);

		Assert.assertThat(list.getTotalElements(), is(5L));

	}

	private void populateFindPerformance(final String service) throws ParseException {

		for (int i = 0; i < 5; i++) {

			final WSPerformanceEntity echange = new WSPerformanceEntity();
			echange.setId(String.valueOf(i));
			echange.setMessage("2014-12-15 19:08:27,030 [INFO] c.t.stif.log.system.performance - [Service][getGeneralMessage][10]");
			echange.setPath("/var/log/stif/performance/acquisition-system-perf.log");
			echange.setLog_date("2014-12-15T19:03:55.906Z");
			echange.setLog_level("INFO");
			echange.setClassname("c.t.s.log.metier.acquisition.error");
			echange.setComposant(ModuleEnum.ACQUISITION.name());
			echange.setService(service);

			final IndexQuery indexQuery = new IndexQueryBuilder().withObject(echange).withIndexName("logstash_perf")
					.withId(echange.getId()).withType("logs").build();

			template.bulkIndex(Arrays.asList(indexQuery));
			template.refresh("logstash_perf", true);

		}

	}

	private void populateFindAll(final String service, final String erreur) {

		for (int i = 0; i < 5; i++) {

			final WSMetierSuccessEntity echange = new WSMetierSuccessEntity();
			echange.setId(String.valueOf(i));
			echange.setMessage("2014-11-12 19:28:55,053 [ERROR] c.t.s.log.metier.acquisition.error - [12/11/2014 18:28:55.042]-[WS]-[GeneralMessage]-[SIRI]-[Unknown]-[Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE]-[Sens_reception]-[VRAI]-[null]-[0]-[null]-[SNCF-ACCES:Message::1:LOC]-[null]-[null]-[null]-[null]-[null]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[SNCF-ACCES]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[IVTR_ERR_30]");
			//echange.setHost("stif-pc-dev-03");
			echange.setPath("/var/log/stif/metier/acquisition-relais-error.log");
			echange.setLog_date("2014-11-12 19:28:55,053");
			echange.setLog_level("ERROR");
			echange.setClassname("c.t.s.log.metier.acquisition.error");
			echange.setRequest_timestamp("2014-11-12 19:28:55,053");
			echange.setType_message("WS");
			echange.setService(service);
			echange.setDomaine("SIRI");
			echange.setType_structure("Unknown");
			echange.setAction_value("Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE");
			echange.setMode("REQUETE");
			echange.setSens("Sens_reception");
			echange.setContrat("VRAI");
			echange.setRequestor_ref("RELAI");
			echange.setTaille(25);
			echange.setLine_ref("lineRef");
			echange.setMessage_identifier("SNCF-ACCES:Message::1:LOC");
			echange.setMonitoring_ref("monitoring_ref");
			echange.setOperator_ref("operator_ref");
			echange.setSubscription_ref("subscription_ref");
			echange.setError_condition_description("description");
			echange.setError_condition_structure(EnumErrorCondition.ACCESS_NOT_ALLOWED_ERROR.getErrorConditionStructure());
			echange.setError_condition_error_text("La version de la requête ne correspond pas à la version du partenaire.");
			echange.setError_condition_specific_field("specific field");
			echange.setProducer_ref("SNCF-ACCES");
			echange.setDescription("La version de la requête ne correspond pas à la version du partenaire.");
			echange.setStatus(Boolean.TRUE.toString());
			echange.setCode_erreur_ivtr(erreur);
			//echange.setDuration(604800);

			final IndexQuery indexQuery = new IndexQueryBuilder().withObject(echange).withIndexName("logstash").withId(echange.getId())
					.withType("logs").build();

			template.bulkIndex(Arrays.asList(indexQuery));
			template.refresh("logstash", true);

		}

	}

	private void populateFindByActionNature() {

		for (int i = 0; i < 50; i++) {

			final IHMActionEntity action = new IHMActionEntity();
			action.setId(String.valueOf(i));
			action.setMessage("2014-11-25 12:31:20,166 [INFO] c.thalesgroup.stif.log.system.action - [ACTION_IHM][r@r.r][ROLE_ADMINISTRATEUR][CREATION_MODIFICATION][SUCCESS][SITE][null][{\"id\":10,\"name\":\"CRON_RAPPORT\",\"value\":\"0 0 0 2 1/1 ?\",\"description\":\"Cron pour les rapports\"}]");
			action.setPath("/var/log/stif/metier/acquisition-relais-error.log");
			action.setLog_date("2014-11-25 12:31:20,166");
			action.setLog_level("ERROR");
			action.setClassname("c.t.s.log.metier.acquisition.error");
			action.setAction_ihm(UserActionEnum.ACTION_IHM.name());
			action.setEmail("r@r.r");
			action.setRole("ROLE_ADMINISTRATEUR");
			action.setAction_type(EnumActionType.CREATION_MODIFICATION.name());
			if (i > 32) {
				action.setAction_nature(EnumActionNature.ILLEGAL_ACCESS.name());
			} else {
				action.setAction_nature(EnumActionNature.SUCCESS.name());
			}
			action.setEcran_type(EnumEcranType.SITE.name());
			action.setId_metier(null);

			final IndexQuery indexQuery = new IndexQueryBuilder().withObject(action).withIndexName("logstash_ihm").withId(action.getId())
					.withType("logs").build();

			template.bulkIndex(Arrays.asList(indexQuery));
			template.refresh("logstash_ihm", true);

		}

	}

	private void populateFindAllNotification() {

		for (int i = 0; i < 150; i++) {

			final WSMetierSuccessEntity echange = new WSMetierSuccessEntity();
			echange.setId(String.valueOf(i));
			echange.setMessage("2014-11-12 19:28:55,053 [ERROR] c.t.s.log.metier.acquisition.error - [12/11/2014 18:28:55.042]-[WS]-[GeneralMessage]-[SIRI]-[Unknown]-[Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE]-[Sens_reception]-[VRAI]-[null]-[0]-[null]-[SNCF-ACCES:Message::1:LOC]-[null]-[null]-[null]-[null]-[null]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[SNCF-ACCES]-[La version de la requête ne correspond pas à la version du partenaire.]-[null]-[IVTR_ERR_30]");
			//echange.setHost("stif-pc-dev-03");
			echange.setPath("/var/log/stif/metier/acquisition-relais-error.log");
			echange.setLog_date("2014-11-12 19:28:55,053");
			echange.setLog_level("ERROR");
			echange.setClassname("c.t.s.log.metier.acquisition.error");
			echange.setRequest_timestamp("2014-11-12 19:28:55,053");
			echange.setType_message("WS");
			echange.setService("GeneralMessage");
			echange.setDomaine("SIRI");
			echange.setType_structure("Unknown");
			echange.setAction_value("Réception d'une réponse/notification d'un producteur sur le service: GeneralMessage]-[REQUETE");
			if (i >= 25) {
				echange.setMode(ModeEchange.INCONNU.name());
			} else {
				echange.setMode(ModeEchange.NOTIFICATION.name());

			}
			echange.setSens("Sens_reception");
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

			final IndexQuery indexQuery = new IndexQueryBuilder().withObject(echange).withIndexName("logstash").withId(echange.getId())
					.withType("logs").build();

			template.bulkIndex(Arrays.asList(indexQuery));
			template.refresh("logstash", true);

		}

	}
}
