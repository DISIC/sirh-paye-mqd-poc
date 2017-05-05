package com.thalesgroup.stif.reporting.core.generation;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.sirh.mqd.commons.traces.enums.EnumActionNature;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.generation.Rapport5;
import com.sirh.mqd.reporting.persistence.bc.ElasticsearchBC;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration({ "classpath:/application-context-test.xml" })
public class Rapport5Test {

	@InjectMocks
	@Autowired
	private Rapport5 rapport5;

	@Mock
	ElasticsearchBC elasticsearchBC;

	Date dateDebut = null;

	Date dateFin = null;

	@Before
	public void setUp() throws ParseException {

		MockitoAnnotations.initMocks(this);
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		dateDebut = formatter.parse("2013-11-12 19:28:55");
		dateFin = formatter.parse("2013-12-12 19:28:55");
		rapport5.setRepertoire("/tmp");
	}

	@Test
	public void buildRapportVisibleTestKO() throws DRException, InterruptedException, FileNotFoundException {
		//Données vides
		Mockito.when(
				elasticsearchBC.findByActionNature(Mockito.eq(EnumActionNature.SUCCESS.name()), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(new Long(0L));

		//Données vides
		Mockito.when(
				elasticsearchBC.findByActionNature(Mockito.eq(EnumActionNature.ILLEGAL_ACCESS.name()), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(new Long(0L));

		rapport5.build(dateDebut, dateFin);

		// Si le path est nul, le fichier n'est pas généré
		Assert.assertNotNull(rapport5.getFilePath());

	}

	@Test
	public void buildRapportVisibleTest() throws DRException, InterruptedException, FileNotFoundException {
		Mockito.when(
				elasticsearchBC.findByActionNature(Mockito.eq(EnumActionNature.SUCCESS.name()), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(new Long(initDataSourceBouchon(EnumActionNature.SUCCESS.name())));

		Mockito.when(
				elasticsearchBC.findByActionNature(Mockito.eq(EnumActionNature.ILLEGAL_ACCESS.name()), Mockito.any(Date.class),
						Mockito.any(Date.class))).thenReturn(new Long(initDataSourceBouchon(EnumActionNature.ILLEGAL_ACCESS.name())));

		rapport5.build(dateDebut, dateFin);

		Assert.assertTrue(rapport5.dataSource != null);

		final File file = new File(rapport5.getFilePath());
		System.out.println(rapport5.getFilePath());

		Assert.assertTrue(file.exists());
		Assert.assertTrue(file.length() > 0);

	}

	/**
	 * Initialise les données du graph à pour un partenaire spécifique
	 *
	 * @param plageEntry
	 *            partenaire accompagné de sa plage d'initialisation
	 */
	private Long initDataSourceBouchon(final String actions) {

		Long result = 0L;

		//Définition des index de la datasource
		rapport5.dataSource = new DRDataSource("semaine", "date", "autorise", "illicite");

		final Date startDate = dateDebut;
		final Date endDate = dateFin;
		//Initialisation de l'environnement temporel
		Date date = startDate;
		Date finSemaine = DateUtils.addDays(date, 7);
		String semaine = rapport5.formatSemaine(date);

		//Population de la datasource
		while (endDate.after(date)) {
			final Date dateSuivante = DateUtils.addTime(date, 0, rapport5.PAS, 0);

			if (date.after(finSemaine)) {
				semaine = rapport5.formatSemaine(date);
				finSemaine = DateUtils.addDays(date, 7);
			}

			final long autorise = countBouchon(date) * 10;
			final long ilicite = countBouchon(date);

			rapport5.dataSource.add(semaine, date, autorise, ilicite);
			date = dateSuivante;

			if (actions == EnumActionNature.ILLEGAL_ACCESS.name()) {
				result = ilicite;
			}
			if (actions == EnumActionNature.SUCCESS.name()) {
				result = autorise;
			}
			//
			//			for (int i = 0; i < 50; i++) {
			//				IHMActionEntity action = new IHMActionEntity();
			//				action.setId(String.valueOf(i));
			//				action.setMessage("2014-11-25 12:31:20,166 [INFO] c.thalesgroup.stif.log.system.action - [ACTION_IHM][r@r.r][ROLE_ADMINISTRATEUR][CREATION_MODIFICATION][SUCCESS][SITE][null][{\"id\":10,\"name\":\"CRON_RAPPORT\",\"value\":\"0 0 0 2 1/1 ?\",\"description\":\"Cron pour les rapports\"}]");
			//				action.setPath("/var/log/stif/metier/acquisition-relais-error.log");
			//				action.setLog_date("2014-11-25 12:31:20,166");
			//				action.setLog_level("ERROR");
			//				action.setClassname("c.t.s.log.metier.acquisition.error");
			//				action.setAction_ihm(UserActionEnum.ACTION_IHM.name());
			//				action.setEmail("r@r.r");
			//				action.setRole("ROLE_ADMINISTRATEUR");
			//				action.setAction_type(EnumActionType.CREATION_MODIFICATION.name());
			//				if (i > 32 && i < 46) {
			//					action.setAction_nature(EnumActionNature.ILLEGAL_ACCESS.name());
			//				} else {
			//					action.setAction_nature(EnumActionNature.SUCCESS.name());
			//				}
			//				action.setEcran_type(EnumEcranType.SITE.name());
			//				action.setId_metier(null);
			//
			//			}
		}

		return result;

	}

	private long countBouchon(final Date date) {
		float multiplicateur = 1;
		if (date.before(DateUtils.parseHoraire("8:00", date))) {
			multiplicateur = 0.25f;
		} else if (date.before(DateUtils.parseHoraire("10:00", date))) {
			multiplicateur = 0.5f;
		} else if (date.before(DateUtils.parseHoraire("12:00", date))) {
			multiplicateur = 1.5f;
		} else if (date.before(DateUtils.parseHoraire("14:00", date))) {
			multiplicateur = 0.5f;
		} else if (date.before(DateUtils.parseHoraire("16:00", date))) {
			multiplicateur = 2f;
		} else if (date.before(DateUtils.parseHoraire("18:00", date))) {
			multiplicateur = 1f;
		} else if (date.before(DateUtils.parseHoraire("20:00", date))) {
			multiplicateur = 0.5f;
		} else if (date.before(DateUtils.parseHoraire("24:00", date))) {
			multiplicateur = 0.25f;
		}
		return (long) (((((int) (Math.random() * 10))) + 5 * (Math.random() * 5) + 10) * multiplicateur);
	}
}
