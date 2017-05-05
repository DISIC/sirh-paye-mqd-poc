package com.thalesgroup.stif.commons.utils.test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import org.fest.assertions.Assertions;
import org.junit.Assert;
import org.junit.Test;

import com.sirh.mqd.commons.exchanges.exception.TechnicalException;
import com.sirh.mqd.commons.utils.DateUtils;

public class DateUtilsTest {

	/******************************************************************************************/
	/** Tests de : {@link DateUtils#formateDateEEEEddMMMMyyyy(Date)} */

	/**
	 * Test de la méthode {@link DateUtils#formateDateEEEEddMMMMyyyy(Date)}. <br/>
	 * Cas nominal.
	 */
	@Test
	public void testFormateDateEEEEddMMMMyyyyOOCasNominal() {
		Calendar c = DateUtils.getCalendarInstance();
		c.set(Calendar.YEAR, 2012);
		c.set(Calendar.MONTH, Calendar.DECEMBER);
		c.set(Calendar.DAY_OF_MONTH, 31);

		Date date = c.getTime();

		String resultat = DateUtils.formateDateEEEEddMMMMyyyy(date);

		Assertions.assertThat(resultat).isNotNull();
		Assertions.assertThat(resultat).isEqualTo("lundi 31 décembre 2012");
	}

	/**
	 * Test de la méthode {@link DateUtils#formateDateEEEEddMMMMyyyy(Date)}. <br/>
	 * Cas où la date fournie est null.
	 */
	@Test
	public void testFormateDateEEEEddMMMMyyyyO1DateEnEntreeNull() {
		String resultat = DateUtils.formateDateEEEEddMMMMyyyy(null);

		Assertions.assertThat(resultat).isNull();
	}

	/******************************************************************************************/
	/** Tests de : {@link DateUtils#parseeDateAAAAMMJJhhmmss(String)} */

	/**
	 * Test de la méthode {@link DateUtils#parseeDateAAAAMMJJhhmmss(String)}.<br/>
	 * Cas nominal.
	 */
	@Test
	public void testParseeDateAAAAMMJJhhmmss00CasNominal() throws Exception {
		// Initialisations
		String chaineEnEntree = "2013-02-13T05:21:38Z";

		// Appel de la méthode
		Date resultat = DateUtils.parseeDateAAAAMMJJhhmmss(chaineEnEntree);

		// Vérifications
		Assertions.assertThat(resultat).isNotNull();

		Calendar calResultat = DateUtils.getCalendarInstance();
		calResultat.setTime(resultat);

		Assertions.assertThat(calResultat.get(Calendar.YEAR)).isEqualTo(2013);
		Assertions.assertThat(calResultat.get(Calendar.MONTH)).isEqualTo(Calendar.FEBRUARY);
		Assertions.assertThat(calResultat.get(Calendar.DAY_OF_MONTH)).isEqualTo(13);
		Assertions.assertThat(calResultat.get(Calendar.HOUR_OF_DAY)).isEqualTo(5);
		Assertions.assertThat(calResultat.get(Calendar.MINUTE)).isEqualTo(21);
		Assertions.assertThat(calResultat.get(Calendar.SECOND)).isEqualTo(38);
		Assertions.assertThat(calResultat.get(Calendar.MILLISECOND)).isEqualTo(0);
	}

	/**
	 * Test de la méthode {@link DateUtils#parseeDateAAAAMMJJhhmmss(String)}.<br/>
	 * Cas null en entrée.
	 */
	@Test
	public void testParseeDateAAAAMMJJhhmmss01NulnEnEntree() throws Exception {
		// Initialisations
		String chaineEnEntree = null;

		// Appel de la méthode
		Date resultat = DateUtils.parseeDateAAAAMMJJhhmmss(chaineEnEntree);

		// Vérifications
		Assertions.assertThat(resultat).isNull();
	}

	/******************************************************************************************/
	/** Tests de : {@link DateUtils#parseeDateAAAAMMJJhhmmsss(String)} */

