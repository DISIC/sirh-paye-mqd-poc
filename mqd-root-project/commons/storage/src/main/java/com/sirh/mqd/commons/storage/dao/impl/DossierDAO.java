package com.sirh.mqd.commons.storage.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.storage.constantes.ComparaisonConstantes;
import com.sirh.mqd.commons.storage.constantes.DossierConstantes;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IDossierDAO;
import com.sirh.mqd.commons.storage.entity.AlerteEntity;
import com.sirh.mqd.commons.storage.entity.ComparaisonEntity;
import com.sirh.mqd.commons.storage.entity.DossierEntity;
import com.sirh.mqd.commons.utils.constante.Constantes;

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
		query.addCriteria(Criteria.where(ComparaisonConstantes.COLONNE_ANOMALIE + Constantes.DOT
				+ ComparaisonConstantes.COLONNE_ANOMALIE_DATE_CLOTURE).exists(false));
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
		final Query query = new Query();
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_PAY_LOT).is(dossier.getPayLot()));
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_MATRICULE).is(dossier.getRenoiRHMatricule()));
		if (mongoTemplate.exists(query, DossierEntity.class)) {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

			final MongoConverter converter = mongoTemplate.getConverter();
			final DBObject dbo = (DBObject) converter.convertToMongoType(dossier);

			final Map<String, Object> map = mapper.convertValue(dbo, Map.class);
			map.remove(DossierConstantes.COLONNE_ID);
			final Update update = new Update();
			for (final Map.Entry<String, Object> entry : map.entrySet()) {
				update.set(entry.getKey(), entry.getValue());
			}
			mongoTemplate.upsert(query, update, DossierEntity.class);
		} else {
			mongoTemplate.save(dossier);
		}
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
		query.addCriteria(Criteria.where(ComparaisonConstantes.COLONNE_ANOMALIE + Constantes.DOT
				+ ComparaisonConstantes.COLONNE_ANOMALIE_DATE_CLOTURE).exists(false));
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
		final Query query = new Query();
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_PAY_LOT).is(payLot));
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_MATRICULE).is(renoiRHMatricule));
		return Math.toIntExact(mongoTemplate.count(query, AlerteEntity.class));
	}

	@Override
	public void insertAlerte(final AlerteEntity alerte) {
		mongoTemplate.save(alerte);
	}

	@Override
	public int countAlerte(final AlerteEntity entity) {
		final Query query = new Query();
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_PAY_LOT).is(entity.getPayLot()));
		query.addCriteria(Criteria.where(DossierConstantes.COLONNE_MATRICULE).is(entity.getRenoiRHMatricule()));
		query.addCriteria(Criteria.where(ComparaisonConstantes.COLONNE_TYPE_DONNEE).is(entity.getType()));
		return Math.toIntExact(mongoTemplate.count(query, AlerteEntity.class));
	}

	@Override
	public void deleteAlerte(final AlerteEntity entity) {
		mongoTemplate.remove(entity);
	}
}
