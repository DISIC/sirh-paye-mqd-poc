package com.thalesgroup.stif.commons.tampon.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
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
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.impl.OperatorTamponDAO;
import com.sirh.mqd.commons.test.EmbeddedRedisThales;
import com.sirh.mqd.commons.test.JedisConnectionFactoryThales;
import com.sirh.mqd.commons.test.EmbeddedRedisThales.EmbeddedRedisRuleBuilderThales;
import com.thalesgroup.stif.commons.echange.dto.participant.OperatorTamponDTO;

/**
 * Class de test pour la persistence des données Partenaires.
 * 
 * @see doc.story.ref184
 * @author Thales Services
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tampon-context-test.xml")
@UsingDataSet(locations = "/dataset-messages.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
public class OperatorTamponDAOTest {

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
	private OperatorTamponDAO operatorDAO;

	@Test
	public void testOperatorExist() {
		// TODO insérer un script Redis pour les tests
	}

	@Test
	public void testMajParticipantOperator() {

		Set<OperatorTamponDTO> setOperator = new HashSet<OperatorTamponDTO>();

		OperatorTamponDTO op1 = new OperatorTamponDTO();
		OperatorTamponDTO op2 = new OperatorTamponDTO();
		OperatorTamponDTO op3 = new OperatorTamponDTO();
		OperatorTamponDTO op4 = new OperatorTamponDTO();

		op1.setParticipantRef("participantTest1");
		op2.setParticipantRef("participantTest1");
		op3.setParticipantRef("participantTest2");
		op4.setParticipantRef("participantTest2");

		op1.setCode("opCode1");
		op2.setCode("opCode2");

		op3.setCode("opCode3");
		op4.setCode("opCode4");

		setOperator.add(op1);
		setOperator.add(op2);
		setOperator.add(op3);
		setOperator.add(op4);
		operatorDAO.majParticipantOperator(setOperator, null, null);

		List<String> listOp1 = operatorDAO.getOperatorCode("participantTest1");

		List<String> listOp2 = operatorDAO.getOperatorCode("participantTest2");

		Assert.assertTrue(listOp1.contains("opCode1"));
		Assert.assertTrue(listOp1.contains("opCode2"));
		Assert.assertTrue(listOp2.contains("opCode3"));
		Assert.assertTrue(listOp2.contains("opCode4"));
	}

}
