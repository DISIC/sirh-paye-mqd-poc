package com.sirh.mqd.commons.exchanges.enums;

/**
 * Enumération listant les différents périmètres d'anomalie existants.<br/>
 * (1 périmètre ~= 1 fichier PAY en entrée)
 *
 * @author alexandre
 */
public enum AnomaliePerimetreEnum {

	ABSENCE("Absence"),

	ADRESSE("Adresse"),

	CARRIERE("Carrière"),

	COORDONNEE_BANCAIRE("Coordonnées bancaires"),

	ENFANT("Enfants"),

	ETAT_CIVIL("Etat civil"),

	NBI("NBI"),

	POSITION("Position"),

	TEMPS_TRAVAIL("Temps de travail");

	private String libelle;

	AnomaliePerimetreEnum(final String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(final String libelle) {
		this.libelle = libelle;
	}
}
