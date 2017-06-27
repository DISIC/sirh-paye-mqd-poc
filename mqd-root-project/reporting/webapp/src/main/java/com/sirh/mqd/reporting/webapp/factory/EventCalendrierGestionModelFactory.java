package com.sirh.mqd.reporting.webapp.factory;

import com.sirh.mqd.commons.exchanges.dto.eventcalendriergestion.EventCalendrierGestionDTO;
import com.sirh.mqd.reporting.webapp.model.EventCalendrierGestionModel;

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

	public static EventCalendrierGestionModel createEventCalendrierGestionModel(
			final EventCalendrierGestionDTO eventCalendrierGestionDTO) {
		final EventCalendrierGestionModel eventCalendrierGestionModel = new EventCalendrierGestionModel();
		eventCalendrierGestionModel.setEvenement(eventCalendrierGestionDTO.getEvenement());
		eventCalendrierGestionModel.setType(eventCalendrierGestionDTO.getType());
		eventCalendrierGestionModel.setDebut(eventCalendrierGestionDTO.getDebut());
		eventCalendrierGestionModel.setEcheance(eventCalendrierGestionDTO.getEcheance());
		eventCalendrierGestionModel.setActeurs(eventCalendrierGestionDTO.getActeurs());
		eventCalendrierGestionModel.setCorps(eventCalendrierGestionDTO.getCorps());
		eventCalendrierGestionModel.setService(eventCalendrierGestionDTO.getService());
		eventCalendrierGestionModel.setCouleur(eventCalendrierGestionDTO.getCouleur());

		return eventCalendrierGestionModel;
	}

	public static EventCalendrierGestionDTO createEventCalendrierGestionDTO(
			final EventCalendrierGestionModel eventCalendrierGestionModel) {
		final EventCalendrierGestionDTO eventCalendrierGestionDTO = new EventCalendrierGestionDTO();
		eventCalendrierGestionDTO.setEvenement(eventCalendrierGestionModel.getEvenement());
		eventCalendrierGestionDTO.setType(eventCalendrierGestionModel.getType());
		eventCalendrierGestionDTO.setDebut(eventCalendrierGestionModel.getDebut());
		eventCalendrierGestionDTO.setEcheance(eventCalendrierGestionModel.getEcheance());
		eventCalendrierGestionDTO.setActeurs(eventCalendrierGestionModel.getActeurs());
		eventCalendrierGestionDTO.setCorps(eventCalendrierGestionModel.getCorps());
		eventCalendrierGestionDTO.setService(eventCalendrierGestionModel.getService());
		eventCalendrierGestionDTO.setCouleur(eventCalendrierGestionModel.getCouleur());

		return eventCalendrierGestionDTO;
	}
}
