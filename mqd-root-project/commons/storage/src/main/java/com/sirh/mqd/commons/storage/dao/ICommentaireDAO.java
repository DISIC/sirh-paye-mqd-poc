package com.sirh.mqd.commons.storage.dao;

import java.util.List;

import com.sirh.mqd.commons.storage.entity.CommentaireEntity;

/**
 * @see doc.story.ref307
 *
 *      Interface fournissant les méthodes d'insert et
 *      de select de la table des commentaires
 *
 * @author khalil
 */

public interface ICommentaireDAO {

	/**
	 * Méthode permettant de lister les commentaires d'un dossier
	 *
	 * @param payLot
	 *            champ qui permet avec renoiRHMatricule, d'identifier un
	 *            dossier
	 * @param renoiRHMatricule
	 * 			  champ qui permet avec payLot, d'identifier un
	 *            dossier
	 * @return List<CommentaireDTO> correspondant aux commentaire du dossier
	 */
	List<CommentaireEntity> selectCommentairesDossier(String payLot, String renoiRHMatricule);

	/**
	 * Méthode permettant d'ajouter un commentaire à la liste de commantaires
	 * d'un dossier
	 *
	 * @param commentaire
	 * 			  commentaire à insérer
	 */
	void insertCommentaire(CommentaireEntity commentaire);
}
