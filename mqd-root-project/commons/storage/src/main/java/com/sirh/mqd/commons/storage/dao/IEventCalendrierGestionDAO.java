package com.sirh.mqd.commons.storage.dao;

import java.util.Date;

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
	 * Méthode permettant de récupérer les infos d'un evenement
	 *
	 * @param evenement
	 *            champ contenant le libellé de l'evenement
	 * @param debut
	 *            date de début de l'évènement
	 * @param echeance
	 *            date de fin de l'évènement
	 * @return EventCalendrierGestionEntity correspondant à l'évènement
	 */
	EventCalendrierGestionEntity selectEventCalendrierGestion(String evenement, Date debut, Date echeance);

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
