package com.sirh.mqd.supplier.core.utils;

import java.util.regex.Pattern;

/**
 * Classe utilitaire permettant de gérer les données des calendriers de gestion.
 *
 * @author alexandre
 */
public final class CalendrierGestionUtils {

	private static final String CSV_FILE_DGAC_SEPARATOR = Pattern.quote("|");

	/**
	 * Non constructeur.
	 *
	 * @throws InstantiationException
	 *             si tentative d'appel de ce constructeur.
	 */
	public CalendrierGestionUtils() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + CalendrierGestionUtils.class.getName());
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
		return line.split(CSV_FILE_DGAC_SEPARATOR, -1);
	}
}
