package com.sirh.mqd.commons.exchanges.dto.historique;

import java.util.Date;

import com.sirh.mqd.commons.utils.DateUtils;

/**
 * DTO permettant d'avoir l'historique des actions utilisateurs et internes sur
 * le traitement des anomalies.
 *
 * @author alexandre
 */
public class HistoriqueDTO {

	private Date dateAction;

	private String acteur;

	private String description;

	public HistoriqueDTO() {
		super();
	}

	public Date getDateAction() {
		return dateAction;
	}

	public void setDateAction(final Date dateAction) {
		this.dateAction = DateUtils.clonerDate(dateAction);
	}

	public String getActeur() {
		return acteur;
	}

	public void setActeur(final String acteur) {
		this.acteur = acteur;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
}
