package com.sirh.mqd.commons.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.storage.constantes.AlerteConstantes;
import com.sirh.mqd.commons.storage.constantes.ComparaisonConstantes;
import com.sirh.mqd.commons.storage.constantes.DossierConstantes;

/**
 * Entité de gestion des alertes remontées suite à une pseudo correction d'une
 * anomalie
 *
 * @author alexandre
 */
@Document(collection = AlerteConstantes.COLLECTION_NAME)
@CompoundIndexes({
		@CompoundIndex(name = "index_alerte_sirh", def = "{'" + DossierConstantes.COLONNE_PAY_LOT + "' : 1, '"
				+ DossierConstantes.COLONNE_MATRICULE + "': 1, '" + ComparaisonConstantes.COLONNE_TYPE_DONNEE
				+ "': 1}"),
		@CompoundIndex(name = "index_alerte_dgac", def = "{'" + DossierConstantes.COLONNE_MATRICULE + "': 1, '"
				+ ComparaisonConstantes.COLONNE_TYPE_DONNEE + "': 1}"),
		@CompoundIndex(name = "index_alerte_dossier", def = "{'" + DossierConstantes.COLONNE_PAY_LOT + "' : 1, '"
				+ DossierConstantes.COLONNE_MATRICULE + "': 1}") })
public class AlerteEntity {

	@Id
	private String id;

	@Field(DossierConstantes.COLONNE_PAY_LOT)
	private String payLot;

	@Field(DossierConstantes.COLONNE_MATRICULE)
	private String renoiRHMatricule;

	@Field(ComparaisonConstantes.COLONNE_TYPE_DONNEE)
	private AnomalieTypeEnum type;

	public AlerteEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getPayLot() {
		return payLot;
	}

	public void setPayLot(final String payLot) {
		this.payLot = payLot;
	}

	public String getRenoiRHMatricule() {
		return renoiRHMatricule;
	}

	public void setRenoiRHMatricule(final String renoiRHMatricule) {
		this.renoiRHMatricule = renoiRHMatricule;
	}

	public AnomalieTypeEnum getType() {
		return type;
	}

	public void setType(final AnomalieTypeEnum type) {
		this.type = type;
	}
}
