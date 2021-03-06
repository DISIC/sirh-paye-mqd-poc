package com.sirh.mqd.reporting.webapp.model;

import java.io.Serializable;
import java.util.Date;

public class EventCalendrierModel implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -5741918602888434247L;

	private String evenement;

	private String type;

	private Date debut;

	private Date echeance;

	private String acteurs;

	private String corps;

	private String service;

	private String couleur;

	private String commentaire;

	public EventCalendrierModel() {
		super();
	}

	public String getEvenement() {
		return evenement;
	}

	public void setEvenement(final String evenement) {
		this.evenement = evenement;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(final Date debut) {
		this.debut = debut;
	}

	public Date getEcheance() {
		return echeance;
	}

	public void setEcheance(final Date echeance) {
		this.echeance = echeance;
	}

	public String getActeurs() {
		return acteurs;
	}

	public void setActeurs(final String acteurs) {
		this.acteurs = acteurs;
	}

	public String getCorps() {
		return corps;
	}

	public void setCorps(final String corps) {
		this.corps = corps;
	}

	public String getService() {
		return service;
	}

	public void setService(final String service) {
		this.service = service;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(final String couleur) {
		this.couleur = couleur;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(final String commentaire) {
		this.commentaire = commentaire;
	}
}
