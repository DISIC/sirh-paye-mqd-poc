package com.sirh.mqd.supplier.core.utils;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.enums.AnomalieEtatEnum;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.utils.constante.Constantes;
import com.sirh.mqd.supplier.api.FunctionTwoParameters;

/**
 * Classe utilitaire permettant de gérer la détection des anomalies entre les
 * données.
 *
 * @author alexandre
 */
public final class AnomalieDetectionMSOUtils {

	private static final String CSV_FILE_PAY_SEPARATOR = "\",\"";

	private static final String CSV_FILE_MSO_SEPARATOR = "\",\"";

	private static final Pattern DATA_NON_ALPHABETIC_PATTERN = Pattern.compile("[^a-zA-Z]+");
	// OU spécifier les caractères à escaper "[[](){},.;-'!?<>%]"

	private static final Pattern DATA_BETWEEN_BRACKETS_PATTERN = Pattern.compile("\\(.*?\\)");

	private static final Function<String, String> FUNCTION_REPLACE_SPECIAL_CHAR = (data) -> data != null
			? StringUtils.normalizeSpace(DATA_NON_ALPHABETIC_PATTERN.matcher(data).replaceAll(Constantes.SPACE)) : null;

	private static final Function<String, String> FUNCTION_FORMAT_DATA_BETWEEN_BRACKETS = (data) -> {
		String payGrade = data;
		if (data != null) {
			final Matcher matcher = DATA_BETWEEN_BRACKETS_PATTERN.matcher(data);
			if (matcher.find()) {
				payGrade = matcher.group().substring(1, matcher.group().length() - 1);
			}
		}
		return payGrade;
	};

	private static final Function<String, String> FUNCTION_TRUNCATE_TO_15_CHAR = (data) -> data != null
			? 15 > data.length() ? data : data.substring(0, 15) : null;

	private static final Function<String, String> FUNCTION_TRUNCATE_TO_20_CHAR = (data) -> data != null
			? 20 > data.length() ? data : data.substring(0, 20) : null;

	private static final FunctionTwoParameters<String, String, Boolean> FUNCTION_CASE_INSENSITIVE_COMPARISON = (data1,
			data2) -> data1 != null && data2 != null && !data1.equalsIgnoreCase(data2);

	private static final FunctionTwoParameters<String, String, Boolean> FUNCTION_CASE_SENSITIVE_COMPARISON = (data1,
			data2) -> data1 != null && data2 != null && !data1.equals(data2);

	private static final Predicate<ComparaisonDTO> FILTER_CASE_SENSITIVE_DATA_COMPARISON = (
			anomalie) -> (FUNCTION_CASE_SENSITIVE_COMPARISON.apply(anomalie.getDonnees().getDonneePAY(),
					anomalie.getDonnees().getDonneeGA()));

	private static final Predicate<ComparaisonDTO> FILTER_CASE_INSENSITIVE_DATA_COMPARISON = (
			anomalie) -> (FUNCTION_CASE_INSENSITIVE_COMPARISON.apply(anomalie.getDonnees().getDonneePAY(),
					anomalie.getDonnees().getDonneeGA()));

	private static final Predicate<ComparaisonDTO> FILTER_CASE_SENSITIVE_DATA_COMPARISON_GA_BETWEEN_BRACKETS = (
			anomalie) -> (FUNCTION_CASE_SENSITIVE_COMPARISON.apply(anomalie.getDonnees().getDonneePAY(),
					FUNCTION_FORMAT_DATA_BETWEEN_BRACKETS.apply(anomalie.getDonnees().getDonneeGA())));

	private static final Predicate<ComparaisonDTO> FILTER_CASE_INSENSITIVE_DATA_COMPARISON_PAY_15_CHAR_LIMITATION = (
			anomalie) -> (FUNCTION_CASE_INSENSITIVE_COMPARISON.apply(
					FUNCTION_REPLACE_SPECIAL_CHAR.apply(anomalie.getDonnees().getDonneePAY()),
					FUNCTION_TRUNCATE_TO_15_CHAR
							.apply(FUNCTION_REPLACE_SPECIAL_CHAR.apply(anomalie.getDonnees().getDonneeGA()))));

	private static final Predicate<ComparaisonDTO> FILTER_CASE_INSENSITIVE_DATA_COMPARISON_PAY_20_CHAR_LIMITATION = (
			anomalie) -> (FUNCTION_CASE_INSENSITIVE_COMPARISON.apply(
					FUNCTION_REPLACE_SPECIAL_CHAR.apply(anomalie.getDonnees().getDonneePAY()),
					FUNCTION_TRUNCATE_TO_20_CHAR
							.apply(FUNCTION_REPLACE_SPECIAL_CHAR.apply(anomalie.getDonnees().getDonneeGA()))));

	private static final Predicate<ComparaisonDTO> FILTER_CASE_INSENSITIVE_DATA_TYPES = (
			anomalie) -> (AnomalieTypeEnum.ETAT_CIVIL_NOM.equals(anomalie.getType())
					|| AnomalieTypeEnum.ETAT_CIVIL_PRENOM.equals(anomalie.getType())
					|| AnomalieTypeEnum.ADRESSE_NOM_VOIE.equals(anomalie.getType()));

	private static final Predicate<ComparaisonDTO> FILTER_CASE_CARRIERE_GRADE_DATA_TYPES = (
			anomalie) -> (AnomalieTypeEnum.CARRIERE_GRADE.equals(anomalie.getType()));

