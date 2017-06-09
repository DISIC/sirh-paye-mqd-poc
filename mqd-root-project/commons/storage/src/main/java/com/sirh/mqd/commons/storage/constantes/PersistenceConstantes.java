package com.sirh.mqd.commons.storage.constantes;

/**
 * Liste des constantes utilisées dans la persistence
 *
 * @author alexandre
 */
public final class PersistenceConstantes {

	/**
	 * Nom du template SPRING DATA de connexion à MongoDB
	 */
	public static final String MONGO_TEMPLATE = "mongoTemplate";

	/**
	 * Nom du DAO pour les commentaires
	 */
	public static final String COMMENTAIRE_DAO = "commentaireDAO";

	/**
	 * Nom du BC pour les commentaires
	 */
	public static final String COMMENTAIRE_BC = "commentaireBC";

	/**
	 * Nom du DAO pour la gestion des dossiers et anomalies
	 */
	public static final String DOSSIER_DAO = "dossierDAO";

	/**
	 * Nom du BC pour la gestion des dossiers et anomalies
	 */
	public static final String DOSSIER_BC = "dossierBC";

	/**
	 * Nom du DAO pour la gestion des accès utilisateurs
	 */
	public static final String USER_DAO = "userDAO";

	/**
	 * Nom du BC pour la gestion des accès utilisateurs
	 */
	public static final String USER_BC = "userBC";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private PersistenceConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + PersistenceConstantes.class.getName());
	}
}
