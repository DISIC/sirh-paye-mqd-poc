package com.sirh.mqd.commons.exchanges.factory;

/**
 * Factory générique gérant la création des logs.
 *
 * @author alexandre
 */
public final class LogFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private LogFactory() throws InstantiationException {
		throw new InstantiationException("Création non autorisée d'une instance de : " + LogFactory.class.getName());
	}

}
