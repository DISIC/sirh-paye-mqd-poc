package com.sirh.mqd.supplier.core.utils;

import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.utils.constante.Constantes;

/**
 * Classe utilitaire permettant de gérer la détection des anomalies entre les
 * données.
 *
 * @author alexandre
 */
public final class AnomalieDetectionUtils {

	private static final String CSV_FILE_PAY_SEPARATOR = "\",\"";

	private static final Predicate<ComparaisonDTO> FILTER_CASE_SENSITIVE_DATA_COMPARISON = (
			anomalie) -> (anomalie.getDonnees().getDonneePAY() != null && anomalie.getDonnees().getDonneeGA() != null
					&& !anomalie.getDonnees().getDonneePAY().equals(anomalie.getDonnees().getDonneeGA()));

	private static final Predicate<ComparaisonDTO> FILTER_CASE_INSENSITIVE_DATA_COMPARISON = (
			anomalie) -> (anomalie.getDonnees().getDonneePAY() != null && anomalie.getDonnees().getDonneeGA() != null
					&& !anomalie.getDonnees().getDonneePAY().equalsIgnoreCase(anomalie.getDonnees().getDonneeGA()));

	private static final Predicate<ComparaisonDTO> FILTER_CASE_INSENSITIVE_DATA_TYPES = (
			anomalie) -> (AnomalieTypeEnum.NOM.equals(anomalie.getType())
					|| AnomalieTypeEnum.PRENOM.equals(anomalie.getType()));

	private static final Predicate<ComparaisonDTO> FILTER_UNSUPPORTED_DATA_TYPES = (
			anomalie) -> (AnomalieTypeEnum.GRADE.equals(anomalie.getType())
					|| AnomalieTypeEnum.NIVEAU_ECHELON.equals(anomalie.getType()));

	/**
	 * Non constructeur.
	 *
	 * @throws InstantiationException
	 *             si tentative d'appel de ce constructeur.
	 */
	public AnomalieDetectionUtils() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + AnomalieDetectionUtils.class.getName());
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
				.filter((FILTER_CASE_INSENSITIVE_DATA_TYPES.negate().and(FILTER_CASE_SENSITIVE_DATA_COMPARISON))
						.or(FILTER_CASE_INSENSITIVE_DATA_TYPES.and(FILTER_CASE_INSENSITIVE_DATA_COMPARISON)))
				.forEach((anomalie) -> {
					anomalie.setAnomalieDonnees(true);
				});
	}

	/**
	 * Méthode permettant de diviser une chaîne de caractères issue d'un fichier
	 * PAY dont les données sont séparées par des virgules et dont les données
	 * sont entourées de guillements
	 *
	 * @param line
	 *            la ligne à découper
	 * @return {@link Array} tableau des données
	 */
	public static String[] splitPAYData(final String line) {
		final String[] lineArray = line.split(CSV_FILE_PAY_SEPARATOR);
		lineArray[0] = lineArray[0].replaceAll(Constantes.QUOTE, StringUtils.EMPTY);
		final int lastIndex = lineArray.length - 1;
		lineArray[lastIndex] = lineArray[lastIndex].replaceAll(Constantes.QUOTE, StringUtils.EMPTY);
		return lineArray;
	}
}
