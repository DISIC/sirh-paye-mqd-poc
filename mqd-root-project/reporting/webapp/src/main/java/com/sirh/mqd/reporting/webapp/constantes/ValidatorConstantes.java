package com.sirh.mqd.reporting.webapp.constantes;

/**
 * Constantes génériques aux vues du projet.
 *
 * @author alexandre
 */
public final class ValidatorConstantes {

	public static final String REQUIRED_VALIDATOR = "requiredValidator";

	public static final String USERNAME_VALIDATOR = "usernameValidator";

	public static final String PASSWORD_VALIDATOR = "passwordValidator";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private ValidatorConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + ValidatorConstantes.class.getName());
	}
}
