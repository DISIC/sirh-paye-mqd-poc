package com.sirh.mqd.reporting.webapp.factory;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

import com.sirh.mqd.commons.exchanges.dto.calendrier.EventCalendrierDTO;

/**
 * Factory de création du calendrier gestion à manipuler côté IHM.
 *
 * @author khalil
 */
public final class CalendrierGestionModelFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private CalendrierGestionModelFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + CalendrierGestionModelFactory.class.getName());
	}

	public static DefaultScheduleEvent createEventCalendrierModel(final EventCalendrierDTO eventCalendrierGestionDTO) {
		final DefaultScheduleEvent event = new DefaultScheduleEvent();
		event.setTitle(eventCalendrierGestionDTO.getEvenement());
		event.setStartDate(eventCalendrierGestionDTO.getDebut());
		event.setEndDate(eventCalendrierGestionDTO.getEcheance());
		event.setData(eventCalendrierGestionDTO);
		event.setDescription(eventCalendrierGestionDTO.getCommentaire());
		return event;
	}

	public static EventCalendrierDTO createEventCalendrierGestionDTO(final ScheduleEvent event) {
		final EventCalendrierDTO eventCalendrierGestionDTO = new EventCalendrierDTO();
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
		eventCalendrierGestionDTO.setCommentaire(event.getDescription());
		return eventCalendrierGestionDTO;
	}
}
