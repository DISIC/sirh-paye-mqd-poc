package com.sirh.mqd.commons.storage.constantes;

/**
 * Liste des constantes utilisées dans la persistence
 *
 * @author khalil
 */
public final class ConfigConstantes {

	public static final String COLLECTION_NAME = "config";

	public static final String COLONNE_ID = "_id";

	public static final String COLONNE_VALEUR = "valeur";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private ConfigConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + ConfigConstantes.class.getName());
	}
}
