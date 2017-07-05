package com.sirh.mqd.commons.storage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.storage.constantes.ComparaisonConstantes;
import com.sirh.mqd.commons.storage.constantes.DossierConstantes;
import com.sirh.mqd.commons.utils.constante.Constantes;

/**
 * Entité des comparaisons des données en entrée et de leur état : anomalies
 * détectées ou non
 *
 * @author alexandre
 */
@Document(collection = ComparaisonConstantes.COLLECTION_NAME)
@CompoundIndexes({
		@CompoundIndex(name = "index_comparaison_sirh", def = "{'" + DossierConstantes.COLONNE_PAY_LOT + "' : 1, '"
				+ DossierConstantes.COLONNE_MATRICULE + "': 1, '" + ComparaisonConstantes.COLONNE_TYPE_DONNEE
				+ "': 1}"),
		@CompoundIndex(name = "index_comparaison_dgac", def = "{'" + DossierConstantes.COLONNE_MATRICULE + "': 1, '"
				+ ComparaisonConstantes.COLONNE_TYPE_DONNEE + "': 1}"),
		@CompoundIndex(name = "index_comparaison_dossier", def = "{'" + DossierConstantes.COLONNE_PAY_LOT + "' : 1, '"
				+ DossierConstantes.COLONNE_MATRICULE + "': 1}"),
		@CompoundIndex(name = "index_anomalie_dossier_sirh", def = "{'" + DossierConstantes.COLONNE_PAY_LOT + "' : 1, '"
				+ DossierConstantes.COLONNE_MATRICULE + "': 1, '" + ComparaisonConstantes.COLONNE_ANOMALIE + "': 1, '"
				+ ComparaisonConstantes.COLONNE_ANOMALIE + Constantes.DOT
				+ ComparaisonConstantes.COLONNE_ANOMALIE_DATE_CLOTURE + "': 1}"),
		@CompoundIndex(name = "index_anomalie_dossier_dgac", def = "{'" + DossierConstantes.COLONNE_MATRICULE
				+ "': 1, '" + ComparaisonConstantes.COLONNE_ANOMALIE + "': 1, '"
				+ ComparaisonConstantes.COLONNE_ANOMALIE + Constantes.DOT
				+ ComparaisonConstantes.COLONNE_ANOMALIE_DATE_CLOTURE + "': 1}") })
public class ComparaisonEntity {

	@Id
	private String id;

	@Field(DossierConstantes.COLONNE_PAY_LOT)
	private String payLot;

	@Field(DossierConstantes.COLONNE_MATRICULE)
	private String renoiRHMatricule;

	@Field(ComparaisonConstantes.COLONNE_TYPE_DONNEE)
	private AnomalieTypeEnum type;

	@Field(ComparaisonConstantes.COLONNE_ANOMALIE_REOUVERTE)
	private boolean anomalieReouverte;

	@Field(ComparaisonConstantes.COLONNE_ANOMALIE)
	private AnomalieEntity anomalie;

	@Field(ComparaisonConstantes.COLONNE_DONNEES)
	private DifferenceEntity donnees;

	public ComparaisonEntity() {
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

	public AnomalieEntity getAnomalie() {
		return anomalie;
	}

	public void setAnomalie(final AnomalieEntity anomalie) {
		this.anomalie = anomalie;
	}

	public DifferenceEntity getDonnees() {
		return donnees;
	}

	public void setDonnees(final DifferenceEntity donnees) {
		this.donnees = donnees;
	}

	public boolean isAnomalieReouverte() {
		return anomalieReouverte;
	}

	public void setAnomalieReouverte(final boolean anomalieReouverte) {
		this.anomalieReouverte = anomalieReouverte;
	}
}
