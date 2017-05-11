package com.sirh.mqd.reporting.core.api;

import java.util.List;

import com.sirh.mqd.commons.exchanges.dto.pivot.AnomalieDTO;

/**
 * Service de gestion des informations d'un ou plusieurs dossiers
 *
 * @author alexandre
 */
public interface IDossierService {

	/**
	 * Méthode permettant de lister les anomalies d'un dossier
	 *
	 * @param dossierId
	 *            identifiant du dossier
	 * @return {@link List} des anomalies
	 */
	List<AnomalieDTO> listerAnomalies(String dossierId);

}
