package com.sirh.mqd.reporting.core.api;

import java.util.List;

import com.sirh.mqd.commons.exchanges.dto.commentaire.CommentaireDTO;

/**
 * Service de gestion des commentaires d'un dossier
 *
 * @author khalil
 */
public interface ICommentaireService {

	/**
	 * Méthode permettant de lister les commentaires d'un dossier
	 *
	 * @param renoiRHMatricule
	 *            matricule renoiRH
	 * @param payLot
	 *            lot PAY
	 * @return {@link List} des commentaires
	 */
	List<CommentaireDTO> listerCommentaires(String renoiRHMatricule, String payLot);

	/**
	 * Méthode permettant d'ajouter un commentaire à un dossier
	 *
	 * @param commentaireDTO
	 *            commentaire à ajouter
	 */
	void ajouterCommentaire(CommentaireDTO commentaireDTO);

}
