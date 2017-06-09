package com.sirh.mqd.commons.storage.constantes;

/**
 * Liste des constantes utilisées dans la persistence
 *
 * @author alexandre
 */
public final class CommentaireConstantes {

	public static final String COLLECTION_NAME = "commentaires";

	public static final String COLONNE_ID = "_id";

	public static final String COLONNE_DATE_CREATION = "date_creation";

	public static final String COLONNE_GESTIONNAIRE = "gestionnaire";

	public static final String COLONNE_DESCRIPTION = "description";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private CommentaireConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + CommentaireConstantes.class.getName());
	}
}
