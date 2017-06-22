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
public enum DossierDisponibiliteEnum {

	AUCUNE_INFORMATION(""),

	DISPONIBILITE_1("Disponible dans le service"),

	DISPONIBILITE_2("Disponible dans un autre service"),

	DISPONIBILITE_3("Demandé aux archives"),

	DISPONIBILITE_4("Inacessible ou perdu");

	public static final List<DossierDisponibiliteEnum> CACHE_DISPLAYABLE_ENUMS = new ArrayList<DossierDisponibiliteEnum>();

	public static final List<DossierDisponibiliteEnum> CACHE_ENUMS = Collections
			.unmodifiableList(Arrays.asList(values()));

	private String libelle;

	DossierDisponibiliteEnum(final String libelle) {
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
	public static DossierDisponibiliteEnum enumOf(final String libelle) {
		final Iterator<DossierDisponibiliteEnum> iter = CACHE_ENUMS.iterator();
		while (iter.hasNext()) {
			final DossierDisponibiliteEnum etat = iter.next();
			if (libelle.equals(etat.getLibelle())) {
				return etat;
			}
		}
		throw new IllegalArgumentException(
				"Impossible de convertir un élément de StatutDossierEnum à partir du libellé : '" + libelle + "'");
	}

	/**
	 * Méthode permettant de récupérer la liste des libellées affichables pour
	 * les statuts possibles d'un dossier.
	 *
	 * @return {@link List} des libellés des status dossiers existants
	 */
	public static List<DossierDisponibiliteEnum> getEnumAffichables() {
		if (CollectionUtils.isEmpty(CACHE_DISPLAYABLE_ENUMS)) {
			for (final DossierDisponibiliteEnum libelle : values()) {
				if (!AUCUNE_INFORMATION.equals(libelle)) {
					CACHE_DISPLAYABLE_ENUMS.add(libelle);
				}
			}
		}
		return CACHE_DISPLAYABLE_ENUMS;
	}
}
