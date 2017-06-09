package com.sirh.mqd.commons.exchanges.dto.pivot;

import java.util.Date;

import com.sirh.mqd.commons.exchanges.enums.AnomalieEtatEnum;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.utils.DateUtils;

/**
 * DTO permettant de manipuler les anomalies d'un dossier
 *
 * @author alexandre
 */
public class ComparaisonDTO {

	private String payLot;

	private String renoiRHMatricule;

	private AnomalieTypeEnum type;

	private String modeOperatoire;

	private Date dateEcheance;

	private Date dateCloture;

	private AnomalieEtatEnum etatCorrection;

	private boolean anomalieReouverte;

	private boolean anomalieDonnees;

	private String responsableLogin;

	private String responsableNom;

	private String responsablePrenom;

	private Date dateModification;

	private DifferenceDTO donnees;

	public ComparaisonDTO() {
		super();
		this.donnees = new DifferenceDTO();
	}

	public String getPayLot() {
		return payLot;
	}

	public void setPayLot(final String payLot) {
		this.payLot = payLot;
	}

	public String getRenoiRHMatricule() {
		return renoiRHMatricule;
	}

	public void setRenoiRHMatricule(final String renoiRHMatricule) {
		this.renoiRHMatricule = renoiRHMatricule;
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

	public Date getDateCloture() {
		return dateCloture;
	}

	public void setDateCloture(final Date dateCloture) {
		this.dateCloture = DateUtils.clonerDate(dateCloture);
	}

	public AnomalieEtatEnum getEtatCorrection() {
		return etatCorrection;
	}

	public void setEtatCorrection(final AnomalieEtatEnum etatCorrection) {
		this.etatCorrection = etatCorrection;
	}

	public AnomalieTypeEnum getType() {
		return type;
	}

	public void setType(final AnomalieTypeEnum type) {
		this.type = type;
	}

	public DifferenceDTO getDonnees() {
		return donnees;
	}

	public void setDonnees(final DifferenceDTO donnees) {
		this.donnees = donnees;
	}

	public boolean isAnomalieReouverte() {
		return anomalieReouverte;
	}

	public void setAnomalieReouverte(final boolean anomalieReouverte) {
		this.anomalieReouverte = anomalieReouverte;
	}

	public boolean isAnomalieDonnees() {
		return anomalieDonnees;
	}

	public void setAnomalieDonnees(final boolean anomalieDonnees) {
		this.anomalieDonnees = anomalieDonnees;
	}

	public String getResponsableLogin() {
		return responsableLogin;
	}

	public void setResponsableLogin(final String responsableLogin) {
		this.responsableLogin = responsableLogin;
	}

	public String getResponsableNom() {
		return responsableNom;
	}

	public void setResponsableNom(final String responsableNom) {
		this.responsableNom = responsableNom;
	}

	public String getResponsablePrenom() {
		return responsablePrenom;
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
}
