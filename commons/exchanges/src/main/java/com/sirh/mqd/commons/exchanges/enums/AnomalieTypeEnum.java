package com.sirh.mqd.commons.exchanges.enums;

/**
 * Enumération listant les différents types d'anomalie existants.<br/>
 * (1 type ~= 1 champ d'un fichier PAY en entrée)
 *
 * @author alexandre
 */
public enum AnomalieTypeEnum {

	NOM("Nom"),

	PRENOM("Prénom"),

	DATE_NAISSANCE("Date de naissance"),

	CIVILITE("Civilité"),

	SEXE("Sexe");

	private String libelle;

	AnomalieTypeEnum(final String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(final String libelle) {
		this.libelle = libelle;
	}
}
