package com.sirh.mqd.commons.exchanges.dto.calendrier;

import java.util.Date;

import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;

/**
 * DTO permettant de manipuler le calendrier gestion
 *
 * @author khalil
 */
public class EventCalendrierDTO {

	private String id;

	private InteractionSirhEnum referentiel;

	private String evenement;

	private String type;

	private Date debut;

	private Date echeance;

	private String acteurs;

	private String corps;

	private String service;

	private String couleur;

	public EventCalendrierDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
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

	public InteractionSirhEnum getReferentiel() {
		return referentiel;
	}

	public void setReferentiel(final InteractionSirhEnum referentiel) {
		this.referentiel = referentiel;
	}
}
