package com.sirh.mqd.reporting.webapp.constantes;

/**
 * Classe de constante pour le module Core
 *
 * @author alexandre
 */
public final class ContextConstantes {

	/**
	 * Nom du service lié à la gestion des resources labels
	 */
	public static final String LABEL = "label";

	/**
	 * Nom du service lié à la gestion des resources titles
	 */
	public static final String TITRE = "titre";

	/**
	 * Nom du service lié à la gestion des resources messages
	 */
	public static final String MESSAGE = "message";

	/**
	 * Nom du service lié au chargement des messages
	 */
	public static final String MESSAGE_SOURCE = "messageSource";

	/**
	 * Nom du service lié au chargement des messages JSF
	 */
	public static final String JSF_UTILS = "jsfUtils";

	/**
	 * Nom du service de gestion des informations d'authentification
	 */
	public static final String LOGIN_UTILS = "loginUtils";

	/**
	 * Valeur de retour pour gérer la redirection en cas de succès
	 */
	public static final String REDIRECT_SUCCESS = "success";

	/**
	 * Valeur de retour pour gérer la redirection en cas d'erreur
	 */
	public static final String REDIRECT_ERROR = "error";

	/**
	 * Header à passer dans les WS KeyStone
	 */
	public static final String HEADER_KEYSTONE_TOKEN_ADMIN = "X-Auth-Token";

	/**
	 * Service d'authentification
	 */
	public static final String KEYSTONE_AUTH_PROVIDER = "authenticationProvider";

	/**
	 * Nom du Manager d'Authentification de Spring
	 */
	public static final String AUTHENTIFICATION_MANAGER = "authenticationManager";

	/**
	 * X Correliation id à diffuser pour les appels de WS
	 */
	public static final String HEADER_CORRELATION_ID = "X-CORRELATIONID";

	/**
	 * Non constructeur.
	 *
	 * @throws InstantiationException
	 *             si tentative d'appel de ce constructeur.
	 */
	public ContextConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + ContextConstantes.class.getName());
	}
}
