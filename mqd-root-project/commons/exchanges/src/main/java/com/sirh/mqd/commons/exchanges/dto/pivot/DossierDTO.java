package com.sirh.mqd.commons.exchanges.dto.pivot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO correspondant au dossier d'un gestionnaire
 *
 * @author alexandre
 */
public class DossierDTO {

	private int ministere;

	private String payCle;

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

	private Integer renoiRHCivilite;

	private String renoiRHNom;

	private String renoiRHPrenom;

	private Integer renoiRHSexe;

	private Date renoiRHDateNaissance; // Format en entrée : "MM/yy"

	private Date renoiRHDateCertification; // Format en entrée : "dd/MM/yyyy"

	private List<Date> mouvementsCarriere; // Format en entrée : "dd/MM/yyyy"

	private int nbAlertes;

	private int nbAnomalies;

	public DossierDTO() {
		super();
		this.mouvementsCarriere = new ArrayList<Date>();
	}

	public int getMinistere() {
		return ministere;
	}

	public void setMinistere(final int ministere) {
		this.ministere = ministere;
	}

	public String getPayCle() {
		return payCle;
	}

	public void setPayCle(final String payCle) {
		this.payCle = payCle;
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

	public Integer getRenoiRHCivilite() {
		return renoiRHCivilite;
	}

	public void setRenoiRHCivilite(final Integer renoiRHCivilite) {
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

	public Integer getRenoiRHSexe() {
		return renoiRHSexe;
	}

	public void setRenoiRHSexe(final Integer renoiRHSexe) {
		this.renoiRHSexe = renoiRHSexe;
	}

	public Date getRenoiRHDateNaissance() {
		return renoiRHDateNaissance;
	}

	public void setRenoiRHDateNaissance(final Date renoiRHDateNaissance) {
		this.renoiRHDateNaissance = renoiRHDateNaissance;
	}

	public String getRenoiRHMatricule() {
		return renoiRHMatricule;
	}

	public void setRenoiRHMatricule(final String renoiRHMatricule) {
		this.renoiRHMatricule = renoiRHMatricule;
	}

	public int getNbAlertes() {
		return nbAlertes;
	}

	public void setNbAlertes(final int nbAlertes) {
		this.nbAlertes = nbAlertes;
	}

	public List<Date> getMouvementsCarriere() {
		return mouvementsCarriere;
	}

	public void setMouvementsCarriere(final List<Date> mouvementsCarriere) {
		this.mouvementsCarriere = mouvementsCarriere.stream().collect(Collectors.toList());
	}

	public int getNbAnomalies() {
		return nbAnomalies;
	}

	public void setNbAnomalies(final int nbAnomalies) {
		this.nbAnomalies = nbAnomalies;
	}
}
