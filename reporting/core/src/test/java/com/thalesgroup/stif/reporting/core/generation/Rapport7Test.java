package com.thalesgroup.stif.reporting.core.generation;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import net.sf.dynamicreports.report.exception.DRException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;

import com.sirh.mqd.commons.exchanges.exception.ApplicationException;
import com.sirh.mqd.commons.storage.bc.ReflexTamponBC;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.generation.Rapport7;
import com.sirh.mqd.reporting.persistence.bc.ElasticsearchBC;
import com.sirh.mqd.reporting.persistence.entities.WSPerformanceEntity;

/**
 * @see doc.story.ref1664
 * @author adile
 *
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration({ "classpath:/application-context-test.xml" })
public class Rapport7Test {

	@InjectMocks
	@Autowired
	private Rapport7 rapport7;

	@Mock
	ElasticsearchBC elasticsearchBC;

	@Mock
	ReflexTamponBC reflexTamponBC;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		rapport7.setRepertoire("/tmp");

	}

	@Test
	public void buildRapportTest() throws DRException, InterruptedException, FileNotFoundException, ParseException, ApplicationException {

		Mockito.when(elasticsearchBC.find(Mockito.any(String.class), Mockito.any(Date.class), Mockito.any(Date.class))).then(
				new Answer<PageImpl<WSPerformanceEntity>>() {

					@Override
					public PageImpl<WSPerformanceEntity> answer(final InvocationOnMock invocation) throws Throwable {

						return new PageImpl<WSPerformanceEntity>(generateData());
					}
				});

		// creation du rapport
		rapport7.build(null, null);

		Assert.assertTrue(rapport7.dataSource != null);

		final File file = new File(rapport7.getFilePath());
		System.out.println(rapport7.getFilePath());

		Assert.assertTrue(file.exists());
		Assert.assertTrue(file.length() > 0);

	}

	/**
	 * @param content
	 * @throws ParseException
	 */
	private List<WSPerformanceEntity> generateData() throws ParseException {
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,S");
		final Date dateLog = formatter.parse("2014-11-12 19:28:55,053");
		final List<WSPerformanceEntity> content = new ArrayList<WSPerformanceEntity>();

		final List<String> services = new ArrayList<String>();
		services.add("getStopMonitoring");
		services.add("getGeneralMessage");

		// Données du mock
		for (int i = 0; i < 100; i++) {

			final Random r = new Random();
			final int valeur = 1 + r.nextInt(10);

			for (int j = 0; j < valeur; j++) {

				final Random rand = new Random();

				final WSPerformanceEntity echange = new WSPerformanceEntity();

				echange.setId(String.valueOf(1));
				echange.setMessage("2014-12-15 19:08:27,030 [INFO] c.t.stif.log.system.performance - [Service][getGeneralMessage][10]");
				echange.setPath("/var/log/stif/metier/acquisition-system-performance.log");
				echange.setLog_date(formatter.format(DateUtils.addTime(dateLog, i, 0, 0)));
				echange.setLog_level("INFO");
				echange.setClassname("c.t.s.log.metier.acquisition.info");
				echange.setService(services.get(rand.nextInt(services.size())));
				echange.setDuree(rand.nextInt(3000));

				content.add(echange);
			}
		}

		return content;
	}
}
