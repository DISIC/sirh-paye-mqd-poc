package com.sirh.mqd.commons.exchanges.dto.pivot;

/**
 * DTO correspondant au paramètres saisies dynamiquement
 *
 * @author khalil
 */
public class ConfigDTO {

	private String id;

	private Object valeur;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Object getValeur() {
		return valeur;
	}

	public void setValeur(final Object valeur) {
		this.valeur = valeur;
	}
}
