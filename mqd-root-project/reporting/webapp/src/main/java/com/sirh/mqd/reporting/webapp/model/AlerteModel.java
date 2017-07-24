package com.sirh.mqd.reporting.webapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import com.sirh.mqd.commons.exchanges.enums.AlerteEtatEnum;
import com.sirh.mqd.commons.utils.DateUtils;

/**
 * Model d'alerte d'un dossier à manipuler dans la partie Vue
 *
 * @author alexandre
 */
public class AlerteModel implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 7814970046973218320L;

	private String id;

	private String perimetre;

	private String type;

	private String valeur;

	private Date dateEcheance;

	private Date dateModification;

	private Date dateCloture;

	private String responsableLogin;

	private String responsableNom;

	private String responsablePrenom;

	private AlerteEtatEnum etat;

	private List<AlerteEtatEnum> listeEtats;

	public AlerteModel() {
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

	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(final Date dateEcheance) {
		this.dateEcheance = DateUtils.clonerDate(dateEcheance);
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(final Date dateModification) {
		this.dateModification = DateUtils.clonerDate(dateModification);
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

	public Date getDateCloture() {
		return dateCloture;
	}

	public void setDateCloture(final Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	public AlerteEtatEnum getEtat() {
		return etat;
	}

	public void setEtat(final AlerteEtatEnum etat) {
		this.etat = etat;
	}

	public List<AlerteEtatEnum> getListeEtats() {
		return listeEtats;
	}

	public void setListeEtats(final List<AlerteEtatEnum> listeEtats) {
		this.listeEtats = listeEtats;
	}
}
