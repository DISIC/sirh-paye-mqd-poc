package com.sirh.mqd.commons.storage.factory;

import com.sirh.mqd.commons.exchanges.dto.commentaire.CommentaireDTO;
import com.sirh.mqd.commons.storage.entity.CommentaireEntity;

/**
 * Factory de création des entités des commentaires.
 *
 * @author khalil
 */
public class CommentaireEntityFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private CommentaireEntityFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + CommentaireEntityFactory.class.getName());
	}

	public static CommentaireDTO createCommentaireDTO(final CommentaireEntity entity) {
		CommentaireDTO commentaire = null;
		if (entity != null) {
			commentaire = new CommentaireDTO();
			commentaire.setContenu(entity.getContenu());
			commentaire.setDateCreation(entity.getDateCreation());
			commentaire.setUtilisateur(entity.getUtilisateur());
			commentaire.setPayLot(entity.getPayLot());
			commentaire.setRenoiRHMatricule(entity.getRenoiRHMatricule());
		}
		return commentaire;
	}

	public static CommentaireEntity createCommentaireEntity(final CommentaireDTO commentaire) {
		CommentaireEntity entity = null;
		if (commentaire != null) {
			entity = new CommentaireEntity();
			entity.setContenu(commentaire.getContenu());
			entity.setDateCreation(commentaire.getDateCreation());
			entity.setUtilisateur(commentaire.getUtilisateur());
			entity.setPayLot(commentaire.getPayLot());
			entity.setRenoiRHMatricule(commentaire.getRenoiRHMatricule());
		}
		return entity;
	}
}

