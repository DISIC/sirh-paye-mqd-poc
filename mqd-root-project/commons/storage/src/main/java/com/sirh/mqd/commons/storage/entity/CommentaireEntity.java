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

	@Field(CommentaireConstantes.COLONNE_AUTEUR_LOGIN)
	private String auteurLogin;

	@Field(CommentaireConstantes.COLONNE_AUTEUR_NOM)
	private String auteurNom;

	@Field(CommentaireConstantes.COLONNE_AUTEUR_PRENOM)
	private String auteurPrenom;

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

	public String getAuteurLogin() {
		return auteurLogin;
	}

	public void setAuteurLogin(final String auteurLogin) {
		this.auteurLogin = auteurLogin;
	}

	public String getAuteurNom() {
		return auteurNom;
	}

	public void setAuteurNom(final String auteurNom) {
		this.auteurNom = auteurNom;
	}

	public String getAuteurPrenom() {
		return auteurPrenom;
	}

	public void setAuteurPrenom(final String auteurPrenom) {
		this.auteurPrenom = auteurPrenom;
	}
}
