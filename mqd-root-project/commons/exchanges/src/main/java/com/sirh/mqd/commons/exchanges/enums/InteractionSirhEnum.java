package com.sirh.mqd.commons.exchanges.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Enumération listant les noms des SIRH
 *
 * @author alexandre
 */
public enum InteractionSirhEnum {

	PAY("pay"),

	MSO("mso"),

	DGAC("dgac");

	private String libelle;

	public static final List<InteractionSirhEnum> CACHE_ENUMS = Collections.unmodifiableList(Arrays.asList(values()));

	InteractionSirhEnum(final String libelle) {
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
	public static InteractionSirhEnum enumOf(final String libelle) {
		final Iterator<InteractionSirhEnum> iter = CACHE_ENUMS.iterator();
		while (iter.hasNext()) {
			final InteractionSirhEnum sirh = iter.next();
			if (libelle.equalsIgnoreCase(sirh.getLibelle())) {
				return sirh;
			}
		}
		return null;
	}
}
