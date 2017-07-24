package com.sirh.mqd.commons.exchanges.constante;

/**
 * Classe des constantes propres aux noms des couleurs de seuils d'alerte
 * présents en Base de Données
 *
 * @author alexandre
 */
public final class AlerteCouleurSeuilsConstantes {

	public static final String ROUGE = "rouge";

	public static final String ORANGE = "orange";

	public static final String JAUNE = "jaune";

	public static final String VERT = "vert";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private AlerteCouleurSeuilsConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + AlerteCouleurSeuilsConstantes.class.getName());
	}
}
