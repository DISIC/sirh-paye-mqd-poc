package com.sirh.mqd.commons.traces.dto;

import java.io.Serializable;
import java.util.Date;

import com.google.common.base.Joiner;
import com.sirh.mqd.commons.exchanges.constante.Constantes;

public class LogPerformanceDTO implements Serializable {

	private static final long serialVersionUID = 6266664191877532634L;

	private Date date;

	private String composant;

	private String service;

	private long start;

	private long end;

	private String identifiant;

	private LogPerformanceDTO() {
		super();
	}

	public LogPerformanceDTO(final Date date, final String composant, final String service, final long start, final long end,
			final String identifiant) {
		this();
		this.date = date;
		this.composant = composant;
		this.service = service;
		this.start = start;
		this.end = end;
		this.identifiant = identifiant;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public String getComposant() {
		return composant;
	}

	public void setComposant(final String composant) {
		this.composant = composant;
	}

	public String getService() {
		return service;
	}

	public void setService(final String service) {
		this.service = service;
	}

	public long getStart() {
		return start;
	}

	public void setStart(final long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(final long end) {
		this.end = end;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(final String identifiant) {
		this.identifiant = identifiant;
	}

	@Override
	public String toString() {
		StringBuilder logText = new StringBuilder();
		logText.append(Constantes.SPACE).append(Constantes.OPEN_SQUARE_BRACKET);
		Joiner joiner = Joiner.on(Constantes.CLOSE_SQUARE_BRACKET + Constantes.OPEN_SQUARE_BRACKET).useForNull(Constantes.NULL);
		joiner.appendTo(logText, this.composant, this.service, (this.end - this.start), this.identifiant);
		logText.append(Constantes.CLOSE_SQUARE_BRACKET);
		return logText.toString();
	}

}
