package com.thalesgroup.stif.commons.tampon.bc;

import java.util.Calendar;
import java.util.Date;

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
import com.sirh.mqd.commons.storage.bc.EtatSystemeTamponBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IEtatSystemeTamponDAO;
import com.sirh.mqd.commons.test.EmbeddedRedisThales;
import com.sirh.mqd.commons.test.JedisConnectionFactoryThales;
import com.sirh.mqd.commons.test.EmbeddedRedisThales.EmbeddedRedisRuleBuilderThales;
import com.sirh.mqd.commons.utils.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tampon-context-test.xml")
@UsingDataSet(locations = "/dataset-codifligne.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
public class EtatSystemeTamponBCTest {

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
	EtatSystemeTamponBC etatSystemeTamponBC;

	@Autowired
	IEtatSystemeTamponDAO etatSystemeTamponDAO;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Jedis jedis = EmbeddedRedisInstances.getInstance().getDefaultJedis();
		jedisConnectionFactory.setJedis(jedis);
	}

	@Test
	public void testVerifierEtatSystemeTest() {
		boolean status = etatSystemeTamponBC.verifierEtatSysteme();

		Assertions.assertThat(status).isFalse();
	}

	@Test
	public void testEtatSysteme() throws InterruptedException {

		//Given
		Date currentDate = DateUtils.getCalendarInstance().getTime();
		Calendar currentCal = DateUtils.getCalendarInstance();
		Calendar whenCal = DateUtils.getCalendarInstance();

		// Ajout d'une pause sinon test trop rapide et ne valide pas le test de date
		Thread.sleep(1000);

		//When
		etatSystemeTamponBC.ajouterEtatSysteme();
		Date whenDate = etatSystemeTamponBC.rechercherEtatSysteme();
		whenCal.setTime(whenDate);

		//Then
		Assertions.assertThat(whenDate.after(currentDate)).isTrue();
		Assertions.assertThat(whenCal.get(Calendar.DATE)).isEqualTo(currentCal.get(Calendar.DATE));
		Assertions.assertThat(whenCal.get(Calendar.MONTH)).isEqualTo(currentCal.get(Calendar.MONTH));
		Assertions.assertThat(whenCal.get(Calendar.YEAR)).isEqualTo(currentCal.get(Calendar.YEAR));
	}
}
