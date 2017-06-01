package com.sirh.mqd.supplier.core.constantes;

/**
 * Constantes génériques au projet.
 *
 * @author alexandre
 */
public final class CoreConstantes {

	public static final String PAY_SERVICE = "payService";

	public static final String SIRH_MSO_SERVICE = "sirhMsoService";

	public static final String SIRH_DGAC_SERVICE = "sirhDgacService";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private CoreConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + CoreConstantes.class.getName());
	}
}
