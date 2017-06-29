package com.sirh.mqd.reporting.webapp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Model de synthese à manipuler dans la partie Vue
 *
 * @author Maxime
 */
public class SyntheseModel implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -5160008072948613800L;

	private String nomUsage;

	private String nomNaissance;

	private String prenom;

	private Integer sexe;

	private String nationalite;

	private String nir;

	private Date dateCertificatoinNir;

	private Date dateNaissance;

	private String villeNaissance;

	private String adresse;

	private String cpVille;

	private String situationFamiliale;

	private String contactTel;

	private String contactMail;

	private String matriculeAncienSIRH;

	private String idClassotheque;

	public SyntheseModel() {
		super();
	}

	public String getNomUsage() {
		return nomUsage;
	}

	public void setNomUsage(final String nomUsage) {
		this.nomUsage = nomUsage;
	}

	public String getNomNaissance() {
		return nomNaissance;
	}

	public void setNomNaissance(final String nomNaissance) {
		this.nomNaissance = nomNaissance;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(final String prenom) {
		this.prenom = prenom;
	}

	public Integer getSexe() {
		return sexe;
	}

	public void setSexe(final Integer sexe) {
		this.sexe = sexe;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(final String nationalite) {
		this.nationalite = nationalite;
	}

	public String getNir() {
		return nir;
	}

	public void setNir(final String nir) {
		this.nir = nir;
	}

	public Date getDateCertificatoinNir() {
		return dateCertificatoinNir;
	}

	public void setDateCertificatoinNir(final Date dateCertificatoinNir) {
		this.dateCertificatoinNir = dateCertificatoinNir;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(final Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getVilleNaissance() {
		return villeNaissance;
	}

	public void setVilleNaissance(final String villeNaissance) {
		this.villeNaissance = villeNaissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(final String adresse) {
		this.adresse = adresse;
	}

	public String getCpVille() {
		return cpVille;
	}

	public void setCpVille(final String cpVille) {
		this.cpVille = cpVille;
	}

	public String getSituationFamiliale() {
		return situationFamiliale;
	}

	public void setSituationFamiliale(final String situationFamiliale) {
		this.situationFamiliale = situationFamiliale;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(final String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail(final String contactMail) {
		this.contactMail = contactMail;
	}

	public String getMatriculeAncienSIRH() {
		return matriculeAncienSIRH;
	}

	public void setMatriculeAncienSIRH(final String matriculeAncienSIRH) {
		this.matriculeAncienSIRH = matriculeAncienSIRH;
	}

	public String getIdClassotheque() {
		return idClassotheque;
	}

	public void setIdClassotheque(final String idClassotheque) {
		this.idClassotheque = idClassotheque;
	}
}