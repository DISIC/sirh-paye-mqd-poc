package com.sirh.mqd.reporting.webapp.constantes;

import java.io.File;

/**
 * Classe de constante pour la création du Menu
 *
 * @author alexandre
 */
public final class MenuConstantes {

	/**
	 * Chemin vers le fichier de configuration XML du menu
	 */
	public static final String XML_FILE = File.separator + "menu.xml";

	/**
	 * Attribut ID de l'accueil dans le menu
	 */
	public static final String ACCUEIL_ATTR_ID = "1";

	/**
	 * Elément menuItem du fichier de configuration XML du menu
	 */
	public static final String XML_MENU = "menuItem";

	/**
	 * Elément subMenu du fichier de configuration XML du menu
	 */
	public static final String XML_SUBMENU = "subMenu";

	/**
	 * Attribut URL d'un élément XML du fichier de configuration du menu
	 */
	public static final String ATTR_URL = "url";

	/**
	 * Attribut KEY d'un élément XML du fichier de configuration du menu
	 */
	public static final String ATTR_KEY = "key";

	/**
	 * Attribut ID d'un élément XML du fichier de configuration du menu
	 */
	public static final String ATTR_ID = "id";

	/**
	 * Attribut ACCESS d'un élément XML du fichier de configuration du menu
	 */
	public static final String ATTR_ACCESS = "access";

	/**
	 * Préfix des ID des éléments du menu (Submenu et MenuItem)
	 */
	public static final String PREFIX_ID = "menu_";

	/**
	 * Nom de l'attribut à chercher dans la requête pour changement de menu
	 */
	public static final String ARIANE_ATTR_ID = "menuId";

	/**
	 * Non constructeur.
	 *
	 * @throws InstantiationException
	 *             si tentative d'appel de ce constructeur.
	 */
	public MenuConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + MenuConstantes.class.getName());
	}
}
