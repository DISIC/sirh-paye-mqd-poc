package com.thalesgroup.stif.commons.tampon.bc;

import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;

import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.redis.EmbeddedRedisInstances;
import com.lordofthejars.nosqlunit.redis.RedisRule;
import com.lordofthejars.nosqlunit.redis.RedisRule.RedisRuleBuilder;
import com.sirh.mqd.commons.exchanges.enums.EnumGmFilterType;
import com.sirh.mqd.commons.exchanges.enums.TypeObjetReflex;
import com.sirh.mqd.commons.exchanges.exception.ApplicationException;
import com.sirh.mqd.commons.storage.bc.ReflexTamponBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IReflexTamponDAO;
import com.sirh.mqd.commons.test.EmbeddedRedisThales;
import com.sirh.mqd.commons.test.JedisConnectionFactoryThales;
import com.sirh.mqd.commons.test.EmbeddedRedisThales.EmbeddedRedisRuleBuilderThales;
import com.thalesgroup.stif.commons.echange.dto.reflex.ReflexTamponDTO;
import com.thalesgroup.stif.commons.echange.utils.SiriUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tampon-context-test.xml")
@UsingDataSet(locations = "/dataset-reflex.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
public class ReflexTamponBCUnitTest {

	/**
	 * Injection du Contexte SPRING
	 */
	@Autowired
	private ApplicationContext applicationContext;

	@ClassRule
	public static EmbeddedRedisThales embeddedRedis = EmbeddedRedisRuleBuilderThales.newEmbeddedRedisRule().build();

	@Rule
	public RedisRule redisRule = RedisRuleBuilder.newRedisRule().defaultEmbeddedRedis();

	@Autowired
	@Qualifier(PersistenceConstantes.JEDIS_CONNECTION_FACTORY)
	private MongodbConnectionFactory jedisConnectionFactory;

	@InjectMocks
	ReflexTamponBC reflexTamponBC = new ReflexTamponBC();

	@Mock
	IReflexTamponDAO reflexTamponDAO;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Jedis jedis = EmbeddedRedisInstances.getInstance().getDefaultJedis();
		jedisConnectionFactory.setJedis(jedis);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testRechercheZDECasZDE() throws Exception {
		//given
		String monitoringRef = "STIF:StopPoint:Q:2:";
		ReflexTamponDTO value = new ReflexTamponDTO();
		value.setTypeObjetReflex(TypeObjetReflex.ZDE);
		value.setIdRefA("2");
		Mockito.when(reflexTamponDAO.get(SiriUtils.extraireIdTechnique(monitoringRef))).thenReturn(value);

		//when
		List<String> actual22 = reflexTamponBC.rechercheZDE22(monitoringRef);

		//then
		Assertions.assertThat(actual22).isNotEmpty();
		Assertions.assertThat(actual22.get(0)).isEqualTo("STIF:StopPoint:Q:2:");

		//when
		List<String> actual24 = reflexTamponBC.rechercheZDE24(monitoringRef);

		//then
		Assertions.assertThat(actual24).isNotEmpty();
		Assertions.assertThat(actual24.get(0)).isEqualTo("STIF:StopPoint:Q:2:");
	}

	@Test
	public void testRechercheZDECasGDL() throws Exception {
		//given
		String monitoringRef = "STIF:StopArea:SP:2:";
		ReflexTamponDTO value = new ReflexTamponDTO();
		value.setTypeObjetReflex(TypeObjetReflex.GDL);
		value.setIdRefA("2");
		value.addZoneEmbarquement("12");
		Mockito.when(reflexTamponDAO.get(SiriUtils.extraireIdTechnique(monitoringRef))).thenReturn(value);

		//when
		List<String> actual22 = reflexTamponBC.rechercheZDE22(monitoringRef);

		//then
		Assertions.assertThat(actual22).isNotEmpty();
		Assertions.assertThat(actual22.get(0)).isEqualTo("STIF:StopPoint:Q:12:");

		//when
		List<String> actual24 = reflexTamponBC.rechercheZDE24(monitoringRef);

		//then
		Assertions.assertThat(actual24).isNotEmpty();
		Assertions.assertThat(actual24.get(0)).isEqualTo("STIF:StopPoint:Q:12:");
	}

	@Test(expected = ApplicationException.class)
	public void testRechercheZDEException() throws Exception {
		//given
		String monitoringRef = "STIF:StopPoint:Q:2:";

		//when
		reflexTamponBC.rechercheZDE22(monitoringRef);
		//when
		reflexTamponBC.rechercheZDE24(monitoringRef);
	}

	@Test
	public void testVerifierIdArret() throws ApplicationException {
		String monitoringRef = "STIF:StopPoint:Q:109309:LOC";
		reflexTamponBC.verifierIdArret(monitoringRef, EnumGmFilterType.STOP_POINT_REF.getName());
	}

	@Test(expected = ApplicationException.class)
	public void testVerifierIdArretInvalidDataReferenceExceptionMonitoringRefReflex() throws ApplicationException {
		String monitoringRef = "STIF:StopPoint:Q:10930";
		reflexTamponBC.verifierIdArret(monitoringRef, EnumGmFilterType.STOP_POINT_REF.getName());
	}

	@Test(expected = ApplicationException.class)
	public void testVerifierIdArretInvalidDataReferenceExceptionPattern() throws ApplicationException {
		String monitoringRef = null;
		reflexTamponBC.verifierIdArret(monitoringRef, EnumGmFilterType.STOP_POINT_REF.getName());
	}

	@Test(expected = ApplicationException.class)
	public void testRechercherObjetReflexMonitoringRefReflexException() throws ApplicationException {
		ReflexTamponDTO reflexTamponA = new ReflexTamponDTO();
		//		reflexTamponA.setIdRefA("109309");
		String monitoringRefA = "STIF:StopPoint:Q:109309:";

		// STIF-430
		List<String> listA = reflexTamponBC.rechercheZDE22(monitoringRefA);
		Assertions.assertThat(listA).isNotEmpty();
		Assertions.assertThat(listA.get(0)).isEqualTo(reflexTamponA.getIdRefA());

		ReflexTamponDTO reflexTamponB = new ReflexTamponDTO();
		//		reflexTampon.setIdRefA("110070");
		String monitoringRefB = "STIF:StopPoint:Q:110070:";

		// STIF-430
		List<String> listB = reflexTamponBC.rechercheZDE22(monitoringRefB);
		Assertions.assertThat(listB).isNotEmpty();
		Assertions.assertThat(listB.get(0)).isEqualTo(reflexTamponB.getIdRefA());

	}
}