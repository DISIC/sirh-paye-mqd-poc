package com.sirh.mqd.commons.storage.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.sirh.mqd.commons.storage.constantes.CommentaireConstantes;
import com.sirh.mqd.commons.storage.constantes.DossierConstantes;

/**
 * Entité des commentaires
 *
 * @author alexandre
 */
@Document(collection = CommentaireConstantes.COLLECTION_NAME)
@CompoundIndexes({ @CompoundIndex(name = "index_commentaire_dossier", def = "{'" + DossierConstantes.COLONNE_PAY_LOT
		+ "' : 1, '" + DossierConstantes.COLONNE_MATRICULE + "' : 1}") })
public class CommentaireEntity {

	@Id
	private String id;

	@Indexed
	@Field(DossierConstantes.COLONNE_PAY_LOT)
	private String payLot;

	@Indexed
	@Field(DossierConstantes.COLONNE_MATRICULE)
	private String renoiRHMatricule;

	@Field(CommentaireConstantes.COLONNE_DATE_CREATION)
	private Date dateCreation;

	@Field(CommentaireConstantes.COLONNE_DESCRIPTION)
	private String contenu;

	@Field(CommentaireConstantes.COLONNE_GESTIONNAIRE)
	private String utilisateur;

	public CommentaireEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(final Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(final String contenu) {
		this.contenu = contenu;
	}

	public String getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(final String utilisateur) {
		this.utilisateur = utilisateur;
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
}
