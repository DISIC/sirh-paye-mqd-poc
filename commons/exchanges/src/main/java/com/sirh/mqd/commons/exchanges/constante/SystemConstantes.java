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
