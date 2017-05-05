package com.thalesgroup.stif.commons.tampon.dao.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
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
import com.sirh.mqd.commons.exchanges.exception.TechnicalException;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO;
import com.sirh.mqd.commons.test.EmbeddedRedisThales;
import com.sirh.mqd.commons.test.JedisConnectionFactoryThales;
import com.sirh.mqd.commons.test.EmbeddedRedisThales.EmbeddedRedisRuleBuilderThales;
import com.thalesgroup.stif.commons.echange.dto.codifligne.CodifLigneDTO;

/**
 * @author stephane
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tampon-context-test.xml")
@UsingDataSet(locations = "/dataset-codifligne.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
public class CodifLigneTamponDAOTest {

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

	@Spy
	@InjectMocks
	@Autowired
	private CodifLigneTamponDAO codifLigneDAO;

	String ligne1CodeStif;

	String ligne2CodeStif;

	String ligne11CodeAdministratif;

	String ligne12CodeAdministratif;

	String ligne1CodeCommercial;

	CodifLigneDTO ligne1;

	CodifLigneDTO ligne2;

	/**
	 * Initialisation avant chaque test
	 */
	@Before
	public void setUp() {
		Jedis jedis = EmbeddedRedisInstances.getInstance().getDefaultJedis();
		jedisConnectionFactory.setJedis(jedis);

		// given
		String ligne1Name = "ligne1";
		String ligne2Name = "ligne2";
		String ligne11Name = "ligne1.1";
		String ligne12Name = "ligne1.2";
		ligne1CodeStif = ligne1Name + "CodeStif";
		ligne2CodeStif = ligne2Name + "CodeStif";
		ligne11CodeAdministratif = ligne11Name + "CodeAdministratif";
		ligne12CodeAdministratif = ligne12Name + "CodeAdministratif";
		ligne1CodeCommercial = ligne1Name + "CodeCommercial";

		// Ligne1
		ligne1 = new CodifLigneDTO();
		ligne1.setCodeAdminStif(ligne1CodeStif);
		ligne1.setRefLigneAdmin(ligne11CodeAdministratif);
		ligne1.setRefLigneCom(ligne1CodeCommercial);

		// Ligne2
		ligne2 = new CodifLigneDTO();
		ligne2.setCodeAdminStif(ligne1CodeStif);
		ligne2.setRefLigneAdmin(ligne12CodeAdministratif);
		ligne2.setRefLigneCom(ligne1CodeCommercial);

		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExist(java.lang.String, String)} avec un
	 * code Stif
	 */
	@Test
	public final void testIsExistWithCodeStif() {
		// given

		//STIFSCRUM-2598
		// when
		boolean result = codifLigneDAO.isExist(ligne1CodeStif, "1.3");

		// then
		Assert.assertTrue(result);
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExist(java.lang.String, String)} avec un
	 * code Administratif
	 */
	@Test
	public final void testIsExistWithCodeAdministratif() {
		// given

		//STIFSCRUM-2598
		// when
		boolean result = codifLigneDAO.isExist(ligne11CodeAdministratif, "1.3");

		// then
		Assert.assertTrue(result);
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExist(java.lang.String, String)} avec un
	 * code Commercial
	 */
	@Test
	public final void testIsExistWithCodeCommercial() {
		// given

		//STIFSCRUM-2598
		// when
		boolean result = codifLigneDAO.isExist(ligne1CodeCommercial, "2.4");

		// then
		Assert.assertTrue(result);
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExist(java.lang.String, String)} with a
	 * wrong key
	 */
	@Test
	public final void testIsExistWithWrongKey() {
		// given

		//STIFSCRUM-2598
		// when
		boolean result = codifLigneDAO.isExist("unknow", "1.3");

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExist(java.lang.String, String)} with null
	 */
	@Test
	public final void testIsExistWithNull() {
		// given

		// when
		boolean result = codifLigneDAO.isExist(null, null);

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExistByCodeStif(java.lang.String)} avec un
	 * code Stif
	 */
	@Test
	public final void testIsExistByCodeStifWithCodeStif() {
		// given

		// when
		boolean result = codifLigneDAO.isExistByCodeStif(ligne1CodeStif);

		// then
		Assert.assertTrue(result);
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExistByCodeStif(java.lang.String)} avec un
	 * code Administratif
	 */
	@Test
	public final void testIsExistByCodeStifWithCodeAdministratif() {
		// given

		// when
		boolean result = codifLigneDAO.isExistByCodeStif(ligne11CodeAdministratif);

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExistByCodeStif(java.lang.String)} avec un
	 * code Commercial
	 */
	@Test
	public final void testIsExistByCodeStifWithCodeCommercial() {
		// given

		// when
		boolean result = codifLigneDAO.isExistByCodeStif(ligne1CodeCommercial);

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExistByCodeStif(java.lang.String)} with a
	 * wrong key
	 */
	@Test
	public final void testIsExistByCodeStifWithWrongKey() {
		// given

		// when
		boolean result = codifLigneDAO.isExistByCodeStif("unknow");

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExistByCodeStif(java.lang.String)} with
	 * null
	 */
	@Test
	public final void testIsExistByCodeStifWithNull() {
		// given

		// when
		boolean result = codifLigneDAO.isExistByCodeStif(null);

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExistByCodeAdministratif(java.lang.String)} avec un code
	 * Stif
	 */
	@Test
	public final void testIsExistByCodeAdministratifWithCodeStif() {
		// given

		// when
		boolean result = codifLigneDAO.isExistByCodeAdministratif(ligne1CodeStif);

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExistByCodeAdministratif(java.lang.String)} avec un code
	 * Administratif
	 */
	@Test
	public final void testIsExistByCodeAdministratifWithCodeAdministratif() {
		// given

		// when
		boolean result = codifLigneDAO.isExistByCodeAdministratif(ligne11CodeAdministratif);

		// then
		Assert.assertTrue(result);
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExistByCodeAdministratif(java.lang.String)} avec un code
	 * Commercial
	 */
	@Test
	public final void testIsExistByCodeAdministratifWithCodeCommercial() {
		// given

		// when
		boolean result = codifLigneDAO.isExistByCodeAdministratif(ligne1CodeCommercial);

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExistByCodeAdministratif(java.lang.String)}
	 * with a wrong key
	 */
	@Test
	public final void testIsExistByCodeAdministratifWithWrongKey() {
		// given

		// when
		boolean result = codifLigneDAO.isExistByCodeAdministratif("unknow");

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExistByCodeAdministratif(java.lang.String)}
	 * with null
	 */
	@Test
	public final void testIsExistByCodeAdministratifWithNull() {
		// given

		// when
		boolean result = codifLigneDAO.isExistByCodeAdministratif(null);

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExistByCodeCommercial(java.lang.String)}
	 * avec un code Stif
	 */
	@Test
	public final void testIsExistByCodeCommercialWithCodeStif() {
		// given

		// when
		boolean result = codifLigneDAO.isExistByCodeCommercial(ligne1CodeStif);

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExistByCodeCommercial(java.lang.String)}
	 * avec un code Administratif
	 */
	@Test
	public final void testIsExistByCodeCommercialWithCodeAdministratif() {
		// given

		// when
		boolean result = codifLigneDAO.isExistByCodeCommercial(ligne11CodeAdministratif);

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExistByCodeCommercial(java.lang.String)}
	 * avec un code Commercial
	 */
	@Test
	public final void testIsExistByCodeCommercialWithCodeCommercial() {
		// given

		// when
		boolean result = codifLigneDAO.isExistByCodeCommercial(ligne1CodeCommercial);

		// then
		Assert.assertTrue(result);
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExistByCodeCommercial(java.lang.String)}
	 * with a wrong key
	 */
	@Test
	public final void testIsExistByCodeCommercialWithWrongKey() {
		// given

		// when
		boolean result = codifLigneDAO.isExistByCodeCommercial("unknow");

		// then
		Assert.assertFalse(result);
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#isExistByCodeCommercial(java.lang.String)}
	 * with null
	 */
	@Test
	public final void testIsExistByCodeCommercialWithNull() {
		// given

		// when
		boolean result = codifLigneDAO.isExistByCodeCommercial(null);

		// then
		Assert.assertFalse(result);
	}

	private boolean isLigneInList(final CodifLigneDTO ligneToCheck, final List<CodifLigneDTO> lignes) {
		boolean result = false;
		for (CodifLigneDTO ligne : lignes) {
			if (ligne.equals(ligneToCheck)) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStif(java.lang.String)} avec
	 * un code Stif
	 */
	@Test
	public final void testGetLignesByCodeStifWithCodeStif() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStif(ligne1CodeStif);

		// then
		Assert.assertEquals(2, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStif(java.lang.String)} avec
	 * un code Administratif
	 */
	@Test
	public final void testGetLignesByCodeStifWithCodeAdministratif() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStif(ligne11CodeAdministratif);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStif(java.lang.String)} avec
	 * un code Commercial
	 */
	@Test
	public final void testGetLignesByCodeStifWithCodeCommercial() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStif(ligne1CodeCommercial);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStif(java.lang.String)} with a
	 * wrong key
	 */
	@Test
	public final void testGetLignesByCodeStifWithWrongKey() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStif("unknow");

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStif(java.lang.String)} with
	 * null
	 */
	@Test
	public final void testGetLignesByCodeStifWithNull() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStif(null);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeAdministratif(java.lang.String)} avec un code
	 * Stif
	 */
	@Test
	public final void testGetLignesByCodeAdministratifWithCodeStif() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeAdministratif(ligne1CodeStif);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeAdministratif(java.lang.String)} avec un code
	 * Administratif
	 */
	@Test
	public final void testGetLignesByCodeAdministratifWithCodeAdministratif() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeAdministratif(ligne11CodeAdministratif);

		// then
		Assert.assertEquals(1, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeAdministratif(java.lang.String)} avec un code
	 * Commercial
	 */
	@Test
	public final void testGetLignesByCodeAdministratifWithCodeCommercial() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeAdministratif(ligne1CodeCommercial);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeAdministratif(java.lang.String)} with a wrong
	 * key
	 */
	@Test
	public final void testGetLignesByCodeAdministratifWithWrongKey() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeAdministratif("unknow");

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeAdministratif(java.lang.String)} with null
	 */
	@Test
	public final void testGetLignesByCodeAdministratifWithNull() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeAdministratif(null);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeCommercial(java.lang.String)}
	 * avec un code Stif
	 */
	@Test
	public final void testGetLignesByCodeCommercialWithCodeStif() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeCommercial(ligne1CodeStif);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeCommercial(java.lang.String)}
	 * avec un code Administratif
	 */
	@Test
	public final void testGetLignesByCodeCommercialWithCodeAdministratif() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeCommercial(ligne11CodeAdministratif);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeCommercial(java.lang.String)}
	 * avec un code Commercial
	 */
	@Test
	public final void testGetLignesByCodeCommercialWithCodeCommercial() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeCommercial(ligne1CodeCommercial);

		// then
		Assert.assertEquals(2, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeCommercial(java.lang.String)}
	 * with a wrong key
	 */
	@Test
	public final void testGetLignesByCodeCommercialWithWrongKey() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeCommercial("unknow");

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeCommercial(java.lang.String)}
	 * with null
	 */
	@Test
	public final void testGetLignesByCodeCommercialWithNull() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeCommercial(null);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignes(java.lang.String)} avec un code
	 * Stif
	 */
	@Test
	public final void testGetLignesWithCodeStif() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignes(ligne1CodeStif);

		// then
		Assert.assertEquals(2, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignes(java.lang.String)} avec un code
	 * Administratif
	 */
	@Test
	public final void testGetLignesWithCodeAdministratif() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignes(ligne11CodeAdministratif);

		// then
		Assert.assertEquals(1, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
	}

	/**
	 * Test method pour {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignes(java.lang.String)} avec un code
	 * Commercial
	 */
	@Test
	public final void testGetLignesWithCodeCommercial() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignes(ligne1CodeCommercial);

		// then
		Assert.assertEquals(2, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignes(java.lang.String)} with a wrong key
	 */
	@Test
	public final void testGetLignesWithWrongKey() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignes("unknow");

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignes(java.lang.String)} with null
	 */
	@Test
	public final void testGetLignesWithNull() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignes(null);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeAdministratif(java.lang.String)}
	 * avec un code Stif et un code administratif
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeAdministratifWithCodeStifAndCodeAdministratif() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStifAndCodeAdministratif(ligne1CodeStif, ligne11CodeAdministratif);

		// then
		Assert.assertEquals(1, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeAdministratif(java.lang.String)}
	 * avec un code Stif et un code Commercial
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeAdministratifWithCodeStifAndCodeCommercial() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStifAndCodeAdministratif(ligne1CodeStif, ligne1CodeCommercial);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeAdministratif(java.lang.String)}
	 * avec un code Commercial et un code Administratif
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeAdministratifWithCodeCommercialAndCodeAdministratif() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStifAndCodeAdministratif(ligne1CodeCommercial, ligne11CodeAdministratif);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeAdministratif(java.lang.String)}
	 * avec un code Administratif et un code Stif
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeAdministratifWithCodeAdministratifAndCodeStif() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStifAndCodeAdministratif(ligne11CodeAdministratif, ligne1CodeStif);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeAdministratif(java.lang.String)}
	 * with a wrong key
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeAdministratifWithWrongKey() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStifAndCodeAdministratif("unknow1", "unknow2");

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeAdministratif(java.lang.String)}
	 * with null
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeAdministratifWithNull() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStifAndCodeAdministratif(null, null);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeCommercial(java.lang.String)} avec
	 * un code Stif et un code Commercial
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeCommercialWithCodeStifAndCodeCommercial() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStifAndCodeCommercial(ligne1CodeStif, ligne1CodeCommercial);

		// then
		Assert.assertEquals(2, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeCommercial(java.lang.String)} avec
	 * un code Stif et un code Commercial
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeCommercialWithCodeStifAndCodeAdministratif() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStifAndCodeCommercial(ligne1CodeStif, ligne11CodeAdministratif);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeCommercial(java.lang.String)} avec
	 * un code Administratif et un code Commercial
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeCommercialWithCodeAdministratifAndCodeCommercial() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStifAndCodeCommercial(ligne11CodeAdministratif, ligne1CodeCommercial);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeCommercial(java.lang.String)} avec
	 * un code Commercial et un code Stif
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeCommercialWithCodeCommercialAndCodeStif() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStifAndCodeCommercial(ligne1CodeCommercial, ligne1CodeStif);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeCommercial(java.lang.String)} with
	 * a wrong key
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeCommercialWithWrongKey() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStifAndCodeCommercial("unknow1", "unknow2");

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeCommercial(java.lang.String)} with
	 * null
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeCommercialWithNull() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getLignesByCodeStifAndCodeCommercial(null, null);

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeCommercial(java.lang.String)} avec
	 * un code Stif, un code Administratif et un code Commercial
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeAdministratifAndCodeCommercialWithCodeStifAndCodeAdministratifAndCodeCommercial() {
		// given

		// when
		CodifLigneDTO result = codifLigneDAO.getLigneByCodeStifAndCodeAdministratifAndCodeCommercial(ligne1CodeStif,
				ligne11CodeAdministratif, ligne1CodeCommercial);

		// then
		Assert.assertTrue(ligne1.equals(result));
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeCommercial(java.lang.String)} avec
	 * un code Stif, un code Administratif et un code Stif
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeAdministratifAndCodeCommercialWithCodeStifAndCodeAdministratifAndCodeStif() {
		// given

		// when
		CodifLigneDTO result = codifLigneDAO.getLigneByCodeStifAndCodeAdministratifAndCodeCommercial(ligne1CodeStif,
				ligne11CodeAdministratif, ligne1CodeStif);

		// then
		Assert.assertNull(result);
	}

	/**
	 * Test method pour
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeCommercial(java.lang.String)} avec
	 * un un code Commercial, un code Administratif et un code Commercial
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeAdministratifAndCodeCommercialWithCodeCommercialAndCodeAdministratifAndCodeCommercial() {
		// given

		// when
		CodifLigneDTO result = codifLigneDAO.getLigneByCodeStifAndCodeAdministratifAndCodeCommercial(ligne1CodeCommercial,
				ligne11CodeAdministratif, ligne1CodeCommercial);

		// then
		Assert.assertNull(result);
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeCommercial(java.lang.String)} with
	 * a wrong key
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeAdministratifAndCodeCommercialWithWrongKey() {
		// given

		// when
		CodifLigneDTO result = codifLigneDAO.getLigneByCodeStifAndCodeAdministratifAndCodeCommercial("unknow1", "unknow2", "unknow3");

		// then
		Assert.assertNull(result);
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getLignesByCodeStifAndCodeCommercial(java.lang.String)} with
	 * null
	 */
	@Test
	public final void testGetLignesByCodeStifAndCodeAdministratifAndCodeCommercialWithNull() {
		// given

		// when
		CodifLigneDTO result = codifLigneDAO.getLigneByCodeStifAndCodeAdministratifAndCodeCommercial(null, null, null);

		// then
		Assert.assertNull(result);
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getAllLignes()} with data in Redis
	 */
	@Test
	public final void testGetAllLignesWithData() {
		// given

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();

		// then
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#getAllLignes()} without data in Redis
	 */
	@Test
	public final void testGetAllLignesWithOutData() {
		// given
		codifLigneDAO.reset();

		// when
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();

		// then
		Assert.assertTrue(result.isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#addOrUpdate(com.thalesgroup.stif.commons.echange.dto.codifligne.CodifLigneDTO)}
	 * with code Stif, code Administratif and code Commercial
	 */
	@Test
	public final void testAddOrUpdateWithCodeStifAndCodeAdministratifAndCodeCommercial() {
		// given
		String codeStif3 = "codeStif3";
		String codeAdministratif3 = "codeAdministratif3";
		String codeCommercial3 = "codeCommercial3";
		CodifLigneDTO ligne3 = new CodifLigneDTO();
		ligne3.setCodeAdminStif(codeStif3);
		ligne3.setRefLigneAdmin(codeAdministratif3);
		ligne3.setRefLigneCom(codeCommercial3);

		// when
		codifLigneDAO.addOrUpdate(ligne3, 28L);

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(4, result.size());
		Assert.assertTrue(isLigneInList(ligne3, result));
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#addOrUpdate(com.thalesgroup.stif.commons.echange.dto.codifligne.CodifLigneDTO)}
	 * with code Stif, code Administratif and without code Commercial
	 */
	@Test
	public final void testAddOrUpdateWithCodeStifAndCodeAdministratifAndWithoutCodeCommercial() {
		// given
		String codeStif3 = "codeStif3";
		String codeAdministratif3 = "codeAdministratif3";
		CodifLigneDTO ligne3 = new CodifLigneDTO();
		ligne3.setCodeAdminStif(codeStif3);
		ligne3.setRefLigneAdmin(codeAdministratif3);

		// when
		codifLigneDAO.addOrUpdate(ligne3, 28L);

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(4, result.size());
		Assert.assertTrue(isLigneInList(ligne3, result));
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#addOrUpdate(com.thalesgroup.stif.commons.echange.dto.codifligne.CodifLigneDTO)}
	 * with code Stif, code Commercial and without code Administratif
	 */
	@Test
	public final void testAddOrUpdateWithCodeStifAndCodeCommercialAndWithoutCodeAdministratif() {
		// given
		String codeStif3 = "codeStif3";
		String codeCommercial3 = "codeCommercial3";
		CodifLigneDTO ligne3 = new CodifLigneDTO();
		ligne3.setCodeAdminStif(codeStif3);
		ligne3.setRefLigneCom(codeCommercial3);

		// when
		codifLigneDAO.addOrUpdate(ligne3, 28L);

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(4, result.size());
		Assert.assertTrue(isLigneInList(ligne3, result));
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#addOrUpdate(com.thalesgroup.stif.commons.echange.dto.codifligne.CodifLigneDTO)}
	 * with code Administratif, code Commercial and without code Stif
	 */
	@Test
	public final void testAddOrUpdateWithCodeAdministratifAndCodeCommercialAndWithoutCodeStif() {
		// given
		String codeAdministratif3 = "codeAdministratif3";
		String codeCommercial3 = "codeCommercial3";
		CodifLigneDTO ligne3 = new CodifLigneDTO();
		ligne3.setRefLigneAdmin(codeAdministratif3);
		ligne3.setRefLigneCom(codeCommercial3);

		// when
		codifLigneDAO.addOrUpdate(ligne3, 28L);

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(4, result.size());
		Assert.assertTrue(isLigneInList(ligne3, result));
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#addOrUpdate(com.thalesgroup.stif.commons.echange.dto.codifligne.CodifLigneDTO)}
	 * without code Stif, code Administratif and code Commercial
	 */
	@Test
	public final void testAddOrUpdateWithoutCodeStifAndCodeAdministratifAndCodeCommercial() {
		// given
		CodifLigneDTO ligne3 = new CodifLigneDTO();

		// when
		codifLigneDAO.addOrUpdate(ligne3, 28L);

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(3, result.size());
		Assert.assertFalse(isLigneInList(ligne3, result));
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#addOrUpdate(com.thalesgroup.stif.commons.echange.dto.codifligne.CodifLigneDTO)}
	 * with code Stif, code Administratif and code Commercial already exist
	 */
	@Test
	public final void testAddOrUpdateWithCodeStifAndCodeAdministratifAndCodeCommercialAlreadyExist() {
		// given
		String codeStif3 = ligne1CodeStif;
		String codeAdministratif3 = ligne11CodeAdministratif;
		String codeCommercial3 = ligne1CodeCommercial;
		String nomCT2 = "nomCT2";
		String nomTransporteur = "nomTransporteur";
		String mode = "mode";
		CodifLigneDTO ligne3 = new CodifLigneDTO();
		ligne3.setCodeAdminStif(codeStif3);
		ligne3.setRefLigneAdmin(codeAdministratif3);
		ligne3.setRefLigneCom(codeCommercial3);
		ligne3.setNom(nomCT2);
		ligne3.setTransporteur1(nomTransporteur);
		ligne3.setMode(mode);

		// when
		codifLigneDAO.addOrUpdate(ligne3, 28L);

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(isLigneInList(ligne3, result));
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#addOrUpdate(com.thalesgroup.stif.commons.echange.dto.codifligne.CodifLigneDTO)}
	 * with ligne = null
	 */
	@Test
	public final void testAddOrUpdateWithLigneNull() {
		// given
		CodifLigneDTO ligne3 = null;

		// when
		codifLigneDAO.addOrUpdate(ligne3, 28L);

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#delete(com.thalesgroup.stif.commons.echange.dto.codifligne.CodifLigneDTO)}
	 * with already existing ligne
	 */
	@Test
	public final void testDeleteWithAlreadyExistingLigne() {
		// given
		String codeStif3 = ligne1CodeStif;
		String codeAdministratif3 = ligne11CodeAdministratif;
		String codeCommercial3 = ligne1CodeCommercial;
		String nomCT2 = "nomCT2";
		String nomTransporteur = "nomTransporteur";
		String mode = "mode";
		CodifLigneDTO ligne3 = new CodifLigneDTO();
		ligne3.setCodeAdminStif(codeStif3);
		ligne3.setRefLigneAdmin(codeAdministratif3);
		ligne3.setRefLigneCom(codeCommercial3);
		ligne3.setNom(nomCT2);
		ligne3.setTransporteur1(nomTransporteur);
		ligne3.setMode(mode);

		// when
		codifLigneDAO.delete(ligne3);

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(2, result.size());
		Assert.assertFalse(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for
	 * {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#delete(com.thalesgroup.stif.commons.echange.dto.codifligne.CodifLigneDTO)}
	 * with ligne = null
	 */
	@Test
	public final void testDeleteWithLigneNull() {
		// given
		CodifLigneDTO ligne3 = null;

		// when
		codifLigneDAO.delete(ligne3);

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#deleteByCodeStif(java.lang.String)} with not
	 * existing ligne
	 */
	@Test
	public final void testDeleteByCodeStifWithNotExistingLigne() {
		// given
		String codeStif3 = "codeStif3";

		// when
		try {
			codifLigneDAO.deleteByCodeStif(codeStif3);
			Assert.fail("Test en erreur : L'exception TechnicalException est attendu mais n'a pas été levée.");
		} catch (TechnicalException e) {
		}

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#deleteByCodeStif(java.lang.String)} with
	 * already existing ligne
	 */
	@Test
	public final void testDeleteByCodeStifWithAlreadyExistingLigne() {
		// given

		// when
		codifLigneDAO.deleteByCodeStif(ligne1CodeStif);
		codifLigneDAO.deleteByCodeStif(ligne2CodeStif);

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(0, result.size());
		Assert.assertFalse(isLigneInList(ligne1, result));
		Assert.assertFalse(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#deleteByCodeStif(java.lang.String)} with code
	 * Stif = null
	 */
	@Test
	public final void testDeleteByCodeStifWithCodeStifNull() {
		// given
		String codeStif3 = null;

		// when
		codifLigneDAO.deleteByCodeStif(codeStif3);

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#deleteByCodeAdministratif(java.lang.String)}
	 * with not existing ligne
	 */
	@Test
	public final void testDeleteByCodeAdministratifWithNotExistingLigne() {
		// given
		String codeAdministratif3 = "codeAdministratif3";

		// when
		try {
			codifLigneDAO.deleteByCodeAdministratif(codeAdministratif3);
			Assert.fail("Test en erreur : L'exception TechnicalException est attendu mais n'a pas été levée.");
		} catch (TechnicalException e) {
		}

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#deleteByCodeAdministratif(java.lang.String)}
	 * with already existing ligne
	 */
	@Test
	public final void testDeleteByCodeAdministratifWithAlreadyExistingLigne() {
		// given

		// when
		codifLigneDAO.deleteByCodeAdministratif(ligne11CodeAdministratif);

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(2, result.size());
		Assert.assertFalse(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#deleteByCodeAdministratif(java.lang.String)}
	 * with code Administratif = null
	 */
	@Test
	public final void testDeleteByCodeAdministratifWithCodeAdministratifNull() {
		// given
		String codeAdministratif3 = null;

		// when
		codifLigneDAO.deleteByCodeAdministratif(codeAdministratif3);

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#deleteByCodeCommercial(java.lang.String)}
	 * with not existing ligne
	 */
	@Test
	public final void testDeleteByCodeCommercialWithNotExistingLigne() {
		// given
		String codeCommercial3 = "codeCommercial3";

		// when
		try {
			codifLigneDAO.deleteByCodeAdministratif(codeCommercial3);
			Assert.fail("Test en erreur : L'exception TechnicalException est attendu mais n'a pas été levée.");
		} catch (TechnicalException e) {
		}

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#deleteByCodeCommercial(java.lang.String)}
	 * with already existing ligne
	 */
	@Test
	public final void testDeleteByCodeCommercialWithAlreadyExistingLigne() {
		// given

		// when
		codifLigneDAO.deleteByCodeCommercial(ligne1CodeCommercial);

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(1, result.size());
		Assert.assertFalse(isLigneInList(ligne1, result));
		Assert.assertFalse(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#deleteByCodeCommercial(java.lang.String)}
	 * with code Commercial = null
	 */
	@Test
	public final void testDeleteByCodeCommercialWithCodeCommercialNull() {
		// given
		String codeCommercial3 = null;

		// when
		codifLigneDAO.deleteByCodeAdministratif(codeCommercial3);

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(isLigneInList(ligne1, result));
		Assert.assertTrue(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#reset(java.lang.String)}
	 */
	@Test
	public final void testReset() {
		// given

		// when
		codifLigneDAO.reset();

		// then
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(0, result.size());
		Assert.assertFalse(isLigneInList(ligne1, result));
		Assert.assertFalse(isLigneInList(ligne2, result));
	}

	/**
	 * Test method for {@link com.sirh.mqd.commons.storage.dao.impl.CodifLigneTamponDAO#reset(java.lang.String)} without lignes in
	 * Tampon
	 */
	@Test
	public final void testResetWithoutLignes() {
		// given
		// Le tampon doit être vide
		codifLigneDAO.reset();
		List<CodifLigneDTO> result = codifLigneDAO.getAllLignes();
		Assert.assertEquals(0, result.size());

		// when
		codifLigneDAO.reset();

		// then
	}
}
