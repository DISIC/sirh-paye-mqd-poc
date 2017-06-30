package com.sirh.mqd.reporting.core.api;

import java.util.List;

import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;

/**
 * Service de gestion des informations d'un ou plusieurs dossiers
 *
 * @author alexandre
 */
public interface IDossierService {

	/**
	 * Méthode permettant de lister les dossiers
	 *
	 * @param payLot
	 *            le lot paye associé à l'utilisateur
	 * @param corpsCode
	 *            le corps auquel est associé l'utilisateur
	 * @param affectationCode
	 *            l'affectation auquel est associé à l'utilisateur
	 * @param gestionnaireCode
	 *            le code gestionnaire DGAC
	 * @return {@link List} des dossiers rattachés à l'utilisateur connecté
	 */
	List<DossierDTO> listerDossiers(String payLot, String corpsCode, String affectationCode, String gestionnaireCode);

	/**
	 * Méthode permettant de lister les anomalies d'un dossier
	 *
	 * @param renoiRHMatricule
	 *            matricule renoiRH
	 * @param payLot
	 *            lot PAY
	 * @return {@link List} des anomalies
	 */
	List<ComparaisonDTO> listerAnomalies(String renoiRHMatricule, String payLot);

	/**
	 * Méthode permettant de modifier l'état de correction d'une anomalie
	 *
	 * @param anomalie
	 *            l'anomalie à mettre à jour
	 */
	void modifierAnomalie(ComparaisonDTO anomalie);

}
