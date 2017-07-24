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

	private String id;

	private String payLot;

	private String matricule;

	private AnomalieTypeEnum type;

	private String valeur;

	private AlerteEtatEnum etat;

	private String responsableLogin;

	private String responsableNom;

	private String responsablePrenom;

	private Date dateModification;

	private Date dateEcheance;

	private Date dateCloture;

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

	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(final Date dateEcheance) {
		this.dateEcheance = dateEcheance;
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

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(final String valeur) {
		this.valeur = valeur;
	}

	public AlerteEtatEnum getEtat() {
		return etat;
	}

	public void setEtat(final AlerteEtatEnum etat) {
		this.etat = etat;
	}

	public Date getDateCloture() {
		return dateCloture;
	}

	public void setDateCloture(final Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	public AnomalieTypeEnum getType() {
		return type;
	}

	public void setType(final AnomalieTypeEnum type) {
		this.type = type;
	}
}
