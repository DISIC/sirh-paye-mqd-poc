package com.sirh.mqd.reporting.webapp.factory;

import com.sirh.mqd.commons.exchanges.dto.commentaire.CommentaireDTO;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.webapp.model.CommentaireModel;

/**
 * Factory de création des anomalies d'un dossier à manipuler côté IHM.
 *
 * @author khalil
 */

public class CommentaireModelFactory {
	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private CommentaireModelFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + CommentaireModelFactory.class.getName());
	}

	public static CommentaireModel createCommentaireModel(final CommentaireDTO commentaireDTO) {
		final CommentaireModel commentaireModel = new CommentaireModel();
		commentaireModel.setDateCreation(DateUtils.clonerDate(commentaireDTO.getDateCreation()));
		commentaireModel.setContenu(commentaireDTO.getContenu());
		commentaireModel.setUtilisateur(commentaireDTO.getUtilisateur());
		return commentaireModel;
	}

	public static CommentaireDTO createCommentaireDTO(final CommentaireModel commentaireModel) {
		final CommentaireDTO commentaireDTO = new CommentaireDTO();
		commentaireDTO.setDateCreation(DateUtils.clonerDate(commentaireModel.getDateCreation()));
		commentaireDTO.setContenu(commentaireModel.getContenu());
		commentaireDTO.setUtilisateur(commentaireModel.getUtilisateur());
		return commentaireDTO;
	}

}