	private static final Predicate<ComparaisonDTO> FILTER_CASE_TRUNCATE_20_CHAR_DATA_TYPES = (
			anomalie) -> (AnomalieTypeEnum.ETAT_CIVIL_NOM.equals(anomalie.getType()));

	private static final Predicate<ComparaisonDTO> FILTER_CASE_TRUNCATE_15_CHAR_DATA_TYPES = (
			anomalie) -> (AnomalieTypeEnum.ETAT_CIVIL_PRENOM.equals(anomalie.getType()));

	private static final Predicate<ComparaisonDTO> FILTER_UNSUPPORTED_DATA_TYPES = (
			anomalie) -> (AnomalieTypeEnum.COORDONNEE_BANCAIRE_IBAN.equals(anomalie.getType())
					|| AnomalieTypeEnum.COORDONNEE_BANCAIRE_MODE_PAIEMENT.equals(anomalie.getType())
					|| AnomalieTypeEnum.COORDONNEE_BANCAIRE_BIC_SWIFT.equals(anomalie.getType()));

	/**
	 * Non constructeur.
	 *
	 * @throws InstantiationException
	 *             si tentative d'appel de ce constructeur.
	 */
	public AnomalieDetectionMSOUtils() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + AnomalieDetectionMSOUtils.class.getName());
	}

	/**
	 * Méthode permettant de détecter des anomalies et de définir un état en
	 * anomalie.
	 *
	 * @param comparaisons
	 *            liste des données en entrée à comparer pour détecter des
	 *            anomalies
	 */
	public static void verifierPresenceAnomalie(final List<ComparaisonDTO> comparaisons) {
		comparaisons.stream().filter(FILTER_UNSUPPORTED_DATA_TYPES.negate())
				.filter((FILTER_CASE_INSENSITIVE_DATA_TYPES.negate().and(FILTER_CASE_CARRIERE_GRADE_DATA_TYPES.negate())
						.and(FILTER_CASE_SENSITIVE_DATA_COMPARISON)).or(
								FILTER_CASE_INSENSITIVE_DATA_TYPES.negate().and(FILTER_CASE_CARRIERE_GRADE_DATA_TYPES)
										.and(FILTER_CASE_SENSITIVE_DATA_COMPARISON_GA_BETWEEN_BRACKETS))
								.or(FILTER_CASE_INSENSITIVE_DATA_TYPES
										.and(FILTER_CASE_TRUNCATE_20_CHAR_DATA_TYPES.negate())
										.and(FILTER_CASE_TRUNCATE_15_CHAR_DATA_TYPES.negate())
										.and(FILTER_CASE_INSENSITIVE_DATA_COMPARISON))
								.or(FILTER_CASE_INSENSITIVE_DATA_TYPES.and(FILTER_CASE_TRUNCATE_15_CHAR_DATA_TYPES)
										.and(FILTER_CASE_INSENSITIVE_DATA_COMPARISON_PAY_15_CHAR_LIMITATION))
								.or(FILTER_CASE_INSENSITIVE_DATA_TYPES.and(FILTER_CASE_TRUNCATE_20_CHAR_DATA_TYPES)
										.and(FILTER_CASE_INSENSITIVE_DATA_COMPARISON_PAY_20_CHAR_LIMITATION)))
				.forEach((anomalie) -> {
					if (anomalie.getEtatCorrection() != null
							&& AnomalieEtatEnum.CORRECTION_EFFECTUEE.equals(anomalie.getEtatCorrection())) {
						anomalie.setAnomalieReouverte(Boolean.TRUE);
					} else if (anomalie.getEtatCorrection() == null) {
						anomalie.setEtatCorrection(AnomalieEtatEnum.A_TRAITER);
					}
					anomalie.setAnomalieDonnees(Boolean.TRUE);
					anomalie.setDateCloture(null);
				});
	}

	/**
	 * Méthode permettant de diviser une chaîne de caractères issue d'un fichier
	 * PAY fourni par MSO dont les données sont séparées par des virgules et
	 * dont les données sont entourées de guillements
	 *
	 * @param line
	 *            la ligne à découper
	 * @return {@link Array} tableau des données
	 */
	public static String[] splitPAYData(final String line) {
		final String[] lineArray = line.split(CSV_FILE_PAY_SEPARATOR, -1);
		lineArray[0] = lineArray[0].replaceAll(Constantes.QUOTE, StringUtils.EMPTY);
		final int lastIndex = lineArray.length - 1;
		lineArray[lastIndex] = lineArray[lastIndex].replaceAll(Constantes.QUOTE, StringUtils.EMPTY);
		return lineArray;
	}

	/**
	 * Méthode permettant de diviser une chaîne de caractères issue d'un fichier
	 * MSO dont les données sont séparées par des virgules et dont les données
	 * sont entourées de guillements
	 *
	 * @param line
	 *            la ligne à découper
	 * @return {@link Array} tableau des données
	 */
	public static String[] splitMSOData(final String line) {
		final String[] lineArray = line.split(CSV_FILE_MSO_SEPARATOR, -1);
		lineArray[0] = lineArray[0].replaceAll(Constantes.QUOTE, StringUtils.EMPTY);
		final int lastIndex = lineArray.length - 1;
		lineArray[lastIndex] = lineArray[lastIndex].replaceAll(Constantes.QUOTE, StringUtils.EMPTY);
		return lineArray;
	}
}
