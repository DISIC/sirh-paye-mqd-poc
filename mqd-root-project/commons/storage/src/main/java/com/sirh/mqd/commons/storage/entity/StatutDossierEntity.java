package com.sirh.mqd.commons.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.sirh.mqd.commons.exchanges.enums.DossierAffectationEnum;
import com.sirh.mqd.commons.exchanges.enums.DossierDisponibiliteEnum;
import com.sirh.mqd.commons.storage.constantes.DossierConstantes;
import com.sirh.mqd.commons.storage.constantes.StatutDossierConstantes;

/**
 * Entité des statut dossier
 *
 * @author alexandre
 */
@Document(collection = StatutDossierConstantes.COLLECTION_NAME)
@CompoundIndexes({ @CompoundIndex(name = "index_statut_dossier", def = "{'" + DossierConstantes.COLONNE_PAY_LOT
		+ "' : 1, '" + DossierConstantes.COLONNE_MATRICULE + "' : 1}") })
public class StatutDossierEntity {

	@Id
	private String id;

	@Indexed
	@Field(DossierConstantes.COLONNE_PAY_LOT)
	private String payLot;

	@Indexed
	@Field(DossierConstantes.COLONNE_MATRICULE)
	private String renoiRHMatricule;

	@Field(StatutDossierConstantes.COLONNE_DISPONIBILITE)
	private DossierDisponibiliteEnum disponibilite;

	@Field(StatutDossierConstantes.COLONNE_AFFECTATION)
	private DossierAffectationEnum affectation;

	public StatutDossierEntity() {
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

	public DossierDisponibiliteEnum getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(final DossierDisponibiliteEnum disponibilite) {
		this.disponibilite = disponibilite;
	}

	public DossierAffectationEnum getAffectation() {
		return affectation;
	}

	public void setAffectation(final DossierAffectationEnum affectation) {
		this.affectation = affectation;
	}
}
