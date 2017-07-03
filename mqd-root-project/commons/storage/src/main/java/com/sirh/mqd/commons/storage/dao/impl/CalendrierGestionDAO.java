package com.sirh.mqd.commons.storage.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;
import com.sirh.mqd.commons.storage.constantes.EventCalendrierConstantes;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.ICalendrierGestionDAO;
import com.sirh.mqd.commons.storage.entity.EventCalendrierEntity;

/**
 * Implémentation du DAO permettant l'accès à la table Calendrier_Gestion
 *
 * @author khalil
 */
@Service(PersistenceConstantes.CALENDRIER_GESTION_DAO)
public class CalendrierGestionDAO implements ICalendrierGestionDAO {

	@Autowired
	@Qualifier(PersistenceConstantes.MONGO_TEMPLATE)
	private MongoTemplate mongoTemplate;

	@Override
	public EventCalendrierEntity selectEventCalendrierGestion(final String id) {
		EventCalendrierEntity eventCalendrierGestion = new EventCalendrierEntity();
		if (id != null) {
			final Query query = new Query();
			query.addCriteria(Criteria.where(EventCalendrierConstantes.COLONNE_ID).is(id));
			eventCalendrierGestion = mongoTemplate.findOne(query, EventCalendrierEntity.class);
		}
		return eventCalendrierGestion;
	}

	@Override
	public List<EventCalendrierEntity> selectEventsCalendrierGestion(final InteractionSirhEnum referentiel,
			final String service, final Date dateDebut, final Date dateFin) {
		final Query query = new Query();
		if (dateFin != null) {
			query.addCriteria(Criteria.where(EventCalendrierConstantes.COLONNE_DEBUT).lte(dateFin));
		}
		if (dateDebut != null) {
			query.addCriteria(Criteria.where(EventCalendrierConstantes.COLONNE_ECHEANCE).gte(dateDebut));
		}
		if (referentiel != null) {
			query.addCriteria(Criteria.where(EventCalendrierConstantes.COLONNE_REFERENTIEL).is(referentiel));
		}
		if (StringUtils.isNotBlank(service)) {
			query.addCriteria(Criteria.where(EventCalendrierConstantes.COLONNE_SERVICE).is(service));
		}
		return mongoTemplate.find(query, EventCalendrierEntity.class);
	}

	@Override
	public void upsertCalendrierGestion(final EventCalendrierEntity eventCalendrierGestionEntity) {
		if (eventCalendrierGestionEntity != null) {
			mongoTemplate.save(eventCalendrierGestionEntity);
		}
	}

	@Override
	public void deleteCalendrierGestion(final EventCalendrierEntity eventCalendrierGestionEntity) {
		if (eventCalendrierGestionEntity != null) {
			mongoTemplate.remove(eventCalendrierGestionEntity);
		}
	}
}