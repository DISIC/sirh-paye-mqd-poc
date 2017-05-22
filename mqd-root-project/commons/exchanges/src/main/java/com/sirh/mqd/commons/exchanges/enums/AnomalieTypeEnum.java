package com.sirh.mqd.commons.exchanges.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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

	public static final List<AnomalieTypeEnum> CACHE_ENUMS = Collections.unmodifiableList(Arrays.asList(values()));

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

	/**
	 * Parse le libellé dans un élément de cette énumération.
	 *
	 * @param libelle
	 *            prend comme valeurs un des libellés des énumérations.
	 */
	public static AnomalieTypeEnum enumOf(final String libelle) {
		final Iterator<AnomalieTypeEnum> iter = CACHE_ENUMS.iterator();
		while (iter.hasNext()) {
			final AnomalieTypeEnum type = iter.next();
			if (libelle.equals(type.getLibelle())) {
				return type;
			}
		}
		throw new IllegalArgumentException(
				"Impossible de convertir un élément d'AnomalieTypeEnum à partir du libellé : '" + libelle + "'");
	}
}
