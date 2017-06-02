package com.sirh.mqd.commons.exchanges.enums;

/**
 * Enumération listant les noms des SIRH
 *
 * @author alexandre
 */
public enum InteractionSirhEnum {

	PAY("pay"),

	GA("renoirh"),

	MSO("mso"),

	DGAC("dgac");

	private String libelle;

	InteractionSirhEnum(final String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(final String libelle) {
		this.libelle = libelle;
	}
}
