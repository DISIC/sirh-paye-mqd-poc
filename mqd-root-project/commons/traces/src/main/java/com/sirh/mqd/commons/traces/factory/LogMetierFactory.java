package com.sirh.mqd.commons.traces.factory;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringEscapeUtils;

import com.sirh.mqd.commons.traces.dto.LogMetierDTO;
import com.sirh.mqd.commons.traces.enums.LogMetierEnum;

/**
 * Factory de création des messages métier.
 *
 * @author alexandre
 */
public final class LogMetierFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private LogMetierFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + LogMetierFactory.class.getName());
	}

	/**
	 * Méthode renvoyant le log métier avec le libellé complété à partir du
	 * pattern de l'énumération et des arguments.
	 *
	 * @param message
	 *            l'énumération du message métier à logguer
	 *            {@link LogMetierEnum}
	 * @param arguments
	 *            les arguments nécessaires au pattern de l'énumération
	 *            {@link LogMetierEnum}
	 * @return {@link LogMetierDTO} l'objet DTO gérant les logs métiers
	 */
	private static LogMetierDTO createBusinessMessage(final LogMetierEnum message, final Object... arguments) {
		final String libelle = MessageFormat.format(StringEscapeUtils.escapeJson(message.getPattern()), arguments);
		return new LogMetierDTO(message.getMode(), message.getModule(), message.getReferentiel(), libelle);
	}

	/**
	 * Exemple d'erreur avec le message {0} intégré.
	 */
	public static LogMetierDTO createLogMetierMessage1(final String libelle) {
		return createBusinessMessage(LogMetierEnum.MESSAGE_1, libelle);
	}
}
