package com.sirh.mqd.commons.traces.enums;

/**
 * Type de log
 *
 * @author stephane
 */
public enum LogType {

	/**
	 * Server sftp
	 */
	SFTP,

	/**
	 * Web services
	 */
	WS,

	/**
	 * Tout le reste. Mais, tant qu'on peut, il faut définir un enum pour chaque type de log.
	 */
	OTHER
}
