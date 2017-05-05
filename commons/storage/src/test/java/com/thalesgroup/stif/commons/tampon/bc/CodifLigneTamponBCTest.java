package com.thalesgroup.stif.commons.tampon.bc;

import java.util.ArrayList;
import java.util.List;

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
import com.sirh.mqd.commons.exchanges.constante.SiriConstantes;
import com.sirh.mqd.commons.exchanges.exception.ApplicationException;
import com.sirh.mqd.commons.storage.bc.CodifLigneTamponBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.ICodifLigneTamponDAO;
import com.sirh.mqd.commons.test.EmbeddedRedisThales;
import com.sirh.mqd.commons.test.JedisConnectionFactoryThales;
import com.sirh.mqd.commons.test.EmbeddedRedisThales.EmbeddedRedisRuleBuilderThales;
import com.thalesgroup.stif.commons.echange.dto.codifligne.CodifLigneDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tampon-context-test.xml")
@UsingDataSet(locations = "/dataset-codifligne.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
public class CodifLigneTamponBCTest {

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
	CodifLigneTamponBC codifligneTamponBC;

	@Autowired
	ICodifLigneTamponDAO codifligneTamponDAO;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Jedis jedis = EmbeddedRedisInstances.getInstance().getDefaultJedis();
		jedisConnectionFactory.setJedis(jedis);
	}

	@Test
	public void testVerifierLineRef24() throws ApplicationException {
		// given
		String lineRef = "STIF:Line::ligne1CodeCommercial:";
		codifligneTamponBC.verifierLineRef(lineRef, SiriConstantes.VERSION_24_PROFILE);
	}

	@Test
	public void testVerifierLineRef22() throws ApplicationException {
		// given
		String lineRef = "AMIV:Line::ligne1CodeStif:";
		codifligneTamponBC.verifierLineRef(lineRef, SiriConstantes.VERSION_22_PROFILE);
	}

	@Test
	public void testAjouterCodifLigne24() throws ApplicationException {
		CodifLigneDTO cl = new CodifLigneDTO();
		cl.setCodeAdminStif("ligne1CodeCommercial");

		codifligneTamponBC.ajouterCodifLigne(cl, 28L);

		String lineRef = "STIF:Line::ligne1CodeCommercial:";
		codifligneTamponBC.verifierLineRef(lineRef, SiriConstantes.VERSION_24_PROFILE);
	}

	@Test
	public void testAjouterCodifLigne22() throws ApplicationException {
		CodifLigneDTO cl = new CodifLigneDTO();
		cl.setCodeAdminStif("codeAdminStif");

		codifligneTamponBC.ajouterCodifLigne(cl, 28L);

		String lineRef = "AMIV:Line::codeAdminStif:";
		codifligneTamponBC.verifierLineRef(lineRef, SiriConstantes.VERSION_22_PROFILE);
	}

	@Test(expected = ApplicationException.class)
	public void testverifierLineRefBADREQUEST_LINEGREF() throws ApplicationException {
		String monitoringRef = "STIF:StopPoint:Q:10930";
		//STIFSCRUM-2598
		codifligneTamponBC.verifierLineRef(monitoringRef, SiriConstantes.VERSION_22_PROFILE);
	}

	@Test(expected = ApplicationException.class)
	public void testverifierLineRefBADREQUEST_PATTERN() throws ApplicationException {
		String monitoringRef = null;
		//STIFSCRUM-2598
		codifligneTamponBC.verifierLineRef(monitoringRef, SiriConstantes.VERSION_22_PROFILE);
	}

	@Test
	public void testConvertLineRef22To24() {
		List<String> lineRefList = codifligneTamponBC.convertLineRef22To24("ligne2CodeStif");
		List<String> lineRefListResult = new ArrayList<String>();
		lineRefListResult.add("ligne2CodeCommercial");
		Assertions.assertThat(lineRefList).isEqualTo(lineRefListResult);
	}

	@Test
	public void testConvertLineRef24To22() {
		List<CodifLigneDTO> codifligneDTOList = codifligneTamponBC.getAllLignes();
		String lineRef = codifligneTamponBC.convertLineRef24To22(codifligneDTOList, "ligne2CodeCommercial");
		String lineRefResult = "ligne2CodeStif";
		Assertions.assertThat(lineRef).isEqualTo(lineRefResult);
	}
}
