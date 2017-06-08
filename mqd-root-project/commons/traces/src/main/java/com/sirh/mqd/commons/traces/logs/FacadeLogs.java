package com.sirh.mqd.commons.traces.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.traces.IFacadeLogs;
import com.sirh.mqd.commons.traces.constantes.ConstantesTraces;
import com.sirh.mqd.commons.traces.dto.LogActionDTO;
import com.sirh.mqd.commons.traces.dto.LogMetierDTO;
import com.sirh.mqd.commons.traces.dto.LogTechniqueDTO;

/**
 * Façade pour les logs.
 *
 * @author alexandre
 */
@Component(ConstantesTraces.FACADE_LOGS)
public class FacadeLogs implements IFacadeLogs {

	/**
	 * Logger pour les logs métier
	 */
	private final Logger logMetier;

	/**
	 * Logger pour les logs techniques haut-niveau
	 */
	private final Logger logTechnique;

	/**
	 * Logger pour les logs définissant une action IHM
	 */
	private final Logger logAction;

	/**
	 * Constructeur
	 */
	public FacadeLogs() {
		super();
		this.logMetier = LoggerFactory.getLogger("com.sirh.mqd.log.business");
		this.logAction = LoggerFactory.getLogger("com.sirh.mqd.log.ihm");
		this.logTechnique = LoggerFactory.getLogger("com.sirh.mqd.log.error");
	}

	private void logMessage(final Level logLevel, final Logger logger, final String message) {
		switch (logLevel) {
		case DEBUG:
			logger.debug(message);
			break;
		case ERROR:
			logger.error(message);
			break;
		case INFO:
			logger.info(message);
			break;
		case TRACE:
			logger.trace(message);
			break;
		case WARN:
			logger.warn(message);
			break;
		default:
			break;
		}
	}

	@Override
	@Async(ConstantesTraces.TASK_EXECUTOR_NAME)
	public void logTechnique(final Level logLevel, final LogTechniqueDTO exception) {
		logMessage(logLevel, this.logTechnique, exception.toString());
	}

	@Override
	@Async(ConstantesTraces.TASK_EXECUTOR_NAME)
	public void logMetier(final Level logLevel, final LogMetierDTO messageMetier) {
		logMessage(logLevel, this.logMetier, messageMetier.toString());
	}

	@Override
	@Async(ConstantesTraces.TASK_EXECUTOR_NAME)
	public void logAction(final Level logLevel, final LogActionDTO actionIHM) {
		logMessage(logLevel, this.logAction, actionIHM.toString());
	}
}