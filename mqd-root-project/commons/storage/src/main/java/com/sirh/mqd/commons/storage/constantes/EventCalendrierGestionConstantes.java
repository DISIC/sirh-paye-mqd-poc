package com.sirh.mqd.commons.storage.constantes;

/**
 * Liste des constantes utilisées dans la persistence
 *
 * @author khalil
 */
public final class EventCalendrierGestionConstantes {

	public static final String COLLECTION_NAME = "calendrier_gestion";

	public static final String COLONNE_ID = "_id";

	public static final String COLONNE_EVENEMENT = "evenement";

	public static final String COLONNE_TYPE = "type";

	public static final String COLONNE_DEBUT = "debut";

	public static final String COLONNE_ECHEANCE = "echeance";

	public static final String COLONNE_ACTEURS = "acteurs";

	public static final String COLONNE_CORPS = "corps";

	public static final String COLONNE_SERVICE = "service";

	public static final String COLONNE_COULEUR = "couleur";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private EventCalendrierGestionConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + EventCalendrierGestionConstantes.class.getName());
	}
}
