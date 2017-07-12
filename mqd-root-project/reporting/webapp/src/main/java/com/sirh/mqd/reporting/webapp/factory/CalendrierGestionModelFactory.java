package com.sirh.mqd.reporting.webapp.factory;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.timeline.TimelineEvent;

import com.sirh.mqd.commons.exchanges.dto.calendrier.EventCalendrierDTO;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.webapp.model.EventCalendrierModel;

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

	public static DefaultScheduleEvent createDefaultScheduleEvent(final EventCalendrierDTO eventCalendrierDTO) {
		final DefaultScheduleEvent event = new DefaultScheduleEvent();
		event.setTitle(eventCalendrierDTO.getEvenement());
		event.setStartDate(eventCalendrierDTO.getDebut());
		event.setEndDate(eventCalendrierDTO.getEcheance());
		event.setData(createEventCalendrierModel(eventCalendrierDTO));
		event.setDescription(eventCalendrierDTO.getCommentaire());
		event.setStyleClass(StringUtils.normalizeSpace(eventCalendrierDTO.getCouleur()));
		event.setEditable(Boolean.FALSE);
		return event;
	}

	public static EventCalendrierModel createEventCalendrierModel(final EventCalendrierDTO eventCalendrierDTO) {
		final EventCalendrierModel event = new EventCalendrierModel();
		event.setEvenement(eventCalendrierDTO.getEvenement());
		event.setDebut(eventCalendrierDTO.getDebut());
		event.setEcheance(eventCalendrierDTO.getEcheance());
		event.setCommentaire(eventCalendrierDTO.getCommentaire());
		event.setActeurs(eventCalendrierDTO.getActeurs());
		event.setCorps(eventCalendrierDTO.getCorps());
		event.setCouleur(eventCalendrierDTO.getCouleur());
		event.setService(eventCalendrierDTO.getService());
		event.setType(eventCalendrierDTO.getType());
		return event;
	}

	public static TimelineEvent createDefaultTimelineEvent(final EventCalendrierDTO eventCalendrierDTO) {
		final TimelineEvent event = new TimelineEvent();
		event.setStartDate(eventCalendrierDTO.getDebut());
		if (DateUtils.compareDates(eventCalendrierDTO.getDebut(), eventCalendrierDTO.getEcheance()) != 0) {
			event.setEndDate(eventCalendrierDTO.getEcheance());
		}
		event.setData(createEventCalendrierModel(eventCalendrierDTO));
		event.setGroup(eventCalendrierDTO.getType());
		event.setStyleClass(StringUtils.normalizeSpace(eventCalendrierDTO.getCouleur()));
		event.setEditable(Boolean.FALSE);
		return event;
	}
}
