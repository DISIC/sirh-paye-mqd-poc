package com.sirh.mqd.commons.traces.dto;

import java.io.Serializable;
import java.util.Date;

import com.google.common.base.Joiner;
import com.sirh.mqd.commons.constantes.Constantes;
import com.sirh.mqd.commons.traces.enums.LogType;
import com.sirh.mqd.commons.utils.DateUtils;

/***
 *
 * @author ahmed
 *
 */
public class LogTechniqueDTO implements Serializable {

	private static final long serialVersionUID = 5566801067528723852L;

	/**
	 * date du log
	 */
	private Date date;

	/**
	 * Type du log (Ws, Other)
	 */
	private LogType logType;

	/**
	 *
	 */
	private String composant;

	/**
	 *
	 */
	private String srcClassName;

	/**
	 * Titre du message
	 */
	private String name;

	/**
	 * Contenu du message
	 */
	private String content;

	private LogTechniqueDTO() {
		super();
	}

	public LogTechniqueDTO(final Date date, final LogType logType, final String composant, final String srcClassName, final String name,
			final String content) {
		this();
		this.date = date;
		this.logType = logType;
		this.composant = composant;
		this.srcClassName = srcClassName;
		this.name = name;
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public LogType getLogType() {
		return logType;
	}

	public void setLogType(final LogType logType) {
		this.logType = logType;
	}

	public String getComposant() {
		return composant;
	}

	public void setComposant(final String composant) {
		this.composant = composant;
	}

	public String getSrcClassName() {
		return srcClassName;
	}

	public void setSrcClassName(final String srcClassName) {
		this.srcClassName = srcClassName;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		String eventDateTxt = DateUtils.formateDateJJMMAAAAhhmmssSSS(this.date);
		StringBuilder logText = new StringBuilder();
		logText.append(Constantes.SPACE).append(Constantes.OPEN_SQUARE_BRACKET);
		Joiner joiner = Joiner.on(Constantes.CLOSE_SQUARE_BRACKET + Constantes.DASH + Constantes.OPEN_SQUARE_BRACKET).useForNull(
				Constantes.DASH);
		joiner.appendTo(logText, eventDateTxt, this.logType, this.composant, this.srcClassName, this.name, this.content);
		logText.append(Constantes.CLOSE_SQUARE_BRACKET);
		return logText.toString();
	}

}
