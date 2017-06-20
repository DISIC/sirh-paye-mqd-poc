package com.sirh.mqd.commons.storage.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

import com.sirh.mqd.commons.exchanges.enums.AnomalieEtatEnum;
import com.sirh.mqd.commons.storage.constantes.ComparaisonConstantes;

/**
 * Entité de gestion des anomalies détectées sur la donnée
 *
 * @author alexandre
 */
public class AnomalieEntity {

	@Field(ComparaisonConstantes.COLONNE_ANOMALIE_MODE_OPERATOIRE)
	private String modeOperatoire;

	@Field(ComparaisonConstantes.COLONNE_ANOMALIE_DATE_ECHEANCE)
	private Date dateEcheance;

	// @Field(ComparaisonConstantes.COLONNE_ANOMALIE_DATE_CLOTURE)
	// private Date dateCloture;

	@Field(ComparaisonConstantes.COLONNE_ANOMALIE_ETAT)
	private AnomalieEtatEnum etatCorrection;

	@Field(ComparaisonConstantes.COLONNE_ANOMALIE_RESPONSABLE_LOGIN)
	private String responsableLogin;

	@Field(ComparaisonConstantes.COLONNE_ANOMALIE_RESPONSABLE_NOM)
	private String responsableNom;

	@Field(ComparaisonConstantes.COLONNE_ANOMALIE_RESPONSABLE_PRENOM)
	private String responsablePrenom;

	@Field(ComparaisonConstantes.COLONNE_ANOMALIE_DATE_MODIFICATION)
	private Date dateModification;

	public AnomalieEntity() {
		super();
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
		this.dateEcheance = dateEcheance;
	}

	// public Date getDateCloture() {
	// return dateCloture;
	// }
	//
	// public void setDateCloture(final Date dateCloture) {
	// this.dateCloture = dateCloture;
	// }

	public AnomalieEtatEnum getEtatCorrection() {
		return etatCorrection;
	}

	public void setEtatCorrection(final AnomalieEtatEnum etatCorrection) {
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
}
