package com.sirh.mqd.reporting.core.api;

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
	 * avec une limitation dans le temps :
	 * <ul>
	 * <li>date de début : 1 mois avant la date actuelle (tout le mois compris)
	 * </li>
	 * <li>date de fin : 6 mois après la date actuelle (tout le dernier mois
	 * compris)</li>
	 * </ul>
	 *
	 * @param referentiel
	 *            le sirh
	 * @param service
	 *            le service associé à l'événement
	 * @return {@link List} des evenements du calendrier gestion
	 */
	List<EventCalendrierDTO> listerEventsAvecBornesTemporelles(InteractionSirhEnum referentiel, String service);

	/**
	 * Méthode permettant d'ajouter un evenement au calendrier gestion
	 *
	 * @param eventCalendrierGestionDTO
	 *            evenement à ajouter
	 */
	void ajouterEvent(EventCalendrierDTO eventCalendrierGestionDTO);
}
