package com.sirh.mqd.commons.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.sirh.mqd.commons.storage.constantes.ConfigConstantes;

/**
 * Entité des paramètres saisis dynamiquement
 *
 * @author khalil
 */
@Document(collection = ConfigConstantes.COLLECTION_NAME)
public class ConfigEntity {

	@Id
	@Field(ConfigConstantes.COLONNE_ID)
	private String id;

	@Field(ConfigConstantes.COLONNE_VALEUR)
	private Object valeur;

	public ConfigEntity() {
		super();
	}

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
