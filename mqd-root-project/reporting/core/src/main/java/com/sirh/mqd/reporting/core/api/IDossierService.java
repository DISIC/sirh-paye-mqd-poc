package com.sirh.mqd.reporting.core.api;

import java.util.List;

import com.sirh.mqd.commons.exchanges.dto.pivot.AnomalieDTO;
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
	 * @param userId
	 *            identifiant de l'utilisateur
	 * @return {@link List} des dossiers rattachés à l'utilisateur connecté
	 */
	List<DossierDTO> listerDossiers(String userId);

	/**
	 * Méthode permettant de lister les anomalies d'un dossier
	 *
	 * @param renoiRHMatricule
	 *            matricule renoiRH
	 * @param payLot
	 *            lot PAY
	 * @return {@link List} des anomalies
	 */
	List<AnomalieDTO> listerAnomalies(String renoiRHMatricule, String payLot);

}
