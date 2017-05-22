package com.sirh.mqd.reporting.api.resources;

import java.io.Serializable;

/**
 * Interface du service technique de gestion des titres de l'application.
 *
 * @author alexandre
 */
public interface ITitreSourceBundle extends Serializable {

	/**
	 * Retourne le titre associé à un code donné.<br/>
	 * Si le code n'est pas trouvé, un titre par défaut sera utilisé.
	 *
	 * @param code
	 *            Le code du titre
	 * @param arguments
	 *            Les arguments attendus dans le titre
	 * @return String Le titre associé
	 */
	String getTitre(String code, Object... arguments);
}
