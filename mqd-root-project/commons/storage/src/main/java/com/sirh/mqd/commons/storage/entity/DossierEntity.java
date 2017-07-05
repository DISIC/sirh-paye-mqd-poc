package com.sirh.mqd.commons.storage.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.sirh.mqd.commons.storage.constantes.DossierConstantes;

/**
 * Entité des dossiers
 *
 * @author alexandre
 */
@Document(collection = DossierConstantes.COLLECTION_NAME)
@CompoundIndexes({
		@CompoundIndex(name = "index_dossier", def = "{'" + DossierConstantes.COLONNE_PAY_LOT + "' : 1, '"
				+ DossierConstantes.COLONNE_MATRICULE + "': 1}"),
		@CompoundIndex(name = "index_user_profile_mse", def = "{'" + DossierConstantes.COLONNE_PAY_LOT + "' : 1, '"
				+ DossierConstantes.COLONNE_CORPS_CODE + "': 1}") })
public class DossierEntity {

	@Id
	private String id;

	@Field(DossierConstantes.COLONNE_MINISTERE)
	private Integer ministere;

	@Field(DossierConstantes.COLONNE_PAY_CLE)
	private String payCle;

	@Field(DossierConstantes.COLONNE_DOSSIER_NUMERO)
	private Integer dossierNumero;

	@Field(DossierConstantes.COLONNE_DI_GESTIONNAIRE)
	private Integer diGestionnaire;

	@Field(DossierConstantes.COLONNE_ADMIN_CODE)
	private String adminCode;

	@Field(DossierConstantes.COLONNE_ADMIN_CODE_DEPARTEMENT)
	private String adminCodeDepartement;

	@Indexed
	@Field(DossierConstantes.COLONNE_PAY_LOT)
	private String payLot;

	@Indexed
	@Field(DossierConstantes.COLONNE_MATRICULE)
	private String renoiRHMatricule;

	@Indexed
	@Field(DossierConstantes.COLONNE_CORPS_CODE)
	private String renoiRHCorpsCode;

	@Field(DossierConstantes.COLONNE_CORPS_LIBELLE_COURT)
	private String renoiRHCorpsLibelleCourt;

	@Field(DossierConstantes.COLONNE_GRADE_CODE)
	private String renoiRHGradeCode;

	@Field(DossierConstantes.COLONNE_GRADE_LIBELLE_COURT)
	private String renoiRHGradeLibelleCourt;

	@Indexed
	@Field(DossierConstantes.COLONNE_AFFECTATION_CODE)
	private String renoiRHAffectationCode;

	@Field(DossierConstantes.COLONNE_AFFECTATION_LIBELLE_COURT)
	private String renoiRHAffectationLibelleCourt;

	@Field(DossierConstantes.COLONNE_MODALITE_SERVICE_CODE)
	private String renoiRHModaliteServiceCode;

	@Field(DossierConstantes.COLONNE_MODALITE_SERVICE_LIBELLE_LONG)
	private String renoiRHModaliteServiceLibelleLong;

	@Field(DossierConstantes.COLONNE_CIVILITE)
	private Integer renoiRHCivilite;

	@Field(DossierConstantes.COLONNE_NOM)
	private String renoiRHNom;

	@Field(DossierConstantes.COLONNE_PRENOM)
	private String renoiRHPrenom;

	@Field(DossierConstantes.COLONNE_SEXE)
	private Integer renoiRHSexe;

	@Field(DossierConstantes.COLONNE_DATE_NAISSANCE)
	private Date renoiRHDateNaissance; // Format : "01/MM/yyyy"

	@Field(DossierConstantes.COLONNE_PAY_DATE_CERTIFICATION)
	private Date renoiRHDateCertification; // Format : "dd/MM/yyyy"

	@Field(DossierConstantes.COLONNE_DATES_MOUVEMENTS_CARRIERE)
	private List<Date> mouvementsCarriere; // Format : "dd/MM/yyyy"

	@Field(DossierConstantes.COLONNE_NIR)
	private String nir;

	@Field(DossierConstantes.COLONNE_TG_CODE)
	private String tgCode;

	@Field(DossierConstantes.COLONNE_TEMOIN_DOSSIER_PRINCIPAL)
	private Integer temoinDossierPrincipal;

	@Indexed
	@Field(DossierConstantes.COLONNE_GESTIONNAIRE_CODE)
	private String gestionnaireCode;

	@Transient
	private int nbAlertes;

	@Transient
	private int nbAnomalies;

	public DossierEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Integer getMinistere() {
		return ministere;
	}

	public void setMinistere(final Integer ministere) {
		this.ministere = ministere;
	}

	public String getPayCle() {
		return payCle;
	}

	public void setPayCle(final String payCle) {
		this.payCle = payCle;
	}

	public Integer getDossierNumero() {
		return dossierNumero;
	}

	public void setDossierNumero(final Integer dossierNumero) {
		this.dossierNumero = dossierNumero;
	}

	public Integer getDiGestionnaire() {
		return diGestionnaire;
	}

	public void setDiGestionnaire(final Integer diGestionnaire) {
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

	public Date getRenoiRHDateCertification() {
		return renoiRHDateCertification;
	}

	public void setRenoiRHDateCertification(final Date renoiRHDateCertification) {
		this.renoiRHDateCertification = renoiRHDateCertification;
	}

	public List<Date> getMouvementsCarriere() {
		return mouvementsCarriere;
	}

	public void setMouvementsCarriere(final List<Date> mouvementsCarriere) {
		this.mouvementsCarriere = mouvementsCarriere;
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

	public String getNir() {
		return nir;
	}

	public void setNir(final String nir) {
		this.nir = nir;
	}

	public String getTgCode() {
		return tgCode;
	}

	public void setTgCode(final String tgCode) {
		this.tgCode = tgCode;
	}

	public Integer getTemoinDossierPrincipal() {
		return temoinDossierPrincipal;
	}

	public void setTemoinDossierPrincipal(final Integer temoinDossierPrincipal) {
		this.temoinDossierPrincipal = temoinDossierPrincipal;
	}

	public String getGestionnaireCode() {
		return gestionnaireCode;
	}

	public void setGestionnaireCode(final String gestionnaireCode) {
		this.gestionnaireCode = gestionnaireCode;
	}
}
