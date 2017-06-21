package com.sirh.mqd.reporting.webapp.model;

import java.io.Serializable;

public class StatutDossierModel implements Serializable{

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -3561569777820082043L;

	private String disponibilite;

	private String affectation;

	public StatutDossierModel() {
		super();
	}

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
}
