package com.sirh.mqd.commons.storage.dao;

import com.sirh.mqd.commons.storage.entity.StatutDossierEntity;

/**
 * @see doc.story.ref307
 *
 *      Interface fournissant les méthodes d'insert et de select de la table des
 *      commentaires
 *
 * @author khalil
 */

public interface IStatutDossierDAO {

	/**
	 * Méthode permettant de récupérer les statuts d'un dossier
	 *
	 * @param renoiRHMatricule
	 *            champ qui permet avec payLot, d'identifier un dossier
	 * @param payLot
	 *            champ qui permet avec renoiRHMatricule, d'identifier un
	 *            dossier
	 * @return StatutDossierEntity correspondant au statut du dossier
	 */
	StatutDossierEntity selectStatutDossier(String renoiRHMatricule, String payLot);

	/**
	 * Méthode permettant de modifier les statuts d'un dossier
	 *
	 * @param statutDossierEntity
	 *            statut du dossier à modifier
	 */
	void updateStatutDossier(StatutDossierEntity statutDossierEntity);

}
