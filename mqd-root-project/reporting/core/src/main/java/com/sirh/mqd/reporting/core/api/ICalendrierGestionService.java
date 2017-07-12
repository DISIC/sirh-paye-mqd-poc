package com.sirh.mqd.reporting.core.api;

import java.util.Date;
import java.util.List;

import com.sirh.mqd.commons.exchanges.dto.calendrier.EventCalendrierDTO;
import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;

/**
 * Service de gestion des informations d'un calendrier gestion
 *
 * @author khalil
 */
public interface ICalendrierGestionService {

	/**
	 * Méthode permettant de lister tous les evenements du calendrier gestion
	 * avec une limitation dans le temps.
	 *
	 * @param referentiel
	 *            le sirh
	 * @param service
	 *            le service associé à l'événement
	 * @param dateDebut
	 *            la date de début de recherche
	 * @param dateFin
	 *            la date de fin de recherche
	 * @return {@link List} des evenements du calendrier gestion
	 */
	List<EventCalendrierDTO> listerEventsAvecBornesTemporelles(InteractionSirhEnum referentiel, String service,
			Date dateDebut, Date dateFin);

	/**
	 * Méthode permettant d'ajouter un evenement au calendrier gestion
	 *
	 * @param eventCalendrierGestionDTO
	 *            evenement à ajouter
	 */
	void ajouterEvent(EventCalendrierDTO eventCalendrierGestionDTO);
}
