package com.sirh.mqd.reporting.core.constantes;

/**
 * Constantes génériques au projet.
 *
 * @author alexandre
 */
public final class CoreConstantes {

	public static final String DOSSIER_SERVICE = "dossierService";

	public static final String COMMENTAIRE_SERVICE = "commentaireService";

	public static final String USER_SERVICE = "userService";

	public static final String STATUT_DOSSIER_SERVICE = "statutDossierService";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private CoreConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + CoreConstantes.class.getName());
	}
}
