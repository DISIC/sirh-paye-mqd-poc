package com.sirh.mqd.commons.exchanges.dto.pivot;

/**
 * DTO permettant de regrouper les données par ensembles de données PAY +
 * données des différents SIRH.
 *
 * @author alexandre
 */
public class DifferenceDTO {

	private String donneePAY;

	private String donneeGA;

	public DifferenceDTO() {
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
