package com.sirh.mqd.reporting.webapp.model;

import java.io.Serializable;

import com.sirh.mqd.commons.exchanges.enums.DossierAffectationEnum;
import com.sirh.mqd.commons.exchanges.enums.DossierDisponibiliteEnum;

public class StatutDossierModel implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -3561569777820082043L;

	private DossierDisponibiliteEnum disponibilite;

	private DossierAffectationEnum affectation;

	public StatutDossierModel() {
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
}
