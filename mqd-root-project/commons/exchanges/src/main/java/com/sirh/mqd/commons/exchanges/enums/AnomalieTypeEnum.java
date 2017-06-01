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

	NOM("Nom", AnomaliePerimetreEnum.ETAT_CIVIL),

	PRENOM("Prénom", AnomaliePerimetreEnum.ETAT_CIVIL),

	DATE_NAISSANCE("Date de naissance", AnomaliePerimetreEnum.ETAT_CIVIL),

	CIVILITE("Civilité", AnomaliePerimetreEnum.ETAT_CIVIL),

	SEXE("Sexe", AnomaliePerimetreEnum.ETAT_CIVIL),

	NB_POINTS("Nombre de points", AnomaliePerimetreEnum.NBI),

	INDICE("Indice", AnomaliePerimetreEnum.CARRIERE),

	GRADE("Grade", AnomaliePerimetreEnum.CARRIERE),

	NIVEAU_ECHELON("Niveau d'échelon", AnomaliePerimetreEnum.CARRIERE);

	public static final List<AnomalieTypeEnum> CACHE_ENUMS = Collections.unmodifiableList(Arrays.asList(values()));

	private String libelle;

	private AnomaliePerimetreEnum perimetre;

	AnomalieTypeEnum(final String libelle, final AnomaliePerimetreEnum perimetre) {
		this.libelle = libelle;
		this.perimetre = perimetre;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(final String libelle) {
		this.libelle = libelle;
	}

	public AnomaliePerimetreEnum getPerimetre() {
		return perimetre;
	}

	public void setPerimetre(final AnomaliePerimetreEnum perimetre) {
		this.perimetre = perimetre;
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
