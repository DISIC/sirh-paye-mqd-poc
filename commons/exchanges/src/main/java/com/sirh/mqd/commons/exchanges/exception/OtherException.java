package com.sirh.mqd.commons.exchanges.exception;

/**
 * Exception non applicative
 *
 * @author alexandre
 *
 */
public class OtherException extends Exception {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 2562410216768046938L;

	/**
	 * @param message
	 * @param cause
	 */
	public OtherException(final String message, final Exception cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public OtherException(final String message) {
		super(message);
	}
}
