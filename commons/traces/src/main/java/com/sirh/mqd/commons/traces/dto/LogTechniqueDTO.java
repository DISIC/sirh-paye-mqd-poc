package com.sirh.mqd.commons.traces.dto;

import java.io.Serializable;

import com.google.common.base.Joiner;
import com.sirh.mqd.commons.traces.enums.ExceptionTypeEnum;
import com.sirh.mqd.commons.traces.enums.InteractionModuleEnum;
import com.sirh.mqd.commons.traces.enums.InteractionToolEnum;
import com.sirh.mqd.commons.utils.constante.Constantes;

/***
 * DTO correspondant à un log technique (exception, message d'erreur technique
 * custom, etc...)
 *
 * @author alexandre
 */
public class LogTechniqueDTO implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 5566801067528723852L;

	/**
	 * Type de la technologie (Ws, Other)
	 */
	private InteractionToolEnum technologie;

	/**
	 * Nom du composant applicatif
	 */
	private InteractionModuleEnum composant;

	/**
	 * Le nom de la classe Java associée
	 */
	private String srcClassName;

	/**
	 * Titre du message
	 */
	private ExceptionTypeEnum exception;

	/**
	 * Contenu du message
	 */
	private String content;

	public LogTechniqueDTO() {
		super();
	}

	public LogTechniqueDTO(final InteractionToolEnum technologie, final InteractionModuleEnum composant,
			final String srcClassName, final ExceptionTypeEnum exception, final String content) {
		super();
		this.technologie = technologie;
		this.composant = composant;
		this.exception = exception;
		this.srcClassName = srcClassName;
		this.content = content;
	}

	public InteractionToolEnum getTechnologie() {
		return technologie;
	}

	public void setTechnologie(final InteractionToolEnum technologie) {
		this.technologie = technologie;
	}

	public InteractionModuleEnum getComposant() {
		return composant;
	}

	public void setComposant(final InteractionModuleEnum composant) {
		this.composant = composant;
	}

	public String getSrcClassName() {
		return srcClassName;
	}

	public void setSrcClassName(final String srcClassName) {
		this.srcClassName = srcClassName;
	}

	public ExceptionTypeEnum getException() {
		return exception;
	}

	public void setException(final ExceptionTypeEnum exception) {
		this.exception = exception;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		final StringBuilder logBuilder = new StringBuilder();
		logBuilder.append(Constantes.OPEN_SQUARE_BRACKET);
		final Joiner joiner = Joiner
				.on(Constantes.CLOSE_SQUARE_BRACKET + Constantes.DASH + Constantes.OPEN_SQUARE_BRACKET)
				.useForNull(Constantes.DASH);
		joiner.appendTo(logBuilder, this.technologie.name(), this.composant.name(), this.srcClassName,
				this.exception.getLibelle(), this.content);
		logBuilder.append(Constantes.CLOSE_SQUARE_BRACKET);
		return logBuilder.toString();
	}
}
