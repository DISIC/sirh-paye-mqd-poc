package com.sirh.mqd.reporting.routing.constantes;

/**
 * Liste des constantes utilisées pour le routing
 * 
 * @author Thales Services
 */
public final class RoutingConstantes {

	/**
	 * Nom du bean de reporting
	 */
	public static final String REPORTING_BATCH = "reportingBatch";

	/**
	 * Nom du bean de reporting
	 */
	public static final String SEND_FILE_CHANNEL = "sendFileChannel";

	/**
	 * Non-constructeur
	 * 
	 * @throws InstantiationException
	 */
	private RoutingConstantes() throws InstantiationException {
		throw new InstantiationException("Création non autorisée d'une instance de : " + RoutingConstantes.class.getName());
	}
}
