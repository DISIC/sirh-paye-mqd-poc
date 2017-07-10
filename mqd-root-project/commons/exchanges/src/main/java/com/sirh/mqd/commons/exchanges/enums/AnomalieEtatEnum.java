package com.sirh.mqd.commons.exchanges.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

/**
 * Enumération listant les différents états de correction existants d'une
 * anomalie .
 *
 * @author alexandre
 */
public enum AnomalieEtatEnum {

	ETAT_ANOMALIE_1_A_TRAITER(""),

	ETAT_ANOMALIE_2_EN_COURS("Correction en cours"),

	ETAT_ANOMALIE_3_CORRECTION_EFFECTUEE("Correction effectuée"),

	ETAT_ANOMALIE_4_DEMANDE_ASSISTANCE("Demande d'assistance"),

	ETAT_ANOMALIE_5_AUCUNE_ACTION_NECESSAIRE("Aucune action nécessaire");

	private static final List<String> CACHE_LIBELLES = new ArrayList<String>();

	private static final List<String> CACHE_LIBELLES_ANOMALIES_DEJA_TRAITEES = new ArrayList<String>();

	public static final List<AnomalieEtatEnum> CACHE_ENUMS = Collections.unmodifiableList(Arrays.asList(values()));

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

	/**
	 * Parse le libellé dans un élément de cette énumération.
	 *
	 * @param libelle
	 *            prend comme valeurs un des libellés des énumérations.
	 */
	public static AnomalieEtatEnum enumOf(final String libelle) {
		final Iterator<AnomalieEtatEnum> iter = CACHE_ENUMS.iterator();
		while (iter.hasNext()) {
			final AnomalieEtatEnum etat = iter.next();
			if (libelle.equals(etat.getLibelle())) {
				return etat;
			}
		}
		throw new IllegalArgumentException(
				"Impossible de convertir un élément d'AnomalieEtatEnum à partir du libellé : '" + libelle + "'");
	}

	/**
	 * Méthode permettant de récupérer la liste des libellées pour les états
	 * possibles d'une correction.
	 *
	 * @return {@link List} des libellés d'états de correction existants
	 */
	public static List<String> getLibelles() {
		if (CollectionUtils.isEmpty(CACHE_LIBELLES)) {
			for (final AnomalieEtatEnum libelle : values()) {
				CACHE_LIBELLES.add(libelle.getLibelle());
			}
		}
		return CACHE_LIBELLES;
	}

	public static List<String> getLibellesAnomaliesDejaTraitees() {
		if (CollectionUtils.isEmpty(CACHE_LIBELLES_ANOMALIES_DEJA_TRAITEES)) {
			for (final AnomalieEtatEnum libelle : values()) {
				if (!libelle.getLibelle().equals(ETAT_ANOMALIE_1_A_TRAITER.getLibelle())) {
					CACHE_LIBELLES_ANOMALIES_DEJA_TRAITEES.add(libelle.getLibelle());
				}
			}
		}
		return CACHE_LIBELLES_ANOMALIES_DEJA_TRAITEES;
	}
}
