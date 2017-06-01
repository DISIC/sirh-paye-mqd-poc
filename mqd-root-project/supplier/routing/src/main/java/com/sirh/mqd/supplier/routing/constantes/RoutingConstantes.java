package com.sirh.mqd.supplier.routing.constantes;

/**
 * Liste des constantes utilisées pour le routing
 *
 * @author alexandre
 */
public final class RoutingConstantes {

	/**
	 * Nom du bean payBean
	 */
	public static final String PAY_BEAN = "payBean";

	/**
	 * Nom du bean supplierBatch
	 */
	public static final String SUPPLIER_BATCH = "supplierBatch";

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
