package com.sirh.mqd.commons.storage.factory;

import com.sirh.mqd.commons.exchanges.dto.eventcalendriergestion.EventCalendrierGestionDTO;
import com.sirh.mqd.commons.storage.entity.EventCalendrierGestionEntity;

/**
 * Factory de création des entités des calendrier gestion.
 *
 * @author khalil
 */
public class EventCalendrierGestionEntityFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private EventCalendrierGestionEntityFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + EventCalendrierGestionEntityFactory.class.getName());
	}

	public static EventCalendrierGestionDTO createEventCalendrierGestionDTO(final EventCalendrierGestionEntity entity) {
		EventCalendrierGestionDTO eventCalendrierGestion = null;
		if (entity != null) {
			eventCalendrierGestion = new EventCalendrierGestionDTO();
			eventCalendrierGestion.setId(entity.getId());
			eventCalendrierGestion.setReferentiel(entity.getReferentiel());
			eventCalendrierGestion.setEvenement(entity.getEvenement());
			eventCalendrierGestion.setType(entity.getType());
			eventCalendrierGestion.setDebut(entity.getDebut());
			eventCalendrierGestion.setEcheance(entity.getEcheance());
			eventCalendrierGestion.setActeurs(entity.getActeurs());
			eventCalendrierGestion.setCorps(entity.getCorps());
			eventCalendrierGestion.setService(entity.getService());
			eventCalendrierGestion.setCouleur(entity.getCouleur());
		}
		return eventCalendrierGestion;
	}

	public static EventCalendrierGestionEntity createEventCalendrierGestionEntity(
			final EventCalendrierGestionDTO eventCalendrierGestion) {
		EventCalendrierGestionEntity entity = null;
		if (eventCalendrierGestion != null) {
			entity = new EventCalendrierGestionEntity();
			entity.setId(eventCalendrierGestion.getId());
			entity.setReferentiel(eventCalendrierGestion.getReferentiel());
			entity.setEvenement(eventCalendrierGestion.getEvenement());
			entity.setType(eventCalendrierGestion.getType());
			entity.setDebut(eventCalendrierGestion.getDebut());
			entity.setEcheance(eventCalendrierGestion.getEcheance());
			entity.setActeurs(eventCalendrierGestion.getActeurs());
			entity.setCorps(eventCalendrierGestion.getCorps());
			entity.setService(eventCalendrierGestion.getService());
			entity.setCouleur(eventCalendrierGestion.getCouleur());
		}
		return entity;
	}
}