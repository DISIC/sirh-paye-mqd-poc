package com.thalesgroup.stif.commons.tampon.dao.impl;

import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;

import com.lordofthejars.nosqlunit.redis.EmbeddedRedisInstances;
import com.lordofthejars.nosqlunit.redis.RedisRule;
import com.lordofthejars.nosqlunit.redis.RedisRule.RedisRuleBuilder;
import com.sirh.mqd.commons.exchanges.dto.pivot.Extensions;
import com.sirh.mqd.commons.exchanges.dto.pivot.GeneralMessage;
import com.sirh.mqd.commons.exchanges.dto.pivot.LineSection;
import com.sirh.mqd.commons.exchanges.dto.pivot.Message;
import com.sirh.mqd.commons.exchanges.dto.pivot.MessageText;
import com.sirh.mqd.commons.exchanges.dto.pivot.SituationRef;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.impl.GeneralMessageDAO;
import com.sirh.mqd.commons.test.EmbeddedRedisThales;
import com.sirh.mqd.commons.test.JedisConnectionFactoryThales;
import com.sirh.mqd.commons.test.EmbeddedRedisThales.EmbeddedRedisRuleBuilderThales;
import com.sirh.mqd.commons.utils.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tampon-context-test.xml")
public class GeneralMessageDAOTest {

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
	private GeneralMessageDAO generalMessageDAO;

	@Before
	public void before() {
		Jedis jedis = EmbeddedRedisInstances.getInstance().getDefaultJedis();
		jedisConnectionFactory.setJedis(jedis);
	}

	@Test
	public void testAddGeneralMessage() {
		GeneralMessage generalMessage = new GeneralMessage();
		generalMessage.setDestinationRef(Arrays.asList(""));
		Extensions extensions = new Extensions();
		extensions.setAny(null);
		generalMessage.setExtensions(extensions);
		generalMessage.setGroupOfLinesRef(Arrays.asList("1-metro"));
		generalMessage.setInfoChannelRef("Perturbation impo");
		generalMessage.setInfoMessageIdentifier("RATP:InfoMessage::P_19/02/2016_15.00.00:LOC");
		generalMessage.setInfoMessageVersion("");
		generalMessage.setItemIdentifier("RATP:Item::Arret_21998_99077590:LOC");
		generalMessage.setJourneyPatternRef(Arrays.asList(""));
		generalMessage.setLineref(Arrays.asList("C01381"));
		LineSection lineSection = new LineSection();
		lineSection.setLineRef("");
		generalMessage.setLineSection(Arrays.asList(lineSection));
		Message message = new Message();
		message.setNumberOfLines(0);
		message.setNumerOfCharPerLine(0);
		MessageText messageText = new MessageText();
		messageText.setMessage("--Message de pertubation--");
		messageText.setLanguage("fr");
		message.setMessageText(messageText);
		generalMessage.setMessage(Arrays.asList(message));
		generalMessage.setProducerRef("RATP");
		generalMessage.setRecorderAtDate(new Date());
		generalMessage.setRouteRef(Arrays.asList(""));
		generalMessage.setSituationRef(new SituationRef());
		generalMessageDAO.ajouterGeneralMessage(generalMessage, 1l);
		Assert.assertTrue(generalMessageDAO.listeAllGeneralMessage().size() == 1);

	}
	
	@Test
	public void testEstExpire() {
		GeneralMessage infoMesg = new GeneralMessage();
		// AJOUT D'UNE HEURE
		infoMesg.setValidUntilDate(DateUtils.addTime(DateUtils.getNowSQL(), 1, 0, 0));
	
		Assert.assertFalse(generalMessageDAO.estExpire(infoMesg) );
		
		// SOUSTRACTION D'UNE HEURE
		infoMesg.setValidUntilDate(DateUtils.addDays(DateUtils.getNowSQL(), -1));
		Assert.assertTrue(generalMessageDAO.estExpire(infoMesg) );
	}
}
