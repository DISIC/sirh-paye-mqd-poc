package com.sirh.mqd.reporting.api.resources;

import java.io.Serializable;

/**
 * Interface du service technique de gestion des libellés de l'application.
 *
 * @author alexandre
 */
public interface ILabelSourceBundle extends Serializable {

	/**
	 * Retourne le libellé associé à un code donné.<br/>
	 * Si le code n'est pas trouvé, un libellé par défaut sera utilisé.
	 *
	 * @param code
	 *            Le code du libellé
	 * @param arguments
	 *            Les arguments attendus dans le libellé
	 * @return String Le libellé associé
	 */
	String getLabel(String code, Object... arguments);
}
