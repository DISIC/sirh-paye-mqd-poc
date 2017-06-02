package com.sirh.mqd.commons.storage.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.storage.constantes.ComparaisonConstantes;
import com.sirh.mqd.commons.storage.constantes.DossierConstantes;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IDossierDAO;
import com.sirh.mqd.commons.storage.entity.ComparaisonEntity;
import com.sirh.mqd.commons.storage.entity.DossierEntity;

/**
 * Implémentation du DAO permettant l'accès à la table de synchronisation des
 * referentiels
 *
 * @author alexandre
 */
@Service(PersistenceConstantes.DOSSIER_DAO)
public class DossierDAO implements IDossierDAO {

	@Autowired
	@Qualifier(PersistenceConstantes.MONGO_TEMPLATE)
	private MongoTemplate mongoTemplate;

	@Override
	public DossierEntity selectDossier(final String payLot, final String renoiRHMatricule) {
		final Query query = new Query();
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_PAY_LOT).is(payLot));
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_MATRICULE).is(renoiRHMatricule));
		return mongoTemplate.findOne(query, DossierEntity.class);
	}

	@Override
	public int countAnomaliesDossier(final String payLot, final String renoiRHMatricule) {
		final Query query = new Query();
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_PAY_LOT).is(payLot));
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_MATRICULE).is(renoiRHMatricule));
		query.addCriteria(Criteria.where(ComparaisonConstantes.COLONNE_ANOMALIE).exists(true));
		return Math.toIntExact(mongoTemplate.count(query, ComparaisonEntity.class));
	}

	@Override
	public List<DossierEntity> selectDossiers(final String payLot, final String renoiRHCorpsCode,
			final String renoiRHAffectationCode) {
		final Query query = new Query();
		if (StringUtils.isNotBlank(payLot)) {
			query.addCriteria(Criteria.where(DossierConstantes.COLONNE_PAY_LOT).is(payLot));
			if (StringUtils.isNotBlank(renoiRHCorpsCode)) {
				query.addCriteria(Criteria.where(DossierConstantes.COLONNE_CORPS_CODE).is(renoiRHCorpsCode));
			}
		} else if (StringUtils.isNotBlank(renoiRHCorpsCode)) {
			query.addCriteria(Criteria.where(DossierConstantes.COLONNE_CORPS_CODE).is(renoiRHCorpsCode));
		} else if (StringUtils.isNotBlank(renoiRHAffectationCode)) {
			query.addCriteria(Criteria.where(DossierConstantes.COLONNE_AFFECTATION_CODE).is(renoiRHAffectationCode));
		}
		return mongoTemplate.find(query, DossierEntity.class);
	}

	@Override
	public void insertDossier(final DossierEntity dossier) {
		mongoTemplate.save(dossier);
	}

	@Override
	public ComparaisonEntity selectComparaison(final String payLot, final String renoiRHMatricule,
			final AnomalieTypeEnum typeDonnee) {
		final Query query = new Query();
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_PAY_LOT).is(payLot));
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_MATRICULE).is(renoiRHMatricule));
		query.addCriteria(Criteria.where(ComparaisonConstantes.COLONNE_TYPE_DONNEE).is(typeDonnee));
		return mongoTemplate.findOne(query, ComparaisonEntity.class);
	}

	@Override
	public void insertComparaison(final ComparaisonEntity comparaison) {
		mongoTemplate.save(comparaison);
	}

	@Override
	public List<ComparaisonEntity> selectAnomalies(final String payLot, final String renoiRHMatricule) {
		final Query query = new Query();
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_PAY_LOT).is(payLot));
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_MATRICULE).is(renoiRHMatricule));
		query.addCriteria(Criteria.where(ComparaisonConstantes.COLONNE_ANOMALIE).exists(true));
		return mongoTemplate.find(query, ComparaisonEntity.class);
	}

	@Override
	public List<ComparaisonEntity> selectComparaisons(final String payLot, final String renoiRHMatricule) {
		final Query query = new Query();
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_PAY_LOT).is(payLot));
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_MATRICULE).is(renoiRHMatricule));
		return mongoTemplate.find(query, ComparaisonEntity.class);
	}

	@Override
	public int countAlertesDossier(final String payLot, final String renoiRHMatricule) {
		return 0;
	}
}
