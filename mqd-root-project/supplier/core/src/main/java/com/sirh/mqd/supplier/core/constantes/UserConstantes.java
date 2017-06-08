package com.sirh.mqd.supplier.core.constantes;

/**
 * Constantes propres à la gestion des utilisateurs.
 *
 * @author alexandre
 */
public final class UserConstantes {

	public static final String CSV_FILE_USER = "Utilisateurs.csv";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private UserConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + UserConstantes.class.getName());
	}
}
