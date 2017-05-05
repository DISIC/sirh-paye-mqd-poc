package com.sirh.mqd.commons.storage.dao;

import com.sirh.mqd.commons.exchanges.enums.ReferentielEnum;

/**
 * @see doc.story.ref307
 *
 *      Interface fournissant l'ensemble des méthodes d'accès à la table de
 *      synchronisation des referentiels dans le tampon
 *
 * @author alexandre
 */
public interface ISynchroReferentielsDAO {

	/**
	 * Méthode permettant de remonter la dernière date de mise à jours du
	 * referentiel souhaité.
	 *
	 * @param referentiel
	 *            Réferentiel dont nous souhaitons la dernière date de mise à
	 *            jour.
	 * @return String String correspondant à la date de la dernière mise à jour.
	 */
	String selectLastDateUpDate(ReferentielEnum referentiel);

	/**
	 * Méthode permettant de mettre à jour le timestamp de la dernière
	 * modification du referentiel sélectionné
	 *
	 * @param majDate
	 *            Date de la mise à jours
	 * @param referentiel
	 *            Referentiel mis à jours
	 */
	void insertLastDateUpDate(String majDate, ReferentielEnum referentiel);

}
