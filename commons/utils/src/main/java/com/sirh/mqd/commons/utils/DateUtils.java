package com.sirh.mqd.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.sirh.mqd.commons.exchanges.exception.TechnicalException;

/**
 * Utilitaires sur les dates.
 *
 * @author alexandre
 */
public final class DateUtils {

	/**
	 * Default value for Locale
	 */
	public static final Locale DEFAULT_LOCALE = Locale.FRENCH;

	/**
	 * Default value for Time Zone
	 */
	public static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone("UTC");

	/**
	 * Format de date "jj/mm/aaaa".
	 */
	private static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

	/**
	 * Format de date "aaaammjj".
	 */
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

	/**
	 * Format de date "aaaammjjhh:mm".
	 */
	public static final String DATE_FORMAT_YYYYMMDDHH_MM = "yyyyMMddHH:mm";

	/**
	 * Format de date "jj/mm/aaaa hh:mm"
	 */
	private static final String DATE_FORMAT_DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm";

	/**
	 * Format de date "jj/mm/aaaa hh:mm:ss"
	 */
	private static final String DATE_FORMAT_DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";

	/**
	 * Format de date "jj/mm/aaaa hh:mm:ss"
	 */
	private static final String DATE_FORMAT_DD_MM_YYYY_HH_MM_SS_SSS = "dd/MM/yyyy HH:mm:ss.SSS";

	/**
	 * Format de date "jj/mm/aaaa hh:mm:ss.SSS"
	 */
	private static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * Format de date "eeee jj mm aaaa"
	 */
	private static final String DATE_FORMAT_EEEE_DD_MMMM_YYYY = "EEEE dd MMMM yyyy";

	/**
	 * Format de date "yyyy-MM-dd'T'HH:mm:ss'Z'"
	 */
	private static final String DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	/**
	 * Format de date "yyyy-MM-dd'T'HH:mm:ss,SSSZ"
	 */
	private static final String DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SSSZ = "yyyy-MM-dd'T'HH:mm:ss,SSSZ";

	/**
	 * Format d'entrée pour une horaire
	 */
	public static final String INPUT_FORMAT = "HH:mm";

	/**
	 * Format de date "yyyy-MM-dd HH:mm:ss"
	 */
	private static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Parser d'horaire.
	 */

	/**
	 * Non constructeur.
	 *
	 * @throws InstantiationException
	 *             si tentative d'appel de ce constructeur.
	 */
	private DateUtils() throws InstantiationException {
		throw new InstantiationException("Création non autorisée d'une instance de : " + DateUtils.class.getName());
	}