	/**
	 * Test de la méthode {@link DateUtils#parseeDateAAAAMMJJhhmmsss(String)}.<br/>
	 * Cas nominal.
	 */
	@Test
	public void testParseeDateAAAAMMJJhhmmsss00CasNominal() throws Exception {
		// Initialisations
		String chaineEnEntree = "2013-11-15T17:09:00,160+0000";

		// Appel de la méthode
		Date resultat = DateUtils.parseeDateAAAAMMJJhhmmsss(chaineEnEntree);

		// Vérifications
		Assertions.assertThat(resultat).isNotNull();

		Calendar calResultat = DateUtils.getCalendarInstance();
		calResultat.setTime(resultat);

		Assertions.assertThat(calResultat.get(Calendar.YEAR)).isEqualTo(2013);
		Assertions.assertThat(calResultat.get(Calendar.MONTH)).isEqualTo(Calendar.NOVEMBER);
		Assertions.assertThat(calResultat.get(Calendar.DAY_OF_MONTH)).isEqualTo(15);
		Assertions.assertThat(calResultat.get(Calendar.HOUR_OF_DAY)).isEqualTo(17);
		Assertions.assertThat(calResultat.get(Calendar.MINUTE)).isEqualTo(9);
		Assertions.assertThat(calResultat.get(Calendar.SECOND)).isEqualTo(0);
		Assertions.assertThat(calResultat.get(Calendar.MILLISECOND)).isEqualTo(160);
	}

	/**
	 * Test de la méthode {@link DateUtils#parseeDateAAAAMMJJhhmmsss(String)}.<br/>
	 * Cas null en entrée.
	 */
	@Test
	public void testParseeDateAAAAMMJJhhmmsss01NulnEnEntree() throws Exception {
		// Initialisations
		String chaineEnEntree = null;

		// Appel de la méthode
		Date resultat = DateUtils.parseeDateAAAAMMJJhhmmsss(chaineEnEntree);

		// Vérifications
		Assertions.assertThat(resultat).isNull();
	}

	/******************************************************************************************/
	/** Tests de : {@link DateUtils#formateDateJJMMAAAA(Date)} */

	/**
	 * Test de la méthode {@link DateUtils#formateDateJJMMAAAA(Date)}. <br/>
	 * Cas nominal.
	 */
	@Test
	public void testFormateDateJJMMAAAAOOCasNominal() {
		Calendar c = DateUtils.getCalendarInstance();
		c.set(Calendar.YEAR, 2012);
		c.set(Calendar.MONTH, Calendar.DECEMBER);
		c.set(Calendar.DAY_OF_MONTH, 31);

		Date date = c.getTime();

		String resultat = DateUtils.formateDateJJMMAAAA(date);

		Assertions.assertThat(resultat).isNotNull();
		Assertions.assertThat(resultat).isEqualTo("31/12/2012");
	}

	/**
	 * Test de la méthode {@link DateUtils#formateDateJJMMAAAA(Date)}. <br/>
	 * Cas où la date fournie est null.
	 */
	@Test
	public void testFormateDateJJMMAAAAO1DateEnEntreeNull() {
		String resultat = DateUtils.formateDateJJMMAAAA(null);

		Assertions.assertThat(resultat).isNull();
	}

	/******************************************************************************************/
	/** Tests de : {@link DateUtils#parseeDateJJMMAAAA(String)} */

