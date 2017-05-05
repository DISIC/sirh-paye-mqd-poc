package com.thalesgroup.stif.commons.tampon.bc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

import com.lordofthejars.nosqlunit.redis.EmbeddedRedisInstances;
import com.lordofthejars.nosqlunit.redis.RedisRule;
import com.lordofthejars.nosqlunit.redis.RedisRule.RedisRuleBuilder;
import com.sirh.mqd.commons.storage.bc.SubscriptionTamponBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.test.EmbeddedRedisThales;
import com.sirh.mqd.commons.test.JedisConnectionFactoryThales;
import com.sirh.mqd.commons.test.EmbeddedRedisThales.EmbeddedRedisRuleBuilderThales;
import com.thalesgroup.stif.commons.echange.dto.SubscriptionDiffuseurTamponDTO;
import com.thalesgroup.stif.commons.echange.utils.RedisUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tampon-context-test.xml")
//@UsingDataSet(locations = "/dataset-subscription.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
public class SubscriptionTamponBCTest {

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
	private SubscriptionTamponBC subscriptionTamponBC;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Jedis jedis = EmbeddedRedisInstances.getInstance().getDefaultJedis();
		jedisConnectionFactory.setJedis(jedis);
	}

	@Test
	public void testGetSubscriptions() {
		//Given
		Map<String, List<SubscriptionDiffuseurTamponDTO>> mapSubscriptionDiffuseurTampon = new HashMap<>();
		String key = RedisUtils.genererKeySubscriptionDiffuseurSM("STIF:StopPoint:Q:108925:");
		List<SubscriptionDiffuseurTamponDTO> list = new ArrayList<>();
		SubscriptionDiffuseurTamponDTO subscriptionDiffuseurTamponDTO = new SubscriptionDiffuseurTamponDTO();
		subscriptionDiffuseurTamponDTO.setMonitoringRef("STIF:StopPoint:Q:108925:");
		subscriptionDiffuseurTamponDTO.setPartenaireActif(true);
		subscriptionDiffuseurTamponDTO.setSubscriptionRef("SRpc13");
		list.add(subscriptionDiffuseurTamponDTO);
		mapSubscriptionDiffuseurTampon.put(key, list);
		subscriptionTamponBC.ajouterSubscriptionsSMDiffuseur(mapSubscriptionDiffuseurTampon, new Long(28), TimeUnit.HOURS);

		//When
		List<SubscriptionDiffuseurTamponDTO> listTampon = subscriptionTamponBC.remonterSubscriptionDiffuseur(key);
		//Then
		Assertions.assertThat(listTampon.size()).isNotNull();
		Assertions.assertThat(listTampon.get(0).getMonitoringRef()).isEqualTo("STIF:StopPoint:Q:108925:");
	}

	@Test
	public void testSupprimerSubscriptions() {
		//Given
		Map<String, List<SubscriptionDiffuseurTamponDTO>> mapSubscriptionDiffuseurTampon = new HashMap<>();
		String key = RedisUtils.genererKeySubscriptionDiffuseurSM("STIF:StopPoint:Q:108925:");
		List<SubscriptionDiffuseurTamponDTO> list = new ArrayList<>();
		SubscriptionDiffuseurTamponDTO subscriptionDiffuseurTamponDTO = new SubscriptionDiffuseurTamponDTO();
		subscriptionDiffuseurTamponDTO.setMonitoringRef("STIF:StopPoint:Q:108925:");
		subscriptionDiffuseurTamponDTO.setPartenaireActif(true);
		subscriptionDiffuseurTamponDTO.setSubscriptionRef("SRpc13");
		list.add(subscriptionDiffuseurTamponDTO);
		mapSubscriptionDiffuseurTampon.put(key, list);
		subscriptionTamponBC.ajouterSubscriptionsSMDiffuseur(mapSubscriptionDiffuseurTampon, new Long(28), TimeUnit.HOURS);

		//When
		subscriptionTamponBC.suppressionSubscription(key);
		//Then
		List<SubscriptionDiffuseurTamponDTO> listTampon = subscriptionTamponBC.remonterSubscriptionDiffuseur(key);
		Assertions.assertThat(listTampon).isEmpty();
	}
}
