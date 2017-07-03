package com.sirh.mqd.commons.exchanges.dto.pivot;

import com.sirh.mqd.commons.exchanges.enums.DossierAffectationEnum;
import com.sirh.mqd.commons.exchanges.enums.DossierDisponibiliteEnum;

/**
 * DTO permettant de manipuler les statut d'un dossier
 *
 * @author khalil
 */
public class StatutDossierDTO {

	private String payLot;

	private String renoiRHMatricule;

	private DossierDisponibiliteEnum disponibilite;

	private DossierAffectationEnum affectation;

	public StatutDossierDTO() {
		super();
	}

	public DossierDisponibiliteEnum getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(final DossierDisponibiliteEnum disponibilite) {
		this.disponibilite = disponibilite;
	}

	public DossierAffectationEnum getAffectation() {
		return affectation;
	}

	public void setAffectation(final DossierAffectationEnum affectation) {
		this.affectation = affectation;
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
}
