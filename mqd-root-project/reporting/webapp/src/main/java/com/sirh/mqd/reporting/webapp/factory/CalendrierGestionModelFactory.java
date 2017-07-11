package com.sirh.mqd.reporting.webapp.factory;

import org.apache.commons.lang3.StringUtils;
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
		event.setStyleClass(StringUtils.normalizeSpace(eventCalendrierGestionDTO.getCouleur()));
		return event;
	}

	public static EventCalendrierDTO createEventCalendrierGestionDTO(final ScheduleEvent event) {
		final EventCalendrierDTO eventCalendrierGestionDTO = new EventCalendrierDTO();
		eventCalendrierGestionDTO.setEvenement(event.getTitle());
		eventCalendrierGestionDTO.setDebut(event.getStartDate());
		eventCalendrierGestionDTO.setEcheance(event.getEndDate());
		eventCalendrierGestionDTO.setCommentaire(event.getDescription());

		eventCalendrierGestionDTO.setType(((EventCalendrierDTO) event.getData()).getType());
		eventCalendrierGestionDTO.setActeurs(((EventCalendrierDTO) event.getData()).getActeurs());
		eventCalendrierGestionDTO.setCorps(((EventCalendrierDTO) event.getData()).getCorps());
		eventCalendrierGestionDTO.setService(((EventCalendrierDTO) event.getData()).getService());
		eventCalendrierGestionDTO.setCouleur(((EventCalendrierDTO) event.getData()).getCouleur());
		return eventCalendrierGestionDTO;
	}
}
