package com.sirh.mqd.commons.traces.constantes;

/**
 * Constantes liées aux traces.
 * 
 * @author stephane
 */
public final class ConstantesTraces {
	/**
	 * Nom du bean gérant la facade des logs
	 */
	public static final String FACADE_LOGS = "facadeLogs";
	
	/**
	 * Non-constructeur
	 * 
	 * @throws InstantiationException
	 */
	private ConstantesTraces() throws InstantiationException {
		throw new InstantiationException("Création non autorisée d'une instance de : " + ConstantesTraces.class.getName());
	}
}
