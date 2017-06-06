package com.sirh.mqd.commons.storage.bc;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.commentaire.CommentaireDTO;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.ICommentaireDAO;
import com.sirh.mqd.commons.storage.entity.CommentaireEntity;
import com.sirh.mqd.commons.storage.factory.CommentaireEntityFactory;

/**
 * Implémentation du BusinessController permettant de gérer les commentaires dans la base de données.
 *
 * @author khalil
 */
@Service(PersistenceConstantes.COMMENTAIRE_BC)
public class CommentaireBC {

	@Autowired
	@Qualifier(PersistenceConstantes.COMMENTAIRE_DAO)
	private ICommentaireDAO commentaireDAO;

	/**
	 * Méthode permettant de lister les commentaires
	 *
	 * @param renoiRHMatricule
	 * @param payLot
	 * @return listeCommentaires
	 */
	public List<CommentaireDTO> listerCommentaires(final String payLot, final String renoiRHMatricule) {
		final List<CommentaireEntity> commentaireEntities = this.commentaireDAO.selectCommentairesDossier(payLot, renoiRHMatricule);

		return commentaireEntities.stream().map(commentaireEntity -> CommentaireEntityFactory.createCommentaireDTO(commentaireEntity))
				.collect(Collectors.toList());
	}

	/**
	 * Méthode permettant d'insérer un commentaire
	 *
	 * @param renoiRHMatricule
	 * @param payLot
	 * @param utilisateur
	 * @param commentaire
	 */
	public void insererCommentaire(final String payLot, final String renoiRHMatricule, final String utilisateur, final String commentaire) {
		commentaireDAO.insertCommentaire(payLot, renoiRHMatricule, utilisateur, commentaire);
	}



}
