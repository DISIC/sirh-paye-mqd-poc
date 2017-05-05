package com.thalesgroup.stif.commons.tampon.bc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import com.sirh.mqd.commons.exchanges.enums.TypeObjetReflex;
import com.sirh.mqd.commons.exchanges.exception.ApplicationException;
import com.sirh.mqd.commons.storage.bc.ReflexTamponBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IReflexTamponDAO;
import com.sirh.mqd.commons.test.EmbeddedRedisThales;
import com.sirh.mqd.commons.test.JedisConnectionFactoryThales;
import com.sirh.mqd.commons.test.EmbeddedRedisThales.EmbeddedRedisRuleBuilderThales;
import com.thalesgroup.stif.commons.echange.dto.reflex.ReflexTamponDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tampon-context-test.xml")
@UsingDataSet(locations = "/dataset-reflex.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
public class ReflexTamponBCIntTest {

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

	@Autowired
	ReflexTamponBC reflexTamponBC;

	@Autowired
	IReflexTamponDAO reflexTamponDAO;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Jedis jedis = EmbeddedRedisInstances.getInstance().getDefaultJedis();
		jedisConnectionFactory.setJedis(jedis);
	}

	@Test
	public void testAjouterObjetReflex22() throws ApplicationException {
		ReflexTamponDTO reflexTampon = new ReflexTamponDTO();
		reflexTampon.setIdRefA("idRefA");
		reflexTampon.setIdTypeArret("idTypeArret");
		reflexTampon.setIdVersion("idVersion");
		reflexTampon.setNom("nom");
		reflexTampon.setTypeObjetReflex(TypeObjetReflex.ZDE);
		reflexTamponBC.ajouterObjetReflex(reflexTampon, Long.valueOf(86460));

		String monitoringRef = "STIF:StopPoint:Q:idRefA:";
		List<String> list = reflexTamponBC.rechercheZDE22(monitoringRef);
		Assertions.assertThat(list).isNotEmpty();
		Assertions.assertThat(list.get(0)).isEqualTo(monitoringRef);
	}

	@Test
	public void testAjouterObjetReflex24() throws ApplicationException {
		ReflexTamponDTO reflexTampon = new ReflexTamponDTO();
		reflexTampon.setIdRefA("idRefB");
		reflexTampon.setIdTypeArret("idTypeArret");
		reflexTampon.setIdVersion("idVersion");
		reflexTampon.setNom("nom");
		reflexTampon.setTypeObjetReflex(TypeObjetReflex.ZDE);
		reflexTamponBC.ajouterObjetReflex(reflexTampon, Long.valueOf(86460));

		String monitoringRef = "STIF:StopPoint:Q:idRefB:";
		List<String> list = reflexTamponBC.rechercheZDE24(monitoringRef);
		Assertions.assertThat(list).isNotEmpty();
		Assertions.assertThat(list.get(0)).isEqualTo(monitoringRef);
	}

	@Test
	public void testAjouterObjetsReflex() throws ApplicationException {
		Set<ReflexTamponDTO> listReflexTamponDTO = new HashSet<ReflexTamponDTO>();
		ReflexTamponDTO reflexTampon = new ReflexTamponDTO();
		ReflexTamponDTO reflexTampon1 = new ReflexTamponDTO();

		reflexTampon.setIdRefA("idRefA");
		reflexTampon.setIdTypeArret("idTypeArret");
		reflexTampon.setIdVersion("idVersion");
		reflexTampon.setNom("nom");
		reflexTampon.setTypeObjetReflex(TypeObjetReflex.ZDE);
		reflexTampon1.setIdRefA("idRefAA");
		reflexTampon1.setIdTypeArret("idTypeArret");
		reflexTampon1.setIdVersion("idVersion");
		reflexTampon1.setNom("nom1");
		reflexTampon1.setTypeObjetReflex(TypeObjetReflex.ZDE);

		listReflexTamponDTO.add(reflexTampon);
		listReflexTamponDTO.add(reflexTampon1);
		reflexTamponBC.ajouterObjetsReflex(listReflexTamponDTO, Long.valueOf(86460));

		String monitoringRef = "STIF:StopPoint:Q:idRefA:";
		ReflexTamponDTO result = reflexTamponBC.rechercherObjetReflex(monitoringRef);
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getIdRefA()).isEqualTo("idRefA");

		String monitoringRef1 = "STIF:StopPoint:Q:idRefAA:";
		ReflexTamponDTO result1 = reflexTamponBC.rechercherObjetReflex(monitoringRef1);
		Assertions.assertThat(result1).isNotNull();
		Assertions.assertThat(result1.getIdRefA()).isEqualTo("idRefAA");
	}

}