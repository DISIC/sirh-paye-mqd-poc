package com.sirh.mqd.reporting.core.api;

import java.util.Optional;

import com.sirh.mqd.commons.exchanges.dto.statut_dossier.StatutDossierDTO;

/**
 * Service de gestion des statuts d'un dossier
 *
 * @author khalil
 */
public interface IStatutDossierService {

	/**
	 * Méthode permettant de modifier les statuts d'un dossier
	 *
	 * @param renoiRHMatricule
	 *            matricule renoiRH
	 *
	 * @param payLot
	 *            lot PAY
	 */
	void modifierStatutDossier(StatutDossierDTO statutDossierDTO);

	/**
	 * Méthode permettant de génerer les statuts d'un dossier
	 *
	 * @param renoiRHMatricule
	 *            matricule renoiRH
	 * @param payLot
	 *            lot PAY
	 */
	Optional<StatutDossierDTO> genererStatutDossier(String renoiRHMatricule, String payLot);

}
