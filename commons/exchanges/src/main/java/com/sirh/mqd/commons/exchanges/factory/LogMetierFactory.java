package com.sirh.mqd.commons.exchanges.factory;

import com.sirh.mqd.commons.exchanges.enums.LogMetierEnum;

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
		throw new InstantiationException("Création non autorisée d'une instance de : " + LogMetierFactory.class.getName());
	}

	/**
	 * <pre>
	 * 	Exemple d'erreur avec le message {0} intégré.
	 * </pre>
	 */
	public static synchronized LogMetierEnum MESSAGE_1(final String libelle) {
		return createBusinessMessage(LogMetierEnum.MESSAGE_1, libelle);
	}

	private static LogMetierEnum createBusinessMessage(final LogMetierEnum message,
			final Object... arguments) {
		message.setLibelle(arguments);
		return message;
	}
}
