package com.sirh.mqd.commons.storage.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.storage.constantes.DossierConstantes;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IStatutDossierDAO;
import com.sirh.mqd.commons.storage.entity.StatutDossierEntity;

/**
 * Implémentation du DAO permettant l'accès à la table Statut_Dossier
 *
 * @author khalil
 */
@Service(PersistenceConstantes.STATUT_DOSSIER_DAO)
public class StatutDossierDAO implements IStatutDossierDAO {

	@Autowired
	@Qualifier(PersistenceConstantes.MONGO_TEMPLATE)
	private MongoTemplate mongoTemplate;

	@Override
	public StatutDossierEntity selectStatutDossier(final String renoiRHMatricule, final String payLot) {
		StatutDossierEntity statutDossier = new StatutDossierEntity();
		if (payLot != null && renoiRHMatricule != null) {
			final Query query = new Query();
			if (StringUtils.isNotBlank(payLot)) {
				query.addCriteria(Criteria.where(DossierConstantes.COLONNE_PAY_LOT).is(payLot));
			}
			query.addCriteria(Criteria.where(DossierConstantes.COLONNE_MATRICULE).is(renoiRHMatricule));
			statutDossier = mongoTemplate.findOne(query, StatutDossierEntity.class);
		}
		return statutDossier;
	}

	@Override
	public void updateStatutDossier(final StatutDossierEntity statutDossierEntity) {
		if (statutDossierEntity != null) {
			mongoTemplate.save(statutDossierEntity);
		}
	}
}