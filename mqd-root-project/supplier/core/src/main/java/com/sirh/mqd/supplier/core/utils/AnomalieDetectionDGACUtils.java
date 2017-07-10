package com.sirh.mqd.supplier.core.utils;

import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.enums.AnomalieEtatEnum;
import com.sirh.mqd.supplier.api.FunctionTwoParameters;

/**
 * Classe utilitaire permettant de gérer la détection des anomalies entre les
 * données.
 *
 * @author alexandre
 */
public final class AnomalieDetectionDGACUtils {

	private static final String TXT_FILE_PAY_SEPARATOR = ";";

	private static final String TXT_FILE_DGAC_SEPARATOR = ";";

	private static final FunctionTwoParameters<String, String, Boolean> FUNCTION_CASE_SENSITIVE_COMPARISON = (data1,
			data2) -> data1 != null && data2 != null
					&& !StringUtils.normalizeSpace(data1).equals(StringUtils.normalizeSpace(data2));

	private static final Predicate<ComparaisonDTO> FILTER_CASE_SENSITIVE_DATA_COMPARISON = (
			anomalie) -> (FUNCTION_CASE_SENSITIVE_COMPARISON.apply(anomalie.getDonnees().getDonneePAY(),
					anomalie.getDonnees().getDonneeGA()));

	private static final Predicate<ComparaisonDTO> FILTER_CASE_INSENSITIVE_DATA_TYPES = (anomalie) -> (Boolean.FALSE);

	private static final Predicate<ComparaisonDTO> FILTER_UNSUPPORTED_DATA_TYPES = (anomalie) -> (Boolean.FALSE);

	/**
	 * Non constructeur.
	 *
	 * @throws InstantiationException
	 *             si tentative d'appel de ce constructeur.
	 */
	public AnomalieDetectionDGACUtils() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + AnomalieDetectionDGACUtils.class.getName());
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
				.filter((FILTER_CASE_INSENSITIVE_DATA_TYPES.negate().and(FILTER_CASE_SENSITIVE_DATA_COMPARISON)))
				.forEach((anomalie) -> {
					if (anomalie.getEtatCorrection() != null
							&& AnomalieEtatEnum.ETAT_ANOMALIE_3_CORRECTION_EFFECTUEE.equals(anomalie.getEtatCorrection())) {
						anomalie.setAnomalieReouverte(Boolean.TRUE);
					} else if (anomalie.getEtatCorrection() == null) {
						anomalie.setEtatCorrection(AnomalieEtatEnum.ETAT_ANOMALIE_1_A_TRAITER);
					}
					anomalie.setAnomalieDonnees(Boolean.TRUE);
					anomalie.setDateCloture(null);
				});
	}

	/**
	 * Méthode permettant de diviser une chaîne de caractères issue d'un fichier
	 * PAY fourni par la DGAC dont les données sont séparées par des
	 * point-virgules
	 *
	 * @param line
	 *            la ligne à découper
	 * @return {@link Array} tableau des données
	 */
	public static String[] splitPAYData(final String line) {
		return line.split(TXT_FILE_PAY_SEPARATOR, -1);
	}

	/**
	 * Méthode permettant de diviser une chaîne de caractères issue d'un fichier
	 * DGAC dont les données sont séparées par des point-virgules
	 *
	 * @param line
	 *            la ligne à découper
	 * @return {@link Array} tableau des données
	 */
	public static String[] splitDGACData(final String line) {
		return line.split(TXT_FILE_DGAC_SEPARATOR, -1);
	}
}
