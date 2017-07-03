package com.sirh.mqd.commons.storage.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;
import com.sirh.mqd.commons.storage.constantes.EventCalendrierConstantes;

/**
 * Entité des événements d'un calendrier gestion
 *
 * @author khalil
 */
@Document(collection = EventCalendrierConstantes.COLLECTION_NAME)
@CompoundIndexes({
		@CompoundIndex(name = "index_calendrier_service", def = "{'" + EventCalendrierConstantes.COLONNE_REFERENTIEL
				+ "' : 1, '" + EventCalendrierConstantes.COLONNE_SERVICE + "' : 1}"),
		@CompoundIndex(name = "index_calendrier_range", def = "{'" + EventCalendrierConstantes.COLONNE_DEBUT
				+ "' : 1, '" + EventCalendrierConstantes.COLONNE_ECHEANCE + "' : 1}") })
public class EventCalendrierEntity {

	@Id
	private String id;

	@Indexed
	@Field(EventCalendrierConstantes.COLONNE_REFERENTIEL)
	private InteractionSirhEnum referentiel;

	@Field(EventCalendrierConstantes.COLONNE_EVENEMENT)
	private String evenement;

	@Field(EventCalendrierConstantes.COLONNE_TYPE)
	private String type;

	@Indexed
	@Field(EventCalendrierConstantes.COLONNE_DEBUT)
	private Date debut;

	@Indexed
	@Field(EventCalendrierConstantes.COLONNE_ECHEANCE)
	private Date echeance;

	@Field(EventCalendrierConstantes.COLONNE_ACTEURS)
	private String acteurs;

	@Field(EventCalendrierConstantes.COLONNE_CORPS)
	private String corps;

	@Indexed
	@Field(EventCalendrierConstantes.COLONNE_SERVICE)
	private String service;

	@Field(EventCalendrierConstantes.COLONNE_COULEUR)
	private String couleur;

	public EventCalendrierEntity() {
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
