package com.sirh.mqd.reporting.webapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import com.sirh.mqd.commons.exchanges.enums.AnomalieEtatEnum;
import com.sirh.mqd.commons.utils.DateUtils;

/**
 * Model d'anomalie d'un dossier à manipuler dans la partie Vue
 *
 * @author alexandre
 */
public class AnomalieModel implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -1730791893901496946L;

	private String perimetre;

	private String type;

	private String donneesGA;

	private String donneesPAY;

	private String modeOperatoire;

	private Date dateEcheance;

	private AnomalieEtatEnum etatCorrection;

	private String responsableLogin;

	private String responsableNom;

	private String responsablePrenom;

	private Date dateModification;

	private boolean anomalieReouverte;

	private List<AnomalieEtatEnum> listeEtatsCorrection;

	public AnomalieModel() {
		super();
	}

	public String getPerimetre() {
		return perimetre;
	}

	public void setPerimetre(final String perimetre) {
		this.perimetre = perimetre;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getDonneesGA() {
		return donneesGA;
	}

	public void setDonneesGA(final String donneesGA) {
		this.donneesGA = donneesGA;
	}

	public String getDonneesPAY() {
		return donneesPAY;
	}

	public void setDonneesPAY(final String donneesPAY) {
		this.donneesPAY = donneesPAY;
	}

	public String getModeOperatoire() {
		return modeOperatoire;
	}

	public void setModeOperatoire(final String modeOperatoire) {
		this.modeOperatoire = modeOperatoire;
	}

	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(final Date dateEcheance) {
		this.dateEcheance = DateUtils.clonerDate(dateEcheance);
	}

	public String getResponsableLogin() {
		return responsableLogin;
	}

	public void setResponsableLogin(final String responsableLogin) {
		this.responsableLogin = responsableLogin;
	}

	public String getResponsableNom() {
		return responsableNom.toUpperCase();
	}

	public void setResponsableNom(final String responsableNom) {
		this.responsableNom = responsableNom;
	}

	public String getResponsablePrenom() {
		return WordUtils.capitalizeFully(responsablePrenom, new char[] { '-', ' ', '\'' });
	}

	public void setResponsablePrenom(final String responsablePrenom) {
		this.responsablePrenom = responsablePrenom;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(final Date dateModification) {
		this.dateModification = DateUtils.clonerDate(dateModification);
	}

	public boolean isAnomalieReouverte() {
		return anomalieReouverte;
	}

	public void setAnomalieReouverte(final boolean anomalieReouverte) {
		this.anomalieReouverte = anomalieReouverte;
	}

	public AnomalieEtatEnum getEtatCorrection() {
		return etatCorrection;
	}

	public void setEtatCorrection(final AnomalieEtatEnum etatCorrection) {
		this.etatCorrection = etatCorrection;
	}

	public List<AnomalieEtatEnum> getListeEtatsCorrection() {
		return listeEtatsCorrection;
	}

	public void setListeEtatsCorrection(final List<AnomalieEtatEnum> listeEtatsCorrection) {
		this.listeEtatsCorrection = listeEtatsCorrection;
	}
}
