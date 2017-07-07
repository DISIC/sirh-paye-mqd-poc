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

	ETAT_CIVIL_NOM("Nom", AnomaliePerimetreEnum.ETAT_CIVIL),

	ETAT_CIVIL_PRENOM("Prénom", AnomaliePerimetreEnum.ETAT_CIVIL),

	ETAT_CIVIL_INSEE("INSEE", AnomaliePerimetreEnum.ETAT_CIVIL),

	ETAT_CIVIL_DATE_NAISSANCE("Date de naissance", AnomaliePerimetreEnum.ETAT_CIVIL),

	ETAT_CIVIL_CIVILITE("Civilité", AnomaliePerimetreEnum.ETAT_CIVIL),

	ETAT_CIVIL_SEXE("Sexe", AnomaliePerimetreEnum.ETAT_CIVIL),

	NBI_NB_POINTS("Nombre de points", AnomaliePerimetreEnum.NBI),

	ABSENCE_ABSENCE("Absence", AnomaliePerimetreEnum.ABSENCE),

	ADRESSE_VOIE("Voie", AnomaliePerimetreEnum.ADRESSE),

	ADRESSE_NUMERO_VOIE("Numéro de voie", AnomaliePerimetreEnum.ADRESSE),

	ADRESSE_COMPLEMENT_NUMERO_VOIE("Bis/Ter", AnomaliePerimetreEnum.ADRESSE),

	ADRESSE_TYPE_VOIE("Type de voie", AnomaliePerimetreEnum.ADRESSE),

	ADRESSE_NOM_VOIE("Nom de voie", AnomaliePerimetreEnum.ADRESSE),

	ADRESSE_CODE_POSTAL("Code postal", AnomaliePerimetreEnum.ADRESSE),

	ADRESSE_CODE_PAYS("Code pays", AnomaliePerimetreEnum.ADRESSE),

	ADRESSE_COMMUNE("Commune", AnomaliePerimetreEnum.ADRESSE),

	ADRESSE_COMPLEMENT_ADRESSE("Complément d'adresse", AnomaliePerimetreEnum.ADRESSE),

	COORDONNEE_BANCAIRE_MODE_PAIEMENT("Mode de paiement", AnomaliePerimetreEnum.COORDONNEE_BANCAIRE),

	COORDONNEE_BANCAIRE_IBAN("IBAN", AnomaliePerimetreEnum.COORDONNEE_BANCAIRE),

	COORDONNEE_BANCAIRE_BIC_SWIFT("BIC/SWIFT", AnomaliePerimetreEnum.COORDONNEE_BANCAIRE),

	ENFANT_NB_ENFANTS("Nombre d'enfants", AnomaliePerimetreEnum.ENFANT),

	POSITION_DATE_FIN_FONCTION("Date de fin de fonction", AnomaliePerimetreEnum.POSITION),

	TEMPS_TRAVAIL_MODALITE("Modalité", AnomaliePerimetreEnum.TEMPS_TRAVAIL),

	CARRIERE_INDICE("Indice", AnomaliePerimetreEnum.CARRIERE),

	CARRIERE_GRADE("Grade", AnomaliePerimetreEnum.CARRIERE),

	CARRIERE_NIVEAU_ECHELON("Niveau d'échelon", AnomaliePerimetreEnum.CARRIERE),

	PENSION_PENSION("Pension", AnomaliePerimetreEnum.PENSION),

	TG_NUMERO_DOSSIER("Numéro de dossier", AnomaliePerimetreEnum.TG);

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
