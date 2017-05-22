package com.sirh.mqd.commons.exchanges.dto.pivot;

import com.sirh.mqd.commons.exchanges.enums.AnomaliePerimetreEnum;

/**
 * DTO correspondant à la synthèse des données de chaque dossier par périmètre
 *
 * @author alexandre
 */
public class DetailDTO {

	private AnomaliePerimetreEnum perimetre;

	private int nombreAnomalies;

	private DifferenceDTO donnees;

	public DetailDTO() {
		super();
	}

	public AnomaliePerimetreEnum getPerimetre() {
		return perimetre;
	}

	public void setPerimetre(final AnomaliePerimetreEnum perimetre) {
		this.perimetre = perimetre;
	}

	public int getNombreAnomalies() {
		return nombreAnomalies;
	}

	public void setNombreAnomalies(final int nombreAnomalies) {
		this.nombreAnomalies = nombreAnomalies;
	}

	public DifferenceDTO getDonnees() {
		return donnees;
	}

	public void setDonnees(final DifferenceDTO donnees) {
		this.donnees = donnees;
	}
}
