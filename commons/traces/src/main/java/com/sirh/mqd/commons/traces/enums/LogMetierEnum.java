package com.sirh.mqd.commons.traces.enums;

import org.slf4j.event.Level;

/**
 * Enumération des messages métier.
 *
 * @author alexandre
 */
public enum LogMetierEnum {

	/**
	 * Exemple d'erreur avec le message {0} intégré.
	 */
	MESSAGE_1(InteractionModeEnum.REQUETE, InteractionModuleEnum.REPORTING, InteractionRepositoryEnum.NONE, Level.ERROR, "Exemple d'erreur avec le message {0} intégré.");

	private InteractionModeEnum mode;

	private InteractionModuleEnum module;

	private InteractionRepositoryEnum referentiel;

	private Level severite;

	private String pattern;

	LogMetierEnum(final InteractionModeEnum mode, final InteractionModuleEnum module,
			final InteractionRepositoryEnum referentiel, final Level severite, final String pattern) {
		this.mode = mode;
		this.module = module;
		this.referentiel = referentiel;
		this.pattern = pattern;
		this.severite = severite;
	}

	public InteractionModeEnum getMode() {
		return mode;
	}

	public InteractionModuleEnum getModule() {
		return module;
	}

	public InteractionRepositoryEnum getReferentiel() {
		return referentiel;
	}

	public String getPattern() {
		return pattern;
	}

	public Level getSeverite() {
		return severite;
	}

	public String getName() {
		return name();
	}
}
