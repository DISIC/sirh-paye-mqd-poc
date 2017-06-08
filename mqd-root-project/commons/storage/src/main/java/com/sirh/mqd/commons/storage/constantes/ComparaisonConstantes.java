package com.sirh.mqd.commons.storage.constantes;

/**
 * Liste des constantes utilisées dans la persistence
 *
 * @author alexandre
 */
public final class ComparaisonConstantes {

	public static final String COLLECTION_NAME = "comparaisons";

	public static final String COLONNE_ID = "_id";

	public static final String COLONNE_TYPE_DONNEE = "type_donnee";

	public static final String COLONNE_ANOMALIE_REOUVERTE = "anomalie_reouverte";

	public static final String COLONNE_ANOMALIE = "anomalie";

	public static final String COLONNE_ANOMALIE_MODE_OPERATOIRE = "correction_mode_operatoire";

	public static final String COLONNE_ANOMALIE_DATE_ECHEANCE = "correction_date_echeance";

	public static final String COLONNE_ANOMALIE_DATE_CLOTURE = "correction_date_cloture";

	public static final String COLONNE_ANOMALIE_ETAT = "correction_etat";

	public static final String COLONNE_DONNEES = "donnees";

	public static final String COLONNE_DONNEES_PAY = "pay";

	public static final String COLONNE_DONNEES_GA = "renoirh";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private ComparaisonConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + ComparaisonConstantes.class.getName());
	}
}
