package com.sirh.mqd.commons.exchanges.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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

	TEMPS_TRAVAIL("Temps de travail"),

	PENSION("Pension"),

	TG("TG");

	public static final List<AnomaliePerimetreEnum> CACHE_ENUMS = Collections.unmodifiableList(Arrays.asList(values()));

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

	/**
	 * Parse le libellé dans un élément de cette énumération.
	 *
	 * @param libelle
	 *            prend comme valeurs un des libellés des énumérations.
	 */
	public static AnomaliePerimetreEnum enumOf(final String libelle) {
		final Iterator<AnomaliePerimetreEnum> iter = CACHE_ENUMS.iterator();
		while (iter.hasNext()) {
			final AnomaliePerimetreEnum perimetre = iter.next();
			if (libelle.equals(perimetre.getLibelle())) {
				return perimetre;
			}
		}
		throw new IllegalArgumentException(
				"Impossible de convertir un élément d'AnomaliePerimetreEnum à partir du libellé : '" + libelle + "'");
	}
}
