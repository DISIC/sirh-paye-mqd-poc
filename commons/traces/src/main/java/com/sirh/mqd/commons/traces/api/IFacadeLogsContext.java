package com.sirh.mqd.commons.traces.api;

import com.sirh.mqd.commons.traces.logs.FacadeLogs;

/**
 * Interface de gestion des Facades de LOGS
 * 
 * @author Thales Services
 */
public interface IFacadeLogsContext {

	/**
	 * Retourne une instance approprié {@link FacadeLogs} spécifié par le paramètre <code>name</code> .
	 * 
	 * <p>
	 * Si le paramètre est égale à la valeur définie par {@link FacadeLogs#ROOT_LOGGER_NAME}, il s'agit du log root qui sera directement
	 * retourné sans chercher dans les autres Instance de log.
	 * 
	 * @param name
	 *            Nom du logger
	 * @return L'instance du logger
	 */
	FacadeLogs getLogger(String name);

	/**
	 * Retourne une instance approprié {@link FacadeLogs} spécifié par le paramètre <code>name</code> .
	 * 
	 * <p>
	 * Si le paramètre est égale à la valeur définie par {@link FacadeLogs#ROOT_LOGGER_NAME}, il s'agit du log root qui sera directement
	 * retourné sans chercher dans les autres Instance de log.
	 * 
	 * @param name
	 *            Nom du logger
	 * @return L'instance du logger
	 */
	FacadeLogs getLogger(Class<?> clazz);
}