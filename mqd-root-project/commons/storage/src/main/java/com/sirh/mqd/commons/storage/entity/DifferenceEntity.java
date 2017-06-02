package com.sirh.mqd.commons.storage.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import com.sirh.mqd.commons.storage.constantes.ComparaisonConstantes;

/**
 * Entité des données de synthèse issues des différentes source en entrée (SIRH,
 * PAY, etc...)
 *
 * @author alexandre
 */
public class DifferenceEntity {

	@Field(ComparaisonConstantes.COLONNE_DONNEES_PAY)
	private String donneePAY;

	@Field(ComparaisonConstantes.COLONNE_DONNEES_GA)
	private String donneeGA;

	public DifferenceEntity() {
		super();
	}

	public String getDonneePAY() {
		return donneePAY;
	}

	public void setDonneePAY(final String donneePAY) {
		this.donneePAY = donneePAY;
	}

	public String getDonneeGA() {
		return donneeGA;
	}

	public void setDonneeGA(final String donneeGA) {
		this.donneeGA = donneeGA;
	}
}