	/**
	 * Test de la méthode {@link DateUtils#parseeDateJJMMAAAA(String)}.<br/>
	 * Cas nominal.
	 */
	@Test
	public void testParseeDateJJMMAAAA00CasNominal() throws Exception {
		// Initialisations
		String chaineEnEntree = "13/02/2013";

		// Appel de la méthode
		Date resultat = DateUtils.parseeDateJJMMAAAA(chaineEnEntree);

		// Vérifications
		Assertions.assertThat(resultat).isNotNull();

		Calendar calResultat = DateUtils.getCalendarInstance();
		calResultat.setTime(resultat);

		Assertions.assertThat(calResultat.get(Calendar.YEAR)).isEqualTo(2013);
		Assertions.assertThat(calResultat.get(Calendar.MONTH)).isEqualTo(Calendar.FEBRUARY);
		Assertions.assertThat(calResultat.get(Calendar.DAY_OF_MONTH)).isEqualTo(13);
		Assertions.assertThat(calResultat.get(Calendar.HOUR_OF_DAY)).isEqualTo(0);
		Assertions.assertThat(calResultat.get(Calendar.MINUTE)).isEqualTo(0);
		Assertions.assertThat(calResultat.get(Calendar.SECOND)).isEqualTo(0);
		Assertions.assertThat(calResultat.get(Calendar.MILLISECOND)).isEqualTo(0);
	}

	/**
	 * Test de la méthode {@link DateUtils#parseeDateJJMMAAAA(String)}.<br/>
	 * Cas null en entrée.
	 */
	@Test
	public void testParseeDateJJMMAAAA01NulnEnEntree() throws Exception {
		// Initialisations
		String chaineEnEntree = null;

		// Appel de la méthode
		Date resultat = DateUtils.parseeDateJJMMAAAA(chaineEnEntree);

		// Vérifications
		Assertions.assertThat(resultat).isNull();
	}

	/**
	 * Test de la méthode {@link DateUtils#parseeDateJJMMAAAA(String)}.<br/>
	 * Cas chaine en entrée invalide
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testParseeDateJJMMAAAA02EntreeInvalide() throws Exception {
		// Initialisations
		String chaineEnEntree = "azerty";

		// Appel de la méthode
		DateUtils.parseeDateJJMMAAAA(chaineEnEntree);
	}

	/**
	 * Test de la méthode {@link DateUtils#parseeDateJJMMAAAA(String)}.<br/>
	 * Cas chaine en entrée invalide
	 */
	@Test
	public void testParseeDateJJMMAAAAhhmmss00CasNormal() throws Exception {
		// Initialisations
		String chaineEnEntree = "30/04/2013 12:30:52";

		// Appel de la méthode
		Date resultat = DateUtils.parseeDateJJMMAAAAhhmmss(chaineEnEntree);

		// Vérifications
		Assertions.assertThat(resultat).isNotNull();

		Calendar cal = DateUtils.getCalendarInstance();
		cal.setTime(resultat);
		Assertions.assertThat(cal.get(Calendar.YEAR)).isEqualTo(2013);
		Assertions.assertThat(cal.get(Calendar.DAY_OF_MONTH)).isEqualTo(30);
		Assertions.assertThat(cal.get(Calendar.MONTH)).isEqualTo(Calendar.APRIL);
		Assertions.assertThat(cal.get(Calendar.HOUR_OF_DAY)).isEqualTo(12);
		Assertions.assertThat(cal.get(Calendar.MINUTE)).isEqualTo(30);
		Assertions.assertThat(cal.get(Calendar.SECOND)).isEqualTo(52);
	}

