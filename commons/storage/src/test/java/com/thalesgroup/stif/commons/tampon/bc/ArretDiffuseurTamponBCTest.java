package com.thalesgroup.stif.commons.tampon.bc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sirh.mqd.commons.storage.bc.ArretDiffuseurTamponBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.test.EmbeddedRedisThales;
import com.sirh.mqd.commons.test.JedisConnectionFactoryThales;
import com.sirh.mqd.commons.test.EmbeddedRedisThales.EmbeddedRedisRuleBuilderThales;
import com.thalesgroup.stif.commons.echange.dto.arretDiffuseur.ArretDiffuseurTamponDTO;
import com.thalesgroup.stif.commons.echange.dto.participant.ParticipantTamponDTO;
import com.thalesgroup.stif.commons.echange.utils.RedisUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tampon-context-test.xml")
public class ArretDiffuseurTamponBCTest {

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
	private ArretDiffuseurTamponBC arretTamponBC;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Jedis jedis = EmbeddedRedisInstances.getInstance().getDefaultJedis();
		jedisConnectionFactory.setJedis(jedis);
	}

	@Test
	public void testGetArretDiffuseurs() {
		//Given
		Map<String, List<ArretDiffuseurTamponDTO>> mapArretDiffuseurTampon = new HashMap<>();
		String key = RedisUtils.genererKeyArretDiffuseur("STIF:StopPoint:Q:108925:");
		List<ArretDiffuseurTamponDTO> list = new ArrayList<>();
		ArretDiffuseurTamponDTO arretDiffuseurTamponDTO = new ArretDiffuseurTamponDTO();
		arretDiffuseurTamponDTO.setId(1);
		arretDiffuseurTamponDTO.setMonitoringRef("108925");
		ParticipantTamponDTO participantTamponDTO = new ParticipantTamponDTO();
		participantTamponDTO.setName("SNCF");
		participantTamponDTO.setParticipantRef("SNCF-ACCES");
		participantTamponDTO.setSiriVersion("2.4");
		arretDiffuseurTamponDTO.setParticipantTamponDTO(participantTamponDTO);
		list.add(arretDiffuseurTamponDTO);
		mapArretDiffuseurTampon.put(key, list);
		arretTamponBC.ajouterArretDiffuseurs(mapArretDiffuseurTampon, null, null);

		//When
		List<ArretDiffuseurTamponDTO> listTampon = arretTamponBC.remonterArretDiffuseur(key);
		//Then
		Assertions.assertThat(listTampon.size()).isNotNull();
		Assertions.assertThat(listTampon.get(0).getMonitoringRef()).isEqualTo("108925");
	}
}
