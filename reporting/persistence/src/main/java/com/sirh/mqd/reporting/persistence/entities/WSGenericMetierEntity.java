package com.sirh.mqd.reporting.persistence.entities;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 *
 * Entité d'echange elasticsearch
 *
 */
@Document(indexName = "logstash-met*", shards = 1, replicas = 0)
public class WSGenericMetierEntity extends GenericLogstachEntity {

	private String request_timestamp;

	private String type_message;

	private String service;

	private String domaine;

	private String type_structure;

	private String action_value;

	private String mode;

	private String sens;

	private String contrat;

	@Field(index = FieldIndex.not_analyzed, type = FieldType.String)
	private String requestor_ref;

	private int taille;

	private String line_ref;

	private String message_identifier;

	private String monitoring_ref;

	private String operator_ref;

	private String subscription_ref;

	private String error_condition_description;

	private String error_condition_structure;

	private String error_condition_error_text;

	private String error_condition_specific_field;

	private String producer_ref;

	private String description;

	private String status;

	private String code_erreur_ivtr;

	public String getRequest_timestamp() {
		return request_timestamp;
	}

	public void setRequest_timestamp(final String request_timestamp) {
		this.request_timestamp = request_timestamp;
	}

	@Override
	public String getLog_date() {
		if (super.getLog_date() == null) {
			setLog_date(getRequest_timestamp());
		}
		return super.getLog_date();
	}

	public String getType_message() {
		return type_message;
	}

	public void setType_message(final String type_message) {
		this.type_message = type_message;
	}

	public String getService() {
		return service;
	}

	public void setService(final String service) {
		this.service = service;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(final String domaine) {
		this.domaine = domaine;
	}

	public String getType_structure() {
		return type_structure;
	}

	public void setType_structure(final String type_structure) {
		this.type_structure = type_structure;
	}

	public String getAction_value() {
		return action_value;
	}

	public void setAction_value(final String action_value) {
		this.action_value = action_value;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(final String mode) {
		this.mode = mode;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(final String sens) {
		this.sens = sens;
	}

	public String getContrat() {
		return contrat;
	}

	public void setContrat(final String contrat) {
		this.contrat = contrat;
	}

	public String getRequestor_ref() {
		return requestor_ref;
	}

	public void setRequestor_ref(final String requestor_ref) {
		this.requestor_ref = requestor_ref;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(final int taille) {
		this.taille = taille;
	}

	public String getLine_ref() {
		return line_ref;
	}

	public void setLine_ref(final String line_ref) {
		this.line_ref = line_ref;
	}

	public String getMessage_identifier() {
		return message_identifier;
	}

	public void setMessage_identifier(final String message_identifier) {
		this.message_identifier = message_identifier;
	}

	public String getMonitoring_ref() {
		return monitoring_ref;
	}

	public void setMonitoring_ref(final String monitoring_ref) {
		this.monitoring_ref = monitoring_ref;
	}

	public String getOperator_ref() {
		return operator_ref;
	}

	public void setOperator_ref(final String operator_ref) {
		this.operator_ref = operator_ref;
	}

	public String getSubscription_ref() {
		return subscription_ref;
	}

	public void setSubscription_ref(final String subscription_ref) {
		this.subscription_ref = subscription_ref;
	}

	public String getError_condition_description() {
		return error_condition_description;
	}

	public void setError_condition_description(final String error_condition_description) {
		this.error_condition_description = error_condition_description;
	}

	public String getError_condition_structure() {
		return error_condition_structure;
	}

	public void setError_condition_structure(final String error_condition_structure) {
		this.error_condition_structure = error_condition_structure;
	}

	public String getError_condition_error_text() {
		return error_condition_error_text;
	}

	public void setError_condition_error_text(final String error_condition_error_text) {
		this.error_condition_error_text = error_condition_error_text;
	}

	public String getError_condition_specific_field() {
		return error_condition_specific_field;
	}

	public void setError_condition_specific_field(final String error_condition_specific_field) {
		this.error_condition_specific_field = error_condition_specific_field;
	}

	public String getProducer_ref() {
		return producer_ref;
	}

	public void setProducer_ref(final String producer_ref) {
		this.producer_ref = producer_ref;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public String getCode_erreur_ivtr() {
		return code_erreur_ivtr;
	}

	public void setCode_erreur_ivtr(final String code_erreur_ivtr) {
		this.code_erreur_ivtr = code_erreur_ivtr;
	}

}
