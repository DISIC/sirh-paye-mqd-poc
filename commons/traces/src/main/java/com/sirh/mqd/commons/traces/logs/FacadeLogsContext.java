package com.sirh.mqd.commons.traces.logs;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;

import com.sirh.mqd.commons.traces.api.IFacadeLogsContext;

/**
 * Contexte de la Facade de LOGS
 * 
 * @author alexandre
 */
public class FacadeLogsContext implements IFacadeLogsContext {

	/**
	 * Map de Facades de LOGS
	 */
	private final Map<String, FacadeLogs> loggerCache;

	/**
	 * Nom du Logger de la Facade de LOG par défaut
	 */
	final public String ROOT_LOGGER_NAME = "ROOT";

	/**
	 * Facade de LOG par défaut
	 */
	final FacadeLogs root;

	/**
	 * Constructeur
	 */
	public FacadeLogsContext() {
		super();
		this.loggerCache = new ConcurrentHashMap<String, FacadeLogs>();
		this.root = new FacadeLogs(ROOT_LOGGER_NAME);
		this.loggerCache.put(Logger.ROOT_LOGGER_NAME, root);
	}

	@Override
	public final FacadeLogs getLogger(final Class<?> clazz) {
		return getLogger(clazz.getName());
	}

	@Override
	public final FacadeLogs getLogger(final String name) {

		if (name == null) {
			throw new IllegalArgumentException("name argument cannot be null");
		}

		// Si le logger racine est appellé il est retourné directement
		// sans perdre de temps
		if (ROOT_LOGGER_NAME.equalsIgnoreCase(name)) {
			return root;
		}

		// Vérifie si le logger existe, si oui, il est renvoyé.
		FacadeLogs childLogger = loggerCache.get(name);
		if (childLogger != null) {
			return childLogger;
		}

		// Sinon il est ajouté aux loggers et renvoyé.
		FacadeLogs newLogger = new FacadeLogs(name);
		loggerCache.put(name, newLogger);

		return newLogger;
	}
}
