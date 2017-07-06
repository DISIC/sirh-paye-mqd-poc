package com.sirh.mqd.commons.storage.factory;

import com.sirh.mqd.commons.exchanges.dto.calendrier.EventCalendrierDTO;
import com.sirh.mqd.commons.storage.entity.EventCalendrierEntity;

/**
 * Factory de création des entités des calendrier gestion.
 *
 * @author khalil
 */
public class CalendrierGestionEntityFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private CalendrierGestionEntityFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + CalendrierGestionEntityFactory.class.getName());
	}

	public static EventCalendrierDTO createEventCalendrierDTO(final EventCalendrierEntity entity) {
		EventCalendrierDTO eventCalendrier = null;
		if (entity != null) {
			eventCalendrier = new EventCalendrierDTO();
			eventCalendrier.setId(entity.getId());
			eventCalendrier.setReferentiel(entity.getReferentiel());
			eventCalendrier.setEvenement(entity.getEvenement());
			eventCalendrier.setType(entity.getType());
			eventCalendrier.setDebut(entity.getDebut());
			eventCalendrier.setEcheance(entity.getEcheance());
			eventCalendrier.setActeurs(entity.getActeurs());
			eventCalendrier.setCorps(entity.getCorps());
			eventCalendrier.setService(entity.getService());
			eventCalendrier.setCouleur(entity.getCouleur());
			eventCalendrier.setCommentaire(entity.getCommentaire());
		}
		return eventCalendrier;
	}

	public static EventCalendrierEntity createEventCalendrierEntity(final EventCalendrierDTO eventCalendrier) {
		EventCalendrierEntity entity = null;
		if (eventCalendrier != null) {
			entity = new EventCalendrierEntity();
			entity.setId(eventCalendrier.getId());
			entity.setReferentiel(eventCalendrier.getReferentiel());
			entity.setEvenement(eventCalendrier.getEvenement());
			entity.setType(eventCalendrier.getType());
			entity.setDebut(eventCalendrier.getDebut());
			entity.setEcheance(eventCalendrier.getEcheance());
			entity.setActeurs(eventCalendrier.getActeurs());
			entity.setCorps(eventCalendrier.getCorps());
			entity.setService(eventCalendrier.getService());
			entity.setCouleur(eventCalendrier.getCouleur());
			entity.setCommentaire(eventCalendrier.getCommentaire());
		}
		return entity;
	}
}