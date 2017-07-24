package com.sirh.mqd.commons.storage.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.sirh.mqd.commons.exchanges.enums.AlerteEtatEnum;
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

	@Indexed
	@Field(DossierConstantes.COLONNE_MATRICULE)
	private String matricule;

	@Field(AlerteConstantes.COLONNE_TYPE_DONNEE)
	private AnomalieTypeEnum type;

	@Field(AlerteConstantes.COLONNE_VALEUR_DONNEE)
	private String valeur;

	@Field(AlerteConstantes.COLONNE_ETAT)
	private AlerteEtatEnum etat;

	@Field(AlerteConstantes.COLONNE_RESPONSABLE_LOGIN)
	private String responsableLogin;

	@Field(AlerteConstantes.COLONNE_RESPONSABLE_NOM)
	private String responsableNom;

	@Field(AlerteConstantes.COLONNE_RESPONSABLE_PRENOM)
	private String responsablePrenom;

	@Field(AlerteConstantes.COLONNE_DATE_MODIFICATION)
	private Date dateModification;

	@Field(AlerteConstantes.COLONNE_DATE_ECHEANCE)
	private Date dateEcheance;

	@Field(AlerteConstantes.COLONNE_DATE_CLOTURE)
	private Date dateCloture;

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

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(final String matricule) {
		this.matricule = matricule;
	}

	public AnomalieTypeEnum getType() {
		return type;
	}

	public void setType(final AnomalieTypeEnum type) {
		this.type = type;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(final String valeur) {
		this.valeur = valeur;
	}

	public AlerteEtatEnum getEtat() {
		return etat;
	}

	public void setEtat(final AlerteEtatEnum etat) {
		this.etat = etat;
	}

	public String getResponsableLogin() {
		return responsableLogin;
	}

	public void setResponsableLogin(final String responsableLogin) {
		this.responsableLogin = responsableLogin;
	}

	public String getResponsableNom() {
		return responsableNom;
	}

	public void setResponsableNom(final String responsableNom) {
		this.responsableNom = responsableNom;
	}

	public String getResponsablePrenom() {
		return responsablePrenom;
	}

	public void setResponsablePrenom(final String responsablePrenom) {
		this.responsablePrenom = responsablePrenom;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(final Date dateModification) {
		this.dateModification = dateModification;
	}

	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(final Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public Date getDateCloture() {
		return dateCloture;
	}

	public void setDateCloture(final Date dateCloture) {
		this.dateCloture = dateCloture;
	}
}
