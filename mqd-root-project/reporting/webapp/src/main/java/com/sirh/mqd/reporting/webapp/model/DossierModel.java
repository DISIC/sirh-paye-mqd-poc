package com.sirh.mqd.reporting.webapp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Model d'anomalie d'un dossier à manipuler dans la partie Vue
 *
 * @author alexandre
 */
public class DossierModel implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 4531819019987861366L;

	private String payLot;

	private String nir;

	private String renoiRHMatricule;

	private String renoiRHCorpsCode;

	private String renoiRHCorpsLibelleCourt;

	private String renoiRHAffectationCode;

	private String renoiRHAffectationLibelleCourt;

	private String renoiRHNom;

	private String renoiRHPrenom;

	private Date renoiRHDateNaissance; // Format en entrée : "MM/yy"

	private String renoiRHDateNaissanceAsString; // Format en entrée : "MM/yy"

	private int nbAlertes;

	private int nbAnomalies;

	public DossierModel() {
		super();
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

	public String getRenoiRHCorpsCode() {
		return renoiRHCorpsCode;
	}

	public void setRenoiRHCorpsCode(final String renoiRHCorpsCode) {
		this.renoiRHCorpsCode = renoiRHCorpsCode;
	}

	public String getRenoiRHCorpsLibelleCourt() {
		return renoiRHCorpsLibelleCourt;
	}

	public void setRenoiRHCorpsLibelleCourt(final String renoiRHCorpsLibelleCourt) {
		this.renoiRHCorpsLibelleCourt = renoiRHCorpsLibelleCourt;
	}

	public String getRenoiRHAffectationCode() {
		return renoiRHAffectationCode;
	}

	public void setRenoiRHAffectationCode(final String renoiRHAffectationCode) {
		this.renoiRHAffectationCode = renoiRHAffectationCode;
	}

	public String getRenoiRHAffectationLibelleCourt() {
		return renoiRHAffectationLibelleCourt;
	}

	public void setRenoiRHAffectationLibelleCourt(final String renoiRHAffectationLibelleCourt) {
		this.renoiRHAffectationLibelleCourt = renoiRHAffectationLibelleCourt;
	}

	public String getRenoiRHNom() {
		return renoiRHNom;
	}

	public void setRenoiRHNom(final String renoiRHNom) {
		this.renoiRHNom = renoiRHNom;
	}

	public String getRenoiRHPrenom() {
		return renoiRHPrenom;
	}

	public void setRenoiRHPrenom(final String renoiRHPrenom) {
		this.renoiRHPrenom = renoiRHPrenom;
	}

	public Date getRenoiRHDateNaissance() {
		return renoiRHDateNaissance;
	}

	public void setRenoiRHDateNaissance(final Date renoiRHDateNaissance) {
		this.renoiRHDateNaissance = renoiRHDateNaissance;
	}

	public int getNbAlertes() {
		return nbAlertes;
	}

	public void setNbAlertes(final int nbAlertes) {
		this.nbAlertes = nbAlertes;
	}

	public int getNbAnomalies() {
		return nbAnomalies;
	}

	public void setNbAnomalies(final int nbAnomalies) {
		this.nbAnomalies = nbAnomalies;
	}

	public String getRenoiRHDateNaissanceAsString() {
		return renoiRHDateNaissanceAsString;
	}

	public void setRenoiRHDateNaissanceAsString(final String renoiRHDateNaissanceAsString) {
		this.renoiRHDateNaissanceAsString = renoiRHDateNaissanceAsString;
	}

	public String getNir() {
		return nir;
	}

	public void setNir(final String nir) {
		this.nir = nir;
	}
}
