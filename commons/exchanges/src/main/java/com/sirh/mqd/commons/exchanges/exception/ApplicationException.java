package com.sirh.mqd.commons.exchanges.exception;

import java.util.Date;

import com.sirh.mqd.commons.exchanges.enums.LogMetierEnum;
import com.sirh.mqd.commons.utils.DateUtils;

/**
 * Exception levée lorsque une exception métier survient.
 *
 * @author alexandre
 */
public class ApplicationException extends Exception {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 6510897402139100108L;

	/**
	 * Date de l'exception
	 */
	protected final Date date;

	protected String message;

	protected LogMetierEnum messageMetier;

	/**
	 * Constructeur par défaut
	 */
	public ApplicationException() {
		super();
		this.date = DateUtils.getCalendarInstance().getTime();
	}

	/**
	 * Constructeur
	 *
	 * @param message
	 *            le message à afficher dans l'exception
	 */
	public ApplicationException(final LogMetierEnum messageMetier) {
		super();
		this.message = messageMetier.getLibelle();
		this.date = DateUtils.getCalendarInstance().getTime();
		this.messageMetier = messageMetier;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return DateUtils.clonerDate(date);
	}

	@Override
	public String getMessage() {
		return message;
	}

	public LogMetierEnum getEnumMessagesMetier() {
		return messageMetier;
	}

}
