package com.sirh.mqd.reporting.core.api;

import java.util.List;

import com.sirh.mqd.commons.exchanges.dto.eventcalendriergestion.EventCalendrierGestionDTO;

/**
 * Service de gestion des informations d'un calendrier gestion
 *
 * @author khalil
 */
public interface ICalendrierGestionService {

	/**
	 * Méthode permettant de lister tous les evenements du calendrier gestion
	 *
	 * @return {@link List} des evenements du calendrier gestion
	 */
	List<EventCalendrierGestionDTO> listerEventCalendrierGestion();

	/**
	 * Méthode permettant d'ajouter un evenement au calendrier gestion
	 *
	 * @param eventCalendrierGestionDTO
	 *            evenement à ajouter
	 */
	void ajouterEventCalendrierGestion(EventCalendrierGestionDTO eventCalendrierGestionDTO);

}