	/**
	 * Parse une horaire et donne la date passée à l'horaire concernée
	 *
	 * @param horaire
	 * @return renvois la date passée à l'horaire concernée.
	 */
	public static Date parseHoraire(final String horaire, final Date date) {

		try {
			final SimpleDateFormat inputParser = new SimpleDateFormat(INPUT_FORMAT, Locale.US);
			final Calendar calParser = DateUtils.getCalendarInstance();
			calParser.setTime(inputParser.parse(horaire));
			final Calendar cal = DateUtils.getCalendarInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, calParser.get(Calendar.HOUR_OF_DAY));
			cal.set(Calendar.MINUTE, calParser.get(Calendar.MINUTE));
			return cal.getTime();
		} catch (final java.text.ParseException e) {
			return new Date(0);
		}
	}

	/**
	 * L'instance du Calendrier paramétrée en TimeZone "UTC" et Locale "FRENCH"
	 * à utiliser.
	 *
	 * @return Calendar l'instance du calendrier
	 */
	public static Calendar getCalendarInstance() {
		return GregorianCalendar.getInstance(DEFAULT_TIME_ZONE, DEFAULT_LOCALE);
	}

	/**
	 * Récupération d'un format Date
	 *
	 * @param date
	 *            la date à parser
	 *
	 * @return la date au format eeee jj mm aaaa
	 */
	public static String formateDateEEEEddMMMMyyyy(final Date date) {
		return formateDate(date, DATE_FORMAT_EEEE_DD_MMMM_YYYY);
	}

	/**
	 * Récupération d'un format jj/mm/aaaa
	 *
	 * @param date
	 *            : la date à parser
	 *
	 * @return la date au format jj/mm/aaaa
	 */
	public static String formateDateJJMMAAAA(final Date date) {
		return formateDate(date, DATE_FORMAT_DD_MM_YYYY);
	}

	/**
	 * Récupération d'un format jj/mm/aaaa
	 *
	 * @param date
	 *            : la date à parser
	 *
	 * @return la date au format jj/mm/aaaa
	 */
	public static String formateDateJJMMAAAAhhmm(final Date date) {
		return formateDate(date, DATE_FORMAT_DD_MM_YYYY_HH_MM);
	}

	/**
	 * Récupération d'un format Date
	 *
	 * @param date
	 *            la date à parser
	 *
	 * @return la date au format jj/mm/aaaa hh:mm:ss
	 */
	public static String formateDateJJMMAAAAhhmmss(final Date date) {
		return formateDate(date, DATE_FORMAT_DD_MM_YYYY_HH_MM_SS);
	}

	/**
	 * Récupération d'un format Date
	 *
	 * @param date
	 *            la date à parser
	 *
	 * @return la date au format jj/mm/aaaa hh:mm:ss
	 */
	public static String formateDateMailJJMMAAAAhhmmss(final Date date) {
		String dateRetour = null;
		if (date != null) {
			final SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY_HH_MM_SS, DEFAULT_LOCALE);
			dateRetour = format.format(date);
		}
		return dateRetour;
	}

	/**
	 * Récupération d'un format Date
	 *
	 * @param date
	 *            la date à parser
	 *
	 * @return la date au format jj/mm/aaaa hh:mm:ss.SSS
	 */
	public static String formateDateJJMMAAAAhhmmssSSS(final Date date) {
		return formateDate(date, DATE_FORMAT_DD_MM_YYYY_HH_MM_SS_SSS);
	}

	/**
	 * Récupération d'un format Date
	 *
	 * @param date
	 *            la date à parser
	 *
	 * @return la date au format jj/mm/aaaa hh:mm:ss.SSS
	 */
	public static String formateDateAAAAMMJJhhmmss(final Date date) {
		return formateDate(date, DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * Récupération d'un format Date
	 *
	 * @param date
	 *            la date à parser
	 *
	 * @return la date au format jj/mm/aaaa hh:mm:ss.SSS
	 */
	public static String formateDateAAAAMMJJhhmmssSSS(final Date date) {
		return formateDate(date, DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS);
	}

	/**
	 * Formate une date au format String passé en paramètre.
	 *
	 * @param date
	 * @param dateFormat
	 * @return String
	 */
	private static String formateDate(final Date date, final String dateFormat) {
		String dateRetour = null;

		if (date != null) {
			final SimpleDateFormat format = new SimpleDateFormat(dateFormat, DEFAULT_LOCALE);
			format.setTimeZone(DEFAULT_TIME_ZONE);
			dateRetour = format.format(date);
		}

		return dateRetour;
	}

	/**
	 * Création d'une date
	 *
	 * @param date
	 *            date sous forme de chaîne de caractères
	 *
	 * @return date en objet {@link Date}
	 */
	public static Date parseeDateJJMMAAAA(final String date) {
		return parseDate(date, DATE_FORMAT_DD_MM_YYYY);
	}

	/**
	 * Création d'une date
	 *
	 * @param date
	 *            date sous forme de chaîne de caractères
	 *
	 * @return date en objet {@link Date}
	 */
	public static Date parseeDateJJMMAAAAhhmmss(final String date) {
		return parseDate(date, DATE_FORMAT_DD_MM_YYYY_HH_MM_SS);
	}

	/**
	 * Création d'une date
	 *
	 * @param date
	 *            date sous forme de chaîne de caractères
	 *
	 * @return date en objet {@link Date}
	 */
	public static Date parseeDateJJMMAAAAhhmmssSSS(final String date) {
		return parseDate(date, DATE_FORMAT_DD_MM_YYYY_HH_MM_SS_SSS);
	}

	/**
	 * 2014-11-18 14:06:16,045
	 *
	 * @param date
	 * @return
	 */
	public static Date parseeDateAAAAMMJJhhmmssSSS(final String date) {
		return parseDate(date, DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS);
	}

	/**
	 * Création d'une date
	 *
	 * @param date
	 *            date sous forme de chaîne de caractères
	 *
	 * @return date en objet {@link Date}
	 */
	public static Date parseeDateAAAAMMJJhhmmss(final String date) {
		return parseDate(date, DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z);
	}

	/**
	 * Création d'une date
	 *
	 * @param date
	 *            date sous forme de chaîne de caractères
	 *
	 * @return date en objet {@link Date}
	 */
	public static Date parseeDateAAAAMMJJhhmmsss(final String date) {
		return parseDate(date, DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SSSZ);
	}

	/**
	 * Parse une chaine en une date suivant un format.
	 *
	 * @param date
	 *            date sous forme de chaine.
	 * @param dateFormat
	 *            format de date.
	 * @return date sous forme de date.
	 */
	private static Date parseDate(final String date, final String dateFormat) {
		Date dateRetour = null;

		if (null != date) {
			final SimpleDateFormat format = new SimpleDateFormat(dateFormat, DEFAULT_LOCALE);
			format.setTimeZone(DEFAULT_TIME_ZONE);
			try {
				dateRetour = format.parse(date);
			} catch (final ParseException e) {
				// LOG.error("Erreur en parsant la chaine '{}' : {}", date, e);
				final IllegalArgumentException iae = new IllegalArgumentException("Date : " + date + " non correcte");
				iae.initCause(e);
				throw iae;
			}
		}

		return dateRetour;
	}

	/**
	 * Compare deux dates en se basant uniquement sur leur années, mois et
	 * jours.
	 *
	 * @param d1
	 *            première date à comparer.
	 * @param d2
	 *            première date à comparer.
	 * @return -1, 0 ou 1 si d1 est antérieure, égale ou postérieure à d2.
	 */
	public static int compareDates(final Date d1, final Date d2) {
		return org.apache.commons.lang.time.DateUtils.truncatedCompareTo(d1, d2, Calendar.DAY_OF_MONTH);
	}

	/**
	 * Compare deux dates en se basant uniquement sur leur années, mois et
	 * jours.
	 *
	 * @param d1
	 *            première date à comparer.
	 * @param d2
	 *            première date à comparer.
	 * @return -1, 0 ou 1 si d1 est antérieure, égale ou postérieure à d2.
	 */
	public static int compareMilliSecondes(final Date d1, final Date d2) {
		return org.apache.commons.lang.time.DateUtils.truncatedCompareTo(d1, d2, Calendar.MILLISECOND);
	}

	/**
	 * Convertion {@link XMLGregorianCalendar} vers {@link Date}
	 *
	 * @param xmlgc
	 * @return
	 */
	public static Date convertXmlGregoriancalendarToDate(final XMLGregorianCalendar xmlgc) {
		Date dateRetour = null;
		if (xmlgc != null) {
			dateRetour = xmlgc.toGregorianCalendar().getTime();
		}

		return dateRetour;
	}

	/**
	 * Convertion {@link Date} vers {@link XMLGregorianCalendar}
	 *
	 * @param date
	 * @return
	 * @throws DatatypeConfigurationException
	 */
	public static XMLGregorianCalendar convertDateToXmlGregorianCalendar(final Date date) {
		try {
			XMLGregorianCalendar dateRetour = null;

			if (date != null) {
				final Calendar calendar = DateUtils.getCalendarInstance();
				calendar.setTime(date);
				final XMLGregorianCalendar xmlgc = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar((GregorianCalendar) calendar);
				dateRetour = xmlgc.normalize();
			}

			return dateRetour;
		} catch (final DatatypeConfigurationException e) {
			throw new TechnicalException(e);
		}
	}

	/**
	 * @return un {@link XMLGregorianCalendar} à la date courante
	 * @throws DatatypeConfigurationException
	 */
	public static XMLGregorianCalendar getXmlGregorianCalendar() {
		try {
			final Calendar calendar = DateUtils.getCalendarInstance();
			final XMLGregorianCalendar xmlgc = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar((GregorianCalendar) calendar);
			return xmlgc.normalize();
		} catch (final DatatypeConfigurationException e) {
			throw new TechnicalException(e);
		}
	}

	/**
	 * Crée une nouvelle date en ajoutant un nombre de jours à la date
	 *
	 * @param date
	 *            la date à modifier
	 * @param nbJours
	 *            le nombre de jours à ajouter
	 * @return Date la date obtenue
	 */
	public static Date addDays(final Date date, final int nbJours) {
		final Calendar calendar = DateUtils.getCalendarInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, nbJours);
		return calendar.getTime();
	}

	/**
	 * Méthode permettant de créer une nouvelle date en ajoutant les champs
	 * correspondant à la date
	 *
	 * @param year
	 *            l'année
	 * @param month
	 *            le mois (1-12)
	 * @param day
	 *            le jour du mois (1-31)
	 * @param hour
	 *            l'heure de la journée (0-23)
	 * @return Date la date obtenue
	 */
	public static Date computeDate(final int year, final int month, final int day, final int hour) {
		final Calendar calendar = DateUtils.getCalendarInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1); // La valeur du mois est entre
													// 0 (JANVIER) et 11
													// (DECEMBRE)
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Clone proprement une Date.
	 *
	 * @param dateACloner
	 *            date à cloner
	 * @return date clonée.
	 */
	public static Date clonerDate(final Date dateACloner) {
		if (null == dateACloner) {
			return null;
		} else {
			return Date.class.cast(dateACloner.clone());
		}
	}

	/**
	 * Méthode permettant de comparer 2 heures
	 *
	 * @param time1
	 *            heure de la date 1
	 * @param time2
	 *            heure de la date 2
	 * @return -1, 0 ou 1 si time1 est antérieure, égale ou postérieure à time2.
	 */
	public static int compareTime(final long time1, final long time2) {
		int result = 2;
		final long time = time1 - time2;
		if (time == 0) {
			// heures identiques
			result = 0;
		} else if (time <= 0) {
			// time 1 est inferieur à time 2
			result = -1;
		} else {
			// time 1 est superieur à time 2
			result = 1;
		}
		return result;
	}

	/**
	 * Convertit un string en Duration
	 *
	 * @param delayString
	 *            le string à convertir
	 * @return Duration
	 */
	public static Duration convertStringToDuration(final String delayString) {
		try {
			return DatatypeFactory.newInstance().newDuration(delayString);
		} catch (DatatypeConfigurationException | IllegalArgumentException | UnsupportedOperationException
				| NullPointerException e) {
			throw new TechnicalException(e);
		}
	}

	/**
	 * Crée une nouvelle date en ajoutant un nombre d'heure à la date
	 *
	 * @param date
	 *            Date à modifier
	 * @param nbHeures
	 *            nombre d'heure à ajouter
	 * @param nbMin
	 *            nombre de minute a rajouter
	 * @param nbSec
	 *            nombre de seconde a rajouter
	 * @return Date nouvelle date
	 */
	public static Date addTime(final Date date, final int nbHeures, final int nbMin, final int nbSec) {
		final Calendar calendar = DateUtils.getCalendarInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, nbHeures);
		calendar.add(Calendar.MINUTE, nbMin);
		calendar.add(Calendar.SECOND, nbSec);
		return calendar.getTime();
	}

	/**
	 * Calcule minuit de la date en cours
	 **/
	public static java.sql.Date getNowSQL() {
		final Date date = getCalendarInstance().getTime();
		return new java.sql.Date(date.getTime());
	}

	/**
	 * Calcule minuit de la date en cours
	 **/
	public static Date midnight(final Date date) {
		final Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Calcule le nombre d'heure entre les deux dates
	 *
	 * @param startDate
	 * @param endDate
	 * @return la différence d'heure entre les deux dates
	 */
	public static int hoursBetween(final Date startDate, final Date endDate) {

		final DateTime startDateX = new DateTime(startDate.getTime());
		final DateTime endDateX = new DateTime(endDate.getTime());

		final Hours hours = Hours.hoursBetween(startDateX, endDateX);

		return hours.getHours();

	}

	/**
	 * Cette méthode peut être utilisée dans une boucle afin de garder des
	 * dates-bornes. Les bornes <code>minDate</code> et <code>maxDate</code>
	 * sont manipulées et modifiées par valeur ({@link Date#setTime(long)}).
	 * <code>testDate</code> n'est pas du tout modifié : accès en lecture
	 * seulement.
	 *
	 * @param testDate
	 *            the "current iteration" date to test, in most cases it might
	 *            be inside the boundaries. If not, the boundaries are updated
	 * @param minDate
	 *            the low boundary : any <code>testDate</code> before this date
	 *            should rewrite it. Also rewrite if timestamp is 0.
	 * @param maxDate
	 *            the high boundary : any <code>testDate</code> after this date
	 *            should rewrite it. Also rewrite if timestamp is 0.
	 */
	public static void compareDatesBoundaries(final Date testDate, final Date minDate, final Date maxDate) {
		if (minDate != null) {
			if (minDate.getTime() == 0L || testDate.before(minDate)) {
				minDate.setTime(testDate.getTime());
			}
		}
		if (maxDate != null) {
			if (testDate.after(maxDate)) {
				maxDate.setTime(testDate.getTime());
			}
		}
	}

	/**
	 * Cette méthode vérifie si le format d'une date est valide
	 *
	 * @param date
	 * @return true : si le format est valide, false : si non
	 */
	public static boolean formatDateValide(final String dateString) {
		final String pattern = "dd/MM/yyyy HH:mm:ss";
		boolean valid = true;
		try {
			final DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
			formatter.parseDateTime(dateString);
		} catch (final Exception e) {
			valid = false;
		}

		return valid;
	}
}
