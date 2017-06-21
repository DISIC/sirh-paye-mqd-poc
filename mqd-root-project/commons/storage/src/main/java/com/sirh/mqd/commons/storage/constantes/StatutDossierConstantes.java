package com.sirh.mqd.commons.storage.constantes;

/**
 * Liste des constantes utilisées dans la persistence
 *
 * @author khalil
 */
public final class StatutDossierConstantes {

	public static final String COLLECTION_NAME = "statut_dossier";

	public static final String COLONNE_ID = "_id";

	public static final String COLONNE_DISPONIBILITE = "disponibilite";

	public static final String COLONNE_AFFECTATION = "affectation";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private StatutDossierConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + StatutDossierConstantes.class.getName());
	}
}
