package com.sirh.mqd.commons.exchanges.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

/**
 * Enumération listant les différents états de correction existants d'une
 * alerte.
 *
 * @author alexandre
 */
public enum AlerteEtatEnum {

	A_TRAITER(""),

	EN_COURS("Correction en cours"),

	CORRECTION_EFFECTUEE("Correction effectuée"),

	DEMANDE_ASSISTANCE("Demande d'assistance"),

	AUCUNE_ACTION_NECESSAIRE("Aucune action nécessaire");

	private static final List<AlerteEtatEnum> CACHE_ENUMS_DEJA_TRAITEES = new ArrayList<AlerteEtatEnum>();

	public static final List<AlerteEtatEnum> CACHE_ENUMS = Collections.unmodifiableList(Arrays.asList(values()));

	private String libelle;

	AlerteEtatEnum(final String libelle) {
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
	public static AlerteEtatEnum enumOf(final String libelle) {
		final Iterator<AlerteEtatEnum> iter = CACHE_ENUMS.iterator();
		while (iter.hasNext()) {
			final AlerteEtatEnum etat = iter.next();
			if (libelle.equals(etat.getLibelle())) {
				return etat;
			}
		}
		throw new IllegalArgumentException(
				"Impossible de convertir un élément d'AlerteEtatEnum à partir du libellé : '" + libelle + "'");
	}

	/**
	 * Méthode permettant de récupérer la liste des états possibles d'une
	 * alerte.
	 *
	 * @return {@link List} des états de correction existants
	 */
	public static List<AlerteEtatEnum> getEtatsAlerte() {
		return CACHE_ENUMS;
	}

	/**
	 * Méthode permettant de récupérer la liste des états possibles d'une alerte
	 * en dehors de l'état {@link AlerteEtatEnum.A_TRAITER}.
	 *
	 * @return {@link List} des états existants sauf
	 *         {@link AlerteEtatEnum.A_TRAITER}
	 */
	public static List<AlerteEtatEnum> getEtatsAlerteDejaTraites() {
		if (CollectionUtils.isEmpty(CACHE_ENUMS_DEJA_TRAITEES)) {
			for (final AlerteEtatEnum libelle : values()) {
				if (!libelle.equals(A_TRAITER)) {
					CACHE_ENUMS_DEJA_TRAITEES.add(libelle);
				}
			}
		}
		return CACHE_ENUMS_DEJA_TRAITEES;
	}
}
