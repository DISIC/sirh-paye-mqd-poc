package com.sirh.mqd.commons.storage.constantes;

/**
 * Liste des constantes utilisées dans la persistence
 *
 * @author khalil
 */
public final class EventCalendrierConstantes {

	public static final String COLLECTION_NAME = "events_calendriers_gestion";

	public static final String COLONNE_ID = "_id";

	public static final String COLONNE_EVENEMENT = "evenement";

	public static final String COLONNE_TYPE = "type";

	public static final String COLONNE_DEBUT = "date_debut";

	public static final String COLONNE_ECHEANCE = "date_echeance";

	public static final String COLONNE_ACTEURS = "acteurs";

	public static final String COLONNE_CORPS = "corps";

	public static final String COLONNE_SERVICE = "service";

	public static final String COLONNE_COULEUR = "couleur";

	public static final String COLONNE_REFERENTIEL = "referentiel";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private EventCalendrierConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + EventCalendrierConstantes.class.getName());
	}
}
