package com.sirh.mqd.commons.traces.api;

import com.sirh.mqd.commons.traces.dto.LogActionDTO;
import com.sirh.mqd.commons.traces.dto.LogExceptionDTO;
import com.sirh.mqd.commons.traces.dto.LogPerformanceDTO;
import com.sirh.mqd.commons.traces.dto.LogTechniqueDTO;
import com.sirh.mqd.commons.traces.dto.LogWorkflowDTO;
import com.sirh.mqd.commons.traces.dto.LogsMetierDTO;

/**
 * Interface de gestion des méthodes de LOG propres au projet
 *
 * @author Thales Services
 */
public interface IFacadeLogs {

	/**
	 * Création d'un log lorsqu'une exception est detectée
	 *
	 * @param LogExceptionDTO
	 *            : crée avec la factory LogExceptionFactory
	 */
	void logException(LogExceptionDTO logExceptionDTO);

	/**
	 * Création d'un log performance
	 *
	 * @param logPerformanceDTO
	 *            : crée avec la factory LOgPerformanceFactory
	 */
	void logPerformance(LogPerformanceDTO logPerformanceDTO);

	/**
	 * Création d'un log technique de niveau erreur
	 *
	 * @param logTechniqueDTO
	 *            : crée avec la factory LogTechniqueFactory
	 */
	void logTechniqueError(LogTechniqueDTO logTechniqueDTO);

	/**
	 * création d'un log technique de niveau warn
	 *
	 * @param logTechniqueDTO
	 *            : crée avec la factory LogTechniqueFactory
	 */
	void logTechniqueWarn(LogTechniqueDTO logTechniqueDTO);

	/**
	 *
	 * @param logMetier
	 */
	void logsMetierSuccess(LogsMetierDTO logsMetier);

	/**
	 * Création d'un log métier SIRI en récupérant la date du système
	 *
	 * @param logsMetierDTO
	 *            : crée avec la factory LogsMetierFactory
	 */
	void logsMetierError(LogsMetierDTO logMetierDTO);

	/**
	 * Crétaion d'un log Workflow
	 *
	 * @param logWorkflowDTO
	 *            : crée avec la factory LogWorkflowFactory
	 */
	void logWorkflowInfo(LogWorkflowDTO logWorkflowDTO);

	/**
	 * Ajout d'une ligne de log Définissant une étape de workflow applicative
	 *
	 * @param logWorkflow
	 *            : crée avec la factory : logWorkflowFactory
	 */
	void logWorkflowDebug(LogWorkflowDTO logWorkflow);

	/**
	 * Ajout d'une ligne de log définissant une action IHM
	 *
	 * @param logActionDTO
	 *            : créé avec la factory LogActionFactory
	 */
	void logAction(LogActionDTO logActionDTO);

}