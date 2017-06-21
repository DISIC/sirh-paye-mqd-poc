package com.sirh.mqd.commons.exchanges.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

/**
 * Enumération listant les différents états de disponibilité de la version
 * papier pour chaque dossier.
 *
 * @author khalil
 */
public enum DossierAffectationEnum {

	A_TRAITER(""),

	NON_AFFECTE("Non Affecté"),

	AFFECTE("Affecté");

	private static final List<String> CACHE_LIBELLES = new ArrayList<String>();

	public static final List<DossierAffectationEnum> CACHE_ENUMS = Collections
			.unmodifiableList(Arrays.asList(values()));

	private String libelle;

	DossierAffectationEnum(final String libelle) {
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
	public static DossierAffectationEnum enumOf(final String libelle) {
		final Iterator<DossierAffectationEnum> iter = CACHE_ENUMS.iterator();
		while (iter.hasNext()) {
			final DossierAffectationEnum etat = iter.next();
			if (libelle.equals(etat.getLibelle())) {
				return etat;
			}
		}
		throw new IllegalArgumentException(
				"Impossible de convertir un élément de AffectationDossierEnum à partir du libellé : '" + libelle + "'");
	}

	/**
	 * Méthode permettant de récupérer la liste des libellées pour les
	 * affectations possibles d'un dossier.
	 *
	 * @return {@link List} des libellés des affectation dossiers existants
	 */

	public static List<String> getLibelles() {
		if (CollectionUtils.isEmpty(CACHE_LIBELLES)) {
			for (final DossierAffectationEnum libelle : values()) {
				CACHE_LIBELLES.add(libelle.getLibelle());
			}
		}
		return CACHE_LIBELLES;
	}
}
