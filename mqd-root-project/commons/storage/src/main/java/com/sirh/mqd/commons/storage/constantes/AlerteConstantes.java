package com.sirh.mqd.commons.storage.constantes;

/**
 * Liste des constantes utilisées dans la persistence
 *
 * @author alexandre
 */
public final class AlerteConstantes {

	public static final String COLLECTION_NAME = "alertes";

	public static final String COLONNE_ID = "_id";

	public static final String COLONNE_TYPE_DONNEE = "type_donnee";

	public static final String COLONNE_VALEUR_DONNEE = "valeur_donnee";

	public static final String COLONNE_DATE_ECHEANCE = "date_echeance";

	public static final String COLONNE_DATE_CLOTURE = "date_cloture";

	public static final String COLONNE_DATE_MODIFICATION = "date_modification";

	public static final String COLONNE_ETAT = "etat";

	public static final String COLONNE_RESPONSABLE_LOGIN = "responsable_login";

	public static final String COLONNE_RESPONSABLE_NOM = "responsable_nom";

	public static final String COLONNE_RESPONSABLE_PRENOM = "responsable_prenom";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private AlerteConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + AlerteConstantes.class.getName());
	}
}
