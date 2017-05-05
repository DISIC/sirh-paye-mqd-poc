package com.thalesgroup.stif.commons.tampon.bc;

import java.util.HashSet;
import java.util.Set;

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
import com.sirh.mqd.commons.exchanges.constante.SiriConstantes;
import com.sirh.mqd.commons.exchanges.exception.ApplicationException;
import com.sirh.mqd.commons.exchanges.exception.InvalidDataReferenceException;
import com.sirh.mqd.commons.storage.bc.ParticipantTamponBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IParticipantTamponDAO;
import com.sirh.mqd.commons.test.EmbeddedRedisThales;
import com.sirh.mqd.commons.test.JedisConnectionFactoryThales;
import com.sirh.mqd.commons.test.EmbeddedRedisThales.EmbeddedRedisRuleBuilderThales;
import com.thalesgroup.stif.commons.echange.dto.participant.ParticipantTamponDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tampon-context-test.xml")
@UsingDataSet(locations = "/dataset-operator-participant.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
public class ParticipantTamponBCIntTest {

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
	ParticipantTamponBC participantTamponBC;

	@Autowired
	IParticipantTamponDAO participantTamponDAO;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Jedis jedis = EmbeddedRedisInstances.getInstance().getDefaultJedis();
		jedisConnectionFactory.setJedis(jedis);
	}

	@Test(expected = InvalidDataReferenceException.class)
	public void testAjouterOperatorNotExist() throws ApplicationException {
		ParticipantTamponDTO participantTampon = new ParticipantTamponDTO();
		participantTampon.setName("RATP");
		participantTampon.setParticipantRef("SNCF");
		Set<ParticipantTamponDTO> participantsTampon = new HashSet<ParticipantTamponDTO>();
		participantTamponBC.ajouterParticipants(participantsTampon, null, null);
		participantTampon.setParticipantRef("SNCFFaux");
		participantTamponBC.verifierParticipantRef(participantTampon.getParticipantRef());
	}

	@Test(expected = InvalidDataReferenceException.class)
	public void testAjouterOperatorFalse() throws ApplicationException {
		ParticipantTamponDTO operatorTampon = new ParticipantTamponDTO();
		operatorTampon.setName("RATP");
		operatorTampon.setParticipantRef("RATP");
		Set<ParticipantTamponDTO> participantsTampon = new HashSet<ParticipantTamponDTO>();
		participantTamponBC.ajouterParticipants(participantsTampon, null, null);
		participantTamponBC.verifierParticipantRef(operatorTampon.getParticipantRef());
	}

	@Test
	public void testAjouterOperator() throws ApplicationException {

		ParticipantTamponDTO operatorTampon = new ParticipantTamponDTO();
		operatorTampon.setName("RATP");
		operatorTampon.setParticipantRef("SNCF");
		Set<ParticipantTamponDTO> participantsTampon = new HashSet<ParticipantTamponDTO>();
		participantTamponBC.ajouterParticipants(participantsTampon, null, null);
		participantTamponBC.verifierParticipantRef(operatorTampon.getParticipantRef());

	}

	@Test
	public void testVerifierFournisseurChampSiri() throws ApplicationException {
		participantTamponBC.verifierFournisseurChampSiri("SNCF", "SNCF:Message::1:LOC", SiriConstantes.MESSAGE_IDENTIFIER);
	}

	@Test(expected = InvalidDataReferenceException.class)
	public void testVerifierFournisseurChampSiriMauvaisFournisseurIdSiri() throws ApplicationException {
		participantTamponBC.verifierFournisseurChampSiri("SNCF", "FALSE:Message::1:LOC", SiriConstantes.MESSAGE_IDENTIFIER);
	}

	@Test(expected = InvalidDataReferenceException.class)
	public void testVerifierFournisseurChampSiriFournisseurInexistant() throws ApplicationException {
		participantTamponBC.verifierFournisseurChampSiri("FALSE", "SNCF:Message::1:LOC", SiriConstantes.MESSAGE_IDENTIFIER);
	}

}