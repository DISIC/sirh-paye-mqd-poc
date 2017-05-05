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
import com.sirh.mqd.commons.storage.bc.ArretLigneTamponBC;
import com.sirh.mqd.commons.storage.bc.PassageBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.test.EmbeddedRedisThales;
import com.sirh.mqd.commons.test.JedisConnectionFactoryThales;
import com.sirh.mqd.commons.test.EmbeddedRedisThales.EmbeddedRedisRuleBuilderThales;
import com.thalesgroup.stif.commons.echange.dto.arretLigne.ArretLigneTamponDTO;
import com.thalesgroup.stif.commons.echange.utils.RedisUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tampon-context-test.xml")
public class ArretLigneTamponBCTest {

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
	private ArretLigneTamponBC arretLigneTamponBC;

	@Autowired
	private PassageBC passageBC;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Jedis jedis = EmbeddedRedisInstances.getInstance().getDefaultJedis();
		jedisConnectionFactory.setJedis(jedis);
	}

	@Test
	public void testGetArretLigne() {
		//Given
		Map<String, List<ArretLigneTamponDTO>> mapArretLigneTampon = new HashMap<>();
		String id = "STIF:StopPoint:Q:109311:";
		String key = RedisUtils.genererKeyAssociationArretLigne(id);
		List<ArretLigneTamponDTO> list = new ArrayList<>();
		ArretLigneTamponDTO arretLigneTamponDTO = new ArretLigneTamponDTO();
		arretLigneTamponDTO.setId(1);
		arretLigneTamponDTO.setStopPointRef("109311");
		arretLigneTamponDTO.setLineRef("C00040");
		list.add(arretLigneTamponDTO);
		mapArretLigneTampon.put(key, list);
		arretLigneTamponBC.ajouterArretLigne(mapArretLigneTampon, null, null);

		//When
		List<String> listArretLigneTampon = new ArrayList<String>(passageBC.rechercheLignesParArret(id));
		//Then
		Assertions.assertThat(listArretLigneTampon.size()).isNotNull();
		Assertions.assertThat(listArretLigneTampon.get(0)).isEqualTo("C00040");
	}

	@Test
	public void testGetLigneArret() {
		//Given
		Map<String, List<ArretLigneTamponDTO>> mapLigneArretTampon = new HashMap<>();
		String id = "C00040";
		String key = RedisUtils.genererKeyAssociationLigneArret(id);
		List<ArretLigneTamponDTO> list = new ArrayList<>();
		ArretLigneTamponDTO ligneArretTamponDTO = new ArretLigneTamponDTO();
		ligneArretTamponDTO.setId(1);
		ligneArretTamponDTO.setStopPointRef("109311");
		ligneArretTamponDTO.setLineRef("C00040");
		list.add(ligneArretTamponDTO);
		mapLigneArretTampon.put(key, list);
		arretLigneTamponBC.ajouterLigneArret(mapLigneArretTampon, null, null);

		//When

		List<String> listLigneArretTampon = new ArrayList<String>(passageBC.rechercheArretsParLigne(id));
		//Then
		Assertions.assertThat(listLigneArretTampon.size()).isNotNull();
		Assertions.assertThat(listLigneArretTampon.get(0)).isEqualTo("109311");
	}
}