	@Test
	public void testParseeDateJJMMAAAAhhmmss01CasNul() {
		Date resultat = DateUtils.parseeDateJJMMAAAAhhmmss(null);
		Assertions.assertThat(resultat).isNull();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseeDateJJMMAAAAhhmmss01CasNonParsable() {
		DateUtils.parseeDateJJMMAAAAhhmmss("azerty");
	}

	/**
	 * Test de la méthode {@link DateUtils#parseeDateJJMMAAAA(String)}.<br/>
	 * Cas chaine en entrée invalide
	 */
	@Test
	public void testParseeDateJJMMAAAAhhmmssSSS00CasNormal() throws Exception {
		// Initialisations
		String chaineEnEntree = "30/04/2013 12:30:52.025";

		// Appel de la méthode
		Date resultat = DateUtils.parseeDateJJMMAAAAhhmmssSSS(chaineEnEntree);

		// Vérifications
		Assertions.assertThat(resultat).isNotNull();

		Calendar cal = DateUtils.getCalendarInstance();
		cal.setTime(resultat);
		Assertions.assertThat(cal.get(Calendar.YEAR)).isEqualTo(2013);
		Assertions.assertThat(cal.get(Calendar.DAY_OF_MONTH)).isEqualTo(30);
		Assertions.assertThat(cal.get(Calendar.MONTH)).isEqualTo(Calendar.APRIL);
		Assertions.assertThat(cal.get(Calendar.HOUR_OF_DAY)).isEqualTo(12);
		Assertions.assertThat(cal.get(Calendar.MINUTE)).isEqualTo(30);
		Assertions.assertThat(cal.get(Calendar.SECOND)).isEqualTo(52);
		Assertions.assertThat(cal.get(Calendar.MILLISECOND)).isEqualTo(25);
	}

	@Test
	public void testParseeDateJJMMAAAAhhmmssSSS01CasNul() {
		Date resultat = DateUtils.parseeDateJJMMAAAAhhmmssSSS(null);
		Assertions.assertThat(resultat).isNull();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseeDateJJMMAAAAhhmmssSSS01CasNonParsable() {
		DateUtils.parseeDateJJMMAAAAhhmmssSSS("azerty");
	}

	@Test
	public void testFormateDateJJMMAAAA00CasNominal() {
		Calendar cal = DateUtils.getCalendarInstance();
		cal.set(Calendar.YEAR, 2012);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 23);

		String res = DateUtils.formateDateJJMMAAAA(cal.getTime());

		Assertions.assertThat(res).isEqualTo("23/03/2012");
	}

	@Test
	public void testFormateDateJJMMAAAA00CasNul() {
		String res = DateUtils.formateDateJJMMAAAA(null);

		Assertions.assertThat(res).isNull();
	}

	@Test
	public void testFormateDateJJMMAAAAhhmm00CasNominal() {
		Calendar cal = DateUtils.getCalendarInstance();
		cal.set(Calendar.YEAR, 2012);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 23);
		cal.set(Calendar.HOUR_OF_DAY, 15);
		cal.set(Calendar.MINUTE, 53);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		String res = DateUtils.formateDateJJMMAAAAhhmm(cal.getTime());

		Assertions.assertThat(res).isEqualTo("23/03/2012 15:53");
	}

	@Test
	public void testFormateDateJJMMAAAAhhmm00CasNul() {
		String res = DateUtils.formateDateJJMMAAAAhhmm(null);

		Assertions.assertThat(res).isNull();
	}

	@Test
	public void testFormateDateJJMMAAAAhhmmss00CasNominal() {
		Calendar cal = DateUtils.getCalendarInstance();
		cal.set(Calendar.YEAR, 2012);
		cal.set(Calendar.MONTH, Calendar.FEBRUARY);
		cal.set(Calendar.DAY_OF_MONTH, 23);
		cal.set(Calendar.HOUR_OF_DAY, 15);
		cal.set(Calendar.MINUTE, 53);
		cal.set(Calendar.SECOND, 36);
		cal.set(Calendar.MILLISECOND, 0);

		String res = DateUtils.formateDateJJMMAAAAhhmmss(cal.getTime());

		Assertions.assertThat(res).isEqualTo("23/02/2012 15:53:36");
	}

	@Test
	public void testFormateDateJJMMAAAAhhmmss00CasNul() {
		String res = DateUtils.formateDateJJMMAAAAhhmmss(null);

		Assertions.assertThat(res).isNull();
	}

	@Test
	public void testFormateDateJJMMAAAAhhmmssSSS00CasNominal() {
		Calendar cal = DateUtils.getCalendarInstance();
		cal.set(Calendar.YEAR, 2012);
		cal.set(Calendar.MONTH, Calendar.FEBRUARY);
		cal.set(Calendar.DAY_OF_MONTH, 23);
		cal.set(Calendar.HOUR_OF_DAY, 15);
		cal.set(Calendar.MINUTE, 53);
		cal.set(Calendar.SECOND, 36);
		cal.set(Calendar.MILLISECOND, 25);

		String res = DateUtils.formateDateJJMMAAAAhhmmssSSS(cal.getTime());

		Assertions.assertThat(res).isEqualTo("23/02/2012 15:53:36.025");
	}

