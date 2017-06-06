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
	 * Nom du DAO pour la synchronisation des referentiels
	 */
	public static final String SYNCHRO_REFERENTIELS_DAO = "synchroReferentielsDAO";

	/**
	 * Nom du BC pour la synchronisation des referentiels
	 */
	public static final String SYNCHRO_REFERENTIELS_TAMPON_BC = "synchroReferentielsBC";

	/**
<<<<<<< HEAD
	 * Nom du DAO pour les commentaires
	 */
	public static final String COMMENTAIRE_DAO = "commentaireDAO";

	/**
	 * Nom du BC pour les commentaires
	 */
	public static final String COMMENTAIRE_BC = "commentaireBC";


	

	/**
=======
>>>>>>> branch 'master' of https://github.com/DISIC/sirh-paye-mqd-poc.git
	 * Nom du DAO pour la gestion des dossiers et anomalies
	 */
	public static final String DOSSIER_DAO = "dossierDAO";

	/**
	 * Nom du BC pour la gestion des dossiers et anomalies
	 */
	public static final String DOSSIER_BC = "dossierBC";

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
