package com.sirh.mqd.commons.exchanges.constante;

/**
 * Classe des constantes contenant les messages fixes des Erreurs
 *
 * @author alexandre
 */
public final class ErrorConstantes {

	/**
	 * Constante de préfixe des erreurs de type BAD_REQUEST
	 */
	public static final String BAD_REQUEST = "[BAD_REQUEST] : ";

	/**
	 * Constante de préfixe des erreurs de type INTERNAL_ERROR
	 */
	public static final String INTERNAL_ERROR = "[INTERNAL_ERROR] : ";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private ErrorConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + ErrorConstantes.class.getName());
	}
}
