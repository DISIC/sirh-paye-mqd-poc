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
	 * @param type
	 *            le type d'événement recherché
	 * @param couleur
	 *            la couleur de l'événement recherché
	 * @param corps
	 *            le code corps de l'événement recherché
	 * @return {@link List} des evenements du calendrier gestion
	 */
	List<EventCalendrierDTO> listerEventsAvecBornesTemporelles(InteractionSirhEnum referentiel, String service,
			Date dateDebut, Date dateFin, String type, String couleur, String corps);

	/**
	 * Méthode permettant d'ajouter un événement au calendrier gestion
	 *
	 * @param eventCalendrierGestionDTO
	 *            evenement à ajouter
	 */
	void ajouterEvent(EventCalendrierDTO eventCalendrierGestionDTO);

	/**
	 * Méthode permettant de lister les couleurs d'un evenement
	 *
	 * @return {@link List} des couleurs existantes
	 *
	 */
	List<String> listerCouleursEvents();

	/**
	 * Méthode permettant de lister les corps d'un evenement
	 *
	 * @return {@link List} des corps existantes
	 *
	 */
	List<String> listerCorpsEvents();

	/**
	 * Méthode permettant de lister les types d'un evenement
	 *
	 * @return {@link List} des types existantes
	 *
	 */
	List<String> listerTypesEvents();
}
