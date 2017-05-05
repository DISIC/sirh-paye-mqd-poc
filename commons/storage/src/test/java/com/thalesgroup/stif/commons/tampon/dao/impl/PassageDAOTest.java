package com.thalesgroup.stif.commons.tampon.dao.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
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
import com.sirh.mqd.commons.exchanges.dto.pivot.Course;
import com.sirh.mqd.commons.exchanges.dto.pivot.Passage;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IPassageDAO;
import com.sirh.mqd.commons.test.EmbeddedRedisThales;
import com.sirh.mqd.commons.test.JedisConnectionFactoryThales;
import com.sirh.mqd.commons.test.EmbeddedRedisThales.EmbeddedRedisRuleBuilderThales;
import com.thalesgroup.stif.commons.echange.utils.RedisUtils;
import com.thalesgroup.stif.commons.test.reflection.util.ReflectionUtil;
import com.thalesgroup.stif.commons.test.reflection.util.ReflectionUtil.ReflectionCallBack;
import com.thalesgroup.stif.commons.test.reflection.util.StifToStringStyle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tampon-context-test.xml")
@UsingDataSet(locations = "/dataset-messages.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
public class PassageDAOTest {

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

	@Before
	public void before() {
		Jedis jedis = EmbeddedRedisInstances.getInstance().getDefaultJedis();
		jedisConnectionFactory.setJedis(jedis);
	}

	/**
	 * Bean à tester
	 */
	@Autowired
	private IPassageDAO passageDAO;

	/**
	 * Génération de l'object Passage
	 * 
	 * @param prefix
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private Passage getNewPassage(final String prefix) throws IllegalArgumentException, IllegalAccessException {
		Passage passage = new Passage();
		ReflectionUtil.fill(prefix, passage, new ReflectionCallBack() {

			@Override
			public Object getValue(final Class<?> type, final String name) throws IllegalArgumentException, IllegalAccessException {
				if (type == Course.class) {
					Course course = new Course();
					ReflectionUtil.fill(prefix, course, new ReflectionCallBack() {

						@Override
						public Object getValue(final Class<?> type, final String name) {

							return ReflectionUtil.DEFAULT_VALUE;
						}

					});
					return course;
				}
				return ReflectionUtil.DEFAULT_VALUE;
			}

		});
		return passage;

	}

	@Test
	public void testAjouterPassage() throws Exception {
		Long duree = 1L;
		TimeUnit timeUnit = TimeUnit.MILLISECONDS;
		Passage passage1 = getNewPassage("1_");
		Passage passage2 = getNewPassage("2_");

		HashMap<String, String> expectedPassage = new HashMap<String, String>();
		expectedPassage.put(RedisUtils.genererKeyPassage(passage1),
				ReflectionToStringBuilder.toString(passage1, StifToStringStyle.RECURSIF_STYLE));
		expectedPassage.put(RedisUtils.genererKeyPassage(passage2),
				ReflectionToStringBuilder.toString(passage2, StifToStringStyle.RECURSIF_STYLE));

		passageDAO.ajouterPassage(passage1, duree, timeUnit, null);
		passageDAO.ajouterPassage(passage2, duree, timeUnit, null);

		//Les ajouts de passages sont asynchrone, on ajoute donc un temps d'attente.
		Thread.sleep(500);

		HashSet<String> idsPassage = new HashSet<>();
		idsPassage.add(RedisUtils.genererKeyPassage(passage1));
		idsPassage.add(RedisUtils.genererKeyPassage(passage2));
		List<Passage> result = passageDAO.selectPassageAModifier(idsPassage);

		Assertions.assertThat(result).isNotEmpty();
		Assertions.assertThat(result.size()).isEqualTo(idsPassage.size());

		for (int i = 0; i < idsPassage.size(); i++) {
			Assertions.assertThat(result.get(i)).isNotNull();
			String dumpPassage = ReflectionToStringBuilder.toString(result.get(i), StifToStringStyle.RECURSIF_STYLE);
			String key = idsPassage.toArray(new String[0])[i];
			Assertions.assertThat(dumpPassage).isEqualTo(expectedPassage.get(key));
		}
	}

}
