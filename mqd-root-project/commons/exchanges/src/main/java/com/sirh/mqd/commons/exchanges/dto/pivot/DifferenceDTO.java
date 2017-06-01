package com.sirh.mqd.commons.exchanges.dto.pivot;

/**
 * DTO permettant de regrouper les données par ensembles de données PAY +
 * données des différents SIRH.
 *
 * @author alexandre
 */
public class DifferenceDTO {

	private String donneePAY;

	private boolean erreurDonneePAY;

	private String donneeGA;

	private boolean erreurDonneeGA;

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

	public boolean isErreurDonneePAY() {
		return erreurDonneePAY;
	}

	public void setErreurDonneePAY(final boolean erreurDonneePAY) {
		this.erreurDonneePAY = erreurDonneePAY;
	}

	public boolean isErreurDonneeGA() {
		return erreurDonneeGA;
	}

	public void setErreurDonneeGA(final boolean erreurDonneeGA) {
		this.erreurDonneeGA = erreurDonneeGA;
	}
}
