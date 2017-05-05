package com.sirh.mqd.commons.exchanges.enums;

import java.text.MessageFormat;

import org.apache.commons.lang.StringEscapeUtils;

import ch.qos.logback.classic.Level;

/**
 * Enumération des messages métier.
 *
 * @author alexandre
 */
public enum LogMetierEnum {

	/**
	 * Exemple d'erreur avec le message {0} intégré.
	 */
	MESSAGE_1(1, CommunicationModeEnum.REQUETE, ModuleEnum.REPORTING, ReferentielEnum.NONE, Level.ERROR, "Exemple d'erreur avec le message {0} intégré.");

	private int position;

	private CommunicationModeEnum mode;

	private ModuleEnum module;

	private ReferentielEnum referentiel;

	private Level severite;

	private String pattern;

	private String libelle;

	LogMetierEnum(final int position, final CommunicationModeEnum mode, final ModuleEnum module, final ReferentielEnum referentiel,
			final Level severite, final String pattern) {
		this.position = position;
		this.mode = mode;
		this.module = module;
		this.referentiel = referentiel;
		this.pattern = pattern;
		this.severite = severite;
	}

	@Override
	public String toString() {
		return this.pattern;
	}

	public int getPosition() {
		return position;
	}

	public CommunicationModeEnum getMode() {
		return mode;
	}

	public ModuleEnum getModule() {
		return module;
	}

	public ReferentielEnum getReferentiel() {
		return referentiel;
	}

	public String getPattern() {
		return pattern;
	}

	public Level getSeverite() {
		return severite;
	}

	public String getLibelle() {
		return libelle;
	}

	/**
	 * Renvois les erreurs métiers au format adéquate.
	 */
	public void setLibelle(final Object... arguments) {
		this.libelle = MessageFormat.format(StringEscapeUtils.escapeSql(pattern), arguments);
	}

	public String getName() {
		return name();
	}
}
