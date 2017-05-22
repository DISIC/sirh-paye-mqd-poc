package com.sirh.mqd.commons.utils.exception;

import java.text.MessageFormat;
import java.util.Date;

import com.sirh.mqd.commons.utils.DateUtils;

/**
 * TechnicalException levée lorsque une exception technique survient.
 * 
 * @author alexandre
 */
public class TechnicalException extends RuntimeException {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 6510897402139100108L;

	/**
	 * Date de l'exception
	 */
	private final Date date;

	/**
	 * Constructeur par défaut
	 */
	public TechnicalException() {
		super();
		this.date = DateUtils.getCalendarInstance().getTime();
	}

	/**
	 * Constructeur
	 * 
	 * @param message
	 *            le message à afficher dans l'exception
	 * @param arguments
	 *            les arguments à mettre dans le message
	 */
	public TechnicalException(final String message, final Object... arguments) {
		super(MessageFormat.format(message, arguments));
		this.date = DateUtils.getCalendarInstance().getTime();
	}

	/**
	 * Constructeur
	 * 
	 * @param e
	 *            l'exception initiale levée
	 */
	public TechnicalException(final Throwable e) {
		super(e);
		this.date = DateUtils.getCalendarInstance().getTime();
	}

	/**
	 * Constructeur
	 * 
	 * @param e
	 *            l'exception initiale levée
	 * @param message
	 *            le message à afficher dans l'exception
	 */
	public TechnicalException(final Throwable e, final String message) {
		super(message, e);
		this.date = DateUtils.getCalendarInstance().getTime();
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return DateUtils.clonerDate(date);
	}
}
