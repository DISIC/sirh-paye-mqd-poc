package com.sirh.mqd.commons.storage.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Entité des commentaires
 *
 * @author khalil
 */
@Document(collection = "commentaires")
@CompoundIndexes({@CompoundIndex(name = "index_commentaires_dossier", def = "{'pay_lot' : 1, 'matricule' : 1}")})
public class CommentaireEntity {

	@Id
	private String id;

	@Indexed
	@Field("pay_lot")
	private String payLot;

	@Indexed
	@Field("matricule")
	private String renoiRHMatricule;

	@Field("date_creation")
	private Date dateCreation;

	@Field("desciption")
	private String contenu;

	@Field("gestionnaire")
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
