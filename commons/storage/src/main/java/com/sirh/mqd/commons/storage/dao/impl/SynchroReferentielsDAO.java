package com.sirh.mqd.commons.storage.dao.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.enums.ReferentielEnum;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.ISynchroReferentielsDAO;
import com.sirh.mqd.commons.storage.entity.ReferentielEntity;

/**
 * Implémentation du DAO permettant l'accès à la table de synchronisation des
 * referentiels
 *
 * @author alexandre
 */
@Service(PersistenceConstantes.SYNCHRO_REFERENTIELS_DAO)
@EnableAutoConfiguration
public class SynchroReferentielsDAO implements ISynchroReferentielsDAO {

	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;

	@Override
	public ReferentielEntity selectLastDateUpDate(final ReferentielEnum referentiel) {

		ReferentielEntity d = null;
		if (referentiel.name() != null) {
			final Query query = new Query();
			query.addCriteria(Criteria.where("name").is(referentiel.name()));
			d = mongoTemplate.findOne(query, ReferentielEntity.class);
		}
		return d;
	}

	@Override
	public void insertLastDateUpDate(final String majDate, final ReferentielEnum referentiel, final Long duree,
			final TimeUnit timeUnit) {
		if (majDate != null) {
			final Query query = new Query();
			query.addCriteria(Criteria.where("name").is(referentiel.name()));
			final Update update = new Update();
			if (duree == null || duree.compareTo(new Long(1)) < 0) {
				update.addToSet("lastUpdate", majDate);
				mongoTemplate.upsert(query, update, ReferentielEntity.class);
			} else {
				// On prend la durée -1h pour que la mise à jour ne soit pas
				// bloquée.
				update.addToSet("lastUpdate", majDate);
				mongoTemplate.upsert(query, update, ReferentielEntity.class);

				redisTemplate.opsForValue().set(key, majDate, duree - 1, timeUnit);
			}
		}

	}
}
