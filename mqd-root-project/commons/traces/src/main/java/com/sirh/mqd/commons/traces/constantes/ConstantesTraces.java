package com.sirh.mqd.commons.traces.constantes;

/**
 * Constantes liées aux traces.
 *
 * @author alexandre
 */
public final class ConstantesTraces {

	/**
	 * Nom du bean gérant la facade des logs
	 */
	public static final String FACADE_LOGS = "facadeLogs";

	/**
	 * Nom du bean gérant les tâches d'exécution asynchrone
	 */
	public static final String TASK_EXECUTOR_NAME = "threadPoolTaskExecutor";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private ConstantesTraces() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + ConstantesTraces.class.getName());
	}
}
