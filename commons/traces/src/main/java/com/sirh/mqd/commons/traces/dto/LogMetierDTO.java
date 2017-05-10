package com.sirh.mqd.commons.traces.dto;

import java.io.Serializable;

import com.google.common.base.Joiner;
import com.sirh.mqd.commons.traces.enums.InteractionModeEnum;
import com.sirh.mqd.commons.traces.enums.InteractionModuleEnum;
import com.sirh.mqd.commons.traces.enums.InteractionRepositoryEnum;
import com.sirh.mqd.commons.utils.constante.Constantes;

/**
 * DTO correspondant à un log métier
 *
 * @author alexandre
 */
public class LogMetierDTO implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 4678054629112525685L;

	/**
	 * Mode d'intéraction avec le module
	 */
	private InteractionModeEnum mode;

	/**
	 * Nom du composant applicatif
	 */
	private InteractionModuleEnum composant;

	/**
	 * Nom du référentiel
	 */
	private InteractionRepositoryEnum referentiel;

	/**
	 * Message applicatif
	 */
	private String libelle;

	public LogMetierDTO(final InteractionModeEnum mode, final InteractionModuleEnum module,
			final InteractionRepositoryEnum referentiel, final String libelle) {
		super();
		this.mode = mode;
		this.composant = module;
		this.referentiel = referentiel;
		this.libelle = libelle;
	}

	public InteractionModeEnum getMode() {
		return mode;
	}

	public void setMode(final InteractionModeEnum mode) {
		this.mode = mode;
	}

	public InteractionModuleEnum getComposant() {
		return composant;
	}

	public void setComposant(final InteractionModuleEnum composant) {
		this.composant = composant;
	}

	public InteractionRepositoryEnum getReferentiel() {
		return referentiel;
	}

	public void setReferentiel(final InteractionRepositoryEnum referentiel) {
		this.referentiel = referentiel;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(final String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		final StringBuilder logBuilder = new StringBuilder();
		logBuilder.append(Constantes.OPEN_SQUARE_BRACKET);
		final Joiner joiner = Joiner
				.on(Constantes.CLOSE_SQUARE_BRACKET + Constantes.DASH + Constantes.OPEN_SQUARE_BRACKET)
				.useForNull(Constantes.DASH);
		joiner.appendTo(logBuilder, this.mode.name(), this.composant.name(), this.referentiel.name(), this.libelle);
		logBuilder.append(Constantes.CLOSE_SQUARE_BRACKET);
		return logBuilder.toString();
	}
}
