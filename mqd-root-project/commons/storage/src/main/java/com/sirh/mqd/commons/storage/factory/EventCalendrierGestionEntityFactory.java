package com.sirh.mqd.commons.storage.factory;

import java.util.Date;

import com.sirh.mqd.commons.exchanges.dto.eventcalendriergestion.EventCalendrierGestionDTO;
import com.sirh.mqd.commons.storage.entity.EventCalendrierGestionEntity;
import com.sirh.mqd.commons.utils.constante.Constantes;

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
			entity.setId(generateEntityId(eventCalendrierGestion.getEvenement(), eventCalendrierGestion.getDebut(),
					eventCalendrierGestion.getEcheance()));
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

	private static String generateEntityId(final String evenement, final Date debut, final Date echeance) {
		final StringBuilder builder = new StringBuilder();
		builder.append(evenement);
		builder.append(Constantes.DASH);
		builder.append(debut);
		builder.append(Constantes.DASH);
		builder.append(echeance);
		builder.append(Constantes.DASH);
		return builder.toString();
	}
}