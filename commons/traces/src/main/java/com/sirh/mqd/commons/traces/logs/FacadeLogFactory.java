package com.sirh.mqd.commons.traces.logs;

import org.springframework.core.task.AsyncTaskExecutor;

/**
 * Façade pour les logs. C'est un Singleton.
 * 
 * @author Thales Services
 */
public class FacadeLogFactory {

	/**
	 * Logger du constructeur de génération de Facade de LOGS
	 * 
	 * @param clazz
	 * @return FacadeLogs
	 */
	public static FacadeLogs getLogger(final Class<?> clazz) {
		return new FacadeLogsContext().getLogger(clazz);
	}

	/**
	 * Logger du constructeur de génération de Facade de LOGS
	 * 
	 * @param clazz
	 * @return FacadeLogs
	 */
	public static FacadeLogs getLogger(final Class<?> clazz, final AsyncTaskExecutor logTask) {
		FacadeLogs logger = getLogger(clazz);
		logger.setLogTask(logTask);

		return logger;
	}

	/**
	 * Logger du constructeur de génération de Facade de LOGS
	 * 
	 * @param clazz
	 * @return FacadeLogs
	 */
	public static FacadeLogs getLogger(final String clazz, final AsyncTaskExecutor logTask) {
		FacadeLogs logger = getLogger(clazz);
		logger.setLogTask(logTask);

		return logger;
	}

	/**
	 * Logger du constructeur de génération de Facade de LOGS
	 * 
	 * @param clazz
	 * @return FacadeLogs
	 */
	public static FacadeLogs getLogger(final String clazz) {
		return new FacadeLogsContext().getLogger(clazz);
	}
}
