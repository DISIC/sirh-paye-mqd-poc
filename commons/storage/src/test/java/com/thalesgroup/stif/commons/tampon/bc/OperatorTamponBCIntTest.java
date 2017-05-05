package com.thalesgroup.stif.commons.tampon.bc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import com.sirh.mqd.commons.storage.bc.OperatorTamponBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IOperatorTamponDAO;
import com.sirh.mqd.commons.test.EmbeddedRedisThales;
import com.sirh.mqd.commons.test.JedisConnectionFactoryThales;
import com.sirh.mqd.commons.test.EmbeddedRedisThales.EmbeddedRedisRuleBuilderThales;
import com.thalesgroup.stif.commons.echange.dto.participant.OperatorTamponDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tampon-context-test.xml")
@UsingDataSet(locations = "/dataset-operator-participant.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
public class OperatorTamponBCIntTest {

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
	OperatorTamponBC operatorTamponBC;

	@Autowired
	IOperatorTamponDAO operatorTamponDAO;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		final Jedis jedis = EmbeddedRedisInstances.getInstance().getDefaultJedis();
		jedisConnectionFactory.setJedis(jedis);
	}

	@Test(expected = InvalidDataReferenceException.class)
	public void testAjouterOperatorNotExist() throws ApplicationException {
		final OperatorTamponDTO operatorTampon = new OperatorTamponDTO();
		operatorTampon.setName("RATP");
		operatorTampon.setCode("RATP");
		operatorTampon.setParticipantRef("SNCF");
		final Set<OperatorTamponDTO> operatorsTampon = new HashSet<OperatorTamponDTO>();
		operatorTamponBC.ajouterOperators(operatorsTampon, null, null);
		operatorTamponBC.verifierOperatorExist(operatorTampon.getCode(), operatorTampon.getParticipantRef(), SiriConstantes.OPERATOR_REF);
	}

	@Test(expected = InvalidDataReferenceException.class)
	public void testAjouterOperatorFalse() throws ApplicationException {
		final OperatorTamponDTO operatorTampon = new OperatorTamponDTO();
		operatorTampon.setName("RER");
		operatorTampon.setCode("SNCF:Operator::RERFaux:");
		operatorTampon.setParticipantRef("RATP");
		final Set<OperatorTamponDTO> operatorsTampon = new HashSet<OperatorTamponDTO>();
		operatorTamponBC.ajouterOperators(operatorsTampon, null, null);
		operatorTamponBC.verifierOperatorExist(operatorTampon.getParticipantRef(), operatorTampon.getCode(), SiriConstantes.OPERATOR_REF);
	}

	@Test
	public void testAjouterOperator() throws ApplicationException {

		final OperatorTamponDTO operatorTampon = new OperatorTamponDTO();
		operatorTampon.setName("RER");
		operatorTampon.setCode("SNCF:Operator::RER:");
		operatorTampon.setParticipantRef("SNCF");
		//		Set<OperatorTamponDTO> operatorsTampon = new HashSet<OperatorTamponDTO>();
		//		operatorTamponBC.ajouterOperators(operatorsTampon, null);
		operatorTamponBC.verifierOperatorExist(operatorTampon.getParticipantRef(), operatorTampon.getCode(), SiriConstantes.OPERATOR_REF);

	}

	@Test
	public void testVerifierOperateurChampSiri() throws ApplicationException {
		final List<String> operators = new ArrayList<String>();
		operators.add("RER");
		operatorTamponBC.verifierOperateurChampSiri("SNCF", operators, "RER:DataFrame::1:LOC", SiriConstantes.DATA_FRAME_REF);
	}

	@Test
	public void testVerifierOperateursChampSiri() throws ApplicationException {
		final List<String> operators = new ArrayList<String>();
		operators.add("RER");
		operators.add("Noctilien");
		operatorTamponBC.verifierOperateurChampSiri("SNCF", operators, "RER:DataFrame::1:LOC", SiriConstantes.DATA_FRAME_REF);
	}

	@Test(expected = InvalidDataReferenceException.class)
	public void testVerifierOperateurChampSiriOperateurNonAssocieAuParticipant() throws ApplicationException {
		operatorTamponBC.verifierOperateurChampSiri("SNCF", "FALSE:DataFrame::1:LOC", SiriConstantes.DATA_FRAME_REF);
	}

	@Test(expected = InvalidDataReferenceException.class)
	public void testVerifierOperateurChampSiriParticipantNonAssocieAOperateur() throws ApplicationException {
		final List<String> operators = new ArrayList<String>();
		operators.add("RER");
		operatorTamponBC.verifierOperateurChampSiri("FALSE", operators, "RER:DataFrame::1:LOC", SiriConstantes.DATA_FRAME_REF);
	}

}