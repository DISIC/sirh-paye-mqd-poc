package com.sirh.mqd.commons.storage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.storage.constantes.ConfigConstantes;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IConfigDAO;
import com.sirh.mqd.commons.storage.entity.ConfigEntity;

/**
 * Implémentation du DAO permettant l'accès aux différents paramètres saisis
 * dynamiquement
 *
 * @author khalil
 */
@Repository
@Service(PersistenceConstantes.CONFIG_DAO)
public class ConfigDAO implements IConfigDAO {

	@Autowired
	@Qualifier(PersistenceConstantes.MONGO_TEMPLATE)
	private MongoTemplate mongoTemplate;

	@Override
	public ConfigEntity selectConfig(final String id) {
		final Query query = new Query();
		query.addCriteria(Criteria.where(ConfigConstantes.COLONNE_ID).is(id));

		return mongoTemplate.findOne(query, ConfigEntity.class);
	}
}
