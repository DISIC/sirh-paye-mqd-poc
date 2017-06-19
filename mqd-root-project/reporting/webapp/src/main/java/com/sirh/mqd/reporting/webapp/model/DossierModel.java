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

	private int ministere;

	private int dossierNumero;

	private int diGestionnaire;

	private String adminCode;

	private String adminCodeDepartement;

	private String payLot;

	private String renoiRHMatricule;

	private String renoiRHCorpsCode;

	private String renoiRHCorpsLibelleCourt;

	private String renoiRHGradeCode;

	private String renoiRHGradeLibelleCourt;

	private String renoiRHAffectationCode;

	private String renoiRHAffectationLibelleCourt;

	private String renoiRHModaliteServiceCode;

	private String renoiRHModaliteServiceLibelleLong;

	private Date renoiRHDateCertification; // Format en entrée : "dd/MM/yyyy"

	private String renoiRHCivilite;

	private String renoiRHNom;

	private String renoiRHPrenom;

	private String renoiRHSexe;

	private Date renoiRHDateNaissance; // Format en entrée : "MM/yy"

	private String renoiRHDateNaissanceAsString; // Format en entrée : "MM/yy"

	private int nbAlertes;

	private int nbAnomalies;

	public DossierModel() {
		super();
	}

	public int getMinistere() {
		return ministere;
	}

	public void setMinistere(final int ministere) {
		this.ministere = ministere;
	}

	public int getDossierNumero() {
		return dossierNumero;
	}

	public void setDossierNumero(final int dossierNumero) {
		this.dossierNumero = dossierNumero;
	}

	public int getDiGestionnaire() {
		return diGestionnaire;
	}

	public void setDiGestionnaire(final int diGestionnaire) {
		this.diGestionnaire = diGestionnaire;
	}

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(final String adminCode) {
		this.adminCode = adminCode;
	}

	public String getAdminCodeDepartement() {
		return adminCodeDepartement;
	}

	public void setAdminCodeDepartement(final String adminCodeDepartement) {
		this.adminCodeDepartement = adminCodeDepartement;
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

	public String getRenoiRHGradeCode() {
		return renoiRHGradeCode;
	}

	public void setRenoiRHGradeCode(final String renoiRHGradeCode) {
		this.renoiRHGradeCode = renoiRHGradeCode;
	}

	public String getRenoiRHGradeLibelleCourt() {
		return renoiRHGradeLibelleCourt;
	}

	public void setRenoiRHGradeLibelleCourt(final String renoiRHGradeLibelleCourt) {
		this.renoiRHGradeLibelleCourt = renoiRHGradeLibelleCourt;
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

	public Date getRenoiRHDateCertification() {
		return renoiRHDateCertification;
	}

	public void setRenoiRHDateCertification(final Date renoiRHDateCertification) {
		this.renoiRHDateCertification = renoiRHDateCertification;
	}

	public String getRenoiRHCivilite() {
		return renoiRHCivilite;
	}

	public void setRenoiRHCivilite(final String renoiRHCivilite) {
		this.renoiRHCivilite = renoiRHCivilite;
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

	public String getRenoiRHSexe() {
		return renoiRHSexe;
	}

	public void setRenoiRHSexe(final String renoiRHSexe) {
		this.renoiRHSexe = renoiRHSexe;
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

	public String getRenoiRHModaliteServiceCode() {
		return renoiRHModaliteServiceCode;
	}

	public void setRenoiRHModaliteServiceCode(final String renoiRHModaliteServiceCode) {
		this.renoiRHModaliteServiceCode = renoiRHModaliteServiceCode;
	}

	public String getRenoiRHModaliteServiceLibelleLong() {
		return renoiRHModaliteServiceLibelleLong;
	}

	public void setRenoiRHModaliteServiceLibelleLong(final String renoiRHModaliteServiceLibelleLong) {
		this.renoiRHModaliteServiceLibelleLong = renoiRHModaliteServiceLibelleLong;
	}
}
