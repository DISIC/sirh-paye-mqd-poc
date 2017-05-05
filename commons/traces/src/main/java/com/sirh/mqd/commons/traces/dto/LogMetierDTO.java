package com.sirh.mqd.commons.traces.dto;

import java.io.Serializable;

import com.google.common.base.Joiner;
import com.sirh.mqd.commons.exchanges.constante.Constantes;
import com.sirh.mqd.commons.traces.enums.LogType;

public class LogMetierDTO implements Serializable {

	private static final long serialVersionUID = 4678054629112525685L;

	private String requestTimeStamp;

	private LogType typeMessage;

	private String service;

	private String domaine;

	private String typeStructure;

	private String actionValue;

	private String mode;

	private String sens;

	private String contrat;

	private String requestorRef;

	private int taille;

	private String lineRef;

	private String messageIdentifier;

	private String monitoringRef;

	private String operatorRef;

	private String subscriptionRef;

	private String errorConditionDesc;

	private String errorConditionStructure;

	private String errorConditionErrorText;

	private String errorConditionSpecificField;

	private String producerRef;

	private String description;

	private Boolean status;

	private String codeErreurIvtr;

	public LogMetierDTO() {
		super();
	}

	public LogMetierDTO(final String requestTimeStamp, final LogType typeMessage, final String service, final String domaine,
			final String typeStructure, final String actionValue, final String mode, final String sens, final String contrat,
			final String requestorRef, final int taille, final String lineRef, final String messageIdentifier, final String monitoringRef,
			final String operatorRef, final String subscriptionRef, final String errorConditionDesc, final String errorConditionStructure,
			final String errorConditionErrorText, final String errorConditionSpecificField, final String producerRef,
			final String description, final Boolean status, final String codeErreurIvtr) {
		this();
		this.requestTimeStamp = requestTimeStamp;
		this.typeMessage = typeMessage;
		this.service = service;
		this.domaine = domaine;
		this.typeStructure = typeStructure;
		this.actionValue = actionValue;
		this.mode = mode;
		this.sens = sens;
		this.contrat = contrat;
		this.requestorRef = requestorRef;
		this.taille = taille;
		this.lineRef = lineRef;
		this.messageIdentifier = messageIdentifier;
		this.monitoringRef = monitoringRef;
		this.operatorRef = operatorRef;
		this.subscriptionRef = subscriptionRef;
		this.errorConditionDesc = errorConditionDesc;
		this.errorConditionStructure = errorConditionStructure;
		this.errorConditionErrorText = errorConditionErrorText;
		this.errorConditionSpecificField = errorConditionSpecificField;
		this.producerRef = producerRef;
		this.description = description;
		this.status = status;
		this.codeErreurIvtr = codeErreurIvtr;
	}

	public String getRequestTimeStamp() {
		return requestTimeStamp;
	}

	public void setRequestTimeStamp(final String requestTimeStamp) {
		this.requestTimeStamp = requestTimeStamp;
	}

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(final String actionValue) {
		this.actionValue = actionValue;
	}

	public LogType getTypeMessage() {
		return typeMessage;
	}

	public void setTypeMessage(final LogType typeMessage) {
		this.typeMessage = typeMessage;
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

	public String getTypeStructure() {
		return typeStructure;
	}

	public void setTypeStructure(final String typeStructure) {
		this.typeStructure = typeStructure;
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

	public String getRequestorRef() {
		return requestorRef;
	}

	public void setRequestorRef(final String requestorRef) {
		this.requestorRef = requestorRef;
	}

	public String getLineRef() {
		return lineRef;
	}

	public void setLineRef(final String lineRef) {
		this.lineRef = lineRef;
	}

	public String getMessageIdentifier() {
		return messageIdentifier;
	}

	public void setMessageIdentifier(final String messageIdentifier) {
		this.messageIdentifier = messageIdentifier;
	}

	public String getMonitoringRef() {
		return monitoringRef;
	}

	public void setMonitoringRef(final String monitoringRef) {
		this.monitoringRef = monitoringRef;
	}

	public String getOperatorRef() {
		return operatorRef;
	}

	public void setOperatorRef(final String operatorRef) {
		this.operatorRef = operatorRef;
	}

	public String getSubscriptionRef() {
		return subscriptionRef;
	}

	public void setSubscriptionRef(final String subscriptionRef) {
		this.subscriptionRef = subscriptionRef;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(final int taille) {
		this.taille = taille;
	}

	public String getErrorConditionDesc() {
		return errorConditionDesc;
	}

	public void setErrorConditionDesc(final String errorConditionDesc) {
		this.errorConditionDesc = errorConditionDesc;
	}

	public String getErrorConditionStructure() {
		return errorConditionStructure;
	}

	public void setErrorConditionStructure(final String errorConditionStructure) {
		this.errorConditionStructure = errorConditionStructure;
	}

	public String getErrorConditionErrorText() {
		return errorConditionErrorText;
	}

	public void setErrorConditionErrorText(final String errorConditionErrorText) {
		this.errorConditionErrorText = errorConditionErrorText;
	}

	public String getErrorConditionSpecificField() {
		return errorConditionSpecificField;
	}

	public void setErrorConditionSpecificField(final String errorConditionSpecificField) {
		this.errorConditionSpecificField = errorConditionSpecificField;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getProducerRef() {
		return producerRef;
	}

	public void setProducerRef(final String producerRef) {
		this.producerRef = producerRef;
	}

	public Boolean isStatus() {
		return status;
	}

	public void setStatus(final Boolean status) {
		this.status = status;
	}

	public String getCodeErreurIvtr() {
		return codeErreurIvtr;
	}

	public void setCodeErreurIvtr(final String codeErreurIvtr) {
		this.codeErreurIvtr = codeErreurIvtr;
	}

	@Override
	public String toString() {

		StringBuilder logText = new StringBuilder();
		logText.append(Constantes.SPACE).append(Constantes.OPEN_SQUARE_BRACKET);
		Joiner joiner = Joiner.on(Constantes.CLOSE_SQUARE_BRACKET + Constantes.DASH + Constantes.OPEN_SQUARE_BRACKET).useForNull(
				Constantes.DASH);
		joiner.appendTo(logText, this.requestTimeStamp, this.typeMessage, "null".equals(this.service) ? "-" : this.service,
				"null".equals(this.domaine) ? "-" : this.domaine, this.typeStructure, this.actionValue, this.mode, this.sens, this.contrat,
				this.requestorRef, this.taille, this.lineRef, this.messageIdentifier, this.monitoringRef, this.operatorRef,
				this.subscriptionRef, this.errorConditionDesc, this.errorConditionStructure, this.errorConditionErrorText,
				this.errorConditionSpecificField, this.producerRef, this.description, this.status, this.codeErreurIvtr);
		logText.append(Constantes.CLOSE_SQUARE_BRACKET);
		return logText.toString();
	}
}
