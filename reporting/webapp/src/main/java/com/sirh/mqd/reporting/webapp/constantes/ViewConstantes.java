package com.sirh.mqd.reporting.webapp.constantes;

/**
 * Constantes génériques aux vues du projet.
 *
 * @author alexandre
 */
public final class ViewConstantes {

	public static final String MENU_BEAN = "menuBean";

	public static final String DOSSIER_BEAN = "dossierBean";

	public static final String ANOMALIE_BEAN = "anomalieBean";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private ViewConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + ViewConstantes.class.getName());
	}
}
