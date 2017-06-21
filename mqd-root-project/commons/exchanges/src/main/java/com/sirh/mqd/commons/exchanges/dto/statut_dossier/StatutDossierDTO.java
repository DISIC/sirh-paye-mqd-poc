package com.sirh.mqd.commons.exchanges.dto.statut_dossier;


/**
 * DTO permettant de manipuler les statut d'un dossier
 *
 * @author khalil
 */
public class StatutDossierDTO {

	private String payLot;

	private String renoiRHMatricule;

	private String disponibilite;

	private String affectation;

	public String getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(final String disponibilite) {
		this.disponibilite = disponibilite;
	}

	public String getAffectation() {
		return affectation;
	}

	public void setAffectation(final String affectation) {
		this.affectation = affectation;
	}

	public StatutDossierDTO() {
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
}
