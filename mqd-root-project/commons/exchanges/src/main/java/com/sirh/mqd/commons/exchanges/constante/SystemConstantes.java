package com.sirh.mqd.commons.exchanges.constante;

/**
 * Classe des constantes propres aux noms des paramètres sytèmes présents en
 * Base de Données
 *
 * @author alexandre
 */
public final class SystemConstantes {

	/**
	 * Cron gérant la fréquence d'analyse du répertoire de dépôt des fichiers
	 * MSO.
	 */
	public static final String CRON_MSO = "CRON_MSO";

	public static final String COULEUR_SEUILS_ALERTE = "COULEUR_SEUILS_ALERTE";

	public static final String COULEUR_SEUILS_ANOMALIE = "COULEUR_SEUILS_ANOMALIE";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private SystemConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + SystemConstantes.class.getName());
	}
}
