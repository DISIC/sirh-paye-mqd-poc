package com.sirh.mqd.commons.storage.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.constantes.UserConstantes;
import com.sirh.mqd.commons.storage.dao.IUserDAO;
import com.sirh.mqd.commons.storage.entity.UserEntity;

/**
 * Implémentation du DAO permettant l'accès à la table Utilisateurs
 *
 * @author alexandre
 */
@Service(PersistenceConstantes.USER_DAO)
public class UserDAO implements IUserDAO {

	@Autowired
	@Qualifier(PersistenceConstantes.MONGO_TEMPLATE)
	private MongoTemplate mongoTemplate;

	@Override
	public UserEntity selectUser(final String username) {
		UserEntity user = null;
		if (username != null) {
			final Query query = new Query();
			query.addCriteria(Criteria.where(UserConstantes.COLONNE_USERNAME).is(username));
			user = mongoTemplate.findOne(query, UserEntity.class);
		}
		return user;
	}

	@Override
	public void insertUser(final UserEntity user) {
		if (user != null) {
			mongoTemplate.save(user);
		}
	}

	@Override
	public void updateUserAuthenticationDate(final String username, final Date lastConnection) {
		if (username != null) {
			final Query query = new Query();
			query.addCriteria(Criteria.where(UserConstantes.COLONNE_USERNAME).is(username));
			final Update update = new Update();
			update.set(UserConstantes.COLONNE_DATE_AUTHENTIFICATION, lastConnection);
			mongoTemplate.updateFirst(query, update, UserEntity.class);
		}
	}
}