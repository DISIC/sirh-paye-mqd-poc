package com.sirh.mqd.reporting.webapp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Model d'anomalie d'un dossier à manipuler dans la partie Vue
 *
 * @author khalil
 */

public class HistoriqueModel implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -1889898358932694951L;

	private Date dateHistorique;

	private String utilisateurHistorique;

	private String operationHistorique;

	private String objetHistorique;

	public HistoriqueModel() {
		super();
	}

	public Date getDateHistorique() {
		return dateHistorique;
	}

	public void setDateHistorique(final Date dateHistorique) {
		this.dateHistorique = dateHistorique;
	}

	public String getUtilisateurHistorique() {
		return utilisateurHistorique;
	}

	public void setUtilisateurHistorique(final String utilisateurHistorique) {
		this.utilisateurHistorique = utilisateurHistorique;
	}

	public String getOpérationHistorique() {
		return operationHistorique;
	}

	public void setOperationHistorique(final String operationHistorique) {
		this.operationHistorique = operationHistorique;
	}

	public String getObjetHistorique() {
		return objetHistorique;
	}

	public void setObjetHistorique(final String objetHistorique) {
		this.objetHistorique = objetHistorique;
	}
}
