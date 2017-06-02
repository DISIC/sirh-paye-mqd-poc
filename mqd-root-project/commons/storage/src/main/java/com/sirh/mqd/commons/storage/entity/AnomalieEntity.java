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

	@Field(ComparaisonConstantes.COLONNE_ANOMALIE_DATE_CLOTURE)
	private Date dateCloture;

	@Field(ComparaisonConstantes.COLONNE_ANOMALIE_ETAT)
	private AnomalieEtatEnum etatCorrection;

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

	public Date getDateCloture() {
		return dateCloture;
	}

	public void setDateCloture(final Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	public AnomalieEtatEnum getEtatCorrection() {
		return etatCorrection;
	}

	public void setEtatCorrection(final AnomalieEtatEnum etatCorrection) {
		this.etatCorrection = etatCorrection;
	}
}
