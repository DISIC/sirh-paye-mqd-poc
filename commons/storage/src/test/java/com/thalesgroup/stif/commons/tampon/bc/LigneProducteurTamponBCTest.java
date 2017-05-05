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
import com.sirh.mqd.commons.storage.bc.LigneProducteurTamponBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.test.EmbeddedRedisThales;
import com.sirh.mqd.commons.test.JedisConnectionFactoryThales;
import com.sirh.mqd.commons.test.EmbeddedRedisThales.EmbeddedRedisRuleBuilderThales;
import com.thalesgroup.stif.commons.echange.dto.ligneProducteur.LigneProducteurTamponDTO;
import com.thalesgroup.stif.commons.echange.dto.participant.ParticipantTamponDTO;
import com.thalesgroup.stif.commons.echange.utils.RedisUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tampon-context-test.xml")
public class LigneProducteurTamponBCTest {

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
	private LigneProducteurTamponBC ligneProducteurTamponBC;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Jedis jedis = EmbeddedRedisInstances.getInstance().getDefaultJedis();
		jedisConnectionFactory.setJedis(jedis);
	}

	@Test
	public void testGetLigneProducteur() {
		//Given
		Map<String, List<LigneProducteurTamponDTO>> mapLigneProducteurTampon = new HashMap<>();
		String key = RedisUtils.genererKeyLigneProducteur("STIF:Line:C00041:");
		List<LigneProducteurTamponDTO> list = new ArrayList<>();
		LigneProducteurTamponDTO ligneProducteurTamponDTO = new LigneProducteurTamponDTO();
		ligneProducteurTamponDTO.setId(1);
		ligneProducteurTamponDTO.setLineRef("C00041");
		ParticipantTamponDTO participantTamponDTO = new ParticipantTamponDTO();
		participantTamponDTO.setName("SNCF");
		participantTamponDTO.setParticipantRef("SNCF-ACCES");
		participantTamponDTO.setSiriVersion("2.4");
		ligneProducteurTamponDTO.setParticipantTamponDTO(participantTamponDTO);
		list.add(ligneProducteurTamponDTO);
		mapLigneProducteurTampon.put(key, list);
		ligneProducteurTamponBC.ajouterLigneProducteur(mapLigneProducteurTampon, null, null);

		//When
		List<LigneProducteurTamponDTO> listTampon = ligneProducteurTamponBC.remonterLigneProducteur(key);
		//Then
		Assertions.assertThat(listTampon.size()).isNotNull();
		Assertions.assertThat(listTampon.get(0).getLineRef()).isEqualTo("C00041");
	}
}
