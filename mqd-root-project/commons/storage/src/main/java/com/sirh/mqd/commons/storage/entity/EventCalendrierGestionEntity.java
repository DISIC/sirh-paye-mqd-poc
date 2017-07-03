package com.sirh.mqd.commons.storage.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;
import com.sirh.mqd.commons.storage.constantes.EventCalendrierGestionConstantes;

/**
 * Entité des calendrier gestion
 *
 * @author khalil
 */
@Document(collection = EventCalendrierGestionConstantes.COLLECTION_NAME)
@CompoundIndexes({
		@CompoundIndex(name = "index_calendrier_service", def = "{'"
				+ EventCalendrierGestionConstantes.COLONNE_REFERENTIEL + "' : 1, '"
				+ EventCalendrierGestionConstantes.COLONNE_SERVICE + "' : 1}"),
		@CompoundIndex(name = "index_calendrier_range", def = "{'" + EventCalendrierGestionConstantes.COLONNE_DEBUT
				+ "' : 1, '" + EventCalendrierGestionConstantes.COLONNE_ECHEANCE + "' : 1}") })
public class EventCalendrierGestionEntity {

	@Id
	private String id;

	@Indexed
	@Field(EventCalendrierGestionConstantes.COLONNE_REFERENTIEL)
	private InteractionSirhEnum referentiel;

	@Field(EventCalendrierGestionConstantes.COLONNE_EVENEMENT)
	private String evenement;

	@Field(EventCalendrierGestionConstantes.COLONNE_TYPE)
	private String type;

	@Field(EventCalendrierGestionConstantes.COLONNE_DEBUT)
	private Date debut;

	@Field(EventCalendrierGestionConstantes.COLONNE_ECHEANCE)
	private Date echeance;

	@Field(EventCalendrierGestionConstantes.COLONNE_ACTEURS)
	private String acteurs;

	@Field(EventCalendrierGestionConstantes.COLONNE_CORPS)
	private String corps;

	@Indexed
	@Field(EventCalendrierGestionConstantes.COLONNE_SERVICE)
	private String service;

	@Field(EventCalendrierGestionConstantes.COLONNE_COULEUR)
	private String couleur;

	public EventCalendrierGestionEntity() {
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
