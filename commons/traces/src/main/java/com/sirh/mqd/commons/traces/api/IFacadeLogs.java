package com.sirh.mqd.commons.traces.api;

import com.sirh.mqd.commons.traces.dto.LogActionDTO;
import com.sirh.mqd.commons.traces.dto.LogTechniqueDTO;
import com.sirh.mqd.commons.traces.dto.LogsMetierDTO;

/**
 * Interface de gestion des méthodes de LOG propres au projet
 *
 * @author alexandre
 */
public interface IFacadeLogs {

	/**
	 * Méthode de création d'un log technique
	 *
	 * @param exception
	 *            DTO d'un log technique contenant une exception
	 */
	void logTechnique(LogTechniqueDTO exception);

	/**
	 * Méthode de création d'un log métier en récupérant la date du système
	 *
	 * @param messageMetier
	 *            DTO d'un log métier contenant un message métier en succès,
	 *            alerte ou erreur
	 */
	void logsMetier(LogsMetierDTO messageMetier);

	/**
	 * Méthode de création d'un log définissant une action IHM
	 *
	 * @param actionIHM
	 *            DTO d'un log d'une action réalisée via l'IHM
	 */
	void logAction(LogActionDTO actionIHM);

}