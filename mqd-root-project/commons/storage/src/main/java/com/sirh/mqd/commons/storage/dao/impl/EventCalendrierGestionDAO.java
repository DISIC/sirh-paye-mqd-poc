package com.sirh.mqd.commons.storage.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.storage.constantes.EventCalendrierGestionConstantes;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IEventCalendrierGestionDAO;
import com.sirh.mqd.commons.storage.entity.EventCalendrierGestionEntity;

/**
 * Implémentation du DAO permettant l'accès à la table Event_Calendrier_Gestion
 *
 * @author khalil
 */
@Service(PersistenceConstantes.EVENT_CALENDRIER_GESTION_DAO)
public class EventCalendrierGestionDAO implements IEventCalendrierGestionDAO {

	@Autowired
	@Qualifier(PersistenceConstantes.MONGO_TEMPLATE)
	private MongoTemplate mongoTemplate;

	@Override
	public EventCalendrierGestionEntity selectEventCalendrierGestion(final String evenement, final Date debut,
			final Date echeance) {
		EventCalendrierGestionEntity eventCalendrierGestion = new EventCalendrierGestionEntity();
		if (evenement != null && debut != null && echeance != null) {
			final Query query = new Query();
			query.addCriteria(Criteria.where(EventCalendrierGestionConstantes.COLONNE_EVENEMENT).is(evenement));
			query.addCriteria(Criteria.where(EventCalendrierGestionConstantes.COLONNE_DEBUT).is(debut));
			query.addCriteria(Criteria.where(EventCalendrierGestionConstantes.COLONNE_ECHEANCE).is(echeance));
			eventCalendrierGestion = mongoTemplate.findOne(query, EventCalendrierGestionEntity.class);
		}
		return eventCalendrierGestion;
	}

	@Override
	public void upsertCalendrierGestion(final EventCalendrierGestionEntity eventCalendrierGestionEntity) {
		if (eventCalendrierGestionEntity != null) {
			mongoTemplate.save(eventCalendrierGestionEntity);
		}
	}

	@Override
	public void deleteCalendrierGestion(final EventCalendrierGestionEntity eventCalendrierGestionEntity) {
		if (eventCalendrierGestionEntity != null) {
			mongoTemplate.remove(eventCalendrierGestionEntity);
		}
	}
}