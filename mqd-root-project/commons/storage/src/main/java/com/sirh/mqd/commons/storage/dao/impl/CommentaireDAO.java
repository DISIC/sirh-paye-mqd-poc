package com.sirh.mqd.commons.storage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.storage.constantes.DossierConstantes;
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
		List<CommentaireEntity> listeCommentaires = new ArrayList<CommentaireEntity>();
		if (payLot != null && renoiRHMatricule != null) {
			final Query query = new Query();
			if (StringUtils.isNotBlank(payLot)) {
				query.addCriteria(Criteria.where(DossierConstantes.COLONNE_PAY_LOT).is(payLot));
			}
			query.addCriteria(Criteria.where(DossierConstantes.COLONNE_MATRICULE).is(renoiRHMatricule));
			listeCommentaires = mongoTemplate.find(query, CommentaireEntity.class);
		}
		return listeCommentaires;
	}

	@Override
	public void insertCommentaire(final CommentaireEntity commentaire) {
		if (commentaire != null) {
			mongoTemplate.save(commentaire);
		}
	}
}