	@Test
	public void testFormateDateJJMMAAAAhhmmssSSS00CasNul() {
		String res = DateUtils.formateDateJJMMAAAAhhmmss(null);

		Assertions.assertThat(res).isNull();
	}

	@Test
	public void testCompareDates00CasNominal() {
		Calendar cal1 = DateUtils.getCalendarInstance();
		cal1.set(Calendar.YEAR, 2012);
		cal1.set(Calendar.MONTH, Calendar.FEBRUARY);
		cal1.set(Calendar.DAY_OF_MONTH, 23);
		cal1.set(Calendar.HOUR_OF_DAY, 15);
		cal1.set(Calendar.MINUTE, 23);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);

		Calendar cal2 = DateUtils.getCalendarInstance();
		cal2.set(Calendar.YEAR, 2012);
		cal2.set(Calendar.MONTH, Calendar.FEBRUARY);
		cal2.set(Calendar.DAY_OF_MONTH, 23);
		cal2.set(Calendar.HOUR_OF_DAY, 14);
		cal2.set(Calendar.MINUTE, 26);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);

		Calendar cal3 = DateUtils.getCalendarInstance();
		cal3.set(Calendar.YEAR, 2012);
		cal3.set(Calendar.MONTH, Calendar.FEBRUARY);
		cal3.set(Calendar.DAY_OF_MONTH, 24);
		cal3.set(Calendar.HOUR_OF_DAY, 14);
		cal3.set(Calendar.MINUTE, 23);
		cal3.set(Calendar.SECOND, 0);
		cal3.set(Calendar.MILLISECOND, 0);

		Assertions.assertThat(DateUtils.compareDates(cal1.getTime(), cal2.getTime())).isEqualTo(0);
		Assertions.assertThat(DateUtils.compareDates(cal1.getTime(), cal3.getTime())).isLessThan(0);
		Assertions.assertThat(DateUtils.compareDates(cal3.getTime(), cal1.getTime())).isGreaterThan(0);
	}

	@Test
	public void testConvertXmlGregoriancalendarToDate00CasNominal() throws DatatypeConfigurationException {
		Calendar calendar = DateUtils.getCalendarInstance();
		XMLGregorianCalendar xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) calendar);

		Date date = DateUtils.convertXmlGregoriancalendarToDate(xmlCalendar);

		Assertions.assertThat(date).isNotNull();

		Calendar cal = DateUtils.getCalendarInstance();
		cal.setTime(date);
		Assertions.assertThat(cal.get(Calendar.YEAR)).isEqualTo(calendar.get(Calendar.YEAR));
		Assertions.assertThat(cal.get(Calendar.MONTH)).isEqualTo(calendar.get(Calendar.MONTH));
		Assertions.assertThat(cal.get(Calendar.DAY_OF_MONTH)).isEqualTo(calendar.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testConvertXmlGregoriancalendarToDate01CasNul() {
		Date date = DateUtils.convertXmlGregoriancalendarToDate(null);
		Assertions.assertThat(date).isNull();
	}

	@Test
	public void testconvertDateToXmlGregorianCalendar00CasNominal() throws DatatypeConfigurationException {
		Calendar cal = DateUtils.getCalendarInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 25);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		XMLGregorianCalendar xmlCalendar = DateUtils.convertDateToXmlGregorianCalendar(cal.getTime());
		Assertions.assertThat(xmlCalendar).isNotNull();
		Assertions.assertThat(xmlCalendar.getYear()).isEqualTo(2013);
		Assertions.assertThat(xmlCalendar.getMonth()).isEqualTo(3);
		Assertions.assertThat(xmlCalendar.getDay()).isEqualTo(25);
	}

	@Test
	public void testconvertDateToXmlGregorianCalendar00CasNul() throws DatatypeConfigurationException {
		XMLGregorianCalendar xmlCalendar = DateUtils.convertDateToXmlGregorianCalendar(null);
		Assertions.assertThat(xmlCalendar).isNull();
	}

	/**
	 * Test method for {@link com.cloudwatt.alerting.util.DateUtils#addDays(Date, int)}
	 */
	@Test
	public final void testAddDays() {
		Calendar cal = DateUtils.getCalendarInstance();
		cal.set(Calendar.YEAR, 2010);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 25);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		Date dateTest = cal.getTime();
		final Date test = DateUtils.getCalendarInstance().getTime();
		test.setTime(dateTest.getTime() - 432000000);
		Assertions.assertThat(dateTest).isEqualTo(DateUtils.addDays(test, 5));
	}

	/**
	 * Test method for
	 * <ul>
	 * <li>{@link com.cloudwatt.alerting.util.DateUtils#computeDate(int, int, int, int)}</li>
	 * </ul>
	 * Cas nominal.
	 */
	@Test
	public void testComputeDateCasNominal() {
		//Given
		int year = 2013;
		int month = 1;
		int day = 31;
		int hour = 0;

		//When
		Date resultat = DateUtils.computeDate(year, month, day, hour);

		//then
		Assertions.assertThat(resultat).isNotNull();

		Calendar value = DateUtils.getCalendarInstance();
		value.setTime(resultat);

		Assertions.assertThat(value.get(Calendar.YEAR)).isEqualTo(year);
		Assertions.assertThat(value.get(Calendar.MONTH)).isEqualTo(Calendar.JANUARY);
		Assertions.assertThat(value.get(Calendar.DAY_OF_MONTH)).isEqualTo(day);
		Assertions.assertThat(value.get(Calendar.HOUR_OF_DAY)).isEqualTo(hour);
		Assertions.assertThat(value.get(Calendar.MINUTE)).isEqualTo(0);
		Assertions.assertThat(value.get(Calendar.SECOND)).isEqualTo(0);
		Assertions.assertThat(value.get(Calendar.MILLISECOND)).isEqualTo(0);
	}

	/******************************************************************************************/
	/** Tests de : {@link SGAUtils#clonerDate(Date)} */

	/**
	 * Test de la méthode {@link CWUtils#clonerDate(Date)}.<br/>
	 * Cas nominal.
	 */
	@Test
	public void testClonerDate00CasNominal() {
		Calendar cal = DateUtils.getCalendarInstance();
		cal.set(Calendar.YEAR, 2012);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		cal.set(Calendar.HOUR_OF_DAY, 8);
		cal.set(Calendar.MINUTE, 13);
		cal.set(Calendar.SECOND, 45);
		cal.set(Calendar.MILLISECOND, 78);

		final Date dateAttendue = cal.getTime();

		final Date dateResultat = DateUtils.clonerDate(dateAttendue);

		/** Premier test qui vérifie que les deux objets sont différents. */
		Assert.assertFalse("La date clonée pointe sur la date de référence", dateAttendue == dateResultat);

		/** Deuxième test qui vérifie que les valeurs sont identiques. */
		Assert.assertEquals("La date clonée n'est pas égale à la date source", dateAttendue, dateResultat);
	}

	/**
	 * Test de la méthode {@link CWUtils#clonerDate(Date)}.<br/>
	 * Cas où la date à cloner est null.
	 */
	@Test
	public void testClonerDate01CasNullEnEntree() {
		final Date dateResultat = DateUtils.clonerDate(null);

		/** Deuxième test qui vérifié que les valeurs sont identiques. */
		Assert.assertNull("La date clonée n'est pas null", dateResultat);
	}

	/**
	 * Test de la méthode CompareTime. Cas ou les heures sont égales
	 */
	@Test
	public void testCompareTimeEquals() {
		//Given
		Date date1 = DateUtils.getCalendarInstance().getTime();
		long time1 = date1.getTime();
		long time2 = date1.getTime();
		//When
		int result = DateUtils.compareTime(time1, time2);
		//Then
		Assertions.assertThat(result).isEqualTo(0);
	}

	/**
	 * Test de la méthode CompareTime. Cas ou la première heure est superieur à la seconde
	 */
	@Test
	public void testCompareTimeTime1SuppTime2() {
		//Given
		Date date1 = DateUtils.getCalendarInstance().getTime();
		long time1 = date1.getTime() + 1;
		long time2 = date1.getTime();
		//When
		int result = DateUtils.compareTime(time1, time2);
		//Then
		Assertions.assertThat(result).isEqualTo(1);
	}

	/**
	 * Test de la méthode CompareTime. Cas ou la première heure est inférieur à la seconde
	 */
	@Test
	public void testCompareTimeTime1InfTime2() {
		//Given
		Date date1 = DateUtils.getCalendarInstance().getTime();
		long time1 = date1.getTime() - 1;
		long time2 = date1.getTime();
		//When
		int result = DateUtils.compareTime(time1, time2);
		//Then
		Assertions.assertThat(result).isEqualTo(-1);
	}

	@Test
	public void testStringToDuration() {
		String delayString = "PT1M";
		Duration duration = DateUtils.convertStringToDuration(delayString);
		Assertions.assertThat(duration.toString()).isEqualTo(delayString);

		String badString = "PT1MXX";
		try {
			duration = DateUtils.convertStringToDuration(badString);
			Assert.fail("Une exception technique aurait du etre remontée par le badString");
		} catch (TechnicalException t) {

		}
	}

	@Test
	public void testAddTime() {
		//Given
		int nbHeures = 1;
		int nbMin = 1;
		int nbSec = 1;
		Calendar calendar = DateUtils.getCalendarInstance();
		calendar.set(2014, 03, 28, 11, 35, 00);
		Calendar calendar1 = (Calendar) calendar.clone();
		calendar1.add(Calendar.HOUR, nbHeures);
		calendar1.add(Calendar.MINUTE, nbMin);
		calendar1.add(Calendar.SECOND, nbSec);
		Date date = calendar.getTime();
		Date date1 = calendar1.getTime();
		//When
		Date dateResult = DateUtils.addTime(date, nbHeures, nbMin, nbSec);
		//Then
		Assertions.assertThat(dateResult).isEqualTo(date1);
	}

	@Test
	public void testCron2period() throws Exception {
		Assertions.assertThat(DateUtils.cron2period("0 */1 * * * ?")).isEqualTo(60 + 60);
		Assertions.assertThat(DateUtils.cron2period("0/30 * * * * ?")).isEqualTo(30 + 60);
		Assertions.assertThat(DateUtils.cron2period("0 0 0/1 * * ?")).isEqualTo(3600 + 60);
		Assertions.assertThat(DateUtils.cron2period("0 0/11 * * * ?")).isEqualTo(660 + 60);
		Assertions.assertThat(DateUtils.cron2period("0 0/24 * * * ?")).isEqualTo(1440 + 60);
		Assertions.assertThat(DateUtils.cron2period("0 0/57 * * * ?")).isEqualTo(3420 + 60);
	}

	//STIF-235
	@Test
	public void testPreviewInterval() throws Exception {
		boolean outOfInterval = false;
		Date currentDate = DateUtils.getCalendarInstance().getTime();
		Duration previewInterval = DateUtils.convertStringToDuration("PT1H");

		Date date = DateUtils.addTime(currentDate, 2, 2, 5);
		XMLGregorianCalendar aimedArrivalDate = DateUtils.convertDateToXmlGregorianCalendar(date);
		aimedArrivalDate.setTimezone(60);
		date = DateUtils.addTime(currentDate, 2, 2, 50);
		XMLGregorianCalendar aimedDepartureDate = DateUtils.convertDateToXmlGregorianCalendar(date);
		aimedDepartureDate.setTimezone(60);
		date = DateUtils.addTime(currentDate, 2, 2, 10);
		XMLGregorianCalendar expectedArrivalDate = DateUtils.convertDateToXmlGregorianCalendar(date);
		expectedArrivalDate.setTimezone(60);
		date = DateUtils.addTime(currentDate, 2, 2, 55);
		XMLGregorianCalendar expectedDepartureDate = DateUtils.convertDateToXmlGregorianCalendar(date);
		expectedDepartureDate.setTimezone(60);
		outOfInterval = DateUtils.outOfInterval(aimedArrivalDate, aimedDepartureDate, expectedArrivalDate, expectedDepartureDate,
				previewInterval);
		Assertions.assertThat(outOfInterval).isEqualTo(true);

		date = DateUtils.addTime(currentDate, 1, 50, 5);
		aimedArrivalDate = DateUtils.convertDateToXmlGregorianCalendar(date);
		aimedArrivalDate.setTimezone(60);
		outOfInterval = DateUtils.outOfInterval(aimedArrivalDate, aimedDepartureDate, expectedArrivalDate, expectedDepartureDate,
				previewInterval);
		Assertions.assertThat(outOfInterval).isEqualTo(false);

		date = DateUtils.addTime(currentDate, 2, 2, 5);
		aimedArrivalDate = DateUtils.convertDateToXmlGregorianCalendar(date);
		aimedArrivalDate.setTimezone(60);
		date = DateUtils.addTime(currentDate, 1, 59, 10);
		expectedArrivalDate = DateUtils.convertDateToXmlGregorianCalendar(date);
		expectedArrivalDate.setTimezone(60);
		outOfInterval = DateUtils.outOfInterval(aimedArrivalDate, aimedDepartureDate, expectedArrivalDate, expectedDepartureDate,
				previewInterval);
		Assertions.assertThat(outOfInterval).isEqualTo(false);

		date = DateUtils.rollTime(currentDate, -2, -2, 0);
		aimedArrivalDate = DateUtils.convertDateToXmlGregorianCalendar(date);
		aimedArrivalDate.setTimezone(60);
		date = DateUtils.rollTime(currentDate, -2, -1, -50);
		aimedDepartureDate = DateUtils.convertDateToXmlGregorianCalendar(date);
		aimedDepartureDate.setTimezone(60);
		date = DateUtils.rollTime(currentDate, -2, -1, -51);
		expectedArrivalDate = DateUtils.convertDateToXmlGregorianCalendar(date);
		expectedArrivalDate.setTimezone(60);
		date = DateUtils.rollTime(currentDate, -2, -1, -55);
		expectedDepartureDate = DateUtils.convertDateToXmlGregorianCalendar(date);
		expectedDepartureDate.setTimezone(60);
		outOfInterval = DateUtils.outOfInterval(aimedArrivalDate, aimedDepartureDate, expectedArrivalDate, expectedDepartureDate,
				previewInterval);
		Assertions.assertThat(outOfInterval).isEqualTo(true);

		date = DateUtils.addTime(currentDate, 1, 50, 10);
		aimedDepartureDate = DateUtils.convertDateToXmlGregorianCalendar(date);
		aimedDepartureDate.setTimezone(60);
		outOfInterval = DateUtils.outOfInterval(aimedArrivalDate, aimedDepartureDate, expectedArrivalDate, expectedDepartureDate,
				previewInterval);
		Assertions.assertThat(outOfInterval).isEqualTo(false);

		date = DateUtils.rollTime(currentDate, -2, -2, 0);
		aimedDepartureDate = DateUtils.convertDateToXmlGregorianCalendar(date);
		aimedDepartureDate.setTimezone(60);
		date = DateUtils.addTime(currentDate, 1, 0, 10);
		expectedDepartureDate = DateUtils.convertDateToXmlGregorianCalendar(date);
		expectedDepartureDate.setTimezone(60);
		outOfInterval = DateUtils.outOfInterval(aimedArrivalDate, aimedDepartureDate, expectedArrivalDate, expectedDepartureDate,
				previewInterval);
		Assertions.assertThat(outOfInterval).isEqualTo(false);

	}
}