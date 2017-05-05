package com.sirh.mqd.commons.storage.dao.impl;

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
import com.sirh.mqd.commons.utils.DateUtils;

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
	@Qualifier(PersistenceConstantes.MONGO_TEMPLATE)
	private MongoTemplate mongoTemplate;

	@Override
	public String selectLastDateUpDate(final ReferentielEnum referentiel) {

		String d = null;
		if (referentiel.name() != null) {
			final Query query = new Query();
			query.addCriteria(Criteria.where("name").is(referentiel.name()));
			d = DateUtils
					.formateDateAAAAMMJJhhmmss(mongoTemplate.findOne(query, ReferentielEntity.class).getLastUpdate());
		}
		return d;
	}

	@Override
	public void insertLastDateUpDate(final String majDate, final ReferentielEnum referentiel) {
		if (majDate != null) {
			final Query query = new Query();
			query.addCriteria(Criteria.where("name").is(referentiel.name()));
			final Update update = new Update();
			update.addToSet("lastUpdate", majDate);
			mongoTemplate.upsert(query, update, ReferentielEntity.class);
		}

	}
}
