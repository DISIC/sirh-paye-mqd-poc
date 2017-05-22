package com.sirh.mqd.commons.traces.exception;

import java.util.Date;

import com.sirh.mqd.commons.traces.dto.LogMetierDTO;
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

	protected LogMetierDTO messageDTO;

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
	public ApplicationException(final LogMetierDTO messageDTO) {
		super();
		this.date = DateUtils.getCalendarInstance().getTime();
		this.messageDTO = messageDTO;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return DateUtils.clonerDate(date);
	}

	@Override
	public String getMessage() {
		return messageDTO.getLibelle();
	}

	public LogMetierDTO getMessageDTO() {
		return messageDTO;
	}

}
