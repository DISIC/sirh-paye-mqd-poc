package com.sirh.mqd.reporting.core.constantes;

/**
 * Constantes génériques au projet.
 *
 * @author alexandre
 */
public final class CoreConstantes {

	public static final String DOSSIER_SERVICE = "dossierService";

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
