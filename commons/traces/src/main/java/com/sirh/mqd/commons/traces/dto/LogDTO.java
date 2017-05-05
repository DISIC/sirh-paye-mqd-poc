package com.sirh.mqd.commons.traces.dto;

import java.io.Serializable;
import java.util.Date;

import com.sirh.mqd.commons.traces.enums.LogType;

/**
 * Classe de traitement des logs
 *
 * @author alexandre
 */
public class LogDTO implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -3371035528951776417L;

	/**
	 * Date du log
	 */
	private Date logDate;

	/**
	 * Type de log (Web Services, ...)
	 */
	private LogType logType;

	/**
	 * Nom de l'appelant (Nom du Web Service, ...)
	 */
	private String name;

	/**
	 * Date quand l'événement s'est produit
	 */
	private Date eventDate;

	/**
	 * Nom de la classe où est uilisée ce logger
	 */
	private String srcClassName;

	/**
	 * Contenu du log
	 */
	private String content;

	/**
	 * Constructeur
	 */
	public LogDTO() {
		super();
	}

	/**
	 * @return the logDate
	 */
	public Date getLogDate() {
		return logDate;
	}

	/**
	 * @param logDate
	 *            the logDate to set
	 */
	public void setLogDate(final Date logDate) {
		this.logDate = logDate;
	}

	/**
	 * @return the logType
	 */
	public LogType getLogType() {
		return logType;
	}

	/**
	 * @param logType
	 *            the logType to set
	 */
	public void setLogType(final LogType logType) {
		this.logType = logType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the eventDate
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate
	 *            the date the event has arrived to set
	 */
	public void setEventDate(final Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return the srcClassName
	 */
	public String getSrcClassName() {
		return srcClassName;
	}

	/**
	 * @param srcClassName
	 *            the srcClassName to set
	 */
	public void setSrcClassName(final String srcClassName) {
		this.srcClassName = srcClassName;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(final String content) {
		this.content = content;
	}
}
