package com.sirh.mqd.reporting.persistence.entities;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 *
 * Entité d'action elasticsearch
 *
 */
@Document(indexName = "logstash-ihm*", type = "ihm", shards = 1, replicas = 0)
public class IHMActionEntity extends GenericLogstachEntity {

	private String action_ihm;

	private String email;

	private String role;

	private String action_type;

	private String action_nature;

	private String ecran_type;

	private String id_metier;

	private String date_authentification;

	public String getAction_ihm() {
		return action_ihm;
	}

	public void setAction_ihm(final String action_ihm) {
		this.action_ihm = action_ihm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(final String action_type) {
		this.action_type = action_type;
	}

	public String getAction_nature() {
		return action_nature;
	}

	public void setAction_nature(final String action_nature) {
		this.action_nature = action_nature;
	}

	public String getEcran_type() {
		return ecran_type;
	}

	public void setEcran_type(final String ecran_type) {
		this.ecran_type = ecran_type;
	}

	public String getId_metier() {
		return id_metier;
	}

	public void setId_metier(final String id_metier) {
		this.id_metier = id_metier;
	}

	public String getDate_authentification() {
		return date_authentification;
	}

	public void setDate_authentification(final String date_authentification) {
		this.date_authentification = date_authentification;
	}

}
