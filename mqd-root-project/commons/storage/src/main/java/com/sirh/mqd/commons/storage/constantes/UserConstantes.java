package com.sirh.mqd.commons.storage.constantes;

/**
 * Liste des constantes utilisées dans la persistence
 *
 * @author alexandre
 */
public final class UserConstantes {

	public static final String COLLECTION_NAME = "utilisateurs";

	public static final String COLONNE_USERNAME = "_id";

	public static final String COLONNE_PASSWORD = "password";

	public static final String COLONNE_DATE_AUTHENTIFICATION = "last_connection";

	public static final String COLONNE_ROLES = "roles";

	public static final String COLONNE_PAY_LOT = "pay_lot";

	public static final String COLONNE_CORPS_CODE = "corps_code";

	public static final String COLONNE_AFFECTATION_CODE = "affectation_code";

	public static final String COLONNE_ACCOUNT_ENABLED = "account_enabled";

	public static final String COLONNE_ACCOUNT_NON_EXPIRED = "account_non_expired";

	public static final String COLONNE_ACCOUNT_NON_LOCKED = "account_non_locked";

	public static final String COLONNE_CREDENTIALS_NON_EXPIRED = "credentials_non_expired";

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
