package com.sirh.mqd.commons.storage.dao.impl;

import java.util.Date;
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

	@SuppressWarnings("unchecked")
	@Override
	public void updateCalendrierGestion(final EventCalendrierEntity eventCalendrierGestionEntity) {
		if (eventCalendrierGestionEntity != null) {
			final Query query = new Query();
			query.addCriteria(Criteria.where(EventCalendrierConstantes.COLONNE_EVENEMENT)
					.is(eventCalendrierGestionEntity.getEvenement()));
			if (eventCalendrierGestionEntity.getDebut() != null) {
				query.addCriteria(Criteria.where(EventCalendrierConstantes.COLONNE_DEBUT)
						.is(eventCalendrierGestionEntity.getDebut()));
			}
			if (eventCalendrierGestionEntity.getEcheance() != null) {
				query.addCriteria(Criteria.where(EventCalendrierConstantes.COLONNE_ECHEANCE)
						.is(eventCalendrierGestionEntity.getEcheance()));
			}

			if (mongoTemplate.exists(query, EventCalendrierEntity.class)) {
				final ObjectMapper mapper = new ObjectMapper();
				mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

				final MongoConverter converter = mongoTemplate.getConverter();
				final DBObject dbo = (DBObject) converter.convertToMongoType(eventCalendrierGestionEntity);

				final Map<String, Object> map = mapper.convertValue(dbo, Map.class);
				map.remove(EventCalendrierConstantes.COLONNE_ID);
				final Update update = new Update();
				for (final Map.Entry<String, Object> entry : map.entrySet()) {
					update.set(entry.getKey(), entry.getValue());
				}
				mongoTemplate.upsert(query, update, EventCalendrierEntity.class);
			} else {
				mongoTemplate.save(eventCalendrierGestionEntity);
			}
		}

	}

	@Override
	public void deleteCalendrierGestion(final EventCalendrierEntity eventCalendrierGestionEntity) {
		if (eventCalendrierGestionEntity != null) {
			mongoTemplate.remove(eventCalendrierGestionEntity);
		}
	}
}