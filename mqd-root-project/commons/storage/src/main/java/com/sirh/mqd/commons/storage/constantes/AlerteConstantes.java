package com.sirh.mqd.commons.storage.constantes;

/**
 * Liste des constantes utilisées dans la persistence
 *
 * @author alexandre
 */
public final class AlerteConstantes {

	public static final String COLLECTION_NAME = "alertes";

	public static final String COLONNE_ID = "_id";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private AlerteConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + AlerteConstantes.class.getName());
	}
}
