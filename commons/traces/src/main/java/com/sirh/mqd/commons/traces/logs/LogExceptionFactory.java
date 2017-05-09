package com.sirh.mqd.commons.traces.logs;

import java.util.Date;

import com.sirh.mqd.commons.traces.dto.LogExceptionDTO;
import com.sirh.mqd.commons.traces.dto.LogTechniqueDTO;
import com.sirh.mqd.commons.traces.dto.LogWorkflowDTO;
import com.sirh.mqd.commons.traces.enums.ExceptionType;
import com.sirh.mqd.commons.traces.enums.LogType;

/**
 *
 * @author Ahmed
 *
 */
public class LogExceptionFactory {

	/**
	 * Methode qui crée un objet LogException qui va etre utilisé dans la méthode logException de la classe FacadeLogs Cette méthode défini
	 * le contenu du champ 'name' de l'exception
	 *
	 * @param date
	 * @param logType
	 * @param srcClassName
	 * @param name
	 * @param content
	 * @return LogExceptionDTO
	 */
	public static LogExceptionDTO getLogException(final LogType logType, final String srcClassName, final ExceptionType name,
			final String content) {

		LogTechniqueDTO logTechnique = LogTechniqueFactory.getLogTechnique(new Date(), logType, srcClassName, name.getLibelle(), content);
		LogWorkflowDTO logWorkflow = LogWorkflowFactory.getLogWorkflow(content);
		return new LogExceptionDTO(logTechnique, logWorkflow);
	}

	/**
	 * Methode qui crée un objet LogException qui va etre utilisé dans la méthode logException de la classe FacadeLogs Cette méthode défine
	 * Cette méthode ne prend pas en paramètre le champ 'name', Le champ name contiendra la valeur 'Exception inconnue'
	 *
	 * @param date
	 * @param logType
	 * @param srcClassName
	 * @param content
	 * @return LogExceptionDTO
	 */
	public static LogExceptionDTO getLogException(final LogType logType, final String srcClassName, final String content) {
		LogTechniqueDTO logTechnique = LogTechniqueFactory.getLogTechnique(new Date(), logType, srcClassName,
				ExceptionType.UNKNOWN_EXCEPTION.getLibelle(), content);
		LogWorkflowDTO logWorkflow = LogWorkflowFactory.getLogWorkflow(content);
		return new LogExceptionDTO(logTechnique, logWorkflow);
	}

}
