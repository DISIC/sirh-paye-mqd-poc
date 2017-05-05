package com.sirh.mqd.commons.exception;

import com.sirh.mqd.commons.exchanges.enums.LogMetierEnum;

/**
 * Exception levée lorsque une validation métier échoue.
 *
 * @author alexandre
 *
 */
public class ValidationException extends ApplicationException {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 2562410216768046938L;

	/**
	 * Constructeur
	 *
	 * @param message
	 *            le message à afficher dans l'exception
	 */
	public ValidationException(final LogMetierEnum messageMetier) {
		super(messageMetier);
	}
}
