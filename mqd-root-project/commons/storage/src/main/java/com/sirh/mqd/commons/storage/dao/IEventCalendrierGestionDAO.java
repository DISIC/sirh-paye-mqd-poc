package com.sirh.mqd.commons.storage.dao;

import java.util.List;

import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;
import com.sirh.mqd.commons.storage.entity.EventCalendrierGestionEntity;

/**
 * @see doc.story.ref307
 *
 *      Interface fournissant les méthodes d'insert et de select de la table des
 *      commentaires
 *
 * @author khalil
 */

public interface IEventCalendrierGestionDAO {

	/**
	 * Méthode permettant de récupérer les infos d'UN evenement
	 *
	 * @param id
	 *            identité de l'événement
	 * @return EventCalendrierGestionEntity correspondant à l'évènement
	 */
	EventCalendrierGestionEntity selectEventCalendrierGestion(String id);

	/**
	 * Méthode permettant de de récupérer les infos de TOUS les evenements sous
	 * forme de liste
	 *
	 * @param referentiel
	 *            le sirh
	 * @param service
	 *            le service associé à l'événement
	 * @return List<EventCalendrierGestionDTO> correspondant à tous les
	 *         évènements existants
	 */
	List<EventCalendrierGestionEntity> selectEventsCalendrierGestion(InteractionSirhEnum referentiel, String service);

	/**
	 * Méthode permettant de mettre à jour ou de créer un evenement
	 *
	 * @param eventCalendrierGestionEntity
	 *            evenement à modifier/créer
	 */
	void upsertCalendrierGestion(EventCalendrierGestionEntity eventCalendrierGestionEntity);

	/**
	 * Méthode permettant de supprimer un evenement
	 *
	 * @param eventCalendrierGestionEntity
	 *            evenement à supprimer
	 */
	void deleteCalendrierGestion(EventCalendrierGestionEntity eventCalendrierGestionEntity);

}
