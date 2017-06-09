package com.sirh.mqd.reporting.webapp.factory;

/**
 * Factory de création des historiques.
 *
 * @author alexandre
 */
public final class HistoriqueModelFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private HistoriqueModelFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + HistoriqueModelFactory.class.getName());
	}
}
