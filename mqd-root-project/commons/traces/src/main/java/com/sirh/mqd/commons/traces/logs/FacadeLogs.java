package com.sirh.mqd.commons.traces.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.traces.IFacadeLogs;
import com.sirh.mqd.commons.traces.LogThread;
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
	 * Exécuteur de la tâche de log
	 */
	private AsyncTaskExecutor logTask;

	/**
	 * Constructeur
	 */
	public FacadeLogs() {
		this.logMetier = LoggerFactory.getLogger("com.sirh.mqd.log.business");
		this.logAction = LoggerFactory.getLogger("com.sirh.mqd.log.ihm");
		this.logTechnique = LoggerFactory.getLogger("com.sirh.mqd.log.error");
	}

	private void logMessage(final Level logLevel, final Logger logger, final String message) {
		switch (logLevel) {
		case DEBUG:
			new LogThread(() -> logger.debug(message)).start(this.logTask);
			break;
		case ERROR:
			new LogThread(() -> logger.error(message)).start(this.logTask);
			break;
		case INFO:
			new LogThread(() -> logger.info(message)).start(this.logTask);
			break;
		case TRACE:
			new LogThread(() -> logger.trace(message)).start(this.logTask);
			break;
		case WARN:
			new LogThread(() -> logger.warn(message)).start(this.logTask);
			break;
		default:
			break;
		}
	}

	@Override
	public void logTechnique(final Level logLevel, final LogTechniqueDTO exception) {
		logMessage(logLevel, this.logTechnique, exception.toString());
	}

	@Override
	public void logMetier(final Level logLevel, final LogMetierDTO messageMetier) {
		logMessage(logLevel, this.logMetier, messageMetier.toString());
	}

	@Override
	public void logAction(final Level logLevel, final LogActionDTO actionIHM) {
		logMessage(logLevel, this.logAction, actionIHM.toString());
	}

	public AsyncTaskExecutor getLogTask() {
		return logTask;
	}

	public void setLogTask(final AsyncTaskExecutor logTask) {
		this.logTask = logTask;
	}
}