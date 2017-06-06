package com.sirh.mqd.commons.storage.constantes;

/**
 * Liste des constantes utilisées dans commentaire
 *
 * @author khalil
 */
public final class CommentaireConstantes {

	/**
	 * Nom du document Date de la collection Commentaire
	 */
	public static final String DATE_CREATION = "dateCreation";

	/**
	 * Nom du document PayLot de la collection Commentaire
	 */
	public static final String PAY_LOT = "payLot";

	/**
	 * Nom du document RenoiRHMatricule de la collection Commentaire
	 */
	public static final String MATRICULE = "renoiRHMatricule";

	/**
	 * Nom du document Utilisateur de la collection Commentaire
	 */
	public static final String GESTIONNAIRE = "utilisateur";

	/**
	 * Nom du document Commentaire de la collection Commentaire
	 */
	public static final String DESCRIPTION = "commentaire";


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
