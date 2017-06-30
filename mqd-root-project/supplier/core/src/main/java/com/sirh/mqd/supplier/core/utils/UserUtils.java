package com.sirh.mqd.supplier.core.utils;

import java.util.List;
import java.util.regex.Pattern;

import com.sirh.mqd.commons.exchanges.enums.RoleEnum;

/**
 * Classe utilitaire permettant de gérer les données des utilisateurs reçues en
 * entrée.
 *
 * @author alexandre
 */
public final class UserUtils {

	private static final String CSV_FILE_USER_SEPARATOR = Pattern.quote("|");

	/**
	 * Non constructeur.
	 *
	 * @throws InstantiationException
	 *             si tentative d'appel de ce constructeur.
	 */
	public UserUtils() throws InstantiationException {
		throw new InstantiationException("Création non autorisée d'une instance de : " + UserUtils.class.getName());
	}

	/**
	 * Méthode permettant de diviser une chaîne de caractères issue d'un fichier
	 * Utilisateur dont les données sont séparées par des virgules et dont les
	 * données sont entourées de guillements
	 *
	 * @param line
	 *            la ligne à découper
	 * @return {@link Array} tableau des données
	 */
	public static String[] splitUtilisateursData(final String line) {
		return line.split(CSV_FILE_USER_SEPARATOR, -1);
	}

	/**
	 * Méthode permettant de construire la liste des roles associés à
	 * l'utilisateur. Par défaut, tout utilisateur aura le role USER.
	 *
	 * @param role
	 * @return {@link List} des roles de l'utilisateur
	 */
	public static List<RoleEnum> createListeRoles(final String role) {
		return RoleEnum.enumOf(role);
	}
}
