package com.sirh.mqd.commons.exchanges.enums;

/**
 * Enumération listant les différents états de disponibilité de la version
 * papier pour chaque dossier.
 *
 * @author alexandre
 */
public enum DossierVersionPapierEnum {

	DISPONIBLE("Disponible"),

	INDISPONIBLE("Indisponible");

	private String libelle;

	DossierVersionPapierEnum(final String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(final String libelle) {
		this.libelle = libelle;
	}
}
