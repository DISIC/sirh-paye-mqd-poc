package com.sirh.mqd.commons.traces.utils;

import com.sirh.mqd.commons.traces.ClassContextSecurityManager;
import com.sirh.mqd.commons.traces.factory.LogActionFactory;
import com.sirh.mqd.commons.traces.factory.LogMetierFactory;
import com.sirh.mqd.commons.traces.factory.LogTechniqueFactory;

/**
 * Utilitaires sur les traces de log.
 *
 * @author alexandre
 */
public final class TracesUtils {

	private static ClassContextSecurityManager SECURITY_MANAGER;

	private static boolean SECURITY_MANAGER_CREATION_ALREADY_ATTEMPTED = false;

	/**
	 * Non constructeur.
	 *
	 * @throws InstantiationException
	 *             si tentative d'appel de ce constructeur.
	 */
	private TracesUtils() throws InstantiationException {
		throw new InstantiationException("Création non autorisée d'une instance de : " + TracesUtils.class.getName());
	}

	private static ClassContextSecurityManager getSecurityManager() {
		if (SECURITY_MANAGER != null) {
			return SECURITY_MANAGER;
		} else if (SECURITY_MANAGER_CREATION_ALREADY_ATTEMPTED) {
			return null;
		} else {
			SECURITY_MANAGER = safeCreateSecurityManager();
			SECURITY_MANAGER_CREATION_ALREADY_ATTEMPTED = true;
			return SECURITY_MANAGER;
		}
	}

	private static ClassContextSecurityManager safeCreateSecurityManager() {
		try {
			return new ClassContextSecurityManager();
		} catch (final java.lang.SecurityException sm) {
			return null;
		}
	}

	/**
	 * Méthode permettant de remonter la classe Java d'origine qui a appelé la
	 * Factory de Log LogActionFactory.
	 *
	 * @return {@link Class} le nom de la classe Java qui a invoqué la méthode
	 */
	public static Class<?> getClassCallingLogActionFactory() {
		final ClassContextSecurityManager securityManager = getSecurityManager();
		if (securityManager == null) {
			return null;
		}
		final Class<?>[] traces = securityManager.getClassContext();
		final String className = LogActionFactory.class.getName();
		int lastTargetClassIndex = 0;
		for (int i = 0; i < traces.length; i++) {
			if (className.equals(traces[i].getName())) {
				lastTargetClassIndex = i;
			}
		}
		return traces[lastTargetClassIndex + 2];
	}

	/**
	 * Méthode permettant de remonter la classe Java d'origine qui a appelé la
	 * Factory de Log LogMetierFactory.
	 *
	 * @return {@link Class} le nom de la classe Java qui a invoqué la méthode
	 */
	public static Class<?> getClassCallingLogMetierFactory() {
		final ClassContextSecurityManager securityManager = getSecurityManager();
		if (securityManager == null) {
			return null;
		}
		final Class<?>[] traces = securityManager.getClassContext();
		final String className = LogMetierFactory.class.getName();
		int lastTargetClassIndex = 0;
		for (int i = 0; i < traces.length; i++) {
			if (className.equals(traces[i].getName())) {
				lastTargetClassIndex = i;
			}
		}
		return traces[lastTargetClassIndex + 1];
	}

	/**
	 * Méthode permettant de remonter la classe Java d'origine qui a appelé la
	 * Factory de Log LogTechniqueFactory.
	 *
	 * @return {@link Class} le nom de la classe Java qui a invoqué la méthode
	 */
	public static Class<?> getClassCallingLogTechniqueFactory() {
		final ClassContextSecurityManager securityManager = getSecurityManager();
		if (securityManager == null) {
			return null;
		}
		final Class<?>[] traces = securityManager.getClassContext();
		final String className = LogTechniqueFactory.class.getName();
		int lastTargetClassIndex = 0;
		for (int i = 0; i < traces.length; i++) {
			if (className.equals(traces[i].getName())) {
				lastTargetClassIndex = i;
			}
		}
		return traces[lastTargetClassIndex + 1];
	}
}
