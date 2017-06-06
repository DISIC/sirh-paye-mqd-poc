package com.sirh.mqd.commons.exchanges.dto.commentaire;

import java.util.Date;

import com.sirh.mqd.commons.utils.DateUtils;

/**
 * DTO permettant de manipuler les commentaires d'un dossier
 *
 * @author khalil
 */
public class CommentaireDTO {

	private String payLot;

	private String renoiRHMatricule;

	private Date dateCreation;

	private String contenu;

	private String utilisateur;

	public CommentaireDTO() {
		super();
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(final Date dateCreation) {
		this.dateCreation = DateUtils.clonerDate(dateCreation);
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
