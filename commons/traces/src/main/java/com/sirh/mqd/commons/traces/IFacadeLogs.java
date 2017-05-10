package com.sirh.mqd.commons.traces;

import org.slf4j.event.Level;

import com.sirh.mqd.commons.traces.dto.LogActionDTO;
import com.sirh.mqd.commons.traces.dto.LogMetierDTO;
import com.sirh.mqd.commons.traces.dto.LogTechniqueDTO;

/**
 * Interface de gestion des méthodes de LOG propres au projet
 *
 * @author alexandre
 */
public interface IFacadeLogs {

	/**
	 * Méthode de création d'un log technique
	 *
	 * @param logLevel
	 *            niveau de log du message
	 * @param exception
	 *            DTO d'un log technique contenant une exception
	 */
	void logTechnique(Level logLevel, LogTechniqueDTO exception);

	/**
	 * Méthode de création d'un log métier en récupérant la date du système
	 *
	 * @param logLevel
	 *            niveau de log du message
	 * @param messageMetier
	 *            DTO d'un log métier contenant un message métier en succès,
	 *            alerte ou erreur
	 */
	void logMetier(Level logLevel, LogMetierDTO messageMetier);

	/**
	 * Méthode de création d'un log définissant une action IHM
	 *
	 * @param logLevel
	 *            niveau de log du message
	 * @param actionIHM
	 *            DTO d'un log d'une action réalisée via l'IHM
	 */
	void logAction(Level logLevel, LogActionDTO actionIHM);

}