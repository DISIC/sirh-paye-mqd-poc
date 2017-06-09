package com.sirh.mqd.supplier.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sirh.mqd.commons.exchanges.enums.RoleEnum;
import com.sirh.mqd.commons.utils.constante.Constantes;

/**
 * Classe utilitaire permettant de gérer les données des utilisateurs reçues en
 * entrée.
 *
 * @author alexandre
 */
public final class UserUtils {

	private static final String CSV_FILE_USER_SEPARATOR = "\",\"";

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
		final String[] lineArray = line.split(CSV_FILE_USER_SEPARATOR);
		lineArray[0] = lineArray[0].replaceAll(Constantes.QUOTE, StringUtils.EMPTY);
		final int lastIndex = lineArray.length - 1;
		lineArray[lastIndex] = lineArray[lastIndex].replaceAll(Constantes.QUOTE, StringUtils.EMPTY);
		return lineArray;
	}

	/**
	 * Méthode permettant de construire la liste des roles associés à
	 * l'utilisateur. Par défaut, tout utilisateur aura le role USER.
	 *
	 * @param isAdmin
	 * @return {@link List} des roles de l'utilisateur
	 */
	public static List<RoleEnum> createListeRoles(final String isAdmin) {
		final List<RoleEnum> roles = new ArrayList<RoleEnum>();
		roles.add(RoleEnum.ROLE_USER);
		if (StringUtils.isNotBlank(isAdmin)) {
			roles.add(RoleEnum.ROLE_ADMIN);
		}
		return roles;
	}
}
