package com.sirh.mqd.reporting.routing.constantes;

/**
 * Liste des constantes utilisées pour le routing
 *
 * @author alexandre
 */
public final class RoutingConstantes {

	/**
	 * Nom du bean reportingBatch
	 */
	public static final String REPORTING_BATCH = "reportingBatch";

	/**
	 * Nom du bean sendFileChannel
	 */
	public static final String SEND_FILE_CHANNEL = "sendFileChannel";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private RoutingConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + RoutingConstantes.class.getName());
	}
}
