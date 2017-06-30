package com.sirh.mqd.reporting.core.api;

import java.util.List;

import com.sirh.mqd.commons.exchanges.dto.eventcalendriergestion.EventCalendrierGestionDTO;
import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;

/**
 * Service de gestion des informations d'un calendrier gestion
 *
 * @author khalil
 */
public interface ICalendrierGestionService {

	/**
	 * Méthode permettant de lister tous les evenements du calendrier gestion
	 *
	 * @param referentiel
	 *            le sirh
	 * @param service
	 *            le service associé à l'événement
	 * @return {@link List} des evenements du calendrier gestion
	 */
	List<EventCalendrierGestionDTO> listerEventCalendrierGestion(InteractionSirhEnum referentiel, String service);

	/**
	 * Méthode permettant d'ajouter un evenement au calendrier gestion
	 *
	 * @param eventCalendrierGestionDTO
	 *            evenement à ajouter
	 */
	void ajouterEventCalendrierGestion(EventCalendrierGestionDTO eventCalendrierGestionDTO);

}
