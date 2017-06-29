package com.sirh.mqd.reporting.webapp.factory;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

import com.sirh.mqd.commons.exchanges.dto.eventcalendriergestion.EventCalendrierGestionDTO;

/**
 * Factory de création du calendrier gestion à manipuler côté IHM.
 *
 * @author khalil
 */
public final class EventCalendrierGestionModelFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private EventCalendrierGestionModelFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + EventCalendrierGestionModelFactory.class.getName());
	}

	public static DefaultScheduleEvent createEventCalendrierGestionModel(
			final EventCalendrierGestionDTO eventCalendrierGestionDTO) {
		final DefaultScheduleEvent event = new DefaultScheduleEvent();
		event.setTitle(eventCalendrierGestionDTO.getEvenement());
		event.setStartDate(eventCalendrierGestionDTO.getDebut());
		event.setEndDate(eventCalendrierGestionDTO.getEcheance());
		event.setData(eventCalendrierGestionDTO);
		return event;
	}

	public static EventCalendrierGestionDTO createEventCalendrierGestionDTO(final ScheduleEvent event) {
		final EventCalendrierGestionDTO eventCalendrierGestionDTO = new EventCalendrierGestionDTO();
		eventCalendrierGestionDTO.setEvenement(event.getTitle());
		// eventCalendrierGestionDTO.setType(((EventCalendrierGestionDTO)
		// event.getData()).getType());
		eventCalendrierGestionDTO.setDebut(event.getStartDate());
		eventCalendrierGestionDTO.setEcheance(event.getEndDate());
		// eventCalendrierGestionDTO.setActeurs(((EventCalendrierGestionModel)
		// event.getData()).getActeurs());
		// eventCalendrierGestionDTO.setCorps(((EventCalendrierGestionModel)
		// event.getData()).getCorps());
		// eventCalendrierGestionDTO.setService(((EventCalendrierGestionModel)
		// event.getData()).getService());
		// eventCalendrierGestionDTO.setCouleur(((EventCalendrierGestionModel)
		// event.getData()).getCouleur());

		return eventCalendrierGestionDTO;
	}
}
