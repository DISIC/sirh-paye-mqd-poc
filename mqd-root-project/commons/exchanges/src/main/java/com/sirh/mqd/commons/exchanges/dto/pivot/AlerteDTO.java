package com.sirh.mqd.commons.exchanges.dto.pivot;

import java.util.Date;

import com.sirh.mqd.commons.exchanges.enums.AlerteEtatEnum;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;

/**
 * DTO permettant de manipuler les alertes d'un dossier
 *
 * @author alexandre
 */
public class AlerteDTO {

	private String payLot;

	private String matricule;

	private AnomalieTypeEnum type;

	private Date dateEcheance;

	private AlerteEtatEnum etatCorrection;

	private String responsableLogin;

	private String responsableNom;

	private String responsablePrenom;

	private Date dateModification;

	private String donnee;

	public AlerteDTO() {
		super();
	}

	public String getPayLot() {
		return payLot;
	}

	public void setPayLot(final String payLot) {
		this.payLot = payLot;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(final String matricule) {
		this.matricule = matricule;
	}

	public AnomalieTypeEnum getType() {
		return type;
	}

	public void setType(final AnomalieTypeEnum type) {
		this.type = type;
	}

	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(final Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public AlerteEtatEnum getEtatCorrection() {
		return etatCorrection;
	}

	public void setEtatCorrection(final AlerteEtatEnum etatCorrection) {
		this.etatCorrection = etatCorrection;
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
		this.dateModification = dateModification;
	}

	public String getDonnee() {
		return donnee;
	}

	public void setDonnee(final String donnee) {
		this.donnee = donnee;
	}
}
