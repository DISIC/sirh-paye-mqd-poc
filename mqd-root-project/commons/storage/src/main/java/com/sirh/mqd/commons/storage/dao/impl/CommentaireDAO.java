package com.sirh.mqd.commons.storage.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.storage.constantes.CommentaireConstantes;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.ICommentaireDAO;
import com.sirh.mqd.commons.storage.entity.CommentaireEntity;

/**
 * Implémentation du DAO permettant l'accès à la table Commentaire
 *
 * @author khalil
 */

@Service(PersistenceConstantes.COMMENTAIRE_DAO)
public class CommentaireDAO implements ICommentaireDAO {

	@Autowired
	@Qualifier(PersistenceConstantes.MONGO_TEMPLATE)
	private MongoTemplate mongoTemplate;

	@Override
	public List<CommentaireEntity> selectCommentairesDossier(final String payLot, final String renoiRHMatricule) {

		List<CommentaireEntity> listeCommentaires  = new ArrayList<CommentaireEntity>();
		if (payLot != null && renoiRHMatricule != null) {
			final Query query = new Query();
			query.addCriteria(Criteria.where(CommentaireConstantes.PAY_LOT).is(payLot));
			query.addCriteria(Criteria.where(CommentaireConstantes.MATRICULE).is(renoiRHMatricule));
			listeCommentaires = mongoTemplate.find(query, CommentaireEntity.class);
		}
		return listeCommentaires;
	}

	@Override
	public void insertCommentaire(final String payLot, final String renoiRHMatricule, final String utilisateur, final String commentaire) {
		if (payLot != null && renoiRHMatricule != null && utilisateur != null && commentaire != null) {
			final Query query = new Query();
			final Update update = new Update();
			final Date dateCreation = new Date();
			update.addToSet(CommentaireConstantes.PAY_LOT, payLot);
			update.addToSet(CommentaireConstantes.MATRICULE, renoiRHMatricule);
			update.addToSet(CommentaireConstantes.GESTIONNAIRE, utilisateur);
			update.addToSet(CommentaireConstantes.DESCRIPTION, commentaire);
			update.addToSet(CommentaireConstantes.DATE_CREATION, dateCreation);
			mongoTemplate.upsert(query, update, CommentaireEntity.class);
		}

	}
}