package com.sirh.mqd.commons.exchanges.dto.pivot;

import java.util.Date;

import com.sirh.mqd.commons.exchanges.enums.AnomalieEtatEnum;
import com.sirh.mqd.commons.exchanges.enums.AnomaliePerimetreEnum;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.utils.DateUtils;

/**
 * DTO permettant de manipuler les anomalies d'un dossier
 *
 * @author alexandre
 */
public class AnomalieDTO {

	private AnomaliePerimetreEnum perimetre;

	private AnomalieTypeEnum type;

	private DifferenceDTO donnees;

	private String modeOperatoire;

	private Date dateEcheance;

	private Date dateCloture;

	private AnomalieEtatEnum etatCorrection;

	public AnomalieDTO() {
		super();
	}

	public AnomaliePerimetreEnum getPerimetre() {
		return perimetre;
	}

	public void setPerimetre(final AnomaliePerimetreEnum perimetre) {
		this.perimetre = perimetre;
	}

	public AnomalieTypeEnum getType() {
		return type;
	}

	public void setType(final AnomalieTypeEnum type) {
		this.type = type;
	}

	public DifferenceDTO getDonnees() {
		return donnees;
	}

	public void setDonnees(final DifferenceDTO donnees) {
		this.donnees = donnees;
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
		this.dateEcheance = DateUtils.clonerDate(dateEcheance);
	}

	public Date getDateCloture() {
		return dateCloture;
	}

	public void setDateCloture(final Date dateCloture) {
		this.dateCloture = DateUtils.clonerDate(dateCloture);
	}

	public AnomalieEtatEnum getEtatCorrection() {
		return etatCorrection;
	}

	public void setEtatCorrection(final AnomalieEtatEnum etatCorrection) {
		this.etatCorrection = etatCorrection;
	}
}
