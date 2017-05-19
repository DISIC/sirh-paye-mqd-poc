package com.sirh.mqd.commons.exchanges.enums;

/**
 * Enumération listant les différents états de correction existants d'une
 * anomalie .
 *
 * @author alexandre
 */
public enum AnomalieEtatEnum {

	A_TRAITER(""),

	FAUX_SIGNALEMENT("Faux signalement"),

	CORRIGE("Corrigé"),

	DEMANDE_ASSISTANCE("Demande d'assistance"),

	REOUVERTURE("Réouverture");

	private String libelle;

	AnomalieEtatEnum(final String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(final String libelle) {
		this.libelle = libelle;
	}
}
