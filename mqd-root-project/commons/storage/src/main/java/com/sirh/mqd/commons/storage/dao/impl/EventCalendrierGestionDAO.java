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
import com.sirh.mqd.commons.storage.constantes.EventCalendrierGestionConstantes;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IEventCalendrierGestionDAO;
import com.sirh.mqd.commons.storage.entity.EventCalendrierGestionEntity;
import com.sirh.mqd.commons.utils.DateUtils;

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
	public EventCalendrierGestionEntity selectEventCalendrierGestion(final String id) {
		EventCalendrierGestionEntity eventCalendrierGestion = new EventCalendrierGestionEntity();
		if (id != null) {
			final Query query = new Query();
			query.addCriteria(Criteria.where(EventCalendrierGestionConstantes.COLONNE_ID).is(id));
			eventCalendrierGestion = mongoTemplate.findOne(query, EventCalendrierGestionEntity.class);
		}
		return eventCalendrierGestion;
	}

	@Override
	public List<EventCalendrierGestionEntity> selectEventsCalendrierGestion(final InteractionSirhEnum referentiel,
			final String service) {
		final Query query = new Query();
		final Date now = DateUtils.getCalendarInstance().getTime();
		final Date dateDebut = DateUtils.addMonthsWithBounds(now, -1);
		final Date dateFin = DateUtils.addMonthsWithBounds(now, 6);
		query.addCriteria(Criteria.where(EventCalendrierGestionConstantes.COLONNE_DEBUT).lte(dateFin));
		query.addCriteria(Criteria.where(EventCalendrierGestionConstantes.COLONNE_ECHEANCE).gte(dateDebut));
		if (referentiel != null) {
			query.addCriteria(Criteria.where(EventCalendrierGestionConstantes.COLONNE_REFERENTIEL).is(referentiel));
		}
		if (StringUtils.isNotBlank(service)) {
			query.addCriteria(Criteria.where(EventCalendrierGestionConstantes.COLONNE_SERVICE).is(service));
		}
		return mongoTemplate.find(query, EventCalendrierGestionEntity.class);
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